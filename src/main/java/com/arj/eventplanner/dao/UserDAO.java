package com.arj.eventplanner.dao;

import com.arj.eventplanner.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll() throws SQLException, ClassNotFoundException;
    int insert(User u) throws SQLException, ClassNotFoundException;
    User getByUserName(String userName) throws SQLException, ClassNotFoundException;
    User getByEmail(String email) throws SQLException, ClassNotFoundException;
    User getById(int id) throws SQLException, ClassNotFoundException;
    User login(String userName, String password) throws SQLException, ClassNotFoundException;
    boolean isUserNameTaken(String userName) throws SQLException, ClassNotFoundException;
    boolean isEmailTaken(String email) throws SQLException, ClassNotFoundException;
}
