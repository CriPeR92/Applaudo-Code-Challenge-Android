package com.example.applaudocodechallengeandroid.data.retrofit

import com.example.applaudocodechallengeandroid.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

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
        @Query("animeId") id: String
    ): Response<MainCharactersAnimeResponse>

    @GET
    suspend fun getNextOrBeforeAnimeEpisodes(
        @Url url: String
    ): Response<AnimeEpisodesResponse>

    @GET
    suspend fun getNextOrBeforeAnimeCharacters(
        @Url url: String
    ): Response<MainCharactersAnimeResponse>

//    @GET("chapters")
//    suspend fun getMangaEpisodes(
//        @Query("filter[mangaId]") id: String,
//    ): Response<MainChaptersMangaResponse>

//    @GET("manga-characters")
//    suspend fun getMangaCharacters(
//        @Query("include") include: String = "character",
//        @Query("mangaId") id: String
//    ): Response<MainCharactersMangaResponse>

}