package com.code.jamie.smartfarm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.databinding.FaqItemBinding
import com.code.jamie.smartfarm.models.FAQItem

class FAQAdapter(private val faqItemsList: List<FAQItem>, private var listener: OnItemClick?) :
    RecyclerView.Adapter<FAQAdapter.FAQViewHolderHolder>() {

    interface OnItemClick {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClick) {
        this.listener = listener;
    }

    class FAQViewHolderHolder(itemView: View, listener: OnItemClick) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = FaqItemBinding.bind(itemView)
        fun bind(faqItem: FAQItem) {
            binding.faqTitle.text = faqItem.title
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolderHolder {
        return FAQViewHolderHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.faq_item,
                    parent,
                    false
                ),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: FAQViewHolderHolder, position: Int) {
        holder.bind(faqItemsList[position])
    }

    override fun getItemCount() = faqItemsList.size
}