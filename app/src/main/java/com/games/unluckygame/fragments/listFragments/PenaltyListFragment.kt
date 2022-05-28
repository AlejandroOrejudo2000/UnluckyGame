package com.games.unluckygame.fragments.listFragments

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.adapter.PenaltyItemAdapter
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Item
import com.games.unluckygame.entity.Penalty
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.PenaltySectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class PenaltyListFragment(
    private val sectionFragment: PenaltySectionFragment,
    private val itemList:  List<Penalty>

) : ListFragment() {

    override fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter = PenaltyItemAdapter(itemList, sectionFragment)
        recyclerView.adapter = adapter
    }
}