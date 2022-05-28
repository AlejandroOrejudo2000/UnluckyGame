package com.games.unluckygame.fragments.sectionFragments

import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.cardFragments.EventCardFragment
import com.games.unluckygame.fragments.cardFragments.GameCardFragment
import com.games.unluckygame.fragments.listFragments.EventListFragment
import com.games.unluckygame.fragments.listFragments.GameListFragment
import com.games.unluckygame.fragments.sampleFragments.EventSampleFragment
import com.games.unluckygame.fragments.sampleFragments.GameSampleFragment
import com.games.unluckygame.fragments.sampleFragments.SampleFragment

class EventSectionFragment(
    private val name: String,
) : SectionFragment(name) {

    override val sampleFragment = EventSampleFragment(this, mutableListOf())
    override val listFragment = EventListFragment(this, mutableListOf())
    override val cardFragment = EventCardFragment()

    fun displayItemCard(item: Event) {
        cardFragment.event = item
        cardFragment.show(childFragmentManager, "TAG")
    }
}