package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class Query{

    private String query;


    public Query(String query){
        this.query = query;
    }

    public Table doQuery(){

        Table table;
        String password = "password";
        String username = "username";
        Connection conn;

        // try to connect
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project3",
                    username,
                    password);

            // debug if connection fails
        } catch (Exception ex){
            return null;
        }

        return table;
    }

    

    public String getQuery() {
        return query;
    }
}

