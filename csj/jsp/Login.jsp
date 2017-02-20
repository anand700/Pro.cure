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
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.1.6/ng-dialog.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.1.6/ng-dialog-theme-plain.min.css" />
<link href="/csj/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
<script data-require="angular.js@1.2.x" src="/csj/js/angular.js" data-semver="1.2.21"></script>
  <script src="/csj/js/ng-dialog.min.js"></script>
  <script src="/csj/js/angular-resource.js"></script>
  <script src="/csj/js/Login.js"></script>
  <link rel="stylesheet" href="/csj/css/password.css" />
  <style>
    [ng\:cloak],
    [ng-cloak],
    .ng-cloak {
      display: none;
    }
  </style>
<!-- <link href="/csj/css/simple-sidebar.css" rel="stylesheet"> -->
<html>
<center>
<head><title>Pro.cure</title></head>
<h1>Pro.cure</h1>
<div ng-app="app" ng-controller="ctrl" ng-cloak>
    <form name="passwordForm" action=/csj/jsp/LoginValidate.jsp >
      <div class="control-group">
        <div class="controls">
          <input  type=text name=username placeholder="Enter the User Name"><br>
          <input autofocus type="password" placeholder="Enter the Password" name=password ng-model="password" />
        </div>
      </div>
      <password_meter></password_meter>
      <input ng-show="isValidPassword() && passwordForm.password.$valid" placeholder="Enter the Password" type="Submit" value="Submit" ng-click="onSubmit()" />
      <input ng-show="!isValidPassword()" type="Submit" value="Submit" ng-click="errorMessage()" />
      <br>
      <a href=/csj/jsp/SignUp.jsp>Sign Up</a><br>
      <span ng-show="!isValidPassword()" ng-class="strength">Minimum 8 alphanumeric character with atleast one uppercase, one lower case and one special character</span>

    </form>
    <br>
  </div>
  </center>
</html>
<%

HttpSession session=request.getSession();
session.invalidate();


if(null != request.getAttribute("ERROR")){
    out.println("<center><h5  style=\"color:#cc0000;\">" + request.getAttribute("ERROR") + "</h5></center>\n" );
}
out.println("<center>" +
"</ul>\n" +
"</form>" +
"</body></html>");


String userName = request.getParameter("username");
String password = request.getParameter("password");
String email= request.getParameter("email");
String phoneNum= request.getParameter("phonenum");
String typeOfUser= request.getParameter("typeofuser");

//>>Write
if(null != userName && null == request.getAttribute("ERROR")){
    ArrayList<String> userDetails = new ArrayList<String>();
    
    userDetails.add(password);
    userDetails.add(email);
    userDetails.add(phoneNum);
    userDetails.add(typeOfUser);
    try {
        MySQLDataStoreUtilities.addUser(userName, userDetails);
    } catch (Exception e) {
        System.out.println("Login>>Exception occured");
    }
}
%>
