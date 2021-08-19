package com.example.applaudocodechallengeandroid

import android.app.Application
import com.example.applaudocodechallengeandroid.di.apiModule
import com.example.applaudocodechallengeandroid.di.repositoryModule
import com.example.applaudocodechallengeandroid.di.retrofitModule
import com.example.applaudocodechallengeandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplaudoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplaudoApp)
            modules(
                listOf(
                    retrofitModule,
                    apiModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}