package com.example.applaudocodechallengeandroid.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.applaudocodechallengeandroid.R

/**
 * BaseViewModel from wich all viewModels will inherit
 */

open class BaseViewModel() : ViewModel() {
    var errorMessage =  "Error"//application.getString(R.string.unexpected_error)
}