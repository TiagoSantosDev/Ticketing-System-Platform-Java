/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.tutorial;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class JsonDataProcessorTest {
    
    Map<String, Object> expectedPass;
    Map<String, Object> expectedFail;
    
    public JsonDataProcessorTest() {   
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        expectedPass = new HashMap<>();
        expectedFail = new HashMap<>();
        
        expectedPass.put("cliID","tiasant");
        expectedPass.put("description","incident");     
        
        expectedFail.put("short_description", "incident");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of extractData method, of class JsonDataProcessor.
     * @throws java.io.IOException
     */
    @Test
    public void testExtractData() throws IOException {
        System.out.println("extractData");
        
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> schemaMap = mapper.readValue(new File(
                "files/JsonDataProcessorTestFile.json"),
                    new TypeReference<Map<String, Object>>() {
        });
        
        System.out.println(schemaMap);
        
        Assert.assertTrue(schemaMap.equals(expectedPass));
        Assert.assertFalse(schemaMap.equals(expectedFail));
        
    }
    
}
