package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.content.Context
import android.content.res.Configuration
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luckeiros.ticketandroid.common.extension.formatDate
import com.luckeiros.ticketandroid.common.extension.layoutInflater
import com.luckeiros.ticketandroid.common.extension.loadImage
import com.luckeiros.ticketandroid.common.view.ImageType
import com.luckeiros.ticketandroid.databinding.ItemEventBinding
import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.domain.EventImage

internal class EventAdapter(
    private val events: List<Event>
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

        fun bind(event: Event) = with(binding) {

            with(event) {
                tvName.text = name
                tvDate.text = date?.formatDate()
                tvVenue.text = venue
                tvLocation.text = location

                images?.let {
                    val matchedImageUrl = findBestMatchedImageUrl(it, ivEvent.context)

                    matchedImageUrl?.let { url -> ivEvent.loadImage(ivEvent.context, url) }
                }
            }
        }

        private fun findBestImageType(context: Context): ImageType {
            val densityDpi = context.resources.displayMetrics.densityDpi
            val deviceOrientation = context.resources.configuration.orientation
            val isLandscape = deviceOrientation == Configuration.ORIENTATION_LANDSCAPE

            return ImageType.findBestMatch(densityDpi, isLandscape)
        }

        private fun findBestMatchedImageUrl(images: List<EventImage>, context: Context): String? {
            val bestMatch = findBestImageType(context)
            val matchedImage =
                images.find { it.ratio == bestMatch.ratio && it.width == bestMatch.width && it.height == bestMatch.height }
            return matchedImage?.url
        }
    }
}