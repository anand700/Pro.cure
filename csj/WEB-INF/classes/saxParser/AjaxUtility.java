package saxParser;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.io.*;


public class AjaxUtility {

	  public static Connection getConnection() {

	   System.out.println("-------- MySQL JDBC Connection Testing ------------");

	   try {
	      Class.forName("com.mysql.jdbc.Driver");
	   } catch (ClassNotFoundException e) {
	      System.out.println("Where is your MySQL JDBC Driver?");
	      e.printStackTrace();
	      return null;
	   }

	   System.out.println("MySQL JDBC Driver Registered!");
	   Connection connection = null;

	   try {
	      connection = DriverManager
	      .getConnection("jdbc:mysql://localhost/myDatabase","root", "anand123");

	   } catch (SQLException e) {
	      System.out.println("Connection Failed! Check output console");
	      e.printStackTrace();
	      return null;
	   }

	   if (connection != null) {
	      System.out.println("You made it, take control your database now!");
	      return connection;
	   } else {
	      System.out.println("Failed to make connection!");
	   }
	   return null;
	  }

	public static HashMap<String,Product> getData()
	{

		HashMap<String,Product> hm=new HashMap<String,Product>();
		Connection conn = getConnection();
	         Statement stmt = null;
	         try{
	            System.out.println("AjaxUtility>>");
	            stmt = conn.createStatement();
	            String sql;
	            sql = "select * from product_table;";
	            ResultSet rs = stmt.executeQuery(sql);
	            while(rs.next()){
	               Product p = new Product();
		   p.setId(rs.getString("product_id"));
		   p.setName(rs.getString("product_name"));
		   hm.put(rs.getString("product_id"), p);
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
	         }catch(SQLException se){
	            se.printStackTrace();
	         }catch(Exception e){
	            e.printStackTrace();
	         }finally{
	            try{
	               if(stmt!=null)
	                  stmt.close();
	            }catch(SQLException se2){
	            }
	            try{
	               if(conn!=null)
	                  conn.close();
	            }catch(SQLException se){
	               se.printStackTrace();
	            }
	         }
	         System.out.println("<<AjaxUtility");
		return hm;


		
	}

	public StringBuffer readdata(String searchId)
	{
		System.out.println("readdata>>"+searchId);
		StringBuffer sb = new StringBuffer();
		HashMap<String,Product> data;
		data=getData();
		Iterator it = data.entrySet().iterator();
		while (it.hasNext()){
			
			Map.Entry pi = (Map.Entry)it.next();
			Product p=(Product)pi.getValue();
			System.out.println("readdata>>"+p.getName());
			if (p.getName().toLowerCase().startsWith(searchId.toLowerCase()))
			{
				System.out.println("readdata>>Inside");
				sb.append("<product>");
				sb.append("<id>" + p.getId() + "</id>");
				sb.append("<productName>" + p.getName() + "</productName>");
				sb.append("</product>");
			}
			System.out.println("readdata<<");
		}
		return sb;
	}
}