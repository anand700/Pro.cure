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
    
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String retailer = request.getParameter("retailer");
    String category = request.getParameter("category");
    String price = request.getParameter("price");
    String userName = request.getParameter("username");
    String description = request.getParameter("description");
    String quantity = request.getParameter("quantity");

    
    saxParser.Product p = new saxParser.Product();
    try{
        System.out.println("id ****************: "+id);
        p = MySQLDataStoreUtilities.getProduct(Integer.parseInt(id));
        System.out.println("username ****************: "+(String)username);
        int total = p.getPrice() * Integer.parseInt(quantity);
        p.setQuantity(Integer.parseInt(quantity));
        p.setTotal(total);
        p.setUsername((String)username);
        System.out.println("p ****************: "+p.getId());
    }catch(Exception e){
        System.out.println("AddDeleteUpdate>>Read database>>Exception occured");
    }
    
    try {
        MySQLDataStoreUtilities.addCart(p);
    } catch (Exception e) {
        System.out.println("AddCart>>Write to serializable>>Exception occured");
    }
    
    
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);    
    //request.getRequestDispatcher("/jsp/customer/ListCart.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/customer/ListCart.jsp");
    //<<Redirect to salesmen homepage
}
%>
