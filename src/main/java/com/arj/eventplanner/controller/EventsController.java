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

@WebServlet(name = "events", urlPatterns = {"/events"})
public class EventsController extends HttpServlet {

    private EventDAO eventDAO;
    private InboxDAO inboxDAO;

    public EventsController() throws SQLException, ClassNotFoundException {
        eventDAO = new EventDAOImpl();
        inboxDAO = new InboxDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urlTokens = request.getQueryString().split("=");
        User currentUser = (User) request.getSession().getAttribute("loggedIn");
        if (currentUser != null) {
            try {
                request.setAttribute("allEvents", eventDAO.getAll());
                int totalUnreadMsgs = inboxDAO.getUnreadMsgNo(currentUser);
                request.setAttribute("unreadMsgsNumber", totalUnreadMsgs);
                getCurrentEvent(request, urlTokens);
                request.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(EventsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] urlTokens = request.getQueryString().split("=");
            if (urlTokens.length > 1) {
                Event e = getCurrentEvent(request, urlTokens);
                if (urlTokens[0].equalsIgnoreCase("editid")) {
                    e.setTitle(request.getParameter("title"));
                    e.setDescription(request.getParameter("description"));
                    e.setOrganizer((User) request.getSession().getAttribute("loggedIn"));
                    e.setVenue(request.getParameter("venue"));
                    e.setStartDate(stringToSQLDate(request.getParameter("startdate")));
                    e.setEndDate(stringToSQLDate(request.getParameter("enddate")));
                    if (eventDAO.updateById(e) > 0) {
                        response.sendRedirect("events?viewall=1");
                    }
                } else if (urlTokens[0].equalsIgnoreCase("deleteid")) {
                    if (eventDAO.deleteById(e) > 0) {
                        response.sendRedirect("events?viewall=1");
                    }
//                } else if(urlTokens[0].equals("viewall")){
//                    eventDAO.getAll();
                }
            }
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(EventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Date stringToSQLDate(String date) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(date);
        java.sql.Date sqlStartDate = new java.sql.Date(utilDate.getTime());
        return sqlStartDate;
    }

    private Event getCurrentEvent(HttpServletRequest request, String[] urlTokens) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(urlTokens[urlTokens.length - 1]);
        Event event = eventDAO.getById(id);
        request.setAttribute("currentEvent", event);
        return event;
    }

}
