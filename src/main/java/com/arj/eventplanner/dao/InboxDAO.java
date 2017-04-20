package com.arj.eventplanner.dao;

import com.arj.eventplanner.entity.Inbox;
import com.arj.eventplanner.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface InboxDAO {

    List<Inbox> getAll() throws ClassNotFoundException, SQLException;

    int insert(Inbox i) throws ClassNotFoundException, SQLException;

    int update(Inbox i) throws ClassNotFoundException, SQLException;

    int deleteMsgById(Inbox i) throws ClassNotFoundException, SQLException;

    Inbox getById(int id) throws ClassNotFoundException, SQLException;

    List<Inbox> getByReceiver(User receiver) throws ClassNotFoundException, SQLException;
}
