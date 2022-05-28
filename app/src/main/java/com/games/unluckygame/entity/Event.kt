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
    override val description: String,
    var type: Type,
    var scope: Scope,
) : Item
{
    @Ignore override val slotId = R.layout.slot_event
    @Ignore override val cardId = R.layout.card_event
    @Ignore override val sampleSize = 1

    constructor(name: String, description: String) :
            this(name, description, Type.ITEM, Scope.SINGLE)

    enum class Type(val t: String){
        ITEM("Objeto reservable"),
        CELL("Evento de casilla"),
        CHAOTIC("Caos"),
        SCOREMODIFIER("Modificador de puntos")

    }

    enum class Scope(val s: String){
        SINGLE("Individual"),
        DOUBLE("Dos jugadores"),
        ALL("GLOBAL")
    }

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvEventName).text = name
        view.findViewById<TextView>(R.id.tvEventType).text = type.toString()
        view.findViewById<TextView>(R.id.tvEventScope).text = scope.toString()
        view.findViewById<TextView>(R.id.tvEventDesc).text = description
    }


}
