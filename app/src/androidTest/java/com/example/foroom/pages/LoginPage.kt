package com.example.foroom.pages

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DS
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf

class LoginPage {

    private fun inputField(parentId: Int): ViewInteraction =
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(parentId)),
                isDisplayed()
            )
        )

    private fun description(parentId: Int): ViewInteraction =
        onView(
            allOf(
                withId(DS.id.descriptionTextView),
                isDescendantOfA(withId(parentId))
            )
        )

    private fun hasTextColorRes(colorRes: Int) =
        object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("text color resource $colorRes")
            }

            override fun matchesSafely(view: TextView): Boolean =
                view.currentTextColor == ContextCompat.getColor(view.context, colorRes)
        }

    private fun logInButton() =
        onView(allOf(withId(R.id.logInButton), isDisplayed()))

    private fun signUpButton() =
        onView(allOf(withId(R.id.signUpButton), isDisplayed()))

    fun waitUntilDisplayed() {
        onView(withId(R.id.logInButton)).waitUntilVisible(45)
    }

    fun typeUsername(text: String) = inputField(R.id.userNameInput).input(text)

    fun typePassword(text: String) = inputField(R.id.passwordInput).input(text)

    fun tapLogIn() = logInButton().tap()

    fun tapSignUp() = signUpButton().tap()

    fun waitForUsernameError() {
        description(R.id.userNameInput)
            .waitUntilVisible(10)
            .check(matches(hasTextColorRes(DS.color.foroom_background_pink)))
    }

    fun waitForPasswordError() {
        description(R.id.passwordInput)
            .waitUntilVisible(10)
            .check(matches(hasTextColorRes(DS.color.foroom_background_pink)))
    }

    fun waitForHomeScreen() {
        onView(withId(R.id.navBar)).waitUntilVisible(20)
    }
}