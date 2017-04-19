package com.arj.eventplanner.dao.impl;

import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.entity.User;
import com.arj.eventplanner.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection conn;

    public UserDAOImpl() throws SQLException, ClassNotFoundException {
        conn = DBConnection.conn();
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tbl_users";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUserName(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setSex(rs.getString("sex"));
            u.setStatus(rs.getBoolean("status"));
            users.add(u);
        }
        conn.close();
        return users;
    }

    @Override
    public int insert(User u) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tbl_users(username,password,email,sex,status) "
                + "VALUES (?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, u.getUserName());
        stmt.setString(2, u.getPassword());
        stmt.setString(3, u.getEmail());
        stmt.setString(4, u.getSex());
        stmt.setBoolean(5, u.isStatus());
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }

    @Override
    public User login(String userName, String password) throws SQLException, ClassNotFoundException {
        for (User u : getAll()) {
            if (u.getUserName().equalsIgnoreCase(userName) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User getByUserName(String userName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tbl_users WHERE username=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUserName(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setSex(rs.getString("sex"));
            u.setStatus(rs.getBoolean("status"));
            return u;
        }
        conn.close();
        return null;
    }

    @Override
    public User getByEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tbl_users WHERE email=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUserName(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setSex(rs.getString("sex"));
            u.setStatus(rs.getBoolean("status"));
            return u;
        }
        conn.close();
        return null;
    }

    @Override
    public boolean isUserNameTaken(String userName) throws SQLException, ClassNotFoundException {
        for (User u : getAll()) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmailTaken(String email) throws SQLException, ClassNotFoundException {
        for (User u : getAll()) {
            if (u.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getById(int id) throws SQLException, ClassNotFoundException {
        for(User u: getAll()){
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }

}