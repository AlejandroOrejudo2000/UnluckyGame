package com.games.unluckygame.data

import androidx.fragment.app.Fragment
import com.games.unluckygame.R
import java.io.Serializable

data class DisplayInfo(
    var section: DisplaySection,
    var type: DisplayType
) : Serializable {

    constructor() : this(DisplaySection.MINIGAMES, DisplayType.RANDOM)

    fun getSectionTitleResourceId() : Int{
        return when(this.section){
            DisplaySection.MINIGAMES -> R.string.minigames
            DisplaySection.EVENTS -> R.string.events
            DisplaySection.PENALTIES -> R.string.penalties
        }
    }
}