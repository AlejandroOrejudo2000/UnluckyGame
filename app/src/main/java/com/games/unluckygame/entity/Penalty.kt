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
) : Item {

    @Ignore override val slotId = R.layout.slot_penalty
    @Ignore override val cardId = R.layout.card_penalty
    @Ignore override val sampleSize = 3

    constructor(name: String, description: String) : this(name, "Facil", description)

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvPenaltyName).text = name
        view.findViewById<TextView>(R.id.tvPenaltyDiff).text = difficulty.toString()
        view.findViewById<TextView>(R.id.tvPenaltyDesc).text = description
    }

}
