package com.luckeiros.ticketandroid.feature.event.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val date: String?,
    val venue: String?,
    val location: String?
)

@Entity(
    tableName = "event_images",
    foreignKeys = [
        ForeignKey(
            entity = EventEntity::class,
            parentColumns = ["id"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EventImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val ratio: String,
    val width: Int,
    val height: Int,
    val eventId: Int
)

@Entity(tableName = "pages")
data class PageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)