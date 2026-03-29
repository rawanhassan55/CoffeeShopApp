package com.example.coffeeshopapp.utils.result

 sealed class ResultWrapper < out T>{
     data class Success<out T>(val data: T) : ResultWrapper<T>()
     data class Error(val message : String, val cause: Throwable? = null) : ResultWrapper<Nothing>()
 }