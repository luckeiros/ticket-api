package com.luckeiros.ticketandroid.feature.event.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luckeiros.ticketandroid.feature.event.data.local.dao.EventDao
import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventEntity
import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventImageEntity

@Database(
    entities = [EventEntity::class, EventImageEntity::class],
    version = 1
)
abstract class EventDataBase : RoomDatabase() {

    abstract val dao: EventDao
}