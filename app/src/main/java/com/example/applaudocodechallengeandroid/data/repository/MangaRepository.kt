package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import com.example.applaudocodechallengeandroid.model.MainMangaResponse
import com.example.applaudocodechallengeandroid.model.MangaChapterResponse
import retrofit2.Response
import javax.inject.Inject

class MangaRepository @Inject constructor() {

    @Inject
    lateinit var api: ApiInterface

    /**
     * Search mangas by query "search"
     */
    suspend fun getManga(
        search: String
    ): Response<MainMangaResponse> {
        return api.getManga(search)
    }

    /**
     * Get all manga characters from a specific manga "id"
     */
    suspend fun getMangaCharacters(
        mangaId: String
    ): Response<MainCharactersResponse> {
        return api.getMangaCharacters(id = mangaId)
    }

    /**
     * Get all manga chapters from a specific manga "id"
     */
    suspend fun getMangaChapters(
        id: String
    ): Response<MangaChapterResponse> {
        return api.getMangaChapters(id = id)
    }

    /**
     * Get next or previous page of an manga search
     */
    suspend fun getMangaPrevOrNext(
        url: String
    ): Response<MainMangaResponse> {
        return api.getPrevOrNextManga(url = url)
    }

    /**
     * Get next or previous page of an mangaChapters search
     */
    suspend fun getMangaChaptersBackOrNext(
        url: String
    ): Response<MangaChapterResponse> {
        return api.getNextOrBeforeMangaChapters(url = url)
    }

    /**
     * Get next or previous page of an mangaCharacters search
     */
    suspend fun getMangaCharactersBackOrNext(
        url: String
    ): Response<MainCharactersResponse> {
        return api.getNextOrBeforeMangaCharacters(url = url)
    }
}