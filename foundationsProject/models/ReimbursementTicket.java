package com.revature.strings.foundationsProject.models;

import java.util.Objects;

public class ReimbursementTicket {
    //The reimbursement ticket will include a ticket id, the user id who made it, the amount
    // The description, and the status
    private int ticketId;
    private int userId;
    private double amount;
    private String description;
    private boolean status;

    //going to make a couple methods, getters/setters, tostring/equals/hashcode override


    public ReimbursementTicket(int ticketId, int userId, double amount, String description, boolean status) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public ReimbursementTicket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimbursementTicket{" +
                "ticketId=" + ticketId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementTicket that = (ReimbursementTicket) o;
        return ticketId == that.ticketId && userId == that.userId && Double.compare(that.amount, amount) == 0 && status == that.status && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, userId, amount, description, status);
    }

}
