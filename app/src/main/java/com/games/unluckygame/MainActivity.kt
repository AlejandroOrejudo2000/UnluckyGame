package com.games.unluckygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.games.unluckygame.database.GameDataBase
import com.games.unluckygame.entity.Event
import com.games.unluckygame.entity.Game
import com.games.unluckygame.entity.Penalty
import com.games.unluckygame.fragments.sectionFragments.EventSectionFragment
import com.games.unluckygame.fragments.sectionFragments.GameSectionFragment
import com.games.unluckygame.fragments.sectionFragments.PenaltySectionFragment
import com.games.unluckygame.fragments.sectionFragments.SectionFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = GameDataBase.getInstance(this)
        val gameDao = db.gameDao()
        val penaltyDao = db.penaltyDao()
        val eventDao = db.eventDao()


        val gameList: MutableList<Game> = mutableListOf()
        val eventList: MutableList<Event> = mutableListOf()
        val penaltyList: MutableList<Penalty> = mutableListOf()

        lifecycleScope.launch {
            for (i in 1..10){
                gameList.add(Game(name = "Minijuego $i", description = "Desc $i"))
                eventList.add(Event(name = "Evento $i", description = "Desc $i"))
                penaltyList.add(Penalty(name = "Castigo $i", description = "Desc $i"))
            }
            gameList.forEach { gameDao.insertGame(it) }
            eventList.forEach { eventDao.insertEvent(it) }
            penaltyList.forEach { penaltyDao.insertPenalty(it) }
        }


        val fragmentGames = GameSectionFragment("MINIJUEGOS", gameDao)
        val fragmentEvents = EventSectionFragment("EVENTOS", eventDao)
        val fragmentPenalties = PenaltySectionFragment("CASTIGOS", penaltyDao)

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