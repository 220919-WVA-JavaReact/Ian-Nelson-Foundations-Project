package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Employee;

public interface EmployeeDAO {

    //methods for logging in and for registering

    Employee getByUsername(String username);

    //method for creating account
    //how do we implement for ID and isManager?
    Employee createEmployee(String username, String password);

    Employee createEmployeeManager(String username, String password, String userRole);


}
