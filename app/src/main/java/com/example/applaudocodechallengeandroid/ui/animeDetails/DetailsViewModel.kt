package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(
    application: Application,
    private val animeRepository: AnimeRepository,
    val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel(application) {

    var selectedAnime = LiveEvent<Anime>()
    var genreResponse = LiveEvent<GenreResponse?>()
    var episodesResponse = LiveEvent<AnimeEpisodesResponse?>()
    var charactersResponse = LiveEvent<MainCharactersResponse?>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarEpisodes = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)
    val videoUrl = LiveEvent<String>()
    var addFavorites = LiveEvent<Boolean>()
    var isFavorite = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

    fun goToYoutube(link: String) {
        videoUrl.postValue(link)
    }

    fun getGenres() {
        hideProgressGenre.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                animeRepository.getGenre(
                    id = selectedAnime.value?.id.toString(),
                    type = selectedAnime.value?.type.toString()
                )
            }.onSuccess { response: Response<GenreResponse> ->
                onSuccessGenre(response.body())
            }.onFailure {
                hideProgressGenre.postValue(false)
                onFailed(errorMessage)
            }
        }
    }

    fun getEpisodes() {
        hideProgressBarEpisodes.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                animeRepository.getAnimeEpisodes(id = selectedAnime.value?.id.toString())
            }.onSuccess { response: Response<AnimeEpisodesResponse> ->
                onSuccessAnimeEpisode(response.body())
            }.onFailure {
                hideProgressBarEpisodes.postValue(false)
                onFailed(errorMessage)
            }
        }
    }

    fun getCharacters() {
        hideProgressBarCharacters.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                animeRepository.getAnimeCharacters(
                    animeId = selectedAnime.value?.id.toString(),
                )
            }.onSuccess { response: Response<MainCharactersResponse> ->
                onSuccessAnimeCharacter(response.body())
            }.onFailure {
                hideProgressBarCharacters.postValue(false)
                onFailed(errorMessage)
            }
        }

    }

    fun getNextCharacters(action: String) {
        if (action == "next" && charactersResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    animeRepository.getAnimeCharactersBackOrNext(
                        url = charactersResponse.value?.links?.next.toString()
                    )
                }.onSuccess { response: Response<MainCharactersResponse> ->
                    onSuccessAnimeCharacter(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        } else if (action == "back" && charactersResponse.value?.links?.prev != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    animeRepository.getAnimeCharactersBackOrNext(
                        url = charactersResponse.value?.links?.prev.toString()
                    )
                }.onSuccess { response: Response<MainCharactersResponse> ->
                    onSuccessAnimeCharacter(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        }
    }


    fun getNextEpisodes(action: String) {
        if (action == "next" && episodesResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    animeRepository.getAnimeEpisodesBackOrNext(
                        url = episodesResponse.value?.links?.next.toString()
                    )
                }.onSuccess { response: Response<AnimeEpisodesResponse> ->
                    onSuccessAnimeEpisode(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        } else if (action == "back" && episodesResponse.value?.links?.prev != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    animeRepository.getAnimeEpisodesBackOrNext(
                        url = episodesResponse.value?.links?.prev.toString()
                    )
                }.onSuccess { response: Response<AnimeEpisodesResponse> ->
                    onSuccessAnimeEpisode(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        }
    }

    fun addToFavorites() {
        addFavorites.postValue(true)
    }

    private fun onSuccessGenre(response: GenreResponse?) {
        genreResponse.postValue(response)
    }

    private fun onSuccessAnimeEpisode(response: AnimeEpisodesResponse?) {
        episodesResponse.postValue(response)
    }

    private fun onSuccessAnimeCharacter(response: MainCharactersResponse?) {
        charactersResponse.postValue(response)
    }

    private fun onFailed(errorResponse: String) {
        error.postValue(errorResponse)
    }

}