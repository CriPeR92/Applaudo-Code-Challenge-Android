package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.AnimeEpisodesResponse
import com.example.applaudocodechallengeandroid.model.GenreResponse
import com.example.applaudocodechallengeandroid.model.MainAnimeResponse
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface CallbackAnime {
    fun onSuccessAnime(response: MainAnimeResponse)
    fun onFailed(errorResponse: String)
}

interface CallbackAnimeCharacter {
    fun onSuccessAnimeCharacter(response: MainCharactersResponse)
    fun onFailed(errorResponse: String)
}

interface CallbackAnimeEpisode {
    fun onSuccessAnimeEpisode(response: AnimeEpisodesResponse)
    fun onFailed(errorResponse: String)
}

interface CallbackGenre {
    fun onSuccessGenre(response: GenreResponse)
    fun onFailed(errorResponse: String)
}

class AnimeRepository(private val api: ApiInterface) {

    suspend fun getSeries(
        callbackAnime: CallbackAnime,
        search: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getSeries(search) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackAnime.onSuccessAnime(response.body()!!)
            } else {
                callbackAnime.onFailed("error")
            }
        }
    }

    suspend fun getAnimeCharacters(
        callbackAnimeCharacter: CallbackAnimeCharacter,
        animeId: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getAnimeCharacters(id = animeId) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackAnimeCharacter.onSuccessAnimeCharacter(response.body()!!)
            } else {
                callbackAnimeCharacter.onFailed("error")
            }
        }
    }

    suspend fun getAnimeEpisodes(
        callbackAnimeEpisode: CallbackAnimeEpisode,
        id: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getAnimeEpisodes(id = id) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackAnimeEpisode.onSuccessAnimeEpisode(response.body()!!)
            } else {
                callbackAnimeEpisode.onFailed("error")
            }
        }
    }

    suspend fun getAnimeEpisodesBackOrNext(
        callbackAnimeEpisode: CallbackAnimeEpisode,
        url: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getNextOrBeforeAnimeEpisodes(url = url) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackAnimeEpisode.onSuccessAnimeEpisode(response.body()!!)
            } else {
                callbackAnimeEpisode.onFailed("error")
            }
        }
    }

    suspend fun getAnimeCharactersBackOrNext(
        callbackAnimeCharacter: CallbackAnimeCharacter,
        url: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getNextOrBeforeAnimeCharacters(url = url) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackAnimeCharacter.onSuccessAnimeCharacter(response.body()!!)
            } else {
                callbackAnimeCharacter.onFailed("error")
            }
        }
    }


    suspend fun getGenre(
        callbackGenre: CallbackGenre,
        id: String,
        type: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getGenres(id = id, type = type) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackGenre.onSuccessGenre(response.body()!!)
            } else {
                callbackGenre.onFailed("error")
            }
        }
    }

}