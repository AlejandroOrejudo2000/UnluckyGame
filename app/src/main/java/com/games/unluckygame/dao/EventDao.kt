package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event)

    @Transaction
    @Query("SELECT * FROM events_table where name = :eventName")
    suspend fun getEventWithName(eventName: String): Event

    @Transaction
    @Query("SELECT * FROM events_table")
    suspend fun getAll(): List<Event>

    @Transaction
    @Query("DELETE FROM events_table")
    suspend fun clear()

}