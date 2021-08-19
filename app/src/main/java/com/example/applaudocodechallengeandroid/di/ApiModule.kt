package com.example.applaudocodechallengeandroid.di

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(ApiInterface::class.java) }
}