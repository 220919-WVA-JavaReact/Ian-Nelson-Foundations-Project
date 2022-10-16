package com.revature.strings.foundationsProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TicketServiceAPI {
    TicketDAO td = new TicketDAOImplSQL();



    //can i check to see if a session is null in a single method before all methods in this class/servlet?


    public boolean createTicketAPI(String description, float amount, Employee employee) {
        Ticket ticket = null;
        ticket = new Ticket(description, amount, employee.getUserID());

        boolean successful = td.createTicket(ticket, employee);
        return successful;

    }


    public List<Ticket> viewPendingTickets(){
        List<Ticket> tickets = td.getAllPendingTicket();
        return tickets;
    }

    public List<Ticket> viewAllTickets(Employee employee){
        List<Ticket> tickets = td.getAllTicket(employee);

        return tickets;
    }

    public boolean approveTicket(){
        boolean success = td.approveTicket(),
    }

}
