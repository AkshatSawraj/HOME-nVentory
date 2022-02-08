/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccess.ItemsDB;
import DataAccess.UserDB;
import Models.Item;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("Click here to download CSV file")) {
            String file = "UserName, No. Of Items, Total Value \n ";
            UserDB db = new UserDB();
            ItemsDB itemdb = new ItemsDB();
            ArrayList<User> users = null;
            try {
                users = (ArrayList<User>) db.getAll();
            } catch (Exception ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (User i : users) {
                file += (i.getFirstName() + " " + i.getLastName()) + ", ";
                ArrayList<Item> items = null;
                try {
                    items = (ArrayList) itemdb.getAll(i.getEmail());
                } catch (Exception ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                file += "" + items.size() + ", ";
                double totalPrice = 0;
                for (Item j : items) {
                    totalPrice += j.getPrice();
                }
                file += "" + totalPrice + "\n ";
            }
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=Report.csv");
            response.getWriter().write(file);

        } else if (action.equals("Click here to download CSV file of Active Users")) {
            String file = "Name, Number Of Items \n ";
            UserDB db = new UserDB();
            ItemsDB itemdb = new ItemsDB();
            ArrayList<User> users = null;
            try {
                users = (ArrayList<User>) db.getAll();
            } catch (Exception ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (User i : users) {
                if (i.getRole().equals("regular user")) {
                    file += (i.getFirstName() + " " + i.getLastName()) + ", ";
                    ArrayList<Item> items = null;
                    try {
                        items = (ArrayList) itemdb.getAll(i.getEmail());
                    } catch (Exception ex) {
                        Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    file += "" + items.size() + "\n ";
                    
                }
            }
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=Report.csv");
            response.getWriter().write(file);

        }

    }

}
