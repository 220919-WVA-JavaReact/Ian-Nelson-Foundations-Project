package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class Ticket {
    //The reimbursement ticket will include a ticket id, the user id who made it, the amount
    // The description, and the status
    private int ticketId;
    private String status;
    private String description;
    private float amount;

    private int userID;


    //going to make a couple methods, getters/setters, tostring/equals/hashcode override


    public Ticket(int ticketId, String status, String description, float amount, int userID) {
        this.ticketId = ticketId;
        this.status = status;
        this.description = description;
        this.amount = amount;
        this.userID = userID;
    }

    public Ticket(int ticketId, String description, String status, float amount) {
        this.ticketId = ticketId;
        this.status = status;
        this.description = description;
        this.amount = amount;
    }

    public Ticket(String description, float amount) {
        this.description = description;
        this.amount = amount;
    }

    public Ticket(String description, float amount, int userID) {
        this.description = description;
        this.amount = amount;
        this.userID = userID;
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

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", user=" + userID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Double.compare(ticket.amount, amount) == 0 && userID == ticket.userID && status.equals(ticket.status) && description.equals(ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, status, description, amount, userID);
    }
}