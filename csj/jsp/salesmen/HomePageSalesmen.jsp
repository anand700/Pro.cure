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
    
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv=Content-Type content=text/html charset=utf-8 />");
    out.println("<title> Pro.cure </title>");
    
    //out.println("<script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script>");
    
    out.println("<meta name=viewport content=width=device-width, minimum-scale=1.0, maximum-scale=1.0 />");
    out.println("</head>");
    out.println("<body>");
    out.println("<div id=container>");
    out.println("<header>");
    out.println("<div class=width>");
    out.println("<h1>");
    System.out.println("Home page>>doGet ");
    
    System.out.println("HomePage>>: typeOfUser:"+request.getAttribute("typeOfUser"));
    out.println("</a>");
    out.println("</h1>");
    out.println("</div>");
    out.println("</header>");
    
    
    out.println("<div id=body class=width>");
    String filter = null;
    if(null != request.getParameter("filter")){
        filter = request.getParameter("filter");
    }else{
        filter = null;
    }
    
    out.println("<section id=content>");
    
    HashMap<String, saxParser.Order>  map = new HashMap<String, saxParser.Order> ();
    HashMap<String, saxParser.Order>  accessoryMap = new HashMap<String, saxParser.Order> ();
    try {
        
        if(null == filter){
            map = MySQLDataStoreUtilities.selectAllOrders();
            accessoryMap = MySQLDataStoreUtilities.selectAllOrdersWithFilterAsAccessory();
        }else{
            map = MySQLDataStoreUtilities.selectAllOrdersWithFilter(filter);
        }
        
    } catch (Exception e) {
        System.out.println("HomePage>>Read From Database>>Exception occured");
    }
    if (map.isEmpty()){
        out.println("<center><h3>No items in order history.</h3></center>");
        out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
    }else{
        for(Map.Entry<String,saxParser.Order> m :map.entrySet()){
            out.println("<center>");
            out.println("<ul>");
            out.println("<form action=\"/csj/jsp/salesmen/AddDeleteUpdateOrderSalesmen.jsp\" method=\"post\">");
            out.println("<h1>" + m.getValue().getName() + "</h1>\n" );
            
            out.println("<li> Category : "+m.getValue().getCategory()+"");out.println("</li>");
            out.println("<li> Retailer : "+m.getValue().getRetailer()+"");out.println("</li>");
            out.println("<li> Price : $"+m.getValue().getPrice()+"");out.println("</li>");
            out.println("<li>"+"Total: "+m.getValue().getTotal()+"");out.println("</li>");
            out.println("<li>"+"Username: "+m.getValue().getUsername()+"");out.println("</li>");
            out.println("<li>"+"OrderStatus: "+m.getValue().getOrderStatus()+"");out.println("</li>");
            out.println("<li>"+"Date Of Order: "+m.getValue().getDate()+"");out.println("</li>");
            out.println("<li>"+"ExpectedDelivery: "+m.getValue().getExpectedDelivery()+"");out.println("</li>");
            out.println("<button button class=\"btn btn-success btn-sm\" type=submit name=Update value="+m.getValue().getId()+">Update</button>");
            out.println("<button button class=\"btn btn-success btn-sm\" type=submit name=Delete value="+m.getValue().getId()+">Delete</button><br><br>");
            out.println("</form><br><br><br><br><br><br>");
            out.println("</center>");
            
            out.println("</ul>");
        }
    }
    
    
    //<<Get Product Code
    
    out.println("</section>");
    
    out.println("<div class=clear>");out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
out.println("</body>");
    out.println("</html>");
}
%>
