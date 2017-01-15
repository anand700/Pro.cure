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
System.out.println("LoginValidate>>doPost ");
String name=request.getParameter("username");
String password=request.getParameter("password");
String typeOfUser= null;
int userId = 0;
boolean isValid = false;
ServletContext sc = request.getSession().getServletContext();
try {
    
    if(MySQLDataStoreUtilities.checkIfUserExists(name,password)){
        typeOfUser = MySQLDataStoreUtilities.getTypeOfUser(name,password);
        userId = MySQLDataStoreUtilities.getUserId(name,password);
        isValid = true;
    }

    
    
    if(isValid){
        System.out.println("LoginValidate>>name:"+name);
        
        HttpSession session=request.getSession();
        session.setAttribute("username",name);
        session.setAttribute("userId",Integer.toString(userId));
        request.setAttribute("typeOfUser", typeOfUser);
        RequestDispatcher rd;
        System.out.println("LoginValidate>>typeOfUser: "+typeOfUser);
        if(typeOfUser.trim().equalsIgnoreCase("Customer")){
            request.getServletContext().getRequestDispatcher("/jsp/customer/HomePage.jsp").forward(request, response);
        }else if(typeOfUser.trim().equalsIgnoreCase("Salesmen")){
            request.getServletContext().getRequestDispatcher("/jsp/salesmen/HomePageSalesmen.jsp").forward(request, response);
        }else if(typeOfUser.trim().equalsIgnoreCase("Admin")){
            request.getServletContext().getRequestDispatcher("/jsp/admin/HomePageAdmin.jsp").forward(request, response);
        }
        
    } else {
        request.setAttribute("ERROR", "Please Enter valid Username and Password");
        request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
    }
} catch (Exception e) {
    System.out.println("LoginValidate>>Exception occured: ");
}


%>
