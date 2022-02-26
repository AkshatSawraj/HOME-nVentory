/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DataAccess.UserDB;
import Models.Category;
import Models.User;
import Servlets.InventoryServlet;
import java.sql.SQLException;
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
public class UserDbTests {
    
    private UserDB DB;
    public UserDbTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DB=new UserDB();
    }
    
    @After
    public void tearDown() {
        DB=null;
    }

    @Test
    public void testGetAll() {
        try {

            List<User> users = null;
            try {

                users = (List<User>) DB.getAll();
            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            assertEquals("cprg352+admin@gmail.com", users.get(0).getEmail());
            assertEquals("cprg352+admin2@gmail.com", users.get(1).getEmail());
            assertEquals("cprg352+anne@gmail.com", users.get(2).getEmail());
            assertEquals("cprg352+barb@gmail.com", users.get(3).getEmail());
            
            

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Test
    public void testGet() throws SQLException {
        try {
            assertEquals("Admin", DB.getUserByEmail("cprg352+admin@gmail.com").getFirstName());
            assertEquals("Barb", DB.getUserByEmail("cprg352+barb@gmail.com").getFirstName());
            
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Test
    public void testDelete() throws Exception {
        assertEquals("Barb", DB.getUserByEmail("cprg352+barb@gmail.com").getFirstName());
        DB.delete(new User("cprg352+barb@gmail.com",true,"Barb","Barber","password","2"));
        assertNotEquals("Barb", DB.getUserByEmail("cprg352+barb@gmail.com").getFirstName());
        
        

    }
    
    
    @Test
    public void testUpdate() throws Exception {
        
        assertEquals("Barb", DB.getUserByEmail("cprg352+barb@gmail.com").getFirstName());
        DB.update(new User("cprg352+akshat@gmail.com", true, "Akshat","Sawraj", "password", "regular user"));
        assertEquals("Akshat", DB.getUserByEmail("cprg352+akshat@gmail.com").getFirstName());
        
    }
    @Test
    public void testInsert() throws Exception {
        
        DB.insert(new User("cprg352+akshat@gmail.com", true, "Akshat","Sawraj", "password", "regular user"));
        assertEquals("Akshat", DB.getUserByEmail("cprg352+akshat@gmail.com").getFirstName());
            
        

    }
}
