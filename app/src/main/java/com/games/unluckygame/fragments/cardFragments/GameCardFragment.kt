package com.games.unluckygame.fragments.cardFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.games.unluckygame.R
import com.games.unluckygame.entity.Game

class GameCardFragment : CardFragment() {
    var game : Game? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.card_game, container, false)
        root.findViewById<TextView>(R.id.tvgameName).text = game?.name
        root.findViewById<TextView>(R.id.tvGameDiff).text = game?.difficulty.toString()
        root.findViewById<TextView>(R.id.tvGameType).text = game?.type.toString()
        root.findViewById<TextView>(R.id.tvGameReward).text = game?.reward
        root.findViewById<TextView>(R.id.tvGameMaterials).text = game?.materials
        root.findViewById<TextView>(R.id.tvGameDesc).text = game?.description
        return root
    }
}