'use strict';

var gulp = require('gulp');

// load plugins
var $ = require('gulp-load-plugins')();

var concat = require('gulp-concat');

var handleError = function (err) {
    new $.util.log(err);
    this.emit('end');
}

gulp.task('hjson', function () {
    return gulp.src(['app/config/*.hjson'])
        .pipe($.hjson({to: 'json'}))
        .pipe(gulp.dest('dist/config'));
});

gulp.task('styles', function () {
    return gulp.src('app/styles/main.scss')
        .pipe($.sass({errLogToConsole: true}))
        .pipe(gulp.dest('dist/styles'));
});

gulp.task('concatCSS', function(){
  return gulp.src([
    "bower_components/foundation/css/foundation.css",
	"bower_components/codemirror/lib/codemirror.css",
	"bower_components/font-source-sans-pro/source-sans-pro.css",
	"bower_components/please-wait/build/please-wait.css",
	"bower_components/codemirror/addon/fold/foldgutter.css",
    "app/styles/spectrum.css"
  ])
    .pipe(concat('vendor.css'))
    .pipe(gulp.dest('dist/styles/'));
});

gulp.task('scripts', function () {
    return gulp.src('app/scripts/*.coffee')
        .pipe($.coffee({bare: true})).on('error', handleError)
        .pipe(gulp.dest('dist/scripts/'));
});

gulp.task('copyJS', function(){
    return gulp.src('app/scripts/**/*.js')
        .pipe(gulp.dest('dist/scripts/'));
});

gulp.task('concatJS', function(){
  return gulp.src(["bower_components/modernizr/modernizr.js",
 "bower_components/jquery/dist/jquery.js",
 "bower_components/fastclick/lib/fastclick.js",
 "bower_components/jquery.cookie/jquery.cookie.js",
 "bower_components/jquery-placeholder/jquery.placeholder.js",
 "bower_components/foundation/js/foundation.js",
 "bower_components/underscore/underscore.js",
 "bower_components/backbone/backbone.js",
 "bower_components/codemirror/lib/codemirror.js",
 "bower_components/x2js/xml2json.min.js",
 "bower_components/randomcolor/randomColor.js",
 "bower_components/uri.js/src/URI.js",
 "bower_components/uri.js/src/IPv6.js",
 "bower_components/uri.js/src/SecondLevelDomains.js",
 "bower_components/uri.js/src/punycode.js",
 "bower_components/uri.js/src/URITemplate.js",
 "bower_components/uri.js/src/jquery.URI.js",
 "bower_components/uri.js/src/URI.min.js",
 "bower_components/uri.js/src/jquery.URI.min.js",
 "bower_components/uri.js/src/URI.fragmentQuery.js",
 "bower_components/uri.js/src/URI.fragmentURI.js",
 "bower_components/codemirror/addon/edit/matchbrackets.js",
 "bower_components/codemirror/addon/edit/closebrackets.js",
 "bower_components/codemirror/mode/sparql/sparql.js",
 "bower_components/foundation/js/foundation/foundation.abide.js",
 "bower_components/foundation/js/foundation/foundation.accordion.js",
 "bower_components/foundation/js/foundation/foundation.alert.js",
 "bower_components/foundation/js/foundation/foundation.clearing.js",
 "bower_components/foundation/js/foundation/foundation.dropdown.js",
 "bower_components/foundation/js/foundation/foundation.equalizer.js",
 "bower_components/foundation/js/foundation/foundation.interchange.js",
 "bower_components/foundation/js/foundation/foundation.joyride.js",
 "bower_components/foundation/js/foundation/foundation.js",
 "bower_components/foundation/js/foundation/foundation.magellan.js",
 "bower_components/foundation/js/foundation/foundation.offcanvas.js",
 "bower_components/foundation/js/foundation/foundation.orbit.js",
 "bower_components/foundation/js/foundation/foundation.reveal.js",
 "bower_components/foundation/js/foundation/foundation.slider.js",
 "bower_components/foundation/js/foundation/foundation.tab.js",
 "bower_components/foundation/js/foundation/foundation.tooltip.js",
 "bower_components/foundation/js/foundation/foundation.topbar.js",
 "bower_components/please-wait/build/please-wait.js",
 "app/scripts/algos/visualisationUtil/Tonejs-Instruments.js",
  "app/scripts/algos/visualisationUtil/Tone.js",
  "app/scripts/algos/visualisationUtil/vis-network.min.js",
  "app/scripts/algos/visualisationUtil/NexusUI.js",
  "app/scripts/algos/visualisationUtil/visualizationUtil.js",
  "app/scripts/algos/visualisationUtil/pitchMapping.js",
  "app/scripts/algos/visualisationUtil/spatializationMapping.js",
  "app/scripts/algos/visualisationUtil/melodyMapping.js",
  "app/scripts/algos/visualisationUtil/loudnessMapping.js",
  "app/scripts/algos/visualisationUtil/instrumentMapping.js",
  "app/scripts/algos/visualisationUtil/durationMapping.js",
  "app/scripts/algos/visualisationUtil/chordMapping.js",
  "app/scripts/algos/visualisationUtil/octaveMapping.js",
  "app/scripts/algos/visualisationUtil/spectrum.js",
//LUPOSDATE3000 GENERATED CODE START
  "app/scripts/algos/visualisationUtil/kotlin.js",
  "app/scripts/algos/visualisationUtil/KotlinBigInteger-bignum-jsLegacy.js",
  "app/scripts/algos/visualisationUtil/krypto-root-krypto.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Shared_BrowserJS/Luposdate3000_Shared_JS.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Shared.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Buffer_Manager_Inmemory/Luposdate3000_Buffer_Manager.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Endpoint_Launcher_None/Luposdate3000_Endpoint_Launcher.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Jena_Wrapper_Off/Luposdate3000_Jena_Wrapper.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_KV.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Parser.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Triple_Store_Id_Triple.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_VK.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Dictionary.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Operator_Base.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Operator_Arithmetik.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Operator_Logical.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Operator_Physical.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Optimizer_Ast.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Optimizer_Logical.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Result_Format.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Triple_Store_Manager.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Operator_Factory.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Optimizer_Distributed_Query.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Optimizer_Physical.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Endpoint.js",
  "app/scripts/algos/bin_Threads_NoInline_Debug/Luposdate3000_Test.js"
//LUPOSDATE3000 GENERATED CODE END
])
    .pipe(concat('vendor.js'))
    .pipe(gulp.dest('dist/scripts/'));
});

gulp.task('svg', function () {
    var config = {
        shape: {
            id: {
                separator: '-'
            }
        },
        mode: {
            symbol: {
                dest: 'sprites',
                sprite: 'svgs.svg'
            }
        }
    };
    return gulp.src('app/images/icons/*.svg')
        .pipe($.svgSprite(config))
        .pipe(gulp.dest('dist/images'));
});

gulp.task('JST', function () {
    return gulp.src('app/templates/**/*.html')
        .pipe($.jstConcat('jst.js', {
            renameKeys: ['^.*templates/(.*).html$', '$1']
        })).on('error', handleError)
        .pipe(gulp.dest('dist/scripts'))
})

gulp.task('html', gulp.series('styles', 'concatCSS', 'scripts', 'JST', 'copyJS', 'concatJS', 'hjson', function () {
    var jsFilter = $.filter('**/*.js');
    var cssFilter = $.filter('**/*.css');

    return gulp.src('app/*.html')
        .pipe(gulp.dest('dist'));
}));

gulp.task('resources', function () {
    return gulp.src('app/resources/**/*')
        .pipe(gulp.dest('dist/resources'));
});

gulp.task('images', function () {
    return gulp.src('app/images/**/*')
        .pipe(gulp.dest('dist/images'));
});

gulp.task('fonts', function () {
    var streamqueue = require('streamqueue');
    return streamqueue({objectMode: true},
        gulp.src('app/bower_components/font-source-sans-pro/{EOT,OTF,TTF,WOFF}/**/*.{eot,svg,ttf,woff,otf}')
    )
        .pipe(gulp.dest('dist/styles'));
});

gulp.task('clean', function () {
    return gulp.src(['dist'], {allowEmpty:true, read: false}).pipe($.clean());
});


gulp.task('build', gulp.series('html', 'svg', 'images', 'fonts', 'resources', function (done) {
    console.log('build done');
	done();
}));

gulp.task('default', gulp.series('clean', 'build', function (done) {
    console.log('done');
	done();
}));
