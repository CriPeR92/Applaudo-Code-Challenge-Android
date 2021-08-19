package com.example.applaudocodechallengeandroid.ui.home

import android.app.Application
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applaudocodechallengeandroid.base.BaseViewModel
import com.example.applaudocodechallengeandroid.base.LiveEvent
import com.example.applaudocodechallengeandroid.data.repository.CallbackSeries
import com.example.applaudocodechallengeandroid.data.repository.SeriesRepository
import com.example.applaudocodechallengeandroid.model.MainSeriesResponse
import com.example.applaudocodechallengeandroid.model.Series
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val seriesRepository: SeriesRepository) : BaseViewModel(application),
    CallbackSeries {

    var seriesResponse = LiveEvent<MainSeriesResponse>()
    var seriesSelected = MutableLiveData<Series>()
//    var seasons = LiveEvent<ArrayList<Episode>>()
    val hideProgress = MutableLiveData(false)
    var search: String = ""
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<Error>()

    fun getQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                println("asd")
                return true
            }
        }
    }

    fun getSeries() {
        hideProgress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            seriesRepository.getSeries(this@HomeViewModel)
        }
    }

    fun favorites() {
        showFavorites.postValue(true)
    }

    fun search(query: String) {
//        if (Validator.validateInput(search)) {
            hideProgress.value = true
            viewModelScope.launch(Dispatchers.IO) {
                seriesRepository.getSeries(this@HomeViewModel)
            }
//        }
    }

    fun onClickActionGridAdapter() {
//        hideProgress.postValue(true)
//        showSelected.postValue(show)
//        viewModelScope.launch(Dispatchers.IO) {
//            seasonsRepository.getSeasons(this@HomeViewModel, show.id.toString())
//        }
    }


    override fun onSuccessSeries(response: MainSeriesResponse) {
        seriesResponse.postValue(response)
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }
}