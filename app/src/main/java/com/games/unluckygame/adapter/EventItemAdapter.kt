package com.games.unluckygame.adapter

import com.games.unluckygame.R
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.sectionFragments.EventSectionFragment
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class EventItemAdapter(
    private val events : List<Event>,
    private val sectionFragment: EventSectionFragment
) : ItemAdapter() {

    override fun getLayoutId(): Int = R.layout.slot_event

    override fun onBindViewHolder(holder: ItemAdapter.ItemAdapterViewHolder, position: Int) {
        holder.textView.text = events[position].name
        holder.cv.setOnClickListener{
            sectionFragment.displayItemCard(events[position])
        }
    }

    override fun getItemCount(): Int = events.size

}