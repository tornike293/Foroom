package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf

class ProfilePage {

    fun openProfileTab() = onView(allOf(withId(R.id.homeNavigationProfile), isDisplayed())).tap()

    fun waitUntilDisplayed() {
        onView(withId(R.id.changeLanguageItem)).waitUntilVisible(15)
    }

    fun tapChangePassword() = onView(withId(R.id.changePasswordItem)).tap()

    fun tapChangeLanguage() = onView(withId(R.id.changeLanguageItem)).tap()

    fun waitForLabel(text: String) {
        onView(allOf(withText(text), isDisplayed())).waitUntilVisible(15)
    }
}