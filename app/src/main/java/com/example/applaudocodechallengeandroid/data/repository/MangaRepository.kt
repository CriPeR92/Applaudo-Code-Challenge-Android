package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.MainCharactersResponse
import com.example.applaudocodechallengeandroid.model.MainMangaResponse
import com.example.applaudocodechallengeandroid.model.MangaChapterResponse
import io.reactivex.Observable
import javax.inject.Inject

class MangaRepository @Inject constructor() {

    @Inject
    lateinit var api: ApiInterface

    /**
     * Search mangas by query "search"
     */
    fun getManga(
        search: String
    ): Observable<MainMangaResponse> {
        return api.getManga(search)
    }

    /**
     * Get all manga characters from a specific manga "id"
     */
    fun getMangaCharacters(
        mangaId: String
    ): Observable<MainCharactersResponse> {
        return api.getMangaCharacters(id = mangaId)
    }

    /**
     * Get all manga chapters from a specific manga "id"
     */
    fun getMangaChapters(
        id: String
    ): Observable<MangaChapterResponse> {
        return api.getMangaChapters(id = id)
    }

    /**
     * Get next or previous page of an manga search
     */
    fun getMangaPrevOrNext(
        url: String
    ): Observable<MainMangaResponse> {
        return api.getPrevOrNextManga(url = url)
    }

    /**
     * Get next or previous page of an mangaChapters search
     */
    fun getMangaChaptersBackOrNext(
        url: String
    ): Observable<MangaChapterResponse> {
        return api.getNextOrBeforeMangaChapters(url = url)
    }

    /**
     * Get next or previous page of an mangaCharacters search
     */
    fun getMangaCharactersBackOrNext(
        url: String
    ): Observable<MainCharactersResponse> {
        return api.getNextOrBeforeMangaCharacters(url = url)
    }
}