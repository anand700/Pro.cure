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
    String productId = request.getParameter("ProductId");
    String productModelName = request.getParameter("ProductModelName");
    String productCategory = request.getParameter("ProductCategory");
    String productPrice = request.getParameter("ProductPrice");
    String retailerName = request.getParameter("RetailerName");
    String retailerZip = request.getParameter("RetailerZip");
    String retailerCity = request.getParameter("RetailerCity");
    String retailerState = request.getParameter("RetailerState");
    String productOnSale = request.getParameter("ProductOnSale");
    String manufacturerName = request.getParameter("ManufacturerName");
    String userID = request.getParameter("UserID");
    String userAge = request.getParameter("UserAge");
    String userGender = request.getParameter("UserGender");
    String userOccupation = request.getParameter("UserOccupation");
    String reviewRating = request.getParameter("ReviewRating");
    String reviewDate = request.getParameter("ReviewDate");
    String reviewText = request.getParameter("ReviewText");
    
    Review r = new Review();
    r.setProductId(productId);
    r.setProductModelName(productModelName);
    r.setProductCategory(productCategory);
    r.setProductPrice(productPrice);
    r.setRetailerName(retailerName);
    r.setRetailerZip(retailerZip);
    r.setRetailerCity(retailerCity);
    r.setRetailerState(retailerState);
    r.setProductOnSale(productOnSale);
    r.setManufacturerName(manufacturerName);
    r.setUserID(userID);
    r.setUserAge(userAge);
    r.setUserGender(userGender);
    r.setUserOccupation(userOccupation);
    r.setReviewRating(reviewRating);
    r.setReviewDate(reviewDate);
    r.setReviewText(reviewText);
    
    System.out.println("data:"+productModelName+productCategory+productPrice+retailerName+retailerZip+retailerCity+retailerState+productOnSale+manufacturerName+userID+userAge+userGender+userOccupation+
    reviewRating+reviewDate+reviewText);
    
    try{
        MongoDBDataStoreUtilities.writeReview(r);
    }catch(Exception e){
        e.printStackTrace();
    }
    
    //>>Redirect to salesmen homepage
    session.setAttribute("username",(String)username);
    //request.getRequestDispatcher("/jsp/customer/HomePage.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() +"/jsp/customer/HomePage.jsp");
    //<<Redirect to salesmen homepage
}
%>
