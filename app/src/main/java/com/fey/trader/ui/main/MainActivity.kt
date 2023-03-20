package com.fey.trader.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.fey.trader.R
import com.fey.trader.core.BaseActivity
import com.fey.trader.databinding.ActivityMainBinding
import com.fey.trader.utils.extensions.hide
import com.fey.trader.utils.extensions.show
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainActivityViewModel::class.java
), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setTransparentStatusBar()
        setupNavigation()

    }

    private fun setTransparentStatusBar() {
        window.apply {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //    window.statusBarColor = Color.TRANSPARENT
            /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                  window.insetsController?.let { controller ->
                      controller.hide(WindowInsets.Type.statusBars())
                      controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                  }
              } else {
                  window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                          or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                          or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
              }*/
         //   statusBarColor = Color.TRANSPARENT
        }
    }

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.toolbar_menu, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.itemId) {
             R.id.menuItemSearch -> {
                 findNavController(R.id.container_fragment).navigate(R.id.loginFragment)
                 true
             }
             else -> false
         }
     }

    private fun setupNavigation() {
        val appBarConfig = AppBarConfiguration(
            setOf(R.id.loginFragment),
            binding.drawerLayout
        )

        val navController = findNavController(R.id.container_fragment)
           binding.toolbar.overflowIcon = getDrawable(R.drawable.ic_menu)
        binding.toolbar.navigationIcon?.setTint(Color.parseColor("#130e51"))
        setupWithNavController(binding.toolbar, navController, appBarConfig)
   //     setupWithNavController(binding.navigationView, navController)
      //  binding.navigationView.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.toolbar.hide()
                }
                R.id.portfolioFragment -> {
                    binding.toolbar.show()
                    //   binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
                }
                else -> {
                    // binding.toolbar.setNavigationIcon(R.drawable.ic_back)
                    binding.toolbar.show()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            findNavController(R.id.container_fragment),
            binding.drawerLayout
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                binding.drawerLayout.openDrawer(GravityCompat.START)
                R.id.aboutApp -> {
                   binding.drawerLayout.closeDrawer(GravityCompat.START)
                   findNavController(R.id.container_fragment).navigate(R.id.portfolioFragment)
              }
        }
        return item.onNavDestinationSelected(findNavController(R.id.container_fragment)) || super.onOptionsItemSelected(
            item
        )
    }
}
