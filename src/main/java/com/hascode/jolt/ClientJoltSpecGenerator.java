/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.jolt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class ClientJoltSpecGenerator {
    
    /**
     * Generates a spec file.
     * @param filePath
     * @param fields
     * @throws IOException 
     */
    public static void clientSpecGenerator(String filePath, 
            LinkedHashMap fields) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        String schemaString = mapper.writeValueAsString(fields);
        
        System.out.println(schemaString);
       
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        String header = "[\n"
                + "    {\n"
                + "        \"operation\": \"shift\","
                + "\n"
                + "        \"spec\":";

        String footer = "\n    }\n"
                + "]";

        try {

            File file = new File(filePath);

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);


            try {
                Files.write(Paths.get(filePath), header.getBytes(), 
                        StandardOpenOption.APPEND);
                Files.write(Paths.get(filePath), schemaString.getBytes(),
                        StandardOpenOption.APPEND);
                Files.write(Paths.get(filePath), footer.getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

    }

    
}
