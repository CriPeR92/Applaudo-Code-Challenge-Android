package com.example.applaudocodechallengeandroid.data.retrofit

import com.example.applaudocodechallengeandroid.model.MainSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("anime?filter[categories]=adventure")
    suspend fun getSeries(): Response<MainSeriesResponse>

}