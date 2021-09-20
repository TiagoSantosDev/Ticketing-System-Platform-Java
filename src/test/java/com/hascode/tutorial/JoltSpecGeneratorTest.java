/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.tutorial;

import com.hascode.jolt.JoltSpecGenerator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

/**
 *
 * @author tiasant
 */
public class JoltSpecGeneratorTest {
    
    public class TestSchema {
        public String a;
        public String b;
    }
    
    private final String EXPECTED_FILE_PATH = "files/JoltSpecGeneratorExpectedFile.json";
    private final String TEST_FILE_PATH = "files/JoltSpecGeneratorTestFile.json";
    private final String DIFFERENT_FILE_PATH = "files/test.json";
    TestSchema test;
    File expectedFile;
    File similarFile;
    File differentFile;
            
    public JoltSpecGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        test = new TestSchema();
        expectedFile = new File(EXPECTED_FILE_PATH);
        similarFile = new File(TEST_FILE_PATH);
        differentFile = new File(DIFFERENT_FILE_PATH);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of specGenerator method, of class JoltSpecGenerator.
     * @throws java.io.IOException
     */
    @Test
    public void testSpecGenerator() throws IOException {
        System.out.println("specGenerator");
        JoltSpecGenerator.specGenerator(TEST_FILE_PATH, test);
        boolean shouldPass = FileUtils.contentEquals(expectedFile, similarFile);
        boolean shouldFail = FileUtils.contentEquals(expectedFile, differentFile);
        
        Assert.assertTrue(shouldPass);
        Assert.assertFalse(shouldFail);
        
    }
    
}
