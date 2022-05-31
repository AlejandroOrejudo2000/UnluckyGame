package com.games.unluckygame.fragments.sectionFragments

import com.games.unluckygame.dao.GameDao
import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.cardFragments.EventCardFragment
import com.games.unluckygame.fragments.cardFragments.GameCardFragment
import com.games.unluckygame.fragments.listFragments.GameListFragment
import com.games.unluckygame.fragments.sampleFragments.GameSampleFragment
import com.games.unluckygame.fragments.sampleFragments.SampleFragment

class GameSectionFragment(
    private val name: String,
    private val dao: GameDao
) : SectionFragment(name) {

    override val sampleFragment = GameSampleFragment(this, dao)
    override val listFragment = GameListFragment(this, dao)
    override val cardFragment = GameCardFragment()

    fun displayItemCard(item: Game) {
        cardFragment.game = item
        cardFragment.show(childFragmentManager, "TAG")
    }
}