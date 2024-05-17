package com.example.infoo10;

import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)

public class HomeFragmentUiTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        // Launch the MainActivity with HomeFragment
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        activityRule.launchActivity(intent);

        // Navigate to HomeFragment
        onView(withId(R.id.home)).perform(click());
    }

    @Test
    public void testHomeFragmentUI() {
        // Check if the search view is displayed
        onView(withId(R.id.search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the "Recent Releases" TextView is displayed
        onView(withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("Recent Releases")));
    }

    @Test
    public void testMovieDataLoading() {
        // Check if the first movie title is displayed in the RecyclerView
        // Replace with an actual movie title from your JSON file
        onView(withText(containsString("Title")))
                .perform(scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSearchFunctionality() {
        // Type text in the search view
        onView(withId(androidx.appcompat.R.id.search_src_text))
                .perform(typeText("Title")); // Replace with part of an actual movie title from your JSON file

        // Check if the filtered movie title is displayed in the RecyclerView
        onView(withText(containsString("Title"))) // Replace with part of the same movie title
                .perform(scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}