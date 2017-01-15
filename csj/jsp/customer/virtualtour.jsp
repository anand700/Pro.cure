<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/csj/css/bootstrap.css" rel="stylesheet">
<link href="/csj/css/freelancer.css" rel="stylesheet">
<link href="/csj/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
<body>   

<div class="w3-content w3-display-container">
  <img class="mySlides" src="/csj/img/VT1.jpg" style="width:100%;height:70%;">
  <img class="mySlides" src="/csj/img/VT2.jpg" style="width:100%;height:70%;">
  <img class="mySlides" src="/csj/img/VT3.jpg" style="width:100%;height:70%;">
  <img class="mySlides" src="/csj/img/VT4.jpg" style="width:100%;height:70%;">
</div>
<script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 2000); // Change image every 2 seconds
}
</script>

</body>
</html>
    