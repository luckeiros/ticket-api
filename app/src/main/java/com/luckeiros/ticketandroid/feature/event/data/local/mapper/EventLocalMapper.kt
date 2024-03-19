//package com.luckeiros.ticketandroid.feature.event.data.local.mapper
//
//import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventEntity
//import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventImageEntity
//import com.luckeiros.ticketandroid.feature.event.data.local.related.EventWithImages
//import com.luckeiros.ticketandroid.feature.event.domain.Event
//import com.luckeiros.ticketandroid.feature.event.domain.EventImage
//
//fun Event.toEntity(eventId: Int) = EventEntity(
//    id = eventId,
//    name = name,
//    date = date.orEmpty(),
//    venue = venue.orEmpty(),
//    location = location.orEmpty()
//)
//
//fun EventImage.toEntity(eventId: Int) = EventImageEntity(
//    eventId = eventId,
//    url = url,
//    ratio = ratio,
//    width = width,
//    height = height
//)
//
//fun EventWithImages.toModel() = Event(
//    name = event.name,
//    date = event.date,
//    venue = event.venue,
//    location = event.location,
//    images = images.map { it.toModel() }
//)
//
//fun EventImageEntity.toModel() = EventImage(
//    url = url,
//    ratio = ratio,
//    width = width,
//    height = height
//)