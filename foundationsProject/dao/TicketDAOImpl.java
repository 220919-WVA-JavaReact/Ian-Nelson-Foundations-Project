package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Ticket;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    //for use when we are able to connect to our database and actually save and access tickets

    @Override
    public Ticket createTicket(double amount, String description) {
        return null;
    }

    @Override
    public List<Ticket> getAllTicket() {
        return null;
    }

    @Override
    public List<Ticket> getTicketsByEmployeeId(int id) {
        return null;
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return false;
    }
}
