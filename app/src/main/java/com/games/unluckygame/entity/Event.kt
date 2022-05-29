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
{
    @Ignore override val slotId = R.layout.slot_event
    @Ignore override val cardId = R.layout.card_event
    @Ignore override val sampleSize = 1

    constructor(name: String, description: String) :
            this(name, description, "Objeto reservable", "Individual")

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvEventName).text = name
        view.findViewById<TextView>(R.id.tvEventType).text = type.toString()
        view.findViewById<TextView>(R.id.tvEventScope).text = scope.toString()
        view.findViewById<TextView>(R.id.tvEventDesc).text = description
    }


}
