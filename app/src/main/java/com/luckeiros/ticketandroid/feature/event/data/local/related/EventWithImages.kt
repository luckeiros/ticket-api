//package com.luckeiros.ticketandroid.feature.event.data.local.related
//
//import androidx.room.Embedded
//import androidx.room.Relation
//import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventEntity
//import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventImageEntity
//
//data class EventWithImages(
//    @Embedded val event: EventEntity,
//    @Relation(parentColumn = "id", entityColumn = "eventId")
//    val images: List<EventImageEntity>
//)
