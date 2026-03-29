package com.example.coffeeshopapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.FilterOptions
import com.example.coffeeshopapp.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment(
    private val onApply: (filter: FilterOptions) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Clear
        binding.btnClear.setOnClickListener {
            clearSelections()
        }

        //  Apply
        binding.btnApply.setOnClickListener {
            val filter = getSelectedFilter()
            onApply(filter)
            dismiss()
        }
    }

    private fun clearSelections() {
        binding.rgPrice.clearCheck()
        binding.rgSort.clearCheck()
    }

    private fun getSelectedFilter(): FilterOptions {

        val price = when (binding.rgPrice.checkedRadioButtonId) {
            R.id.rbAnyPrice -> "Any"
            R.id.rbUnder350 -> "Under 350"
            R.id.rbAbove350 -> "Above 350"
            else -> null
        }

        val sort = when (binding.rgSort.checkedRadioButtonId) {
            R.id.rbPriceLow -> "Price Low → High"
            R.id.rbPriceHigh -> "Price High → Low"
            R.id.rbRatingHigh -> "Rating High → Low"
            else -> null
        }

        return FilterOptions( price, sort)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
