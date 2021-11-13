package com.miso.vinilosapp.ui;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.miso.vinilosapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        /*ViewInteraction view = onView(
                allOf(withParent(IsInstanceOf.<View>instanceOf(FrameLayout.class)),
                        isDisplayed()));
        view.check(matches(isDisplayed())); */

        ViewInteraction skipBtn = onView(
                allOf(withId(R.id.button), withText("Vinilos App! "),isDisplayed()));
        skipBtn.perform(click());

       /* ViewInteraction linearLayout = onView(
                allOf(withId(com.android.systemui.R.id.container),
                        withParent(allOf(withId(com.android.systemui.R.id.keyguard_pattern_view),
                                withParent(withId(com.android.systemui.R.id.view_flipper)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(com.android.systemui.R.id.container),
                        withParent(allOf(withId(com.android.systemui.R.id.keyguard_pattern_view),
                                withParent(withId(com.android.systemui.R.id.view_flipper)))),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed())); */
    }
}
