package com.example.applaudocodechallengeandroid.ui.home

/**
 * Input Validator to make search in home
 */
object Validator {
    fun validateInput(searchInput: String): Boolean {
        return searchInput.isNotEmpty()
    }
}