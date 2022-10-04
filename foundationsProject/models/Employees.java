package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class Employees {
    // MAKE SURE THESE MATCH THE DATABASE BEFORE IMPLEMENTING
    // isManager? i don't think manger needs to be implemented yet but i'll add it for the future
    // and auto set it to false

    private int employeeID;
    private String username;
    private String password;
    private boolean isManager;

    // alt+insert to generate
    // when sure of our fields, generate 3 constructs, one with all, one without ID, one with none
    // then generate getters and setters for all
    // then equals() and hashcode


    public Employees(int employeeID, String username, String password, boolean isManager) {
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public Employees(String username, String password, boolean isManager) {
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeID=" + employeeID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return employeeID == employees.employeeID && isManager == employees.isManager && username.equals(employees.username) && password.equals(employees.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, username, password, isManager);
    }


}
