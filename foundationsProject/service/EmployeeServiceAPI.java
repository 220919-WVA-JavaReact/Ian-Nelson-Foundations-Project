package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.EmployeeDAO;
import com.revature.strings.foundationsProject.dao.EmployeeDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;

public class EmployeeServiceAPI {
    EmployeeDAO ed = new EmployeeDAOImplSQL();


    public String register(String username, String password) {
        Employee employee = new Employee();
        employee = ed.createEmployee(username, password);
        if (employee.getUserID() != 0) {
            System.out.println("Account successfully registered");
            return employee.toString();
        } else {
            return "username";
        }

    }

    public Employee login(String username, String password) {
        Employee employ = new Employee();
        employ = ed.getByUsername(username);
        if (employ.getPassword().equals(password)) {
            System.out.println("You have been logged in!");
            return employ;
        } else {
            System.out.println("Password does not match");
            return null;
        }

    }

    public boolean changeEmployeeRole(Employee employee, String role){
        if (role == "Manager") {
            boolean updatedRole = ed.alterRole(employee, role, "Employee");
            if (updatedRole) {
                return true;
            } else {
                return false;
            }
        } else {
            boolean updatedRole = ed.alterRole(employee, role, "Manager");
            if (updatedRole) {
                return true;
            } else {
                return false;
            }
        }


    }


    public boolean changeEmployeePassword(int userID, String newPass1) {
        boolean updatedPassword = ed.changePass(userID, newPass1);
        if (updatedPassword) {
            return true;
        } else {
            return false;
        }
    }
}

