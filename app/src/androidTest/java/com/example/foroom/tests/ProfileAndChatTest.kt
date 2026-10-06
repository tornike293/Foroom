package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(ClearSessionRule())
        .around(activityRule)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun changeLanguageGeorgianToEnglishAndBack() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(TEST_USER, TEST_PASSWORD)
        loginSteps.verifyHomeScreenDisplayed()

        profileSteps.openProfile()
        profileSteps.switchToGeorgian()
        profileSteps.verifyLabel("ენის შეცვლა")

        profileSteps.switchToEnglish()
        profileSteps.verifyLabel("Change Language")

        profileSteps.switchToGeorgian()
        profileSteps.verifyLabel("ენის შეცვლა")
    }

    @Test
    fun createChatAndFindItInList() {
        val chatName = "$CHAT_NAME_PREFIX${System.currentTimeMillis() % 100000}"

        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(TEST_USER, TEST_PASSWORD)
        loginSteps.verifyHomeScreenDisplayed()

        chatSteps.openCreateChat()
        chatSteps.createChat(chatName)
        chatSteps.verifyChatOpened(chatName)
        chatSteps.closeChat()

        chatSteps.searchChat(chatName)
        chatSteps.verifyChatInList(chatName)
    }

    @Test
    fun changePasswordAndLoginWithNewOne() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(TEST_USER, TEST_PASSWORD)
        loginSteps.verifyHomeScreenDisplayed()

        profileSteps.openProfile()
        profileSteps.changePassword(NEW_PASSWORD)

        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(TEST_USER, NEW_PASSWORD)
        loginSteps.verifyHomeScreenDisplayed()

        profileSteps.openProfile()
        profileSteps.changePassword(TEST_PASSWORD)
        loginSteps.verifyLoginScreenDisplayed()
    }

    companion object {
        private const val TEST_USER = "sandro"
        private const val TEST_PASSWORD = "12345678"
        private const val NEW_PASSWORD = "NewPass_4321"
        private const val CHAT_NAME_PREFIX = "Tornike Lobjanidze"

    }
}