package com.example.wadaeasy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit updaterequest, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Quote_service quote_service;

    @Before
    public void setup(){quote_service=new Quote_service();}

   @Test
    public void  tetcalcharge(){
        int result=quote_service.calcharge(4,10);
        assertEquals(40,result);

    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}