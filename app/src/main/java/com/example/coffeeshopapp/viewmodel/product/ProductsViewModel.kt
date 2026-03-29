package com.example.coffeeshopapp.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.mapper.toProduct
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.data.repository.firebase.firestore.ProductsRepository
import com.example.coffeeshopapp.data.model.CoffeeCategory
import com.example.coffeeshopapp.utils.result.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository,
) : ViewModel() {

    // UI State
    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    // UI Event
    private val _uiEvent = MutableSharedFlow<ProductsUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    // original data (Local)
    private var allProducts: List<Product> = emptyList()

    // search & filter
    private var currentSearchQuery: String = ""
    private var currentPrice: String? = null
    private var currentSort: String? = null
    private var currentCategory: String? = null

    init {
        observeProducts()
    }

    // Room (Offline First)
    private fun observeProducts() {
        viewModelScope.launch {
            repository.observeProducts().collect { entities ->
                allProducts = entities.map { it.toProduct() }
                //filter based on selected category
                applySearchAndFilter(selectedCategory = currentCategory)
            }
        }
    }

    // update data from firestore
    fun refreshProducts(category: String) {
        currentCategory = category.uppercase(Locale.US) //store current category
        viewModelScope.launch {
            //after update, we collect from Local Room to show changes
            when (val result = repository.refreshProducts(currentCategory!!)) {
                is ResultWrapper.Success -> {
                   // data will refresh
                }
                is ResultWrapper.Error -> {
                    _uiEvent.emit(ProductsUiEvent.ShowMessage(result.message))
                }
            }
        }
    }

    // click on product
    fun onProductClicked(productId: Int) {
        viewModelScope.launch {
            _uiEvent.emit(ProductsUiEvent.NavigateToDetails(productId))
        }
    }


    // Search
//    fun onSearchQueryChanged(query: String) {
//        currentSearchQuery = query
//        applySearchAndFilter(selectedCategory = currentCategory)
//    }

    private val searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .collect { query ->
                    currentSearchQuery = query
                    applySearchAndFilter(selectedCategory = currentCategory)
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            searchQuery.emit(query)
        }
    }

    //  Filters
    fun applyFilters(price: String?, sort: String?) {
        currentPrice = price
        currentSort = sort
        applySearchAndFilter(selectedCategory = currentCategory)
    }

    fun clearFilters() {
        currentPrice = null
        currentSort = null
        currentSearchQuery = ""
        _uiState.value = ProductsUiState.Success(allProducts)

        viewModelScope.launch {
            _uiEvent.emit(ProductsUiEvent.ShowMessage("Filters cleared"))
        }
    }

    // apply search and filter on local data
    private fun applySearchAndFilter(selectedCategory: String? = null) {
        var result = allProducts

        // filter on category
        selectedCategory?.let { cat ->
            result = result.filter { product ->
                //transform product.category from String to Enum and compare
                val productCategory = CoffeeCategory.fromString(product.category.toString())
                productCategory.name.equals(cat, ignoreCase = true)
            }
        }


        // Search
        if (currentSearchQuery.isNotBlank()) {
            result = result.filter {
                it.name.contains(currentSearchQuery, ignoreCase = true)
            }
        }

        // Price
        currentPrice?.let {
            result = result.filter { product ->
                when (it) {
                    "Any" -> true
                    "Under 350" -> product.price < 350
                    "Above 350" -> product.price >= 350
                    else -> true
                }
            }
        }

        // Sort
        currentSort?.let {
            result = when (it) {
                "Price Low → High" -> result.sortedBy { p -> p.price }
                "Price High → Low" -> result.sortedByDescending { p -> p.price }
                "Rating High → Low" -> result.sortedByDescending { p -> p.rating }
                else -> result
            }
        }

        _uiState.value = ProductsUiState.Success(result)
    }



}
