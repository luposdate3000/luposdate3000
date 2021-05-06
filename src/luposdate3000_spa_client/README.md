Luposdate Single Page Client
============================

This SPA is built as a client to the [Luposdate](https://github.com/luposdate) SPARQL endpoint. 
It is aimed to replace the [Java applet](http://www.ifis.uni-luebeck.de/index.php?id=181&L=1) that is currently in use. 

Have a look [over here](http://hauptbenutzer.github.io/luposdate-spa-client) for a quick demo.
Prerequisites
-------------

In order to build this project you will need the following tools installed and available in your PATH. 

* [Node JS](https://nodejs.org/)
* [Bower](http://bower.io/)
* [Gulp](http://gulpjs.com/)

Build instructions
------------------

Clone this repository and run

```
$ npm install
$ bower install 
$ gulp
```

and you should have the built application in your `./dist` directory.

Alternatively checkout the [gh-pages branch](https://github.com/hauptbenutzer/luposdate-spa-client/tree/gh-pages), which contains the latest release in built form. 

Configuration
-------------
The client can, for the most part, be configured to suit your needs. For the available options have a look at the [config.hjson](app/config/config.hjson) file. It is recommended that you make your changes there and use `gulp hjson` to compile it. 

If the query parameter `?config={URL}` is present, an attempt to retrieve `{URL}` will be made. This URL is used as-is in the corresponding request, so it can be linking to a local or an external file. Keep in mind that, should you load an external file (i.e., different domain), the responding server will need to send a [CORS](http://en.wikipedia.org/wiki/Cross-origin_resource_sharing) header. Whatever options are set in the `{URL}` file, will overwrite the configuration in the standard `config.hjson`.