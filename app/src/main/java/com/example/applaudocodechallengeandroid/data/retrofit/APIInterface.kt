package com.example.applaudocodechallengeandroid.data.retrofit

import com.example.applaudocodechallengeandroid.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * All API calls from kitsu.io
 */

interface ApiInterface {

    @GET("anime")
    suspend fun getSeries(
        @Query("filter[text]") text: String
    ): Response<MainAnimeResponse>

    @GET("manga")
    suspend fun getManga(
        @Query("filter[text]") text: String
    ): Response<MainMangaResponse>

    @GET("{type}/{id}/genres")
    suspend fun getGenres(
        @Path(value = "type") type: String,
        @Path(value = "id") id: String
    ): Response<GenreResponse>

    @GET("episodes")
    suspend fun getAnimeEpisodes(
        @Query("filter[mediaId]") id: String,
    ): Response<AnimeEpisodesResponse>

    @GET("anime-characters")
    suspend fun getAnimeCharacters(
        @Query("include") include: String = "character",
        @Query("filter[animeId]") id: String
    ): Response<MainCharactersResponse>

    @GET
    suspend fun getPrevOrNextAnime(
        @Url url: String
    ): Response<MainAnimeResponse>

    @GET
    suspend fun getPrevOrNextManga(
        @Url url: String
    ): Response<MainMangaResponse>

    @GET
    suspend fun getPrevOrNextAnimeEpisodes(
        @Url url: String
    ): Response<AnimeEpisodesResponse>

    @GET
    suspend fun getPrevOrNextAnimeCharacters(
        @Url url: String
    ): Response<MainCharactersResponse>


    @GET("chapters")
    suspend fun getMangaChapters(
        @Query("filter[mangaId]") id: String,
    ): Response<MangaChapterResponse>

    @GET("manga-characters")
    suspend fun getMangaCharacters(
        @Query("include") include: String = "character",
        @Query("filter[mangaId]") id: String
    ): Response<MainCharactersResponse>

    @GET
    suspend fun getNextOrBeforeMangaChapters(
        @Url url: String
    ): Response<MangaChapterResponse>

    @GET
    suspend fun getNextOrBeforeMangaCharacters(
        @Url url: String
    ): Response<MainCharactersResponse>


}