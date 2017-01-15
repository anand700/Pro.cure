<?php
$target_dir = "/Applications/tomcat/webapps/csj/img/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

// Check if image file is a actual image or fake image
if(isset($_POST["submit"])) {
    $check = getimagesize($_FILES["fileToUpload"]["tmp_name"]);
    if(move_uploaded_file($_FILES['fileToUpload']['tmp_name'], $target_file)) {
        $uploadOk = 1;
    } else {
        //echo "File is not an image.";
        $uploadOk = 0;
    }
}
ob_start();
$url = "http://localhost:8080/csj/jsp/admin/AddProduct.jsp?&description=".$_POST["description"]."&name=".$_POST["name"]."&retailer=".$_POST["retailer"]."&category=".$_POST["category"]."&price=".$_POST["price"]."&username=".$_POST["username"]."&image=/csj/img/".$_FILES["fileToUpload"]["name"];
header("Location: $url");
exit;
?>