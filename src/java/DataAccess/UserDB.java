/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 835489
 */
public class UserDB {

    private static User deletedUser;

    public List<User> getAll() throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;

        ArrayList<User> userList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "SELECT u.email, u.active, u.first_name, u.last_name, u.password, r.role_name "
                + "FROM user u inner join role r on u.role=r.role_id ";
        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();

            while (results.next()) {

                String email = results.getString(1);
                boolean active = results.getBoolean(2);
                String firstname = results.getString(3);
                String lastname = results.getString(4);
                String password = results.getString(5);
                String role = results.getString(6);

                User newUser = new User(email, active, firstname, lastname, password, role);
                userList.add(newUser);
            }
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return userList;
    }

    public List<User> getByCompany(int id) throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;

        ArrayList<User> userList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "SELECT u.email, u.active, u.first_name, u.last_name, u.password, r.role_name "
                + "FROM user u inner join role r on u.role=r.role_id where u.company_id =" + id + ";";
        try {
            statement = con.prepareStatement(query);
            System.out.println(statement);
            results = statement.executeQuery();

            while (results.next()) {

                String email = results.getString(1);
                boolean active = results.getBoolean(2);
                String firstname = results.getString(3);
                String lastname = results.getString(4);
                String password = results.getString(5);
                String role = results.getString(6);

                User newUser = new User(email, active, firstname, lastname, password, role);
                userList.add(newUser);
            }
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return userList;
    }

    public void delete(User user) throws Exception {
        this.deletedUser = user;

        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }
        String query = " DELETE FROM user WHERE email = '" + user.getEmail() + "';";

        try {

            statement = con.prepareStatement(query);
            System.out.println(statement);
            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }

    }

    public void insert(User user) throws Exception {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "INSERT INTO user (email, active, first_name, last_name, password, role) "
                + "VALUES (?, ?, ?, ?, ?, (Select role_id from role where role_name = ? ) ); ";

        try {
            statement = con.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setBoolean(2, user.isActive());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole());
            System.out.println(statement);

            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "UPDATE user "
                + "SET "
                + " active = ? "
                + ", first_name= ? "
                + ", last_name = ? "
                + ", password = ? "
                + ", role = (select role_id from role where role_name= ? ) "
                + "WHERE email = ?";

        try {
            statement = con.prepareStatement(query);
            System.out.println(statement);
            statement.setBoolean(1, user.isActive());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());

            statement.setString(6, user.getEmail());

            System.out.println(statement);
            statement.executeUpdate();

        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }
//

    public void deactivate(User user) throws SQLException {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "UPDATE user "
                + "SET active = ? "
                + " WHERE email = ?";

        try {
            statement = con.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setString(2, user.getEmail());
            System.out.println(statement);
            statement.executeUpdate();

        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        if (email.contains(" ")) {
            email = email.replace(" ", "+");
        }

        PreparedStatement statement = null;
        ResultSet results = null;
        Connection con = null;
        User userFound = null;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            if (pool != null) {
                con = pool.getConnection();
            }

            String query = "SELECT u.email, u.active, u.first_name, u.last_name, u.password, r.role_name "
                    + "FROM user u inner join role r on u.role=r.role_id "
                    + "WHERE email ='" + email + "'";
            statement = con.prepareStatement(query);
            System.out.println(statement);
            results = statement.executeQuery();

            results.next();
            String emaill = results.getString(1);
            boolean isActive = results.getBoolean(2);
            String firstname = results.getString(3);
            String lastname = results.getString(4);
            String password = results.getString(5);
            String roleName = results.getString(6);
            userFound = new User(emaill, isActive, firstname, lastname, password, roleName);
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return userFound;
    }

    public int getCompanyID(String email) throws SQLException {
        if (email.contains(" ")) {
            email = email.replace(" ", "+");
        }

        PreparedStatement statement = null;
        ResultSet results = null;
        Connection con = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        int id;
        try {
            if (pool != null) {
                con = pool.getConnection();
            }

            String query = "SELECT company_id from user where email ='" + email + "';";
            statement = con.prepareStatement(query);
            System.out.println(statement);
            results = statement.executeQuery();

            results.next();
            id = results.getInt(1);
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        System.out.println(id);
        return id;
    }

    public void insertByCompany(User user, int companyID) throws SQLException {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "INSERT INTO user (email, active, first_name, last_name, password, role, company_id) "
                + "VALUES (?, ?, ?, ?, ?, (Select role_id from role where role_name = ? ),? ); ";

        try {
            statement = con.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setBoolean(2, user.isActive());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole());
            statement.setInt(7, companyID);

            System.out.println(statement);

            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void undo() throws Exception {
        insert(deletedUser);
    }

    public void undo(int compId) throws Exception {
        insertByCompany(deletedUser,compId);
    }

}
