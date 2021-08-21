package com.example.applaudocodechallengeandroid.ui.animeDetails

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.AnimeRepository
import com.example.applaudocodechallengeandroid.data.repository.CallbackAnimeCharacter
import com.example.applaudocodechallengeandroid.data.repository.CallbackAnimeEpisode
import com.example.applaudocodechallengeandroid.data.repository.CallbackGenre
import com.example.applaudocodechallengeandroid.model.Anime
import com.example.applaudocodechallengeandroid.model.AnimeEpisodesResponse
import com.example.applaudocodechallengeandroid.model.GenreResponse
import com.example.applaudocodechallengeandroid.model.MainCharactersAnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application, private val animeRepository: AnimeRepository)
    : BaseViewModel(application), CallbackGenre, CallbackAnimeEpisode, CallbackAnimeCharacter {

    var selectedAnime = LiveEvent<Anime>()
    var genreResponse = LiveEvent<GenreResponse>()
    var episodesResponse = LiveEvent<AnimeEpisodesResponse>()
    var charactersResponse = LiveEvent<MainCharactersAnimeResponse>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarEpisodes = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)

//    var animeResponse = LiveEvent<MainAnimeResponse>()
//    var mangaResponse = LiveEvent<MainMangaResponse>()
//    val hideProgressBarAnime = MutableLiveData(false)
//    val hideProgressBarManga = MutableLiveData(false)
//    var showFavorites = LiveEvent<Boolean>()
//    var error = LiveEvent<Error>()

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

//    fun favorites() {
//        showFavorites.postValue(true)
//    }
//

    override fun onSuccessGenre(response: GenreResponse) {
        genreResponse.postValue(response)
    }

    override fun onSuccessAnimeEpisode(response: AnimeEpisodesResponse) {
        episodesResponse.postValue(response)
    }

    override fun onSuccessAnimeCharacter(response: MainCharactersAnimeResponse) {
        charactersResponse.postValue(response)
    }

    override fun onFailed(errorResponse: String) {

    }
}