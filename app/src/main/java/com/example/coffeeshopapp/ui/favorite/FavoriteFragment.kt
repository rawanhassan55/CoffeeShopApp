package com.example.coffeeshopapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.databinding.FragmentFavoriteBinding
import com.example.coffeeshopapp.ui.adapter.FavoriteAdapter
import com.example.coffeeshopapp.utils.extensions.goBack
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.cart.CartViewModel
import com.example.coffeeshopapp.viewmodel.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.syncFavorites()

        setupRecyclerView()
        setupObservers()
        setupClicks()
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(
            onItemClick = { product ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment2(product.id)
                findNavController().navigate(action)
            },
            onFavoriteClick = { product ->
                viewModel.toggleFavorite(product)
            },
            onAddClick = { product ->
                cartViewModel.addToCart(product, quantity = 1)
                binding.root.showSnack("${product.name} added to cart!")
            }
        )

        binding.rvfavItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteProducts.collectLatest { products ->
                favoriteAdapter.submitList(products.toList())
            }
        }
    }

    private fun setupClicks() {
        binding.ivBack.setOnClickListener {
            goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}