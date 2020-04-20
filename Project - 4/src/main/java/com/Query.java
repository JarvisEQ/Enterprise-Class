package com;

import java.sql.*;
import java.util.Vector;

public class Query{

    private String query;


    public Query(String query){
        this.query = query;
    }

    public Vector<Vector<Object>> doQuery(){

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        String password = "password";
        String username = "client";
        Connection conn;

        // try to connect
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // jdbc is stupid, ignore the URL
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project4?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    username,
                    password);

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(this.query);

            ResultSetMetaData metaData = rs.getMetaData();

            // this gets the column names
            Vector<Object> columnNames = new Vector<Object>();
            int count = metaData.getColumnCount();

            for (int i = 1; i <= count; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            data.add(columnNames);

            // this gets the data

            while (rs.next()) {
                Vector<Object> victor = new Vector<Object>();

                for (int i = 1; i <= count; i++) {
                    victor.add(rs.getObject(i));
                }
                data.add(victor);
            }

            return data;

        // debug if anything fails
        } catch (Exception ex){
            ex.printStackTrace();

            // 500 represents a general error
            data.add(new Vector<Object>(Integer.parseInt("500")));
            return data;
        }

    }



    public String getQuery() {
        return query;
    }
}

