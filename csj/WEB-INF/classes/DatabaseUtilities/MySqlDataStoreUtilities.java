package DatabaseUtilities;

import java.sql.*;
import java.io.*;

public class MySqlDataStoreUtilities
{
	public static Connection getConnection() throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase","root","anand123");
		return conn;
  }
	


	// project room data insertion  roomtype,roomamenities,roomprice,roomqty,roomurl
	 public static boolean InsertRoomDetails(String roomtype, String roomamenities, String roomprice, String roomqty,String roomurl)
	{
		Connection conn = null;
		try{
        
		
		int roomcost = Integer.parseInt(roomprice);
		int roomquantity= Integer.parseInt(roomqty);
		
		conn = getConnection(); 
        PreparedStatement ps=conn.prepareStatement("insert into Room (RoomType,RoomDescription,Price,RoomQuantity,ImageURL)values(?,?,?,?,?)");
	
        ps.setString(1, roomtype);
        ps.setString(2, roomamenities);
        ps.setInt(3, roomcost);
		ps.setInt(4, roomquantity);
		ps.setString(5, roomurl);
       
        int i=ps.executeUpdate();
        
          if(i>0)
          {
			return true;
          }
        
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
			return false;
			
        }
		return false;
	}
	
	//Project - Room data updation  roomtype,roomid,price,roomprice,roomamenities,roomdiscount
	 public static boolean UpdateRoomDetails(String roomtype, String roomid, String price,String roomamenities, String roomqty)
	{
		Connection conn = null;
		int roomid1;
		int price1;
		int qty;
		roomid1 = Integer.parseInt(roomid);
		//price1 = Integer.parseInt(price);
		//qty = Integer.parseInt(roomqty);
		try{
        
		conn = getConnection(); 
        PreparedStatement ps=conn.prepareStatement("update Room set RoomType = ?, RoomDescription=?  where RoomID=?");
        ps.setString(1, roomtype);
		ps.setString(2, roomamenities);
		//ps.setInt(3, Integer.parseInt(roomqty));
		//ps.setInt(4, Integer.parseInt(price));
		
		// ps.setInt(2, price1);
		// ps.setString(3, roomamenities);
        // ps.setInt(4, roomqty1);
		 ps.setInt(3, roomid1);
		
       int i = ps.executeUpdate();
        
          if(i>0)
          {
			return true;
          }
        
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
			return false;
			
        }
		return false;
	}
	
	
	
		//project  deleteRoom (1)
 	public static boolean deleteRoom(String roomid)
	{
		Connection conn = null;
		try{
					conn = getConnection(); 
					PreparedStatement ps =conn.prepareStatement("delete from Room where roomid = ?");
					ps.setString(1, roomid);
					int i=ps.executeUpdate();
			
			  if(i>0)
			  {
				return true;
			  }
			
			}
        catch(Exception exp)
        {
            exp.printStackTrace();
			return false;
			
        }
		return false;
	}


}