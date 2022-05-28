package com.games.unluckygame.entity

import android.view.View

interface Item {
    val name: String
    val description: String

    abstract val sampleSize : Int
    abstract val slotId: Int
    abstract val cardId: Int

    abstract fun inflateView(view : View)

    companion object {
        fun foo() = 1
    }

}
