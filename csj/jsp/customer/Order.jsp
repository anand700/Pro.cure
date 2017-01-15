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
    
    //Product details//
    String id = request.getParameter("id");
    String price = request.getParameter("price");
    String userName = request.getParameter("username");
    String quantity = request.getParameter("quantity");

    //Card Details//
    String cardNumber = request.getParameter("cardNumber");
    String cvv = request.getParameter("cvv");
    String ba = request.getParameter("ba");
    String cardType = request.getParameter("cardType");
    String nameOnCard = request.getParameter("nameOnCard");
    String expYear = request.getParameter("expYear");
    String expMonth = request.getParameter("expMonth");

    System.out.println("Order>>id : "+(String)request.getParameter("id"));
    System.out.println("Order>>cardnumber : "+request.getParameter("cardNumber"));
    
    saxParser.Product p = new saxParser.Product();
    CardDetails c = new CardDetails(); 
    try{
        System.out.println("cvv"+cvv);
        System.out.println("expYear"+expYear);
        System.out.println("cardType"+cardType);
        System.out.println("expMonth"+expMonth);
        p = MySQLDataStoreUtilities.getProduct(Integer.parseInt(id));
        int total = p.getPrice() * Integer.parseInt(quantity);
        p.setQuantity(Integer.parseInt(quantity));
        p.setTotal(total);
        p.setUsername((String)username);
        //System.out.println("here cardNumber : "+cardNumber);
        c.setCardNumber(new BigInteger(cardNumber));
        c.setCvv(Integer.parseInt(cvv));
        c.setCardType(cardType);
        c.setExpYear(Integer.parseInt(expYear));
        c.setExpMonth(Integer.parseInt(expMonth));
        c.setNameOnCard(nameOnCard);
        c.setBa(ba);
       

    }catch(Exception e){
        System.out.println("Order>>Read database>>Exception occured");
    }
    
    try {
        MySQLDataStoreUtilities.orderwithPayment(p,c);
    } catch (Exception e) {
        System.out.println("Order>>Write to database>>Exception occured");
    }
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/customer/ListOrder.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/customer/ListOrder.jsp");
    //<<Redirect to salesmen homepage
}
%>
