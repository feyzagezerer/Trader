package com.fey.trader.ui.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
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
class MainActivity : AppCompatActivity() {
 private val viewModel : MainActivityViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


    }
}
