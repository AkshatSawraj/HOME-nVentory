/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 835489
 */
public class CategoriesDB {

    public ArrayList<Category> getAll() throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;

        ArrayList<Category> categoryList = new ArrayList<Category>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "SELECT category_id ,category_name from category; ";
        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();
            while (results.next()) {

                int categoryId = results.getInt(1);
                String categoryName = results.getString(2);

                Category newCategory = new Category(categoryId, categoryName);
                System.out.println(newCategory);
                categoryList.add(newCategory);
            }
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return categoryList;
    }

    public Category get(int id) throws SQLException {

        PreparedStatement statement = null;
        ResultSet results = null;
        Connection con = null;
        Category categoryFound = null;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            if (pool != null) {
                con = pool.getConnection();
            }

            String query = "SELECT category_id ,category_name from category where category_id = " + id + "; ";
            statement = con.prepareStatement(query);
            System.out.println(statement);
            results = statement.executeQuery();

            results.next();
            String categoryid = results.getString(1);
            String categoryname = results.getString(2);
            categoryFound = new Category(id, categoryname);
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return categoryFound;
    }

    public void update(Category category) throws SQLException {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "UPDATE category "
                + "SET "
                + " category_name = ? "
                + "WHERE category_id= ?";

        try {
            statement = con.prepareStatement(query);
            System.out.println(statement);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryId());

            statement.executeUpdate();

        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void insert(Category category) throws SQLException {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "INSERT INTO category (category_id,category_name) "
                + "VALUES (?, ?); ";

        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getCategoryName());
            System.out.println(statement);

            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

}
