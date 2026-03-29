package com.example.coffeeshopapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.data.model.Category
import com.example.coffeeshopapp.databinding.ItemCategoryBinding


class CategoryAdapter(private val onCategorySelected : (Category) -> Unit) :
        ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback()){

            inner class CategoryViewHolder(
                private val binding: ItemCategoryBinding
            ): RecyclerView.ViewHolder(binding.root){

                fun bind(category: Category) = with(binding){
                    tvCategoryName.text = category.name
                    tvCategoryName.isSelected = category.isSelected

                    tvCategoryName.setTextColor(
                        if (category.isSelected)
                            ContextCompat.getColor(root.context, R.color.white)
                        else
                            ContextCompat.getColor(root.context, R.color.black)
                    )

                    root.setOnClickListener {
                        onCategorySelected(category)
                    }
                }
            }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem

    }
        }
