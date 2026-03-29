package com.example.coffeeshopapp.viewmodel.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.model.UserModel
import com.example.coffeeshopapp.utils.UserDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application)
    : AndroidViewModel(application) {

    private val dataStore = UserDataStore(application)

    val user = dataStore.userFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UserModel("", "", "", "", "")
    )

  fun updateUser(updatedUser: UserModel) {
        viewModelScope.launch {
            dataStore.saveUser(updatedUser)
        }
    }
}