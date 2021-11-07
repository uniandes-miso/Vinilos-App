package com.miso.vinilosapp;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.miso.vinilosapp.view.MainActivity;

import com.miso.vinilosapp.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HU_consultarAlbum {

@Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

@Test
    public void mainTestActivity (){

    ViewInteraction skipBtn = onView(allOf(withId(R.id.button), withText("Vinilos App! "),isDisplayed()));
    skipBtn.perform(click());

    ViewInteraction albumBtn = onView(allOf(withId(R.id.button_albumes), withText("Albumes"),isDisplayed()));
    albumBtn.perform(click());

    ViewInteraction confirmLoginBtn = onView(allOf(withId(R.id.titleAlbums), withText("Álbumes")));
    confirmLoginBtn.noActivity();
//check(matches(isDisplayed());


    }
}
