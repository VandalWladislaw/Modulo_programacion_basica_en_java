package com.wandal.wave.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wandal.wave.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatButton = onView(
allOf(withId(R.id.buttonPlayback),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0),
7),
isDisplayed()))
        appCompatButton.perform(click())
        
        val appCompatButton2 = onView(
allOf(withId(R.id.buttonPlayback),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0),
7),
isDisplayed()))
        appCompatButton2.perform(click())
        
        val tabView = onView(
allOf(withContentDescription("Playlist"),
childAtPosition(
childAtPosition(
withId(R.id.tab_layout),
0),
1),
isDisplayed()))
        tabView.perform(click())
        
        val tabView2 = onView(
allOf(withContentDescription("Albums"),
childAtPosition(
childAtPosition(
withId(R.id.tab_layout),
0),
2),
isDisplayed()))
        tabView2.perform(click())
        
        val tabView3 = onView(
allOf(withContentDescription("Tracks"),
childAtPosition(
childAtPosition(
withId(R.id.tab_layout),
0),
3),
isDisplayed()))
        tabView3.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
