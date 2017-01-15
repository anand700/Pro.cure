var app = angular.module('app', []);

app.controller('ctrl', function($scope, $rootScope) {
  $scope.password = "";
  $scope.strength = "weak";
  $scope.meterVal = 0;
  $scope.getMeterVal = 0;
  $scope.email = "";
  $scope.phoneNumber = "";
  $scope.userName = "";
  $scope.errorMessage = "";
  $scope.userNameInvalidMessage = "";
  $scope.phoneNumberInvalidMessage = "";
  $scope.emailInvalidMessage = "";
  
  
  $scope.getMeterVal = function() {

    //return -1 if password is null
    if (null === $scope.password) {
      return -1;
    }

    var returnVal = 0;

    // Minimum 8 char to have atleast a low strength password.
    if ($scope.password.length < 8) {
      return 0;
    }
    // If Numberic
    if (/[0-9]/.test($scope.password)) {
      returnVal++;
    }
    // If UpperCase
    if (/[A-Z]/.test($scope.password)) {
      returnVal++;
    }
    // If LowerCase
    if (/[a-z]/.test($scope.password)) {
      returnVal++;
    }
    // If SpecialChar
    if (/[^A-Z-0-9]/i.test($scope.password)) {
      returnVal++;
    }

    if (returnVal < 2) {
      $scope.strength = "weak";
    } else if (returnVal < 4) {
      $scope.strength = "medium";
    } else {
      $scope.strength = "strong";
    }
    $scope.meterVal = returnVal;
    return returnVal;
  };
  
  $scope.isValidPassword = function() {
     if ($scope.meterVal == 4) {
      return true;
    } 
    return false;
  };

  $scope.isValidEmail = function() {
     if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($scope.email)){
      return true;
    }
    $scope.emailInvalidMessage = "Invalid Email";
      return false;
  };

    $scope.isValidUserName = function() {
	    var illegalChars = /\W/; // allow letters, numbers, and underscores
	 
	    if ($scope.userName == "") {
	        $scope.userNameInvalidMessage = "You didn't enter a username.";
	        return false;
	 
	    } else if (($scope.userName.length < 5) || ($scope.userName.length > 15)) {
	        $scope.userNameInvalidMessage = "The username is the wrong length.";
	        return false;
	 
	    } else if (illegalChars.test($scope.userName)) {
	        $scope.userNameInvalidMessage = "The username contains illegal characters.";
	        return false;
	 
	    } else {
	    }
	    return true;
  };


$scope.isValidPhoneNumber = function() {
     var error = "";
     var stripped = $scope.phoneNumber.replace(/[\(\)\.\-\ ]/g, '');
 
   if ($scope.phoneNumber == "") {
        $scope.phoneNumberInvalidMessage = "You didn't enter a phone number.\n";
		return false;
 
    } else if (isNaN(parseInt(stripped))) {
        $scope.phoneNumberInvalidMessage = "The phone number contains illegal characters. Don't include dash (-)\n";
		return false;
    } else if (!(stripped.length == 10)) {
        $scope.phoneNumberInvalidMessage = "Invalid phone number. Make sure you included an area code. Don't include dash (-)\n";
		return false;
    }
    return true;
  };

  $scope.onSubmit = function() {
     document.SignUpForm.action = "/csj/jsp/Login.jsp";
     document.SignUpForm.submit();
  };
  
  $scope.errorMessage = function() {
     $scope.errorMessage = "Please correct the fields which are in red.";
  };
});



// Password Meter Directive //
app.directive('passwordMeter', function() {
  return {
    restrict: 'E',
    scope: "=",
    replace: true,
    transclude: false,
    templateUrl: "/csj/jsp/passwordMeter.jsp",
    link: function(scope, element, attrs) {}
  }
});