package com.games.unluckygame.data

import android.view.View
import android.widget.TextView
import com.games.unluckygame.R

data class Penalty(
    override var name: String,
    var difficulty: Difficulty,
    override var description: String
) : Item {

    constructor(name: String, description: String) : this(name, Difficulty.EASY, description)

    enum class Difficulty(val diff: String) {
        EASY("Baja"),
        MEDIUM("Media"),
        HARD("Alta"),
        EXTREME("Muy Alta")
    }

    override fun getViewId(): Int = R.layout.slot_penalty
    override fun getCardId(): Int = R.layout.card_penalty

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvPenaltyName).text = name
        view.findViewById<TextView>(R.id.tvPenaltyDiff).text = difficulty.toString()
        view.findViewById<TextView>(R.id.tvPenaltyDesc).text = description
    }

}
