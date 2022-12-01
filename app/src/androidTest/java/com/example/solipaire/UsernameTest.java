package com.example.solipaire;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.solipaire.activity.StartScreenActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UsernameTest {

    public static final String USERNAME_TO_CHECK = "testuser";
    public static final String PASSWORD_TO_USE = "testpass";

    @Rule
    public ActivityScenarioRule<StartScreenActivity> activityScenarioRule
            = new ActivityScenarioRule<>(StartScreenActivity.class);

    @Test
    public void checkUsernameDisplay() {
        onView(withId(R.id.SignUpButton)).perform(click());

        onView(withId(R.id.mNewUserName)).perform(typeText(USERNAME_TO_CHECK),closeSoftKeyboard());
        onView(withId(R.id.mNewPassword)).perform(typeText(PASSWORD_TO_USE),closeSoftKeyboard());
        onView(withId(R.id.mNewPasswordReEnter)).perform(typeText(PASSWORD_TO_USE),closeSoftKeyboard());

        onView(withId(R.id.createAccountButton)).perform(click(),pressBack());

        onView(withId(R.id.LogInButton)).perform(click());

        onView(withId(R.id.mUserName)).perform(typeText(USERNAME_TO_CHECK),closeSoftKeyboard());
        onView(withId(R.id.mPassword)).perform(typeText(PASSWORD_TO_USE),closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.userDisplay)).check(matches(withText(USERNAME_TO_CHECK)));
    }
}