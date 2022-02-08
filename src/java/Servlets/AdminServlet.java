/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccess.ItemsDB;
import DataAccess.UserDB;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDB userDB = new UserDB();

        List<User> users;
        try {
            users = (List<User>) userDB.getAll();
            request.setAttribute("users", users);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (action.equals("delete")) {
            try {
                String deleteName = request.getParameter("userdel");
                UserDB userDB = new UserDB();
                ItemsDB itemDB = new ItemsDB();
                User user = null;
                user = (User) userDB.getUserByEmail(deleteName);
                System.out.println(user);

                User admin = userDB.getUserByEmail(email);
                if (!user.equals(admin)) {

                    userDB.delete(user);
                    itemDB.deleteByOwner(user.getEmail());

                    request.setAttribute("message", "User deleted");
                    request.setAttribute("deleted", true);
                    doGet(request, response);
                } else {
                    request.setAttribute("message", "Admin can not be deleted");
                    doGet(request, response);
                }

            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                doGet(request, response);
            }
        } else if (action.equals("save")) {
            UserDB userDB = new UserDB();
            String addpassword = request.getParameter("addpassword");
            String addemail = request.getParameter("addemail");
            String addfirstname = request.getParameter("addfirstname");
            String addlastname = request.getParameter("addlastname");

            User user = new User(addemail, true, addfirstname, addlastname, addpassword, "regular user");
            System.out.println(user);
            try {
                userDB.insert(user);
                request.setAttribute("message", "User Added");

                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                doGet(request, response);
            }
        } else if (action.equals("edit")) {
            try {
                String editName = request.getParameter("useredit");
                UserDB userDB = new UserDB();
                User user = null;
                user = (User) userDB.getUserByEmail(editName);

                request.setAttribute("editpassword", user.getPassword());
                request.setAttribute("editemail", user.getEmail());
                request.setAttribute("editfirstname", user.getFirstName());
                request.setAttribute("editlastname", user.getLastName());
                if (user.isActive() == true) {

                    request.setAttribute("isActive", "true");
                } else {
                    request.setAttribute("isActive", "false");
                }
                request.setAttribute("role", user.getRole());

                doGet(request, response);
            } catch (Exception ex) {
                doGet(request, response);
            }
        } else if (action.equals("saveEdit")) {
            UserDB userDB = new UserDB();

            String editPassword = request.getParameter("editpassword");
            String editemail = request.getParameter("editemail");
            String editfirstname = request.getParameter("editfirstname");
            String editlastname = request.getParameter("editlastname");
            String editActive = request.getParameter("activeUsers");
            String role = request.getParameter("role");

            User user = new User(editemail, Boolean.parseBoolean(editActive), editfirstname, editlastname, editPassword, role);
            try {
                userDB.update(user);
                request.setAttribute("message", "User edited");
                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                doGet(request, response);
            }
        } else if (action.equals("Undo")) {
            System.out.println("in undo");
            UserDB db = new UserDB();
            try {
                db.undo();
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            doGet(request, response);

        }
    }

}
