package com.example.applaudocodechallengeandroid.ui.favorites

import android.app.Application
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.SharedPreferencesRepository
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga

class FavoritesViewModel(
    application: Application,
    val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel(application) {

    var animeSelected = LiveEvent<Anime>()
    var mangaSelected = LiveEvent<Manga>()
    var removeAnime = LiveEvent<Anime>()
    var removeManga = LiveEvent<Manga>()

    fun onClickAnimeAdapter(anime: Anime) {
        animeSelected.postValue(anime)
    }

    fun onClickMangaAdapter(manga: Manga) {
        mangaSelected.postValue(manga)
    }

    fun removeAnime(anime: Anime) {
        removeAnime.postValue(anime)
    }

    fun removeManga(manga: Manga) {
        removeManga.postValue(manga)
    }
}