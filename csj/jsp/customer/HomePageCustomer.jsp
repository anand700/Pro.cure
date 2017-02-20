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
<link href="/csj/css/css1.css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="/csj/css/css.css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

<script data-require="angular.js@1.2.x" src="/csj/js/angular.js" data-semver="1.2.21"></script>
  <script src="/csj/js/ng-dialog.min.js"></script>
  <script src="/csj/js/angular-resource.js"></script>
<script src="/csj/js/HomePageCustomer.js"></script>
<style>
[ng\:cloak],
[ng-cloak],
.ng-cloak {
  display: none;
}
</style>


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
    request.getParameter("Filter");
    
    out.println("<section id=content>");
    
    out.println("<div id=body>");
    %>
    <center>
    <!-- <div ng-app="app" ng-controller="ctrl" ng-cloak>
    <h1>Filters</h1>
    <form action=/csj/jsp/customer/Filters.jsp >
      <select id="repeatSelect" ng-model="FilterOptions.availableOptions" name="Filter">
        <option ng-repeat="option in FilterOptions.availableOptions" value="{{option.id}}">{{option.name}}</option>
      </select>
      <br><br><br><br>
      <input type=submit value=Search>
    </form>
    <br>
  </div> -->
    <jsp:include page='virtualtour.jsp'>
        <jsp:param name="articleId" value=""/>
    </jsp:include>        
  </center>
  <div>
                  <div>
                        <img style="float:left;width:50%;" src="/csj/img/VT5.jpg">
                  </div>
    <%
    
        List<Review> reviews = new ArrayList<Review>();
        int i=0;
        try{
                reviews = MongoDBDataStoreUtilities.top5LikedProd();
                out.println("<div style=\"float:right;width:50%;\">");
                out.println("<center>");
                out.println("<h3>Top 5 most liked products</h3>");
                for(i=0; i<reviews.size();i++){
                    //out.println("RetailerName: "+reviews.get(i).getRetailerName()+"<br>");
                    //out.println("ManufacturerName: "+reviews.get(i).getManufacturerName()+"<br>");
                    //out.println("ProductCategory: "+reviews.get(i).getProductCategory()+"<br>");
                    //out.println("UserID: "+reviews.get(i).getUserID()+"<br>");
                    //out.println("ReviewDate: "+reviews.get(i).getReviewDate()+"<br>");
                    out.println("<li>"+reviews.get(i).getProductModelName()+"<br>");
                    //out.println("ReviewText: "+reviews.get(i).getReviewText()+"<br>");
                    //out.println("ReviewRating: "+reviews.get(i).getReviewRating()+"<br><br>");
                    
                }
                out.println("</center>");
                out.println("</div>");
out.println("</div>");



                List<String> top5Prod = MongoDBDataStoreUtilities.top5Prod();
                i = 0;
out.println("<div><div style=\"float:left;width:50%;clear:both;\">");
                out.println("<center>");
                out.println("<h3>Top five most sold products regardless of the rating</h3>");
                out.println("<h5>(Product Name : Count)</h5>");
                while(i < top5Prod.size()){
                    out.println(top5Prod.get(i)+"<br>");
                    i++;
                }
                out.println("</center>");
                out.println("</div>");




                %>
                 <div>
                        <img style="float:right;width:50%;" src="/csj/img/VT6.jpg">
                  </div>
</div>

                  <div>
                     <div>
                        <img style="float:left;width:50%;clear:both;" src="/csj/img/VT2.jpg">
                    </div>
                <%
                List<String> top5Zip = MongoDBDataStoreUtilities.top5Zip();
                i = 0;
                out.println("<div style=\"width:50%;float:right;\">");
                out.println("<center>");
                out.println("<h3>Top five zip-codes where maximum number of products sold</h3>");
                out.println("<h5>(Zip : Count)</h5>");
                while(i < top5Zip.size()){
                    out.println(top5Zip.get(i)+"<br>");
                    i++;
                }
                out.println("</center>");
                out.println("</div>");
                out.println("</div>");

            //}
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
    out.println("</center>");
    out.println("</div>");
    out.println("</section>");
    
    out.println("<div>");out.println("</div>");
    out.println("</body>");
    out.println("<br><br><br><br><br><br><br><br><br><br>");
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



