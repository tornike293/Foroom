package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps(
    private val createChatPage: CreateChatPage = CreateChatPage(),
    private val chatsPage: ChatsPage = ChatsPage()
) {
    fun openCreateChat() {
        createChatPage.openCreateChatTab()
        createChatPage.waitUntilDisplayed()
    }

    fun createChat(name: String) {
        createChatPage.typeChatName(name)
        createChatPage.waitUntilImagesLoaded()
        createChatPage.selectImage(1)
        createChatPage.tapCreateChat()
    }

    fun verifyChatOpened(name: String) = createChatPage.waitForCreatedChat(name)

    fun closeChat() = createChatPage.tapClose()

    fun searchChat(name: String) {
        chatsPage.openChatsTab()
        chatsPage.waitUntilDisplayed()
        chatsPage.searchFor(name)
    }

    fun verifyChatInList(name: String) = chatsPage.waitForChatCard(name)
}