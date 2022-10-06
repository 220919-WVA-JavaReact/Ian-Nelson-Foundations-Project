package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class Ticket {
    //The reimbursement ticket will include a ticket id, the user id who made it, the amount
    // The description, and the status
    private int ticketId;
    private String status;
    private String description;
    private double amount;
    private Employee user;


    //going to make a couple methods, getters/setters, tostring/equals/hashcode override


    public Ticket(int ticketId, String status, String description, double amount, Employee user) {
        this.ticketId = ticketId;
        this.status = status;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public Ticket(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Double.compare(ticket.amount, amount) == 0 && status.equals(ticket.status) && description.equals(ticket.description) && user.equals(ticket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, status, description, amount, user);
    }
}