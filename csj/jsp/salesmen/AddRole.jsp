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
<jsp:include page='TopNavBarSalesmen.jsp'>
<jsp:param name="articleId" value=""/>
</jsp:include>
<%

HttpSession session=request.getSession();
Object username = session.getAttribute("username");
Object userId = session.getAttribute("userId");
session.setAttribute("userId",(String)userId);
if(null == (String)username){
    request.setAttribute("ERROR", "Session timed out.");
    request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
}else{
    out.println("<html><br><br><br><br><br><br><br><br><h1><center>Add Role</center></h1>\n" );
    out.println(
    "<form action=/csj/jsp/Login.jsp> " +
    "<ul>\n" +
    "<center>" +
    "  <input  type=text name=username placeholder=\"Enter the User Name\"><br>\n" +
    "  <input type=text name=password placeholder=\"Enter the Password\"><br>\n" +
    "  <input type=text name=phonenum placeholder=\"Enter the Phone Number\"><br>\n" +
    "  <input type=text name=email placeholder=\"Enter the Email Address\"><br>\n" +
    "  <br><br>Select Type of User: <select class=form name=typeofuser>\n" +
    "  <option value=Customer>Customer</option>\n" +
    "  <option value=Salesmen>Salesmen</option>\n" +
    "  <option value=Admin>Admin</option><br>\n" +
    "  <select>\n" +
    "  <br><br><button class=\"btn btn-success btn-sm\"  type=submit value=Submit>Submit</button>\n" +
    "<center>" +
    "</ul>\n" +
    "</form></html>");
    out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
}
%>
