package com.games.unluckygame.data

import android.view.View

interface Item {
    val name: String
    val description: String

    abstract val sampleSize : Int
    abstract val getViewId: Int
    abstract val getCardId: Int

    abstract fun inflateView(view : View)

}
