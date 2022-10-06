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
                System.out.println("Enter 1 to Log in, 2 to Register, 3 to Exit");
                System.out.println("-------------------------------------------");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        es.login();
                        break;
                    case "2":
                        es.register();
                        break;
                    case "3":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }

            } else {
                System.out.println("To submit a Reimbursement Ticket enter 1, or press 2 to Quit");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ts.createTicket(loggedInUser);
                        break;
                    case "2":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }


        }
    }
}