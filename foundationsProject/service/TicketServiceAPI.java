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


    public boolean createTicketAPI(String description, String amount, Employee employee) {
        Ticket ticket = null;


        if (!description.equals("") && !amount.equals("0.0")) {
            ticket = new Ticket(description, Float.parseFloat(amount), employee.getUserID());
            boolean successful = td.createTicket(ticket, employee);
            return successful;
        }
        return false;
    }


    public List<Ticket> viewPendingTickets(){
        List<Ticket> tickets = td.getAllPendingTicket();
        return tickets;
    }

    public List<Ticket> viewAllTickets(Employee employee){
        List<Ticket> tickets = td.getAllTicket(employee);

        return tickets;
    }

    public boolean updateTicket(int ticketId, String status){
        //we need to pass in a ticket object and String status
        Ticket ticket = new Ticket(ticketId);
        boolean updateStatus = td.approveTicket(ticket, status);

        if (updateStatus) {
            return true;
        }
        return false;
    }

}
