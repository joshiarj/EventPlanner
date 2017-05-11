/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.dao;

import com.arj.eventplanner.entity.Event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public interface EventDAO {
    List<Event> getAll() throws ClassNotFoundException, SQLException;
    int insert(Event e) throws ClassNotFoundException, SQLException;
    Event getById(int id) throws ClassNotFoundException, SQLException;
    int delete(int id) throws ClassNotFoundException, SQLException;
    int updateById(Event e) throws ClassNotFoundException, SQLException;
    int deleteById(Event e) throws ClassNotFoundException, SQLException;    
}
