package com.example.applaudocodechallengeandroid

import com.example.applaudocodechallengeandroid.ui.home.Validator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat

@RunWith(JUnit4::class)
class ValidatorTestHome {

    @Test
    fun whenInputIsValid() {
        val search = "Hello"
        val result = Validator.validateInput(search)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInValid() {
        val search = ""
        val result = Validator.validateInput(search)
        assertThat(result).isEqualTo(false)
    }

}