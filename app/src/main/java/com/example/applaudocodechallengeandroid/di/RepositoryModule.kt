package com.example.applaudocodechallengeandroid.di

import com.example.applaudocodechallengeandroid.data.repository.MangaRepository
import com.example.applaudocodechallengeandroid.data.repository.AnimeRepository
import com.example.applaudocodechallengeandroid.data.repository.SharedPreferencesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AnimeRepository(get()) }
    single { MangaRepository(get()) }
    single { SharedPreferencesRepository() }
}