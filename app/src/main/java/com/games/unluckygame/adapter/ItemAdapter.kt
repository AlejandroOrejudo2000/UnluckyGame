package com.games.unluckygame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.entity.Item
import com.games.unluckygame.fragments.sectionFragments.SectionFragment

abstract class ItemAdapter(
) : RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>()
{
    class ItemAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.tvItemName)
        val cv: CardView = itemView.findViewById(R.id.cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.slot_minigame, parent, false)
        return ItemAdapterViewHolder(view)
    }
}