package com.mutkuensert.fragmenttestingwithhilt.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mutkuensert.fragmenttestingwithhilt.R
import com.mutkuensert.fragmenttestingwithhilt.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testMainFragment(){
        launchFragmentInHiltContainer<MainFragment>()
        onView(withId(R.id.searchButton)).perform(click())
        Thread.sleep(20000L)
    }

}