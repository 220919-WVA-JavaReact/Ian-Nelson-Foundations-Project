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

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int receivedId = rs.getInt("user_id");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");

                employee = new Employee(receivedId, receivedUsername, receivedPassword);
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong.");
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

                employee = new Employee(receivedId, receivedUsername, receivedPassword);
            }

        } catch (SQLException e) {
            System.out.println("Unable to register user");
        }

        return employee;
    }

}
