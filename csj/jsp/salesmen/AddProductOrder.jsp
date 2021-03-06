<!doctype html public \"-//w3c//dtd html 4.0 " +
"transitional//en\">
<%@ page session="false" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="saxParser.*" %>
<%@ page import="java.math.BigInteger" %>
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
    saxParser.Product pFromGet = null;

    //Product details//
    String userName = request.getParameter("username");
    String quantity = request.getParameter("quantity");
    String productId = request.getParameter("id");

    //Card Details//
    String cardNumber = request.getParameter("cardNumber");
    String cvv = request.getParameter("cvv");
    String ba = request.getParameter("ba");
    String cardType = request.getParameter("cardType");
    String nameOnCard = request.getParameter("nameOnCard");
    String expYear = request.getParameter("expYear");
    String expMonth = request.getParameter("expMonth");


System.out.println("getProduct().id : "+productId);
    try {
         pFromGet = MySQLDataStoreUtilities.getProduct(Integer.parseInt(productId));
    } catch (Exception e) {
        System.out.println("AddProductOrder>>Write to serializable>>Exception occured in getProduct()");
    }


    System.out.println("getProduct().name : "+pFromGet);
    String name = pFromGet.getName();
    String retailer = pFromGet.getRetailer();
    String category = pFromGet.getCategory();
    int price = pFromGet.getPrice(); 
    String description = pFromGet.getDescription();
    String image = pFromGet.getImage();
    
    int total = price * Integer.parseInt(quantity);

    saxParser.Product p= new saxParser.Product();
    CardDetails c = new CardDetails(); 
    //p.setId(Integer.toString(maxId+1));
    p.setName(name);
    p.setRetailer(retailer);
    p.setDescription(description);
    p.setImage(image);
    p.setCategory(category);
    p.setPrice(price);
    p.setTotal(total);
    p.setUsername(userName);
    p.setId(productId);
    p.setQuantity(Integer.parseInt(quantity));
    c.setCardNumber(new BigInteger(cardNumber));
    c.setCvv(Integer.parseInt(cvv));
    c.setCardType(cardType);
    c.setExpYear(Integer.parseInt(expYear));
    c.setExpMonth(Integer.parseInt(expMonth));
    c.setNameOnCard(nameOnCard);
    c.setBa(ba);

    try {
        MySQLDataStoreUtilities.orderwithPayment(p,c);
    } catch (Exception e) {
        System.out.println("AddProductOrder>>Write to serializable>>Exception occured");
    }
    //<<Write to serializable file
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/salesmen/HomePageSalesmen.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/salesmen/HomePageSalesmen.jsp");
    //<<Redirect to salesmen homepage
}
%>
