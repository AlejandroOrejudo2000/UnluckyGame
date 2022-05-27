package com.games.unluckygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.data.Item

class SampleFragment<T : Item>(
    private val sectionFragment: SectionFragment<T>,
    private val itemList : List<T>
) : Fragment() {

    var sample : MutableList<T> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_sample, container, false)

        val rv = root.findViewById<RecyclerView>(R.id.rvSample)
        setUpRecyclerView(rv)

        val btnSwap = root.findViewById<ImageButton>(R.id.btnSwap)
        btnSwap.setOnClickListener {
            generateSample()
            setUpRecyclerView(rv)
            rv.adapter?.notifyDataSetChanged()
        }
        val btnDrop = root.findViewById<ImageButton>(R.id.btnDrop)
        btnDrop.setOnClickListener {
            clearSample()
            setUpRecyclerView(rv)
            rv.adapter?.notifyDataSetChanged()
        }
        return root
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter : ItemAdapter<T> = ItemAdapter(sample, sectionFragment)
        recyclerView.adapter = adapter
    }

    private fun clearSample() { sample = mutableListOf()}
    private fun generateSample() {
        val itemCounts = itemList[0].sampleSize
        val randomIndexes = itemList.indices.shuffled()
        clearSample()
        for (i in 0 until itemCounts){
            sample.add(itemList[randomIndexes[i]])
        }
    }

}