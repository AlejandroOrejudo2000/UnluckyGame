package com.games.unluckygame.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.games.unluckygame.R
import com.games.unluckygame.data.Item
import com.google.android.material.bottomnavigation.BottomNavigationView

class SectionFragment<T : Item> (
    private val name : String,
    //BORRAR:
    private val itemList: List<T>
): Fragment()
{
    private val fragmentSample = SampleFragment<T>(this)
    private val fragmentEmpty = EmptyFragment<T>(this)
    private val fragmentList = ListFragment<T>(this, itemList)
    private val cardFragment = CardFragment<T>()
    private var isListDisplayed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.fragment_section, container, false)

        val tvTitle = root.findViewById<TextView>(R.id.tvTitle)
        tvTitle.setText(name)

        val bnv = root.findViewById<BottomNavigationView>(R.id.bnvDisplays)
        bnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.sample -> {
                    setCurrentFragment(fragmentEmpty)
                    isListDisplayed = false
                }
                R.id.list -> {
                    setCurrentFragment(fragmentList)
                    isListDisplayed = true
                }
            }
            true
        }
        if(savedInstanceState != null){
            isListDisplayed = savedInstanceState.getBoolean("isListDisplayed")
        }
        setCurrentFragment(if(isListDisplayed) fragmentList else fragmentEmpty)

        return root
    }
    fun loadSample() = setCurrentFragment(fragmentSample)
    fun dropSample() = setCurrentFragment(fragmentEmpty)

    private fun setCurrentFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragmentDisplay, fragment)
            commit()
        }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isListDisplayed", isListDisplayed)
        super.onSaveInstanceState(outState)
    }

    fun displayItemCard(item : T){
        cardFragment.item = item
        cardFragment.show(childFragmentManager, "TAG")
    }
}