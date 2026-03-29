package com.example.coffeeshopapp.utils.result

inline fun <T> safeCall(action: () -> T): ResultWrapper<T> =
    try { ResultWrapper.Success(action()) }
    catch (e: Exception) { ResultWrapper.Error(mapError(e))
    }
