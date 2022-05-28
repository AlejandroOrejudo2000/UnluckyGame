package com.games.unluckygame.adapter

import com.games.unluckygame.entity.Game
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class GameItemAdapter(
    private val games : List<Game>,
    private val sectionFragment: GameSectionFragment
) : ItemAdapter() {

    override fun onBindViewHolder(holder: ItemAdapter.ItemAdapterViewHolder, position: Int) {
        holder.textView.text = games[position].name
        holder.cv.setOnClickListener{
            sectionFragment.displayItemCard(games[position])
        }
    }

    override fun getItemCount(): Int = games.size

}