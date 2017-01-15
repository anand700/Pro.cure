package saxParser;


import java.io.Serializable;
import java.util.*;


public class Cart implements Serializable{
    public String cartId;
    public String retailer;
    public String name;
    public String id;
    public String image;
    public String category;
    public int price;
    public int quantity;
    public int total;
    public int userId;
    public String username;
    public String description;
    public String orderStatus;
    public String expecteddelivery;
    public Date date;
    public List<String> accessories;
    public Cart(){
        accessories=new ArrayList<String>();
    }
    
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
    
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setExpectedDelivery(String expecteddelivery) {
        this.expecteddelivery = expecteddelivery;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String getDescription() {
        return this.description;
    }

    public String getExpectedDelivery() {
        return this.expecteddelivery;
    }
    public String getOrderStatus() {
        return this.orderStatus;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public String getCartId() {
        return this.cartId;
    }
    
    public String getRetailer() {
        return this.retailer;
    }
    
    
    public String getImage() {
        return this.image;
    }
    
    public String getCategory() {
        return this.category;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public List getAccessories() {
        return accessories;
    }
    
    
    
    
}
