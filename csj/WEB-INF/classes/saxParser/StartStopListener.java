package saxParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;
import java.io.*;
import saxParser.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebListener
public class StartStopListener implements ServletContextListener {
    // @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet has been started.>>");
        try{
            //For init
        }catch(Exception e){
            System.out.println("LoginValidate>>Exception occured: ");
        }
        System.out.println("Servlet has been started.<<");
    }
    
    // @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet has been stopped.>>");
        try{
            // MySQLDataStoreUtilities.deleteAllProducts();
            // MySQLDataStoreUtilities.deleteAllCarts();
            // MySQLDataStoreUtilities.deleteAllOrders();
            //    File products=new File("/Applications/tomcat/webapps/csj/Products");
            //    products.delete();
            //    File orders=new File("/Applications/tomcat/webapps/csj/Order1");
            //    orders.delete();
            //    File carts=new File("/Applications/tomcat/webapps/csj/Cart");
            //    carts.delete();
        }catch(Exception e){
            System.out.println("StartStopListner>>Exception occured: ");
        }
        System.out.println("Servlet has been stopped.<<");
    }
    
}
