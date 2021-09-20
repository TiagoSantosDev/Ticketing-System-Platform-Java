/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.jolt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Inclusion;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import org.codehaus.jackson.map.ObjectMapper;


/**
 *
 * @author Tiago Santos / 1140927
 */
@JsonInclude(Include.NON_NULL)
public class CustomOLRSchemaGenerator {
    
    /**
     * Generates a JSON Schema
     * @param filePath
     * @param connectMap
     * @throws java.lang.IllegalAccessException
     */
    public static void OLRSchemaGenerator(String filePath, LinkedHashMap connectMap) 
            throws IllegalArgumentException, IllegalAccessException {
        
        for (Object object : connectMap.keySet()) {
            if (connectMap.get(object) == null) {
                connectMap.remove(object);
            }
        }
         
        
        OLRSchema schemaObj = new OLRSchema();
        
        for (Field field : schemaObj.getClass().getDeclaredFields()) {
            if (connectMap.containsKey(field.getName())) {
                String value = (String) connectMap.get(field.getName());
                field.set(schemaObj, value);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        

        
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        mapper.setSerializationInclusion(Include.NON_EMPTY); 
        
        try {
            mapper.writeValue(new File(filePath), schemaObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}

