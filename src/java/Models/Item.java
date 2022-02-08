/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author 835489
 */
public class Item {
    public static int count;
    public  int itemId;
    public String itemName;
    public double price;
    public Category category;
    public String categoryName;
    public User owner;

    

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Item(String itemName, double price, Category category) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        count++;
        this.itemId=count;
    }
    public Item(int itemId,String itemName, double price, Category category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        count++;
    }
    public Item(int itemId,String itemName, double price, String category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.categoryName = category;
        count++;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", category=" + category + ", categoryName=" + categoryName + ", owner=" + owner + '}';
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
}
