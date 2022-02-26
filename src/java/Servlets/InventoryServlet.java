/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccess.CategoriesDB;
import DataAccess.ItemsDB;
import DataAccess.UserDB;
import Models.Category;
import Models.Item;
import Models.User;
import Services.InventoryService;
import java.io.IOException;
import java.util.ArrayList;
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
public class InventoryServlet extends HttpServlet {

    private String user = null;
    private int itemId = -1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventoryService service = new InventoryService();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        user = email;
        try {
            List<Item> homeItems = (List<Item>) service.getAll(email);

            request.setAttribute("homeItems", homeItems);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserDB userDB = new UserDB();
        User user = new User();
        try {
            user = userDB.getUserByEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fullName = user.getFirstName() + " " + user.getLastName();
        session.setAttribute("username", fullName);
        session.setAttribute("user", user);

        CategoriesDB CategoryDB = new CategoriesDB();

        List<Category> categories = null;
        try {
            categories = (List<Category>) CategoryDB.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // need to check 
        String username = (String) session.getAttribute("username");
        InventoryService service = new InventoryService();

        String action = request.getParameter("action");

        if (action.equals("Save")) {

            String inputCategory = request.getParameter("inputCategory");
            String inputName = request.getParameter("inputName");
            double inputPrice = Double.parseDouble(request.getParameter("inputPrice"));
            CategoriesDB categoryDB = new CategoriesDB();

            List<Category> categoryList = null;

            int id = 0;
            Category category = null;

            try {
                categoryList = (List<Category>) categoryDB.getAll();
            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            int inputCategoryID = Integer.parseInt(inputCategory);
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getCategoryId() == inputCategoryID) {
                    category = categoryList.get(i);
                }
            }

            if (inputPrice > 0) {

                Item item;
                try {
                    service.insert(category, inputName, inputPrice, user);
                    request.setAttribute("invalid", "Item has been sucessfully added.");
                    request.getSession().setAttribute("username", user);
                    doGet(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                request.setAttribute("invalid", "Price not valid");
                request.getSession().setAttribute("username", user);
                doGet(request, response);
            }
            request.getSession().setAttribute("username", user);
            doGet(request, response);

        }
        if (action.equals("delete")) {
            String id = request.getParameter("itemID");
            int intId = Integer.parseInt(id);
            try {
                service.delete(intId, user);
                request.setAttribute("invalid", "Item successfully deleted.");
                request.setAttribute("deleted", true);
                request.getSession().setAttribute("username", user);

                doGet(request, response);
            } catch (Exception ex) {

            }

        }
        if (action.equals("edit")) {
            try {
                List<Item> homeItems = (List<Item>) service.getAll(user);
                String id = request.getParameter("itemID");
                int intId = Integer.parseInt(id);
                Item item = null;
                for (Item i : homeItems) {
                    if (i.getItemId() == intId) {
                        item = i;
                    }
                }

                request.setAttribute("editcategory", item.getCategoryName());
                request.setAttribute("editname", item.getItemName());
                request.setAttribute("editprice", item.getPrice());
                this.itemId = item.getItemId();

                request.getSession().setAttribute("username", user);
                doGet(request, response);
            } catch (Exception ex) {
                request.getSession().setAttribute("username", user);
                doGet(request, response);
            }

        }
        if (action.equals("saveEdit")) {
            ItemsDB db = new ItemsDB();
            String editCategory = request.getParameter("editcategory");
            String editName = request.getParameter("editname");
            String editPrice = request.getParameter("editprice");

            double price = Double.parseDouble(editPrice);
            Item item = null;
            if (this.itemId != -1) {
                item = new Item(this.itemId, editName, price, editCategory);
            }
            try {
                db.update(item);
                request.setAttribute("invalid", "Item edited");
                request.getSession().setAttribute("username", user);

                doGet(request, response);
            } catch (Exception ex) {
                request.setAttribute("invalid", "Error Occured Please try again");

                request.getSession().setAttribute("username", user);

                doGet(request, response);
            }

        }
        if (action.equals("Undo")) {
            ItemsDB db = new ItemsDB();
            try {
                db.undo();
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            doGet(request, response);

        }

    }

}
