package com.games.unluckygame.fragments.sectionFragments

import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.games.unluckygame.R
import com.games.unluckygame.fragments.cardFragments.CardFragment
import com.games.unluckygame.fragments.listFragments.ListFragment
import com.games.unluckygame.fragments.sampleFragments.SampleFragment
import com.games.unluckygame.utils.ExcelReader
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class SectionFragment(
    private val name: String
): Fragment()
{
    abstract val sampleFragment : SampleFragment
    abstract val listFragment : ListFragment
    abstract val cardFragment : CardFragment
    private var isListDisplayed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val startForResult = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            uri : Uri? ->

            uri?.let {
                val path = uri.lastPathSegment
                println(path)
                ExcelReader.readFile(uri, activity?.contentResolver)
            }

        }

        // ROOT
        var root = inflater.inflate(R.layout.fragment_section, container, false)

        // TITLE
        val tvTitle = root.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = name

        // BUTTONS
        val bnv = root.findViewById<BottomNavigationView>(R.id.bnvDisplays)
        bnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.sample -> {
                    setCurrentFragment(sampleFragment)
                    isListDisplayed = false
                }
                R.id.list -> {
                    setCurrentFragment(listFragment)
                    isListDisplayed = true
                }
            }
            true
        }

        val btnLoadData = root.findViewById<ImageButton>(R.id.btnLoadData)
        btnLoadData.setOnClickListener {
            startForResult.launch(arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        }

        // AWAKE
        if(savedInstanceState != null){
            isListDisplayed = savedInstanceState.getBoolean("isListDisplayed")
        }
        setCurrentFragment(if(isListDisplayed) listFragment else sampleFragment)
        return root
    }

    fun loadSample() = setCurrentFragment(sampleFragment)

    private fun setCurrentFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flFragmentDisplay, fragment)
            commit()
        }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isListDisplayed", isListDisplayed)
        super.onSaveInstanceState(outState)
    }
}