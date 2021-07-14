package com.code.jamie.smartfarm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.databinding.NotificationItemBinding
import com.code.jamie.smartfarm.models.NotificationItem

class NotificationAdapter(
    private val notificationList: List<NotificationItem>,
    private var listener: OnItemClick?
) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    interface OnItemClick {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClick) {
        this.listener = listener
    }

    class NotificationViewHolder(itemView: View, listener: OnItemClick) :
        RecyclerView.ViewHolder(itemView) {
        val binding = NotificationItemBinding.bind(itemView)
        fun bind(notificationItem: NotificationItem) {
            binding.notificationTitleTv.text = notificationItem.title
            binding.notificationDescriptionTv.text = notificationItem.description
            binding.timeTv.text = notificationItem.notifyingTime
            if (notificationItem.notify == true) {
                binding.notifyIv.visibility = View.VISIBLE
            } else {
                binding.notifyIv.visibility = View.INVISIBLE
            }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.notification_item,
                    parent,
                    false
                ),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    override fun getItemCount() = notificationList.size
}