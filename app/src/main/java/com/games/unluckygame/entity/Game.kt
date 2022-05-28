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
    var difficulty: Difficulty,
    var type: Type,
    var reward : String,
    var materials: String,
    override val description: String,
) : Item
{
    @Ignore override val slotId = R.layout.slot_minigame
    @Ignore override val cardId = R.layout.card_game
    @Ignore override val sampleSize = 3

    constructor(name: String, description: String) :
            this(name, Difficulty.EASY, Type.ALLVSALL,
            "recompensa", "materiales", description)

    enum class Difficulty(val diff: String) {
        EASY("Baja"),
        MEDIUM("Media"),
        HARD("Alta")
    }
    enum class Type (var t : String) {
        DUEL("1 vs 1"),
        TEAMS("Por equipos"),
        ALLVSALL("Todos contra todos"),
        ONEVSALL("Uno contra todos"),
        FOUR("Cuatrop jugadores"),
        GROUP("Grupal"),
        COUPLES("Por parejas"),
        ALLVSALLDIRECTED("Todos contra todos (con un director)")
    }

    override fun inflateView(view: View) {
        view.findViewById<TextView>(R.id.tvgameName).text = name
        view.findViewById<TextView>(R.id.tvGameDiff).text = difficulty.toString()
        view.findViewById<TextView>(R.id.tvGameType).text = type.toString()
        view.findViewById<TextView>(R.id.tvGameReward).text = reward
        view.findViewById<TextView>(R.id.tvGameMaterials).text = materials
        view.findViewById<TextView>(R.id.tvGameDesc).text = description
    }

}
