package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.MainMangaResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface CallbackManga {
    fun onSuccessManga(response: MainMangaResponse)
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
}