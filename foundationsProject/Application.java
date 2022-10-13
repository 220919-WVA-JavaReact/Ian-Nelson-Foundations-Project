package com.revature.strings.foundationsProject;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.service.EmployeeService;
import com.revature.strings.foundationsProject.service.TicketService;

import java.util.Scanner;
public class Application {

    static Scanner sc = new Scanner(System.in);
    static EmployeeService es = new EmployeeService();
    static TicketService ts = new TicketService();

    public static void main(String[] args) {

        Employee loggedInUser = null;
        boolean running = true;

        while(running) {
            if (loggedInUser == null) {
                System.out.println("-------------------------------------------");
                System.out.println("Enter 1 to Log in, 2 to Register, 3 to Register as Manager, 4 to Exit");
                System.out.println("-------------------------------------------");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        loggedInUser = es.login();
                        break;
                    case "2":
                        es.register();
                        break;
                    case "3":
                        es.registerManager();
                        break;
                    case "4":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }

            } else if (loggedInUser.getUserRole().equals("Manager")) {
                //Process tickets (approve or deny, cannot change status after being approved or denied)
                //View a list of pending tickets
                //Tickets will be removed from list once processed
                System.out.println("To View Pending Tickets enter 1, Enter 2 to Approve or Deny Tickets, or enter 3 to Quit.");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ts.viewPendingTickets();
                        break;
                    case "2":
                        ts.updateTicket();
                        break;
                    case "3":
                        running = false;
                        break;
                }
                break;



            } else {
                System.out.println("To submit a Reimbursement Ticket enter 1, enter 2 to view your Tickets, or enter 3 to Quit.");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ts.createTicket(loggedInUser);
                        break;
                    case "2":
                        ts.getTicketById(loggedInUser);
                        break;
                    case "3":
                        // to log out, loggedInUser=null
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }


        }
    }
}