/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.dao.impl;

import com.arj.eventplanner.dao.EventDAO;
import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.entity.Event;
import com.arj.eventplanner.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class EventDAOImpl implements EventDAO {

    private Connection conn;

    public EventDAOImpl() throws SQLException, ClassNotFoundException {
        conn = DBConnection.conn();
    }

    @Override
    public List<Event> getAll() throws ClassNotFoundException, SQLException {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT * from tbl_events";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            UserDAO userDAO = new UserDAOImpl();
            Event e = new Event();
            e.setId(rs.getInt("id"));
            e.setTitle(rs.getString("title"));
            e.setDescription(rs.getString("description"));
            e.setOrganizer(userDAO.getByUserName(rs.getString("organizer")));
            e.setVenue(rs.getString("venue"));
            e.setStartDate(rs.getDate("startdate"));
            e.setEndDate(rs.getDate("enddate"));
            e.setInviteStatus(rs.getBoolean("invitestatus"));
            eventList.add(e);
        }
        return eventList;
    }

    @Override
    public int insert(Event e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_events(title,description,organizer,venue,startdate,enddate,invitestatus) "
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, e.getTitle());
        stmt.setString(2, e.getDescription());
        stmt.setString(3, e.getOrganizer().getUserName());
        stmt.setString(4, e.getVenue());
        stmt.setDate(5, e.getStartDate());
        stmt.setDate(6, e.getEndDate());
        stmt.setBoolean(7, e.isInviteStatus());
        int result = stmt.executeUpdate();
        return result;
    }

    @Override
    public Event getById(int id) throws ClassNotFoundException, SQLException {
        for (Event e : getAll()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM tbl_events where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }

    @Override
    public int updateById(Event e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE tbl_events SET title=?,description=?,organizer=?,venue=?,"
                + "startdate=?,enddate=?,invitestatus=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, e.getTitle());
        stmt.setString(2, e.getDescription());
        stmt.setString(3, e.getOrganizer().getUserName());
        stmt.setString(4, e.getVenue());
        stmt.setDate(5, e.getStartDate());
        stmt.setDate(6, e.getEndDate());
        stmt.setBoolean(7, e.isInviteStatus());
        stmt.setInt(8, e.getId());
        int result = stmt.executeUpdate();
        return result;
    }

    @Override
    public int deleteById(Event e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM tbl_events WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, e.getId());
        int result = stmt.executeUpdate();
        return result;
    }

}
