var app = angular.module('app', []);

app.controller('ctrl', function($scope, $rootScope) {
  $scope.nameOnCard = "";
  $scope.cardNumber = "";
  $scope.cvv = "";
  $scope.ba = "";
  $scope.cardType = "";
  $scope.expMonth = "";
  $scope.expYear = "";
  $scope.nameOnCardInvalidMessage = "";
  $scope.cardNumberInvalidMessage = "";
  $scope.emailInvalidMessage = "";
  $scope.cardTypeInvalidMessage = "";
  $scope.cvvInvalidMessage = "";
  $scope.baInvalidMessage = "";
  $scope.ExpMonthInvalidMessage = "";
  $scope.ExpYearInvalidMessage = "";

  $scope.selectYear = {
   availableOptions: [
     {id: '1', name: '2017'},
     {id: '2', name: '2018'},
     {id: '3', name: '2019'},
     {id: '4', name: '2020'},
     {id: '5', name: '2021'},
     {id: '6', name: '2022'},
     {id: '7', name: '2023'}
   ]
  };
  $scope.selectMonth = {
   availableOptions: [
     {id: '1', name: '1'},
     {id: '2', name: '2'},
     {id: '3', name: '3'},
     {id: '4', name: '4'},
     {id: '5', name: '5'},
     {id: '6', name: '6'},
     {id: '7', name: '7'},
     {id: '5', name: '8'},
     {id: '6', name: '9'},
     {id: '7', name: '10'},
     {id: '5', name: '11'},
     {id: '6', name: '12'}
   ]
  };
  $scope.selectCardType = {
   availableOptions: [
     {id: '1', name: 'Visa'},
     {id: '2', name: 'Discover'},
     {id: '3', name: 'Master Card'},
     {id: '4', name: 'Visa Debit'},
     {id: '5', name: 'Maestro'},
     {id: '6', name: 'American Express'}
   ]
  };

  $scope.isValidNameOnCard = function() {
	    var illegalChars = /\W/; // allow letters, numbers, and underscores
	 
	    if ($scope.nameOnCard == "") {
	        $scope.nameOnCardInvalidMessage = "You didn't enter a name.";
	        return false;
	 
	    } else if (illegalChars.test($scope.nameOnCard)) {
	        $scope.nameOnCardInvalidMessage = "The name contains illegal characters.";
	        return false;
	 
	    } else {
	    }
	    return true;
  };

  $scope.isValidCardNumber = function() {
	     var stripped = $scope.cardNumber.replace(/[\(\)\.\-\ ]/g, '');
	 
	   if ($scope.cardNumber == "") {
	        $scope.cardNumberInvalidMessage = "You didn't enter the card number.\n";
			return false;
	    } else if (isNaN(parseInt(stripped))) {
	        $scope.cardNumberInvalidMessage = "The Card number contains illegal characters. Don't include dash (-)\n";
			return false;
	    } else if (!(stripped.length == 16)) {
	        $scope.cardNumberInvalidMessage = "Invalid card number. Make sure you type in 16 numbers. Don't include dash (-)\n";
			return false;
	    }
	    return true;
  };

  $scope.isValidCardType = function() {
	    if ($scope.cardType == "") {
	        $scope.cardTypeInvalidMessage = "You didn't enter card type.";
	        return false;
	 
	    }else {
	    }
	    return true;
  };

   $scope.isValidCvv = function() {
	     var stripped = $scope.cvv.replace(/[\(\)\.\-\ ]/g, '');
	 
	   if ($scope.cvv == "") {
	        $scope.cvvInvalidMessage = "You didn't enter a CVV.";
			return false;
	    } else if (isNaN(parseInt(stripped))) {
	        $scope.cvvInvalidMessage = "The CVV contains illegal characters. Don't include dash (-)\n";
			return false;
	    } else if (!(stripped.length == 3)) {
	        $scope.cvvInvalidMessage = "Invalid CVV. Make sure you type in 3 numbers. Don't include dash (-)\n";
			return false;
	    }
	    return true;
  };

  $scope.isValidBA = function() {
	     var stripped = $scope.ba.replace(/[\(\)\.\-\ ]/g, '');
	 
	   if ($scope.ba == "") {
	        $scope.baInvalidMessage = "You didn't enter a Billing Address.\n";
			return false;
	    } 
	    return true;
  };

    $scope.isValidExpMonth = function() {
	    if ($scope.expMonth == "") {
	        $scope.ExpMonthInvalidMessage = "You didn't enter Expiry Month.";
	        return false;
	 
	    }else {
	    }
	    return true;
  };

    $scope.isValidExpYear = function() {
	    if ($scope.expYear == "") {
	        $scope.ExpYearInvalidMessage = "You didn't enter Expiry Year.";
	        return false;
	 
	    }else {
	    }
	    return true;
  };

  $scope.onClickSubmit = function() {
  	var id = $scope.productId;
  	alert(id);
  	var input = document.createElement("input");

	input.setAttribute("type", "hidden");

	input.setAttribute("name", "id");

	input.setAttribute("value", id);

	document.SalesmenAddOrderForm.appendChild(input);
	document.SalesmenAddOrderForm.action = "/csj/jsp/salesmen/AddProductOrder.jsp";
	document.SalesmenAddOrderForm.submit();
  };
  
  $scope.errorMessage = function() {
     $scope.errorMessage = "Please correct the fields which are in red.";
  };
});