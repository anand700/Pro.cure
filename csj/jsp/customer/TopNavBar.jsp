
<!doctype html public \"-//w3c//dtd html 4.0 " +
"transitional//en\">
<%@ page session="false" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="saxParser.*" %>
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

    <a class="navbar-brand" href="#page-top"><%=session.getAttribute("username")%></a>

</div>
<!-- <img src="image1.JPG" class="nav navbar-nav navbar-left img img-circle " height="90" width="90"></img> -->
<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav navbar-right">
<!-- <li class="page-scroll">
<a href="/csj/jsp/customer/HomePageCustomer.jsp">Home</a>
</li> -->
<li class="page-scroll">
<a href="/csj/jsp/customer/HomePage.jsp">Home</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/customer/ListCart.jsp">Cart</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/customer/ListOrder.jsp">Order History</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/customer/DealMatches.jsp">Deal Matches</a>
</li>
<li class="page-scroll">
<a href="/csj/jsp/Login.jsp">Sign Out</a>
</li>
<li class="page-scroll">
<body onload="init()">
<script type="text/javascript" src="/csj/js/javascript.js"></script>
<div name="autofillform">
<input type="text" name="searchId" value="" class="input" id=searchId onkeyup="doCompletion()" placeholder="search Product here.." style="padding: 5px; height:22.5px; margin-top:20px; font-size: 11px;" />
<div id="auto-row">
<table id="complete-table" style=" position: absolute; width: 157px;"></table>
</div> </div>
</body>
</li>
</ul>
</div>
<!-- /.navbar-collapse -->
</div>
<!-- /.container-fluid -->
</nav>
