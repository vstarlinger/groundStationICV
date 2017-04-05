/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @see https://netbeans.org/kb/docs/java/junit-intro.html
 */
package com.aerospaceresearch.mirka2rxgroundstation.sensors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class IridiumTest {
    
    public IridiumTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        /*
            The setUp method is a test initialization method and is run before 
            each test case in the test class. A test initialization method is 
            not required for running tests, but if you need to initialize some 
            variables before you run a test, you use the test initializer 
            method.
        */
    }
    
    @AfterClass
    public static void tearDownClass() {
        /*
            The tearDown method is a test finalizer method and is run after 
            each test case in the test class. A test finalizer method is not 
            required for running tests, but you may need a finalizer to clean 
            up any data that was required when running the test cases.
        */
    }

    /**
     * Test of toString method, of class Iridium.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Iridium instance = new Iridium(10, 100, 50, 1);
        String expResult = "Iridium: Signalstrength: " + 10 + " High: " + 100 +
                            " Low: " + 50 + " Tries: " + 1;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSignalstrength method, of class Iridium.
     */
    @Test
    public void testGetSignalstrength() {
        System.out.println("getSignalstrength");
        Iridium instance = new Iridium(10, 100, 50, 1);
        int expResult = 10;
        int result = instance.getSignalstrength();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSignalstrength method, of class Iridium.
     */
    @Test
    public void testSetSignalstrength() {
        System.out.println("setSignalstrength");
        int signalstrength = 20;
        Iridium instance = new Iridium(10, 100, 50, 1);
        instance.setSignalstrength(signalstrength);
        assertEquals(signalstrength, instance.getSignalstrength());
    }

    /**
     * Test of getAlltimeHigh method, of class Iridium.
     */
    @Test
    public void testGetAlltimeHigh() {
        System.out.println("getAlltimeHigh");
        Iridium instance = new Iridium(10, 100, 50, 1);
        int expResult = 100;
        int result = instance.getAlltimeHigh();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAlltimeHigh method, of class Iridium.
     */
    @Test
    public void testSetAlltimeHigh() {
        System.out.println("setAlltimeHigh");
        int alltimeHigh = 200;
        Iridium instance = new Iridium(10, 100, 50, 1);
        instance.setAlltimeHigh(alltimeHigh);
        assertEquals(alltimeHigh, instance.getAlltimeHigh());
    }

    /**
     * Test of getAlltimeLow method, of class Iridium.
     */
    @Test
    public void testGetAlltimeLow() {
        System.out.println("getAlltimeLow");
        Iridium instance = new Iridium(10, 100, 50, 1);
        int expResult = 50;
        int result = instance.getAlltimeLow();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAlltimeLow method, of class Iridium.
     */
    @Test
    public void testSetAlltimeLow() {
        System.out.println("setAlltimeLow");
        int alltimeLow = 10;
        Iridium instance = new Iridium(10, 100, 50, 1);
        instance.setAlltimeLow(alltimeLow);
        assertEquals(alltimeLow, instance.getAlltimeLow());
    }

    /**
     * Test of getTries method, of class Iridium.
     */
    @Test
    public void testGetTries() {
        System.out.println("getTries");
        Iridium instance = new Iridium(10, 100, 50, 1);
        int expResult = 1;
        int result = instance.getTries();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTries method, of class Iridium.
     */
    @Test
    public void testSetTries() {
        System.out.println("setTries");
        int tries = 2;
        Iridium instance = new Iridium(10, 100, 50, 1);
        instance.setTries(tries);
        assertEquals(tries, instance.getTries());
    }
    
}
