/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.controller;

import com.arj.eventplanner.dao.EventDAO;
import com.arj.eventplanner.dao.InboxDAO;
//import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.dao.impl.EventDAOImpl;
import com.arj.eventplanner.dao.impl.InboxDAOImpl;
//import com.arj.eventplanner.dao.impl.UserDAOImpl;
import com.arj.eventplanner.entity.User;
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
//    private UserDAO userDAO;
    private InboxDAO inboxDAO;

    public DefaultServlet() throws SQLException, ClassNotFoundException {
        eventDAO = new EventDAOImpl();
//        userDAO = new UserDAOImpl();
        inboxDAO = new InboxDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User currentUser = (User) request.getSession().getAttribute("loggedIn");
            if (currentUser != null) {
                request.setAttribute("allevents", eventDAO.getAll());
                int totalUnreadMsgs = inboxDAO.getUnreadMsgNo(currentUser);
                request. setAttribute("unreadMsgsNumber", totalUnreadMsgs);
                request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DefaultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
