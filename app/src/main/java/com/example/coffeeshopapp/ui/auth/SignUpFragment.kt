package com.example.coffeeshopapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.UserModel
import com.example.coffeeshopapp.databinding.FragmentSignUpBinding
import com.example.coffeeshopapp.utils.UserDataStore
import com.example.coffeeshopapp.utils.extensions.hideKeyboard
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

  private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var userDataStore: UserDataStore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClicks()
        observeState()
        observeEvent()
    }

    private fun setUpClicks(){
        binding.signUpBtn.setOnClickListener {
            binding.root.hideKeyboard()
            val name = binding.userEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passEt.text.toString().trim()
            viewModel.signUp(name,email,password)
        }
        binding.signInTv.setOnClickListener { navigateSafely(R.id.action_signUpFragment_to_loginFragment) }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.signUpBtn.isEnabled = !state.isLoading
            }
        }
    }

    private fun observeEvent(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authEvent.collectLatest { event ->
                when(event){
                    is AuthUiEvent.ShowMessage -> binding.root.showSnack(event.message)
                    is AuthUiEvent.NavigateToVerifyEmail ->{
                        saveUserLocally()
                        navigateSafely(R.id.action_signUpFragment_to_verifyEmailFragment)
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun saveUserLocally(){

        viewLifecycleOwner.lifecycleScope.launch {

            val user = UserModel(
                name = binding.userEt.text.toString().trim(),
                email = binding.emailEt.text.toString().trim(),
                phone = "",
                birthday = "",
                image = ""

            )

            userDataStore.saveUser(user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

