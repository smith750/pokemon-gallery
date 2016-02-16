import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class PokemonTable extends React.Component {
	constructor() {
		super();
		this.state = {pokemon: []};
	}
	
	componentWillMount() {
		axios.get('http://pokeapi.co/api/v2/pokemon/')
			.then((response) => {
				const newState = {pokemon: response.data};
				this.setState(newState);
			})
			.catch((response) => {
				console.error("Could not access resource, "+response);
			})
	}

	render() {
		let table = (<div></div>);
		if (this.state.pokemon.length > 0) {
			const dataRows = this.state.pokemon.map((pokemonObject) => (<tr key={pokemonObject.name}><td>{pokemonObject.name}</td></tr>));
			table = (<table><tbody><tr key="header"><th>Pokemon Name</th></tr>{dataRows}</tbody></table>);
		}
		return table;
	}
}

class Main extends React.Component {
	render() {
		return (<PokemonTable/>);
	}
}

ReactDOM.render(<Main/>, document.getElementById('main'));