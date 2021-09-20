package com.hascode.jolt;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class CamelExample {

    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://scanned?fileName=input.json")
                        .to("jolt:/spec.json?inputType=JsonString&outputType=JsonString").to("file://transformed");
            }
        });

        main.run();
    }

}
