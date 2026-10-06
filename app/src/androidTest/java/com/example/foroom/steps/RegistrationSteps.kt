package com.example.foroom.steps

import com.example.foroom.pages.RegistrationPage

class RegistrationSteps(private val registrationPage: RegistrationPage = RegistrationPage()) {

    fun verifyRegistrationScreenDisplayed() = registrationPage.waitUntilDisplayed()

    fun register(username: String, password: String) {
        registrationPage.typeUsername(username)
        registrationPage.typePassword(password)
        registrationPage.typeRepeatPassword(password)
        registrationPage.waitUntilAvatarsLoaded()
        registrationPage.selectAvatar(1)
        registrationPage.tapSignUp()
    }

    fun verifyHomeScreenDisplayed() = registrationPage.waitForHomeScreen()
}