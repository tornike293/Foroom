package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

class LoginSteps(private val loginPage: LoginPage = LoginPage()) {

    fun verifyLoginScreenDisplayed() = loginPage.waitUntilDisplayed()

    fun loginWith(username: String, password: String) {
        loginPage.typeUsername(username)
        loginPage.typePassword(password)
        loginPage.tapLogIn()
    }

    fun tapSignUp() = loginPage.tapSignUp()

    fun verifyUsernameError() = loginPage.waitForUsernameError()

    fun verifyPasswordError() = loginPage.waitForPasswordError()
}