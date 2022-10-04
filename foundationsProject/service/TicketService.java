package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImpl;

import java.util.Scanner;

public class TicketService {
    //Menu logic for ticket interactions and DAO interactions

    Scanner sc = new Scanner(System.in);

    //making an instance of the dao at the class level so I can call upon it
    TicketDAO td = new TicketDAOImpl();

    public void createTicket(){
        System.out.println("Please enter the amount you want reimbursed");
        double ticketAmount = sc.nextDouble();

        System.out.println("Please enter a description of your claim");
        String description = sc.nextLine();

        //Will need a way to pass the employee id to the ticket
        //Will also automatically want to assign it an unresolved status

        td.createTicket(ticketAmount, description);
    }

}
