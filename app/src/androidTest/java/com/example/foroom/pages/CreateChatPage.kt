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
import androidx.test.espresso.matcher.ViewMatchers.withText

class CreateChatPage {

    private fun inputField(parentId: Int): ViewInteraction =
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(parentId)),
                isDisplayed()
            )
        )

    fun openCreateChatTab() =
        onView(allOf(withId(R.id.homeNavigationCreateChat), isDisplayed())).tap()

    fun waitUntilDisplayed() {
        onView(withId(R.id.createChatButton)).waitUntilVisible(15)
    }

    fun typeChatName(name: String) = inputField(R.id.chatNameInput).input(name)

    fun waitUntilImagesLoaded() {
        onView(allOf(withId(R.id.chatImageChooser), listWithLoadedImages()))
            .waitUntilVisible(20)
    }

    fun selectImage(index: Int) = onView(imageAt(index)).tap()

    fun tapCreateChat() = onView(withId(R.id.createChatButton)).tap()

    fun waitForCreatedChat(name: String) {
        onView(withId(R.id.messagesRecyclerView)).waitUntilVisible(20)
        onView(
            allOf(
                withId(DS.id.chatNameTextView),
                isDescendantOfA(withId(R.id.chatHeaderView)),
                withText(name),
                isDisplayed()
            )
        ).waitUntilVisible(10)
    }
    fun tapClose() = onView(allOf(withId(R.id.closeButton), isDisplayed())).tap()
}