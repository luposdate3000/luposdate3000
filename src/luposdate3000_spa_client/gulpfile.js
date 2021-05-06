'use strict';

var gulp = require('gulp');
var browserSync = require('browser-sync');
var reload = browserSync.reload;

// load plugins
var $ = require('gulp-load-plugins')();


var handleError = function (err) {
    new $.util.log(err);
    this.emit('end');
}

gulp.task('styles', function () {
    return gulp.src('app/styles/main.scss')
        .pipe($.sass({errLogToConsole: true}))
        .pipe($.autoprefixer('last 1 version'))
        .pipe(gulp.dest('app/styles'))
        .pipe(reload({stream: true}))
        .pipe($.size())
        .pipe($.notify("Compilation complete."));
});

gulp.task('scripts', function () {
    return gulp.src('app/scripts/*.coffee')
        .pipe($.coffee({bare: true})).on('error', handleError)
        .pipe(reload({stream: true}))
        .pipe(gulp.dest('app/scripts/'))
        .pipe($.notify("Compilation complete."));
});

gulp.task('html', ['styles', 'scripts', 'JST', 'json'], function () {
    var jsFilter = $.filter('**/*.js');
    var cssFilter = $.filter('**/*.css');
//    var assets = $.useref.assets();

    return gulp.src('app/*.html')
//        .pipe(assets)
//        .pipe(assets.restore())
//        .pipe(jsFilter)
//        .pipe($.uglify())
//        .pipe(jsFilter.restore())
//        .pipe(cssFilter)
//        .pipe($.csso())
//        .pipe(cssFilter.restore())
//        .pipe(assets.restore())
        .pipe($.useref())
        .pipe(gulp.dest('dist'))
        .pipe($.size());
});

gulp.task('resources', function () {
    return gulp.src('app/resources/**/*')
        .pipe(gulp.dest('dist/resources'));
});

gulp.task('images', function () {
    return gulp.src('app/images/**/*')
        .pipe(gulp.dest('dist/images'))
        .pipe(reload({stream: true, once: true}))
        .pipe($.size());
});

gulp.task('fonts', function () {
    var streamqueue = require('streamqueue');
    return streamqueue({objectMode: true},
        gulp.src('app/bower_components/font-source-sans-pro/{EOT,OTF,TTF,WOFF}/**/*.{eot,svg,ttf,woff,otf}')
    )
        .pipe(gulp.dest('dist/styles'))
        .pipe($.size());
});

gulp.task('clean', function () {
    return gulp.src(['app/styles/main.css', 'dist'], {read: false}).pipe($.clean());
});

gulp.task('build', ['html', 'images', 'fonts', 'resources']);

gulp.task('default', ['clean'], function () {
    gulp.start('build');
});

gulp.task('serve', ['styles', 'scripts', 'JST', 'hjson', 'svg'], function () {
    browserSync({
        server: {
            baseDir: 'app'
        },
        debugInfo: false,
        open: false
    }, function (err, bs) {
        require('opn')(bs.options.urls.local);
        console.log('Started connect web server on ' + bs.options.urls.local);
    });
});

// inject bower components
gulp.task('wiredep', function () {
    var wiredep = require('wiredep').stream;
    gulp.src('app/styles/*.scss')
        .pipe(wiredep({
            directory: 'app/bower_components'
        }))
        .pipe(gulp.dest('app/styles'));
    gulp.src('app/*.html')
        .pipe(wiredep({
            directory: 'app/bower_components',
            exclude: ['bootstrap-sass-official']
        }))
        .pipe(gulp.dest('app'));
});

gulp.task('JST', function () {
    gulp.src('app/templates/**/*.html')
        .pipe($.jstConcat('app/scripts/jst.js', {
            renameKeys: ['^.*templates/(.*).html$', '$1']
        })).on('error', handleError)
        .pipe(gulp.dest('.'))
})

gulp.task('inject', function () {
    gulp.src('app/*.html')
        .pipe($.inject(
            // Include all js files in scripts except the ones at top level
            gulp.src(['app/scripts/**/*.js', '!app/scripts/main.js', '!app/scripts/loading.js'], {read: false}),
            {ignorePath: 'app/', addRootSlash: false}
        ))
        .pipe(gulp.dest('app'))
});

gulp.task('watch', ['serve'], function () {

    // watch for changes
    gulp.watch(['app/*.html'], reload);
    gulp.watch('app/styles/**/*.scss', ['styles']);
    gulp.watch('app/scripts/**/*.coffee', ['scripts']);
    gulp.watch('app/config/**/*.hjson', ['hjson']);
    gulp.watch('app/templates/**/*.html', ['JST']);
    gulp.watch('app/images/icons/*.svg', ['svg']);
    gulp.watch('bower.json', ['wiredep']);
});

gulp.task('hjson', function () {
    gulp.src(['app/config/*.hjson'])
        .pipe($.hjson({to: 'json'}))
        .pipe(gulp.dest('app/config'));
});

gulp.task('json', ['hjson'], function () {
    gulp.src('app/config/*.json')
        .pipe(gulp.dest('dist/config'));
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
    gulp.src('app/images/icons/*.svg')
        .pipe($.svgSprite(config))
        .pipe(gulp.dest('app/images'));
});

/**
 * Push build to gh-pages
 */
gulp.task('deploy', ['build'], function () {
    return gulp.src("./dist/**/*")
        .pipe($.ghPages())
});

gulp.task('coffeelint', function() {
    return gulp.src('./app/scripts/**/*.coffee')
        .pipe($.coffeelint())
        .pipe($.coffeelint.reporter());
});

gulp.task('jshint', function() {
    return gulp.src('./app/scripts/**/*.js')
        .pipe($.jshint())
        .pipe($.jshint.reporter('jshint-stylish'));
});
