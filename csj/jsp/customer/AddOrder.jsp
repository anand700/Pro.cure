
<%@ page session="false" %>
<%@ page import="java.io.*,java.text.*" %>
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
<script src="/csj/js/customer/AddOrder.js"></script>
<style>
[ng\:cloak],
[ng-cloak],
.ng-cloak {
  display: none;
}
</style>
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
    
    String add = request.getParameter("Add");
    String order = request.getParameter("Order");
    String writeReview = request.getParameter("writeReview");
    String readReview = request.getParameter("readReview");
    
    System.out.println(add);
    System.out.println(order);
    System.out.println(writeReview);
    System.out.println(readReview);


    out.println("<br><br>");
    
    if(null != add){
        String[] parts = add.split(";");
        String title = "Add To Cart";
        
        saxParser.Product p1 = new saxParser.Product();
        try{
            p1 = MySQLDataStoreUtilities.getProduct(Integer.parseInt(parts[0]));
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
        "<form action=/csj/jsp/customer/AddCart.jsp> " +
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
         "  <button class=\"btn btn-success btn-sm\" type=submit name=id value='"+p1.getId()+"'>Confirm</button>\n" +
         "<div style=\"clear:both;\"></div>" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
        out.println("<br><br><br><br><br><br><br><br><br><br>");
        
    }else if(null != order){
        String[] parts = order.split(";");
        
        System.out.println("order");
        String title = "Order Product";
        saxParser.Product p1 = new saxParser.Product();
        try{
            p1 = MySQLDataStoreUtilities.getProduct(Integer.parseInt(parts[0]));
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
        "<form name=\"AddOrderForm\"> " +
        "<center>\n" +
        "<img style=\"margin-left:5%;float:left;width:35%;height: 40%;border:1px solid #2c3e50;\" src="+p1.getImage()+" alt=\"No Image Uploaded\" />"+
        "<div style=\"float:right;width: 60%;\">" +
        "  <b>Name</b> : "+p1.getName()+"<br>\n" +
        "  <b>Retailer</b> : "+p1.getRetailer()+"<br>\n" +
        "  <b>Category</b> : "+p1.getCategory()+" <br>\n" +
        "  <h7 style=\"color:#cc0000;\" ><b>Price</b> : $"+p1.getPrice()+"</h7><br>\n" +
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
      <button class="btn btn-success btn-sm" ng-show="isValidCardType() && isValidNameOnCard() && isValidCardNumber() && isValidCvv() && isValidBA() && isValidExpMonth && isValidExpYear()" ng-click="onClickSubmit('<%=p1.getId() %>')">Submit</button>
      <button class="btn btn-success btn-sm" ng-show="!isValidCardType() || !isValidNameOnCard() || !isValidCardNumber() || !isValidCvv() || !isValidBA() || !isValidExpMonth() || !isValidExpYear()" ng-click="errorMessage()">Submit</button>
      <br>
      <span style="color:red;"><h6>{{errorMessage}}</h6>
          </span><br>
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
    }else if(null != writeReview){
        
        String[] parts = writeReview.split(";");
        
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        System.out.println("Review Product");
        String title = "Review Product";
        
        
        saxParser.Product p1 = new saxParser.Product();
        try{
            p1 = MySQLDataStoreUtilities.getProduct(Integer.parseInt(parts[0]));
            System.out.println("p1.getName() : "+p1.getName());
        }catch(Exception e){
            System.out.println("AddDeleteUpdate>>Read database>>Exception occured");
        }
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
        "transitional//en\">\n";
        out.println(docType +
        "<html><br><br><br><br><br><br>" +
        "<head>" +
        "<meta http-equiv=Content-Type content=text/html charset=utf-8 />" +
        "<title> "+title+" </title>" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h3 align=\"center\">" + title + "</h3>\n" +
        "<form action=\"/csj/jsp/customer/WriteReview.jsp\">" +
        "<ul>\n" +
        "<center>" +
        "  <input  type=\"text\" name=\"ProductId\" value='"+p1.getId()+"' readonly><br>\n" +
        "  <input  type=\"text\" name=\"ProductModelName\" value='"+p1.getName()+"' readonly><br>\n" +
        "  <input  type=text name=ProductCategory value='"+p1.getCategory()+"' readonly><br>\n" +
        "  <input  type=text name=ProductPrice value='"+p1.getPrice()+"' readonly><br>\n" +
        "  <input  type=text name=RetailerName value='"+p1.getRetailer()+"' readonly><br>\n" +
        "  <input  type=text name=RetailerZip placeholder='Retailer Zip'><br>\n" +
        "  <input  type=text name=RetailerCity placeholder='Retailer City'><br>\n" +
        "  <input  type=text name=RetailerState placeholder='Retailer State'><br><br>\n" +
        "  ProductOnSale: <select name=ProductOnSale>\n" +
        "  <option value=yes>Yes</option>\n" +
        "  <option value=no>No</option>\n" +
        "  <select><br><br>\n" +
        "  <input  type=text name=ManufacturerName placeholder='Manufacturer Name'><br><br>\n" +
        "  ManufacturerRebate: <select name=ManufacturerRebate>\n" +
        "  <option value=yes>Yes</option>\n" +
        "  <option value=no>No</option>\n" +
        "  <select><br><br>\n" +
        "  <input  type=text name=UserID value='"+(String)username+"' readonly><br>\n" +
        "  <input  type=text name=UserAge placeholder='User Age'><br><br>\n" +
        "  UserGender: <select name=UserGender>\n" +
        "  <option value=male>Male</option>\n" +
        "  <option value=female>Female</option>\n" +
        "  <select><br><br>\n" +
        "  <input  type=text name=UserOccupation placeholder='User Occupation'><br>\n" +
        "  <input  type=text name=ReviewRating placeholder='Review Rating'><br>\n" +
        "  <input  type=text name=ReviewDate value='"+df.format(dt)+"' readonly><br><br>\n" +
        "  <textarea type=text size='14' name=ReviewText placeholder='Review Text' ></textarea><br><br>\n" +
        "  <button class=\"btn btn-success btn-sm\" type=submit value=Confirm>Submit</button>\n" +
        "<center>" +
        "</ul>\n" +
        "</form>" +
        "</body></html>");
        
    }else if(null != readReview){
        
        String[] parts = readReview.split(";");
        
        System.out.println("Read Review");
        String title = "Read Review";
        
        saxParser.Product p1 = new saxParser.Product();
        try{
            p1 = MySQLDataStoreUtilities.getProduct(Integer.parseInt(parts[0]));
            System.out.println("p1"+p1);
        }catch(Exception e){
            System.out.println("AddDeleteUpdate>>Read database>>Exception occured");
        }
        
        
        Review r = new Review();
        r.setProductId(p1.getId());
        r.setProductModelName(p1.getName());
        r.setProductCategory(p1.getCategory());
        r.setUserID(p1.getUsername());
        r.setRetailerName(p1.getRetailer());
        //>>
        List<Review> reviews = new ArrayList<Review>();
        try{
            reviews = MongoDBDataStoreUtilities.readReview(r);
        }catch(Exception e){
            e.printStackTrace();
        }
        //<<
        
        out.println("<html>");
        //out.println("<br><br><br><br><br><br><br><br>");
        out.println("<head>");
        out.println("<meta http-equiv=Content-Type content=text/html charset=utf-8 />");
        out.println("<title> Pro.cure </title>");
        out.println("<link rel=stylesheet href=styles.css type=text/css />");
        //out.println("<script src=http://html5shiv.googlecode.com/svn/trunk/html5.js></script>");
        
        out.println("<meta name=viewport content=width=device-width, minimum-scale=1.0, maximum-scale=1.0 />");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<br><br><br><br><br><br>");
        //out.println("<a href=/csj/jsp/customer/HomePage.jsp>Back to HomePage");out.println("</a><br><br><br>");
        out.println("Number of Reviews : "+reviews.size()+"<br><br><br>");
        if(reviews.size() == 0){
            out.println("<h3 style=\"color:#cc0000;\">No reviews for this product</h3>");
            out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
        }
        else{
            for(int i=0; i<reviews.size();i++){
                out.println("RetailerName: "+reviews.get(i).getRetailerName()+"<br>");
                out.println("ManufacturerName: "+reviews.get(i).getManufacturerName()+"<br>");
                out.println("ProductCategory: "+reviews.get(i).getProductCategory()+"<br>");
                out.println("UserID: "+reviews.get(i).getUserID()+"<br>");
                out.println("ReviewDate: "+reviews.get(i).getReviewDate()+"<br>");
                out.println("ProductModelName: "+reviews.get(i).getProductModelName()+"<br>");
                out.println("ReviewText: "+reviews.get(i).getReviewText()+"<br>");
                out.println("ReviewRating: "+reviews.get(i).getReviewRating()+"<br>");
                out.println("<br><br>");
            }
            out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
        }
        
        // out.println("<a href=/csj/HomePage>Back");out.println("</a><br><br><br>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        
        
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


