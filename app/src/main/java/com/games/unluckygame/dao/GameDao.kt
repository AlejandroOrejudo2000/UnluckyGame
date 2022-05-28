package com.games.unluckygame.dao

import androidx.room.*
import com.games.unluckygame.entity.Game

@Dao
interface GameDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGame(game: Game)

    @Transaction
    @Query("SELECT * FROM games_table where name = :gameName")
    fun getGameWithName(gameName: String): Game

    @Transaction
    @Query("SELECT * FROM games_table")
    fun getAll(): List<Game>
}