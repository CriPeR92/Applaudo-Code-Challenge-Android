package com.example.applaudocodechallengeandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.applaudocodechallengeandroid.ApplaudoApp
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.MainComponent

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (application as ApplaudoApp).appComponent.mainComponent().create()

        mainComponent.inject(this)

        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById((R.id.nav_host_media_fragment)) as NavHostFragment
        navController = navHostFragment.navController
    }
}