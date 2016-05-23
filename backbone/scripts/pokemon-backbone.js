import {Backbone, Store} from 'backbone';

let app = {};

app.Pokemon = Backbone.Model.extend({
	defaults: {
		name: '';
	}
});

app.PokemonList = Backbone.Collection.extend({
	model: app.Pokemon,
	localStorage: new Store("backbone-pokemon");
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