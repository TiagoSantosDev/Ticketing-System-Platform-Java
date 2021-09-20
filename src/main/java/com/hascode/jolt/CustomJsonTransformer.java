package com.hascode.jolt;

import java.util.List;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.LinkedHashMap;


/**
 * 
 * @author Tiago Santos / 1140927
 */
public class CustomJsonTransformer {
    
    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("|                 JSON Integration Platform 1.0.0                      |");
        System.out.println("------------------------------------------------------------------------");
        
        
//        JsonDataProcessor processor = new JsonDataProcessor();
//        processor.extractData("files/test.json");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        List<Object> specs = JsonUtils.classpathToList("/spec_olr.json");
        OLRSchema olr = new OLRSchema();
        
        LinkedHashMap teste = new LinkedHashMap<>();
        
        teste.put("description", "descricao");
//        
//        
        LinkedHashMap<String,String> olrGen = new LinkedHashMap<String,String>();
        
        ClientJoltSpecGenerator clientGen = new ClientJoltSpecGenerator();
        clientGen.clientSpecGenerator("files/generatedClientSpec.json", teste);
        
        JoltSpecGenerator specGen = new JoltSpecGenerator();
        specGen.specGenerator("files/generatedClientSpec.json",olr);
//        
        CustomOLRSchemaGenerator schema = new CustomOLRSchemaGenerator();
        schema.OLRSchemaGenerator("files/test.json", teste);
        
        JsonDataProcessor processor = new JsonDataProcessor();
        processor.extractData("files/test.json");
        
        Chainr chainr = Chainr.fromSpec(specs);

        Object inputJSON = JsonUtils.classpathToObject("/input_cli.json");
        Object transformedOutput = chainr.transform(inputJSON);
        System.out.println(JsonUtils.toPrettyJsonString(transformedOutput));
    }
}
