const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const PATHS = {
    build: path.join(__dirname, 'target', 'resources')
};

module.exports = {
    devtool: 'inline-source-map',

    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src/'),
            css: path.resolve(__dirname, 'src/css/')
        }
    },

    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "[name].bundle.js"
    },

    plugins: [
        // new webpack.optimize.CommonsChunkPlugin({
        //     name: "vendor",
        //     filename: "[name].bundle.js",
        //     minChunks: Infinity
        // }),
        new HtmlWebpackPlugin(),
        new webpack.NamedModulesPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.ProvidePlugin({
            $: "jquery/dist/jquery.min.js",
            jQuery: "jquery/dist/jquery.min.js",
            "window.jQuery": "jquery/dist/jquery.min.js"
        })
    ],

    module: {
        rules: [
            // {
            //     test: /\.js$/,
            //     exclude: /node_modules/,
            //     use: "eslint-loader"
            // },
            {
                test: /\.m?js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },

    devServer: {
        contentBase: PATHS.build,
        port: 9090,
        proxy: {'*': "http://localhost:8080"},
        hot: true
    }
};