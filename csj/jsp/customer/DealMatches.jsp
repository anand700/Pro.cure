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
    out.println("<title> Pro.cure </title>");
    out.println("<link rel=stylesheet href=styles.css type=text/css />");
    
    //out.println("<script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script>");
    
    out.println("<meta name=viewport content=width=device-width, minimum-scale=1.0, maximum-scale=1.0 />");
    out.println("</head>");
    out.println("<body>");
    out.println("<div id=container>");
    out.println("<header>");
    out.println("<div>");
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
    
    
    out.println("<section id=content>");
    
    out.println("<div id=body>");
    out.println("<br>");
    out.println("<br>");
    out.println("<br>");
    
    out.println("<center>" +
    "<h3>Price Match Guaranteed</h3>\n");
    
    //Deal Matches//
    try{
        FileInputStream fstream = new FileInputStream("/Applications/tomcat/webapps/csj/python/DealMatches.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        strLine = br.readLine();
        if (strLine == null || (strLine.length() == 0 && strLine == null)) {
            out.println("No offers found!");
        }else{
            do{
                out.println("<li>"+strLine+"</li>");
            }while((strLine = br.readLine()) != null);
        }
        //Close the input stream
        br.close();
    }catch(Exception e){
        System.out.println("Exception e:"+e);
    }
    
    
    out.println("<hr>");
    
    //Product Matches//
    out.println("<h3>Deal Matches</h3>\n");
    
    
    try{
        FileInputStream fstream = new FileInputStream("/Applications/tomcat/webapps/csj/python/ProductMatches.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        strLine = br.readLine();
        if (strLine == null || (strLine.length() == 0 && strLine == null)) {
            out.println("No offers found!");
        }else{
            do{
                saxParser.Product product = new saxParser.Product();
                product = MySQLDataStoreUtilities.getProductByProductName(strLine);
                
                out.println("<ul>");
                out.println("<form action=\"/csj/jsp/customer/AddOrder.jsp\" method=\"post\">");
                out.println("<img src="+product.getImage()+" alt=\"No Image Uploaded\" style=\"width:1000px;height:350px\">");
                out.println("<h3>" + product.getName() + "</h3>\n" );
                
                out.println("<li> Category : "+product.getCategory()+"");out.println("</li>");
                out.println("<li> Retailer : "+product.getRetailer()+"");out.println("</li>");
                out.println("<li> Price : $"+product.getPrice()+"");out.println("</li>");
                
                
                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Add value='"+product.getId()+";"+session.getAttribute("username")+"'>Add to Cart</button>");
                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Order value='"+product.getId()+";"+session.getAttribute("username")+"'>Order</button>");
                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=readReview value='"+product.getId()+";"+session.getAttribute("username")+"'>Read Review</button>");
                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=writeReview value='"+product.getId()+";"+session.getAttribute("username")+"'>Write Review</button><br><br>");
                out.println("</form><br><br>");
                out.println("</ul>");
                out.println("<br>");
            }while((strLine = br.readLine()) != null);
        }
        
        
        //Close the input stream
        br.close();
    }catch(Exception e){
        System.out.println("Exception e:"+e);
    }
    
    
    
    
    
    
    
    out.println("</center>");
    out.println("</div>");
    out.println("</section>");
    
    out.println("<div>");out.println("</div>");
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



