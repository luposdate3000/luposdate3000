// Generated using webpack-cli https://github.com/webpack/webpack-cli

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const isProduction = process.env.NODE_ENV == 'production';

const stylesHandler = 'style-loader';



const config = {
    entry: ['./app/scripts/main.js'],
target: "web",
    output: {
        path: path.resolve(__dirname, 'dist'),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: 'app/index.html',
        }),
        new CopyWebpackPlugin({
            patterns: [
                {
                    from: 'app/resources',
                    to: 'resources'
                },
                {
                    from: 'app/config',
                    to: 'config'
                },
                {
                    from: 'tonejs-instruments/samples',
                    to: 'resources/samples'
                }
            ]
        }),
    ],
    module: {
        rules: [{
                test: /\.html$/i,
                loader: "html-loader",
            },{
      test: /\.(?:js|mjs|cjs)$/,
      exclude: /node_modules/,
      use: {
        loader: 'babel-loader',
        options: {
          presets: [
            ['@babel/preset-env', { targets: "defaults" }]
          ]
        }
      }
    },
            {
                test: /\.s?[ac]ss$/i,
                use: [
                    // Creates `style` nodes from JS strings
                    "style-loader",
                    // Translates CSS into CommonJS
                    "css-loader",
                    // Compiles Sass to CSS
                    "sass-loader",
                ],
            },
        ],
    },
};

module.exports = () => {
    if (isProduction) {
        config.mode = 'production';
    } else {
        config.mode = 'development';
    }
    return config;
};
