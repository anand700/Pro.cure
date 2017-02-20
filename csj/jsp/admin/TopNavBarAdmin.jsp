
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
%>
<nav class="navbar navbar-default navbar-fixed-top">
<div class="container">
<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header page-scroll">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
<span class="sr-only">Toggle navigation</span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<%
if(null != request.getAttribute("typeOfUser")){
    %>
    <a class="navbar-brand" href="#page-top"><%=session.getAttribute("username")%></a>
    <%
}else{
    %>
    <a class="navbar-brand" href="#page-top">Pro.cure</a>
    <%
}
%>
</div>
<!-- <img src="image1.JPG" class="nav navbar-nav navbar-left img img-circle " height="90" width="90"></img> -->
<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav navbar-right">
<li class="hidden">
<a href="#page-top"></a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/admin/HomePageAdmin.jsp">Home(<%=(String)username%>)</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/admin/AddDeleteUpdateAdmin.jsp">Add Product</a>
</li>
<!--
<li class="page-scroll">
<a href="/csj/jsp/customer/ListOrder.jsp">Order History</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/customer/Filters.jsp">Filters</a>
</li> -->
<li class="page-scroll">
<a href="/csj/jsp/admin/Filters.jsp">Filters</a>
</li> 
<li class="page-scroll">
<a href="/csj/jsp/Login.jsp">Sign Out</a>
</li>
<!-- <li class="page-scroll">
<a href="#contact">Contact</a>
</li> -->
</ul>
</div>
<!-- /.navbar-collapse -->
</div>
<!-- /.container-fluid -->
</nav>
