package com.example.coffeeshopapp.ui.profile

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.UserModel
import com.example.coffeeshopapp.databinding.FragmentProfileBinding
import com.example.coffeeshopapp.utils.extensions.goBack
import com.example.coffeeshopapp.utils.extensions.showSnack
import com.example.coffeeshopapp.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    private var selectedImageUri: String = ""

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                Glide.with(requireContext()).load(it).into(binding.ivProfile)
                selectedImageUri = it.toString()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProfileBinding.bind(view)

        observeUser()
        setupClicks()
        setupEditButton()
        setupDatePicker()
        setupImagePicker()


    }

    //Observe user data
    private fun observeUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    binding.userEt.setText(user.name)
                    binding.emailEt.setText(user.email)
                    binding.phoneEt.setText(user.phone)
                    binding.calenderEt.setText(user.birthday)

                    if (user.image.isNotEmpty()) {
                        Glide.with(requireContext())
                            .load(user.image)
                            .into(binding.ivProfile)

                        selectedImageUri = user.image
                    }

                }
            }
        }
    }

    //  Save
    private fun setupEditButton() {

        binding.saveBtn.setOnClickListener {

            if (binding.userEt.text.isNullOrEmpty()) {
                binding.root.showSnack("Enter your name")
                return@setOnClickListener
            }

            viewModel.updateUser(
                UserModel(
                    binding.userEt.text.toString(),
                    binding.emailEt.text.toString(),
                    binding.phoneEt.text.toString(),
                    binding.calenderEt.text.toString(),
                    selectedImageUri
                )
            )

            binding.root.showSnack("Updated successfully ")
        }
    }


    //  Back button
    private fun setupClicks() {
        binding.ivBack.setOnClickListener { goBack() }
    }

    //Date picker
    private fun setupDatePicker() {
        binding.calenderEt.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    binding.calenderEt.setText(formatter.format(selectedDate.time))
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    //  Image picker
    private fun setupImagePicker() {
        binding.ivEditPhoto.setOnClickListener {
            imagePicker.launch("image/*")

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}