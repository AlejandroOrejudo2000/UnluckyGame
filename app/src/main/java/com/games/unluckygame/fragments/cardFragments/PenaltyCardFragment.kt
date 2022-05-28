package com.games.unluckygame.fragments.cardFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.games.unluckygame.R
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty

class PenaltyCardFragment : CardFragment() {
    var penalty : Penalty? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.card_penalty, container, false)
        root.findViewById<TextView>(R.id.tvPenaltyName).text = penalty?.name
        root.findViewById<TextView>(R.id.tvPenaltyDiff).text = penalty?.difficulty.toString()
        root.findViewById<TextView>(R.id.tvPenaltyDesc).text = penalty?.description
        return root
    }
}