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
import javax.servlet.http.HttpSession;

/**
 *
 * @author 835489
 */
public class AccountServlet extends HttpServlet {

    private String username = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("user");
        email=email.replace(" ", "+");
        this.username = email;
        System.out.println(this.username);
        User user = new User();
        UserDB db = new UserDB();
        try {
            user = db.getUserByEmail(username);
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user);
        request.setAttribute("editpassword", user.getPassword());
        request.setAttribute("editemail", user.getEmail());
        request.setAttribute("editfirstname", user.getFirstName());
        request.setAttribute("editlastname", user.getLastName());
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        HttpSession session = request.getSession();
        UserDB userDB = new UserDB();
        if (action.equals("saveEdit")) {

            String editPassword = request.getParameter("editpassword");
            String editfirstname = request.getParameter("editfirstname");
            String editlastname = request.getParameter("editlastname");

            User user = new User(this.username, true, editfirstname, editlastname, editPassword, "regular user");
            try {
                userDB.update(user);
                request.setAttribute("message", "User edited");
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                doGet(request, response);
            }
        } else if (action.equals("Deactivate")) {
            System.out.println("in deactivate");
            User user = null;
            try {
                user = userDB.getUserByEmail(username);
                userDB.deactivate(user);
                request.setAttribute("message", "User Deactivated");
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.setAttribute("message", "Error Occured, Please Try again later");
                doGet(request, response);
            
            
        }
    }

}
