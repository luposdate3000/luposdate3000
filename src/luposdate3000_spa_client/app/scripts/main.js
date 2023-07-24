(function() {
  var delay, makeTextFile, textFile,
    indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

  this.App = {
    isMergeView: false
  };

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
    App.x2js = new X2JS({
      attributeArray: '_attributes'
    });
    App.URIQuery = URI(document.location.href).query(true);
    return $.getJSON('config/operators.json').done(function(dataOp) {
      App.operators = dataOp;
      return $.getJSON('config/config.json').done(function(dataConf) {
        App.config = dataConf;
        if (App.URIQuery.config) {
          return $.getJSON(App.URIQuery.config).done(function(addData) {
            App.config = $.extend(data, addData, {});
            return App.play();
          });
        } else {
          return App.play();
        }
      });
    });
  };

  App.play = function() {
    App.loadEditors();
    App.bindEvents();
    App.initConfigComponents();
    App.insertQueryPicker();
    initVisualization();
    return pleaseWait.finish(false, function() {
      App.cm['sparql'].refresh();
      App.cm['rif'].refresh();
      return App.cm['rdf'].refresh();
    });
  };

  App.loadEditors = function() {
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
    return App.loadQuery('rif', 0);
  };

  App.loadQuery = function(lang, index) {
    var $statusElement;
    $statusElement = $(".load-query-status[data-lang=" + lang + "]");
    $statusElement.show().html('<i class="fa fa-spinner"></i>');
    return $.ajax({
      url: App.config.defaultData[lang][index],
      dataType: 'text'
    }).done(function(data) {
      $statusElement.html('<i class="fa fa-check-circle"></i>').fadeOut(500);
      return App.cm[lang].getDoc().setValue(data);
    });
  };

  App.getGraphData = function(data, urlPrefix, method, target) {
    var request;
    request = {
      query: data.query,
      evaluator: App.selectedEvaluatorName
    };
    $.ajax({
      url: urlPrefix + "/info",
      method: method,
      data: JSON.stringify(request),
      success: function(data) {
        if (App.checkErrorString(data)) {
          return createGraph(data, target);
        }
      },
      error: function(xhr, status, error) {
        return App.logError(xhr.responseText);
      }
    });
    return $.ajax({
      url: urlPrefix + "/graphs",
      method: method,
      data: JSON.stringify(data),
      success: function(data) {
        if (App.checkErrorString(data)) {
          return createOPGraph(data, target);
        }
      },
      error: function(xhr, status, error) {
        return App.logError(xhr.responseText);
      }
    });
  };

  App.loadluposdate3000 = function(data, url, withGraph) {
    var queryData;
    queryData = {
      query: data.query,
      evaluator: "XML_STREAM"
    };
    if (withGraph) {
      return $.ajax({
        url: url + 'sparql/startSession',
        method: "POST",
        data: queryData,
        success: function(sessionID) {
          var sessionData;
          if (App.checkErrorString(sessionID, 'sparql')) {
            sessionData = {
              sessionID: sessionID
            };
            return $.ajax({
              url: url + 'sparql/getLogicalVisual',
              method: "POST",
              data: sessionData,
              success: function(logSteps) {
                if (App.checkErrorString(logSteps, 'sparql')) {
                  App.logGraph = JSON.parse(logSteps);
                  return $.ajax({
                    url: url + 'sparql/getPhysicalVisual',
                    method: "POST",
                    data: sessionData,
                    success: function(phySteps) {
                      if (App.checkErrorString(phySteps, 'sparql')) {
                        App.physGraph = JSON.parse(phySteps);
                        return $.ajax({
                          url: url + 'sparql/getVisualisationData',
                          method: "POST",
                          data: sessionData,
                          success: function(visData) {
                            if (App.checkErrorString(visData, 'sparql')) {
                              App.globalAnimationList = JSON.parse(visData);
                              return $.ajax({
                                url: url + 'sparql/getResult',
                                method: "POST",
                                data: sessionData,
                                success: function(resultData) {
                                  if (App.checkErrorString(resultData, 'sparql')) {
                                    App.result = resultData;
                                    return $.ajax({
                                      url: url + 'sparql/closeSession',
                                      method: "POST",
                                      data: sessionData,
                                      success: function(closeResponse) {
                                        if (App.checkErrorString(closeResponse, 'sparql')) {
                                          formatResultData();
                                          App.additionalHiddenTabs = ["graph", "op-graph"];
                                          return App.initConfigComponentsHideTabs();
                                        }
                                      },
                                      error: function(xhr, status, error) {
                                        return App.logError(xhr.responseText);
                                      }
                                    });
                                  }
                                },
                                error: function(xhr, status, error) {
                                  return App.logError(xhr.responseText);
                                }
                              });
                            }
                          },
                          error: function(xhr, status, error) {
                            return App.logError(xhr.responseText);
                          }
                        });
                      }
                    },
                    error: function(xhr, status, error) {
                      return App.logError(xhr.responseText);
                    }
                  });
                }
              },
              error: function(xhr, status, error) {
                return App.logError(xhr.responseText);
              }
            });
          }
        },
        error: function(xhr, status, error) {
          return App.logError(xhr.responseText);
        }
      });
    } else {
      return $.ajax({
        url: url + 'sparql/query',
        method: "POST",
        data: queryData,
        success: function(xml) {
          if (App.checkErrorString(xml, 'sparql')) {
            App.processResults(xml, "sparql");
            App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
            return App.initConfigComponentsHideTabs();
          }
        },
        error: function(xhr, status, error) {
          return App.logError(xhr.responseText);
        }
      });
    }
  };

  App.checkErrorString = function(data, target) {
    console.log(data)
    if (($.type(data) === "string") && (data.indexOf("HTTP/1.1 500 Internal Server Error") !== -1)) {
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
      return $('#graphsettings-operator').hide();
    });
    $('#graphsettings').click(function() {
      $('#sonificationsettings-menu').hide();
      $('#graphsettings-ast').show();
      return $('#graphsettings-operator').show();
    });
    $('#op-graph-down').click(function() {
      var graphSelect, graphValue, value;
      value = $('#graph-select').val();
      value = parseInt(value, 10);
      $('#op-graph-up').prop('disabled', false);
      $('#op-graph-up').removeClass('disabled');
      if (value !== 0) {
        value = value - 1;
        graphSelect = $('#graph-select');
        graphValue = graphSelect.val(value);
        graphSelect.trigger('change');
        if (value === 0) {
          return $(this).addClass('disabled');
        }
      }
    });
    $('#op-graph-up').click(function() {
      var graphSelect, graphValue, max, value;
      value = $('#graph-select').val();
      value = parseInt(value, 10);
      $('#op-graph-down').prop('disabled', false);
      $('#op-graph-down').removeClass('disabled');
      graphSelect = $('#graph-select');
      max = graphSelect.children('option').length - 1;
      if (value !== max) {
        value = value + 1;
        graphValue = graphSelect.val(value);
        graphSelect.trigger('change');
        if (value === max) {
          return $(this).addClass('disabled');
        }
      }
    });
    $('.query .evaluate').click(function() {
      var data, e, eev, endpoint, folder, inference, j, k, key, l, len, len1, len2, localhtml, locator, method, n, rdfData, res, target, tmp, url, v, withGraph;
      for (key in App.cm) {
        App.cm[key].save();
      }
      target = $(this).data('target');
      if (target === 'sparql') {
        withGraph = App.config.evalGraphSparql;
      } else {
        withGraph = App.config.evalGraphRif;
      }
      endpoint = App.config.endpoints[App.config.selectedEndpoint];
      data = {
        query: App.cm[target].getValue()
      };
      if (App.config.sendRDF) {
        data['rdf'] = App.cm['rdf'].getValue();
      } else {
        data['rdf'] = '';
      }
      if (endpoint.nonstandard) {
        folder = endpoint[target];
        method = folder[1];
        locator = folder[0];
      } else {
        method = 'GET';
        locator = endpoint.without;
      }
      url = endpoint.url + locator;
      if (endpoint.name === "Browser Luposdate3000" || endpoint.name === "localhost Luposdate3000") {
        if (App.config.sendRDF) {
          luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.close();
          App.luposdate3000Instance = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.initialize();
        }
        if (endpoint.name === "Browser Luposdate3000") {
          if (App.config.sendRDF) {
            try {
              luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.import_turtle_string(App.luposdate3000Instance, data.rdf);
            } catch (error1) {
              e = error1;
              App.logError(e, 'rdf');
            }
          }
          if (withGraph) {
            try {
              eev = new luposdate3000_endpoint.lupos.endpoint.EndpointExtendedVisualize(data.query, App.luposdate3000Instance);
              tmp = eev.getOptimizedStepsLogical();
              App.logGraph = tmp;
              tmp = eev.getOptimizedStepsPhysical();
              App.physGraph = tmp;
              App.result = eev.getResult();
              tmp = eev.getDataSteps();
              for (k = n = 0, len2 = tmp.length; n < len2; k = ++n) {
                v = tmp[k];
                tmp[k] = JSON.parse(v);
              }
              App.globalAnimationList = tmp;
              formatResultData();
              App.additionalHiddenTabs = ["graph", "op-graph"];
              return App.initConfigComponentsHideTabs();
            } catch (error1) {
              e = error1;
              return App.logError(e, target);
            }
          } else {
            try {
              res = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(App.luposdate3000Instance, data.query);
              App.processResults(res, "sparql");
              App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
              return App.initConfigComponentsHideTabs();
            } catch (error1) {
              e = error1;
              return App.logError(e, target);
            }
          }
        } else {
          if (App.config.sendRDF) {
            rdfData = {
              data: data.rdf
            };
            return $.ajax({
              url: url + 'import/turtledata',
              method: "POST",
              data: rdfData,
              success: function(loadResponse) {
                if (App.checkErrorString(loadResponse, 'rdf')) {
                  return App.loadluposdate3000(data, url, withGraph);
                }
              },
              error: function(xhr, status, error) {
                return App.logError(xhr.responseText, 'rdf');
              }
            });
          } else {
            return App.loadluposdate3000(data, url, withGraph);
          }
        }
      } else {
        if (endpoint.nonstandard) {
          data['formats'] = ['xml', 'plain'];
          for (key in App.config['queryParameters']) {
            data[key] = App.config['queryParameters'][key];
          }
          inference = $('input[name="rule"]:checked').val();
          data['inference'] = inference;
          if (inference === 'RIF') {
            data['rif'] = $('#codemirror_rif').val();
          }
          data['evaluator'] = App.selectedEvaluatorName;
        }
        if (withGraph) {
          App.getGraphData(data, url, method, target);
        } else {
          $('.result-tab a').click();
        }
        data = JSON.stringify(data);
        localhtml = "";
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
        return $.ajax({
          url: url,
          method: method,
          data: data,
          success: function(data) {
            App.processResults(data, target);
            if (withGraph) {
              App.additionalHiddenTabs = ["luposdate3000-graph", "luposdate3000-sonification"];
              return App.initConfigComponentsHideTabs();
            } else {
              App.additionalHiddenTabs = ["luposdate3000-graph", "luposdate3000-sonification", "graph", "op-graph"];
              return App.initConfigComponentsHideTabs();
            }
          },
          error: function(xhr, status, error) {
            return App.logError(xhr.responseText);
          }
        });
      }
    });
    $(document).foundation({
      tab: {
        callback: function(tab) {
          var content;
          content = $(tab.children('a').attr('href'));
          if (content.find('.CodeMirror').length) {
            return content.find('.CodeMirror')[0].CodeMirror.refresh();
          }
        }
      }
    });
    $('.query-select').change(function() {
      var index, lang;
      lang = $(this).data('lang');
      index = $(this).find('option:selected').index();
      return App.loadQuery(lang, index);
    });
    $('.fullscreen-toggle').click(function() {
      $('.main-section').toggleClass('full');
      return $(this).toggleClass('active');
    });
    return $('.right-side-toggle').click(function() {
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
      return App.initConfigComponentsHideTabs();
    });
  };

  App.insertQueryPicker = function() {
    var j, lang, len, localhtml, option, ref, results;
    results = [];
    for (lang in {
      'sparql': 'sparql',
      'rdf': 'rdf',
      'rif': 'rif'
    }) {
      localhtml = "";
      ref = App.config['defaultData'][lang];
      for (j = 0, len = ref.length; j < len; j++) {
        option = ref[j];
        localhtml += "<option value=";
        localhtml += _.escape(option);
        localhtml += ">";
        localhtml += App.baseName(option);
        localhtml += "</option>";
      }
      results.push($("#query-select-" + lang).html(localhtml));
    }
    return results;
  };

  App.preprocessResults = function(data, namespaces, colors) {
    var error, resultSets, sparql, xml;
    resultSets = [];
    xml = data;
    if (App.config.endpoints[App.config.selectedEndpoint].nonstandard) {
      if ('triples' in data) {
        resultSets.push(App.processTriples(data.triples, namespaces, colors));
      }
      if ('predicates' in data) {
        resultSets.push(App.processPredicates(data.predicates, namespaces, colors));
      }
      if ('XML' in data) {
        if (data.XML[0] === '') {
          resultSets.push(App.emptyResultSet());
        } else {
          try {
            xml = $.parseXML(data.XML[0]);
          } catch (error1) {
            error = error1;
            console.log(error);
          }
        }
      }
    } else {
      try {
        xml = $.parseXML(data);
      } catch (error1) {
        error = error1;
        console.log(error);
      }
    }
    if ($.isXMLDoc(xml)) {
      sparql = App.x2js.xml2json(xml);
      resultSets.push(App.processSparql(sparql, namespaces, colors));
    }
    return resultSets;
  };

  App.processResults = function(data, lang) {
    var bind, bindIndex, colors, j, key, l, len, len1, len2, len3, localhtml, n, namespaces, o, prefix, ref, ref1, result, resultIndex, resultSet, resultSetIndex, resultSets, resultTab, value, variable, variableIndex;
    namespaces = {};
    colors = {};
    namespaces['rdf'] = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
    namespaces['rdfs'] = 'http://www.w3.org/2000/01/rdf-schema#';
    namespaces['owl'] = 'http://www.w3.org/2002/07/owl#';
    namespaces['xsd'] = 'http://www.w3.org/2001/XMLSchema#';
    if (App.config.sendRDF) {
      $.extend(namespaces, App.parseRDFPrefixes(App.cm['rdf'].getValue()));
    }
    if (lang === 'rif') {
      $.extend(namespaces, App.parseRIFPrefixes(App.cm['rif'].getValue()));
    }
    if (lang === 'sparql') {
      $.extend(namespaces, App.parseSPARQLPrefixes(App.cm['sparql'].getValue()));
    }
    for (key in namespaces) {
      value = namespaces[key];
      colors[key] = randomColor({
        luminosity: 'dark'
      });
    }
    resultSets = App.preprocessResults(data, namespaces, colors);
    if (resultSets.length) {
      resultTab = "<div class='result-tab-content'>";
      resultTab += "<div>";
      if (Object.keys(namespaces).length > 0) {
        resultTab += "<h4>Prefixes</h4>";
        resultTab += "<table>";
        for (value in namespaces) {
          prefix = namespaces[value];
          resultTab += "    <tr>";
          resultTab += "        <td class='sparql-prefix' style='color: ";
          resultTab += colors[value];
          resultTab += "'>";
          resultTab += _.escape(prefix);
          resultTab += "</td>";
          resultTab += "        <td>";
          resultTab += _.escape(value);
          resultTab += "</td>";
          resultTab += "    </tr>";
        }
        resultTab += "</table>";
      }
      resultTab += "";
      for (resultSetIndex = j = 0, len = resultSets.length; j < len; resultSetIndex = ++j) {
        resultSet = resultSets[resultSetIndex];
        resultTab += "<h4>";
        resultTab += _.escape(resultSet.name);
        resultTab += " (";
        resultTab += _.escape(resultSet.results.length);
        resultTab += ")</h4>";
        if (resultSet.results.length > 0) {
          resultTab += "<table class='results'>";
          resultTab += "    <thead>";
          resultTab += "        <tr>";
          ref = resultSet.head;
          for (variableIndex = l = 0, len1 = ref.length; l < len1; variableIndex = ++l) {
            variable = ref[variableIndex];
            resultTab += "            <th>";
            resultTab += "                ";
            resultTab += _.escape(variable);
            resultTab += "            </th>";
          }
          resultTab += "        </tr>";
          resultTab += "    </thead>";
          resultTab += "    <tbody>";
          ref1 = resultSet.results;
          for (resultIndex = n = 0, len2 = ref1.length; n < len2; resultIndex = ++n) {
            result = ref1[resultIndex];
            resultTab += "        <tr>";
            for (bindIndex = o = 0, len3 = result.length; o < len3; bindIndex = ++o) {
              bind = result[bindIndex];
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
        localhtml = "";
        localhtml += "<html class=' js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths'>";
        localhtml += "<head>";
        localhtml += "    <link rel='shortcut icon' href='images/favicon.png' type='image/png'>";
        localhtml += "    <meta charset='utf-8'>";
        localhtml += "    <title>Result of Query</title>";
        localhtml += "    <meta name='description' content=''>";
        localhtml += "    <meta name='viewport' content='width=device-width, initial-scale=1'>";
        localhtml += "    <link rel='stylesheet' href='https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/vendor.css'>";
        localhtml += "    <!-- TODO: Keep fonts in this repo? -->";
        localhtml += "    <link href='//fonts.googleapis.com/css?family=Source+Code+Pro:400,700' rel='stylesheet' type='text/css'>";
        localhtml += "    <link href='//fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>";
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
        return $("#downloadHTMLResult").attr('href', makeTextFile(localhtml));
      }
    } else {
      if ('queryError' in data) {
        return App.logError('Sparql: ' + data.queryError.errorMessage, lang, data.queryError.line);
      } else if ('rdfError' in data) {
        return App.logError('RDF: ' + data.rdfError.errorMessage, 'rdf', data.rdfError.line);
      } else if ('rifError' in data) {
        return App.logError('RIF: ' + data.rifError.errorMessage, 'rif', data.rifError.line);
      } else if ('error' in data) {
        return App.logError(data.error);
      } else {
        if (App.config.endpoints[App.config.selectedEndpoint].nonstandard) {
          return App.logError('Endpoint answer was not valid.');
        } else {
          return App.logError(data);
        }
      }
    }
  };

  App.emptyResultSet = function() {
    return {
      name: 'Results',
      head: [],
      results: []
    };
  };

  App.processSparql = function(doc, namespaces, colors) {
    var bind, bindings, i, index, j, l, len, len1, len2, n, presult, ref, ref1, result, resultSet, value, varbinding, variable, varname, varorder;
    resultSet = App.emptyResultSet();
    if ('boolean' in doc.sparql) {
      resultSet.head.push('Boolean');
      resultSet.results.push([doc.sparql.boolean]);
    } else if (doc.sparql.head !== "") {
      if (!$.isArray(doc.sparql.head.variable)) {
        doc.sparql.head.variable = [doc.sparql.head.variable];
      }
      i = 0;
      varorder = [];
      ref = doc.sparql.head.variable;
      for (j = 0, len = ref.length; j < len; j++) {
        variable = ref[j];
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
      ref1 = doc.sparql.results.result;
      for (l = 0, len1 = ref1.length; l < len1; l++) {
        result = ref1[l];
        presult = [];
        if ($.isArray(result.binding)) {
          bindings = result.binding;
        } else {
          bindings = [result.binding];
        }
        varbinding = [];
        for (n = 0, len2 = bindings.length; n < len2; n++) {
          bind = bindings[n];
          value = '';
          if ((bind != null)) {
            if ('uri' in bind) {
              value = App.replacePrefixes(bind.uri, namespaces, colors);
            } else if ('literal' in bind) {
              value = "\"" + _.escape(bind.literal) + "\"";
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
        for (varname in varorder) {
          index = varorder[varname];
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
    var j, l, len, len1, result, resultSet, triple, variable, varnames;
    varnames = ['subject', 'predicate', 'object'];
    resultSet = {
      name: 'Triples',
      head: varnames,
      results: []
    };
    for (j = 0, len = data.length; j < len; j++) {
      triple = data[j];
      result = [];
      for (l = 0, len1 = varnames.length; l < len1; l++) {
        variable = varnames[l];
        App.processLiteral(triple[variable], namespaces, colors, result);
      }
      resultSet.results.push(result);
    }
    return resultSet;
  };

  App.processPredicates = function(preds, namespaces, colors) {
    var j, k, l, len, len1, len2, n, para, pred, ref, ref1, result, resultSet, v;
    resultSet = {
      name: 'Predicates',
      head: [],
      results: []
    };
    resultSet.head.push('Predicate Name');
    if (preds[0].parameters) {
      ref = preds[0].parameters;
      for (k = j = 0, len = ref.length; j < len; k = ++j) {
        v = ref[k];
        resultSet.head.push("Arg. " + (k + 1));
      }
    }
    for (l = 0, len1 = preds.length; l < len1; l++) {
      pred = preds[l];
      result = [];
      result.push(App.replacePrefixes(pred.predicateName.value, namespaces, colors));
      if (pred.parameters) {
        ref1 = pred.parameters;
        for (n = 0, len2 = ref1.length; n < len2; n++) {
          para = ref1[n];
          App.processLiteral(para, namespaces, colors, result);
        }
      }
      resultSet.results.push(result);
    }
    return resultSet;
  };

  App.processLiteral = function(para, namespaces, colors, result) {
    var prefixeddatatype, r;
    para.value = _.escape(para.value);
    if (para.datatype) {
      prefixeddatatype = App.replacePrefixes(para.datatype, namespaces, colors);
      return result.push("\"" + para.value + "\"^^" + prefixeddatatype);
    } else if (para.type && para.type === 'uri') {
      return result.push(App.replacePrefixes(para.value, namespaces, colors));
    } else if (para.type && para.type === 'bnode') {
      return result.push("_:" + para.value);
    } else {
      r = "\"" + para.value + "\"";
      if (para['xml:lang']) {
        r = r + '@' + para['xml:lang'];
      }
      return result.push(r);
    }
  };

  App.replacePrefixes = function(str, namespaces, colors) {
    var base, key, localhtml, prefix;
    str = _.escape(str);
    for (key in namespaces) {
      prefix = namespaces[key];
      if (str.indexOf(prefix) !== -1) {
        localhtml = "<span class='sparql-prefix' style='color: ";
        localhtml += colors[key];
        localhtml += "'>";
        localhtml += _.escape(key);
        localhtml += ":</span>";
        return str.replace(prefix, localhtml);
      }
    }
    base = App.baseName(str);
    return "&lt;" + str.replace(base, "<em>" + base + "</em>") + "&gt;";
  };

  App.parseRDFPrefixes = function(data) {
    var m, prefixes, reg;
    prefixes = {};
    reg = /@prefix\s+:\s*<([^>]+)>\s*\./g;
    while ((m = reg.exec(data))) {
      prefixes[''] = m[1];
    }
    reg = /@prefix\s+([A-z0-9-]+):\s*<([^>]+)>\s*\./g;
    while ((m = reg.exec(data))) {
      prefixes[m[1]] = m[2];
    }
    return prefixes;
  };

  App.parseSPARQLPrefixes = function(data) {
    var m, prefixes, reg;
    prefixes = {};
    reg = /base\s+<([^>]+)>/ig;
    while ((m = reg.exec(data))) {
      prefixes[''] = m[1];
    }
    reg = /prefix\s+([A-z0-9-]+)\s*:\s*<([^>]+)>/ig;
    while ((m = reg.exec(data))) {
      prefixes[m[1]] = m[2];
    }
    return prefixes;
  };

  App.parseRIFPrefixes = function(data) {
    var m, prefixes, reg;
    prefixes = {};
    reg = /base\(\s*<([^>]+)>\s*\)/ig;
    while ((m = reg.exec(data))) {
      prefixes[''] = m[1];
    }
    reg = /prefix\(([^\s]+)\s+<([^>]+)>\s*\)/ig;
    while ((m = reg.exec(data))) {
      prefixes[m[1]] = m[2];
    }
    return prefixes;
  };

  App.logError = function(msg, editor, line) {
    var localhtml;
    if (editor && line) {
      line--;
      App.cm[editor].setSelection({
        line: line,
        ch: 0
      }, {
        line: line,
        ch: 80
      });
      $("." + editor + "-tab a").click();
    }
    localhtml = "<h4><i class='fa fa-exclamation-triangle'></i> Error</h4><p>The Server responded with:</p><pre style='white-space: pre-wrap;'>";
    localhtml += _.escape(msg);
    localhtml += "</pre>";
    $('#result-tab').html(localhtml);
    App.additionalHiddenTabs = ["graph", "op-graph", "luposdate3000-graph", "luposdate3000-sonification"];
    return App.initConfigComponentsHideTabs();
  };

  App.baseName = function(str) {
    var base;
    base = new String(str).substring(str.lastIndexOf('/') + 1);
    if (base.lastIndexOf('.') !== -1) {
      base = base.substring(0, base.lastIndexOf('.'));
    }
    return base;
  };

  App.initConfigComponentsEndpointSelector = function() {
    var endpoint, i, j, len, localhtml, ref;
    localhtml = "";
    i = 0;
    ref = App.config.endpoints;
    for (j = 0, len = ref.length; j < len; j++) {
      endpoint = ref[j];
      localhtml += "<option value='";
      localhtml += i;
      localhtml += "'>";
      localhtml += _.escape(endpoint.name);
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
        return App.initConfigComponentsHideTabs();
      });
    }
    $('#endpoint_selector').val(App.config.selectedEndpoint);
    return $('#endpoint_selector').change();
  };

  App.initConfigComponentsEvaluatorSelector = function() {
    var endpoint, evaluator, i, j, len, localhtml, ref;
    endpoint = App.config.endpoints[App.config.selectedEndpoint];
    localhtml = "";
    i = 0;
    ref = endpoint.evaluators;
    for (j = 0, len = ref.length; j < len; j++) {
      evaluator = ref[j];
      localhtml += "<option value='";
      localhtml += i;
      localhtml += "'>";
      localhtml += _.escape(evaluator);
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
        if (App.selectedEvaluatorName === "Jena" || App.selectedEvaluatorName === "Sesame") {
          App.config.evalGraphSparql = false;
          App.config.evalGraphRif = false;
          App.updateConfigComponentsEvalGraph();
        }
        App.initConfigComponentsHideTabs();
        App.initConfigComponentsHideWithGraph();
        App.additionalHiddenTabs = App.rightTabs;
        App.initConfigComponentsHideTabs();
        return App.initConfigComponentsHideInference();
      });
    }
    $('#evaluator_selector').val(endpoint.selectedEvaluator);
    return $('#evaluator_selector').change();
  };

  App.initConfigComponentsHideWithGraph = function() {
    if (App.config.hide.withGraph || App.selectedEvaluatorName === "Jena" || App.selectedEvaluatorName === "Sesame") {
      return $('.label-with-graph').hide();
    } else {
      return $('.label-with-graph').show();
    }
  };

  App.initConfigComponentsHideTabs = function() {
    var allTabs, j, l, leftAvailableTab, len, len1, len2, len3, n, o, ref, ref1, ref2, ref3, rightAvailableTab, selector, selector1, selector2, tab, tabsToHide;
    tabsToHide = [];
    ref = App.config.hide.tabs;
    for (j = 0, len = ref.length; j < len; j++) {
      tab = ref[j];
      tabsToHide.push(tab);
    }
    ref1 = App.additionalHiddenTabs;
    for (l = 0, len1 = ref1.length; l < len1; l++) {
      tab = ref1[l];
      tabsToHide.push(tab);
    }
    if (App.selectedEvaluatorName === "Jena" || App.selectedEvaluatorName === "Sesame" || App.selectedEvaluatorName.indexOf("Luposdate3000") !== -1) {
      tabsToHide.push("rif");
    }
    allTabs = [];
    leftAvailableTab = "";
    ref2 = App.leftTabs;
    for (n = 0, len2 = ref2.length; n < len2; n++) {
      tab = ref2[n];
      selector1 = "#";
      selector1 += tab;
      selector1 += "-tab";
      selector2 = "a[href=#";
      selector2 += tab;
      selector2 += "-tab]";
      if (indexOf.call(tabsToHide, tab) >= 0) {
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
    rightAvailableTab = "";
    ref3 = App.rightTabs;
    for (o = 0, len3 = ref3.length; o < len3; o++) {
      tab = ref3[o];
      selector1 = "#";
      selector1 += tab;
      selector1 += "-tab";
      selector2 = "a[href=#";
      selector2 += tab;
      selector2 += "-tab]";
      if (indexOf.call(tabsToHide, tab) >= 0) {
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
    if (indexOf.call(tabsToHide, "rif") >= 0) {
      $("#rule_rif").hide();
      $("#rule_rif_label").hide();
    } else {
      $("#rule_rif").show();
      $("#rule_rif_label").show();
    }
    if ((indexOf.call(tabsToHide, "graph") >= 0) && (indexOf.call(tabsToHide, "op-graph") >= 0)) {
      $('#graphsettings').hide();
    } else {
      $('#graphsettings').show();
    }
    if (indexOf.call(tabsToHide, "luposdate3000-sonification") >= 0) {
      return $("#sonificationsettings").hide();
    } else {
      return $("#sonificationsettings").show();
    }
  };

  App.updateConfigComponentsEvalGraph = function() {
    $("#eval-graph-sparql").prop('checked', App.config.evalGraphSparql);
    return $("#eval-graph-rif").prop('checked', App.config.evalGraphRif);
  };

  App.initConfigComponentsEvalGraph = function() {
    $("#eval-graph-sparql").change(function() {
      return App.config.evalGraphSparql = $("#eval-graph-sparql").is(':checked');
    });
    $("#eval-graph-rif").change(function() {
      return App.config.evalGraphRif = $("#eval-graph-rif").is(':checked');
    });
    return App.updateConfigComponentsEvalGraph();
  };

  App.initConfigComponentsSendRdf = function() {
    $('#send_rdf').change(function() {
      return App.config.sendRDF = $('#send_rdf').is(':checked');
    });
    if (App.config.sendRDF) {
      $('#send_rdf').click();
    }
    if (App.config.hide.sendRDF) {
      return $('#send_rdf').hide();
    }
  };

  App.initConfigComponentsInference = function() {
    var selector;
    $('#rule_radios input').change(function() {
      return App.config.queryParameters.inference = $("input[name='rule']:checked").val();
    });
    selector = "[value=";
    selector += App.config.queryParameters.inference;
    selector += "]";
    return $(selector).click();
  };

  App.initConfigComponentsHideInference = function() {
    var actual, allRadio, j, l, len, len1, len2, n, radio, results, results1, s;
    allRadio = ["rdfs", "owl", "rif", "without"];
    for (j = 0, len = allRadio.length; j < len; j++) {
      radio = allRadio[j];
      s = '#rule_' + radio;
      $(s).show();
      $(s + '_label').show();
    }
    if (App.selectedEvaluatorName.indexOf("Luposdate3000") !== -1) {
      results = [];
      for (l = 0, len1 = allRadio.length; l < len1; l++) {
        radio = allRadio[l];
        s = '#rule_' + radio;
        $(s).hide();
        results.push($(s + '_label').hide());
      }
      return results;
    } else if (App.config.hide.inference) {
      results1 = [];
      for (n = 0, len2 = allRadio.length; n < len2; n++) {
        radio = allRadio[n];
        actual = App.config['queryParameters']['inference'];
        if (actual.toLowerCase() !== radio && !(actual === "OWL2RL" && radio === "owl")) {
          s = '#rule_' + radio;
          $(s).hide();
          results1.push($(s + '_label').hide());
        } else {
          results1.push(void 0);
        }
      }
      return results1;
    }
  };

  App.initConfigComponents = function() {
    var j, len, ref, results, tab;
    $('.my-tabs .my-tab-links a').click(function(e) {
      var currentAttrValue;
      currentAttrValue = $(this).attr('href');
      $('.my-tabs ' + currentAttrValue).show().siblings().hide();
      $(this).parent('li').addClass('active').siblings().removeClass('active');
      return e.preventDefault();
    });
    App.initConfigComponentsEndpointSelector();
    App.initConfigComponentsEvaluatorSelector();
    App.initConfigComponentsHideWithGraph();
    App.initConfigComponentsHideTabs();
    App.initConfigComponentsEvalGraph();
    App.initConfigComponentsSendRdf();
    App.initConfigComponentsInference();
    App.initConfigComponentsHideInference();
    ref = App.config.readOnlyTabs;
    results = [];
    for (j = 0, len = ref.length; j < len; j++) {
      tab = ref[j];
      results.push(App.cm[tab].setOption("readOnly", true));
    }
    return results;
  };

  delay = function(ms, func) {
    return setTimeout(func, ms);
  };

  $(document).ready(function() {
    return App.init();
  });

  textFile = null;

  makeTextFile = function(text) {
    var data;
    data = new Blob([text], {
      type: 'text/plain'
    });
    if (textFile !== null) {
      window.URL.revokeObjectURL(textFile);
    }
    textFile = window.URL.createObjectURL(data);
    return textFile;
  };

}).call(this);
