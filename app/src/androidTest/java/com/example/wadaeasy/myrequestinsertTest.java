package com.example.wadaeasy;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class myrequestinsertTest{

    @Rule
    public ActivityTestRule<myrequestinsert> mActivityActivityTestRule = new ActivityTestRule<myrequestinsert>(myrequestinsert.class);

    public  myrequestinsert mActivityActivity = null;
    @Before
    public void setUp() throws Exception {
    }

    @Test

    public  void testLaunch(){

        View view = mActivityActivity.findViewById(R.id.cl_name);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        mActivityActivity = null;
    }
}