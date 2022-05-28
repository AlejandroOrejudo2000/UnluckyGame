package com.games.unluckygame.fragments.listFragments

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.dao.GameDao
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment
import kotlinx.coroutines.launch

class GameListFragment(
    private val sectionFragment: GameSectionFragment,
    private val dao: GameDao

) : ListFragment() {

    override suspend fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter = GameItemAdapter(dao.getAll(), sectionFragment)
        recyclerView.adapter = adapter
    }
}