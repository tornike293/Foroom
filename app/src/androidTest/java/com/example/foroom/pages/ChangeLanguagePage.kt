package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.tap

class ChangeLanguagePage {
    fun selectGeorgian() = onView(withId(R.id.languageButtonGeo)).tap()
    fun selectEnglish() = onView(withId(R.id.languageButtonEng)).tap()
}