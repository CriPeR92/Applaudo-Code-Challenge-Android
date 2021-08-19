package com.example.applaudocodechallengeandroid.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.applaudocodechallengeandroid.R

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var errorMessage = application.getString(R.string.unexpected_error)
}