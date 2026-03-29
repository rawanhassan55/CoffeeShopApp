package com.example.coffeeshopapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentCartBinding
import com.example.coffeeshopapp.ui.adapter.CartAdapter
import com.example.coffeeshopapp.utils.extensions.goBack
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.cart.CartViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    val binding get() = _binding!!

    private val viewModel: CartViewModel by activityViewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCartRecyclerView()
        setupListeners()
        observeState()


    }

    private fun setupCartRecyclerView() {
        cartAdapter = CartAdapter(
            onPlusClick = { productId ->
                viewModel.increaseQtyById(productId)
            },
            onMinusClick = { productId ->
                viewModel.decreaseQtyById(productId)
            },
            onRemoveClick = { productId ->
                viewModel.removeItemById(productId)
            }
        )
        binding.rvCartItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
            itemAnimator =  null // to remove flickering
        }
    }

    private fun setupListeners() {
        binding.ivBack.setOnClickListener {
            goBack()
        }

        binding.btnCheckout.setOnClickListener {

            if (viewModel.state.value.items.isEmpty()) {
              binding.root.showSnack("Cart is empty")
                return@setOnClickListener
            }

            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setIcon(R.drawable.ic_help_circle)
                .setTitle("Confirm Checkout")
                .setMessage("Do you want to proceed with the checkout?")
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Confirm") { dialog, _ ->

                    dialog.dismiss()

                    // Loading Button
                    binding.btnCheckout.isEnabled = false
                    binding.btnCheckout.text = ""
                    binding.progressCheckout.visibility = View.VISIBLE

                    // Delay
                    viewLifecycleOwner.lifecycleScope.launch {
                        kotlinx.coroutines.delay(500)
                        // Navigation
                        navigateSafely(R.id.action_cartFragment_to_checkoutFragment)
                    }

                    // clear cart
                    viewModel.clearCart()
                }
                .show()

            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.green))

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }

        binding.emptyState.btnBrowseCoffee.setOnClickListener {
            navigateSafely(R.id.action_cartFragment_to_homeFragment)
        }

    }

        private fun observeState() {
     viewLifecycleOwner.lifecycleScope.launch {
         viewModel.state.collectLatest { state ->

             cartAdapter.submitList(state.items)


             if (state.isEmpty) {
                 binding.emptyState.layoutEmptyCart.visibility = View.VISIBLE
                 binding.rvCartItems.visibility = View.GONE
                 binding.layoutSummary.visibility = View.GONE
                 binding.emptyState.lottieEmptyCart.playAnimation()
             } else {
                 binding.emptyState.layoutEmptyCart.visibility = View.GONE
                 binding.rvCartItems.visibility = View.VISIBLE
                 binding.layoutSummary.visibility = View.VISIBLE
             }

             binding.tvSubtotalCart.text =
               getString(R.string.subtotal_format, state.subtotal)

             binding.tvTaxValue.text =
                 getString(R.string.price_format, state.tax)

             binding.tvTotalValue.text =
                 getString(R.string.price_format, state.total)

             binding.progressBar.visibility =
                 if(state.isLoading) View.VISIBLE else View.GONE

             binding.btnCheckout.isEnabled = !state.isLoading

         }
     }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

