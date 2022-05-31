package com.games.unluckygame.entity

import android.view.View
import android.widget.TextView
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.games.unluckygame.R

@Entity(tableName = "events_table")
data class Event(
    @PrimaryKey(autoGenerate = false)
    override val name: String,
    var type: String,
    var scope: String,
    override val description: String,
) : Item

