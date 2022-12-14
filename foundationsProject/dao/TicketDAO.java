package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface TicketDAO {

    //creating a create ticket method

    boolean createTicket(Ticket ticket, Employee employee);

    //not sure what this is for, but we made it during lecture, so I'm going to hold onto it
    ArrayList<Ticket> getAllTicket();

    //get ticket by employee id, useful for listing all tickets an employee has submitted
    List<Ticket> getTicketsByEmployeeId(int id);

    //using a boolean to be able to send in a ticket and have it be able to update
    boolean approveTicket(Ticket ticket, String answer);

    void denyTicket(Ticket ticket);



    ArrayList<Ticket> getAllPendingTicket();

    List<Ticket> getAllTicket(Employee employee);

    Ticket getByTicketID(int ticketID);

    ArrayList<Ticket> getAllTicket(Employee employee, String getStatus);
}
