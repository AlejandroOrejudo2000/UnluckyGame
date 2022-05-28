package com.games.unluckygame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.games.unluckygame.dao.EventDao
import com.games.unluckygame.dao.GameDao
import com.games.unluckygame.dao.PenaltyDao
import com.games.unluckygame.entity.*

@Database(entities = [ Game::class, Event::class, Penalty::class], version = 1)
abstract class GameDataBase : RoomDatabase(){
    abstract fun gameDao(): GameDao
    abstract fun penaltyDao(): PenaltyDao
    abstract fun eventDao(): EventDao

    companion object{
        @Volatile
        private var INSTANCE: GameDataBase? = null

        fun getInstance(context: Context): GameDataBase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GameDataBase::class.java,
                    "game_db"
                ).build().also{
                    INSTANCE = it
                }
            }
        }
    }

}
