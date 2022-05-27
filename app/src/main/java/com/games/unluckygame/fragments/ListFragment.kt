package com.games.unluckygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.adapter.ItemAdapter
import com.games.unluckygame.data.Game
import com.games.unluckygame.data.Item

class ListFragment<T: Item>(
    //BORRAR:
    private val sectionFragment: SectionFragment<T>,
    private val itemList:  List<T>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        // GENERAR LISTA COMPLETA DE ITEMS

        val rv = root.findViewById<RecyclerView>(R.id.rv)
        setUpRecyclerView(rv)
        return root
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        val adapter : ItemAdapter<T> = ItemAdapter(itemList, sectionFragment)
        recyclerView.adapter = adapter
    }

}