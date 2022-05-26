package com.games.unluckygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.games.unluckygame.fragments.SectionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentGames = SectionFragment("MINIJUEGOS")
        val fragmentEvents = SectionFragment("EVENTOS")
        val fragmentPenalties = SectionFragment("CASTIGOS")

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