package com.example.coffeeshopapp.ui.details

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.databinding.FragmentDetailsBinding
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.cart.CartViewModel
import com.example.coffeeshopapp.viewmodel.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    private var currentProduct: Product? = null
    private val args: DetailsFragmentArgs by navArgs()

    private var quantity = 1
    private var expanded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProducts(args.productId)

        observeProduct()
        setupClicks()
        updateQuantityUI()
        setupInitialDescriptionState()
    }

    private fun observeProduct() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.product.collectLatest { product ->
                    product?.let {
                        currentProduct = it
                        bindProduct(it)
                    }
                }
            }
        }
    }

    private fun bindProduct(product: Product) {
        Glide.with(requireContext())
            .load(product.imageUrl)
            .placeholder(R.drawable.cappuccino_1)
            .into(binding.imgCoffee)

        binding.tvTitle.text = product.name
        binding.tvSubtitle.text = product.subtitle
        binding.tvRating.text = getString(R.string.rating_format, product.rating)
        binding.tvDescription.text = product.description
        binding.tvPrice.text = getString(R.string.price_format, product.price)

        // add fav
        binding.btnFav.setImageResource(
            if (product.isFavorite)
                R.drawable.favorite_ic
            else
                R.drawable.favorite_border_ic
        )
    }

    private fun setupClicks() {

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnFav.setOnClickListener {

            currentProduct?.let { product ->
                viewModel.toggleFavorite(product)

                val message =
                    if (product.isFavorite) "Removed from favorite"
                    else "Added to favorite"

                view?.showSnack(message)
            }
        }

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantityUI()
            }
        }

        binding.btnPlus.setOnClickListener {
            quantity++
            updateQuantityUI()
        }

        binding.btnBuyNow.setOnClickListener {
            currentProduct?.let { product ->
                cartViewModel.addToCart(product, quantity)
                navigateSafely(R.id.action_detailsFragment2_to_cartFragment)
            }
        }

        binding.tvReadMore.setOnClickListener {
            expanded = !expanded

            binding.tvDescription.maxLines =
                if (expanded) Int.MAX_VALUE else 3

            binding.tvReadMore.text =
                if (expanded) "Show less ↑" else "Read more →"
        }
    }

    private fun updateQuantityUI() {
        binding.tvQuantity.text = quantity.toString()
        binding.btnMinus.isEnabled = quantity > 1
    }

    private fun setupInitialDescriptionState() {
        binding.tvDescription.maxLines = 3
        binding.tvReadMore.text = getString(R.string.read_more)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
