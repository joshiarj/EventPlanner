/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.eventplanner.controller;

import com.arj.eventplanner.dao.UserDAO;
import com.arj.eventplanner.dao.impl.UserDAOImpl;
import com.arj.eventplanner.entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userprofile", urlPatterns = {"/userprofile"})
public class UserProfileController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/userprofile.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAOImpl();
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            User user = userDAO.login(userName, password);
            if (user != null) {
                if (user.isStatus()) {
                    request.getSession().setAttribute("loggedIn", user);
                    response.sendRedirect("dashboard?login=success");
                } else {
                    response.sendRedirect("login?notactivated");
                }
            } else {
                response.sendRedirect("login?error");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
