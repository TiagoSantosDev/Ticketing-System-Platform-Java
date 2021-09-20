/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hascode.db;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class DBMain {
    
    /**
     * Initializes connection to the DataBase.
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        DBConnection dao = new DBConnection();
        dao.readDataBase();
    }

}