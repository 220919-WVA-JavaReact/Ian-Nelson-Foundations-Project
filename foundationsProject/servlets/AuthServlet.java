package com.revature.strings.foundationsProject.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.service.EmployeeServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/authentication")
public class AuthServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();
    EmployeeServiceAPI esa = new EmployeeServiceAPI();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TEST
        resp.setStatus(204);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //REGISTER&LOGIN
        System.out.println("[LOG] - AuthServlet received a POST request at " + LocalDateTime.now());

        if (req.getParameter("action").equals("register")) {
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
            String payload = esa.register(employee.getUsername(), employee.getPassword());

            if (payload.equals("username")) {
                resp.setStatus(400);
                resp.getWriter().write("Username taken");
            } else {
                //201 created, created acct in db
                resp.setStatus(200);
                resp.getWriter().write("Successfully registered: ");
                resp.getWriter().write(payload);
            }

        } else if (req.getParameter("action").equals("login")) {
            HttpSession session;
            Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
            Employee payload = esa.login(employee.getUsername(), employee.getPassword());

            if (payload == null) {
                resp.setStatus(400);
                resp.getWriter().write("Incorrect Credentials");
            } else {
                session = req.getSession();
                session.setAttribute("auth-user", payload);
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(payload));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
            resp.setStatus(200);
            resp.getWriter().write("session ended");
        }
    }
}
