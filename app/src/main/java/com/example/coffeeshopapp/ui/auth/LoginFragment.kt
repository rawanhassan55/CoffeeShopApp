package com.example.coffeeshopapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentLoginBinding
import com.example.coffeeshopapp.utils.extensions.hideKeyboard
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

   private var _binding : FragmentLoginBinding?= null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        observeEvents()
        setUpClicks()
    }

    private fun setUpClicks(){
        binding.loginBtn.setOnClickListener {
            binding.root.hideKeyboard()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passEt.text.toString().trim()
            viewModel.login(email, password)
        }

        binding.signupTv.setOnClickListener { navigateSafely(R.id.action_loginFragment_to_signUpFragment) }

        binding.forgetPassTv.setOnClickListener { navigateSafely(R.id.action_loginFragment_to_forgetPasswordFragment) }

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                binding.loginBtn.isEnabled = !state.isLoading
            }
        }

    }
    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authEvent.collectLatest { event ->
                when (event) {
                    is AuthUiEvent.ShowMessage -> binding.root.showSnack(event.message)
                    AuthUiEvent.NavigateToHome -> navigateSafely(R.id.action_loginFragment_to_homeFragment)
                    AuthUiEvent.NavigateToVerifyEmail -> navigateSafely(R.id.action_loginFragment_to_verifyEmailFragment)
                    else -> Unit
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}




