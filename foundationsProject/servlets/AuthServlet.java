package com.revature.strings.foundationsProject.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/authentication")
public class AuthServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        //test
        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - AuthServlet received a POST request at " + LocalDateTime.now());

        Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
        //this should grab the input from our postman and put it into our employee java object of Employee class
        System.out.println(employee);

    }

}
