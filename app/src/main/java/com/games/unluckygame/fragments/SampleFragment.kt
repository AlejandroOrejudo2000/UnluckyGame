package com.games.unluckygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.games.unluckygame.R

class SampleFragment(
    private val sectionFragment: SectionFragment
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_sample, container, false)

        val btnSwap = root.findViewById<ImageButton>(R.id.btnSwap)
        btnSwap.setOnClickListener {
            Toast.makeText(activity, "Cambiando elementos", Toast.LENGTH_LONG).show()
        }
        val btnDrop = root.findViewById<ImageButton>(R.id.btnDrop)
        btnDrop.setOnClickListener {
            sectionFragment.dropSample()
        }
        return root
    }
}