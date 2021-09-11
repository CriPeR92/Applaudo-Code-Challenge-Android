package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * AnimeRepository: Contains all anime calls to the API
 * GetAnime, GetAnimeCharacters, GetAnimeEpisodes, Get next or previous
 */

class AnimeRepository @Inject constructor() {

    @Inject
    lateinit var api: ApiInterface

    /**
     * Search animes by query "search"
     */
    fun getSeries(
        search: String
    ): Observable<MainAnimeResponse> {
        return api.getSeries(search).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Get all anime characters from a specific anime "id"
     */
    fun getAnimeCharacters(
        animeId: String
    ): Observable<MainCharactersResponse> {
        return api.getAnimeCharacters(id = animeId)
    }

    /**
     * Get all anime episodes from a specific anime "id"
     */
    fun getAnimeEpisodes(
        id: String
    ): Observable<AnimeEpisodesResponse> {
        return api.getAnimeEpisodes(id = id)
    }

    /**
     * Get next or previous page of an anime search
     */
    fun getAnimePrevOrNext(
        url: String
    ): Observable<MainAnimeResponse> {
        return api.getPrevOrNextAnime(url = url)
    }

    /**
     * Get next or previous page of an animeEpisodes search
     */
    fun getAnimeEpisodesPrevOrNext(
        url: String
    ): Observable<AnimeEpisodesResponse> {
        return api.getPrevOrNextAnimeEpisodes(url = url)
    }

    /**
     * Get next or previous page of an animeCharacters search
     */
    fun getAnimeCharactersPrevOrNext(
        url: String
    ): Observable<MainCharactersResponse> {
        return api.getPrevOrNextAnimeCharacters(url = url)
    }

    /**
     * Get all genres from a specific anime "id" and type "anime or manga"
     */
    fun getGenre(
        id: String,
        type: String
    ): Observable<GenreResponse> {
        return api.getGenres(id = id, type = type)
    }
}