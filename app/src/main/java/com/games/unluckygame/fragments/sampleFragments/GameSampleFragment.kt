package com.games.unluckygame.fragments.sampleFragments

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.adapter.GameItemAdapter
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class GameSampleFragment(
    private val sectionFragment: GameSectionFragment,
    private val itemList : List<Game>
): SampleFragment() {

    private var sample : MutableList<Game> = mutableListOf()


    override fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter : GameItemAdapter = GameItemAdapter(sample, sectionFragment)
        recyclerView.adapter = adapter
    }

    override fun clearSample() {
        sample = mutableListOf()
    }

    override fun generateSample() {
        if(itemList.isEmpty()) return
        val randomIndexes = itemList.indices.shuffled()
        clearSample()
        for (i in 0 until 3){
            sample.add(itemList[randomIndexes[i]])
        }
    }


}