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
    out.println("<header>");
    out.println("<div class=width>");
    out.println("<h1>");
    System.out.println("Home page>>doGet ");
    
    System.out.println("HomePage>>: typeOfUser:"+request.getAttribute("typeOfUser"));
    out.println("</a>");
    out.println("</h1>");
    out.println("</div>");
    out.println("</header>");
    
    
    %>
    <jsp:include page='TopNavBar.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
    
    
    <%
    
    
    
    
    out.println("<div id=body class=width>");
    
    
    
    out.println("<section id=content>");
    out.println("<center>");
    out.println("<br><br><br>" );
    
    
    //>>Condition to check if i have to read from readproducts or hm
    // HashMap<String,List<saxParser.Product>> mainhm = new HashMap<String,List<saxParser.Product>>();
    // mainhm = readProduct;
    //<<Condition to check if i have to read from readproducts or hm
    String filter = null;
    if(null != request.getParameter("filter")){
        filter = request.getParameter("filter");
    }else{
        filter = null;
    }
    //Get Cart Code>>
    HashMap<String, saxParser.Cart>  map = new HashMap<String, saxParser.Cart> ();
    HashMap<String, saxParser.Cart>  accessoryMap = new HashMap<String, saxParser.Cart> ();
    try {
        
        if(null == filter){
            map = MySQLDataStoreUtilities.selectAllCartsForUser((String)userId);
        }else{
            map = MySQLDataStoreUtilities.selectAllCartsWithFilterForUser(filter,(String)userId);
        }
        
    } catch (Exception e) {
        System.out.println("HomePage>>Read From Database>>Exception occured");
    }
    
    if (map.isEmpty()){
        out.println("<h3 style=\"color:#cc0000;\">No items in cart.</h3>");
        out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
    }else{
        for(Map.Entry<String,saxParser.Cart> m :map.entrySet()){
            
            out.println("<ul>");
            out.println("<form action=\"/csj/jsp/customer/DeleteOrOrderFromCart.jsp\">");
            out.println("<h1>" + m.getValue().getName() + "</h1>\n" );
            
            out.println("<li> Category : "+m.getValue().getCategory()+"");out.println("</li>");
            //out.println("<li> Product Id : "+m.getValue().getId()+"");out.println("</li>");
            out.println("<li> Retailer : "+m.getValue().getRetailer()+"");out.println("</li>");
            out.println("<li> Price : $"+m.getValue().getPrice()+"");out.println("</li>");
            out.println("<li>"+"Quantity: "+m.getValue().getQuantity()+"");out.println("</li>");
            out.println("<li>"+"Total: "+m.getValue().getTotal()+"");out.println("</li>");
            out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Delete value="+m.getValue().getId()+">Remove from Cart</button>");
            out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Order value="+m.getValue().getId()+">Order</button><br><br>");
            out.println("</form><br><br>");
            
            out.println("</ul>");
            out.println("<br>");
            //out.println("<hr>");
        }
        out.println("<br><br><br><br><br>");
    }
    //<<Get Cart Code
    out.println("</center>");
    out.println("</section>");
    
    out.println("<div class=clear>");out.println("</div>");
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
