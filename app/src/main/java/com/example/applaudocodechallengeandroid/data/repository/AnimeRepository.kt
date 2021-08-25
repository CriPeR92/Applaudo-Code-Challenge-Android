package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.*
import retrofit2.Response

/**
 * AnimeRepository: Contains all anime calls to the API
 * GetAnime, GetAnimeCharacters, GetAnimeEpisodes, Get next or previous
 */

class AnimeRepository(private val api: ApiInterface) {

    /**
     * Search animes by query "search"
     */
    suspend fun getSeries(
        search: String
    ): Response<MainAnimeResponse> {
        return api.getSeries(search)
    }

    /**
     * Get all anime characters from a specific anime "id"
     */
    suspend fun getAnimeCharacters(
        animeId: String
    ): Response<MainCharactersResponse> {
        return api.getAnimeCharacters(id = animeId)
    }

    /**
     * Get all anime episodes from a specific anime "id"
     */
    suspend fun getAnimeEpisodes(
        id: String
    ): Response<AnimeEpisodesResponse> {
        return api.getAnimeEpisodes(id = id)
    }

    /**
     * Get next or previous page of an anime search
     */
    suspend fun getAnimePrevOrNext(
        url: String
    ): Response<MainAnimeResponse> {
        return api.getPrevOrNextAnime(url = url)
    }

    /**
     * Get next or previous page of an animeEpisodes search
     */
    suspend fun getAnimeEpisodesPrevOrNext(
        url: String
    ): Response<AnimeEpisodesResponse> {
        return api.getPrevOrNextAnimeEpisodes(url = url)
    }

    /**
     * Get next or previous page of an animeCharacters search
     */
    suspend fun getAnimeCharactersPrevOrNext(
        url: String
    ): Response<MainCharactersResponse> {
        return api.getPrevOrNextAnimeCharacters(url = url)
    }

    /**
     * Get all genres from a specific anime "id" and type "anime or manga"
     */
    suspend fun getGenre(
        id: String,
        type: String
    ): Response<GenreResponse> {
        return api.getGenres(id = id, type = type)
    }
}