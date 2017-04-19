/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.controller;

import com.arj.eventplanner.dao.EventDAO;
import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.dao.impl.EventDAOImpl;
import com.arj.eventplanner.dao.impl.UserDAOImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zeppelin
 */
public class DefaultServlet extends HttpServlet {

    private EventDAO eventDAO;
    private UserDAO userDAO;

    public DefaultServlet() throws SQLException, ClassNotFoundException {
        eventDAO = new EventDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("allevents", eventDAO.getAll());
//            request.setAttribute("users", userDAO.getAll());
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DefaultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
