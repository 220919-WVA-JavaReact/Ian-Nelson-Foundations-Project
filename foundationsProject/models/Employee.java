package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class Employee {
    // MAKE SURE THESE MATCH THE DATABASE BEFORE IMPLEMENTING
    // isManager? i don't think manger needs to be implemented yet but i'll add it for the future
    // and auto set it to false

    private int userID;
    private String username;
    private String password;

    private String userRole;

    public Employee(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public Employee() {
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int userID, String username, String password, String userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return userID == employee.userID && Objects.equals(username, employee.username) && Objects.equals(password, employee.password) && Objects.equals(userRole, employee.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, password, userRole);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}