package com.example.coffeeshopapp.viewmodel.product

 sealed class ProductsUiEvent {
//// One-time message (Toast/Snack bar), does NOT persist on rotation
     data class ShowMessage(val message: String) : ProductsUiEvent()

     data class NavigateToDetails(val productId: Int) : ProductsUiEvent()

     //if you want to make another somethings like animation or otherwise ,not just show message
    // data object ProductAddedToCart : ProductsUiEvent()
}

