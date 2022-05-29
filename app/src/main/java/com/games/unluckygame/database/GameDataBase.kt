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

    suspend fun insertStringData(data : String){
        val lines = data.split(";")
        for(line in lines){
            var cells = line.split(":")
            when(cells.count()){
                6 -> insertStringAsGame(cells)
                4 -> insertStringAsEvent(cells)
                3 -> insertStringAsPenalty(cells)
            }
        }
    }

    private suspend fun insertStringAsGame(d : List<String>){
        val game = Game(d[0],d[1],d[2],d[3],d[4],d[5])
        gameDao().insertGame(game)
    }

    private suspend fun insertStringAsEvent(d : List<String>){
        val event = Event(d[0],d[1],d[2],d[3])
        eventDao().insertEvent(event)
    }

    private suspend fun insertStringAsPenalty(d : List<String>){
        val penalty = Penalty(d[0],d[1],d[2])
        penaltyDao().insertPenalty(penalty)
    }

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
