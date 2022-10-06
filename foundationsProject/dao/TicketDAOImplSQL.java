package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Ticket;
import com.revature.strings.foundationsProject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketDAOImplSQL implements TicketDAO{

    @Override
    public boolean createTicket(Ticket ticket) {

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ticket VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ticket.getStatus());
            stmt.setString(2, ticket.getDescription());
            stmt.setDouble(3, ticket.getAmount());
            //need clarification about what is happening below
            stmt.setInt(4, ticket.getUser().getUserID());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }

        return false;
    }







    @Override
    public List<Ticket> getAllTicket() {
        return null;
    }

    @Override
    public List<Ticket> getTicketsByEmployeeId(int id) {
        return null;
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return false;
    }
}
