'use strict';

const gulp = require('gulp');
const jstConcat = require("gulp-jst-concat")
const concat = require('gulp-concat');
const clean = require('gulp-clean');
const coffee = require('gulp-coffee');
const sourcemaps = require('gulp-sourcemaps');
const merge = require('merge-stream');
const debug = require('gulp-debug');
const order = require("gulp-order");
const sass = require("gulp-sass");
const hjson = require("gulp-hjson");
const util = require("gulp-util");
const svgSprite = require("gulp-svg-sprite")
const handleError = function(err) {
    new util.log(err);
    this.emit('end');
}

gulp.task('hjson', function() {
    return gulp
        .src([
            "app/config/config.hjson",
            "app/config/operators.hjson",
        ])
        .pipe(hjson({
            to: 'json'
        }))
        .pipe(gulp.dest('dist/config'));
});


gulp.task('concatCSS', function() {
    var css_other = gulp
        .src([
            "bower_components/foundation/css/foundation.css",
            "bower_components/codemirror/lib/codemirror.css",
            "bower_components/font-source-sans-pro/source-sans-pro.css",
            "bower_components/please-wait/build/please-wait.css",
            "bower_components/codemirror/addon/fold/foldgutter.css",
            "app/styles/spectrum.css",
        ])
    var css_sass = gulp
        .src([
            "app/styles/main.scss",
        ])
        .pipe(sass({
            errLogToConsole: true
        }))
    return merge(css_other, css_sass)
        .pipe(concat('vendor.css'))
        .pipe(gulp.dest('dist/styles/'));
});


gulp.task('concatJS', function() {
    var js_other = gulp
        .src([
            "bower_components/modernizr/modernizr.js",
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
            "node_modules/tone/build/Tone.js",
            "node_modules/tonejs-instruments#8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6/Tonejs-Instruments.js",
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
            "app/scripts/algos/luposdate3000/kotlin.js",
            "app/scripts/algos/luposdate3000/KotlinBigInteger-bignum-jsLegacy.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Shared_JS.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Shared.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Parser.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Jena_Wrapper.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Endpoint_Launcher.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Buffer_Manager.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_KV.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Id_Triple.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_VK.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Dictionary.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Base.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Arithmetik.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Logical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Physical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Ast.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Logical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Manager.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Result_Format.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Physical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Factory.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Distributed_Query.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Endpoint.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Test.js",
            //LUPOSDATE3000 GENERATED CODE END
            "app/scripts/algos/createGraph.js",
            "app/scripts/algos/createOPGraph.js",
            "app/scripts/codemirror-modes/rif/rif.js",
            "app/scripts/codemirror-modes/N3/N3.js",
            "app/scripts/algos/visualization.js",
        ])
    var js_coffee = gulp
        .src([
            "app/scripts/main.coffee",
            "app/scripts/loading.coffee",
        ])
        .pipe(coffee({
            bare: true
        })).on('error', handleError)
    var js_jst = gulp
        .src([
            "app/templates/spinner.html",
            "app/templates/results/boolean.html",
            "app/templates/results/standalone.html",
            "app/templates/results/error.html",
            "app/templates/results/none.html",
            "app/templates/results/prefixes.html",
            "app/templates/results/prefix.html",
            "app/templates/results.html",
            "app/templates/query_picker.html",
        ])
        .pipe(jstConcat('jst.js', {
            renameKeys: ['^.*templates/(.*).html$', '$1']
        })).on('error', handleError)
    return merge(js_other, js_coffee, js_jst)
        .pipe(sourcemaps.init({
            loadMaps: true,
            largeFile: true
        }))
        .pipe(order([
            "bower_components/modernizr/modernizr.js",
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
            "node_modules/tone/build/Tone.js",
            "node_modules/tonejs-instruments#8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6/Tonejs-Instruments.js",
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
            "app/scripts/algos/luposdate3000/kotlin.js",
            "app/scripts/algos/luposdate3000/KotlinBigInteger-bignum-jsLegacy.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Shared_JS.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Shared.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Parser.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Jena_Wrapper.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Endpoint_Launcher.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Buffer_Manager.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_KV.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Id_Triple.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_VK.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Dictionary.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Base.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Arithmetik.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Logical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Physical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Ast.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Logical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Manager.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Result_Format.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Physical.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Operator_Factory.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Distributed_Query.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Endpoint.js",
            "app/scripts/algos/luposdate3000/Luposdate3000_Test.js",
            //LUPOSDATE3000 GENERATED CODE END
            "app/scripts/algos/createGraph.js",
            "app/scripts/algos/createOPGraph.js",
            "app/scripts/codemirror-modes/rif/rif.js",
            "app/scripts/codemirror-modes/N3/N3.js",
            "app/scripts/algos/visualization.js",
            "app/scripts/loading.js",
            "app/scripts/main.js",
        ], {
            base: './'
        }))
        .pipe(debug())
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('dist/scripts/'));
});

gulp.task('html', gulp.series('concatCSS', 'concatJS', 'hjson', function() {
    return gulp
        .src([
            "app/index.html",
            "app/templates/spinner.html",
            "app/templates/results/boolean.html",
            "app/templates/results/standalone.html",
            "app/templates/results/error.html",
            "app/templates/results/none.html",
            "app/templates/results/prefixes.html",
            "app/templates/results/prefix.html",
            "app/templates/results.html",
            "app/templates/query_picker.html",
        ])
        .pipe(gulp.dest('dist'));
}));

gulp.task('resources', function() {
    var resources_n3 = gulp
        .src([
            "app/resources/N3/lubm.n3",
            "app/resources/N3/yagodata.n3",
            "app/resources/N3/sp2b.n3",
        ])
        .pipe(gulp.dest('dist/resources/N3'));
    var resources_sparql = gulp
        .src([
            "app/resources/Sparql/lubm_asktest.sparql",
            "app/resources/Sparql/lubm_test.sparql",
            "app/resources/Sparql/Merge.sparql",
            "app/resources/Sparql/OneTriplePattern.sparql",
            "app/resources/Sparql/endpoint_test.sparql",
            "app/resources/Sparql/lubm_constructtest.sparql",
            "app/resources/Sparql/Filter.sparql",
            "app/resources/Sparql/Optional-Clause.sparql",
            "app/resources/Sparql/Hash-Map.sparql",
        ])
        .pipe(gulp.dest('dist/resources/Sparql'));
    var resources_rif = gulp
        .src([
            "app/resources/RIF/rule_equality.rif",
            "app/resources/RIF/rule_And.rif",
            "app/resources/RIF/rule_comparison.rif",
            "app/resources/RIF/rule_fibonacci.rif",
            "app/resources/RIF/rule_exists.rif",
            "app/resources/RIF/rule_functional.rif",
            "app/resources/RIF/rule_assignment.rif",
        ])
        .pipe(gulp.dest('dist/resources/RIF'));
    return merge(resources_n3, resources_sparql, resources_rif)
});
gulp.task('instruments', function() {
    return gulp
        .src('node_modules/tonejs-instruments#8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6/samples/**/*')
        .pipe(gulp.dest('dist/resources/samples'));
});

gulp.task('images', function() {
    var image_svgs = gulp
        .src([
            "app/images/icons/fullscreen.svg",
            "app/images/icons/fullscreen-close.svg",
            "app/images/icons/screen-query.svg",
            "app/images/icons/screen-both.svg",
        ])
        .pipe(svgSprite({
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
        }))
    var image_other = gulp
        .src([
            "app/images/logo.svg",
            "app/images/favicon.png",
        ])
    return merge(image_svgs, image_other)
        .pipe(gulp.dest('dist/images'));
});

gulp.task('fonts', function() {
    return gulp
        .src(
            "bower_components/font-source-sans-pro/{EOT,OTF,TTF,WOFF}/**/*.{eot,svg,ttf,woff,otf}",
        )
        .pipe(gulp.dest('dist/styles'));
});

gulp.task('clean', function() {
    return gulp
        .src([
            "dist"
        ], {
            allowEmpty: true,
            read: false
        }).pipe(clean());
});


gulp.task('build', gulp.series('html', 'images', 'resources', 'fonts', 'instruments', function(done) {
    console.log('build done');
    done();
}));

gulp.task('default', gulp.series('clean', 'build', function(done) {
    console.log('done');
    done();
}));