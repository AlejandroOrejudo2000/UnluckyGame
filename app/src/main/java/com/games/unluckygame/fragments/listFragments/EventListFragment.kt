package com.games.unluckygame.fragments.listFragments

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.EventItemAdapter
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.EventSectionFragment
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class EventListFragment(
    private val sectionFragment: EventSectionFragment,
    private val itemList:  List<Event>

) : ListFragment() {

    override fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter = EventItemAdapter(itemList, sectionFragment)
        recyclerView.adapter = adapter
    }
}