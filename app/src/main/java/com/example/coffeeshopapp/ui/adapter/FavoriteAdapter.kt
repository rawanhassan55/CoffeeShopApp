package com.example.coffeeshopapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onItemClick: (Product) -> Unit,
    private val onFavoriteClick: (Product) -> Unit,
    private val onAddClick: (Product) -> Unit
) : ListAdapter<Product, FavoriteAdapter.FavoriteViewHolder>(DiffCallback()) {

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) = with(binding) {

            productName.text = product.name
            productPrice.text =
                root.context.getString(R.string.price_format, product.price)

            txtRating.text = root.context.getString(R.string.rating_format, product.rating)

            Glide.with(root.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.coffee_shop_logo)
                .into(imgProduct)


            iconFavorite.setImageResource(
                if (product.isFavorite)
                    R.drawable.favorite_ic
                else
                    R.drawable.favorite_border_ic
            )

            iconFavorite.setOnClickListener {
                onFavoriteClick(product)
            }

            btnAdd.setOnClickListener {
                onAddClick(product)
            }

            root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}