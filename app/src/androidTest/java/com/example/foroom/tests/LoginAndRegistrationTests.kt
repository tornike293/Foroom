package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext


class ClearSessionRule : ExternalResource() {
    override fun before() {
        val store = GlobalContext.get().get<ForoomUserDataStore>()
        runBlocking { store.clearUserData() }
    }
}

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(ClearSessionRule())
        .around(activityRule)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun validUsernameInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(EXISTING_USER, WRONG_PASSWORD)
        loginSteps.verifyPasswordError()
    }

    @Test
    fun invalidUsernameInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith("$NON_EXISTING_USER_PREFIX${System.currentTimeMillis()}", WRONG_PASSWORD)
        loginSteps.verifyUsernameError()
        loginSteps.verifyPasswordError()
    }

    @Test
    fun successfulRegistration() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.tapSignUp()
        registrationSteps.verifyRegistrationScreenDisplayed()
        registrationSteps.register("$NEW_USER_PREFIX${System.currentTimeMillis()}", VALID_PASSWORD)
        registrationSteps.verifyHomeScreenDisplayed()
    }

    companion object {
        private const val EXISTING_USER = "tornike"
        private const val NON_EXISTING_USER_PREFIX = "no_such_user_"
        private const val WRONG_PASSWORD = "wrongPass_123"
        private const val NEW_USER_PREFIX = "tornike2"
        private const val VALID_PASSWORD = "Pass1234!"
    }
}