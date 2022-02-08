/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataAccess.ItemsDB;
import DataAccess.UserDB;
import Models.Category;
import Models.Item;
import Models.User;
import java.util.List;

/**
 *
 * @author 835489
 */
public class InventoryService {
    
    public List<Item> getAll(String email) throws Exception {
        ItemsDB DB = new ItemsDB();
        List<Item> homeitems = DB.getAll(email);
        return homeitems;
    }
    
    public void insert(Category cat, String itemName, double price, String owner) throws Exception {
        Item item = new Item(itemName, price, cat);
        UserDB userDB = new UserDB();
        User user = userDB.getUserByEmail(owner);
        item.setOwner(user);
        
        ItemsDB itemDB = new ItemsDB();
        itemDB.insert(item);
    }
    public void delete(int id) throws Exception {
        ItemsDB itemDB = new ItemsDB();
        Item item = (Item) itemDB.get(id);
        System.out.println(item);
        itemDB.delete(item);
    }
    
    public void delete(int id,String email) throws Exception {
        ItemsDB itemDB = new ItemsDB();
        Item item = (Item) itemDB.get(id);
        UserDB db=new UserDB();
        item.setOwner(db.getUserByEmail(email));
        System.out.println(item);
        itemDB.delete(item);
    }

   

}
