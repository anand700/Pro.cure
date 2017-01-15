var app = angular.module('app', []);

app.controller('ctrl', function($scope, $rootScope) {
  $scope.password = "";
  $scope.strength = "weak";
  $scope.meterVal = 0;
  $scope.getMeterVal = 0;
  
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

  $scope.onSubmit = function() {
     //alert("Submitted Successfully");
  };
  
  $scope.errorMessage = function() {
     //alert("Please enter the correct password");
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