package com.example.foroom.pages

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DS
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf

class RegistrationPage {

    private fun inputField(parentId: Int): ViewInteraction =
        onView(
            allOf(
                withId(DS.id.inputEditText),
                isDescendantOfA(withId(parentId)),
                isDisplayed()
            )
        )

    private fun listWithChoosingEnabled() =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with choosing enabled")
            }

            override fun matchesSafely(view: ImageChooserListView) = view.isChoosingEnabled
        }

    private fun avatarAt(index: Int) =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar at index $index")
            }

            override fun matchesSafely(view: ImageChooserItemView): Boolean {
                val row = view.parent as? ViewGroup ?: return false
                val list = row.parent as? ImageChooserListView ?: return false
                return list.indexOfChild(row) * list.columns + row.indexOfChild(view) / 2 == index
            }
        }

    fun waitUntilDisplayed() {
        onView(withId(R.id.repeatPasswordInput)).waitUntilVisible(15)
    }

    fun typeUsername(text: String) = inputField(R.id.userNameInput).input(text)

    fun typePassword(text: String) = inputField(R.id.passwordInput).input(text)

    fun typeRepeatPassword(text: String) = inputField(R.id.repeatPasswordInput).input(text)

    fun waitUntilAvatarsLoaded() {
        onView(allOf(withId(R.id.listView), listWithChoosingEnabled())).waitUntilVisible(20)
    }

    fun selectAvatar(index: Int) = onView(avatarAt(index)).tap()

    fun tapSignUp() = onView(allOf(withId(R.id.signUpButton), isDisplayed())).tap()

    fun waitForHomeScreen() {
        onView(withId(R.id.navBar)).waitUntilVisible(20)
    }
}