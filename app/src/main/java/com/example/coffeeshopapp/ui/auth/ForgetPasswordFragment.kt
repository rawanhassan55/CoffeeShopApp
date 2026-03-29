package com.example.coffeeshopapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.databinding.FragmentForgetPasswordBinding
import com.example.coffeeshopapp.utils.extensions.goBack
import com.example.coffeeshopapp.utils.extensions.hideKeyboard
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgetPasswordFragment : Fragment() {

    private var _binding :FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
       _binding = FragmentForgetPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        observeState()
        observeEvent()
    }

    private fun setUpClicks(){
        binding.btnSendLink.setOnClickListener {
            binding.root.hideKeyboard()
            val email = binding.etEmail.text.toString().trim()
            viewModel.resetPassword(email)
        }
    }

    private fun observeState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collectLatest { state ->
                binding.progressBar.visibility = if(state.isLoading) View.VISIBLE else View.GONE
                binding.btnSendLink.isEnabled = !state.isLoading
            }

        }
    }

    private fun observeEvent(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authEvent.collectLatest { event ->
                when(event){
                    is AuthUiEvent.ShowMessage -> binding.root.showSnack(event.message)
                    is AuthUiEvent.NavigateBack -> goBack()
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
