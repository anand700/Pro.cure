package saxParser;


import java.util.*;

public class Room {
    int  roomID;
    int  price;
    String roomType;
    String roomDescription;
    String imageURL;
    int roomQuantity;
    
    
    public void setRoomID(int roomID)
    {
        this.roomID = roomID;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    public void setRoomQuantity(int roomQuantity)
    {
        this.roomQuantity = roomQuantity;
    }
    
    
    public void setRoomType(String roomType)
    {
        this.roomType = roomType;
    }
    
    
    public void setRoomDescription(String roomDescription)
    {
        this.roomDescription = roomDescription;
    }
    
    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
    
    public int getRoomID()
    {
        return roomID;
    }
    
    
    public int getPrice()
    {
        return price;
    }
    
    public int getRoomQuantity()
    {
        return roomQuantity;
    }
    
    
    public String getRoomType()
    {
        return roomType;
    }
    
    public String getRoomDescription()
    {
        return roomDescription;
    }
    
    
    public String getImageURL()
    {
        return imageURL;
    }
    
    
    
}
