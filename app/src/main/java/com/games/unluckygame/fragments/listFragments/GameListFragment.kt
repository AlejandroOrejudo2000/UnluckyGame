package com.games.unluckygame.fragments.listFragments

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class GameListFragment(
    private val sectionFragment: GameSectionFragment,
    private val itemList:  List<Game>

) : ListFragment() {

    override fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter = GameItemAdapter(itemList, sectionFragment)
        recyclerView.adapter = adapter
    }
}