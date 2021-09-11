package com.example.applaudocodechallengeandroid.ui.animeDetails

import androidx.lifecycle.MutableLiveData
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var animeRepository: AnimeRepository

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    var selectedAnime = LiveEvent<Anime>()
    var genreResponse = LiveEvent<GenreResponse?>()
    var episodesResponse = LiveEvent<AnimeEpisodesResponse?>()
    var charactersResponse = LiveEvent<MainCharactersResponse?>()
    val hideProgressGenre = MutableLiveData(false)
    val hideProgressBarEpisodes = MutableLiveData(false)
    val hideProgressBarCharacters = MutableLiveData(false)
    val videoUrl = LiveEvent<String>()
    val share = LiveEvent<String>()
    var addFavorites = LiveEvent<Boolean>()
    var isFavorite = LiveEvent<Boolean>()
    var error = LiveEvent<String>()

    fun goToYoutube(link: String) {
        videoUrl.postValue(link)
    }

    fun shareName(name: String) {
        share.postValue(name)
    }

    fun getGenres() {
        animeRepository.run {
            getGenre(
                id = selectedAnime.value?.id.toString(),
                type = selectedAnime.value?.type.toString()
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

    fun getEpisodes() {
        animeRepository.run {
            getAnimeEpisodes(id = selectedAnime.value?.id.toString())
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressBarEpisodes.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessAnimeEpisode(it) }, {
                    hideProgressBarEpisodes.postValue(false)
                    onFailed(errorMessage)
                })
        }
    }

    fun getCharacters() {
        animeRepository.run {
            getAnimeCharacters(animeId = selectedAnime.value?.id.toString())
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressBarCharacters.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessAnimeCharacter(it) }, {
                    hideProgressBarCharacters.postValue(false)
                    onFailed(errorMessage)
                })
        }
    }

    fun getNextCharacters(action: String) {
        if (action == "next" && charactersResponse.value?.links?.next != null) {
            animeRepository.run {
                getAnimeCharactersPrevOrNext(url = charactersResponse.value?.links?.next.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnimeCharacter(it) }, {
                        onFailed(errorMessage)
                    })
            }
        } else if (action == "back" && charactersResponse.value?.links?.prev != null) {
            animeRepository.run {
                getAnimeCharactersPrevOrNext(url = charactersResponse.value?.links?.prev.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnimeCharacter(it) }, {
                        onFailed(errorMessage)
                    })
            }
        }
    }

    fun getNextEpisodes(action: String) {
        if (action == "next" && episodesResponse.value?.links?.next != null) {
            animeRepository.run {
                getAnimeEpisodesPrevOrNext(url = episodesResponse.value?.links?.next.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnimeEpisode(it) }, {
                        onFailed(errorMessage)
                    })
            }
        } else if (action == "back" && episodesResponse.value?.links?.prev != null) {
            animeRepository.run {
                getAnimeEpisodesPrevOrNext(url = episodesResponse.value?.links?.prev.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnimeEpisode(it) }, {
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