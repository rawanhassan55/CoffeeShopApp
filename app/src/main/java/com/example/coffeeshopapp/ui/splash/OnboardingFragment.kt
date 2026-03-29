package com.example.coffeeshopapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.coffeeshopapp.utils.UserDataStore
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentOnboardingBinding
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import kotlinx.coroutines.launch

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var userDataStore: UserDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDataStore = UserDataStore(requireContext())

        binding.startBtn.setOnClickListener {
            lifecycleScope.launch {
                userDataStore.setOnboardingSeen()
                navigateSafely(R.id.action_onboardingFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}