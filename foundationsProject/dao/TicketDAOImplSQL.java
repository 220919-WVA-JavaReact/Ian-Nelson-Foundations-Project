package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.models.Ticket;
import com.revature.strings.foundationsProject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImplSQL implements TicketDAO {

    @Override
    public boolean createTicket(Ticket ticket, Employee employee) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO ticket (description, amount, user_id) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ticket.getDescription());
            stmt.setFloat(2, (float) ticket.getAmount());
            //need clarification about what is happening below
            stmt.setInt(3, employee.getUserID());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1) {
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
    public ArrayList<Ticket> getAllPendingTicket() {
        //get all pending tickets
        List<Ticket> ticketList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Pending");
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
            //add sout and make desc & amount NOT NULL
            e.printStackTrace();
        }

        return (ArrayList<Ticket>) ticketList;

    }


    @Override
    public ArrayList<Ticket> getAllTicket(Employee employee) {
        List<Ticket> ticketList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
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
                    int userID = rs.getInt("user_id");
                    ticketList.add(new Ticket(ticketID, description, status, amount, userID));
                }
            }
        } catch (Exception e) {
            //add sout and make desc & amount NOT NULL
            e.printStackTrace();
        }

        return (ArrayList<Ticket>) ticketList;
    }

    public Ticket getByTicketID(int id) {
        Ticket ticket = new Ticket();
        System.out.println(id);

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                    rs.next();
                    int ticketID = rs.getInt("ticket_id");
                    String description = rs.getString("description");
                    float amount = rs.getFloat("amount");
                    String status = rs.getString("status");

                    ticket = new Ticket(ticketID, description, status, amount);

            } else {
                System.out.println("Ticket not being passed through getbyticketid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ticket retrieved");
        //START DEBUGGING PROCESS HERE
        return ticket;
    }


    @Override
    public List<Ticket> getTicketsByEmployeeId(int id) {
        return null;
    }


    @Override
    public boolean approveTicket(Ticket ticket, String answer) {



        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE ticket SET status = ? WHERE ticket_id = ? AND status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, answer);
            ps.setInt(2, ticket.getTicketId());
            ps.setString(3, "Pending");

            int result = ps.executeUpdate();

            if (result == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong in ticketDAOSQL!");
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public void denyTicket(Ticket ticket) {

    }

    @Override
    public ArrayList<Ticket> getAllTicket(Employee employee, String getStatus) {
        List<Ticket> ticketList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE user_id = ? AND status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getUserID());
            ps.setString(2, getStatus);
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int ticketID = rs.getInt("ticket_id");
                    String description = rs.getString("description");
                    float amount = rs.getFloat("amount");
                    String status = rs.getString("status");
                    int userID = rs.getInt("user_id");
                    ticketList.add(new Ticket(ticketID, description, status, amount, userID));
                }
            }
        } catch (Exception e) {
            //add sout and make desc & amount NOT NULL
            e.printStackTrace();
        }

        return (ArrayList<Ticket>) ticketList;
    }
}
