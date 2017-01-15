<!doctype html public \"-//w3c//dtd html 4.0 " +
"transitional//en\">
<%@ page session="false" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="saxParser.*" %>
<link href="/csj/css/bootstrap.css" rel="stylesheet">
<link href="/csj/css/freelancer.css" rel="stylesheet">
<link href="/csj/css/normalize.css" rel="stylesheet">
<link href="/csj/css/skeleton.css" rel="stylesheet">
<link href="/csj/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
<!-- <link href="/csj/css/simple-sidebar.css" rel="stylesheet"> -->

<%

HttpSession session=request.getSession();
Object username = session.getAttribute("username");
Object userId = session.getAttribute("userId");
session.setAttribute("userId",(String)userId);
if(null == (String)username){
    request.setAttribute("ERROR", "Session timed out.");
    request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
}else{
    saxParser.Product pFromGet = null;
    String userName = request.getParameter("username");
    String quantity = request.getParameter("quantity");
    String productId = request.getParameter("id");
System.out.println("getProduct().id : "+productId);
    try {
         pFromGet = MySQLDataStoreUtilities.getProduct(Integer.parseInt(productId));
    } catch (Exception e) {
        System.out.println("AddProductOrder>>Write to serializable>>Exception occured in getProduct()");
    }

    String name = pFromGet.getName();
    String retailer = pFromGet.getRetailer();
    String category = pFromGet.getCategory();
    int price = pFromGet.getPrice(); 
    
    int total = price * Integer.parseInt(quantity);

    
    saxParser.Product p= new saxParser.Product();
    //p.setId(Integer.toString(maxId+1));
    p.setName(name);
    p.setRetailer(retailer);
    p.setCategory(category);
    p.setPrice(price);
    p.setTotal(total);
    p.setUsername(userName);
    p.setId(productId);
    p.setQuantity(Integer.parseInt(quantity));

    try {
        MySQLDataStoreUtilities.addOrder(p);
    } catch (Exception e) {
        System.out.println("AddProductOrder>>Write to serializable>>Exception occured");
    }
    //<<Write to serializable file
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/salesmen/HomePageSalesmen.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/salesmen/HomePageSalesmen.jsp");
    //<<Redirect to salesmen homepage
}
%>
