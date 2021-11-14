package com.miso.vinilosapp.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.miso.vinilosapp.R;
import com.miso.vinilosapp.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HUConsultAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainTestActivity (){

        //ViewInteraction representa la vista que se retorna desde un onView()
        // es usado para los assert a la vista
        ViewInteraction skipBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.button), withText("Vinilos App! "),isDisplayed()));
        skipBtn.check(matches(withId(R.id.button)));
        skipBtn.perform(click());

        ViewInteraction albumBtn = onView(allOf(withId(R.id.button_albumes), withText("Albumes"),isDisplayed()));
        albumBtn.check(matches(withId(R.id.button_albumes)));
        albumBtn.perform(click());

       // ViewInteraction confirmLoginBtn = onView((withId(R.id.albumFragment)));
       // confirmLoginBtn.perform(click());

       // ViewInteraction returnMenu = onView(allOf(withId(R.id.musiciansFragment), withId(R.id.musiciansFragment),isDisplayed()));
       // returnMenu.perform(click());
//check(matches(isDisplayed());

    }

}
