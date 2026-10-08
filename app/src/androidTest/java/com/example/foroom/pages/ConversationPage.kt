package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.alternator.foroom.R
import com.example.design_system.R as DS
import com.example.foroom.Helper.input
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf

class ConversationPage {

    private fun messagesList() = withId(R.id.messagesRecyclerView)

    private fun messageText(text: String) = allOf(
        withId(DS.id.messageTextView),
        withText(text),
        isDescendantOfA(messagesList())
    )

    fun waitUntilOpened(chatName: String) {
        onView(messagesList()).waitUntilVisible(20)
        onView(
            allOf(
                withId(DS.id.chatNameTextView),
                isDescendantOfA(withId(R.id.chatHeaderView)),
                withText(chatName),
                isDisplayed()
            )
        ).waitUntilVisible(10)
    }

    fun typeMessage(text: String) {
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(R.id.messageInput)),
                isDisplayed()
            )
        ).input(text)
    }

    fun tapSend() {
        val send = onView(
            allOf(
                withId(DS.id.sendMessageButton),
                isDescendantOfA(withId(R.id.messageInput)),
                isEnabled(),
                isDisplayed()
            )
        )
        send.waitUntilVisible(15).tap()
    }

    fun waitForMessage(text: String) {
        onView(allOf(messageText(text), isDisplayed())).waitUntilVisible(20)
    }

    fun waitForSender(text: String, sender: String) {
        onView(
            allOf(
                withId(DS.id.userNameTextView),
                withText(sender),
                isDescendantOfA(allOf(withId(DS.id.contentLinearLayout), hasDescendant(messageText(text)))),
                isDescendantOfA(messagesList()),
                isDisplayed()
            )
        ).waitUntilVisible(10)
    }

    fun isMessageDisplayed(text: String): Boolean = try {
        onView(allOf(messageText(text), isDisplayed())).check(matches(isDisplayed()))
        true
    } catch (e: Throwable) {
        false
    }

    fun swipeToOlderMessages() {
        var top = 0
        var height = 0
        onView(messagesList()).check { view, _ ->
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            top = location[1]
            height = view.height
        }
        swiper(top + height / 4, top + height * 3 / 4, 100)
    }

    fun tapClose() = onView(allOf(withId(R.id.closeButton), isDisplayed())).tap()
}