package com.hascode.db;

import com.hascode.io.ConfigFileController;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tiago Santos / 1140927
 */
public class DBConnection {
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet mappingSet = null;
    private ResultSet fieldsSet = null;
    private ResultSet numberOfClientsSet = null;
    
    private final int client = 1;
    private int numberOfClients = 0;
    
    private LinkedHashMap firstSchema = null;
    
    //MySQL Queries 
    private static final String SELECT_ALL_MAP_DATA = "select * from mapping_data";
    private static final String SELECT_ALL_MAP_DATA_WHERE_CLIENTID = 
            "select * from mapping_data where client_id = ? ";
    private static final String SELECT_CLIFIELD_MAP_DATA_WHERE_CLIENT_ID = 
            "select client_field from mapping_data where client_id = ?";
    private static final String NUMBER_OF_CLIENTS  = 
            "select MAX(id) AS id FROM client_data;";

   
    private final RetrieveClientFields retrieve = new RetrieveClientFields();

    /**
     * Retrieves data from the Database.
     * @throws Exception
     */
    public void readDataBase() throws Exception {
        try {
            
            ConfigFileController config = new ConfigFileController();
            config.extractConfigFileData();
            
            
            Class.forName("com.mysql.jdbc.Driver");

            
      
            connect = DriverManager.getConnection (config.dburl, config.dbuser,
                    config.dbpassword);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            ArrayList<String> paramFieldsList = new ArrayList<>();
            
            paramFieldsList.add("descWGL");
            paramFieldsList.add("closedWGL");
            paramFieldsList.add("incWGL");
            paramFieldsList.add("sysWGL");
            paramFieldsList.add("calWGL");

            searchAndCompare(paramFieldsList);
            //getNumberOfClients();
            
                   
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    /**
     * 
     * @param resultSet
     * @throws SQLException 
     */
    private void writeMetaData(ResultSet resultSet) throws SQLException {

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " " + 
                    resultSet.getMetaData().getColumnName(i));
        }
    }

    /**
     * Retrieves data from the client table and outputs it in
     * the console log.
     * @param resultSet
     * @throws SQLException 
     */
    private void writeResultSetFromClientTable(ResultSet resultSet) 
            throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String cli_name = resultSet.getString("client_name");
            String cli_initials = resultSet.getString("client_initials");
            String endpoint = resultSet.getString("endpoint");
            System.out.println("ID: " + id);
            System.out.println("Client name: " + cli_name);
            System.out.println("Initials: " + cli_initials);
            System.out.println("Endpoint: " + endpoint);
        }
    }
    
    /**
     * Retrieves data from the mapping table and inserts into 
     * a LinkedHashMap.
     * @param resultSet
     * @throws SQLException 
     */
    private LinkedHashMap writeResultSetFromMappingTable(ResultSet mappingSet,
            int client_id) throws SQLException {
        
        preparedStatement = connect.prepareStatement
                                (SELECT_ALL_MAP_DATA_WHERE_CLIENTID);
        preparedStatement.setInt(1, client);

        mappingSet = preparedStatement.executeQuery();
        
        String id = null;
        String cli_id = null;        
        String cli_field = null;
        String olr_field = null;
                
        while (mappingSet.next()) {
            id = mappingSet.getString("id");
            cli_id = mappingSet.getString("client_id");
            cli_field = mappingSet.getString("client_field");
            olr_field = mappingSet.getString("olr_field");
        }
         
        System.out.println(id);
        firstSchema.put("id", id);
        firstSchema.put("client_id", cli_id);
        firstSchema.put("id", id);
        firstSchema.put("id", id);
        
        
        return firstSchema;
       
        
        
    }
    
    /**
     * Searches the database for the client whose fields
     * match the ones found in the received JSON object.
     * @param resultSet
     * @return
     * @throws SQLException 
     */
    private boolean searchAndCompare( ArrayList<String> paramFieldsList)
            throws SQLException {
        
        numberOfClients = getNumberOfClients();
       
        boolean[] wasFound = new boolean[paramFieldsList.size()];
        
        
        for (int client_id = 1; client_id <= numberOfClients; client_id++) {

            preparedStatement = connect.
                    prepareStatement(SELECT_CLIFIELD_MAP_DATA_WHERE_CLIENT_ID);
            preparedStatement.setInt(1, client_id);

            mappingSet = preparedStatement.executeQuery();
           
            
            while (mappingSet.next()) {
                for (int i = 0; i < paramFieldsList.size(); i++) {
                    if (mappingSet.getString("client_field").equals(paramFieldsList.get(i))) {
                        System.out.println("true");
                        wasFound[i] = true;
                    }

                }
            }

            if (allFieldsMatch(wasFound)) {
                System.out.println("A match was found.");
                
                return true;
            }

        }

        System.out.println("No matches found.");
        return false;
         
    }
    
    /**
     * 
     * @param array
     * @return 
     */
    public static boolean allFieldsMatch(boolean[] array) {
        for (boolean b : array) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


 
    
    /**
     * Closes Result Sets, Statements and the connection to the Database.
     */
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            
            if (mappingSet != null) {
                mappingSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts client-specific fields into a List for processing purposes.
     * @param client
     * @return
     * @throws SQLException 
     */
    private Map getFieldsFromSpecificClient(int client) throws SQLException {
        
        Map fields = new LinkedHashMap<String,String>();
        
        preparedStatement = connect.prepareStatement
                                (SELECT_ALL_MAP_DATA_WHERE_CLIENTID);
        preparedStatement.setInt(1, client);

        fieldsSet = preparedStatement.executeQuery();
        
        while (fieldsSet.next()) {
            String cli_field = fieldsSet.getString("client_field");
            String olr_field = fieldsSet.getString("olr_field");
            fields.put(cli_field, olr_field);
        }
 
        System.out.println(fields);
        
        return fields;
    }
    
    /**
     * Gets total number of clients.
     * 
     * @return numberOfClients
     * @throws SQLException 
     */
    private int getNumberOfClients() throws SQLException {
        
        
        preparedStatement = connect.prepareStatement
                                (NUMBER_OF_CLIENTS);
        
        numberOfClientsSet = preparedStatement.executeQuery();
        
        while (numberOfClientsSet.next()) 
        {
            numberOfClients = numberOfClientsSet.getInt("id");
        } 
        
        
        System.out.println(numberOfClients);
        
        
        return numberOfClients;
    }

}