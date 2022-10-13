package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketService {
    //Menu logic for ticket interactions and DAO interactions

    Scanner sc = new Scanner(System.in);

    //making an instance of the dao at the class level so I can call upon it
    TicketDAO td = new TicketDAOImplSQL();

    private static final DecimalFormat df = new DecimalFormat ("0.00");

    public void createTicket(Employee employee){
        Ticket ticket = null;
        System.out.println("Please enter a short description of your claim");
        String description = sc.nextLine();
        System.out.println("Please enter the amount of your claim in '00.00' format");
        float amount = sc.nextFloat();

        ticket = new Ticket(description, amount, employee.getUserID());

        boolean successful = td.createTicket(ticket, employee);

        if (successful){
            System.out.println("Successfully created a ticket!");
        }   else{
            System.out.println("Something went wrong in ticketservice");
        }

    }
    public void getTicketById(Employee employee){
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) td.getAllTicket(employee);
        System.out.println("You have " + tickets.size() + " tickets.");

        for (Ticket ticket : tickets){
            System.out.println("----------------------");
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Description: " + ticket.getDescription());
            System.out.println("Amount: " + df.format((ticket.getAmount())));
            System.out.println("Status: " + ticket.getStatus());
        }
        System.out.println("------------------------");
    }



    public void viewPendingTickets(){
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) td.getAllPendingTicket();
        System.out.println("There are " + tickets.size() + "PENDING tickets.");

        for (Ticket ticket : tickets){
            System.out.println("----------------------");
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Description: " + ticket.getDescription());
            System.out.println("Amount: " + df.format((ticket.getAmount())));
            System.out.println("Status: " + ticket.getStatus());
        }
        System.out.println("There are " + tickets.size() + "PENDING tickets.");
        System.out.println("------------------------");
    }

    public void updateTicket(){
        //we need to get a ticket by ticket id
        System.out.println("Please enter the ticket ID");
        int ticketID = sc.nextInt();

        Ticket ticket = td.getByTicketID(ticketID);

        //list ticket info here, see above

        System.out.println("Press 1 to Approve, 2 to Deny, 3 to Exit");
        sc.nextLine();
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                td.approveTicket(ticket);
                //UPDATE tickets SET status = Approved WHERE tick_id = ?
                break;
            case "2":
                //deny ticket
                break;
            case "3":
                break;

        }

    }

   // public void viewAllTickets(Employee employee){


}
