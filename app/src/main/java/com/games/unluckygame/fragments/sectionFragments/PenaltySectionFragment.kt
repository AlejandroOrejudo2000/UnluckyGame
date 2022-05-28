package com.games.unluckygame.fragments.sectionFragments

import com.games.unluckygame.dao.PenaltyDao
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty
import com.games.unluckygame.fragments.cardFragments.EventCardFragment
import com.games.unluckygame.fragments.cardFragments.GameCardFragment
import com.games.unluckygame.fragments.cardFragments.PenaltyCardFragment
import com.games.unluckygame.fragments.listFragments.GameListFragment
import com.games.unluckygame.fragments.listFragments.PenaltyListFragment
import com.games.unluckygame.fragments.sampleFragments.GameSampleFragment
import com.games.unluckygame.fragments.sampleFragments.PenaltySampleFragment
import com.games.unluckygame.fragments.sampleFragments.SampleFragment

class PenaltySectionFragment(
    private val name: String,
    private val dao: PenaltyDao
) : SectionFragment(name) {
    override val sampleFragment = PenaltySampleFragment(this, dao)
    override val listFragment = PenaltyListFragment(this, mutableListOf())
    override val cardFragment = PenaltyCardFragment()

    fun displayItemCard(item: Penalty) {
        cardFragment.penalty = item
        cardFragment.show(childFragmentManager, "TAG")
    }
}