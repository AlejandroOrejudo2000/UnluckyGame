package com.games.unluckygame.data

import androidx.fragment.app.Fragment
import com.games.unluckygame.R
import com.games.unluckygame.fragments.MinigamesListFragment
import com.games.unluckygame.fragments.MinigamesRandomFragment
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

    fun getFragment() : Fragment {
        return when(this.section){
            DisplaySection.MINIGAMES -> {
                if (type == DisplayType.RANDOM) MinigamesRandomFragment()
                else MinigamesListFragment()
            }
            DisplaySection.EVENTS -> {
                if (type == DisplayType.RANDOM) MinigamesRandomFragment()
                else MinigamesListFragment()
            }
            DisplaySection.PENALTIES -> {
                if (type == DisplayType.RANDOM) MinigamesRandomFragment()
                else MinigamesListFragment()
            }
        }
    }
}