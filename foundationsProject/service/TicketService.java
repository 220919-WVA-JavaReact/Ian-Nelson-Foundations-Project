package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.TicketDAO;
import com.revature.strings.foundationsProject.dao.TicketDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;

import java.util.Scanner;

public class TicketService {
    //Menu logic for ticket interactions and DAO interactions

    Scanner sc = new Scanner(System.in);

    //making an instance of the dao at the class level so I can call upon it
    TicketDAO td = new TicketDAOImplSQL();

    public void createTicket(Employee employee){
        System.out.println("Please enter a short description of your claim");
        String description = sc.nextLine();
        System.out.println("Please enter the amount of your claim in '00.00' format");
        double amount = sc.nextDouble();

        Ticket ticket = new Ticket(description, amount, employee);

        boolean successful = td.createTicket(ticket);

        if (successful){
            System.out.println("Successfully created a ticket!");
        }   else{
            System.out.println("Something went wrong!");
        }



    }

}
