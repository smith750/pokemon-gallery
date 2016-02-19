var webpack = require('webpack');
module.exports = {
    entry: {
        main: "./scripts/pokemon-backbone.js"
    },
	resolve: {
		extensions: ['','.js']
	},
    output: {
        path: __dirname + '/build',
        filename: "[name].bundle.js"
    },
    module: {
        loaders: [
            { loader: "babel-loader", test: /\.js$/, query: { plugins: ['transform-runtime'], presets: ['es2015','stage-0'] }, exclude: /node_modules/ }
        ]
    },
    plugins: [
        new webpack.NoErrorsPlugin()
    ]

}