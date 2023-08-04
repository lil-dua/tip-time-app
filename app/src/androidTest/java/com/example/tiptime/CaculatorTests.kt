package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.containsString
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/***
 * Created by HoangRyan aka LilDua on 8/4/2023.
 */
@RunWith(AndroidJUnit4::class)
class CaculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.edit_cost))
            .perform(typeText("150.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.radio_okay))
            .perform(click())

        onView(withId(R.id.button_calculate))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$22.50"))))
    }
}