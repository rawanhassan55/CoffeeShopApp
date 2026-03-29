package com.example.coffeeshopapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentSplashBinding
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //just in the first time
      //  FirestoreSeeder.seedProducts()

        observeEvents()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            viewModel.checkAuthStatus()
        }
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authEvent.collect { event ->
                when (event) {
                    AuthUiEvent.NavigateToOnboarding -> {
                        navigateSafely(action = R.id.action_splashFragment_to_onboardingFragment)
                    }

                    AuthUiEvent.NavigateToHome -> {
                        navigateSafely(action = R.id.action_splashFragment_to_homeFragment)
                    }

                    AuthUiEvent.NavigateToLogin -> {
                        navigateSafely(R.id.action_splashFragment_to_loginFragment)
                    }

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


