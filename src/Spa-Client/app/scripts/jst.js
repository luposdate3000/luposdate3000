this.JST = {"query_picker": function(obj) {
obj || (obj = {});
var __t, __p = '', __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
with (obj) {

 _.each(options, function(option) { ;
__p += '\r\n    <option value="' +
((__t = (option )) == null ? '' : __t) +
'">\r\n        ' +
((__t = ( App.baseName(option) )) == null ? '' : __t) +
'\r\n    </option>\r\n';
 }); ;
__p += '\r\n\r\n';

}
return __p
},
"results": function(obj) {
obj || (obj = {});
var __t, __p = '', __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
with (obj) {
__p +=
((__t = ( JST['results/prefixes']({prefixes: prefixes, colors: colors}) )) == null ? '' : __t) +
'\r\n\r\n';
 _.each(resultSets, function(resultSet) { ;
__p += '\r\n    <h4>' +
((__t = ( resultSet.name )) == null ? '' : __t) +
' (' +
((__t = ( resultSet.results.length )) == null ? '' : __t) +
')</h4>\r\n    ';
 if(resultSet.results.length) { ;
__p += '\r\n    <table class="results">\r\n        <thead>\r\n            <tr>\r\n            ';
 _.each(resultSet.head, function(variable) { ;
__p += '\r\n                <th>\r\n                ' +
((__t = ( _.escape(variable) )) == null ? '' : __t) +
'\r\n                </th>\r\n            ';
 }); ;
__p += '\r\n            </tr>\r\n        </thead>\r\n        <tbody>\r\n            ';
 _.each(resultSet.results, function(result) { ;
__p += '\r\n                <tr>\r\n                    ';
 _.each(result, function(bind) { ;
__p += '\r\n                        <td>' +
((__t = ( bind )) == null ? '' : __t) +
'</td>\r\n                    ';
 }); ;
__p += '\r\n                </tr>\r\n            ';
 }); ;
__p += '\r\n        </tbody>\r\n    </table>\r\n    ';
 }; ;
__p += '\r\n';
 }); ;
__p += '\r\n';

}
return __p
},
"spinner": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<h4>Waiting for Server..</h4>\r\n<div class=\'sk-spinner sk-spinner-cube-grid dark\'>\r\n<div class=\'sk-cube\'></div><div class=\'sk-cube\'></div><div class=\'sk-cube\'></div>\r\n<div class=\'sk-cube\'></div><div class=\'sk-cube\'></div><div class=\'sk-cube\'></div>\r\n<div class=\'sk-cube\'></div><div class=\'sk-cube\'></div><div class=\'sk-cube\'></div></div>';

}
return __p
},
"results/boolean": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<h4>Boolean</h4>\r\n<p>' +
((__t = ( boolean )) == null ? '' : __t) +
'</p>';

}
return __p
},
"results/error": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<h4><i class="fa fa-exclamation-triangle"></i> Error</h4>\r\n<p>The Server responded with:</p>\r\n<pre>\r\n' +
((__t = ( _.escape(msg) )) == null ? '' : __t) +
'\r\n</pre>';

}
return __p
},
"results/none": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<h4>Server returned no identifiable results.</h4>\r\n<pre>\r\n' +
((__t = ( _.escape(plain) )) == null ? '' : __t) +
'\r\n</pre>';

}
return __p
},
"results/prefix": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<span class="sparql-prefix" style="color: ' +
((__t = ( color )) == null ? '' : __t) +
'">' +
((__t = ( prefix )) == null ? '' : __t) +
':</span>\r\n';

}
return __p
},
"results/prefixes": function(obj) {
obj || (obj = {});
var __t, __p = '', __j = Array.prototype.join;
function print() { __p += __j.call(arguments, '') }
with (obj) {

 if(Object.keys(prefixes).length) { ;
__p += '\r\n<h4>Prefixes</h4>\r\n<table>\r\n    ';
 _.each(prefixes, function(value, prefix) { ;
__p += '\r\n        <tr>\r\n            <td class="sparql-prefix" style="color: ' +
((__t = ( colors[prefix] )) == null ? '' : __t) +
'">' +
((__t = ( prefix )) == null ? '' : __t) +
'</td>\r\n            <td>' +
((__t = ( value )) == null ? '' : __t) +
'</td>\r\n        </tr>\r\n    ';
 }); ;
__p += '\r\n</table>\r\n';
 } ;
__p += '\r\n';

}
return __p
},
"results/standalone": function(obj) {
obj || (obj = {});
var __t, __p = '';
with (obj) {
__p += '<html class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"><head>\r\n    <link rel="shortcut icon" href="images/favicon.png" type="image/png">\r\n    <meta charset="utf-8">\r\n    <title>Result of Query</title>\r\n    <meta name="description" content="">\r\n    <meta name="viewport" content="width=device-width, initial-scale=1">\r\n\r\n    <link rel="stylesheet" href="https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/vendor.css">\r\n\r\n    <!-- TODO: Keep fonts in this repo? -->\r\n    <link href="//fonts.googleapis.com/css?family=Source+Code+Pro:400,700" rel="stylesheet" type="text/css">\r\n    <link href="//fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet" type="text/css">\r\n    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">\r\n\r\n    <link rel="stylesheet" href="https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/main.css">\r\n\r\n<meta class="foundation-data-attribute-namespace"><meta class="foundation-mq-xxlarge"><meta class="foundation-mq-xlarge-only"><meta class="foundation-mq-xlarge"><meta class="foundation-mq-large-only"><meta class="foundation-mq-large"><meta class="foundation-mq-medium-only"><meta class="foundation-mq-medium"><meta class="foundation-mq-small-only"><meta class="foundation-mq-small"><style></style><style></style><meta class="foundation-mq-topbar"></head>\r\n<body style="margin: 20pt">\r\n<!--[if lt IE 10]>\r\n<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade\r\n    your browser</a> to improve your experience.</p>\r\n<![endif]-->\r\n<h1>Result of Query</h1>\r\n\r\n' +
((__t = ( content )) == null ? '' : __t) +
'\r\n\r\n</body></html>';

}
return __p
}};