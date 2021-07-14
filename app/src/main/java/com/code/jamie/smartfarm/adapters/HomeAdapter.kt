package com.code.jamie.smartfarm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.HomeAdapter.HomeViewHolder
import com.code.jamie.smartfarm.databinding.ShopItemBinding
import com.code.jamie.smartfarm.models.ShopItem
import com.squareup.picasso.Picasso

class HomeAdapter(private val shopItems:List<ShopItem>,var listener: OnItemClick?) : RecyclerView.Adapter<HomeViewHolder>() {
    interface OnItemClick{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:OnItemClick){
        this.listener = listener;
    }

    class HomeViewHolder(itemView: View,listener: OnItemClick?) : RecyclerView.ViewHolder(itemView) {
        private val binding = ShopItemBinding.bind(itemView)
        fun bind(shopItem: ShopItem) {
            binding.shopTitleTextView.text = shopItem.title
            binding.shopLocationTextView.text = shopItem.location
            binding.shopDescriptionTextView.text = shopItem.description
            Picasso.get().load(shopItem.imageUrl)
                .placeholder(R.drawable.chicken)
                .into(binding.shopItemImageView)
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    listener?.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shop_item,
                parent,
                false),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(shopItems[position])
    }

    override fun getItemCount()=shopItems.size
}