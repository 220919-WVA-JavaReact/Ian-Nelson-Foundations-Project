package com.revature.strings.foundationsProject.servlets;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;
import com.revature.strings.foundationsProject.service.EmployeeServiceAPI;
import com.revature.strings.foundationsProject.service.TicketServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    EmployeeServiceAPI esa = new EmployeeServiceAPI();
    TicketServiceAPI tsa = new TicketServiceAPI();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // GET ALL SESSION USER'S TICKETS
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(401);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 401);
            errorMessage.put("Message", "You must be logged in to view this content.");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        } else {
            Employee loggedInEmploy = (Employee) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("Pending")) {
                List<Ticket> tickets = tsa.viewTickets(loggedInEmploy, "Pending");
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));
            } else if (req.getParameter("action").equals("Approved")) {
                List<Ticket> tickets = tsa.viewTickets(loggedInEmploy, "Approved");
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));
            } else if (req.getParameter("action").equals("Denied")) {
                List<Ticket> tickets = tsa.viewTickets(loggedInEmploy, "Denied");
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));
            } else {
                List<Ticket> tickets = tsa.viewAllTickets(loggedInEmploy);
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SUBMIT TICKET
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(401);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 401);
            errorMessage.put("Message", "You must be logged in to view this content");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        } else {
            Employee loggedInEmploy = (Employee) session.getAttribute("auth-user");

            Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);
            //resp.setStatus(200);
            String description = ticket.getDescription();
            String amount = String.valueOf(ticket.getAmount());

            boolean success = tsa.createTicketAPI(description, amount, loggedInEmploy);

            if (success) {
                resp.setStatus(201);
                resp.getWriter().write("Successfully submitted ticket.");
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Description and Amount must not be left blank.");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UPDATE PW
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(401);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 401);
            errorMessage.put("Message", "You must be logged in to access this page.");
            errorMessage.put("Timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        } else {
            Employee loggedInEmploy = (Employee) session.getAttribute("auth-user");
            resp.getWriter().write(mapper.writeValueAsString(loggedInEmploy));

            HashMap<String, Object> postInput = mapper.readValue(req.getInputStream(), HashMap.class);

            String passwordCheck = (String) postInput.get("current-password");
            String newPass1 = (String) postInput.get("new-password");
            String newPass2 = (String) postInput.get("new-password2");

            if (passwordCheck.equals(loggedInEmploy.getPassword())) {
                if (!passwordCheck.equals(newPass1)) {
                    if (newPass1.equals(newPass2)) {
                        boolean passwordUpdate = esa.changeEmployeePassword(loggedInEmploy.getUserID(), newPass1);
                        if (passwordUpdate) {
                            resp.setStatus(200);
                            resp.getWriter().write("Password updated!");

                            Employee newEmployeeSession = esa.getEmployee(loggedInEmploy.getUsername());
                            HttpSession newSession = req.getSession();
                            newSession.setAttribute("auth-user", newEmployeeSession);
                            resp.getWriter().write(mapper.writeValueAsString(newEmployeeSession));

                        } else {
                            resp.setStatus(202);
                            resp.getWriter().write("Your new password can not be the same as your old password.");
                        }
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("New passwords don't match.");
                    }
                } else {
                    resp.setStatus(400);
                    resp.getWriter().write("New password can not match the old password.");
                }
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Provided password does not match your current password.");
            }
        }
    }
}
