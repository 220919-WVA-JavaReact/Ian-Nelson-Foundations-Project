package com.revature.strings.foundationsProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpSession;

public class TicketServiceAPI {
    TicketDAO td = new TicketDAOImplSQL();


    //todo get loggedinuser information and pass it to an emlpoyee object?
    //can i check to see if a session is null in a single method before all methods in this class/servlet?


    public boolean createTicketAPI(String description, float amount, Employee employee) {
        Ticket ticket = null;
        ticket = new Ticket(description, amount, employee.getUserID());

        boolean successful = td.createTicket(ticket, employee);
        return successful;

    }



        //Employee loggedInEmployee =
        //ticket = new Ticket(description, amount, employee.getUserID); this will create the ticket after we get the
        //                                                              logged in user
        //then we will send to ticketDAO

    //}

    //public String getTicketsByUser(int id){

   // }

}