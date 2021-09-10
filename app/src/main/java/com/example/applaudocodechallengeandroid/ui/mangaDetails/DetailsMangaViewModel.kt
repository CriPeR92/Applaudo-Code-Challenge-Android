package com.example.applaudocodechallengeandroid.ui.mangaDetails

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class DetailsMangaViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var animeRepository: AnimeRepository

    @Inject
    lateinit var mangaRepository: MangaRepository

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    var selectedManga = LiveEvent<Manga>()
    var genreResponse = LiveEvent<GenreResponse?>()
    var chaptersResponse = LiveEvent<MangaChapterResponse?>()
    var charactersResponse = LiveEvent<MainCharactersResponse?>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarChapter = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)
    var addFavorites = LiveEvent<Boolean>()
    val share = LiveEvent<String>()
    var isFavorite = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

    fun shareName(name: String) {
        share.postValue(name)
    }

    fun getGenres() {
        hideProgressGenre.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                animeRepository.getGenre(
                    id = selectedManga.value?.id.toString(),
                    type = selectedManga.value?.type.toString()
                )
            }.onSuccess { response: Response<GenreResponse> ->
                onSuccessGenre(response.body())
            }.onFailure {
                hideProgressGenre.postValue(false)
                onFailed(errorMessage)
            }
        }
    }

    fun getChapters() {
        hideProgressBarChapter.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                mangaRepository.getMangaChapters(
                    id = selectedManga.value?.id.toString(),
                )
            }.onSuccess { response: Response<MangaChapterResponse> ->
                onSuccessMangaChapter(response.body())
            }.onFailure {
                hideProgressBarChapter.postValue(false)
                onFailed(errorMessage)
            }
        }
    }

    fun getCharacters() {
        hideProgressBarCharacters.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                mangaRepository.getMangaCharacters(
                    mangaId = selectedManga.value?.id.toString(),
                )
            }.onSuccess { response: Response<MainCharactersResponse> ->
                onSuccessMangaCharacter(response.body())
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
                    mangaRepository.getMangaCharactersBackOrNext(
                        url = charactersResponse.value?.links?.next.toString()
                    )
                }.onSuccess { response: Response<MainCharactersResponse> ->
                    onSuccessMangaCharacter(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        } else if (action == "back" && charactersResponse.value?.links?.prev != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    mangaRepository.getMangaCharactersBackOrNext(
                        url = charactersResponse.value?.links?.prev.toString()
                    )
                }.onSuccess { response: Response<MainCharactersResponse> ->
                    onSuccessMangaCharacter(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        }
    }


    fun getNextChapters(action: String) {
        if (action == "next" && chaptersResponse.value?.links?.next != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    mangaRepository.getMangaChaptersBackOrNext(
                        url = chaptersResponse.value?.links?.next.toString()
                    )
                }.onSuccess { response: Response<MangaChapterResponse> ->
                    onSuccessMangaChapter(response.body())
                }.onFailure {
                    onFailed(errorMessage)
                }
            }
        } else if (action == "back" && chaptersResponse.value?.links?.prev != null) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching {
                    mangaRepository.getMangaChaptersBackOrNext(
                        url = chaptersResponse.value?.links?.prev.toString()
                    )
                }.onSuccess { response: Response<MangaChapterResponse> ->
                    onSuccessMangaChapter(response.body())
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

    private fun onSuccessMangaCharacter(response: MainCharactersResponse?) {
        charactersResponse.postValue(response)
    }

    private fun onSuccessMangaChapter(response: MangaChapterResponse?) {
        chaptersResponse.postValue(response)
    }

    private fun onFailed(errorResponse: String) {
        error.postValue(errorResponse)
    }
}