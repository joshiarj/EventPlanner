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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "inbox", urlPatterns = {"/inbox/*"})
public class InboxController extends HttpServlet {
    
    private InboxDAO inboxDAO;
    
    public InboxController() throws SQLException, ClassNotFoundException {
        inboxDAO = new InboxDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urlTokens = request.getQueryString().split("=");
        User currentUser = (User) request.getSession().getAttribute("loggedIn");
        if (currentUser != null) {
            try {
                request.setAttribute("allMessages", inboxDAO.getAll());
                getMsgFromURL(request, urlTokens);
                request.getRequestDispatcher("/WEB-INF/views/inbox.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
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
                Inbox i = getMsgFromURL(request, urlTokens);
                if (urlTokens[0].equalsIgnoreCase("viewmsgid")) {
                    i.setReadStatus(true);
                    if (inboxDAO.update(i) > 0) {
                        response.sendRedirect("inbox?viewmsgid=" + i.getId());
                    }
                } else if (urlTokens[0].equalsIgnoreCase("deletemsgid")) {
                    if (inboxDAO.deleteMsgById(i) > 0) {
                        response.sendRedirect("inbox?msgdeleted=1");
                    }
                } else if (urlTokens[0].equalsIgnoreCase("replytomsgid")) {
                    Inbox reply = new Inbox();
                    reply.setSender(i.getReceiver());
                    reply.setReceiver(i.getSender());
                    reply.setSubject(request.getParameter("subject"));
                    reply.setMessage(request.getParameter("message"));
                    reply.setReadStatus(false);
                    if (inboxDAO.insert(reply) > 0) {
                        response.sendRedirect("inbox?replysent=1");
                    }
                } else if (urlTokens[0].equalsIgnoreCase("unreadmsgid")) {
                    i.setReadStatus(false);
                    if (inboxDAO.update(i) > 0) {
                        response.sendRedirect("inbox?all=1");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Inbox getMsgFromURL(HttpServletRequest request, String[] urlTokens) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(urlTokens[urlTokens.length - 1]);
        Inbox msg = inboxDAO.getById(id);
        request.setAttribute("msgFromURL", msg);
        return msg;
    }
}
