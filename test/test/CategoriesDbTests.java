/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DataAccess.CategoriesDB;
import Models.Category;
import Servlets.InventoryServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 835489
 */
public class CategoriesDbTests {

    private CategoriesDB DB;

    @Before
    public void setUp() {
        DB = new CategoriesDB();

    }

    @After
    public void tearDown() {
        DB = null;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetAll() {
        try {

            List<Category> categories = null;
            try {

                categories = (List<Category>) DB.getAll();
            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            assertEquals("kitchen", categories.get(0).getCategoryName());
            assertEquals("bathroom", categories.get(1).getCategoryName());
            assertEquals("living room", categories.get(2).getCategoryName());
            assertEquals("basement", categories.get(3).getCategoryName());
            assertEquals("bedroom", categories.get(4).getCategoryName());
            assertEquals("garage", categories.get(5).getCategoryName());
            assertEquals("office", categories.get(6).getCategoryName());
            assertEquals("utility room", categories.get(7).getCategoryName());
            assertEquals("storage", categories.get(8).getCategoryName());
            assertEquals("other", categories.get(9).getCategoryName());

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testGet() throws SQLException {
        try {
            assertEquals("kitchen", DB.get(1).categoryName);
            assertEquals("bathroom", DB.get(2).categoryName);
            
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void testUpdate() throws SQLException {
        try {
            assertEquals("kichen", DB.get(1).categoryName);
            DB.update(new Category(1,"bonusroom"));
            assertEquals("bonusroom", DB.get(1).categoryName);
            
            
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void testInsert() throws SQLException {
        try {
            DB.insert(new Category(11,"BonusRoom"));
            assertEquals("BonusRoom", DB.get(11).categoryName);
            
            
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
    
}
