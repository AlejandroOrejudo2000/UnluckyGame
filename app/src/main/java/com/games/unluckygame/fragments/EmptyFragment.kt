package com.games.unluckygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.games.unluckygame.R

class EmptyFragment(
    private val sectionFragment: SectionFragment
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_empty, container, false)

        val btnSample = root.findViewById<ImageButton>(R.id.btnSample)
        btnSample.setOnClickListener {
            sectionFragment.loadSample()
        }
        return root
    }
}