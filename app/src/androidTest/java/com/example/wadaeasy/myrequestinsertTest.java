package com.example.wadaeasy;

import android.app.Activity;
import android.view.View;


import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;




public class myrequestinsertTest{

    @Rule
    public ActivityTestRule<myrequestinsert> mainTestRule = new ActivityTestRule<myrequestinsert>(myrequestinsert.class);

    public  myrequestinsert mActivity = null;
    @Before
    public void setUp() throws Exception {
         mActivity = mainTestRule.getActivity();

    }

    @Test
    public void testUserInputScenario(){

        //if not null the activity has launched
        View view=mActivity.findViewById(R.id.cl_Name);
        Assert.assertNotNull(view);

    }

    public  void testLaunch(){


    }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}