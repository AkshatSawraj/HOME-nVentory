package Servlets;

import DataAccess.UserDB;
import Models.User;
import Services.AccountService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String logout = request.getParameter("logout");
        if (logout != null) {
            session.invalidate();
            request.setAttribute("failed", "You have successfully logged out.");

        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.equals("") || password.equals("")) {
            request.setAttribute("failed", "Email or Password cant be empty");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;

        }

        AccountService service = new AccountService();
        User user = service.login(email, password);
        if (user == null) {
            request.setAttribute("failed", "Please enter correect email and password.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("email", user.getEmail());
       

        if (user.getRole().equals("system admin") && user.isActive() == true) {
            response.sendRedirect("admin");
            
            
        } else if (user.getRole().equals("company admin") && user.isActive() == true) {
            UserDB db=new UserDB();
            try {
                session.setAttribute("companyId", db.getCompanyID(user.getEmail()));
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("company");
            
            
            
        } else if (user.getRole().equals("regular user") && user.isActive() == true) {
            response.sendRedirect("inventory");
        } else {
            request.setAttribute("failed", "This user is not active!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }

}
