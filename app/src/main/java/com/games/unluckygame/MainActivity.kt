package com.games.unluckygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.games.unluckygame.data.DisplayInfo
import com.games.unluckygame.data.DisplaySection
import com.games.unluckygame.data.DisplayType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMinigames.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.MINIGAMES, DisplayType.RANDOM)) }
        btnMinigamesList.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.MINIGAMES, DisplayType.LIST)) }
        btnEvents.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.EVENTS, DisplayType.RANDOM)) }
        btnEventsList.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.EVENTS, DisplayType.LIST)) }
        btnPenalties.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.PENALTIES, DisplayType.RANDOM)) }
        btnPenaltiesList.setOnClickListener { setActivityTarget(DisplayInfo(DisplaySection.PENALTIES, DisplayType.LIST)) }
    }

    private fun setActivityTarget(info : DisplayInfo){
        Intent(this, DisplayActivity::class.java).also{
            it.putExtra("displayInfo", info)
            startActivity(it)
        }
    }

}