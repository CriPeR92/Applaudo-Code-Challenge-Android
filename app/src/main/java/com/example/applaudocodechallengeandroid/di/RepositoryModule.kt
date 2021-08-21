package com.example.applaudocodechallengeandroid.di

import com.example.applaudocodechallengeandroid.data.repository.MangaRepository
import com.example.applaudocodechallengeandroid.data.repository.AnimeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AnimeRepository(get()) }
    single { MangaRepository(get()) }
}