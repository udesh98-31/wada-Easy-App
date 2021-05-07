package com.example.wadaeasy;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;


public class myrequestinsertTest{

    @Rule
    public ActivityTestRule<myrequestinsert> mActivityActivityTestRule = new ActivityTestRule<myrequestinsert>(myrequestinsert.class);

    private String nName = "Tony";
    //public  myrequestinsert mActivityActivity = null;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUserInputScenario(){

        //input some text in the edit text
        Espresso.onView(withId(R.id.cl_Name)).perform(typeText(nName));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.edit)).perform(click());
        //checking the text in the textview
        Espresso.onView(withId(R.id.nameview)).check(matches(withText(nName)));
    }

    public  void testLaunch(){

        //View view = mActivityActivity.findViewById(R.id.cl_name);
        //assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        //mActivityActivity = null;
    }
}