package com.games.unluckygame.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.games.unluckygame.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SectionFragment (
    private val name : String
): Fragment()
{
    private val fragmentSample = SampleFragment(this)
    private val fragmentEmpty = EmptyFragment(this)
    private val fragmentList = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
                R.id.sample -> setCurrentFragment(fragmentEmpty)
                R.id.list -> setCurrentFragment(fragmentList)
            }
            true
        }

        bnv.selectedItemId = R.id.sample
        setCurrentFragment(fragmentEmpty)

        return root
    }
    fun loadSample() = setCurrentFragment(fragmentSample)
    fun dropSample() = setCurrentFragment(fragmentEmpty)

    private fun setCurrentFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragmentDisplay, fragment)
            commit()
        }
}