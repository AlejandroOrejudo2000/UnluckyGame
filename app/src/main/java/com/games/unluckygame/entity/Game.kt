package com.games.unluckygame.entity

import android.view.View
import android.widget.TextView
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.games.unluckygame.R

@Entity(tableName = "games_table")
data class Game(
    @PrimaryKey(autoGenerate = false)
    override var name: String,
    var difficulty: String,
    var type: String,
    var reward : String,
    var materials: String,
    override val description: String,
) : Item

