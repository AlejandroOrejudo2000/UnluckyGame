package com.games.unluckygame.data

import android.view.View
import android.widget.TextView
import com.games.unluckygame.R

data class Penalty(
    override var name: String,
    var difficulty: Difficulty,
    override var description: String
) : Item {

    override val getViewId = R.layout.slot_penalty
    override val getCardId = R.layout.card_penalty
    override val sampleSize = 3

    constructor(name: String, description: String) : this(name, Difficulty.EASY, description)

    enum class Difficulty(val diff: String) {
        EASY("Baja"),
        MEDIUM("Media"),
        HARD("Alta"),
        EXTREME("Muy Alta")
    }

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvPenaltyName).text = name
        view.findViewById<TextView>(R.id.tvPenaltyDiff).text = difficulty.toString()
        view.findViewById<TextView>(R.id.tvPenaltyDesc).text = description
    }

}
