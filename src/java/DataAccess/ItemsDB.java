/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Category;
import Models.Item;
import Models.User;
import Services.InventoryService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 835489
 */
public class ItemsDB {
 private static Item deletedItem;
    public void update(Item item) throws SQLException {
        System.out.println("in update");
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "UPDATE item "
                + "SET category = (select category_id from category where category_name = ?) "
                + ", item_name = ? "
                + ", price= ? "
                + "WHERE item_id = ?";

        try {
            statement = con.prepareStatement(query);

            statement.setString(1, item.getCategoryName());
            statement.setString(2, item.getItemName());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, item.getItemId());
            System.out.println(statement);
            statement.executeUpdate();

        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }

    }

    public List<Item> getAll(String owner) throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;

        ArrayList<Item> itemList = new ArrayList<Item>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if (pool != null) {
            con = pool.getConnection();
        }
        String query = "SELECT i.item_id, c.category_name, i.item_name, i.price from item i inner join category c on i.category= c.category_id  where owner='" + owner + "';";

        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();
            while (results.next()) {
                int itemId = results.getInt(1);
                String category = results.getString(2);
                String itemName = results.getString(3);
                double price = results.getDouble(4);
                Item newItem = new Item(itemId, itemName, price, category);
                itemList.add(newItem);
            }
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return itemList;
    }

    public Item get(int id) throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if (pool != null) {
            con = pool.getConnection();
        }
        Item newItem = null;
        String query = "SELECT item_id, category, item_name, price from item where item_id= '" + id + "';";
        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();
            while (results.next()) {
                int itemId = results.getInt(1);
                int category = results.getInt(2);
                String itemName = results.getString(3);
                double price = results.getDouble(4);
                newItem = new Item(itemId, itemName, price, new Category(category));

            }
        } finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return newItem;
    }

    public void insert(Item item) throws Exception {
        System.out.println("DataAccess.ItemsDB.insert()");
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "INSERT INTO item (item_id,category,item_name,price,owner) VALUES (?, ?, ?, ?, ?); ";

        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, item.getItemId());
            statement.setInt(2, item.getCategory().getCategoryId());
            statement.setString(3, item.getItemName());
            statement.setDouble(4, item.getPrice());
            statement.setString(5, item.getOwner().getEmail());
            System.out.println(statement);
            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void delete(Item item) throws Exception {
        this.deletedItem=item;
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "DELETE FROM item WHERE item_id = " + item.getItemId();

        try {
            statement = con.prepareStatement(query);
            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);

        }

    }

    public void deleteByOwner(String email) throws SQLException {
        PreparedStatement statement = null;
        Connection con = null;

        ConnectionPool pool = ConnectionPool.getInstance();
        if (pool != null) {
            con = pool.getConnection();
        }

        String query = "DELETE FROM item WHERE owner = '" + email+ "' ;";

        try {
            statement = con.prepareStatement(query);
            statement.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);

        }
    }

    public void undo() throws Exception {
        InventoryService is=new InventoryService();
        System.out.println(deletedItem);
        insert(deletedItem);
    }

}
