package com.example.coffeeshopapp.ui.cart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentCheckoutBinding
import com.example.coffeeshopapp.utils.extensions.goBack
import com.example.coffeeshopapp.utils.extensions.navigateSafely
import com.example.coffeeshopapp.utils.extensions.showSnack
import nl.dionsegijn.konfetti.core.*
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSuccessAnimation()
        setupClicks()
    }

    private fun startSuccessAnimation() {

        val scaleAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.success_scale)
        binding.imgSuccess.startAnimation(scaleAnim)


        binding.konfettiView.start(
            Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA),
                emitter = Emitter(duration = 2, TimeUnit.SECONDS).perSecond(100)
            )
        )
    }

    private fun setupClicks() {

        binding.btnBack.setOnClickListener {
            goBack()
        }

        binding.btnTrack.setOnClickListener {
            binding.root.showSnack("Tracking order...")
        }

        binding.tvAnother.setOnClickListener {
            navigateSafely(R.id.action_checkoutFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
