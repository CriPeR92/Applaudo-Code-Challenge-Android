package com.example.applaudocodechallengeandroid.ui

import android.view.KeyEvent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.applaudocodechallengeandroid.R
import com.example.applaudocodechallengeandroid.ui.home.HomeFragment
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest : TestCase() {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ApplaudoCodeChallengeAndroid)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testInputSearch() {
        val searchText = "Dragon Ball"
        Espresso.onView(withId(R.id.svSearch)).perform(typeText(searchText),pressKey(
            KeyEvent.KEYCODE_ENTER))
        Truth.assertThat(
            Espresso.onView(withId(R.id.btnFavorites))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        )
    }

}