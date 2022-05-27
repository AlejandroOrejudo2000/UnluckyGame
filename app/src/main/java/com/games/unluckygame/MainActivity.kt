package com.games.unluckygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.games.unluckygame.data.Event
import com.games.unluckygame.data.Game
import com.games.unluckygame.data.Penalty
import com.games.unluckygame.fragments.CardFragment
import com.games.unluckygame.fragments.SectionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameList: MutableList<Game> = mutableListOf()
        val eventList: MutableList<Event> = mutableListOf()
        val penaltyList: MutableList<Penalty> = mutableListOf()
        for (i in 1..10){
            gameList.add(Game(name = "Minijuego $i", description = "Desc $i"))
            eventList.add(Event(name = "Evento $i", description = "Desc $i"))
            penaltyList.add(Penalty(name = "Castigo $i", description = "Desc $i"))
        }

        val fragmentGames = SectionFragment<Game>("MINIJUEGOS", gameList)
        val fragmentEvents = SectionFragment<Event>("EVENTOS", eventList)
        val fragmentPenalties = SectionFragment<Penalty>("CASTIGOS", penaltyList)

        setCurrentFragment(fragmentGames)

        bnvDisplays.setOnItemSelectedListener {
            when(it.itemId){
                R.id.games -> setCurrentFragment(fragmentGames)
                R.id.events -> setCurrentFragment(fragmentEvents)
                R.id.penalties -> setCurrentFragment(fragmentPenalties)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}