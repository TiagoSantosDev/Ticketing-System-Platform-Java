/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 *
 * @author Tiago Santos / 1140927
 */
public class ConfigFileController {
    
    public static String dbuser;
    public static String dbpassword;
    public static String database;
    public static String dburl;
    
    /**
     * Extracts username, password, database name and database URL for secure
     * access to the Database.
     */
    public void extractConfigFileData() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("properties/config.properties");
            prop.load(input);
            
            dbuser = prop.getProperty("dbuser");
            dbpassword = prop.getProperty("dbpassword");
            database = prop.getProperty("database");
            dburl = prop.getProperty("dburl");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
