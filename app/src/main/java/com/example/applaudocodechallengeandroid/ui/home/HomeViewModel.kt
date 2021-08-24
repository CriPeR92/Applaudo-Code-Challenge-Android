package com.example.applaudocodechallengeandroid.ui.home

import android.app.Application
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    application: Application,
    private val animeRepository: AnimeRepository,
    private val mangaRepository: MangaRepository,
    val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel(application) {

    var animeResponse = LiveEvent<MainAnimeResponse?>()
    var mangaResponse = LiveEvent<MainMangaResponse?>()
    var selectedAnime = LiveEvent<Anime>()
    var selectedManga = LiveEvent<Manga>()
    val hideProgressBarAnime = MutableLiveData(false)
    val hideProgressBarManga = MutableLiveData(false)
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

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
            kotlin.runCatching {
                animeRepository.getSeries(query)
            }.onSuccess { status: Response<MainAnimeResponse> ->
                onSuccessAnime(status.body())
            }.onFailure {
                hideProgressBarAnime.postValue(false)
                onFailed(errorMessage)
            }
        }
    }


    fun getManga(query: String) {
        hideProgressBarManga.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                mangaRepository.getManga(query)
            }.onSuccess { status: Response<MainMangaResponse> ->
                onSuccessManga(status.body())
            }.onFailure {
                hideProgressBarManga.postValue(false)
                onFailed(errorMessage)
            }
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

    private fun onSuccessAnime(response: MainAnimeResponse?) {
        animeResponse.postValue(response)
    }

    private fun onSuccessManga(response: MainMangaResponse?) {
        mangaResponse.postValue(response)
    }

    private fun onFailed(errorResponse: String) {
        error.postValue(errorResponse)
    }
}