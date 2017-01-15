package saxParser;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import saxParser.*;



public class MySQLDataStoreUtilities {

    public static final String ORDER_TABLE_NAME = "order_table";
    public static final String ORDER_TABLE_PK_COLUMN = "order_id";
    
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

    public static HashMap<String, saxParser.Room> selectAllRoomWithFilter(String filter){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Room> map = new HashMap<String, saxParser.Room>();
        try{
            System.out.println("selectAllRoomWithFilter>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from Room where RoomType LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Room room = new saxParser.Room();
                int room_id  = rs.getInt("RoomID");
                int price  = rs.getInt("Price");
                int roomQuantity  = rs.getInt("RoomQuantity");
                String roomType = rs.getString("RoomType");
                String roomDescription = rs.getString("RoomDescription");
                String roomImageURL = rs.getString("ImageURL");
                
                room.setRoomType(roomType);
                room.setRoomDescription(rs.getString("RoomDescription"));
                room.setRoomID(rs.getInt("RoomID"));
                room.setPrice(rs.getInt("Price"));
                room.setRoomQuantity(rs.getInt("RoomQuantity"));
                room.setImageURL(rs.getString("ImageURL"));
                
                System.out.print("room_id: " + room_id);
                System.out.print(", price: " + price);
                System.out.print(", roomQuantity: " + roomQuantity);
                System.out.println(", roomType: " + roomType);
                System.out.println(", roomDescription: " + roomDescription);
                
                map.put(rs.getString("RoomType"),room);
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
        System.out.println("<<selectAllRoomWithFilter");
        return map;
    }
    
    public static HashMap<String, saxParser.Room> selectAllRoom(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Room> map = new HashMap<String, saxParser.Room>();
        try{
            System.out.println("selectAllRoomWithFilter>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from Room;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Room room = new saxParser.Room();
                int room_id  = rs.getInt("RoomID");
                int price  = rs.getInt("Price");
                int roomQuantity  = rs.getInt("RoomQuantity");
                String roomType = rs.getString("RoomType");
                String roomDescription = rs.getString("RoomDescription");
                
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                room.setRoomType(roomType);
                room.setRoomDescription(rs.getString("RoomDescription"));
                room.setRoomID(rs.getInt("RoomID"));
                room.setPrice(rs.getInt("Price"));
                room.setRoomQuantity(rs.getInt("RoomQuantity"));
                room.setImageURL(rs.getString("ImageURL"));
                
                System.out.print("room_id: " + room_id);
                System.out.print(", price: " + price);
                System.out.print(", roomQuantity: " + roomQuantity);
                System.out.println(", roomType: " + roomType);
                System.out.println(", roomDescription: " + roomDescription);
                
                map.put(rs.getString("RoomType"),room);
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
        System.out.println("<<selectAllRoomWithFilter");
        return map;
    }
    
    public static void selectUser(){
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            System.out.println("selectUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int user_id  = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String password = rs.getString("password");
                String phone_number = rs.getString("phone_number");
                
                //Display values
                System.out.print("user_id: " + user_id);
                System.out.print(", user_name: " + user_name);
                System.out.print(", password: " + password);
                System.out.println(", phone_number: " + phone_number);
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
        System.out.println("<<selectUser");
    }
    
    
    public static HashMap<String, saxParser.Product> selectAllProductsWithFilterAsAccesory(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Product> map = new HashMap<String, saxParser.Product>();
        try{
            System.out.println("selectAllProduct>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table where lower(category) LIKE \"%accessory%\" or lower(category) LIKE \"%accessories%\" LIMIT 4;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Product product = new saxParser.Product();
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                String description = rs.getString("description");
                
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDate(rs.getDate("ordered_date"));
                product.setDescription(rs.getString("description"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                System.out.println(", description: " + description);
                
                map.put(rs.getString("product_id"),product);
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
        System.out.println("<<selectAllProduct");
        return map;
    }
    
    
    public static HashMap<String, saxParser.Product> selectAllProductsWithFilter(String filter){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Product> map = new HashMap<String, saxParser.Product>();
        try{
            System.out.println("selectAllProduct>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table where category LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Product product = new saxParser.Product();
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                String description = rs.getString("description");
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDate(rs.getDate("ordered_date"));
                product.setDescription(rs.getString("description"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                System.out.println(", description: " + description);
                
                map.put(rs.getString("product_id"),product);
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
        System.out.println("<<selectAllProduct");
        return map;
    }
    
    public static HashMap<String, saxParser.Product> selectAllProductsByName(String filter){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Product> map = new HashMap<String, saxParser.Product>();
        try{
            System.out.println("selectAllProductsByName>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table where product_name LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Product product = new saxParser.Product();
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                String description = rs.getString("description");
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDescription(rs.getString("description"));
                product.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                System.out.println(", description: " + description);
                map.put(rs.getString("product_id"),product);
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
        System.out.println("<<selectAllProductsByName");
        return map;
    }
    
    
    public static HashMap<String, saxParser.Cart> selectAllCartsWithFilter(String filter){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Cart> map = new HashMap<String, saxParser.Cart>();
        try{
            System.out.println("selectAllCartsWithFilter>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table where category LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Cart order = new saxParser.Cart();
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setCartId(Integer.toString(rs.getInt("cart_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("cart_id")),order);
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
        System.out.println("<<selectAllCartsWithFilter");
        return map;
    }
    
    public static HashMap<String, saxParser.Cart> selectAllCartsWithFilterForUser(String filter, String userId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Cart> map = new HashMap<String, saxParser.Cart>();
        try{
            System.out.println("selectAllCartsWithFilterForUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table where user_id = "+userId+" and category LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Cart order = new saxParser.Cart();
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setCartId(Integer.toString(rs.getInt("cart_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("cart_id")),order);
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
        System.out.println("<<selectAllCartsWithFilterForUser");
        return map;
    }
    
    public static HashMap<String, saxParser.Order> selectAllOrdersWithFilter(String filter){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("selectAllOrders>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where category LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<selectAllOrders");
        return map;
    }
    
    
    public static HashMap<String, saxParser.Order> selectAllOrdersWithFilterForUser(String filter, String userId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("selectAllOrdersWithFilterForUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where userId = "+userId+" and category LIKE \"%"+filter+"%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<selectAllOrdersWithFilterForUser");
        return map;
    }
    
    public static HashMap<String, saxParser.Order> selectAllOrdersWithFilterAsAccessory(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("selectAllOrders>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where category LIKE \"%accessory%\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<selectAllOrders");
        return map;
    }
    
    
    public static HashMap<String, saxParser.Cart> getCart(int productId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String,saxParser.Cart> map = new HashMap<String,saxParser.Cart>();
        try{
            System.out.println("getCart>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table where product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Cart cart = new saxParser.Cart();
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                cart.setCartId(Integer.toString(rs.getInt("cart_id")));
                cart.setId(rs.getString("product_id"));
                cart.setName(rs.getString("product_name"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setRetailer(rs.getString("retailer"));
                cart.setImage(rs.getString("image"));
                cart.setCategory(rs.getString("category"));
                cart.setUsername(rs.getString("username"));
                cart.setPrice(rs.getInt("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotal(rs.getInt("total"));
                cart.setOrderStatus(rs.getString("orderStatus"));
                cart.setExpectedDelivery(rs.getString("expecteddelivery"));
                cart.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("cart_id")),cart);
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
        System.out.println("<<getCart");
        return map;
    }
    
    public static saxParser.Cart getCart1(int productId, int userId){
        Connection conn = getConnection();
        Statement stmt = null;
        saxParser.Cart cart = new saxParser.Cart();
        //HashMap<String,saxParser.Cart> map = new HashMap<String,saxParser.Cart>();
        try{
            System.out.println("getCart1>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table where user_id = "+userId+" and product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                String description = rs.getString("description");
                
                
                cart.setCartId(Integer.toString(rs.getInt("cart_id")));
                cart.setDescription(description);
                cart.setId(rs.getString("product_id"));
                cart.setName(rs.getString("product_name"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setRetailer(rs.getString("retailer"));
                cart.setImage(rs.getString("image"));
                cart.setCategory(rs.getString("category"));
                cart.setUsername(rs.getString("username"));
                cart.setPrice(rs.getInt("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotal(rs.getInt("total"));
                cart.setOrderStatus(rs.getString("orderStatus"));
                cart.setExpectedDelivery(rs.getString("expecteddelivery"));
                cart.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                //map.put(Integer.toString(rs.getInt("cart_id")),cart);
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
        System.out.println("<<getCart1");
        return cart;
    }
    
    public static HashMap<String, saxParser.Cart> selectAllCarts(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String,saxParser.Cart> map = new HashMap<String,saxParser.Cart>();
        try{
            System.out.println("selectAllCarts>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Cart cart = new saxParser.Cart();
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                cart.setCartId(Integer.toString(rs.getInt("cart_id")));
                cart.setId(rs.getString("product_id"));
                cart.setName(rs.getString("product_name"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setRetailer(rs.getString("retailer"));
                cart.setImage(rs.getString("image"));
                cart.setCategory(rs.getString("category"));
                cart.setUsername(rs.getString("username"));
                cart.setPrice(rs.getInt("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotal(rs.getInt("total"));
                cart.setOrderStatus(rs.getString("orderStatus"));
                cart.setExpectedDelivery(rs.getString("expecteddelivery"));
                cart.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("cart_id")),cart);
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
        System.out.println("<<selectAllCarts");
        return map;
    }
    
    
    
    public static HashMap<String, saxParser.Cart> selectAllCartsForUser(String userId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String,saxParser.Cart> map = new HashMap<String,saxParser.Cart>();
        try{
            System.out.println("selectAllCartsForUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from cart_table where user_id = "+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Cart cart = new saxParser.Cart();
                int order_id  = rs.getInt("cart_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                cart.setCartId(Integer.toString(rs.getInt("cart_id")));
                cart.setId(rs.getString("product_id"));
                cart.setName(rs.getString("product_name"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setRetailer(rs.getString("retailer"));
                cart.setImage(rs.getString("image"));
                cart.setCategory(rs.getString("category"));
                cart.setUsername(rs.getString("username"));
                cart.setPrice(rs.getInt("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotal(rs.getInt("total"));
                cart.setOrderStatus(rs.getString("orderStatus"));
                cart.setExpectedDelivery(rs.getString("expecteddelivery"));
                cart.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("cart_id")),cart);
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
        System.out.println("<<selectAllCartsForUser");
        return map;
    }
    
    public static HashMap<String, saxParser.Order> getOrder(int productId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("getOrder>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                order.setDescription(rs.getString("description"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<getOrder");
        return map;
    }
    
    public static saxParser.Order getOrder1(int productId, int userId){
        Connection conn = getConnection();
        Statement stmt = null;
        //HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        saxParser.Order order = new saxParser.Order();
        try{
            System.out.println("getOrder1>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where user_id = "+userId+" and product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                order.setDescription(rs.getString("description"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                //map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<getOrder1");
        return order;
    }
    
    
    public static saxParser.Order getOrderForSalesmen(int productId){
        Connection conn = getConnection();
        Statement stmt = null;
        //HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        saxParser.Order order = new saxParser.Order();
        try{
            System.out.println("getOrderForSalesmen>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                String description = rs.getString("description");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDescription(rs.getString("description"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                //map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<getOrderForSalesmen");
        return order;
    }
    
    public static HashMap<String, saxParser.Order> selectAllOrders(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("selectAllOrders>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<selectAllOrders");
        return map;
    }
    
    public static HashMap<String, saxParser.Order> selectAllOrdersForUser(String userId){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Order> map = new HashMap<String, saxParser.Order>();
        try{
            System.out.println("selectAllOrdersForUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from order_table where user_id = "+userId+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Order order = new saxParser.Order();
                int order_id  = rs.getInt("order_id");
                String product_id  = rs.getString("product_id");
                String product_name = rs.getString("product_name");
                int user_id = rs.getInt("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int total = rs.getInt("total");
                String orderStatus = rs.getString("orderStatus");
                String expecteddelivery = rs.getString("expecteddelivery");
                String ordered_date = rs.getString("ordered_date");
                
                
                order.setOrderId(Integer.toString(rs.getInt("order_id")));
                order.setId(rs.getString("product_id"));
                order.setName(rs.getString("product_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setRetailer(rs.getString("retailer"));
                order.setImage(rs.getString("image"));
                order.setCategory(rs.getString("category"));
                order.setUsername(rs.getString("username"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getInt("total"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setExpectedDelivery(rs.getString("expecteddelivery"));
                order.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
                
                map.put(Integer.toString(rs.getInt("order_id")),order);
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
        System.out.println("<<selectAllOrdersForUser");
        return map;
    }
    
    public static saxParser.Product getProduct(int productId){
        Connection conn = getConnection();
        Statement stmt = null;
        saxParser.Product product = new saxParser.Product();
        try{
            System.out.println("getProduct>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table where product_id = "+productId+";";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setDescription(rs.getString("description"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
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
        System.out.println("<<getProduct");
        return product;
    }
    
    public static saxParser.Product getProductByProductName(String productName){
        Connection conn = getConnection();
        Statement stmt = null;
        saxParser.Product product = new saxParser.Product();
        try{
            System.out.println("getProductByProductName>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table where product_name = \""+productName+"\" LIMIT 1;";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDate(rs.getDate("ordered_date"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);
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
        System.out.println("<<getProductByProductName");
        return product;
    }
    
    
    public static HashMap<String, saxParser.Product> selectAllProduct(){
        Connection conn = getConnection();
        Statement stmt = null;
        HashMap<String, saxParser.Product> map = new HashMap<String, saxParser.Product>();
        try{
            System.out.println("selectAllProduct>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from product_table;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                saxParser.Product product = new saxParser.Product();
                int product_id  = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String user_id = rs.getString("user_id");
                String retailer = rs.getString("retailer");
                String image = rs.getString("image");
                String category = rs.getString("category");
                String user_name = rs.getString("username");
                String price = rs.getString("price");
                
                // product.setOrderId(Integer.toString(rs.getInt("order_id")));
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setUserId(rs.getInt("user_id"));
                product.setRetailer(rs.getString("retailer"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setUsername(rs.getString("username"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setTotal(rs.getInt("total"));
                product.setOrderStatus(rs.getString("orderStatus"));
                product.setExpectedDelivery(rs.getString("expecteddelivery"));
                product.setDate(rs.getDate("ordered_date"));
                product.setDescription(rs.getString("description"));
                
                System.out.print("product_id: " + product_id);
                System.out.print(", user_id: " + user_id);
                System.out.print(", retailer: " + retailer);
                System.out.println(", product_name: " + product_name);
                System.out.println(", image: " + image);
                System.out.println(", category: " + category);
                System.out.println(", user_name: " + user_name);
                System.out.println(", price: " + price);

                
                map.put(rs.getString("product_id"),product);
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
        System.out.println("<<selectAllProduct");
        return map;
    }
    
    
    // Adding a User //
    public static void addUser(String userName,ArrayList userDetails){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addUser>>");
            stmt = conn.createStatement();
            String sql = null;
            sql = "INSERT INTO user (user_name, password, phone_number, email, type_of_user) "+
            "VALUES ('"+userName+"','"+userDetails.get(0)+"','"+userDetails.get(2)+"','"+userDetails.get(1)+"','"+userDetails.get(3)+"');";
            System.out.println("addUser>> sql :"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addUser");
    }
    
    // get User Id by name and password //
    public static int getUserId(String userName, String password){
        
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            System.out.println("getUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select user_id from user where user_name = \""+userName+"\" and password = \""+password+"\";";
            System.out.println("sql:"+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs != null){
                    System.out.println("<<getUser");
                    return rs.getInt("user_id");
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
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
                return 0;
            }
        }
        System.out.println("<<getUser");
        return 0;
    }
    
    
    // get User Id by name and password //
    public static int getUserIdByUserName(String userName){
        
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            System.out.println("getUserIdByUserName>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select user_id from user where user_name = \""+userName+"\";";
            System.out.println("sql:"+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs != null){
                    System.out.println("<<getUserIdByUserName");
                    return rs.getInt("user_id");
                }
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
        System.out.println("<<getUserIdByUserName");
        return 0;
    }
    
    // get type of user by name and password //
    public static String getTypeOfUser(String userName, String password){
        
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            System.out.println("getTypeOfUser>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select type_of_user from user where user_name = \""+userName+"\" and password = \""+password+"\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs != null){
                    System.out.println("<<getTypeOfUser");
                    return rs.getString("type_of_user");
                }
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
        System.out.println("<<getTypeOfUser");
        return "";
    }
    
    // get type of user by name//
    public static String getTypeOfUserByUserName(String userName){
        
        Connection conn = getConnection();
        Statement stmt = null;
        try{
            System.out.println("getTypeOfUserByUserName>>");
            stmt = conn.createStatement();
            String sql;
            sql = "select type_of_user from user where user_name = \""+userName+"\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs != null){
                    System.out.println("<<getTypeOfUserByUserName");
                    return rs.getString("type_of_user");
                }
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
        System.out.println("<<getTypeOfUserByUserName");
        return "";
    }
    
    
    // check If user exists //
    public static boolean checkIfUserExists(String userName, String password){
        if(getUserId(userName,password) != 0){
            return true;
        }
        return false;
    }
    
    // Adding Order //
    public static void addOrder(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addOrder>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            System.out.println("addOrder>> userId:"+userId);
            sql = "INSERT INTO order_table (product_id, user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+product.getId()+"','"+userId+"','"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            System.out.println("addOrder>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addOrder");
    }



// Adding Order With Return ID//
    public static int addOrderWithReturnId(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        int id = 0;
        
        try{
            System.out.println("addOrderWithReturnId>>");
            stmt = conn.createStatement();
            String sql = null;

            //get User Id//
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            System.out.println("addOrderWithReturnId>> userId:"+userId);

            //Insert into order_table//
            sql = "INSERT INTO order_table (product_id, user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+product.getId()+"','"+userId+"','"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            System.out.println("addOrderWithReturnId>>sql:"+sql);
            stmt.executeUpdate(sql);

            //return id//
            sql = "select LAST_INSERT_ID();";
            ResultSet rs = stmt.executeQuery(sql);
              while(rs.next()){
                System.out.println("<<insertOrderPayment : "+rs);
                 id  = rs.getInt(1);
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
        System.out.println("<<addOrderWithReturnId");
        return id;
    }

    // Adding Order //
    public static void orderwithPayment(saxParser.Product product, saxParser.CardDetails cardDetails){
        
        System.out.println("orderwithPayment>> card type :"+cardDetails.getCardType());
        try{
            
            //Insert into order table and Get order id//
            int orderId = addOrderWithReturnId(product);

            //Insert into Card Details and Get card id//
            int cdId = insertCardDetails(cardDetails);

            //Insert into order_payment//
            insertOrderPayment(orderId, cdId);

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("<<orderwithPayment");
    }


    // Insert Card Details //
    public static int insertCardDetails(saxParser.CardDetails cardDetails){
        
        Connection conn = getConnection();
        Statement stmt = null;
        int id = 0;
        
        try{
            System.out.println("insertCardDetails>>");
            stmt = conn.createStatement();
            String sql = null;
            sql = "INSERT INTO card_details (cardType, cardNumber, expYear, expMonth, lastModified, extraInfo, cvv, name, billing_address) "+
            "VALUES ('"+cardDetails.getCardType()+"','"+cardDetails.getCardNumber()+"','"+cardDetails.getExpYear()+"','"+cardDetails.getExpMonth()+"',CURDATE(),'"+cardDetails.getExtraInfo()+"','"+cardDetails.getCvv()+"','"+cardDetails.getNameOnCard()+"','"+cardDetails.getBa()+"');";
            System.out.println("insertCardDetails>>sql:"+sql);
            stmt.executeUpdate(sql);


            //return id//
            sql = "select LAST_INSERT_ID();";
            ResultSet rs = stmt.executeQuery(sql);
              while(rs.next()){
                System.out.println("<<insertOrderPayment : "+rs);
                 id  = rs.getInt(1);
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
        System.out.println("<<insertCardDetails");
        return id;
    }
    

    // Insert Card Details //
    public static void insertOrderPayment(int orderId, int creditCardID){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("insertOrderPayment>>");
            stmt = conn.createStatement();
            String sql = null;
            sql = "INSERT INTO order_payment (creditCardID, order_id, extraInfo) "+
            "VALUES ('"+creditCardID+"','"+orderId+"', 'None');";
            System.out.println("insertOrderPayment>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<insertOrderPayment");
    }

    

    // Adding Product //
    public static void addProduct(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addProduct>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            System.out.println("addProduct>> userId:"+userId);
            sql = "INSERT INTO product_table (user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ("+userId+",'"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            System.out.println("addProduct>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addProduct");
    }
    
    public static void addProductInitally(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addProduct>>");
            stmt = conn.createStatement();
            String sql = null;
            // int userId = 0;
            // userId = getUserIdByUserName(product.getUsername());
            // System.out.println("addProduct>> userId:"+userId);
            sql = "INSERT INTO product_table (user_id, product_name, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES (4,'"+product.getName()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            System.out.println("addProduct>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addProduct");
    }


    public static void addRoomInitally(saxParser.Room room){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addRoomInitally>>");
            stmt = conn.createStatement();
            String sql = null;
            // int userId = 0;
            // userId = getUserIdByUserName(product.getUsername());
            // System.out.println("addProduct>> userId:"+userId);
            sql = "INSERT INTO Room (ImageURL, Price, RoomType, RoomQuantity, RoomDescription) "+
            "VALUES ('"+room.getImageURL()+"',"+room.getPrice()+",'"+room.getRoomType()+"',"+room.getRoomQuantity()+",'"+room.getRoomDescription()+"');";
            System.out.println("addProduct>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addRoomInitally");
    }
    
    // Adding Cart //
    public static void addCart(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("addCart>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            System.out.println("addCart>> userId:"+userId);
            sql = "INSERT INTO cart_table (product_id, user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+product.getId()+"','"+userId+"','"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            System.out.println("addCart>>sql:"+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<addCart");
    }
    
    // Delete Order //
    public static void deleteOrder(String orderId, String userName){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteOrder>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(userName);
            deleteOrderPaymentForOrder(orderId);
            sql = "delete from order_table where order_id = "+orderId+" and user_id = "+userId+";";
            System.out.println("deleteOrder>> sql: "+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteOrder");
    }


    // Delete Order Payment for the order  //
    public static void deleteOrderPaymentForOrder(String orderId){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteOrderPaymentForOrder>>");
            stmt = conn.createStatement();
            String sql = null;
            sql = "delete from order_payment where order_id = "+orderId+";";
            System.out.println("deleteOrderPaymentForOrder>> sql: "+sql);
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteOrderPaymentForOrder");
    }


    // Update Order Id in Card Details //
    public static void updateOrderIdInCardDetails(String orderId){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("updateOrderIdInCardDetails>>");
            stmt = conn.createStatement();
            String sql = null;
            int id = getMaxId(ORDER_TABLE_NAME, ORDER_TABLE_PK_COLUMN);
            sql = "update order_table set order_id = "+id+" where order_id = "+orderId+";";
            stmt.executeUpdate(sql);
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
        System.out.println("<<updateOrderIdInCardDetails");
    }

    // Get Max Id //
    public static int getMaxId(String tableName, String tableColumn){
        
        Connection conn = getConnection();
        Statement stmt = null;
        int id = 0;
        
        try{
            System.out.println("getMaxId>>");
            stmt = conn.createStatement();
            String sql = null;

            //return id//
            sql = "select max("+tableColumn+") from "+tableName+";";
            ResultSet rs = stmt.executeQuery(sql);
              while(rs.next()){
                 id  = rs.getInt(1);
                 System.out.println("getMaxId>>id : "+id);
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
        System.out.println("<<getMaxId");
        return id;
    }


    // Delete Order //
    public static void deleteOrderSalesmen(String orderId){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteOrder>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            //userId = getUserIdByUserName(userName);
            deleteOrderPaymentForOrder(orderId);
            sql = "delete from order_table where order_id = "+orderId+";";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteOrder");
    }
    
    public static void deleteProduct(String productId, String userName){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteProduct>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(userName);
            sql = "delete from product_table where product_id = "+productId+";";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteProduct");
    }
    
    
    
    
    public static void deleteCart(String productId, String userName){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteCart>>");
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(userName);
            sql = "delete from cart_table where product_id = "+productId+" and user_id = "+userId+";";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteCart");
    }
    
    // Modify Order //
    public static void modifyOrder(saxParser.Order order){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("modifyOrder>>");
            deleteOrder(order.getOrderId(),order.getUsername());
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            System.out.println("order.getDate()>> : "+order.getDate());
            userId = getUserIdByUserName(order.getUsername());
            sql = "INSERT INTO order_table (product_id, user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+order.getId()+"','"+userId+"','"+order.getName()+"','"+order.getDescription()+"','"+order.getRetailer()+"','"+order.getImage()+"','"+order.getCategory()+"',"+order.getPrice()+","+order.getQuantity()+","+order.getTotal()+",'"+order.getUsername()+"','"+order.getOrderStatus()+"','"+order.getExpectedDelivery()+"','"+order.getDate()+"');";
            stmt.executeUpdate(sql);
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
        System.out.println("<<modifyOrder");
    }
    
    public static void modifyProduct(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("modifyProduct>>");
            deleteProduct(product.getId(),product.getUsername());
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            sql = "INSERT INTO product_table (user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+userId+"','"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','"+product.getImage()+"','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            stmt.executeUpdate(sql);
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
        System.out.println("<<modifyProduct");
    }
    
    public static void modifyCart(saxParser.Product product){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("modifyCart>>");
            deleteOrder(product.getId(),product.getUsername());
            stmt = conn.createStatement();
            String sql = null;
            int userId = 0;
            userId = getUserIdByUserName(product.getUsername());
            sql = "INSERT INTO cart_table (product_id, user_id, product_name, description, retailer, image, category, price, quantity, total, username, orderStatus, expecteddelivery, ordered_date) "+
            "VALUES ('"+product.getId()+"','"+userId+"','"+product.getName()+"','"+product.getDescription()+"','"+product.getRetailer()+"','product','"+product.getCategory()+"',"+product.getPrice()+","+product.getQuantity()+","+product.getTotal()+",'"+product.getUsername()+"','Pending.....','14 days',CURDATE());";
            stmt.executeUpdate(sql);
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
        System.out.println("<<modifyCart");
    }
    
    // Get Order Status //
    public static String getOrderStatusByDate(Date date){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        int diff = getDifferenceInDates(dt, date);
        if(diff > 14){
            return "Delivered";
        }else if(diff > 9){
            return "Shipped";
        }else{
            return "Pending.....";
        }
    }
    
    
    public static void deleteAllOrders(){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteAllCart>>");
            stmt = conn.createStatement();
            String sql = null;
            
            sql = "delete from order_table;";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteAllCart");
    }
    
    
    public static void deleteAllCarts(){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteAllCart>>");
            stmt = conn.createStatement();
            String sql = null;
            
            sql = "delete from cart_table;";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteAllCart");
    }
    
    public static void deleteAllProducts(){
        
        Connection conn = getConnection();
        Statement stmt = null;
        
        try{
            System.out.println("deleteAllProduct>>");
            stmt = conn.createStatement();
            String sql = null;
            
            sql = "delete from product_table;";
            stmt.executeUpdate(sql);
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
        System.out.println("<<deleteAllProduct");
    }
    
    // Get Expected Delivery//
    // public static void getExpectedDelivery(Date date){
    
    // }
    
    // Get Expected Delivery//
    public static int getDifferenceInDates(Date date1, Date date2){
        return (int)( (date1.getTime() - date2.getTime())
                     / (1000 * 60 * 60 * 24) );
    }
    
    
    
}

