package com.example.applaudocodechallengeandroid

import android.app.Application
import com.example.applaudocodechallengeandroid.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class ApplaudoApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}