import Backbone from 'backbone';

let app = {};

app.PokemonList = Backbone.Model.extend({
	defaults: {
		pokemons: []
	}
});

const AppView = Backbone.View.extend({
	el: '#main',
	
	initialize() {
		this.render();
	},
	
	render() {
		this.$el.html("hi!");
	}
});

const appView = new AppView();