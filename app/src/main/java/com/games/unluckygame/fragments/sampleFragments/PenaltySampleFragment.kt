package com.games.unluckygame.fragments.sampleFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.EventItemAdapter
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.adapter.PenaltyItemAdapter
import com.games.unluckygame.dao.PenaltyDao
import com.games.unluckygame.database.GameDataBase
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty
import com.games.unluckygame.fragments.sectionFragments.EventSectionFragment
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.PenaltySectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment
import kotlinx.coroutines.launch

class PenaltySampleFragment(
    private val sectionFragment: PenaltySectionFragment,
    private val dao : PenaltyDao
): SampleFragment() {

    private var sample : MutableList<Penalty> = mutableListOf()

    override fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter = PenaltyItemAdapter(sample, sectionFragment)
        recyclerView.adapter = adapter
    }

    override fun clearSample() {
        sample = mutableListOf()
    }

    override fun generateSample() {
        lifecycleScope.launch{
            val itemList = dao.getAll()
            clearSample()
            if(itemList.isNotEmpty()) {
                val randomIndexes = itemList.indices.shuffled()
                for (i in 0 until 3.coerceAtMost(itemList.size)){
                    sample.add(itemList[randomIndexes[i]])
                }
            }
            setUpRecyclerView(rv)
        }
    }
}