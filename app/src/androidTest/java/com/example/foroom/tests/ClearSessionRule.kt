package com.example.foroom.tests

import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import kotlinx.coroutines.runBlocking
import org.junit.rules.ExternalResource
import org.koin.core.context.GlobalContext

class ClearSessionRule : ExternalResource() {
    override fun before() {
        val store = GlobalContext.get().get<ForoomUserDataStore>()
        runBlocking { store.clearUserData() }
    }
}