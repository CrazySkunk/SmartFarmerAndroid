package com.code.jamie.smartfarm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.MarketAdapter.MarketViewHolder
import com.code.jamie.smartfarm.databinding.ProductItemBinding
import com.code.jamie.smartfarm.models.Product
import com.squareup.picasso.Picasso

class MarketAdapter(private val productsList: List<Product>, private var listener: OnItemClick?) :
    RecyclerView.Adapter<MarketViewHolder>() {

    interface OnItemClick {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClick) {
        this.listener = listener
    }

    class MarketViewHolder(itemView: View, listener: OnItemClick) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ProductItemBinding.bind(itemView)
        fun bind(product: Product) {
            binding.productTitleTv.text = product.title
            binding.productDescriptionTv.text = product.description
            binding.productPriceTv.text = product.price
            Picasso.get().load(product.imageUrl)
                .placeholder(R.drawable.android_apps)
                .into(binding.productIv)
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.product_item,
                    parent,
                    false
                ),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount() = productsList.size
}