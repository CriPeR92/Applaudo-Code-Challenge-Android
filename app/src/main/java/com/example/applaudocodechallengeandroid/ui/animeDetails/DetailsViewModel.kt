package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.AnimeEpisodesResponse
import com.example.applaudocodechallengeandroid.model.GenreResponse
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application, private val animeRepository: AnimeRepository)
    : BaseViewModel(application), CallbackGenre, CallbackAnimeEpisode, CallbackAnimeCharacter {

    var selectedAnime = LiveEvent<Anime>()
    var genreResponse = LiveEvent<GenreResponse>()
    var episodesResponse = LiveEvent<AnimeEpisodesResponse>()
    var charactersResponse = LiveEvent<MainCharactersResponse>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarEpisodes = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)
    private var prefs: SharedPreferences? = null
    var addFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

    fun getGenres() {
        hideProgressGenre.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            animeRepository.getGenre(
                this@DetailsViewModel,
                id = selectedAnime.value?.id.toString(),
                type = selectedAnime.value?.type.toString()
            )
        }
    }

    fun getEpisodes() {
        hideProgressBarEpisodes.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            animeRepository.getAnimeEpisodes(
                this@DetailsViewModel,
                id = selectedAnime.value?.id.toString(),
            )
        }
    }

    fun getCharacters() {
        hideProgressBarCharacters.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            animeRepository.getAnimeCharacters(
                this@DetailsViewModel,
                animeId = selectedAnime.value?.id.toString(),
            )
        }
    }

    fun getNextCharacters(action: String) {
        if (action == "next" && charactersResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                animeRepository.getAnimeCharactersBackOrNext(
                    this@DetailsViewModel,
                    url = charactersResponse.value?.links?.next.toString()
                )
            }
        } else if(action == "back" && charactersResponse.value?.links?.prev != null){
            viewModelScope.launch(Dispatchers.IO) {
                animeRepository.getAnimeCharactersBackOrNext(
                    this@DetailsViewModel,
                    url = charactersResponse.value?.links?.prev.toString()
                )
            }
        }
    }


    fun getNextEpisodes(action: String) {
        if (action == "next" && episodesResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                animeRepository.getAnimeEpisodesBackOrNext(
                    this@DetailsViewModel,
                    url = episodesResponse.value?.links?.next.toString()
                )
            }
        } else if(action == "back" && episodesResponse.value?.links?.prev != null){
            viewModelScope.launch(Dispatchers.IO) {
                animeRepository.getAnimeEpisodesBackOrNext(
                    this@DetailsViewModel,
                    url = episodesResponse.value?.links?.prev.toString()
                )
            }
        }

    }

    fun addToFavorites() {
        addFavorites.postValue(true)
    }

    override fun onSuccessGenre(response: GenreResponse) {
        genreResponse.postValue(response)
    }

    override fun onSuccessAnimeEpisode(response: AnimeEpisodesResponse) {
        episodesResponse.postValue(response)
    }

    override fun onSuccessAnimeCharacter(response: MainCharactersResponse) {
        charactersResponse.postValue(response)
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(errorResponse)
    }
}