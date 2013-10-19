angular.module('mcwordy')
  .controller 'MooshNewCtrl', ['$scope', ($scope)->
    $scope.words = []
    $scope.refreshWords = ->
      $scope.words = _.times 3, (i) ->
        "Randoword#{Math.floor(Math.random() * 10)}"
  ]
