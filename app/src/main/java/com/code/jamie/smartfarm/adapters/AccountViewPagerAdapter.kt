package com.code.jamie.smartfarm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.databinding.PagerItemBinding
import com.code.jamie.smartfarm.models.PagerItem

class AccountViewPagerAdapter(private val pagerItemsList: List<PagerItem>) :
    RecyclerView.Adapter<AccountViewPagerAdapter.AccountPagerViewHolder>() {
    inner class AccountPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PagerItemBinding.bind(itemView)
        fun bind(pagerItem: PagerItem) {
            binding.accountPagerTextview.text = pagerItem.title
            pagerItem.imageUrl?.let { binding.accountPagerImage.setImageResource(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountPagerViewHolder {
        return AccountPagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.pager_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: AccountPagerViewHolder, position: Int) {
        holder.bind(pagerItemsList[position])
    }

    override fun getItemCount(): Int {
        return pagerItemsList.size
    }
}