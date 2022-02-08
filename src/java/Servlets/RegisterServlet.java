/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccess.UserDB;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 835489
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB db = new UserDB();
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("Save")) {
            String email = request.getParameter("inputEmail");
            String password = request.getParameter("inputPassword");
            String firstName = request.getParameter("inputFirst");
            String lastName = request.getParameter("inputLast");
            try {
                System.out.println("in try");
                db.insert(new User(email, true, firstName, lastName, password, "regular user"));
                request.setAttribute("message", "User Registered");
                System.out.println("inserted");
                doGet(request, response);

            } catch (Exception ex) {
                request.setAttribute("message", "User Registeration Failed");
                doGet(request, response);
            }
            
        }
        request.setAttribute("message", "User Registeration Failed");
                doGet(request, response);

    }

}
