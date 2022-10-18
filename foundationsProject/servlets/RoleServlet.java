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

}
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                //todo pass in the employee's userID we want to update in postman
                if (req.getParameter("action").equals("Promote")) {
                    //WE NEED TO PASS IN THE USER ID, from employee instance
                    //We are passing in manager to update from employee to manager
                    //at some point we will need to make sure they are not already a manager
                //boolean updateSuccess = esa.updateRole(updateEmployee, "Manager");
                }
            }
        }
}
}