package com.example.applaudocodechallengeandroid.base

import androidx.lifecycle.ViewModel

/**
 * BaseViewModel from wich all viewModels will inherit
 */

open class BaseViewModel() : ViewModel() {
    var errorMessage = "Error"
}