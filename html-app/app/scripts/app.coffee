'use strict'

angular.module('mcwordy', [])
  .config ($routeProvider) ->
    $routeProvider
      .when '/moosh/new',
        templateUrl: 'views/moosh/new.html'
        controller: 'MooshNewCtrl'
      .otherwise
        redirectTo: '/moosh/new'
