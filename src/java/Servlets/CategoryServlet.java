
package Servlets;

import DataAccess.CategoriesDB;
import DataAccess.UserDB;
import Models.Category;
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
public class CategoryServlet extends HttpServlet {

    private int id = -1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriesDB CategoryDB = new CategoriesDB();

        List<Category> categories = null;
        try {
            categories = (List<Category>) CategoryDB.getAll();
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        String action = request.getParameter("action");
       
        HttpSession session = request.getSession();
        if (action.equals("edit")) {
            try {

                String id = request.getParameter("categoryedit");
                this.id = Integer.parseInt(id);
                CategoriesDB db = new CategoriesDB();
                Category found = db.get(Integer.parseInt(id));
                request.setAttribute("editname", found.getCategoryName());
                doGet(request, response);
            } catch (Exception ex) {
                doGet(request, response);
            }
        } else if (action.equals("saveEdit")) {
            CategoriesDB db = new CategoriesDB();
            String editname = request.getParameter("editname");
            Category category = new Category(id, editname);
            try {
                db.update(category);
                request.setAttribute("message", "Category edited");
                doGet(request, response);
            } catch (SQLException ex) {
                request.setAttribute("message", "Error Ocuured, Please try again later");
                doGet(request, response);
            }

        } else if (action.equals("save")) {
            CategoriesDB db = new CategoriesDB();
            String addid = request.getParameter("addid");
            String addname = request.getParameter("addname");
            int ID=Integer.parseInt(addid);
            Category category =new Category(ID, addname);
            try {
                db.insert(category);
                request.setAttribute("message", "Category Added");

                doGet(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                doGet(request, response);
            }
        }

    }

}



