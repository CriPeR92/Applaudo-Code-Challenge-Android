package com.example.applaudocodechallengeandroid

import android.app.Application
import com.example.applaudocodechallengeandroid.di.*

open class ApplaudoApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}