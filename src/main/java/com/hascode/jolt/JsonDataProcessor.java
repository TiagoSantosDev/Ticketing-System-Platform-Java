/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.jolt;

import java.io.File;
import java.util.Map;
 
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;

/**
 *
 * @author Tiago Santos / 1140927
 */

public class JsonDataProcessor {
    
    /**
     * Extracts data from JSON file and converts it into a 
     * Map-type structure for JAVA processing
     * @param filePath
     * @return 
     */
    public static LinkedList extractData(String filePath) {
        
        
        ObjectMapper mapper = new ObjectMapper();
        LinkedList paramFieldsList = new LinkedList<>();
        

        try {
            Map<String, Object> schemaMap = mapper.readValue(new File(
                    filePath), new TypeReference<Map<String, Object>>() {
            });

            JsonSchemaComparator.compareSchemas(schemaMap);
            
            for (String field : schemaMap.keySet()) {
                paramFieldsList.add(field);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(paramFieldsList);
        
        return paramFieldsList;

    }
    
}
