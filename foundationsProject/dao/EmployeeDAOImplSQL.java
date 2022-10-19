package com.revature.strings.foundationsProject.dao;

import com.revature.strings.foundationsProject.models.Employee;
import com.revature.strings.foundationsProject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImplSQL implements EmployeeDAO{
    @Override
    public Employee getByUsername(String username) {
        Employee employee = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int receivedId = rs.getInt("user_id");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                String receivedRole = rs.getString("user_role");

                employee = new Employee(receivedId, receivedUsername, receivedPassword, receivedRole);

            }

        } catch (SQLException e) {
            System.out.println("Something went wrong in employeeDAOsql");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee createEmployee(String username, String password) {

        Employee employee = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO users (username, password) VALUES (?,?) RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int receivedId = rs.getInt("user_id");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                String receivedRole = rs.getString("user_role");

                employee = new Employee(receivedId, receivedUsername, receivedPassword, receivedRole);
            }

        } catch (SQLException e) {
            System.out.println("Unable to register user, credentials taken");
        }

        return employee;
    }

    @Override
    public Employee createEmployeeManager(String username, String password, String userRole) {
        Employee employee = new Employee();

        //finish this after employee service, with manager values
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO users (username, password, user_role) VALUES (?,?,?) RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, userRole);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int receivedId = rs.getInt("user_id");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                String receivedManager = rs.getString("user_role");


                employee = new Employee(receivedId, receivedUsername, receivedPassword);
            }

        } catch (SQLException e) {
            System.out.println("Unable to register user, credentials taken");
        }

        return employee;
    }

    @Override
    public boolean updateRole(Employee employee, String answer) {
        return false;
    }

    @Override
    public boolean promoteRole(Employee employee, String role) {
        return false;
    }

    @Override
    public boolean promoteRole(Employee employee, String newRole, String currentRole) {
        return false;
    }

    @Override
    public boolean alterRole(Employee employee, String newRole, String currentRole) {



        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET user_role = ? WHERE user_id = ? AND user_role = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newRole);
            ps.setInt(2, employee.getUserID());
            ps.setString(3, currentRole);

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


}
