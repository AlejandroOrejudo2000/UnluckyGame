package com.games.unluckygame.entity

import android.view.View
import android.widget.TextView
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.games.unluckygame.R

@Entity(tableName = "penalties_table")
data class Penalty(
    @PrimaryKey(autoGenerate = false)
    override var name: String,
    var difficulty: String,
    override var description: String
) : Item