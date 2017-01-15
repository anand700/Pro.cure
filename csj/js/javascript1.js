function init() {
	completeField = document.getElementById("searchId");
	completeTable = document.getElementById("complete-table"); 
	autoRow = document.getElementById("auto-row");
}
function doCompletion() {
	//alert("url");
	var url = "http://localhost:8080/csj/Autocomplete?action=complete&searchId="+searchId.value+""; 
	//alert(url);
	req = initRequest();
	req.open("GET", url, true);
	
	req.onreadystatechange = callback;
	req.send(null);
}
function initRequest() {
	if (window.XMLHttpRequest) {
		if (navigator.userAgent.indexOf('MSIE') != -1) {
			isIE = false; 
		}
		return new XMLHttpRequest(); 
	} else if (window.ActiveXObject) {
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP"); 
	}
}
function appendProduct(productName,productId) { 
	var row;
	var cell;
	var linkElement;
	//alert("appendProduct before");
	var isIE = false;
	if (isIE) {
		//alert("appendProduct in if");
		completeTable.style.display = 'block';
		row = completeTable.insertRow(completeTable.rows.length); cell = row.insertCell(0);
	} else {
		//alert("appendProduct");
		completeTable.style.display = 'table'; row = document.createElement("tr"); cell = document.createElement("td"); 
		row.appendChild(cell); 
		completeTable.appendChild(row);
	}
	cell.className = "popupCell";
	linkElement = document.createElement("a");
	linkElement.className = "popupItem";
	linkElement.setAttribute("href", "http://localhost:8080/csj/HomePageCustomer?filterByProductName="+productName); 
	linkElement.appendChild(document.createTextNode(productName)); cell.appendChild(linkElement);
 }



 function parseMessages(response) { // no matches returned
	if (response == null) {
		alert("inside if");
		return false; 
	} else {
		//alert(response);
		var products = response.getElementsByTagName("products"); 
		//alert(products);
		//alert(products.childNodes);
		if (products.childNodes.length > 0) {
			
			completeTable.setAttribute("bordercolor", "black"); 
			completeTable.setAttribute("border", "1");
			for (loop = 0; loop < products.childNodes.length; loop++) {
				
				var product = products.childNodes[loop];
				var productName = product.getElementsByTagName("productName")[0]; 
				var productId = product.getElementsByTagName("id")[0]; 
				appendProduct(productName.childNodes[0].nodeValue,productId.childNodes[0].nodeValue); 
			}
		} 
	}
}
function parseMessages1(response) { // no matches returned
	if (response == null) {
		//alert("inside if");
		return false; 
	} else {
		//alert(response);
		var response1 = response.substr(response.indexOf('>')+1, response.length);
		var s = response1.split("<product>");
		
		for(var i = 0; i < s.length; i++) {
			//alert("here");
			if(isEmpty(s[i])){
				
			}else{
				var productid = s[i].substr(s[i].indexOf('>')+1, s[i].indexOf('</')-4);
				var productName = s[i].substr(s[i].indexOf('productName>')+12, s[i].indexOf('</productName')-25);
				appendProduct(productName,productid);
			}
		}
	}
}
 
 function isEmpty(value) {
  return typeof value == 'string' && !value.trim() || typeof value == 'undefined' || value === null;
}

function callback() { 
	clearTable();
	if (req.readyState == 4) {
		if (req.status == 200) { 
			parseMessages1(req.response);
		} 
	}
}

function clearTable() {
	if (completeTable.getElementsByTagName("tr").length > 0) {
		completeTable.style.display = 'none';
		for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
			completeTable.removeChild(completeTable.childNodes[loop]); 
		}
	} 
}