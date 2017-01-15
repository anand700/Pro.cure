<!doctype html public \"-//w3c//dtd html 4.0 " +
"transitional//en\">
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
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
  <script src="/csj/js/SignUp.js"></script>
  <link rel="stylesheet" href="/csj/css/password.css" />
  <style>
    [ng\:cloak],
    [ng-cloak],
    .ng-cloak {
      display: none;
    }
  </style>
  <center>
  <h1 align="center"> Sign Up </h1>
<div ng-app="app" ng-controller="ctrl" ng-cloak>
    <form name="SignUpForm">
      <div class="control-group">
        <div class="controls">
          <input type=text name=phonenum placeholder="Phone Number" ng-model="phoneNumber" ><br>
          <span ng-show="!isValidPhoneNumber()" style="color:red;">{{phoneNumberInvalidMessage}}
          </span><br>
          <input type=text name=email placeholder="Email" ng-model="email" ><br>
          <span ng-show="!isValidEmail()" style="color:red;">{{emailInvalidMessage}}
          </span><br>
          <input  type=text name=username placeholder="User Name" ng-model="userName" ><br>
          <span ng-show="!isValidUserName()" style="color:red;">{{userNameInvalidMessage}}
          </span><br>
          <input autofocus type="password" placeholder="Password" name=password ng-model="password" /><br>
          <span ng-show="!isValidPassword()" ng-class="strength">Minimum 8 alphanumeric character with atleast one uppercase, one lower case and one special character</span><br>
        </div>
      </div>
      <password_meter></password_meter>
      <br>Select Type of User: <select name=typeofuser>
          <option value=Customer>Customer</option>
          <option value=Salesmen>Salesmen</option>
          <option value=Admin>Admin</option>
          <select><br><br>
      <input ng-show="isValidPassword() && isValidPhoneNumber() && isValidEmail() && isValidUserName()" type="Submit" value="Submit" ng-click=onSubmit() />
      <input ng-show="!isValidPassword() || !isValidPhoneNumber() || !isValidEmail() || !isValidUserName()" type="Submit" value="Submit" ng-click="errorMessage()" />
      <br>
      <span style="color:red;">{{errorMessage}}
          </span><br>
    </form>
    <br>
  </div>
  </center>