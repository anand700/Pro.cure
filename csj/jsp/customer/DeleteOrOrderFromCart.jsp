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
<script data-require="angular.js@1.2.x" src="/csj/js/angular.js" data-semver="1.2.21"></script>
  <script src="/csj/js/ng-dialog.min.js"></script>
  <script src="/csj/js/angular-resource.js"></script>
<script src="/csj/js/customer/DeleteOrOrderFromCart.js"></script>
<style>
[ng\:cloak],
[ng-cloak],
.ng-cloak {
  display: none;
}
</style>
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
    
    String order = request.getParameter("Order");
    String delete = request.getParameter("Delete");
    
    System.out.println("Order: "+order);
    System.out.println("Delete: "+delete);
    
    if(null != order){
        System.out.println("Order");
        String title = "Order";
        
        saxParser.Cart p1 = new saxParser.Cart();
        try{
            p1 = MySQLDataStoreUtilities.getCart1(Integer.parseInt(order), Integer.parseInt((String)userId));
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
        "<h3 align=\"center\">" + title + "</h3><br>\n" +
        "<div ng-app=\"app\" ng-controller=\"ctrl\" ng-cloak>"+
        "<form name=\"OrderFromCart\"> " +
        "<center>\n" +
        "<img style=\"margin-left:5%;float:left;width:35%;height: 40%;border:1px solid #2c3e50;\" src="+p1.getImage()+" alt=\"No Image Uploaded\" />"+
        "<div style=\"float:right;width: 60%;\">" +
        "  <b>Name</b> : "+p1.getName()+"<br>\n" +
        "  <b>Retailer</b> : "+p1.getRetailer()+"<br>\n" +
        "  <b>Category</b> : "+p1.getCategory()+" <br>\n" +
        "  <h7 style=\"color:red;\" ><b>Price</b> : $"+p1.getPrice()+"</h7><br>\n" +
         "</div>" +
        "  <b>Description</b> : "+p1.getDescription()+"<br>\n" +
        "  <b>Quantity</b> : <select name=quantity>" +
         " <option>1</option>" +
         " <option>2</option>" +
         " <option>3</option>" +
         " <option>4</option>" +
         " <option>5</option>" +
         " <option>6</option>" +
         " <option>7</option>" +
         " <option>8</option>" +
         " <option>9</option>" +
         " </select><br>\n" +
         "<div style=\"clear:both;\">");
         %>
         <br><hr><h3 align="center">Card Details</h3><br>
           <b>Card Type</b> : <select ng-model="cardType" name=cardType>
            <option ng-repeat="option in selectCardType.availableOptions" value='"{{option.name}}"'>{{option.name}}</option>
          </select><br>
          <span ng-show="!isValidCardType()" style="color:red;">{{cardTypeInvalidMessage}}
          </span><br>
           <input  type=text name=nameOnCard ng-model="nameOnCard" placeholder="Enter the Name on Card"><br>
           <span ng-show="!isValidNameOnCard()" style="color:red;">{{nameOnCardInvalidMessage}}
          </span><br>
           <input  type=text name=cardNumber ng-model="cardNumber" placeholder="Enter Card Number"><br>
           <span ng-show="!isValidCardNumber()" style="color:red;">{{cardNumberInvalidMessage}}
          </span><br>
           <input  type=text name=cvv ng-model="cvv" placeholder="Enter CVV"><br>
           <span ng-show="!isValidCvv()" style="color:red;">{{cvvInvalidMessage}}
          </span><br>
           <input  type=text name=ba ng-model="ba" placeholder="Enter Billing Address"><br>
           <span ng-show="!isValidBA()" style="color:red;">{{baInvalidMessage}}
          </span><br>
           <b>Exp Month</b> : <select ng-model="expMonth" name=expMonth>
            <option ng-repeat="option in selectMonth.availableOptions" value='"{{option.id}}"'>{{option.name}}</option>
          </select><br>
          <span ng-show="!isValidExpMonth()" style="color:red;">{{ExpMonthInvalidMessage}}
          </span><br>
           <b>Exp Year</b> : <select ng-model="expYear" name=expYear>
            <option ng-repeat="option in selectYear.availableOptions" value='"{{option.id}}"'>{{option.name}}</option>
          </select><br>
          <span ng-show="!isValidExpYear()" style="color:red;">{{ExpYearInvalidMessage}}
          </span><br>
      <button ng-show="isValidCardType() && isValidNameOnCard() && isValidCardNumber() && isValidCvv() && isValidBA() && isValidExpMonth && isValidExpYear()" ng-click="onClickSubmit('<%=p1.getId() %>')">Submit</button>
      <button ng-show="!isValidCardType() || !isValidNameOnCard() || !isValidCardNumber() || !isValidCvv() || !isValidBA() || !isValidExpMonth() || !isValidExpYear()" ng-click="errorMessage()">Submit</button>
      <br>
      <h5><span style="color:red;">{{errorMessage}}
          </span></h5>
        </center>
        </form>
        </div>
         <%
        out.println(""+
                        "<div><center><br><br><br><br><br><hr><h5>Related Accessories:</h5></center>");
        HashMap<String, saxParser.Product>  map = new HashMap<String, saxParser.Product> ();
    HashMap<String, saxParser.Product>  accessoryMap = new HashMap<String, saxParser.Product> ();
        try {
            map = MySQLDataStoreUtilities.selectAllProduct();
            accessoryMap = MySQLDataStoreUtilities.selectAllProductsWithFilterAsAccesory();
        } catch (Exception e) {
            System.out.println("HomePage>>Read From Database>>Exception occured");
        }

        if(!accessoryMap.isEmpty()){
                int countOfAccessories = 0;
                //out.println("<h3>Related Accessories : </h3>\n" );
                for(Map.Entry<String,saxParser.Product> m1 :accessoryMap.entrySet()){
                    if(m1.getValue().getCategory().toLowerCase().contains("accessory") || m1.getValue().getCategory().toLowerCase().contains("accessories")){
                        %>
                        <form action="/csj/jsp/customer/AddOrder.jsp">
                        <center>
                        <div class="responsive">
                          <div class="img">
                              <img src='<%=m1.getValue().getImage()%>' alt="No Image Uploaded">
                            </div>
                            <div class="desc">
                            <%
                                out.println("<h5>" + m1.getValue().getName() + "</h5>\n" );
                                out.println(" <li>Category : "+m1.getValue().getCategory()+"</li>");
                                out.println(" <li>Retailer : "+m1.getValue().getRetailer()+"</li>");
                                out.println(" <li>Price : $"+m1.getValue().getPrice()+"</li>");
                                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Add value='"+m1.getValue().getId()+";"+session.getAttribute("username")+"'>Add to Cart</button>");
                                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=Order value='"+m1.getValue().getId()+";"+session.getAttribute("username")+"'>Order</button>");
                                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=readReview value='"+m1.getValue().getId()+";"+session.getAttribute("username")+"'>Read Review</button>");
                                out.println("<button class=\"btn btn-success btn-sm\" type=submit name=writeReview value='"+m1.getValue().getId()+";"+session.getAttribute("username")+"'>Write Review</button>");
                            %>
                            
                          </div>
                        </div>
                        </a>
                        </center>
                        </form>
                        <%
                        //out.println("<h4>" + m1.getValue().getName() + "</h4>\n" );
                        //out.println("<ul>");
                        //out.println(" Category : "+m1.getValue().getCategory()+"");
                        //out.println(" Retailer : "+m1.getValue().getRetailer()+"");
                        //out.println(" Price : "+m1.getValue().getPrice()+"");
                        //out.println("</ul>");
                        countOfAccessories ++;
                    }
            }
            if(countOfAccessories < 1){
                    out.println("<h3 style=\"color:#cc0000;\"> No Related Accessories ");
            }
            }

            out.println("</div></body></html>");
        
    }else if(null != delete){
        System.out.println("Delete");
        String title = "Delete from Cart";
        
        saxParser.Product p= new saxParser.Product();
        
        //>>GetObject
        
        //>>GetObject
        
        saxParser.Cart p1 = new saxParser.Cart();
        try{
            p1 = MySQLDataStoreUtilities.getCart1(Integer.parseInt(delete),Integer.parseInt((String)userId));
            System.out.println("p1"+p1);
        }catch(Exception e){
            System.out.println("AddDeleteUpdate>>Read database>>Exception occured");
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
        "<form action=/csj/jsp/customer/DeleteFromCart.jsp > " +
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
         "  <button class=\"btn btn-success btn-sm\" type=submit name=id value='"+p1.getId()+"'>Confirm</button>\n" +
         "<div style=\"clear:both;\"></div>" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html><br><br><br><br><br><br><br><br><br><br><br>");
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
