package com.revature.strings.foundationsProject;
import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.service.EmployeeService;

import java.util.Scanner;
public class Application {

    public static EmployeeService es = new EmployeeService();

    public static void main(String[] args) {

        //create scanner object
        Scanner sc = new Scanner(System.in);

        //Prompt user to log in, Register, or Exit
        System.out.println("-------------------------------------------");
        System.out.println("Enter 1 to Log in, 2 to Register, 3 to Exit");
        System.out.println("-------------------------------------------");
        String choice = sc.nextLine();

        //Storing an employee variable, so we can log in or register
        Employee loggedInEmployee = null;

        if (choice.equals("1")){

            loggedInEmployee = es.login();

        } else if (choice.equals("2")){

            //calling our registration method from EmployeeService
            loggedInEmployee = es.register();

            //Here is where I would pass the username/pass to the database

        } else if (choice.equals("3")){

            System.exit(0);

        }
    System.exit(0);

    }
}