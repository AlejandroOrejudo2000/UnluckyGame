package com.games.unluckygame.data

import android.view.View

interface Item {
    val name: String
    val description: String

    abstract fun getViewId() : Int
    abstract fun getCardId() : Int
    abstract fun inflateView(view : View)
}
