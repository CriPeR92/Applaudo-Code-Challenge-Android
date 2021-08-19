package com.example.applaudocodechallengeandroid.data.repository

import com.example.applaudocodechallengeandroid.data.retrofit.ApiInterface
import com.example.applaudocodechallengeandroid.model.MainSeriesResponse

interface CallbackSeries {
    fun onSuccessSeries(response: MainSeriesResponse)
    fun onFailed(errorResponse: String)
}

class SeriesRepository(private val api: ApiInterface) {

    suspend fun getSeries(
        callbackSeries: CallbackSeries,
    ) {
        val response = api.getSeries()
        if (response.isSuccessful) {
            callbackSeries.onSuccessSeries(response.body()!!)
        } else {
            callbackSeries.onFailed("error")
        }

    }
}