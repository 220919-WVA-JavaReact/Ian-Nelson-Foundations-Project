package com.revature.strings.foundationsProject.service;

import com.revature.strings.foundationsProject.dao.EmployeeDAO;
import com.revature.strings.foundationsProject.dao.EmployeeDAOImplSQL;
import com.revature.strings.foundationsProject.models.Employee;

public class EmployeeServiceAPI {
    EmployeeDAO ed = new EmployeeDAOImplSQL();


    public Employee Register (String username, String password) {
        Employee employee = new Employee();
        employee = ed.createEmployee(username, password);
        if (employee.getUserID() != 0) {
            System.out.println("Account successfully registered");
            return employee;
        }
        return null;
    }
}
