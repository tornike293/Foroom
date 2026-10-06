package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DS
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf

class ChangePasswordPage {

    private fun inputField(parentId: Int): ViewInteraction =
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(parentId)),
                isDisplayed()
            )
        )

    fun waitUntilDisplayed() {
        onView(allOf(withId(DS.id.actionButton), isDisplayed())).waitUntilVisible(15)
    }

    fun typePassword(text: String) = inputField(R.id.passwordInput).input(text)

    fun typeRepeatPassword(text: String) = inputField(R.id.repeatPasswordInput).input(text)

    fun tapConfirm() = onView(allOf(withId(DS.id.actionButton), isDisplayed())).tap()
}