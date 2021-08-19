package com.example.applaudocodechallengeandroid.di

import com.example.applaudocodechallengeandroid.data.repository.SeriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SeriesRepository(get()) }
}