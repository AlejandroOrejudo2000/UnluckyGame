package com.games.unluckygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.games.unluckygame.data.DisplayInfo
import com.games.unluckygame.data.DisplaySection
import com.games.unluckygame.data.DisplayType
import com.games.unluckygame.fragments.MinigamesListFragment
import com.games.unluckygame.fragments.MinigamesRandomFragment
import kotlinx.android.synthetic.main.activity_minigames.*

class DisplayActivity : AppCompatActivity() {

    var displayInfo = DisplayInfo()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minigames)

        displayInfo = intent.getSerializableExtra("displayInfo") as DisplayInfo

        tvTitle.setText(displayInfo.getSectionTitleResourceId())

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv, displayInfo.getFragment())
            commit()
        }

        btnRandom.setOnClickListener{
            displayInfo.type = DisplayType.RANDOM
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv, displayInfo.getFragment())
                commit()
            }
        }

        btnList.setOnClickListener {
            displayInfo.type = DisplayType.LIST
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv, displayInfo.getFragment())
                commit()
            }
        }
    }
}