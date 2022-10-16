package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImplSQL;
import com.revature.strings.foundationsProject.models.Ticket;

public class TicketServiceAPI {
    TicketDAO td = new TicketDAOImplSQL();

    //todo get loggedinuser information and pass it to an emlpoyee object?

    public String createTicketAPI(String description, float amount){
        Ticket ticket = new Ticket();
        //ticket = new Ticket(description, amount, employee.getUserID); this will create the ticket after we get the
        //                                                              logged in user
        //then we will send to ticketDAO

    }

}
