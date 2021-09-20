/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.tutorial;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hascode.jolt.CustomOLRSchemaGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class CustomSchemaGeneratorTest {
    
    private BufferedReader in = null;
    
    public CustomSchemaGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException{
    } 
    
    @After
    public void tearDown()throws IOException
    {
        if (in != null)
        {
            in.close();
        }

        in = null;
    }

    /**
     * Test of OLRSchemaGenerator method, of class CustomSchemaGenerator.
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGenerationOfOLRSchema() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("OLRSchemaGenerator");

        File f = new File("files/OLRSchemaGeneratorTestFile.json");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File exists");
        }

        CustomOLRSchemaGenerator.OLRSchemaGenerator(
                "files/OLRSchemaGeneratorTestFile.json",null);
          
    }
    
    /**
     * Test of OLRSchemaGenerator method, of class CustomSchemaGenerator.
     * @throws java.lang.IllegalAccessException
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testSerializationOfNullValues() throws IllegalArgumentException, IllegalAccessException, JsonProcessingException {
        System.out.println("Test Serialization of null values");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        File f = new File("files/OLRSchemaGeneratorTestFile_Null.json");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File exists");
        }

        CustomOLRSchemaGenerator.OLRSchemaGenerator(
                "files/OLRSchemaGeneratorTestFile_Null.json",null);
          
        Map<String, String> dtoMap = new HashMap<>();
        dtoMap.put("String1", "Test Value");
        dtoMap.put("String2", null);

        String dtoMapAsString = mapper.writeValueAsString(dtoMap);

        assertThat(dtoMapAsString, containsString("String1"));
        assertThat(dtoMapAsString, not(containsString("String2")));
        
    }
    
}
