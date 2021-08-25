package com.example.applaudocodechallengeandroid.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.applaudocodechallengeandroid.R

/**
 * BaseViewModel from wich all viewModels will inherit
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var errorMessage = application.getString(R.string.unexpected_error)
}