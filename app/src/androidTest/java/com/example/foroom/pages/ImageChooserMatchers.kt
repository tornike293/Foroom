package com.example.foroom.pages

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import org.hamcrest.Description
import com.example.shared.model.Image


fun listWithChoosingEnabled() =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image list with choosing enabled")
        }

        override fun matchesSafely(view: ImageChooserListView) = view.isChoosingEnabled
    }

fun imageAt(index: Int) =
    object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image at index $index")
        }

        override fun matchesSafely(view: ImageChooserItemView): Boolean {
            val row = view.parent as? ViewGroup ?: return false
            val list = row.parent as? ImageChooserListView ?: return false
            return list.indexOfChild(row) * list.columns + row.indexOfChild(view) / 2 == index
        }
    }


fun listWithLoadedImages() =
    object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("image list with real (non-blank) images")
        }

        override fun matchesSafely(view: ImageChooserListView): Boolean =
            view.images.isNotEmpty() && view.images.none { it.id == Image.BLANK_IMAGE_ID }
    }