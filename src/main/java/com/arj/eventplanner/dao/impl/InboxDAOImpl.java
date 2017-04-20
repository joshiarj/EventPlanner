package com.arj.eventplanner.dao.impl;

import com.arj.eventplanner.dao.InboxDAO;
import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.entity.Inbox;
import com.arj.eventplanner.entity.User;
import com.arj.eventplanner.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InboxDAOImpl implements InboxDAO {

    private Connection conn;

    public InboxDAOImpl() throws ClassNotFoundException, SQLException {
        conn = DBConnection.conn();
    }

    @Override
    public List<Inbox> getAll() throws ClassNotFoundException, SQLException {
        List<Inbox> msgList = new ArrayList<>();
        String sql = "SELECT * FROM tbl_inbox";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        UserDAO userDAO = new UserDAOImpl();
        while (rs.next()) {
            Inbox i = new Inbox();
            i.setId(rs.getInt("id"));
            i.setSender(userDAO.getByUserName(rs.getString("sender")));
            i.setReceiver(userDAO.getByUserName(rs.getString("receiver")));
            try {
                i.setReceivedDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("receiveddate")));
            } catch (ParseException ex) {
                Logger.getLogger(InboxDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            i.setSubject(rs.getString("subject"));
            i.setMessage(rs.getString("message").replace("\n", "<br/>"));
            i.setReadStatus(rs.getBoolean("readstatus"));
            msgList.add(i);
        }
        return msgList;
    }

    @Override
    public int insert(Inbox i) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_inbox(sender,receiver,subject,message,readstatus)"
                + " VALUES(?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, i.getSender().getUserName());
        stmt.setString(2, i.getReceiver().getUserName());
        stmt.setString(3, i.getSubject());
        stmt.setString(4, i.getMessage());
        stmt.setBoolean(5, false);
        return stmt.executeUpdate();
    }

    @Override
    public int deleteMsgById(Inbox i) throws ClassNotFoundException, SQLException {        
        String sql="DELETE FROM tbl_inbox WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, i.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Inbox getById(int id) throws ClassNotFoundException, SQLException {
        for (Inbox i : getAll()) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Inbox> getByReceiver(User receiver) throws ClassNotFoundException, SQLException {
        List<Inbox> receiverMsgs = new ArrayList<>();
        int msgId = 1;
        for (Inbox i : getAll()) {
            if (i.getReceiver() == receiver) {
                i.setId(msgId);
                receiverMsgs.add(i);
                msgId++;
            }
        }
        return receiverMsgs;
    }

    @Override
    public int update(Inbox i) throws ClassNotFoundException, SQLException {
        String sql="UPDATE tbl_inbox SET readstatus=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBoolean(1, i.isReadStatus());
        stmt.setInt(2, i.getId());
        return stmt.executeUpdate();
    }

}
