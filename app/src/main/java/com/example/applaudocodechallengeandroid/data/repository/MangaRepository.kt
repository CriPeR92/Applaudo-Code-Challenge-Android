package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.AnimeEpisodesResponse
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import com.example.applaudocodechallengeandroid.model.MainMangaResponse
import com.example.applaudocodechallengeandroid.model.MangaChapterResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface CallbackManga {
    fun onSuccessManga(response: MainMangaResponse)
    fun onFailed(errorResponse: String)
}

interface CallbackMangaCharacter {
    fun onSuccessMangaCharacter(response: MainCharactersResponse)
    fun onFailed(errorResponse: String)
}

interface CallbackMangaChapter {
    fun onSuccessMangaChapter(response: MangaChapterResponse)
    fun onFailed(errorResponse: String)
}


class MangaRepository(private val api: ApiInterface) {

    suspend fun getManga(
        callbackManga: CallbackManga,
        search: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getManga(search) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackManga.onSuccessManga(response.body()!!)
            } else {
                callbackManga.onFailed("error")
            }
        }
    }

    suspend fun getMangaCharacters(
        callbackMangaCharacter: CallbackMangaCharacter,
        mangaId: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getMangaCharacters(id = mangaId) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackMangaCharacter.onSuccessMangaCharacter(response.body()!!)
            } else {
                callbackMangaCharacter.onFailed("error")
            }
        }
    }

    suspend fun getMangaChapters(
        callbackMangaChapter: CallbackMangaChapter,
        id: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getMangaChapters(id = id) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackMangaChapter.onSuccessMangaChapter(response.body()!!)
            } else {
                callbackMangaChapter.onFailed("error")
            }
        }
    }

    suspend fun getMangaChaptersBackOrNext(
        callbackMangaChapter: CallbackMangaChapter,
        url: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getNextOrBeforeMangaChapters(url = url) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackMangaChapter.onSuccessMangaChapter(response.body()!!)
            } else {
                callbackMangaChapter.onFailed("error")
            }
        }
    }

    suspend fun getMangaCharactersBackOrNext(
        callbackMangaCharacter: CallbackMangaCharacter,
        url: String
    ) {
        coroutineScope {
            val responseDeferred = async { api.getNextOrBeforeMangaCharacters(url = url) }
            val response = responseDeferred.await()
            if (response.isSuccessful) {
                callbackMangaCharacter.onSuccessMangaCharacter(response.body()!!)
            } else {
                callbackMangaCharacter.onFailed("error")
            }
        }
    }
}