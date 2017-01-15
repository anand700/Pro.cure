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
    String[] idList = request.getParameter("id").split(",");
    String orderId = idList[1];
    String id = idList[0];
    String name = request.getParameter("name");
    String retailer = request.getParameter("retailer");
    String category = request.getParameter("category");
    String price = request.getParameter("price");
    String userName = request.getParameter("username");
    String quantity = request.getParameter("quantity");
    int total = Integer.parseInt(price) * Integer.parseInt(quantity);
    
    System.out.println("UpdateProductOrder:Adding1");
    saxParser.Order p= new saxParser.Order();
    p.setOrderId(orderId);
    p.setId(id);
    p.setName(name);
    p.setRetailer(retailer);
    p.setCategory(category);
    p.setPrice(Integer.parseInt(price));
    p.setQuantity(Integer.parseInt(quantity));
    p.setTotal(total);
    p.setUsername(userName);
    
    System.out.println("UpdateProductOrder:writing");
    
    try {
        MySQLDataStoreUtilities.modifyOrder(p);
    } catch (Exception e) {
        System.out.println("UpdateProductOrder>>Write to serializable>>Exception occured");
    }
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/salesmen/HomePageSalesmen.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/salesmen/HomePageSalesmen.jsp");
    //<<Redirect to salesmen homepage
}
%>
