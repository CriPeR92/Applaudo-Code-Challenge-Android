package com.example.applaudocodechallengeandroid

import com.example.applaudocodechallengeandroid.di.ActivityScope
import com.example.applaudocodechallengeandroid.ui.MainActivity
import com.example.applaudocodechallengeandroid.ui.animeDetails.AnimeDetailsFragment
import com.example.applaudocodechallengeandroid.ui.favorites.FavoritesFragment
import com.example.applaudocodechallengeandroid.ui.home.HomeFragment
import com.example.applaudocodechallengeandroid.ui.mangaDetails.MangaDetailsFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: AnimeDetailsFragment)
    fun inject(fragment: MangaDetailsFragment)
    fun inject(fragment: FavoritesFragment)
}