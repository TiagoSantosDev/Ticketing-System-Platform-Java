/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.jolt;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class JsonSchemaComparator {
    
    /**
     * Compares the default OLR Schema against the Schema from the client.
     * @param schemaMap
     * @return 
     */
    public static boolean compareSchemas(Map<String, Object> schemaMap) {
        
        boolean found = false;
        Iterator entries = schemaMap.entrySet().iterator();
        
        while (entries.hasNext()) {
            found = false;
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            
            for (olrSchemaKeys olrKey : olrSchemaKeys.values()) {
                if (key.equals(olrKey.name())) {
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("Non compatible Schemas");
                return false;
            }

        }
        
        System.out.println("Compatible Schemas");
        return true;
    }
    
}
