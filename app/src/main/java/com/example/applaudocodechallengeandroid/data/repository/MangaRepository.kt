package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import com.example.applaudocodechallengeandroid.model.MainMangaResponse
import com.example.applaudocodechallengeandroid.model.MangaChapterResponse
import retrofit2.Response

class MangaRepository(private val api: ApiInterface) {

    suspend fun getManga(
        search: String
    ): Response<MainMangaResponse> {
        return api.getManga(search)
    }

    suspend fun getMangaCharacters(
        mangaId: String
    ): Response<MainCharactersResponse> {
        return api.getMangaCharacters(id = mangaId)
    }

    suspend fun getMangaChapters(
        id: String
    ): Response<MangaChapterResponse> {
        return api.getMangaChapters(id = id)
    }

    suspend fun getMangaChaptersBackOrNext(
        url: String
    ): Response<MangaChapterResponse> {
        return api.getNextOrBeforeMangaChapters(url = url)
    }

    suspend fun getMangaCharactersBackOrNext(
        url: String
    ): Response<MainCharactersResponse> {
        return api.getNextOrBeforeMangaCharacters(url = url)
    }
}