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
    private val itemList: List<T>
): Fragment()
{
    private val fragmentSample = SampleFragment<T>(this, itemList)
    private val fragmentList = ListFragment<T>(this, itemList)
    private val cardFragment = CardFragment<T>()
    var isListDisplayed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.fragment_section, container, false)

        val tvTitle = root.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = name

        val bnv = root.findViewById<BottomNavigationView>(R.id.bnvDisplays)
        bnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.sample -> {
                    setCurrentFragment(fragmentSample)
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
        setCurrentFragment(if(isListDisplayed) fragmentList else fragmentSample)
        return root
    }
    fun loadSample() = setCurrentFragment(fragmentSample)

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