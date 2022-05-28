package com.games.unluckygame.fragments.listFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

abstract class ListFragment(
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)
        val rv = root.findViewById<RecyclerView>(R.id.rv)
        setUpRecyclerView(rv)
        return root
    }

    abstract fun setUpRecyclerView(recyclerView: RecyclerView);

}