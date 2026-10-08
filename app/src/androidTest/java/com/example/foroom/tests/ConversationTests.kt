package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(ClearSessionRule())
        .around(activityRule)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val conversationSteps = ConversationSteps()

    private fun signInAs(username: String, password: String) {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(username, password)
        loginSteps.verifyHomeScreenDisplayed()
    }

    private fun signOut() {
        profileSteps.openProfile()
        profileSteps.signOut()
        loginSteps.verifyLoginScreenDisplayed()
    }

    private fun unique() = System.currentTimeMillis() % 100000

    @Test
    fun sendMessageInJohnWeekChat() {
        val message = "let's go for a drink ${unique()}"

        signInAs(USER_A, PASSWORD_A)
        conversationSteps.openChat(JOHN_WEEK)
        conversationSteps.sendMessage(message)

        conversationSteps.closeChat()
        conversationSteps.openChat(JOHN_WEEK)
        conversationSteps.verifyMessageDisplayed(message)
    }

    @Test
    fun sendQuestionInOwnNameChat() {
        val question = "Which module do you like most in the Automation Academy? ${unique()}"

        signInAs(USER_A, PASSWORD_A)
        conversationSteps.openChat(OWN_CHAT)
        conversationSteps.sendMessage(question)
    }

    @Test
    fun continueConversationWithAnotherAccount() {
        val suffix = unique()
        val greeting = "hello from A $suffix"
        val reply = "hello from B $suffix"

        signInAs(USER_A, PASSWORD_A)
        conversationSteps.openChat(SHARED_CHAT)
        conversationSteps.sendMessage(greeting)
        conversationSteps.sendManyMessages("filler $suffix", 26)
        conversationSteps.closeChat()
        signOut()

        signInAs(USER_B, PASSWORD_B)
        conversationSteps.openChat(SHARED_CHAT)
        conversationSteps.swipeUntilMessageVisible(greeting)
        conversationSteps.verifyMessageSender(greeting, USER_A)
        conversationSteps.sendMessage(reply)
        conversationSteps.closeChat()
        signOut()

        signInAs(USER_A, PASSWORD_A)
        conversationSteps.openChat(SHARED_CHAT)
        conversationSteps.verifyMessageDisplayed(reply)
        conversationSteps.verifyMessageSender(reply, USER_B)
    }

    companion object {
        private const val USER_A = "ani"
        private const val PASSWORD_A = "12345678"
        private const val USER_B = "natia"
        private const val PASSWORD_B = "12345678"

        private const val JOHN_WEEK = "johnWeek"
        private const val OWN_CHAT = "Tornike Lobjanidze TBC"
        private const val SHARED_CHAT = "something"
    }
}