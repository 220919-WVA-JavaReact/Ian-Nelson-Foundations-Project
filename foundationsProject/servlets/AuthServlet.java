package com.revature.strings.foundationsProject.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.service.EmployeeServiceAPI;

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
    EmployeeServiceAPI esa = new EmployeeServiceAPI();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        //test
        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - AuthServlet received a POST request at " + LocalDateTime.now());

        if (req.getParameter("action").equals("register")) {
            //this should grab the input from our postman and put it into our employee java object of Employee class
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
            Employee employ = esa.register(employee.getUsername(), employee.getPassword());
            String respPayload = mapper.writeValueAsString(employ);
            resp.getWriter().write(respPayload);

        } else if (req.getParameter("action").equals("login")) {
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);

            String payload = esa.login(employee.getUsername(), employee.getPassword());

            if (payload.equals("username")) {
                resp.setStatus(400);
                resp.getWriter().write("Username not found.");
            } else if (payload.equals("password")) {
                resp.setStatus(400);
                resp.getWriter().write("Password does not match.");
            } else {
                //Employee employ = mapper.readValue(payload, Employee.class);
                //todo session work
                resp.setStatus(200);
                resp.getWriter().write(payload);
            }



        }

    }

}
