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
            resp.getWriter().write(mapper.writeValueAsString(loggedInEmploy));
            //we now have the sessions info, now to call for ticket
            TicketServiceAPI tsa = new TicketServiceAPI();

            List<Ticket> tickets = tsa.viewAllTickets(loggedInEmploy);

            if (tickets != null) {
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Here we want to be able to submit tickets with our logged-in user session
        resp.setContentType("application/json");
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
            resp.getWriter().write(mapper.writeValueAsString(loggedInEmploy));
            //we want to take the description, the amount, and the auth user id and pass it to create a ticket
            Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);
            resp.setStatus(200);

            String description = ticket.getDescription();
            String amount = String.valueOf(ticket.getAmount());
            resp.getWriter().write(mapper.writeValueAsString(amount));
            boolean success = tsa.createTicketAPI(description, amount, loggedInEmploy);

            if (success) {
                resp.setStatus(200);
                resp.getWriter().write("Successfully submitted ticket.");
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Description and Amount must not be left blank.");
            }


            //resp.getWriter().write(mapper.writeValueAsString(ticket));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Here we want to be able to submit tickets with our logged-in user session
        resp.setContentType("application/json");
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
            resp.getWriter().write(mapper.writeValueAsString(loggedInEmploy));

            HashMap<String, Object> postInput = mapper.readValue(req.getInputStream(), HashMap.class);

            //we are casting the ^ Object as/to a String
            //we could also theoretically just do String, String for this code, but object is typically better
            String passwordCheck = (String) postInput.get("current-password");
            String newPass1 = (String) postInput.get("new-password");
            String newPass2 = (String) postInput.get("new-password2");
            if (passwordCheck.equals(loggedInEmploy.getPassword())) {
                if (newPass1.equals(newPass2)) {
                    boolean passwordUpdate = esa.changeEmployeePassword(loggedInEmploy.getUserID(), newPass1);
                    if (passwordUpdate) {
                        resp.setStatus(200);
                        resp.getWriter().write("Password updated!");
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("Something went wrong");
                    }
                } else {
                    resp.setStatus(400);
                    resp.getWriter().write("New passwords don't match.");
                }
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Provided password does not match your current password.");
            }
            // lets try to make a hash map from the json input, currentpw, newpassword, newpassword2


        }
    }
}
