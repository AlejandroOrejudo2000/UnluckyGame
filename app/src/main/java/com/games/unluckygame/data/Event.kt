package com.games.unluckygame.data

import android.view.View
import android.widget.TextView
import com.games.unluckygame.R

data class Event(
    override val name: String,
    override val description: String,
    var type: Type,
    var scope: Scope,
) : Item
{
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

    override fun getViewId() = R.layout.slot_event
    override fun getCardId() = R.layout.card_event

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvEventName).text = name
        view.findViewById<TextView>(R.id.tvEventType).text = type.toString()
        view.findViewById<TextView>(R.id.tvEventScope).text = scope.toString()
        view.findViewById<TextView>(R.id.tvEventDesc).text = description
    }


}
