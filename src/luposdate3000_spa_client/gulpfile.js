'use strict';

const gulp = require('gulp'); //base module
const concat = require('gulp-concat'); //concat multiple files into one
const clean = require('gulp-clean'); //the clean target
const coffee = require('gulp-coffee'); //for coffe script
const sourcemaps = require('gulp-sourcemaps'); //adding the modified sourcemaps to the concatenated files
const merge = require('merge-stream'); //merging multiple inputstreams into one
const debug = require('gulp-debug');
const order = require("gulp-order"); //sorting the files, such that the initialisation ordering is ok
const hjson = require("gulp-hjson"); //json files which can include comments
const svgSprite = require("gulp-svg-sprite") //merging images into one

gulp.task('hjson', function() {
    return gulp.src([
            "app/config/config.hjson",
            "app/config/operators.hjson",
            "app/config/configWithSonification.hjson",
        ])
        .pipe(hjson({
            to: 'json'
        }))
        .pipe(gulp.dest('dist/config'));
});


gulp.task('concatCSS', function() {
    return gulp.src([
            "bower_components/foundation/css/foundation.css",
            "bower_components/codemirror/lib/codemirror.css",
            "bower_components/font-source-sans-pro/source-sans-pro.css",
            "bower_components/codemirror/addon/fold/foldgutter.css",
            "bower_components/spectrum/spectrum.css",
            "app/styles/main.css",
        ])
        .pipe(sourcemaps.init({
            loadMaps: true,
            largeFile: true
        }))
        .pipe(concat('vendor.css'))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('dist/styles/'));
});


gulp.task('concatJS', function() {
    return merge(
            gulp.src([
                //the order of files doe NOT matter here
                // dependencies of dependencies ...
                "bower_components/modernizr/modernizr.js", // dependency of "foundation.js"
                "bower_components/underscore/underscore.js", // dependency of "backbone.js"
                //
                "bower_components/fastclick/lib/fastclick.js", // optimization for touchscreen devices
                "bower_components/randomcolor/randomColor.js", // syntax highlighing in result
                "bower_components/codemirror/mode/sparql/sparql.js", // syntax highlighting in sparql + rdf
                "bower_components/uri.js/src/URI.js", // for executing the queries
                "bower_components/jquery/dist/jquery.js", // accessing buttons
                "bower_components/foundation/js/foundation.js",
                "bower_components/codemirror/lib/codemirror.js", //sparql + rdf editor
                "bower_components/codemirror/addon/edit/matchbrackets.js", // highlighting of brackets in sparql
                "bower_components/codemirror/addon/edit/closebrackets.js", // automatically add a closing bracket if you type an opening bracket
                "bower_components/x2js/xml2json.min.js",
                "node_modules/tone/build/Tone.js", // used by sonification - sound library
                "tonejs-instruments/Tonejs-Instruments.js", // used by sonification - instrument-sound files lib
                "node_modules/nexusui/dist/NexusUI.js", // used by sonification - config-sliders and checkboxes
                "bower_components/spectrum/spectrum.js", // used by sonification


                "app/scripts/algos/visualisationUtil/vis-network.min.js", // used by sonification
                "app/scripts/algos/visualisationUtil/visualizationUtil.js", // used by sonification
                "app/scripts/algos/visualisationUtil/pitchMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/spatializationMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/melodyMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/loudnessMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/instrumentMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/durationMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/chordMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/octaveMapping.js", // used by sonification
                "app/scripts/algos/visualisationUtil/globalMapping.js", // used by sonification

                "app/scripts/algos/createGraph.js",
                "app/scripts/algos/createOPGraph.js",
                "app/scripts/codemirror-modes/rif/rif.js",
                "app/scripts/codemirror-modes/N3/N3.js",
                "app/scripts/algos/visualization.js", // used by sonification
                //LUPOSDATE3000 GENERATED CODE START
"app/scripts/algos/luposdate3000/kotlin-kotlin-stdlib-js-ir.js",
"app/scripts/algos/luposdate3000/KotlinBigInteger-bignum-js-ir.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Shared_JS.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Shared.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Parser.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Test_Buffermanager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Jena_Wrapper.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Id_Triple.js",
"app/scripts/algos/luposdate3000/Luposdate3000_VK.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Buffer_Manager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Result_Format.js",
"app/scripts/algos/luposdate3000/Luposdate3000_KV.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Dictionary.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Base.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Arithmetik.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Logical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Physical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Ast.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Logical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Manager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Physical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Factory.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Endpoint.js",
                //LUPOSDATE3000 GENERATED CODE END
                "app/scripts/main.js",
            ]),
//            gulp.src([
//                "app/scripts/main.coffee",
//                "app/scripts/loading.coffee",
//            ])
//            .pipe(coffee({
//                bare: true
//            })).on('error', function(err) {
//                console.log(err)
//            })
        )
        .pipe(sourcemaps.init({
            loadMaps: true,
            largeFile: true
        }))
        .pipe(order([
            //the order of files IS important here
            "bower_components/modernizr/modernizr.js",
            "bower_components/jquery/dist/jquery.js",
            "bower_components/fastclick/lib/fastclick.js",
            "bower_components/foundation/js/foundation.js",
            "bower_components/underscore/underscore.js",
            "bower_components/codemirror/lib/codemirror.js",
            "bower_components/codemirror/addon/edit/matchbrackets.js",
            "bower_components/codemirror/addon/edit/closebrackets.js",
            "bower_components/x2js/xml2json.min.js",
            "bower_components/randomcolor/randomColor.js",
            "bower_components/uri.js/src/URI.js",
            "bower_components/codemirror/mode/sparql/sparql.js",
            "node_modules/tone/build/Tone.js",
            "tonejs-instruments/Tonejs-Instruments.js",
            "app/scripts/algos/visualisationUtil/vis-network.min.js",
            "node_modules/nexusui/dist/NexusUI.js",
            "app/scripts/algos/visualisationUtil/visualizationUtil.js",
            "app/scripts/algos/visualisationUtil/pitchMapping.js",
            "app/scripts/algos/visualisationUtil/spatializationMapping.js",
            "app/scripts/algos/visualisationUtil/melodyMapping.js",
            "app/scripts/algos/visualisationUtil/loudnessMapping.js",
            "app/scripts/algos/visualisationUtil/instrumentMapping.js",
            "app/scripts/algos/visualisationUtil/durationMapping.js",
            "app/scripts/algos/visualisationUtil/chordMapping.js",
            "app/scripts/algos/visualisationUtil/octaveMapping.js",
            "bower_components/spectrum/spectrum.js",
            //LUPOSDATE3000 GENERATED CODE START
"app/scripts/algos/luposdate3000/kotlin-kotlin-stdlib-js-ir.js",
"app/scripts/algos/luposdate3000/KotlinBigInteger-bignum-js-ir.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Shared_JS.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Shared.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Parser.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Test_Buffermanager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Jena_Wrapper.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Id_Triple.js",
"app/scripts/algos/luposdate3000/Luposdate3000_VK.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Buffer_Manager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Result_Format.js",
"app/scripts/algos/luposdate3000/Luposdate3000_KV.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Dictionary.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Base.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Arithmetik.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Logical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Physical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Ast.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Logical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Triple_Store_Manager.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Optimizer_Physical.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Operator_Factory.js",
"app/scripts/algos/luposdate3000/Luposdate3000_Endpoint.js",
            //LUPOSDATE3000 GENERATED CODE END
            "app/scripts/algos/createGraph.js",
            "app/scripts/algos/createOPGraph.js",
            "app/scripts/codemirror-modes/rif/rif.js",
            "app/scripts/codemirror-modes/N3/N3.js",
            "app/scripts/algos/visualization.js",
            "app/scripts/main.js",
        ], {
            base: './'
        }))
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('dist/scripts/'));
});

gulp.task('html', gulp.series('concatCSS', 'concatJS', 'hjson', function() {
    return gulp.src([
            "app/index.html",
        ])
        .pipe(gulp.dest('dist'));
}));

gulp.task('resources', function() {
    return merge(
        gulp.src([
            "app/resources/N3/lubm.n3",
            "app/resources/N3/yagodata.n3",
            "app/resources/N3/sp2b.n3",
        ])
        .pipe(gulp.dest('dist/resources/N3')),
        gulp.src([
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
        .pipe(gulp.dest('dist/resources/Sparql')),
        gulp.src([
            "app/resources/RIF/rule_equality.rif",
            "app/resources/RIF/rule_And.rif",
            "app/resources/RIF/rule_comparison.rif",
            "app/resources/RIF/rule_fibonacci.rif",
            "app/resources/RIF/rule_exists.rif",
            "app/resources/RIF/rule_functional.rif",
            "app/resources/RIF/rule_assignment.rif",
        ])
        .pipe(gulp.dest('dist/resources/RIF')),
        gulp.src([
            "app/resources/uebungen/CloudAndWebTechnologiesSparql1/Luebeck.n3",
            "app/resources/uebungen/CloudAndWebTechnologiesSparql1/s1a.sparql",
            "app/resources/uebungen/CloudAndWebTechnologiesSparql1/config.json",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/CloudAndWebTechnologiesSparql1')),
        gulp.src([
            "app/resources/uebungen/CloudAndWebTechnologiesOWL1/s1aa.sparql",
            "app/resources/uebungen/CloudAndWebTechnologiesOWL1/s1a.n3",
            "app/resources/uebungen/CloudAndWebTechnologiesOWL1/s1ac.sparql",
            "app/resources/uebungen/CloudAndWebTechnologiesOWL1/config.json",
            "app/resources/uebungen/CloudAndWebTechnologiesOWL1/s1ab.sparql",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/CloudAndWebTechnologiesOWL1')),
        gulp.src([
            "app/resources/uebungen/SemanticWebSparql11_1/query1.rq",
            "app/resources/uebungen/SemanticWebSparql11_1/config.json",
            "app/resources/uebungen/SemanticWebSparql11_1/sp2b.n3",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/SemanticWebSparql11_1')),
        gulp.src([
            "app/resources/uebungen/SemanticWebOWL1/DriversWithoutRequiredLicence.sparql",
            "app/resources/uebungen/SemanticWebOWL1/RequiredLicencesOfCE.sparql",
            "app/resources/uebungen/SemanticWebOWL1/TomBelongsToPersonWithLicence.sparql",
            "app/resources/uebungen/SemanticWebOWL1/EuropeanDrivingLicences.n3",
            "app/resources/uebungen/SemanticWebOWL1/config.json",
            "app/resources/uebungen/SemanticWebOWL1/TomBelongsToDriver.sparql",
            "app/resources/uebungen/SemanticWebOWL1/DriversWithIncorrectLicence.sparql",
            "app/resources/uebungen/SemanticWebOWL1/DriversWithCorrectLicence.sparql",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/SemanticWebOWL1')),
        gulp.src([
            "app/resources/uebungen/SemanticWebOwl2Rdf/EuropeanDrivingLicences.n3",
            "app/resources/uebungen/SemanticWebOwl2Rdf/transitivitySubClassOf.rif",
            "app/resources/uebungen/SemanticWebOwl2Rdf/config.json",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/SemanticWebOwl2Rdf')),
        gulp.src([
            "app/resources/uebungen/SemanticWebRIF1/empty.txt",
            "app/resources/uebungen/SemanticWebRIF1/ruleset1.rif",
            "app/resources/uebungen/SemanticWebRIF1/config.json",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/SemanticWebRIF1')),
        gulp.src([
            "app/resources/uebungen/SemanticWebSparql10_1/lubm_demo.n3",
            "app/resources/uebungen/SemanticWebSparql10_1/query1.rq",
            "app/resources/uebungen/SemanticWebSparql10_1/config.json",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/SemanticWebSparql10_1')),
        gulp.src([
            "app/resources/uebungen/CloudAndWebTechnologiesRIF1/traversalWithPath.rif",
            "app/resources/uebungen/CloudAndWebTechnologiesRIF1/empty.txt",
            "app/resources/uebungen/CloudAndWebTechnologiesRIF1/config.json",
        ])
        .pipe(gulp.dest('dist/resources/uebungen/CloudAndWebTechnologiesRIF1')),
    )
});
gulp.task('instruments', function() {
    return gulp.src('tonejs-instruments/samples/**/*')
        .pipe(gulp.dest('dist/resources/samples'));
});

gulp.task('images', function() {
    return merge(
            gulp.src([
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
            })),
            gulp.src([
                "app/images/logo.svg",
                "app/images/favicon.png",
            ])
        )
        .pipe(gulp.dest('dist/images'));
});

gulp.task('fonts', function() {
    return merge(
        gulp.src([
            "bower_components/font-source-sans-pro/OTF/SourceSansPro-BoldIt.otf",
            "bower_components/font-source-sans-pro/OTF/SourceSansPro-Bold.otf",
            "bower_components/font-source-sans-pro/OTF/SourceSansPro-Regular.otf",
            "bower_components/font-source-sans-pro/OTF/SourceSansPro-Semibold.otf",
        ])
        .pipe(gulp.dest('dist/styles/OTF')),
        gulp.src([
            "bower_components/font-source-sans-pro/WOFF/OTF/SourceSansPro-BoldIt.otf.woff",
            "bower_components/font-source-sans-pro/WOFF/OTF/SourceSansPro-Bold.otf.woff",
            "bower_components/font-source-sans-pro/WOFF/OTF/SourceSansPro-Regular.otf.woff",
            "bower_components/font-source-sans-pro/WOFF/OTF/SourceSansPro-Semibold.otf.woff",
        ])
        .pipe(gulp.dest('dist/styles/WOFF/OTF'))
    );
});

gulp.task('clean', function() {
    return gulp.src([
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
