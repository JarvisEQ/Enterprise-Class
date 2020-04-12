package com;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MakeQuery extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                            throws ServletException, java.io.IOException {
        // parse that JSON package
        Gson gson = new Gson();
        Query query;


        response.setContentType("text/json");

        query = gson.fromJson()

        response.getWriter().println("Servlet wrote this! (Test.java)");

    }


    // We will not support get for making Queries, return Forbidden if someone tries to.
    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
                            throws javax.servlet.ServletException, java.io.IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
