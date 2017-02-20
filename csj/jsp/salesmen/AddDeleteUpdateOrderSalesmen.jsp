
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
<script src="/csj/js/salesmen/AddDeleteUpdateOrderSalesmen.js"></script>
<style>
[ng\:cloak],
[ng-cloak],
.ng-cloak {
  display: none;
}
</style>
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
    
    String update = request.getParameter("Update");
    String delete = request.getParameter("Delete");
    
    System.out.println(update);
    System.out.println(delete);
    

    
    if(null == update && null == delete){
        System.out.println("Add");
        String title = "Add Order";
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
        "<div ng-app=\"app\" ng-controller=\"ctrl\" ng-cloak>"+
        "<form name=\"SalesmenAddOrderForm\"> " +
        "<ul>\n" +
        "<center>" +
        "  <input type=text name=username placeholder=\"Enter the Username\"><br>\n" +
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
        "  <input type=text name=id ng-model=\"productId\" placeholder=\"Enter Product id\"><br>\n");
        %>
            <br><hr><h3 align="center">Card Details</h3><br>
           <b>Card Type</b> : <select ng-model="cardType" name=cardType>
            <option ng-repeat="option in selectCardType.availableOptions" value='{{option.name}}'>{{option.name}}</option>
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
            <option ng-repeat="option in selectMonth.availableOptions" value='{{option.name}}'>{{option.name}}</option>
          </select><br>
          <span ng-show="!isValidExpMonth()" style="color:red;">{{ExpMonthInvalidMessage}}
          </span><br>
           <b>Exp Year</b> : <select ng-model="expYear" name=expYear>
            <option ng-repeat="option in selectYear.availableOptions" value='{{option.name}}'>{{option.name}}</option>
          </select><br>
          <span ng-show="!isValidExpYear()" style="color:red;">{{ExpYearInvalidMessage}}
          </span><br>
      <button class="btn btn-success btn-sm" ng-show="isValidCardType() && isValidNameOnCard() && isValidCardNumber() && isValidCvv() && isValidBA() && isValidExpMonth && isValidExpYear()" ng-click="onClickSubmit()">Submit</button>
      <button class="btn btn-success btn-sm" ng-show="!isValidCardType() || !isValidNameOnCard() || !isValidCardNumber() || !isValidCvv() || !isValidBA() || !isValidExpMonth() || !isValidExpYear()" ng-click="errorMessage()">Submit</button>
      <br>
      <span style="color:red;"><h6>{{errorMessage}}</h6>
          </span><br>
        </center>
        </form>
        </div>
        <%
        
    }else if(null != update){
        System.out.println("Update");
        String title = "Update Order";
        
        saxParser.Order p1 = new saxParser.Order();
        try{
            p1 = MySQLDataStoreUtilities.getOrderForSalesmen(Integer.parseInt(update));
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
        "<form action=/csj/jsp/salesmen/UpdateProductOrder.jsp > " +
        "<ul>\n" +
        "<center>" +
        "  Product Id, Order Id : <input type=text name=id value='"+p1.getId()+","+p1.getOrderId()+"' readonly><br>\n" +
        "  Product Name : <input type=text name=name value='"+p1.getName()+"'><br>\n" +
        "  Retailer : <input type=text name=retailer value='"+p1.getRetailer()+"'><br>\n" +
        "  Category : <input type=text name=category value='"+p1.getCategory()+"'><br>\n" +
        "  Price : $<input type=text name=price value='"+p1.getPrice()+"'><br>\n" +
        "  Description : <input type=text name=description value='"+p1.getDescription()+"'><br>\n" +
        "  Quantity : <input type=text name=quantity value='"+p1.getQuantity()+"' ><br>\n" +
        "  Username : <input type=text name=username value='"+p1.getUsername()+"'><br>\n" +
        "  Expected Delivery : <input type=text name=expectedDelivery value='"+p1.getExpectedDelivery()+"'><br>\n" +
        "  Order Status : <input type=text name=orderStatus value='"+p1.getOrderStatus()+"'><br>\n" +
        "  Image URL : <input type=text name=image value='"+p1.getImage()+"'><br>\n" +
        "  Ordered Date : <input type=text name=date value='"+p1.getDate()+"'><br>\n" +
        "  <button class=\"btn btn-success btn-sm\" type=submit value=Confirm>Submit</button>\n" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
        
    }else if(null != delete){
        System.out.println("Delete");
        String title = "Delete or Cancel Order";
        
        saxParser.Order p1 = new saxParser.Order();
        try{
            p1 = MySQLDataStoreUtilities.getOrderForSalesmen(Integer.parseInt(delete));
            System.out.println("p1"+p1);
        }catch(Exception e){
            System.out.println("AddDeleteUpdateOrder>>Read database>>Exception occured");
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
        "<form action=/csj/jsp/salesmen/DeleteProductOrder.jsp > " +
        "<ul>\n" +
        "<center>" +
        "  <input type=text name=id value='"+p1.getId()+","+p1.getOrderId()+"' readonly><br>\n" +
        "  <input type=text name=name value='"+p1.getName()+"' readonly><br>\n" +
        "  <input type=text name=retailer value='"+p1.getRetailer()+"' readonly><br>\n" +
        "  <input type=text name=category value='"+p1.getCategory()+"' readonly><br>\n" +
        "  <input type=text name=price value='"+p1.getPrice()+"' readonly><br>\n" +
        "  <input type=text name=quantity value='"+p1.getQuantity()+"' readonly ><br>\n" +
        "  <input type=text name=username value='"+p1.getUsername()+"' readonly><br>\n" +
        "  <button class=\"btn btn-success btn-sm\" type=submit value=Confirm>Submit</button>\n" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
    }
    out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
    %>
    <!-- Footer -->
    <jsp:include page='footer.jsp'>
    <jsp:param name="articleId" value=""/>
    </jsp:include>
<%
    session.setAttribute("username",(String)username);
}
%>
