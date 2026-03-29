package com.example.coffeeshopapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.databinding.ItemProductBinding


class ProductAdapter(
    private val onAddClick: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback()){


   inner class ProductViewHolder(private val binding: ItemProductBinding) :
           RecyclerView.ViewHolder(binding.root){

fun bind(product: Product) = with(binding){
    tvCoffeeName.text = product.name
    tvCoffeeSubTitle.text = product.subtitle
    tvCoffeePrice.text = root.context.getString(R.string.price_format, product.price)

    //tvRating.text = product.rating.toString()

    Glide.with(root.context)
        .load(product.imageUrl)
        .placeholder(R.drawable.coffee_shop_logo)
        .into(imgCoffee)

    btnAdd.setOnClickListener {
        onAddClick(product)
    }
    root.setOnClickListener {
        onItemClick(product)
    }
}
           }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
      val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


   class DiffCallback : DiffUtil.ItemCallback<Product>(){
       override fun areItemsTheSame(oldItem: Product, newItem: Product) =
          oldItem.id == newItem.id


       override fun areContentsTheSame(oldItem: Product, newItem: Product) =
           oldItem == newItem

   }

}