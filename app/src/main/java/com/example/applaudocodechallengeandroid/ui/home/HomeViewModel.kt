package com.example.applaudocodechallengeandroid.ui.home

import android.app.Application
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    private val animeRepository: AnimeRepository,
    private val mangaRepository: MangaRepository
) : BaseViewModel(application),
    CallbackAnime, CallbackManga {

    var animeResponse = LiveEvent<MainAnimeResponse>()
    var mangaResponse = LiveEvent<MainMangaResponse>()
    var genreResponse = LiveEvent<GenreResponse>()
    var selectedAnime = LiveEvent<Anime>()
    var selectedManga = LiveEvent<Manga>()
    val hideProgressBarAnime = MutableLiveData(false)
    val hideProgressBarManga = MutableLiveData(false)
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<Error>()

    fun getQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return true
            }
        }
    }

    fun getSeries(query: String) {
        hideProgressBarAnime.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            animeRepository.getSeries(this@HomeViewModel, query)
        }
    }

    fun getManga(query: String) {
        hideProgressBarManga.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.getManga(this@HomeViewModel, query)
        }
    }

    fun favorites() {
        showFavorites.postValue(true)
    }

    fun search(query: String) {
//        if (Validator.validateInput(search)) {
        getSeries(query)
        getManga(query)
//        }
    }

    fun onClickActionAnime(anime: Anime) {
        selectedAnime.postValue(anime)
    }

    fun onClickActionManga(manga: Manga) {
        selectedManga.postValue(manga)
    }

    override fun onSuccessAnime(response: MainAnimeResponse) {
        animeResponse.postValue(response)
    }

    override fun onSuccessManga(response: MainMangaResponse) {
        mangaResponse.postValue(response)
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }
}