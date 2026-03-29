package com.example.coffeeshopapp.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentSettingsBinding
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.auth.AuthUiEvent
import com.example.coffeeshopapp.viewmodel.auth.AuthViewModel
import com.example.coffeeshopapp.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    //  ViewModels
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSettingsBinding.bind(view)

        observeUser()
        observeAuthEvents()
        setupClicks()
    }

    private fun observeUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.user.collect { user ->

                    binding.nameTv.text =
                        user.name.ifEmpty { "Guest" }

                    binding.emailTv.text = user.email

                    if (user.image.isNotEmpty()) {
                        Glide.with(requireContext())
                            .load(user.image)
                            .centerCrop()
                            .into(binding.ivProfile)
                    } else {
                        binding.ivProfile.setImageResource(R.drawable.person_ic)
                    }
                }
            }
        }
    }

    // buttons
    private fun setupClicks() {

        // Edit Profile
        binding.editBtn.setOnClickListener {
            findNavController().navigate(R.id.profileFragment2)
        }

        // Logout
        binding.logOutBtn.setOnClickListener {
            showLogoutDialog()
        }
    }

    // Dialog
    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                authViewModel.logout()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    //  (Navigation + Messages)
    private fun observeAuthEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.authEvent.collect { event ->

                    when (event) {

                        is AuthUiEvent.NavigateToLogin -> {
                                findNavController().navigate(R.id.loginFragment)
                        }

                        is AuthUiEvent.ShowMessage -> {
                            view?.showSnack(event.message)
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}