package com.newapptest.manuelperera.newapptest.presentation.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.newapptest.manuelperera.newapptest.R
import com.newapptest.manuelperera.newapptest.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override var activityLayout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setupBottomNavMenu()
    }

    private fun setupBottomNavMenu() {
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }

}