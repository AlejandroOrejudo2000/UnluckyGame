package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(event: Event)

    @Transaction
    @Query("SELECT * FROM events_table where name = :eventName")
    fun getEventWithName(eventName: String): Event

    @Transaction
    @Query("SELECT * FROM events_table")
    fun getAll(): List<Event>
}