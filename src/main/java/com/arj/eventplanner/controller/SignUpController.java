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

@WebServlet(name = "signup", urlPatterns = {"/signup/*", "/register/*"})
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("loggedIn");
        if (currentUser == null) {
            request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
        } else {
            response.sendRedirect("home?alreadyloggedin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAOImpl();
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("retypepassword");
            String email = request.getParameter("email");
//            String photo = request.getParameter("profilepicture");
            String sex = request.getParameter("sex");
            if (userDAO.getByUserName(userName) == null) {
                if (userDAO.getByEmail(email) == null) {
                    if (password.equals(rePassword)) {
                        User u = new User();
                        u.setUserName(userName);
                        u.setPassword(password);
                        u.setEmail(email);
                        u.setSex(sex);
//                        u.setProfilePicture();
                        u.setStatus(false);
                        if (userDAO.insert(u) > 0) {
                            response.sendRedirect("signup?success");
                        }
                    } else {
                        response.sendRedirect("signup?pwderror");
                    }
                } else {
                    response.sendRedirect("signup?emerror");
                }
            } else {
                response.sendRedirect("signup?unerror");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void sendAlertMsg(String msg, HttpServletResponse response) throws IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<script type=\"text/javascript\">");
//        out.println("alert('" + msg + "');");
//        out.println("location='signup'");
//        out.println("</script>");
//    }
}
