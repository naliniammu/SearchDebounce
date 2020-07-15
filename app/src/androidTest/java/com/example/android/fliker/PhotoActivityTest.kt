package com.example.android.fliker

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.android.fliker.ui.PhotosActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoActivityTest {
    @Rule
    var main =
        ActivityTestRule(PhotosActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun testShouldLaunchTheMainActivityAndFindItemsInTheList() {
        val recyclerView =
            main.activity.findViewById<View>(R.id.photosRecyclerView) as RecyclerView
        ViewMatchers.assertThat(recyclerView.scrollBarSize, Matchers.`is`(11))
    }

    @Test
    fun ensureTextChangesWork() { // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.searchBox))
            .perform(ViewActions.typeText("dogs"), ViewActions.closeSoftKeyboard())
        // Check that the text was changed.
        Espresso.onView(ViewMatchers.withId(R.id.searchBox))
            .check(ViewAssertions.matches(ViewMatchers.withText("dogs")))
    }

    @Test
    @Throws(Exception::class)
    fun testShouldTestTheItemNameInTheList() {
        val recyclerView =
            main.activity.findViewById<View>(R.id.photosRecyclerView) as RecyclerView
        /* check if the ViewHolder is being displayed */if (recyclerView.scrollBarSize > 0) {
            for (i in 0 until recyclerView.scrollBarSize) {
                if (i == 1) {
                    Espresso.onView(ViewMatchers.withId(R.id.photosRecyclerView)).perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                            i,
                            ViewActions.typeTextIntoFocusedView("Flag")
                        )
                    )
                }
            }
        }
    }
}