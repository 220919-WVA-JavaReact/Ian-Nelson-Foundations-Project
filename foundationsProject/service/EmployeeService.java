package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.EmployeeDAO;
import com.revature.strings.foundationsProject.dao.EmployeeDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;

import java.util.Scanner;

public class EmployeeService {

    //ability to call to the database and the DAO methods I made
    EmployeeDAO ed = new EmployeeDAOImplSQL();

    Scanner sc = new Scanner(System.in);

    //Going to have our log in menu logic here
    public Employee login() {

        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();


        //this method will end up finding the username in our database
        Employee employ = ed.getByUsername(username);

        //next we will check to see if the password matches
        if (employ.getUserID() == 0) {
            System.out.println("No employee associated with that username");
        } else {
            if (employ.getPassword().equals(password)){
                System.out.println("You have been logged in!");
                return employ;


            }
            //Going to return employ, so we have the ability to store in the main class
        }
        return null;
    }
    //Now we are going to create a method to register
    public Employee register(){
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        //calling the dao method to create an employee
        Employee employee = ed.createEmployee(username,password);
        if (employee.getUserID() != 0) {
            System.out.println("Account successfully registered");
            return employee;
        }
        return null;

    }



}
