package com.hascode.jolt;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JacksonExample {
    public static void main(String[] args) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        
        String s1 = "ola";
        String s2 = null;

        String rs1 = null;
        String rs2 = null;
        rs1 = mapper.writeValueAsString(s1);
        rs2 = mapper.writeValueAsString(s2);
        
        try{
            mapper.writeValue(new File("jackson.json"), s1 );
            mapper.writeValue(new File("jackson2.json"), s2 );
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        
        Map<String, String> dtoMap = new HashMap<String, String>();
        dtoMap.put("dtoObject1", "ola");
        dtoMap.put("dtoObject2", null);

        String dtoMapAsString = mapper.writeValueAsString(dtoMap);

        System.out.println(dtoMapAsString);

        
    }
    
}

