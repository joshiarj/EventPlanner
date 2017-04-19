/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.controller;

import com.arj.eventplanner.dao.InboxDAO;
import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.dao.impl.InboxDAOImpl;
import com.arj.eventplanner.dao.impl.UserDAOImpl;
import com.arj.eventplanner.entity.Inbox;
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

@WebServlet(name = "users", urlPatterns = {"/users/*"})
public class UsersController extends HttpServlet {

    private UserDAO userDAO;
    private InboxDAO inboxDAO;

    public UsersController() throws SQLException, ClassNotFoundException {
        userDAO = new UserDAOImpl();
        inboxDAO = new InboxDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urlTokens = request.getQueryString().split("=");
        User currentUser = (User) request.getSession().getAttribute("loggedIn");
        if (currentUser != null) {
            try {
                getUserFromURL(request, urlTokens);
                request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
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
                User u = getUserFromURL(request, urlTokens);
                if (urlTokens[0].equalsIgnoreCase("editid")) {
//                    e.setTitle(request.getParameter("title"));
//                    e.setDescription(request.getParameter("description"));
//                    e.setOrganizer((User) request.getSession().getAttribute("loggedIn"));
//                    e.setVenue(request.getParameter("venue"));
//                    e.setStartDate(stringToSQLDate(request.getParameter("startdate")));
//                    e.setEndDate(stringToSQLDate(request.getParameter("enddate")));
//                    if (eventDAO.updateById(e) > 0) {
//                        response.sendRedirect("home?eventedited");
//                    }
                } else if (urlTokens[0].equalsIgnoreCase("deleteid")) {
//                    if (eventDAO.deleteById(e) > 0) {
//                        response.sendRedirect("home?eventdeleted");
//                    }
                }else if (urlTokens[0].equalsIgnoreCase("msgtoid")) {
                    Inbox i = new Inbox();
                    i.setSender((User) request.getSession().getAttribute("loggedIn"));
                    i.setReceiver(getUserFromURL(request, urlTokens));
                    i.setSubject(request.getParameter("subject"));
                    i.setMessage(request.getParameter("message"));
                    i.setReadStatus(false);
                    if(inboxDAO.insert(i)>0){
                        response.sendRedirect("users?msgsent=1");
                    }
                }
            }
//        } catch (SQLException | ClassNotFoundException | ParseException ex) {
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private User getUserFromURL(HttpServletRequest request, String[] urlTokens) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(urlTokens[urlTokens.length - 1]);
        User user = userDAO.getById(id);
        request.setAttribute("userFromURL", user);
        return user;
    }

//    private Date stringToSQLDate(String date) throws SQLException, ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date utilDate = sdf.parse(date);
//        java.sql.Date sqlStartDate = new java.sql.Date(utilDate.getTime());
//        return sqlStartDate;
//    }

}
