/*
 * decaffeinate suggestions:
 * DS101: Remove unnecessary use of Array.from
 * DS102: Remove unnecessary code created because of implicit returns
 * DS205: Consider reworking code to avoid use of IIFEs
 * DS207: Consider shorter variations of null checks
 * DS208: Avoid top-level this
 */
// Attach App as global variable for debugging


//require('nexusui/dist/NexusUI.js')  // used by sonification - config-sliders and checkboxes
//require('tone/build/Tone.js') // used by sonification - sound library
//require('./algos/luposdate3000/Luposdate3000_Endpoint.js')

this.App = {
    isMergeView: false
};

function myEscape(x){
x=""+x
x = x.replace("<","&lt;")
x = x.replace(">","&gt;")
return x
}
App.init = function() {
    initLuposdate3000();
    App.samples = {};
    App.globalAnimationList = [];
    App.luposdate3000Instance = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.initialize();
    App.luposdate3000Instance.useDictionaryInlineEncoding = false;
    App.mappingIdentifiers = {
        Pitch: '#pitchSettings',
        Instrument: '#instrumentSettings',
        Loudness: '#loudnessSettings',
        Spatialization: '#spatializationSettings',
        Duration: '#durationSettings',
        Melody: '#melodySettings',
        Chord: '#chordSettings',
        Octave: '#octaveSettings'
    };
    App.leftTabs = ["sparql", "rdf", "rif"];
    App.rightTabs = ["result", "graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
    App.additionalHiddenTabs = App.rightTabs;
    App.mappingFunctions = {};
    chordSetup();
    durationSetup();
    instrumentSetup();
    loudnessSetup();
    melodySetup();
    octaveSetup();
    pitchSetup();
    spatializationSetup();

    // Load xml converter
    App.x2js = new X2JS({
        attributeArray: '_attributes'
    });

    // Load configuration
    App.URIQuery = URI(document.location.href).query(true);

    $.getJSON('config/operators.json').done(function(dataOp) {
        App.operators = dataOp;
        $.getJSON('config/config.json').done(function(dataConf) {
            App.config = dataConf;
            if (App.URIQuery.config) {
                $.getJSON(App.URIQuery.config).done(function(addData) {
                    //merge the default config with the provided url-config
                    App.config = $.extend(data, addData, {});
                    App.play();
                });
            } else {
                App.play();
            }
        });
    });
};


App.play = function() {
    App.loadEditors();
    App.bindEvents();
    // App.initConfigComponents after App.bindEvents
    // App.initConfigComponents after loading App.config
    App.initConfigComponents();
    App.insertQueryPicker();
    initVisualization();
        App.cm['sparql'].refresh();
        App.cm['rif'].refresh();
        App.cm['rdf'].refresh();
};

App.loadEditors = function() {
    // Initialize editors
    App.cm = {};

    App.cm['sparql'] = CodeMirror.fromTextArea(document.getElementById('codemirror'), {
        mode: 'sparql',
        lineNumbers: true,
        matchBrackets: true,
        autoCloseBrackets: true
    });

    App.loadQuery('sparql', 0);

    App.cm['rdf'] = CodeMirror.fromTextArea(document.getElementById('codemirror_rdf'), {
        lineNumbers: true,
        mode: 'n3',
        matchBrackets: true,
        autoCloseBrackets: true
    });

    App.loadQuery('rdf', 0);

    App.cm['rif'] = CodeMirror.fromTextArea(document.getElementById('codemirror_rif'), {
        lineNumbers: true,
        mode: 'rif',
        matchBrackets: true,
        autoCloseBrackets: true
    });

    App.loadQuery('rif', 0);
};

App.loadQuery = function(lang, index) {
    const $statusElement = $(`.load-query-status[data-lang=${lang}]`);
    $statusElement.show().html('<i class="fa fa-spinner"></i>');
    $.ajax({
        url: App.config.defaultData[lang][index],
        dataType: 'text'
    }).done(function(data) {
        $statusElement.html('<i class="fa fa-check-circle"></i>').fadeOut(500);
        App.cm[lang].getDoc().setValue(data);
    });
};

App.getGraphData = function(data, urlPrefix, method, target) {

    // AST Request
    const request = {
        query: data.query,
        evaluator: App.selectedEvaluatorName
    };
    $.ajax({
        url: urlPrefix + "/info",
        method,
        data: JSON.stringify(request),
        success(data) {
            if (App.checkErrorString(data)) {
                createGraph(data, target);
            }
        },
        error(xhr, status, error) {
            App.logError(xhr.responseText);
        }
    });

    // Operator-Graph Request
    $.ajax({
        url: urlPrefix + "/graphs",
        method,
        data: JSON.stringify(data),
        success(data) {
            if (App.checkErrorString(data)) {
                createOPGraph(data, target);
            }
        },
        error(xhr, status, error) {
            App.logError(xhr.responseText);
        }
    });
};


App.loadluposdate3000 = function(data, url, withGraph) {
    const queryData = {
        query: data.query,
        evaluator: "XML_STREAM"
    };
    if (withGraph) {
        $.ajax({
            url: url + 'sparql/startSession',
            method: "POST",
            data: queryData,
            success(sessionID) {
                if (App.checkErrorString(sessionID, 'sparql')) {
                    const sessionData = {
                        sessionID
                    };
                    $.ajax({
                        url: url + 'sparql/getLogicalVisual',
                        method: "POST",
                        data: sessionData,
                        success(logSteps) {
                            if (App.checkErrorString(logSteps, 'sparql')) {
                                App.logGraph = JSON.parse(logSteps);
                                $.ajax({
                                    url: url + 'sparql/getPhysicalVisual',
                                    method: "POST",
                                    data: sessionData,
                                    success(phySteps) {
                                        if (App.checkErrorString(phySteps, 'sparql')) {
                                            App.physGraph = JSON.parse(phySteps);
                                            $.ajax({
                                                url: url + 'sparql/getVisualisationData',
                                                method: "POST",
                                                data: sessionData,
                                                success(visData) {
                                                    if (App.checkErrorString(visData, 'sparql')) {
                                                        App.globalAnimationList = JSON.parse(visData);
                                                        $.ajax({
                                                            url: url + 'sparql/getResult',
                                                            method: "POST",
                                                            data: sessionData,
                                                            success(resultData) {
                                                                if (App.checkErrorString(resultData, 'sparql')) {
                                                                    App.result = resultData;
                                                                    $.ajax({
                                                                        url: url + 'sparql/closeSession',
                                                                        method: "POST",
                                                                        data: sessionData,
                                                                        success(closeResponse) {
                                                                            if (App.checkErrorString(closeResponse, 'sparql')) {
                                                                                formatResultData();
                                                                                App.additionalHiddenTabs = ["graph", "op-graph"];
                                                                                App.initConfigComponentsHideTabs();
                                                                            }
                                                                        },
                                                                        error(xhr, status, error) {
                                                                            App.logError(xhr.responseText);
                                                                        }
                                                                    });
                                                                }
                                                            },
                                                            error(xhr, status, error) {
                                                                App.logError(xhr.responseText);
                                                            }
                                                        });
                                                    }
                                                },
                                                error(xhr, status, error) {
                                                    App.logError(xhr.responseText);
                                                }
                                            });
                                        }
                                    },
                                    error(xhr, status, error) {
                                        App.logError(xhr.responseText);
                                    }
                                });
                            }
                        },
                        error(xhr, status, error) {
                            App.logError(xhr.responseText);
                        }
                    });
                }
            },
            error(xhr, status, error) {
                App.logError(xhr.responseText);
            }
        });
    } else {
        $.ajax({
            url: url + 'sparql/query',
            method: "POST",
            data: queryData,
            success(xml) {
                if (App.checkErrorString(xml, 'sparql')) {
                    App.processResults(xml, "sparql");
                    App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
                    App.initConfigComponentsHideTabs();
                }
            },
            error(xhr, status, error) {
                App.logError(xhr.responseText);
            }
        });
    }
};

App.checkErrorString = function(data, target) {
    if ((typeof data === 'string') && (data.indexOf("HTTP/1.1 500 Internal Server Error") !== -1)) {
        App.logError(data, target);
        return false;
    } else {
        return true;
    }
};

App.bindEvents = function() {
    $('#sonificationsettings').click(function() {
        $('#sonificationsettings-menu').show();
        $('#graphsettings-ast').hide();
        $('#graphsettings-operator').hide();
    });

    $('#graphsettings').click(function() {
        $('#sonificationsettings-menu').hide();
        $('#graphsettings-ast').show();
        $('#graphsettings-operator').show();
    });


    $('#op-graph-down').click(function() {
        let value = $('#graph-select').val();
        value = parseInt(value, 10);
        $('#op-graph-up').prop('disabled', false);
        $('#op-graph-up').removeClass('disabled');
        if (value !== 0) {
            value = value - 1;
            const graphSelect = $('#graph-select');

            const graphValue = graphSelect.val(value);
            graphSelect.trigger('change');
            if (value === 0) {
                $(this).addClass('disabled');
            }
        }
    });


    $('#op-graph-up').click(function() {
        let value = $('#graph-select').val();
        value = parseInt(value, 10);
        $('#op-graph-down').prop('disabled', false);
        $('#op-graph-down').removeClass('disabled');
        const graphSelect = $('#graph-select');
        const max = graphSelect.children('option').length - 1;

        if (value !== max) {
            value = value + 1;

            const graphValue = graphSelect.val(value);
            graphSelect.trigger('change');
            if (value === max) {
                $(this).addClass('disabled');
            }
        }
    });


    // Send query to endpoint
    $('.query .evaluate').click(function() {
        // Copy changes to textarea
        let key, locator, method, withGraph;
        for (key in App.cm) {
            App.cm[key].save();
        }

        const target = $(this).data('target');
        if (target === 'sparql') {
            withGraph = App.config.evalGraphSparql;
        } else {
            withGraph = App.config.evalGraphRif;
        }
        const endpoint = App.config.endpoints[App.config.selectedEndpoint];
        let data = {
            query: App.cm[target].getValue()
        };
        if (App.config.sendRDF) {
            data['rdf'] = App.cm['rdf'].getValue();
        } else {
            data['rdf'] = '';
        }
        if (endpoint.nonstandard) {
            const folder = endpoint[target];
            method = folder[1];
            locator = folder[0];
        } else {
            method = 'GET';
            locator = endpoint.without;
        }
        const url = endpoint.url + locator;
        if (endpoint.name === "Browser Luposdate3000") {
            if (App.config.sendRDF) {
                try {
                    luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.close();
                    App.luposdate3000Instance = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.initialize();
                    luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.import_turtle_string(App.luposdate3000Instance, data.rdf);
                } catch (error) {
                    App.logError(error, 'rdf');
                }
            }
            //Receive optimized steps for logical and physical operator graph
            if (withGraph) {
                try {
                    let k, v;
                    const eev = new luposdate3000_endpoint.lupos.endpoint.EndpointExtendedVisualize(data.query, App.luposdate3000Instance);
                    let tmp = eev.getOptimizedStepsLogical();
                    for (k = 0; k < tmp.length; k++) {
                            tmp[k] = JSON.parse(tmp[k].toJson());
                    }
                    App.logGraph = tmp;
                    tmp = eev.getOptimizedStepsPhysical();
                    for (k = 0; k < tmp.length; k++) {
                            tmp[k] = JSON.parse(tmp[k].toJson());
                    }
                    App.physGraph = tmp;
                    //Result from the query
                    App.result = eev.getResult();
                    tmp = eev.getDataSteps();
                    for (k = 0; k < tmp.length; k++) {
                        v = tmp[k];
                        tmp[k] = JSON.parse(v);
                    }
                    App.globalAnimationList = tmp;
                    formatResultData();
                    App.additionalHiddenTabs = ["graph", "op-graph"];
                    App.initConfigComponentsHideTabs();
                } catch (error1) {
                    App.logError(error1, target);
                }
            } else {
                try {
                    const res = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(App.luposdate3000Instance, data.query);
                    App.processResults(res, "sparql");
                    App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
                    App.initConfigComponentsHideTabs();
                } catch (error2) {
                    App.logError(error2, target);
                }
            }
        } else if (endpoint.name === "localhost Luposdate3000") {
            if (App.config.sendRDF) {
                const rdfData = {
                    data: data.rdf
                };
                $.ajax({
                    url: url + 'import/turtledata',
                    method: "POST",
                    data: rdfData,
                    success(loadResponse) {
                        if (App.checkErrorString(loadResponse, 'rdf')) {
                            App.loadluposdate3000(data, url, withGraph);
                        }
                    },
                    error(xhr, status, error) {
                        App.logError(xhr.responseText, 'rdf');
                    }
                });
            } else {
                App.loadluposdate3000(data, url, withGraph);
            }
        } else {
            if (endpoint.nonstandard) {
                data['formats'] = ['xml', 'plain'];
                // Set query parameters from config
                for (key in App.config['queryParameters']) {
                    data[key] = App.config['queryParameters'][key];
                }
                const inference = $('input[name="rule"]:checked').val();
                data['inference'] = inference;
                if (inference === 'RIF') {
                    data['rif'] = $('#codemirror_rif').val();
                }
                data['evaluator'] = App.selectedEvaluatorName;
            }
            // Nonstandard endpoints expect JSON-string as request body


            if (withGraph) {
                App.getGraphData(data, url, method, target);
            } else {
                $('.result-tab a').click();
            }

            // Nonstandard endpoints expect JSON-string as request body
            data = JSON.stringify(data);


            // Switch to results tab if needed
            let localhtml = "";
            localhtml += "<h4>Waiting for Server..</h4>";
            localhtml += "<div class='sk-spinner sk-spinner-cube-grid dark'>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "    <div class='sk-cube'></div>";
            localhtml += "</div>";
            $('#result-tab').html(localhtml);

            $.ajax({
                url,
                method,
                data,
                success(data) {
                    App.processResults(data, target);
                    if (withGraph) {
                        App.additionalHiddenTabs = ["luposdate3000-graph", "luposdate3000-sonification"];
                        App.initConfigComponentsHideTabs();
                    } else {
                        App.additionalHiddenTabs = ["luposdate3000-graph", "luposdate3000-sonification", "graph",
                            "op-graph"
                        ];
                        App.initConfigComponentsHideTabs();
                    }
                },
                error(xhr, status, error) {
                    App.logError(xhr.responseText);
                }
            });
        }
    });

    // Reload editor when changing tabs
    $(document).foundation({
        tab: {
            callback(tab) {
                const content = $(tab.children('a').attr('href'));
                if (content.find('.CodeMirror').length) {
                    content.find('.CodeMirror')[0].CodeMirror.refresh();
                }
            }
        }
    });

    $('.query-select').change(function() {
        const lang = $(this).data('lang');
        const index = $(this).find('option:selected').index();
        App.loadQuery(lang, index);
    });

    // Merge/split tabs
    $('.right-side-toggle').click(function() {
        if (App.isMergeView) {
            $(".my-tab-rightside").detach().appendTo('.my-tab-content-rightside');
            $(".my-tab-links-rightside").detach().appendTo('.my-tab-links-content-rightside');
            $("#query").css("width", "50%");
            $("#result-tab").click();
            $("#sparql-tab").click();
        } else {
            $(".my-tab-rightside").detach().appendTo('.my-tab-content-leftside');
            $(".my-tab-links-rightside").detach().appendTo('.my-tab-links-content-leftside');
            $("#query").css("width", "100%");
        }
        $(this).toggleClass('active');
        App.isMergeView = !App.isMergeView;
        App.initConfigComponentsHideTabs();
    });
};

App.insertQueryPicker = () => (() => {
    const result = [];
    for (var lang in {
            'sparql': 'sparql',
            'rdf': 'rdf',
            'rif': 'rif'
        }) {
        var localhtml = "";
        for (var option of Array.from(App.config['defaultData'][lang])) {
            localhtml += "<option value=";
            localhtml += myEscape(option);
            localhtml += ">";
            localhtml += App.baseName(option);
            localhtml += "</option>";
        }
        result.push($(`#query-select-${lang}`).html(localhtml));
    }
    return result;
})();


App.processResults = function(data, lang) {

    // Find and save defined prefixes
    // Generate random colors while we're at it
    let value;
    const namespaces = {};
    const colors = {};
    // define some standard prefixes:
    namespaces['rdf'] = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    namespaces['rdfs'] = 'http://www.w3.org/2000/01/rdf-schema#';
    namespaces['owl'] = 'http://www.w3.org/2002/07/owl#';
    namespaces['xsd'] = 'http://www.w3.org/2001/XMLSchema#';
    // get prefixes from user's query, data and rules
    if (App.config.sendRDF) {
        $.extend(namespaces, App.parseRDFPrefixes(App.cm['rdf'].getValue()));
    }
    if (lang === 'rif') {
        $.extend(namespaces, App.parseRIFPrefixes(App.cm['rif'].getValue()));
    }
    if (lang === 'sparql') {
        $.extend(namespaces, App.parseSPARQLPrefixes(App.cm['sparql'].getValue()));
    }
    for (var key in namespaces) {
        value = namespaces[key];
        colors[key] = randomColor({
            luminosity: 'dark'
        });
    }
    // Actually use them in the result
    // Check validity, preprocess if necessary
    let error;
    const resultSets = [];

    let xml = data;
    if (App.config.endpoints[App.config.selectedEndpoint].nonstandard) {
        // Process specific response types for nonstandard
        if ('triples' in data) {
            resultSets.push(App.processTriples(data.triples, namespaces, colors));
        }
        if ('predicates' in data) {
            resultSets.push(App.processPredicates(data.predicates, namespaces, colors));
        }
        if ('XML' in data) {
            // Sometimes the server will return an empty string
            if (data.XML[0] === '') {
                resultSets.push(App.emptyResultSet());
            } else {
                try {
                    xml = $.parseXML(data.XML[0]);
                } catch (error1) {
                    console.log(error1);
                }
            }
        }
    } else {
        try {
            xml = $.parseXML(data);
        } catch (error2) {
            console.log(error2);
        }
    }

    if ($.isXMLDoc(xml)) {
        const sparql = App.x2js.xml2json(xml);
        resultSets.push(App.processSparql(sparql, namespaces, colors));
    }


    if (resultSets.length) {
        let resultTab = "<div class='result-tab-content'>";
        resultTab += "<div>";
        if (Object.keys(namespaces).length > 0) {
            resultTab += "<h4>Prefixes</h4>";
            resultTab += "<table>";
            for (value in namespaces) {
                var prefix = namespaces[value];
                resultTab += "    <tr>";
                resultTab += "        <td class='sparql-prefix' style='color: ";
                resultTab += colors[value];
                resultTab += "'>";
                resultTab += myEscape(prefix);
                resultTab += "</td>";
                resultTab += "        <td>";
                resultTab += myEscape(value);
                resultTab += "</td>";
                resultTab += "    </tr>";
            }
            resultTab += "</table>";
        }
        resultTab += "";
        for (let resultSetIndex = 0; resultSetIndex < resultSets.length; resultSetIndex++) {
            var resultSet = resultSets[resultSetIndex];
            resultTab += "<h4>";
            resultTab += myEscape(resultSet.name);
            resultTab += " (";
            resultTab += myEscape(resultSet.results.length);
            resultTab += ")</h4>";
            if (resultSet.results.length > 0) {
                resultTab += "<table class='results'>";
                resultTab += "    <thead>";
                resultTab += "        <tr>";
                for (var variableIndex = 0; variableIndex < resultSet.head.length; variableIndex++) {
                    var variable = resultSet.head[variableIndex];
                    resultTab += "            <th>";
                    resultTab += "                ";
                    resultTab += myEscape(variable);
                    resultTab += "            </th>";
                }
                resultTab += "        </tr>";
                resultTab += "    </thead>";
                resultTab += "    <tbody>";
                for (var resultIndex = 0; resultIndex < resultSet.results.length; resultIndex++) {
                    var result = resultSet.results[resultIndex];
                    resultTab += "        <tr>";
                    for (var bindIndex = 0; bindIndex < result.length; bindIndex++) {
                        var bind = result[bindIndex];
                        resultTab += "            <td>";
                        resultTab += bind;
                        resultTab += "</td>";
                    }
                    resultTab += "        </tr>";
                }
                resultTab += "    </tbody>";
                resultTab += "</table>";
            }
        }
        resultTab += "</div>";
        resultTab += "</div>";
        $('#result-tab').html(resultTab);
        if ($('#checkbox_downloadResult').is(':checked')) {
            $('#result-tab').append('<div class="buttonarea"><a id="downloadHTMLResult" download="Result.html" class="button evaluate">Download Result HTML</a></div>');
            let localhtml = "";
            localhtml += "<html class=' js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths'>";
            localhtml += "<head>";
            localhtml += "    <link rel='shortcut icon' href='images/favicon.png' type='image/png'>";
            localhtml += "    <meta charset='utf-8'>";
            localhtml += "    <title>Result of Query</title>";
            localhtml += "    <meta name='description' content=''>";
            localhtml += "    <meta name='viewport' content='width=device-width, initial-scale=1'>";
            localhtml += "    <link rel='stylesheet' href='https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/vendor.css'>";
            localhtml += "    <!-- TODO: Keep fonts in this repo? -->";
            localhtml += "    <link href='//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' rel='stylesheet'>";
            localhtml += "    <link rel='stylesheet' href='https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/main.css'>";
            localhtml += "    <meta class='foundation-data-attribute-namespace'>";
            localhtml += "    <meta class='foundation-mq-xxlarge'>";
            localhtml += "    <meta class='foundation-mq-xlarge-only'>";
            localhtml += "    <meta class='foundation-mq-xlarge'>";
            localhtml += "    <meta class='foundation-mq-large-only'>";
            localhtml += "    <meta class='foundation-mq-large'>";
            localhtml += "    <meta class='foundation-mq-medium-only'>";
            localhtml += "    <meta class='foundation-mq-medium'>";
            localhtml += "    <meta class='foundation-mq-small-only'>";
            localhtml += "    <meta class='foundation-mq-small'>";
            localhtml += "    <meta class='foundation-mq-topbar'>";
            localhtml += "</head>";
            localhtml += "<body style='margin: 20pt'>";
            localhtml += "    <!--[if lt IE 10]>";
            localhtml += "<p class='browsehappy'>You are using an <strong>outdated</strong> browser. Please <a href='https://browsehappy.com/'>upgrade";
            localhtml += "    your browser</a> to improve your experience.</p>";
            localhtml += "<![endif]-->";
            localhtml += "    <h1>Result of Query</h1>";
            localhtml += resultTab;
            localhtml += "</body>";
            localhtml += "</html>";
            $("#downloadHTMLResult").attr('href', makeTextFile(localhtml));
        }
    } else {
        if ('queryError' in data) {
            App.logError('Sparql: ' + data.queryError.errorMessage, lang, data.queryError.line);
        } else if ('rdfError' in data) {
            App.logError('RDF: ' + data.rdfError.errorMessage, 'rdf', data.rdfError.line);
        } else if ('rifError' in data) {
            App.logError('RIF: ' + data.rifError.errorMessage, 'rif', data.rifError.line);
        } else if ('error' in data) {
            App.logError(data.error);
        } else {
            if (App.config.endpoints[App.config.selectedEndpoint].nonstandard) {
                App.logError('Endpoint answer was not valid.');
            } else {
                App.logError(data);
            }
        }
    }
};

App.emptyResultSet = () => ({
    name: 'Results',
    head: [],
    results: []
});

App.processSparql = function(doc, namespaces, colors) {
    const resultSet = App.emptyResultSet();

    if ('boolean' in doc.sparql) {
        resultSet.head.push('Boolean');
        resultSet.results.push([doc.sparql.boolean]);
    } else if (doc.sparql.head !== "") {
        if (!$.isArray(doc.sparql.head.variable)) {
            doc.sparql.head.variable = [doc.sparql.head.variable];
        }
        let i = 0;
        const varorder = [];
        for (var variable of Array.from(doc.sparql.head.variable)) {
            resultSet.head.push(variable._attributes.name);
            varorder[variable._attributes.name] = i;
            i++;
        }
        if (!doc.sparql.results.hasOwnProperty("result")) {
            doc.sparql.results = {
                result: []
            };
        }
        if (!$.isArray(doc.sparql.results.result)) {
            doc.sparql.results.result = [doc.sparql.results.result];
        }
        for (var result of Array.from(doc.sparql.results.result)) {
            var bindings;
            var presult = [];
            if ($.isArray(result.binding)) {
                bindings = result.binding;
            } else {
                bindings = [result.binding];
            }
            var varbinding = [];
            for (var bind of Array.from(bindings)) {
                var value = '';
                if (bind != null) {
                    if ('uri' in bind) {
                        value = App.replacePrefixes(bind.uri, namespaces, colors);
                    } else if ('literal' in bind) {
                        value = "\"" + bind.literal + "\"";
                        if (bind.literal._attributes && 'datatype' in bind.literal._attributes) {
                            value += "^^" + App.replacePrefixes(bind.literal._attributes.datatype, namespaces, colors);
                        }
                        if (bind.literal._attributes && 'xml:lang' in bind.literal._attributes) {
                            value += "@" + bind.literal._attributes['xml:lang'];
                        }
                    } else if ('bnode' in bind) {
                        value = "_:" + bind.bnode;
                    }
                    varbinding[bind._attributes.name] = value;
                }
            }
            for (var varname in varorder) {
                var index = varorder[varname];
                if (varbinding[varname]) {
                    presult[index] = varbinding[varname];
                } else {
                    presult[index] = '';
                }
            }
            resultSet.results.push(presult);
        }
    }

    return resultSet;
};

App.processTriples = function(data, namespaces, colors) {
    const varnames = ['subject', 'predicate', 'object'];
    const resultSet = {
        name: 'Triples',
        head: varnames,
        results: []
    };

    for (var triple of Array.from(data)) {
        var result = [];
        for (var variable of Array.from(varnames)) {
            App.processLiteral(triple[variable], namespaces, colors, result);
        }
        resultSet.results.push(result);
    }

    return resultSet;
};

App.processPredicates = function(preds, namespaces, colors) {
    const resultSet = {
        name: 'Predicates',
        head: [],
        results: []
    };

    resultSet.head.push('Predicate Name');

    if (preds[0].parameters) {
        for (let k = 0; k < preds[0].parameters.length; k++) {
            var v = preds[0].parameters[k];
            resultSet.head.push(`Arg. ${k + 1}`);
        }
    }

    for (var pred of Array.from(preds)) {
        var result = [];
        result.push(App.replacePrefixes(pred.predicateName.value, namespaces, colors));
        if (pred.parameters) {
            for (var para of Array.from(pred.parameters)) {
                App.processLiteral(para, namespaces, colors, result);
            }
        }
        resultSet.results.push(result);
    }

    return resultSet;
};

App.processLiteral = function(para, namespaces, colors, result) {
    para.value = para.value;
    if (para.datatype) {
        const prefixeddatatype = App.replacePrefixes(para.datatype, namespaces, colors);
        result.push(`\"${para.value}\"^^${prefixeddatatype}`);
    } else if (para.type && (para.type === 'uri')) {
        result.push(App.replacePrefixes(para.value, namespaces, colors));
    } else if (para.type && (para.type === 'bnode')) {
        result.push(`_:${para.value}`);
    } else {
        let r = `\"${para.value}\"`;
        if (para['xml:lang']) {
            r = r + '@' + para['xml:lang'];
        }
        result.push(r);
    }
};

App.replacePrefixes = function(str, namespaces, colors) {
    str = myEscape(str);
oldstr=str
    for (var key in namespaces) {
        var prefix = namespaces[key];
        if (str.indexOf(prefix) !== -1) {
            var localhtml = "<span class='sparql-prefix' style='color: ";
            localhtml += colors[key];
            localhtml += "'>";
            localhtml += myEscape(key);
            localhtml += ":</span>";
  str=          str.replace(prefix, localhtml);
        }
    }
if (oldstr!=str){
    return  str ;
}else{
    return "&lt;" + str + "&gt;";
}
};

App.parseRDFPrefixes = function(data) {
    let m;
    const prefixes = {};
    // first detect base
    let reg = /@prefix\s+:\s*<([^>]+)>\s*\./g;
    while (m = reg.exec(data)) {
        prefixes[''] = m[1];
    }
    // now detect prefixes
    reg = /@prefix\s+([A-z0-9-]+):\s*<([^>]+)>\s*\./g;
    while (m = reg.exec(data)) {
        prefixes[m[1]] = m[2];
    }
    return prefixes;
};

App.parseSPARQLPrefixes = function(data) {
    let m;
    const prefixes = {};
    // first detect base
    let reg = /base\s+<([^>]+)>/ig;
    while (m = reg.exec(data)) {
        prefixes[''] = m[1];
    }
    // now detect prefixes
    reg = /prefix\s+([A-z0-9-]+)\s*:\s*<([^>]+)>/ig;
    while (m = reg.exec(data)) {
        prefixes[m[1]] = m[2];
    }
    return prefixes;
};

App.parseRIFPrefixes = function(data) {
    let m;
    const prefixes = {};
    // first detect base
    let reg = /base\(\s*<([^>]+)>\s*\)/ig;
    while (m = reg.exec(data)) {
        prefixes[''] = m[1];
    }
    // now detect prefixes
    reg = /prefix\(([^\s]+)\s+<([^>]+)>\s*\)/ig;
    while (m = reg.exec(data)) {
        prefixes[m[1]] = m[2];
    }
    return prefixes;
};

App.logError = function(msg, editor, line) {
    console.log(msg,editor,line)
    if (editor && line) {
        line--;
        App.cm[editor].setSelection({
            line,
            ch: 0
        }, {
            line,
            ch: 80
        });
        $(`.${editor}-tab a`).click();
    }
    let localhtml = "<h4><i class='fa fa-exclamation-triangle'></i> Error</h4><p>The Server responded with:</p><pre style='white-space: pre-wrap;'>";
    localhtml += myEscape(msg);
    localhtml += "</pre>";
    $('#result-tab').html(localhtml);
    App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
    App.initConfigComponentsHideTabs();
};


App.baseName = function(str) {
    let base = new String(str).substring(str.lastIndexOf('/') + 1);
    if (base.lastIndexOf('.') !== -1) {
        base = base.substring(0, base.lastIndexOf('.'));
    }
    return base;
};

App.initConfigComponentsEndpointSelector = function() {
    let localhtml = "";
    let i = 0;
    for (var endpoint of Array.from(App.config.endpoints)) {
        localhtml += "<option value='";
        localhtml += i;
        localhtml += "'>";
        localhtml += myEscape(endpoint.name);
        localhtml += "</option>";
        i++;
    }
    $("#endpoint_selector").html(localhtml);
    if (!App.initConfigComponentsEndpointSelectorHasInit) {
        App.initConfigComponentsEndpointSelectorHasInit = true;
        $('#endpoint_selector').change(function() {
            App.config.selectedEndpoint = $(this).val();
            App.initConfigComponentsEvaluatorSelector();
            App.additionalHiddenTabs = App.rightTabs;
            App.initConfigComponentsHideTabs();
        });
    }
    $('#endpoint_selector').val(App.config.selectedEndpoint);
    $('#endpoint_selector').change();
};

App.initConfigComponentsEvaluatorSelector = function() {
    let endpoint = App.config.endpoints[App.config.selectedEndpoint];
    let localhtml = "";
    let i = 0;
    for (var evaluator of Array.from(endpoint.evaluators)) {
        localhtml += "<option value='";
        localhtml += i;
        localhtml += "'>";
        localhtml += myEscape(evaluator);
        localhtml += "</option>";
        i++;
    }
    $("#evaluator_selector").html(localhtml);
    if (!App.initConfigComponentsEvaluatorSelectorHasInit) {
        App.initConfigComponentsEvaluatorSelectorHasInit = true;
        $('#evaluator_selector').change(function() {
            endpoint = App.config.endpoints[App.config.selectedEndpoint];
            endpoint.selectedEvaluator = $(this).val();
            App.selectedEvaluatorName = endpoint.evaluators[endpoint.selectedEvaluator];
            if ((App.selectedEvaluatorName === "Jena") || (App.selectedEvaluatorName === "Sesame")) {
                App.config.evalGraphSparql = false;
                App.config.evalGraphRif = false;
                App.updateConfigComponentsEvalGraph();
            }
            App.initConfigComponentsHideTabs();
            App.initConfigComponentsHideWithGraph();
            App.additionalHiddenTabs = App.rightTabs;
            App.initConfigComponentsHideTabs();
            App.initConfigComponentsHideInference();
        });
    }
    $('#evaluator_selector').val(endpoint.selectedEvaluator);
    $('#evaluator_selector').change();
};

App.initConfigComponentsHideWithGraph = function() {
    if (App.config.hide.withGraph || (App.selectedEvaluatorName === "Jena") || (App.selectedEvaluatorName === "Sesame")) {
        $('.label-with-graph').hide();
    } else {
        $('.label-with-graph').show();
    }
};
App.initConfigComponentsHideTabs = function() {
    let selector, selector1, selector2, tab;
    const tabsToHide = [];
    for (tab of Array.from(App.config.hide.tabs)) {
        tabsToHide.push(tab);
    }
    for (tab of Array.from(App.additionalHiddenTabs)) {
        tabsToHide.push(tab);
    }
    if ((App.selectedEvaluatorName === "Jena") || (App.selectedEvaluatorName === "Sesame") || (App.selectedEvaluatorName.indexOf("Luposdate3000") !== -1)) {
        tabsToHide.push("rif");
    }
    const allTabs = [];
    // tabs on the left
    let leftAvailableTab = "";
    for (tab of Array.from(App.leftTabs)) {
        selector1 = "#";
        selector1 += tab;
        selector1 += "-tab";
        selector2 = "a[href=#";
        selector2 += tab;
        selector2 += "-tab]";
        if (Array.from(tabsToHide).includes(tab)) {
            $(selector1).hide();
            $(selector1).removeClass("active");
            $(selector2).hide();
            $(selector2).removeClass("active");
            $(selector2).parent("dd").hide();
        } else {
            $(selector1).show();
            $(selector2).show();
            $(selector2).parent("dd").show();
            if ($(selector1).hasClass("active")) {
                leftAvailableTab = tab;
            }
            if (leftAvailableTab === "") {
                leftAvailableTab = tab;
            }
        }
    }
    if (leftAvailableTab !== "") {
        selector = "a[href=#";
        selector += leftAvailableTab;
        selector += "-tab]";
        $(selector).click();
    }
    // tabs on the right
    let rightAvailableTab = "";
    for (tab of Array.from(App.rightTabs)) {
        selector1 = "#";
        selector1 += tab;
        selector1 += "-tab";
        selector2 = "a[href=#";
        selector2 += tab;
        selector2 += "-tab]";
        if (Array.from(tabsToHide).includes(tab)) {
            $(selector1).hide();
            $(selector1).removeClass("active");
            $(selector2).hide();
            $(selector2).removeClass("active");
            $(selector2).parent("dd").hide();
        } else {
            $(selector1).show();
            $(selector2).show();
            $(selector2).parent("dd").show();
            if ($(selector1).hasClass("active")) {
                rightAvailableTab = tab;
            }
            if (rightAvailableTab === "") {
                rightAvailableTab = tab;
            }
        }
    }
    if (rightAvailableTab !== "") {
        selector = "a[href=#";
        selector += rightAvailableTab;
        selector += "-tab]";
        $(selector).click();
    }
    if (Array.from(tabsToHide).includes("rif")) {
        $("#rule_rif").hide();
        $("#rule_rif_label").hide();
    } else {
        $("#rule_rif").show();
        $("#rule_rif_label").show();
    }
    if ((Array.from(tabsToHide).includes("graph")) && (Array.from(tabsToHide).includes("op-graph"))) {
        $('#graphsettings').hide();
    } else {
        $('#graphsettings').show();
    }
    if (Array.from(tabsToHide).includes("luposdate3000-sonification")) {
        $("#sonificationsettings").hide();
    } else {
        $("#sonificationsettings").show();
    }
};

App.updateConfigComponentsEvalGraph = function() {
    $("#eval-graph-sparql").prop('checked', App.config.evalGraphSparql);
    $("#eval-graph-rif").prop('checked', App.config.evalGraphRif);
};
App.initConfigComponentsEvalGraph = function() {
    $("#eval-graph-sparql").change(() => App.config.evalGraphSparql = $("#eval-graph-sparql").is(':checked'));
    $("#eval-graph-rif").change(() => App.config.evalGraphRif = $("#eval-graph-rif").is(':checked'));
    App.updateConfigComponentsEvalGraph();
};
App.initConfigComponentsSendRdf = function() {
    $('#send_rdf').change(() => App.config.sendRDF = $('#send_rdf').is(':checked'));
    if (App.config.sendRDF) {
        $('#send_rdf').click();
    }
    if (App.config.hide.sendRDF) {
        $('#send_rdf').hide();
    }
};
App.initConfigComponentsInference = function() {
    $('#rule_radios input').change(() => App.config.queryParameters.inference = $("input[name='rule']:checked").val());
    let selector = "[value=";
    selector += App.config.queryParameters.inference;
    selector += "]";
    $(selector).click();
};
App.initConfigComponentsHideInference = function() {
    let radio, s;
    const allRadio = ["rdfs", "owl", "rif", "without"];
    for (radio of Array.from(allRadio)) {
        s = '#rule_' + radio;
        $(s).show();
        $(s + '_label').show();
    }
    if (App.selectedEvaluatorName.indexOf("Luposdate3000") !== -1) {
        const result = [];
        for (radio of Array.from(allRadio)) {
            s = '#rule_' + radio;
            $(s).hide();
            result.push($(s + '_label').hide());
        }
        return result;
    } else if (App.config.hide.inference) {
        const result1 = [];
        for (radio of Array.from(allRadio)) {
            var actual = App.config['queryParameters']['inference'];
            if ((actual.toLowerCase() !== radio) && !((actual === "OWL2RL") && (radio === "owl"))) {
                s = '#rule_' + radio;
                $(s).hide();
                result1.push($(s + '_label').hide());
            } else {
                result1.push(undefined);
            }
        }
        return result1;
    }
};

App.initConfigComponents = function() {
    $('.my-tabs .my-tab-links a').click(function(e) {
        const currentAttrValue = $(this).attr('href');
        $('.my-tabs ' + currentAttrValue).show().siblings().hide();
        $(this).parent('li').addClass('active').siblings().removeClass('active');
        e.preventDefault();
    });

    App.initConfigComponentsEndpointSelector();
    App.initConfigComponentsEvaluatorSelector();
    App.initConfigComponentsHideWithGraph();
    App.initConfigComponentsHideTabs();
    App.initConfigComponentsEvalGraph();
    App.initConfigComponentsSendRdf();
    App.initConfigComponentsInference();
    App.initConfigComponentsHideInference();
    Array.from(App.config.readOnlyTabs).map((tab) => App.cm[tab].setOption("readOnly", true));
};

const delay = (ms, func) => setTimeout(func, ms);


let textFile = null;
var makeTextFile = function(text) {
    const data = new Blob([text], {
        type: 'text/plain'
    });
    // If we are replacing a previously generated file we need to
    // manually revoke the object URL to avoid memory leaks.
    if (textFile !== null) {
        window.URL.revokeObjectURL(textFile);
    }
    textFile = window.URL.createObjectURL(data);
    return textFile;
};
$(document).ready(() => App.init());
