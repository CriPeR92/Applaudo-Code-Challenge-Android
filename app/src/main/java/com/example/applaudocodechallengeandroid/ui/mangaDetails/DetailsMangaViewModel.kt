package com.example.applaudocodechallengeandroid.ui.mangaDetails

import androidx.lifecycle.MutableLiveData
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        animeRepository.run {
            getGenre(
                id = selectedManga.value?.id.toString(),
                type = selectedManga.value?.type.toString()
            )
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressGenre.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessGenre(it) }, {
                    hideProgressGenre.postValue(false)
                    onFailed(errorMessage)
                })
        }
    }

    fun getChapters() {
        mangaRepository.run {
            getMangaChapters(
                id = selectedManga.value?.id.toString(),
            )
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressBarChapter.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessMangaChapter(it) }, {
                    hideProgressBarChapter.postValue(false)
                    onFailed(errorMessage)
                })
        }
    }

    fun getCharacters() {
        mangaRepository.run {
            mangaRepository.getMangaCharacters(
                mangaId = selectedManga.value?.id.toString(),
            )
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressBarCharacters.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessMangaCharacter(it) }, {
                    hideProgressBarCharacters.postValue(false)
                    onFailed(errorMessage)
                })
        }
    }

    fun getNextCharacters(action: String) {
        if (action == "next" && charactersResponse.value?.links?.next != null) {
            mangaRepository.run {
                mangaRepository.getMangaCharactersBackOrNext(
                    url = charactersResponse.value?.links?.next.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessMangaCharacter(it) }, {
                        onFailed(errorMessage)
                    })
            }
        } else if (action == "back" && charactersResponse.value?.links?.prev != null) {
            mangaRepository.run {
                mangaRepository.getMangaCharactersBackOrNext(
                    url = charactersResponse.value?.links?.prev.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessMangaCharacter(it) }, {
                        onFailed(errorMessage)
                    })
            }
        }
    }


    fun getNextChapters(action: String) {
        if (action == "next" && chaptersResponse.value?.links?.next != null) {
            mangaRepository.run {
                mangaRepository.getMangaChaptersBackOrNext(
                    url = chaptersResponse.value?.links?.next.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessMangaChapter(it) }, {
                        onFailed(errorMessage)
                    })
            }
        } else if (action == "back" && chaptersResponse.value?.links?.prev != null) {
            mangaRepository.run {
                mangaRepository.getMangaChaptersBackOrNext(
                    url = chaptersResponse.value?.links?.prev.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessMangaChapter(it) }, {
                        onFailed(errorMessage)
                    })
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