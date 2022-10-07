package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;
import com.revature.strings.foundationsProject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImplSQL implements TicketDAO{

    @Override
    public boolean createTicket(Ticket ticket, Employee employee) {

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO ticket (description, amount, user_id) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ticket.getDescription());
            stmt.setFloat(2, (float) ticket.getAmount());
            //need clarification about what is happening below
            stmt.setInt(3, employee.getUserID());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong in ticketDAOSQL!");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Ticket> getAllTicket() {
        return null;
    }


    @Override
    public ArrayList<Ticket> getAllTicket(Employee employee) {
        List<Ticket> ticketList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ticket WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getUserID());
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
               while (rs.next()) {
                   int ticketID = rs.getInt("ticket_id");
                   String description = rs.getString("description");
                   float amount = rs.getFloat("amount");
                   String status = rs.getString("status");
                   ticketList.add(new Ticket(ticketID, description, status, amount));
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (ArrayList<Ticket>) ticketList;
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
