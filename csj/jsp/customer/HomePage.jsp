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
    out.println("<title> Touch Of Modern </title>");
    out.println("<link rel=stylesheet href=styles.css type=text/css />");
    
    //out.println("<script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script>");
    
    out.println("<meta name=viewport content=width=device-width, minimum-scale=1.0, maximum-scale=1.0 />");
    out.println("</head>");
    out.println("<body>");
    
    
    %>
    <jsp:include page='TopNavBar.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
    
    
    <%
    
    out.println("<center>");
    
    String filter = null;
    if(null != request.getParameter("filter")){
        filter = request.getParameter("filter");
    }else{
        filter = null;
    }
    
    String filterByProductName = null;
    if(null != request.getParameter("filterByProductName")){
        filterByProductName = request.getParameter("filterByProductName");
    }else{
        filterByProductName = null;
    }
    
    
    //Get Product Code>>
    HashMap<String, saxParser.Product>  map = new HashMap<String, saxParser.Product> ();
    HashMap<String, saxParser.Product>  accessoryMap = new HashMap<String, saxParser.Product> ();
    try {
        if(null != filterByProductName){
            map = MySQLDataStoreUtilities.selectAllProductsByName(filterByProductName);
        }else if(null != filter){
            map = MySQLDataStoreUtilities.selectAllProductsWithFilter(filter);
        }else{
            map = MySQLDataStoreUtilities.selectAllProduct();
        }
        accessoryMap = MySQLDataStoreUtilities.selectAllProductsWithFilterAsAccesory();
        
    } catch (Exception e) {
        System.out.println("HomePage>>Read From Database>>Exception occured");
    }

        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
        out.println("<br>");
    
    for(Map.Entry<String,saxParser.Product> m :map.entrySet()){
        //out.println("<a href=\"/csj/jsp/customer/AddOrder.jsp?Order='"+m.getValue().getId()+";"+session.getAttribute("username")+"'>");
        out.println("<div class=\"responsive\">");
        out.println("<form action=\"/csj/jsp/customer/AddOrder.jsp\">");
        out.println("<div class=\"img\"><img src="+m.getValue().getImage()+" alt=\"No Image Uploaded\"></div>");
        out.println("<div class=\"desc\"><h5>" + m.getValue().getName() + "</h5>\n" );
        
        //out.println("<li> Category : "+m.getValue().getCategory()+"");out.println("</li>");
        //out.println("<li> Description : "+m.getValue().getDescription()+"");out.println("</li>");
        //out.println("<li> Retailer : "+m.getValue().getRetailer()+"");out.println("</li>");
        out.println("<li> Price : $"+m.getValue().getPrice()+"");out.println("</li>");
        
        
        out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Add value='"+m.getValue().getId()+";"+session.getAttribute("username")+"'>Add to Cart</button>");
        out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Order value='"+m.getValue().getId()+";"+session.getAttribute("username")+"'>Order</button>");
        out.println("<button class=\"btn btn-success btn-sm\" type=submit name=readReview value='"+m.getValue().getId()+";"+session.getAttribute("username")+"'>Read Review</button>");
        out.println("<button class=\"btn btn-success btn-sm\" type=submit name=writeReview value='"+m.getValue().getId()+";"+session.getAttribute("username")+"'>Write Review</button>");
        out.println("</div></div></form></div>");
        //out.println("</a>");
        
        
        //out.println("</ul>");

        // out.println("<hr>");
        
        
    }
    
    //out.println("</section>");
    out.println("</center>");
    out.println("</body></html>");
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
    
    session.setAttribute("username",(String)username);
}

%>
