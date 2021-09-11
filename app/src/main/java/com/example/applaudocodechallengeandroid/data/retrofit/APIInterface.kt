package com.example.applaudocodechallengeandroid.data.retrofit

import com.example.applaudocodechallengeandroid.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * All API calls from kitsu.io
 */

interface ApiInterface {

    @GET("anime")
    fun getSeries(
        @Query("filter[text]") text: String
    ): Observable<MainAnimeResponse>

    @GET("manga")
    fun getManga(
        @Query("filter[text]") text: String
    ): Observable<MainMangaResponse>

    @GET("{type}/{id}/genres")
    fun getGenres(
        @Path(value = "type") type: String,
        @Path(value = "id") id: String
    ): Observable<GenreResponse>

    @GET("episodes")
    fun getAnimeEpisodes(
        @Query("filter[mediaId]") id: String,
    ): Observable<AnimeEpisodesResponse>

    @GET("anime-characters")
    fun getAnimeCharacters(
        @Query("include") include: String = "character",
        @Query("filter[animeId]") id: String
    ): Observable<MainCharactersResponse>

    @GET
    fun getPrevOrNextAnime(
        @Url url: String
    ): Observable<MainAnimeResponse>

    @GET
    fun getPrevOrNextManga(
        @Url url: String
    ): Observable<MainMangaResponse>

    @GET
    fun getPrevOrNextAnimeEpisodes(
        @Url url: String
    ): Observable<AnimeEpisodesResponse>

    @GET
    fun getPrevOrNextAnimeCharacters(
        @Url url: String
    ): Observable<MainCharactersResponse>


    @GET("chapters")
    fun getMangaChapters(
        @Query("filter[mangaId]") id: String,
    ): Observable<MangaChapterResponse>

    @GET("manga-characters")
    fun getMangaCharacters(
        @Query("include") include: String = "character",
        @Query("filter[mangaId]") id: String
    ): Observable<MainCharactersResponse>

    @GET
    fun getNextOrBeforeMangaChapters(
        @Url url: String
    ): Observable<MangaChapterResponse>

    @GET
    fun getNextOrBeforeMangaCharacters(
        @Url url: String
    ): Observable<MainCharactersResponse>

}