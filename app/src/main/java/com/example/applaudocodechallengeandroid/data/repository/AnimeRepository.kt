package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.AnimeEpisodesResponse
import com.example.applaudocodechallengeandroid.model.GenreResponse
import com.example.applaudocodechallengeandroid.model.MainAnimeResponse
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import retrofit2.Response

class AnimeRepository(private val api: ApiInterface) {

    suspend fun getSeries(
        search: String
    ): Response<MainAnimeResponse> {
        return api.getSeries(search)
    }

    suspend fun getAnimeCharacters(
        animeId: String
    ): Response<MainCharactersResponse> {
        return api.getAnimeCharacters(id = animeId)
    }

    suspend fun getAnimeEpisodes(
        id: String
    ): Response<AnimeEpisodesResponse> {
        return api.getAnimeEpisodes(id = id)
    }

    suspend fun getAnimeEpisodesBackOrNext(
        url: String
    ): Response<AnimeEpisodesResponse> {
        return api.getNextOrBeforeAnimeEpisodes(url = url)
    }

    suspend fun getAnimeCharactersBackOrNext(
        url: String
    ): Response<MainCharactersResponse> {
        return api.getNextOrBeforeAnimeCharacters(url = url)
    }

    suspend fun getGenre(
        id: String,
        type: String
    ): Response<GenreResponse> {
        return api.getGenres(id = id, type = type)
    }
}