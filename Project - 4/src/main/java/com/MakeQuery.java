package com;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Vector;


public class MakeQuery extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                            throws ServletException, java.io.IOException {
        // parse that JSON package
        Gson gson = new Gson();
        Query query;

        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }

            Query obj = gson.fromJson(jb.toString(), Query.class);

            System.out.println(obj.getQuery());

            Vector<Vector<Object>> data = obj.doQuery();
            String reponseJSON = gson.toJson(data);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(reponseJSON);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //response.getWriter().println("Servlet wrote this! (Test.java)");

    }


    // We will not support get for making Queries, return Forbidden if someone tries to.
    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
                            throws javax.servlet.ServletException, java.io.IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
