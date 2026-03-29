package com.example.coffeeshopapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentVerifyEmailBinding
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VerifyEmailFragment : Fragment() {

    private var _binding : FragmentVerifyEmailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       _binding = FragmentVerifyEmailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        observeState()
        observeEvent()
    }


    private fun setUpClicks(){
       binding.tvResend.setOnClickListener {
           viewModel.resendVerificationEmail()
       }
        binding.tvContinueHome.setOnClickListener {
            viewModel.reloadUser()
        }

    }


    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.tvContinueHome.isEnabled = !state.isLoading
                binding.tvResend.isEnabled = !state.isLoading
            }
        }
    }

private fun observeEvent(){
    viewLifecycleOwner.lifecycleScope.launch {
        viewModel.authEvent.collectLatest { event ->
            when(event){
                is AuthUiEvent.ShowMessage -> binding.root.showSnack(event.message)
                AuthUiEvent.NavigateToHome -> navigateSafely(R.id.action_verifyEmailFragment_to_homeFragment)
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