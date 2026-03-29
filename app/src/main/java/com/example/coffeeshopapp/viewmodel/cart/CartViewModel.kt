package com.example.coffeeshopapp.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.mapper.toUiModel
import com.example.coffeeshopapp.data.model.CartItemUi
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.data.repository.cart.CartRepository
import com.example.coffeeshopapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CartState(isLoading = true))
    val state = _state.asStateFlow()


    init {
        observeCartItems()
    }


    private fun observeCartItems() {
        viewModelScope.launch {
            repository.observeCart()
                .catch { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
                .collect { cartEntities ->
                    val itemsUi: List<CartItemUi> = cartEntities.map { it.toUiModel() }
                    val subtotal = itemsUi.sumOf { it.total }
                    val tax = subtotal * Constants.TAX_PERCENT
                    val total = subtotal + tax
                    _state.value = CartState(
                        items = itemsUi,
                        subtotal = subtotal,
                        tax = tax,
                        total = total,
                        isEmpty = itemsUi.isEmpty(),
                        isLoading = false,
                        error = null
                    )

                }
        }
    }

    fun increaseQtyById(id: Int) {
        viewModelScope.launch {
            repository.increaseQty(id)
        }
    }

    fun decreaseQtyById(id: Int) {
        viewModelScope.launch {
            repository.decreaseQty(id)
        }
    }

    fun removeItemById(id: Int) {
        viewModelScope.launch {
            repository.remove(id)
        }
    }

    fun addToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            repository.addToCart(product, quantity)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

}