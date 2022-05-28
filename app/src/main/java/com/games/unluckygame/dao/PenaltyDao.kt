package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty

@Dao
interface PenaltyDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPenalty(penalty: Penalty)

    @Transaction
    @Query("SELECT * FROM penalties_table where name = :penaltyName")
    fun getPenaltyWithName(penaltyName: String): Penalty

    @Transaction
    @Query("SELECT * FROM penalties_table")
    fun getAll(): List<Penalty>
}