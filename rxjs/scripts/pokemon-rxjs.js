import Rx from 'rx-dom';
import $ from 'jquery';

const pokemonListSource = Rx.DOM.get('http://pokeapi.co/api/v2/pokemon/');

function formatPokemonToTable(pokemonListJson) {
	if (pokemonListJson.response) {
		const retrievedNamesAsTableCells = JSON.parse(pokemonListJson.response).map((pokemon) => pokemon.name).map((pokemonName) => "<tr><td>"+pokemonName+"</td></tr>").join("");
		return "<table><tr><th>Pokemon</th><tbody>"+retrievedNamesAsTableCells+"</tbody></table>";
	} else {
		return "<span>No data was retrieved.</span>";
	}
}

pokemonListSource.subscribe(
	(value) => { $("#main").append(formatPokemonToTable(value)); },
	(error) => { $("main").text("Could not retrieve value; error = "+error); },
	() => { console.log("completed retrieving values"); }
);
