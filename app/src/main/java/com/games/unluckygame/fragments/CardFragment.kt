package com.games.unluckygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.games.unluckygame.R
import com.games.unluckygame.data.Item

class CardFragment<T : Item>(): DialogFragment() {

    var item: T? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cardLayoutId : Int = item?.getCardId ?: R.layout.card_default
        val root = inflater.inflate(cardLayoutId, container, false)
        item?.inflateView(root)
        return root
    }
}