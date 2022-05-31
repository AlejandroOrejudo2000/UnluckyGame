package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty

@Dao
interface PenaltyDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPenalty(penalty: Penalty)

    @Transaction
    @Query("SELECT * FROM penalties_table where name = :penaltyName")
    suspend fun getPenaltyWithName(penaltyName: String): Penalty

    @Transaction
    @Query("SELECT * FROM penalties_table")
    suspend fun getAll(): List<Penalty>

    @Transaction
    @Query("DELETE FROM penalties_table")
    suspend fun clear()
}