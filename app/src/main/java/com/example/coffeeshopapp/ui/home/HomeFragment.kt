package com.example.coffeeshopapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.Category
import com.example.coffeeshopapp.data.model.CoffeeCategory
import com.example.coffeeshopapp.databinding.FragmentHomeBinding
import com.example.coffeeshopapp.ui.adapter.CategoryAdapter
import com.example.coffeeshopapp.ui.adapter.ProductAdapter
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.cart.CartViewModel
import com.example.coffeeshopapp.viewmodel.product.ProductsUiEvent
import com.example.coffeeshopapp.viewmodel.product.ProductsUiState
import com.example.coffeeshopapp.viewmodel.product.ProductsViewModel
import com.example.coffeeshopapp.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by activityViewModels()


    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private var categories = CoffeeCategory.entries.mapIndexed { index, cat ->
        Category(cat.displayName(), isSelected = index == 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryRecyclerView()
        setupProductRecyclerView()
        observeUser()
        observeUiState()
        observeUiEvent()

        selectCategory(categories.first())

        //  Search
        binding.etSearch.addTextChangedListener {
            viewModel.onSearchQueryChanged(it.toString())
        }

        //  Filter
        binding.ivFilter.setOnClickListener {
            openFilterBottomSheet()
        }
    }

    private fun openFilterBottomSheet() {
        val bottomSheet = FilterBottomSheetFragment { filter ->
            if (filter.price == null && filter.sort == null) {
                viewModel.clearFilters()
            } else {
                viewModel.applyFilters(filter.price, filter.sort)
            }
           // updateFilterIcon()
        }
        bottomSheet.show(parentFragmentManager, "FilterBottomSheet")
    }

    /*private fun updateFilterIcon() {
        val color = if (viewModel.isFilterActive())
            R.color.primary_color
        else
            R.color.gray

        binding.ivFilter.setCardBackgroundColor(requireContext().getColor(color))
    }*/

    // it works only one time when screen opens
    private fun setupCategoryRecyclerView() {
        categoryAdapter = CategoryAdapter { selectCategory(it) }

        binding.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

//it related with ui only ,screen will open (category selected + colors change)
        categoryAdapter.submitList(categories)
    }

    // work everytime you clicked on category
    private fun selectCategory(selectedCategory: Category) {

        //to reduce network calls and unuseful reload
        if (selectedCategory.isSelected) return

        /* make new immutable copy of all item ("oldList = 1,2,3" ,"newList = 2,4,6") ,
        true if ,The name of the current element (it) is equal to the name of the element that the user selected.
 and it is the best way with rv ,then we will observe the changed list,and can update screen */
        categories = categories.map {
            it.copy(isSelected = it.name == selectedCategory.name)
        }

        //when click to a new category ,update ui ( i give him new list)
        categoryAdapter.submitList(categories)

        //it is upper case because it in enum and firestore as upper case
        viewModel.refreshProducts(
            selectedCategory.name.uppercase(Locale.US)
        )

    }

    private fun setupProductRecyclerView() {
        productAdapter = ProductAdapter(
            onAddClick = { product ->
                cartViewModel.addToCart(product, quantity = 1)
                binding.root.showSnack("${product.name} added to cart!")
            },
            onItemClick = { product ->
                viewModel.onProductClicked(product.id)
            }
        )

        binding.rvProducts.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is ProductsUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvProducts.visibility = View.GONE
                        }

                        is ProductsUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvProducts.visibility = View.VISIBLE
                            productAdapter.submitList(state.product)
                        }

                        is ProductsUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvProducts.visibility = View.GONE
                            binding.root.showSnack(state.message)
                        }
                    }
                }
            }
        }
    }

    private fun observeUiEvent() {
        lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is ProductsUiEvent.NavigateToDetails -> {
                        val navController = findNavController()
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment2(event.productId)
                        if (navController.currentDestination?.id == R.id.homeFragment) {
                            navController.navigate(action)
                        }


                    }

                    is ProductsUiEvent.ShowMessage -> {
                        binding.root.showSnack(event.message)
                    }


                }
            }
        }
    }

    private fun observeUser() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.user.collect { user ->

                    // name
                    if (user.name.isNotEmpty()) {
                        binding.tvUserName.text = user.name
                    } else {
                        binding.tvUserName.text = getString(R.string.guest)
                    }

                    // image
                    if (user.image.isNotEmpty()) {
                        Glide.with(requireContext())
                            .load(user.image)
                            .centerCrop()
                            .into(binding.imgProfile)
                    } else {
                        binding.imgProfile.setImageResource(R.drawable.person_ic)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
