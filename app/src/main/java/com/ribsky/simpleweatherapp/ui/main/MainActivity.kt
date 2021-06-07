package com.ribsky.simpleweatherapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.ribsky.simpleweatherapp.R
import com.ribsky.simpleweatherapp.databinding.ActivityMainBinding
import com.ribsky.simpleweatherapp.util.Const


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var navController: NavController
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SimpleWeatherApp)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.appbar.bringToFront()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.mainActivityViewModel = viewModel
        binding.lifecycleOwner = this

        navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController

        binding.btnMaterial.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.fragmentMain -> {
                    navController.navigate(R.id.action_fragmentMain_to_fragmentInfo)
                    binding.btnMaterial.setImageResource(R.drawable.ic_round_keyboard_arrow_left_24)
                }
                R.id.fragmentInfo -> {
                    navController.navigate(R.id.action_fragmentInfo_to_fragmentMain)
                    binding.btnMaterial.setImageResource(R.drawable.ic_round_keyboard_arrow_right_24)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemInfo -> {
                Snackbar.make(binding.root, getString(R.string.checkGit), Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction(getString(R.string.actionOpen)) {
                            startActivity(
                                Intent.createChooser(
                                    Intent(Intent.ACTION_VIEW, Uri.parse(Const.GITHUB_URL)), null
                                )
                            )
                        }
                        anchorView = binding.btnMaterial
                        show()
                    }
                return true
            }
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle(getString(R.string.titleExit))
            setMessage(getString(R.string.titleMessage))
            setPositiveButton(getString(R.string.titleCancel)) { _, _ -> }
            setNegativeButton(getString(R.string.titleExit)) { _, _ -> finishAffinity() }
            show()
        }
    }
}