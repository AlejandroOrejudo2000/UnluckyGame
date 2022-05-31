package com.games.unluckygame.fragments.cardFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.games.unluckygame.R
import com.games.unluckygame.entity.Event

class EventCardFragment : CardFragment() {
    var event : Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.card_event, container, false)
        event?.let{
            event: Event ->
            root.findViewById<TextView>(R.id.tvEventName).text = event.name
            root.findViewById<TextView>(R.id.tvEventType).text = event.type
            root.findViewById<TextView>(R.id.tvEventScope).text = event.scope
            root.findViewById<TextView>(R.id.tvEventDesc).text = event.description
        }
        return root
    }
}