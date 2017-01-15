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
<jsp:include page='TopNavBar.jsp'>
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
    
    String update = request.getParameter("Update");
    String delete = request.getParameter("Delete");
    
    if(null == update && null == delete){
        System.out.println("Add");
        String title = "Add Order";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
        "<html>" +
        "<link rel=stylesheet href=styles.css type=text/css /\n>" +
        "<head>" +
        "<meta http-equiv=Content-Type content=text/html charset=utf-8 />" +
        "<title> "+title+" </title>" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n" +
        "<form action=/csj/jsp/customer/AddProductOrder.jsp> " +
        "<ul>\n" +
        "<center>" +
        "  <input type=text name=name placeholder=\"Enter the Name\"><br>\n" +
        "  <input type=text name=retailer placeholder=\"Enter the Retailer\"><br>\n" +
        "  <input type=text name=category placeholder=\"Enter the Category\"><br>\n" +
        "  <input type=text name=price placeholder=\"Enter the Price\"><br>\n" +
        "  <input type=text name=quantity placeholder=\"Enter the Quantity\" ><br>\n" +
        "  <input type=text name=username placeholder=\"Enter the Username\"><br>\n" +
        "  <input type=submit value=Submit>\n" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
        
    }else if(null != update){
        System.out.println("Update");
        String title = "Update Order";
        
        saxParser.Order p1 = new saxParser.Order();
        try{
            p1 = MySQLDataStoreUtilities.getOrder1(Integer.parseInt(update), Integer.parseInt((String)userId));
            System.out.println("p1"+p1);
        }catch(Exception e){
            System.out.println("AddDeleteUpdate>>Read database>>Exception occured");
        }
        
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
        "<html><br><br><br><br><br><br><br><br>" +
        "<link rel=stylesheet href=styles.css type=text/css /\n>" +
        "<head>" +
        "<meta http-equiv=Content-Type content=text/html charset=utf-8 />" +
        "<title> "+title+" </title>" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n" +
        "<form action=/csj/jsp/customer/UpdateProductOrder.jsp > " +
        "<ul>\n" +
        "<center>" +
        "  <input  type=text name=id value='"+p1.getId()+","+p1.getOrderId()+"' readonly><br>\n" +
        "  <input  type=text name=name value='"+p1.getName()+"'><br>\n" +
        "  <input  type=text name=retailer value='"+p1.getRetailer()+"'><br>\n" +
        "  <input  type=text name=category value='"+p1.getCategory()+"'><br>\n" +
        "  <input  type=text name=price value='"+p1.getPrice()+"'><br>\n" +
        "  <input  type=text name=quantity value='"+p1.getQuantity()+"' ><br>\n" +
        "  <input type=text name=username value='"+(String)username+"' readonly><br>\n" +
        "  <input type=submit value=Confirm>\n" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
        
    }else if(null != delete){
        System.out.println("Delete");
        String title = "Delete or Cancel Order";
        
        saxParser.Order p1 = new saxParser.Order();
        try{
            p1 = MySQLDataStoreUtilities.getOrder1(Integer.parseInt(delete), Integer.parseInt((String)userId));
            System.out.println("p1"+p1.getQuantity());
        }catch(Exception e){
            System.out.println("AddDeleteUpdateOrder>>Read database>>Exception occured");
        }
        
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
        "<html><br><br><br><br><br><br><br><br><br>" +
        "<link rel=stylesheet href=styles.css type=text/css /\n>" +
        "<head>" +
        "<meta http-equiv=Content-Type content=text/html charset=utf-8 />" +
        "<title> "+title+" </title>" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h3 align=\"center\">" + title + "</h3>\n" +
        "<form action=/csj/jsp/customer/DeleteProductOrder.jsp > " +
        "<ul>\n" +
        "<center>" +
        "<img style=\"margin-left:5%;float:left;width:35%;height: 40%;border:1px solid #2c3e50;\" src="+p1.getImage()+" alt=\"No Image Uploaded\" />"+
        "<div style=\"float:right;width: 60%;\">" +
        "  <b>Name</b> : "+p1.getName()+"<br>\n" +
        "  <b>Retailer</b> : "+p1.getRetailer()+"<br>\n" +
        "  <b>Category</b> : "+p1.getCategory()+" <br>\n" +
        "  <h7 style=\"color:red;\" ><b>Price</b> : $"+p1.getPrice()+"</h7><br>\n" +
         "</div>" +
        "  <b>Description</b> : "+p1.getDescription()+"<br>\n" +
         "  <button class=\"btn btn-success btn-sm\" type=submit name=id value='"+p1.getOrderId()+","+p1.getId()+"'>Confirm</button>\n" +
         "<div style=\"clear:both;\"></div>" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html><br><br><br><br><br><br><br><br><br><br>");
    }
    
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
    session.setAttribute("username",(String)username);
}
%>
