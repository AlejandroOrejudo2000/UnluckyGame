package com.games.unluckygame.adapter

import com.games.unluckygame.R
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.PenaltySectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

class PenaltyItemAdapter(
    private val penalties: List<Penalty>,
    private val sectionFragment: PenaltySectionFragment
) : ItemAdapter() {
    override fun getLayoutId(): Int = R.layout.slot_penalty

    override fun onBindViewHolder(holder: ItemAdapter.ItemAdapterViewHolder, position: Int) {
        holder.textView.text = penalties[position].name
        holder.cv.setOnClickListener{
            sectionFragment.displayItemCard(penalties[position])
        }
    }

    override fun getItemCount(): Int = penalties.size

}