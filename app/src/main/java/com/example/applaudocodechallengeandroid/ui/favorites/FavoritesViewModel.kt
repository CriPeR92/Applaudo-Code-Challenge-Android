package com.example.applaudocodechallengeandroid.ui.favorites

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.Manga

class FavoritesViewModel(application: Application) : BaseViewModel(application) {

    var animeSelected = MutableLiveData<Anime>()
    var mangaSelected = MutableLiveData<Manga>()
    var removeAnime = LiveEvent<Anime>()
    var removeManga = LiveEvent<Manga>()
    var error = LiveEvent<Error>()

    fun removeAnimeFavorite(anime: Anime) {
        removeAnime.postValue(anime)
    }

    fun removeMangaFavorite(manga: Manga) {
        removeManga.postValue(manga)
    }

    fun onClickAnimeAdapter(anime: Anime) {
        animeSelected.postValue(anime)
    }

    fun onClickMangaAdapter(manga: Manga) {
        mangaSelected.postValue(manga)
    }
}