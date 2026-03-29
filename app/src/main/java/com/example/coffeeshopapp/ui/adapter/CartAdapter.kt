package com.example.coffeeshopapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.CartItemUi
import com.example.coffeeshopapp.databinding.ItemCartBinding

class CartAdapter(
    private val onPlusClick: (Int) -> Unit,
    private val onMinusClick: (Int) -> Unit,
    private val onRemoveClick: (Int) -> Unit
) : ListAdapter<CartItemUi, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItemUi) = with(binding) {

            tvProductName.text = item.name
            tvPrice.text =
                root.context.getString(R.string.price_format, item.price)

            tvQuantity.text = root.context.getString(R.string.quantity_format, item.quantity)

            tvSubTotal.text =
                root.context.getString(R.string.subtotal_format, item.total)

            Glide.with(root.context)
                .load(item.image)
                .placeholder(R.drawable.coffee_shop_logo)
                .into(ivProduct)

            btnPlus.setOnClickListener {
                onPlusClick(item.productId)
            }

            btnMinus.setOnClickListener {
                onMinusClick(item.productId)
            }

            ivRemove.setOnClickListener {
                onRemoveClick(item.productId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItemUi>() {

        override fun areItemsTheSame(
            oldItem: CartItemUi,
            newItem: CartItemUi
        ): Boolean = oldItem.productId == newItem.productId

        override fun areContentsTheSame(
            oldItem: CartItemUi,
            newItem: CartItemUi
        ): Boolean = oldItem == newItem
    }
}
