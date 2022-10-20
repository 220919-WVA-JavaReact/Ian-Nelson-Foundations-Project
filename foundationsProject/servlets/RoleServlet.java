package com.revature.strings.foundationsProject.servlets;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;
import com.revature.strings.foundationsProject.service.EmployeeServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //PROMOTE&DEMOTE
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(400);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "No user found with provided credentials");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        } else {
            Employee loggedInEmploy = (Employee) session.getAttribute("auth-user");
            if (loggedInEmploy.getUserRole().equals("Employee")) {
                resp.setStatus(400);
                resp.getWriter().write("Employees may not change others roles.");
        } else {
                Employee updateEmployee = mapper.readValue(req.getInputStream(), Employee.class);
                if (req.getParameter("action").equals("Promote")) {
                    boolean updateSuccess = esa.changeEmployeeRole(updateEmployee, "Manager");
                    if (updateSuccess) {
                        resp.setStatus(200);
                        resp.getWriter().write("Employee promoted to Manager.");
                    } else {
                        //202 accepted but not acted upon
                        resp.setStatus(202);
                        resp.getWriter().write("This user is already a Manager.");
                    }
                } else if (req.getParameter("action").equals("Demote")) {
                    boolean demoteSuccess = esa.changeEmployeeRole(updateEmployee, "Employee");
                    if (demoteSuccess) {
                        resp.setStatus(200);
                        resp.getWriter().write("Manager demoted to Employee");
                    } else {
                        resp.setStatus(202);
                        resp.getWriter().write("This user is not a Manager.");
                    }
                }
            }
        }
}
}
