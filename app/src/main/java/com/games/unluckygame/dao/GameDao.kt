package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Game

@Dao
interface GameDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: Game)

    @Transaction
    @Query("SELECT * FROM games_table where name = :gameName")
    suspend fun getGameWithName(gameName: String): Game

    @Transaction
    @Query("SELECT * FROM games_table")
    suspend fun getAll(): List<Game>
}