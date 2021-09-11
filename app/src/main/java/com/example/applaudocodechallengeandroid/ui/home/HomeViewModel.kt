package com.example.applaudocodechallengeandroid.ui.home

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.*
import com.example.applaudocodechallengeandroid.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var animeRepository: AnimeRepository

    @Inject
    lateinit var mangaRepository: MangaRepository

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    var animeResponse = LiveEvent<MainAnimeResponse?>()
    var mangaResponse = LiveEvent<MainMangaResponse?>()
    var selectedAnime = LiveEvent<Anime>()
    var selectedManga = LiveEvent<Manga>()
    val hideProgressBarAnime = MutableLiveData(false)
    val hideProgressBarManga = MutableLiveData(false)
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<String>()
    lateinit var animeDispose: Disposable

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
        animeDispose = animeRepository.getSeries(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { hideProgressBarAnime.postValue(true) }
            .subscribe({
                onSuccessAnime(it)
                hideProgressBarAnime.postValue(false)
            }, { onFailed(errorMessage) })
    }


    fun getManga(query: String) {
        mangaRepository.run {
            getManga(query)
                .subscribeOn(Schedulers.io())
                .doOnNext { hideProgressBarManga.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccessManga(it) }, { onFailed(errorMessage) })
        }
    }

    fun favorites() {
        showFavorites.postValue(true)
    }

    fun search(query: String) {
        if (Validator.validateInput(query)) {
            getSeries(query)
            getManga(query)
        }
    }

    fun getNextAnime(action: String) {

        if (action == "next" && animeResponse.value?.links?.next != null) {
            animeRepository.run {
                getAnimePrevOrNext(url = animeResponse.value?.links?.next.toString())
                    .subscribeOn(Schedulers.io())
                    .doOnNext { hideProgressBarAnime.postValue(true) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnime(it) }, { onFailed(errorMessage) })
            }

        } else if (action == "back" && animeResponse.value?.links?.prev != null) {
            animeRepository.run {
                getAnimePrevOrNext(
                    url = animeResponse.value?.links?.prev.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .doOnNext { hideProgressBarAnime.postValue(true) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessAnime(it) }, { onFailed(errorMessage) })
            }
        }
    }

    fun getNextManga(action: String) {

        if (action == "next" && mangaResponse.value?.links?.next != null) {
            mangaRepository.run {
                getMangaPrevOrNext(
                    url = mangaResponse.value?.links?.next.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .doOnNext { hideProgressBarAnime.postValue(true) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessManga(it) }, { onFailed(errorMessage) })
            }
        } else if (action == "back" && mangaResponse.value?.links?.prev != null) {
            mangaRepository.run {
                getMangaPrevOrNext(
                    url = mangaResponse.value?.links?.prev.toString()
                )
                    .subscribeOn(Schedulers.io())
                    .doOnNext { hideProgressBarAnime.postValue(true) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccessManga(it) }, { onFailed(errorMessage) })
            }
        }
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