package com.example.applaudocodechallengeandroid.ui.mangaDetails

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

class DetailsMangaViewModel(application: Application, private val animeRepository: AnimeRepository, private val mangaRepository: MangaRepository, val sharedPreferencesRepository: SharedPreferencesRepository)
    : BaseViewModel(application), CallbackGenre, CallbackMangaChapter, CallbackMangaCharacter {

    var selectedManga = LiveEvent<Manga>()
    var genreResponse = LiveEvent<GenreResponse>()
    var chaptersResponse = LiveEvent<MangaChapterResponse>()
    var charactersResponse = LiveEvent<MainCharactersResponse>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarChapter = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)
    var addFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

    fun getGenres() {
        hideProgressGenre.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            animeRepository.getGenre(
                this@DetailsMangaViewModel,
                id = selectedManga.value?.id.toString(),
                type = selectedManga.value?.type.toString()
            )
        }
    }

    fun getChapters() {
        hideProgressBarChapter.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.getMangaChapters(
                this@DetailsMangaViewModel,
                id = selectedManga.value?.id.toString(),
            )
        }
    }

    fun getCharacters() {
        hideProgressBarCharacters.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.getMangaCharacters(
                this@DetailsMangaViewModel,
                mangaId = selectedManga.value?.id.toString(),
            )
        }
    }

    fun getNextCharacters(action: String) {
        if (action == "next" && charactersResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                mangaRepository.getMangaCharactersBackOrNext(
                    this@DetailsMangaViewModel,
                    url = charactersResponse.value?.links?.next.toString()
                )
            }
        } else if(action == "back" && charactersResponse.value?.links?.prev != null){
            viewModelScope.launch(Dispatchers.IO) {
                mangaRepository.getMangaCharactersBackOrNext(
                    this@DetailsMangaViewModel,
                    url = charactersResponse.value?.links?.prev.toString()
                )
            }
        }
    }


    fun getNextChapters(action: String) {
        if (action == "next" && chaptersResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                mangaRepository.getMangaChaptersBackOrNext(
                    this@DetailsMangaViewModel,
                    url = chaptersResponse.value?.links?.next.toString()
                )
            }
        } else if(action == "back" && chaptersResponse.value?.links?.prev != null){
            viewModelScope.launch(Dispatchers.IO) {
                mangaRepository.getMangaChaptersBackOrNext(
                    this@DetailsMangaViewModel,
                    url = chaptersResponse.value?.links?.prev.toString()
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

    override fun onSuccessMangaCharacter(response: MainCharactersResponse) {
        charactersResponse.postValue(response)
    }

    override fun onSuccessMangaChapter(response: MangaChapterResponse) {
        chaptersResponse.postValue(response)
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(errorResponse)
    }
}