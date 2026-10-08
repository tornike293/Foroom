package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ConversationPage

class ConversationSteps(
    private val chatsPage: ChatsPage = ChatsPage(),
    private val conversationPage: ConversationPage = ConversationPage()
) {
    fun openChat(name: String) {
        chatsPage.openChatsTab()
        chatsPage.waitUntilDisplayed()
        chatsPage.searchFor(name)
        chatsPage.waitForChatCard(name)
        chatsPage.openChat(name)
        conversationPage.waitUntilOpened(name)
    }

    fun sendMessage(text: String) {
        conversationPage.typeMessage(text)
        conversationPage.tapSend()
        conversationPage.waitForMessage(text)
    }

    fun sendManyMessages(prefix: String, count: Int) {
        repeat(count) { index -> sendMessage("$prefix ${index + 1}") }
    }

    fun verifyMessageDisplayed(text: String) = conversationPage.waitForMessage(text)

    fun verifyMessageSender(text: String, sender: String) =
        conversationPage.waitForSender(text, sender)

    fun swipeUntilMessageVisible(text: String, maxSwipes: Int = 12) {
        repeat(maxSwipes) {
            if (conversationPage.isMessageDisplayed(text)) return
            conversationPage.swipeToOlderMessages()
        }
        if (!conversationPage.isMessageDisplayed(text)) {
            throw AssertionError("Message '$text' not found after $maxSwipes swipes")
        }
    }

    fun closeChat() = conversationPage.tapClose()
}