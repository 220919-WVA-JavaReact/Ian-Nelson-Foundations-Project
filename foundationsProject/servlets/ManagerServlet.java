package com.revature.strings.foundationsProject.servlets;

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

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
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
            //resp.getWriter().write(mapper.writeValueAsString(loggedInEmploy));
            TicketServiceAPI tsa = new TicketServiceAPI();


            List<Ticket> tickets = tsa.viewPendingTickets();

            if (tickets != null) {
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(tickets));
            }

        }

    }
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
            Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);
            if (loggedInEmploy.getUserRole().equals("Employee")) {
                resp.setStatus(400);
                resp.setContentType("application/json");

                HashMap<String, Object> errorMessage = new HashMap<>();

                errorMessage.put("Status code", 400);
                errorMessage.put("Message", "Employees may not Approve or Deny tickets.");
            }
            if (req.getParameter("action").equals("Approve")) {
                //we need to pass in a ticket id
                //todo LOTS OF APPROVE WORK
                boolean ticketApproved = tsa.updateTicket(ticket.getTicketId(), "Approved");
                if (ticketApproved) {
                    resp.setStatus(200);
                    resp.getWriter().write("Successfully approved ticket.");
                } else {
                    resp.setStatus(400);
                    resp.getWriter().write("Can not update completed tickets.");
                }

            } else if (req.getParameter("action").equals("Deny")) {
                boolean ticketDenied = tsa.updateTicket(ticket.getTicketId(), "Denied");
                if (ticketDenied) {
                    resp.setStatus(200);
                    resp.getWriter().write("Successfully denied ticket.");
                } else {
                    resp.setStatus(400);
                    resp.getWriter().write("Can not update completed tickets.");
                }

            }
        }
    }
}
