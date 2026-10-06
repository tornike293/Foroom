package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DS
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf

class ChatsPage {

    fun openChatsTab() =
        onView(allOf(withId(R.id.homeNavigationChats), isDisplayed())).tap()

    fun waitUntilDisplayed() {
        onView(allOf(withId(R.id.searchChatInput), isDisplayed())).waitUntilVisible(15)
    }

    fun searchFor(name: String) {
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(R.id.searchChatInput)),
                isDisplayed()
            )
        ).input(name)
    }

    fun waitForChatCard(name: String) {
        onView(
            allOf(
                withId(DS.id.chatTitleTextView),
                isDescendantOfA(withId(R.id.chatsRecyclerView)),
                withText(name),
                isDisplayed()
            )
        ).waitUntilVisible(20)
    }
}