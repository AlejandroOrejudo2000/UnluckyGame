package com.games.unluckygame.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.games.unluckygame.R
import com.games.unluckygame.data.Item
import com.games.unluckygame.fragments.SectionFragment

class ItemAdapter<T: Item> (
    private val items : List<T>,
    private val sectionFragment: SectionFragment<T>
) : RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>()
{
    class ItemAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.tvItemName)
        val cv: CardView = itemView.findViewById(R.id.cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        print("A")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.slot_minigame, parent, false)
        return ItemAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
        holder.textView.text = items[position].name
        holder.cv.setOnClickListener{
            sectionFragment.displayItemCard(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}