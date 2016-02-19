import angular from 'angular';

let pokemonApp = angular.module('pokemonApp', []);

pokemonApp.controller('PokemonListControl', ($scope, $http) => {
	$scope.hasData = false;
	$scope.hasError = false;
	$http({
		method: 'GET',
		url: 'http://pokeapi.co/api/v2/pokemon/'
	}).then((response) => {
		$scope.pokemonList = response.data;
		$scope.hasData = true;
	}, (errorResponse) => {
		$scope.hasError = true;
		$scope.errorMessage = errorResponse;
	});
});