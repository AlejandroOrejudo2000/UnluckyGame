package com.games.unluckygame.fragments.sampleFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

abstract class SampleFragment(

) : Fragment() {

    protected lateinit var rv : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sample, container, false)
        rv = root.findViewById<RecyclerView>(R.id.rvSample)
        setUpRecyclerView(rv)

        val btnSwap = root.findViewById<ImageButton>(R.id.btnSwap)
        btnSwap.setOnClickListener {
            generateSample()
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

    abstract fun setUpRecyclerView(recyclerView: RecyclerView)
    abstract fun clearSample()
    abstract fun generateSample()

}