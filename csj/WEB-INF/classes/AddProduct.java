// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import saxParser.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

// Extend HttpServlet class
public class AddProduct extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
{
PrintWriter out = response.getWriter();
out.println("<link href=\"/csj/css/bootstrap.min.css\" rel=\"stylesheet\">");
out.println("<link href=\"/csj/css/freelancer.css\" rel=\"stylesheet\">");
out.println("<link href=\"/csj/css/normalize.css\" rel=\"stylesheet\">");
out.println("<link href=\"/csj/css/skeleton.css\" rel=\"stylesheet\">");
out.println("<link href=\"/csj/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
out.println("<link href=\"http://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
out.println("<link href=\"http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

HttpSession session=request.getSession();
Object username = session.getAttribute("username");
Object userId = session.getAttribute("userId");
session.setAttribute("userId",(String)userId);
if(null == (String)username){
    request.setAttribute("ERROR", "Session timed out.");
    request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
}else{
    
    String name = request.getParameter("name");
    String retailer = request.getParameter("retailer");
    String category = request.getParameter("category");
    String price = request.getParameter("price");
    String image = request.getParameter("image");

    
    //>>Read
    HashMap<String,List<saxParser.Product>> readProduct = new HashMap<String,List<saxParser.Product>>();
    ServletContext scr = request.getSession().getServletContext();
    try {
        File productsr=new File(scr.getRealPath("Products"));
        FileInputStream isr = new FileInputStream(productsr);
        ObjectInputStream oswr = new ObjectInputStream(isr);
        System.out.println("AddProduct:read products1");
        readProduct=(HashMap<String,List<saxParser.Product>>)oswr.readObject();
        System.out.println("AddProduct:read products2"+readProduct);
        oswr.close();
        isr.close();
    } catch (Exception e) {
        System.out.println("AddProduct>>Read serializable file>>Exception occured");
    }
    //<<Read
    //int maxId = 0;
    //for(Map.Entry<String,List<saxParser.Product>> m :readProduct.entrySet()){
    // int i = 0;
    //while(i< m.getValue().size()){
    //if(Integer.parseInt(m.getValue().get(i).getId()) > maxId){
    //maxId = Integer.parseInt(m.getValue().get(i).getId());
    //}
    //i++;
    //}
    //}
    System.out.println("AddProduct:Adding");
    
    
    //>>Add
    HashMap<String,List<saxParser.Product>> tempMap = new HashMap<String,List<saxParser.Product>>();
    
    List<saxParser.Product> tempList = new ArrayList<saxParser.Product>();
    //for(Map.Entry<String,List<saxParser.Product>> mm :readProduct.entrySet()){
    //System.out.println("AddProduct:Adding3");
    //int j = 0;
    //while(j < ((String)mm.getKey()).length()){
    //if(mm.getKey().equals("Electronic Products")){
    //System.out.println("AddProduct:Adding4:j:"+j);
    //tempList = mm.getValue();
    //}
    //j++;
    //}
    //}
    
    System.out.println("AddProduct:Adding1");
    saxParser.Product p= new saxParser.Product();
    //p.setId(Integer.toString(maxId+1));
    p.setName(name);
    p.setRetailer(retailer);
    p.setCategory(category);
    p.setPrice(Integer.parseInt(price));
    p.setUsername((String)username);
    tempList.add(p);
    
    tempMap.put("Electronic Products",tempList);
    //<<Add
    System.out.println("AddProduct:writing");
    
    //>>Write to serializable file
    ServletContext scw = request.getSession().getServletContext();
    try {
        MySQLDataStoreUtilities.addProduct(p);
        File productsw=new File(scw.getRealPath("Products"));
        System.out.println("AddProduct:writing1");
        
        FileOutputStream isw = new FileOutputStream(productsw);
        ObjectOutputStream osww = new ObjectOutputStream(isw);
        System.out.println("AddProduct:writing2");
        
        osww.writeObject(tempMap);
        System.out.println("AddProduct:writing3");
        
        osww.close();
        isw.close();
    } catch (Exception e) {
        System.out.println("AddProduct>>Write to serializable>>Exception occured");
    }
    //<<Write to serializable file
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/admin/HomePageAdmin.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/admin/HomePageAdmin.jsp");
    //<<Redirect to salesmen homepage
}
}
}
