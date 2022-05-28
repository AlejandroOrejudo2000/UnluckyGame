package com.games.unluckygame.fragments.sectionFragments

import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.cardFragments.EventCardFragment
import com.games.unluckygame.fragments.cardFragments.GameCardFragment
import com.games.unluckygame.fragments.listFragments.GameListFragment
import com.games.unluckygame.fragments.sampleFragments.GameSampleFragment
import com.games.unluckygame.fragments.sampleFragments.SampleFragment

class GameSectionFragment(
    private val name: String,
) : SectionFragment(name) {

    override val sampleFragment = GameSampleFragment(this, mutableListOf())
    override val listFragment = GameListFragment(this, mutableListOf())
    override val cardFragment = GameCardFragment()

    fun displayItemCard(item: Game) {
        cardFragment.game = item
        cardFragment.show(childFragmentManager, "TAG")
    }
}