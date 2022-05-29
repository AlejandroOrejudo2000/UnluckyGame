package com.games.unluckygame

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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

    private var readPermissionGranted = false
    private var writePermissionGranted = false

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            readPermissionGranted = it[Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted
            writePermissionGranted = it[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted
        }
        updateOrRequestPermissions()

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

    private fun updateOrRequestPermissions(){
        val hasReadPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val hasWritePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val minSdk29 = Build.VERSION.SDK_INT > Build.VERSION_CODES.Q

        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission || minSdk29

        val permissionsToRequest = mutableListOf<String>()
        if(!writePermissionGranted){
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!readPermissionGranted){
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(permissionsToRequest.isNotEmpty()){
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        }

    }
}