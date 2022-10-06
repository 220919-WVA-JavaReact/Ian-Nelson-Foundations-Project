package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class Employee {
    // MAKE SURE THESE MATCH THE DATABASE BEFORE IMPLEMENTING
    // isManager? i don't think manger needs to be implemented yet but i'll add it for the future
    // and auto set it to false

    private int userID;
    private String username;
    private String password;

    public Employee(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "Employee{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return userID == employee.userID && username.equals(employee.username) && password.equals(employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, password);
    }
}