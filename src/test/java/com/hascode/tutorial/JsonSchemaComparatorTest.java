/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.tutorial;

import com.hascode.jolt.JsonSchemaComparator;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class JsonSchemaComparatorTest {
    
    public JsonSchemaComparatorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        

        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareSchemas method, of class JsonSchemaComparatorTest.
     */
    @org.junit.Test
    public void testCompareSchemas() {
        System.out.println("compareSchemas");
        
        Map<String, Object> schemaMap = new HashMap<String, Object>();
        schemaMap.put("cliID", null);
        schemaMap.put("description", null);
        schemaMap.put("address", null);
        schemaMap.put("short_des", null);
        schemaMap.put("sysCreatedBy", null);
        
        boolean expResult = true;
        boolean result = JsonSchemaComparator.compareSchemas(schemaMap);
        assertEquals(expResult, result);
    }
    
}
