package com.example.foroom.steps

import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ProfilePage
import com.example.foroom.pages.ChangePasswordPage

class ProfileSteps(
    private val profilePage: ProfilePage = ProfilePage(),
    private val changeLanguagePage: ChangeLanguagePage = ChangeLanguagePage(),
    private val changePasswordPage: ChangePasswordPage = ChangePasswordPage()
) {
    fun openProfile() {
        profilePage.openProfileTab()
        profilePage.waitUntilDisplayed()
    }

    fun switchToGeorgian() {
        profilePage.tapChangeLanguage()
        changeLanguagePage.selectGeorgian()
    }

    fun switchToEnglish() {
        profilePage.tapChangeLanguage()
        changeLanguagePage.selectEnglish()
    }

    fun verifyLabel(text: String) = profilePage.waitForLabel(text)

    fun changePassword(newPassword: String) {
        profilePage.tapChangePassword()
        changePasswordPage.waitUntilDisplayed()
        changePasswordPage.typePassword(newPassword)
        changePasswordPage.typeRepeatPassword(newPassword)
        changePasswordPage.tapConfirm()
    }
}