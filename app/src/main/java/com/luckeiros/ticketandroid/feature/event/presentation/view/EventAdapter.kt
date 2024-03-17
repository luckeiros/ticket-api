package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luckeiros.ticketandroid.common.extension.formatDate
import com.luckeiros.ticketandroid.common.extension.layoutInflater
import com.luckeiros.ticketandroid.common.extension.loadImage
import com.luckeiros.ticketandroid.databinding.ItemEventBinding

internal class EventAdapter(
    private val events: List<EventItem>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(parent.layoutInflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    inner class EventViewHolder(
        private val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventItem) = with(binding) {
            with(event) {
                tvName.text = name
                tvDate.text = date.formatDate()
                tvVenue.text = venue
                tvLocation.text = location
                ivEvent.apply { loadImage(context, imageUrl, this) }
            }
        }
    }
}