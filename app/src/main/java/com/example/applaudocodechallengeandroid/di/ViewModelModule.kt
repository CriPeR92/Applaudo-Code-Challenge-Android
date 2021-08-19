package com.example.applaudocodechallengeandroid.di

import com.example.applaudocodechallengeandroid.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(androidApplication(), get()) }
}