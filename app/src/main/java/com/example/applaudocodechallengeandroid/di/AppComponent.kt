package com.example.applaudocodechallengeandroid.di

import android.content.Context
import com.example.applaudocodechallengeandroid.ApplaudoApp
import com.example.applaudocodechallengeandroid.MainComponent
import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.ui.MainActivity
import com.example.applaudocodechallengeandroid.ui.animeDetails.AnimeDetailsFragment
import com.example.applaudocodechallengeandroid.ui.favorites.FavoritesFragment
import com.example.applaudocodechallengeandroid.ui.home.HomeFragment
import com.example.applaudocodechallengeandroid.ui.mangaDetails.MangaDetailsFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}