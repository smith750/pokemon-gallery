var webpack = require('webpack');
module.exports = {
    entry: {
        main: "./scripts/pokemon-react.jsx"
    },
	resolve: {
		extensions: ['', '.js', '.jsx']
	},
    output: {
        path: __dirname + '/build',
        filename: "[name].bundle.js"
    },
    module: {
        loaders: [
            { loader: "babel-loader", test: /\.jsx$/, query: { plugins: ['transform-runtime'], presets: ['es2015','stage-0','react'] }, exclude: /node_modules/ }
        ]
    },
    plugins: [
        new webpack.NoErrorsPlugin()
    ]

}