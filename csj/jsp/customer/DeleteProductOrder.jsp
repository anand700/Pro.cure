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
    //String username = session.getAttribute("username");
    String[] idList = request.getParameter("id").split(",");
    String orderId = idList[0];
    String id = idList[1];
    String name = request.getParameter("name");
    String retailer = request.getParameter("retailer");
    String category = request.getParameter("category");
    String price = request.getParameter("price");

    System.out.println("DeleteProductOrder>>orderId : "+orderId);


    
    String typeOfUser = null;
    try {
        MySQLDataStoreUtilities.deleteOrder(orderId,(String)username);
        typeOfUser = MySQLDataStoreUtilities.getTypeOfUserByUserName((String)username);
    } catch (Exception e) {
        System.out.println("DeleteProductOrder>>Exception occured");
    }
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    if(typeOfUser.trim().equalsIgnoreCase("Customer")){
        //request.getRequestDispatcher("/jsp/customer/ListOrder.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() +"/jsp/customer/ListOrder.jsp");
    }else if(typeOfUser.trim().equalsIgnoreCase("Admin")){
        //request.getRequestDispatcher("/jsp/salesmen/HomePageSalesmen.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() +"/jsp/salesmen/HomePageSalesmen.jsp");
    }
    //<<Redirect to salesmen homepage
}
%>
