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
<jsp:include page='TopNavBarAdmin.jsp'>
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
    out.println("<title> Touch Of Modern </title>");
    out.println("<link rel=stylesheet href=styles.css type=text/css />");
    
    //out.println("<script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script>");
    
    out.println("<meta name=viewport content=width=device-width, minimum-scale=1.0, maximum-scale=1.0 />");
    out.println("</head>");
    out.println("<body>");
    out.println("<div id=container>");
    
    
    
    out.println("<div id=body>");
    
    
    out.println("<center><br><br>");
    out.println("<section id=content>");
    String filter = null;
    if(null != request.getParameter("filter")){
        filter = request.getParameter("filter");
    }else{
        filter = null;
    }
    
    
    
    //Get Product Code>>
    HashMap<String, saxParser.Product>  map = new HashMap<String, saxParser.Product> ();
    HashMap<String, saxParser.Product>  accessoryMap = new HashMap<String, saxParser.Product> ();
    try {
        
        if(null == filter){
            map = MySQLDataStoreUtilities.selectAllProduct();
            accessoryMap = MySQLDataStoreUtilities.selectAllProductsWithFilterAsAccesory();
        }else{
            map = MySQLDataStoreUtilities.selectAllProductsWithFilter(filter);
        }
        
    } catch (Exception e) {
        System.out.println("HomePage>>Read From Database>>Exception occured");
    }
    
    
    for(Map.Entry<String,saxParser.Product> m :map.entrySet()){
        out.println("<ul>");
        out.println("<form action=\"/csj/jsp/admin/AddDeleteUpdateAdmin.jsp\">");
        out.println("<h1>" + m.getValue().getName() + "</h1>\n" );
        
        out.println(" Category : "+m.getValue().getCategory()+"");out.println("<br>");
        out.println(" Retailer : "+m.getValue().getRetailer()+"");out.println("<br>");
        out.println(" Price : $"+m.getValue().getPrice()+"");out.println("<br>");
        out.println("<button type=submit name=Update value='"+m.getValue().getId()+"'>Update</button>");
        out.println("<button type=submit name=Delete value='"+m.getValue().getId()+"'>Delete</button><br><br>");
        out.println("</form><br><br>");
        out.println("</ul>");
        out.println("<br>");
        //out.println("<hr>");
    }

    
    out.println("</section>");
    out.println("</center>");
    out.println("</div>");
    out.println("</div>");
    out.println("</body>");
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
    out.println("</html>");
    session.setAttribute("username",(String)username);
}
%>
