var app = angular.module('app', []);

app.controller('ctrl', function($scope, $rootScope) {
  $scope.here = "Here";
  $scope.FilterOptions = {
   model: null,
   availableOptions: [
     {id: 'Top5Prod', name: 'Top five most sold products regardless of the rating'},
     {id: 'Top5LikedProd', name: 'Top five most liked products'},
     {id: 'Top5Zip', name: 'Top five zip-codes where maximum number of products sold'}
   ]
  };
});