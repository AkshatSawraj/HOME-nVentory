package test;

import DataAccess.ItemsDB;
import Models.Category;
import Models.Item;
import Servlets.InventoryServlet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemsDbTests {

    private ItemsDB DB;

    public ItemsDbTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        DB = new ItemsDB();
    }

    @After
    public void tearDown() {
        DB = null;
    }

    @Test
    public void testGetAll() {

        List<Item> items = null;
        try {

            items = (List<Item>) DB.getAll("cprg352+anne@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals("blender", items.get(0).getItemName());
        assertEquals("toaster", items.get(1).getItemName());
        assertEquals("lamp", items.get(2).getItemName());
        assertEquals("winter tires", items.get(3).getItemName());
        assertEquals("dresser", items.get(4).getItemName());

    }

    @Test
    public void testGet() throws Exception {
        assertEquals("blender", DB.get(1).getItemName());
        assertEquals("toster", DB.get(2).getItemName());

    }
    @Test
    public void testUpdate() throws Exception {
        
        assertEquals("blender", DB.get(1).getItemName());
        DB.update(new Item(1,"mouse",15.7,"office"));
        assertEquals("mouse", DB.get(1).getItemName());

    }
    @Test
    public void testInsert() throws Exception {
        DB.insert(new Item(6,"mouse",15.7,"office"));
        assertEquals("mouse", DB.get(6).getItemName());
        

    }
    @Test
    public void testDelete() throws Exception {
        assertEquals("blender", DB.get(1).getItemName());
        DB.delete(new Item(1,"mouse",15.7,"office"));
        assertNotEquals("blender", DB.get(1).getItemName());
        
        

    }
    
    

}
