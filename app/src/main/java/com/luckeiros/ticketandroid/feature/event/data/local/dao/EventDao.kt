//package com.luckeiros.ticketandroid.feature.event.data.local.dao
//
//import androidx.paging.PagingSource
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.luckeiros.ticketandroid.feature.event.data.local.entity.EventEntity
//import com.luckeiros.ticketandroid.feature.event.data.local.related.EventWithImages
//
//@Dao
//interface EventDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEvents(events: List<EventEntity>)
//
//    @Query("SELECT * FROM events")
//    suspend fun getEvents(): List<EventWithImages>
//
//    @Query("DELETE FROM events")
//    suspend fun deleteEvents()
//
//    @Query("SELECT * FROM events")
//    fun pagingSource(): PagingSource<Int, EventEntity>
//}