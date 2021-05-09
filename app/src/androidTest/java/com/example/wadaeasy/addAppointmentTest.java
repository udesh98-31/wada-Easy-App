package com.example.wadaeasy;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class addAppointmentTest extends TestCase {

    @Rule
    public ActivityTestRule<addAppointment> mainTestRule = new ActivityTestRule<addAppointment>(addAppointment.class);

    private addAppointment mActivity=null;
    @Before
    public void setUp() throws Exception {
        mActivity=mainTestRule.getActivity();
    }


    //if able to find view and it has been launched then the view has been launched
    @Test
    public void testLaunchofSecondActivity(){
        //if not null the activity has launched
        View view=mActivity.findViewById(R.id.ap_confirm);
        assertNotNull(view);




    }


    @After
    public void tearDown() throws Exception {

        mActivity=null;
    }
}