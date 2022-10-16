package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.EmployeeDAO;
import com.revature.strings.foundationsProject.dao.EmployeeDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;

public class EmployeeServiceAPI {
    EmployeeDAO ed = new EmployeeDAOImplSQL();


    public Employee register(String username, String password) {
        Employee employee = new Employee();
        employee = ed.createEmployee(username, password);
        if (employee.getUserID() != 0) {
            System.out.println("Account successfully registered");
            return employee;
        }
        return null;
    }

    public String login(String username, String password) {
        Employee employ = new Employee();
        employ = ed.getByUsername(username);
        if (employ.getUserID() == 0) {
            return "username";
        } else {
            if (employ.getPassword().equals(password)) {
                System.out.println("You have been logged in!");
                return employ.toString();
            } else {
                System.out.println("Password does not match");
                return "password";
            }

        }
    }

}