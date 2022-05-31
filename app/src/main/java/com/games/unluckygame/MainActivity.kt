package com.games.unluckygame

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
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
import com.games.unluckygame.utils.XlsxReader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var readPermissionGranted = false
    private var writePermissionGranted = false

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var startForResult : ActivityResultLauncher<Array<String>>
    private lateinit var clearDialog : AlertDialog

    private lateinit var currentFrag: SectionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startForResult = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            uri : Uri? ->
            lifecycleScope.launch{
                uri?.let {
                    val path = uri.lastPathSegment
                    val inputStream = contentResolver.openInputStream(uri)
                    if (inputStream != null) {
                        val s = XlsxReader.readExcelFile(inputStream)
                        GameDataBase.getInstance(this@MainActivity).insertStringData(s)
                        Toast.makeText(this@MainActivity, "Datos añadidos", Toast.LENGTH_LONG)
                        currentFrag.refresh()
                    }
                }
            }
        }

        clearDialog = AlertDialog.Builder(this)
            .setTitle("Borrar datos")
            .setMessage("¿Borrar todos los datos de la aplicación?")
            .setPositiveButton("Si"){ _, _ ->
                lifecycleScope.launch{
                    Toast.makeText(this@MainActivity, "a", Toast.LENGTH_LONG)
                    GameDataBase.getInstance(this@MainActivity).clearAllData()
                    currentFrag.clear()
                }
            }
            .setNegativeButton("No"){ _, _ ->
                Toast.makeText(this, "No", Toast.LENGTH_LONG)
            }.create()

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            readPermissionGranted = it[Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted
            writePermissionGranted = it[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted
        }
        updateOrRequestPermissions()

        val db = GameDataBase.getInstance(this)
        val gameDao = db.gameDao()
        val penaltyDao = db.penaltyDao()
        val eventDao = db.eventDao()

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

    private fun recreateFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            detach(fragment)
            attach(fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun setCurrentFragment(fragment: SectionFragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
            currentFrag = fragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miupload -> startForResult.launch(arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            R.id.miclear -> clearDialog.show()
        }
        return true
    }

}