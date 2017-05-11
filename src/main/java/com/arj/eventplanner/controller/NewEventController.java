/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.controller;

import com.arj.eventplanner.dao.EventDAO;
import com.arj.eventplanner.dao.InboxDAO;
import com.arj.eventplanner.dao.impl.EventDAOImpl;
import com.arj.eventplanner.dao.impl.InboxDAOImpl;
import com.arj.eventplanner.entity.Event;
import com.arj.eventplanner.entity.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "newevent", urlPatterns = {"/newevent/*"})
public class NewEventController extends HttpServlet {

    private InboxDAO inboxDAO;

    public NewEventController() throws SQLException, ClassNotFoundException {
        inboxDAO = new InboxDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("loggedIn");
        if (currentUser != null) {
            try {
                int totalUnreadMsgs;
                totalUnreadMsgs = inboxDAO.getUnreadMsgNo(currentUser);
                request.setAttribute("unreadMsgsNumber", totalUnreadMsgs);
                request.getRequestDispatcher("/WEB-INF/views/newevent.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(NewEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EventDAO eventDAO = new EventDAOImpl();
            Event e = new Event();
            e.setTitle(request.getParameter("title"));
            e.setDescription(request.getParameter("description"));
            e.setOrganizer((User) request.getSession().getAttribute("loggedIn"));
            e.setVenue(request.getParameter("venue"));
            e.setStartDate(stringToSQLDate(request.getParameter("startdate")));
            e.setEndDate(stringToSQLDate(request.getParameter("enddate")));
            e.setInviteStatus(false);
            if (eventDAO.insert(e) > 0) {
                response.sendRedirect("newevent?success");
            }
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(NewEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Date stringToSQLDate(String date) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(date);
        java.sql.Date sqlStartDate = new java.sql.Date(utilDate.getTime());
        return sqlStartDate;
    }

}
