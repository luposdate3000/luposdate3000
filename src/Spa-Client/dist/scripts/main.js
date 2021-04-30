'use strict';
var delay, getGraphData, makeTextFile, textFile,
  indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

this.App = {
  isMergeView: false
};

App.init = function() {
  $('#luposdate3000graph-tab').hide();
  App.x2js = new X2JS({
    attributeArray: '_attributes'
  });
  App.URIQuery = URI(document.location.href).query(true);
  $.getJSON('config/operators.json').done(function(data) {
    App.operators = data;
    if (App.URIQuery.config) {
      return $.getJSON(App.URIQuery.config).done(function(addData) {
        return App.operators = $.extend(data, addData, {});
      });
    }
  });
  return $.getJSON('config/config.json').done(function(data) {
    App.config = data;
    if (App.URIQuery.config) {
      return $.getJSON(App.URIQuery.config).done(function(addData) {
        App.config = $.extend(data, addData, {});
        return App.play();
      });
    } else {
      return App.play();
    }
  });
};

App.play = function() {
  App.loadEditors();
  App.bindEvents();
  App.initConfigComponents();
  App.insertQueryPicker();
  App.samples = SampleLibrary.load({
    instruments: ['piano', 'bass-electric', 'bassoon', 'cello', 'clarinet', 'contrabass', 'flute', 'french-horn', 'guitar-acoustic', 'guitar-electric', 'guitar-nylon', 'harmonium', 'harp', 'organ', 'saxophone', 'trombone', 'trumpet', 'tuba', 'violin', 'xylophone'],
    baseUrl: "./scripts/algos/samples/"
  });
  pleaseWait.finish();
  return delay(1500, function() {
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

App.setSelectedEndpoint = function() {
  var endpoint, endpointname, i, j, len, ref, results;
  endpointname = $("#endpoint_selector").val();
  i = 0;
  ref = App.config.endpoints;
  results = [];
  for (j = 0, len = ref.length; j < len; j++) {
    endpoint = ref[j];
    if (endpoint.name === endpointname) {
      App.config.selectedEndpoint = i;
    }
    results.push(i++);
  }
  return results;
};

getGraphData = function(data, urlPrefix, method, target) {
  var request;
  request = {
    query: data.query,
    evaluator: $('#evaluator_selector').val()
  };
  $.ajax({
    url: urlPrefix + "/info",
    method: method,
    data: JSON.stringify(request),
    success: function(data) {
      return createGraph(data, target);
    },
    error: function(xhr, status, error) {
      return App.logError(error);
    }
  });
  return $.ajax({
    url: urlPrefix + "/graphs",
    method: method,
    data: JSON.stringify(data),
    success: function(data) {
      return createOPGraph(data, target);
    },
    error: function(xhr, status, error) {
      return App.logError(error);
    }
  });
};

App.showWithGraph = function() {
  if (App.config.hide.withGraph) {
    return $('.label-with-graph').hide();
  } else {
    return $('.label-with-graph').show();
  }
};

App.bindEvents = function() {
  $('#getLuposdate3000Graphlink').click(function() {
    $('#luposdate3000-graph-tab').show();
    return $('#luposdate3000-sonification-tab').hide();
  });
  $('#getLuposdate3000GraphSonlink').click(function() {
    $('#luposdate3000-graph-tab').hide();
    return $('#luposdate3000-sonification-tab').show();
  });
  $('#sonificationsettings').click(function() {
    $('#graphsettings-ast').hide();
    $('#sonificationsettings-menu').show();
    return $('#graphsettings-operator').hide();
  });
  $('#graphsettings').click(function() {
    $('#sonificationsettings-menu').hide();
    $('#graphsettings-ast').show();
    return $('#graphsettings-operator').show();
  });
  $('#getgraphdata').click(function() {
    $('#graphsettings').show();
    $('#graphsettings-ast').show();
    return $('#graphsettings-operator').hide();
  });
  $('#endpoint_selector').change(function() {
    var version;
    version = $(this).val();
    if (version === 'Luposdate3000 - Browser' || version === 'Luposdate3000 - Endpoint') {
      $('#evaluator_selector_label').hide();
      return $('#checkbox_downloadResult_label').hide();
    } else {
      $('#evaluator_selector_label').show();
      return $('#checkbox_downloadResult_label').show();
    }
  });
  $('#evaluator_selector').change(function() {
    var evaluator;
    evaluator = $(this).val();
    if (evaluator === "Jena" || evaluator === "Sesame") {
      $('#eval-graph-sparql').prop('checked', false);
      $('#eval-graph-rif').prop('checked', false);
      $('.label-with-graph').hide();
      if ($('a[href$="#rif-tab"]').attr("aria-selected") === "true") {
        $('a[href$="#sparql-tab"]').click();
      }
      return $('a[href$="#rif-tab"]').hide();
    } else {
      App.showWithGraph();
      return $('a[href$="#rif-tab"]').show();
    }
  });
  $('#getopgraphdata').click(function() {
    $('#graphsettings').show();
    $('#graphsettings-operator').show();
    return $('#graphsettings-ast').hide();
  });
  $('#getLuposdate3000Graph').click(function() {
    $('#graphsettings').hide();
    $('#graphsettings-operator').show();
    return $('#graphsettings-ast').hide();
  });
  $('#result-tab').click(function() {
    return $('#graphsettings').hide();
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
    var data, endpoint, folder, inference, key, locator, method, target, url, withGraph;
    if ($('#endpoint_selector').val() === 'Luposdate3000 - Browser' || $('#endpoint_selector').val() === 'Luposdate3000 - Endpoint') {
      $('#result-tab').show();
      $('#getgraphdata').hide();
      $('#getopgraphdata').hide();
      $('#getLuposdate3000Graph').show();
      $('#getLuposdate3000GraphSon').show();
      console.log("selecting endpoint");
      return App.setSelectedEndpoint();
    } else {
      for (key in App.cm) {
        App.cm[key].save();
      }
      target = $(this).data('target');
      App.setSelectedEndpoint();
      endpoint = App.config.endpoints[App.config.selectedEndpoint];
      data = {
        query: $(this).parents('.query').find('.editor').val()
      };
      if (target === 'sparql') {
        withGraph = $('#eval-graph-sparql').prop('checked');
      } else {
        withGraph = $('#eval-graph-rif').prop('checked');
      }
      if (endpoint.nonstandard) {
        folder = endpoint[target];
        if (App.config.sendRDF) {
          data['rdf'] = App.cm['rdf'].getValue();
        } else {
          data['rdf'] = '';
        }
        method = folder[1];
        locator = folder[0];
        data['formats'] = ['xml', 'plain'];
        for (key in App.config['queryParameters']) {
          data[key] = App.config['queryParameters'][key];
        }
        inference = $('input[name="rule"]:checked').val();
        data['inference'] = inference;
        if (inference === 'RIF') {
          data['rif'] = $('#codemirror_rif').val();
        }
        data['evaluator'] = $('#evaluator_selector').val();
      } else {
        method = 'GET';
        locator = endpoint.without;
      }
      url = "" + App.config.endpoints[App.config.selectedEndpoint].url + locator;
      if (withGraph) {
        getGraphData(data, url, method, target);
        $('#getgraphdata').parent("dd").show();
        $('#getopgraphdata').parent("dd").show();
      } else {
        $('.results-tab a').click();
        $('#getgraphdata').parent("dd").hide();
        $('#getopgraphdata').parent("dd").hide();
      }
      data = JSON.stringify(data);
      if (App.isMergeView) {
        $('.results-tab a').click();
      }
      $('#results-tab').html(JST['spinner']());
      return $.ajax({
        url: url,
        method: method,
        data: data,
        success: function(data) {
          return App.processResults(data, target);
        },
        error: function(xhr, status, error) {
          return App.logError(error);
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
    if ($('.left-side .right-side-tab').length) {
      $('.right-side-tab-content').appendTo($('.right-side .tabs-content'));
      $('.right-side-tab').appendTo($('.right-side .tabs'));
      $('.right-side').show();
      $('.right-side .tabs a').get(0).click();
    } else {
      $('.right-side-tab-content').appendTo($('.left-side .tabs-content'));
      $('.right-side-tab').appendTo($('.left-side .tabs'));
      $('.right-side').hide();
    }
    $(this).toggleClass('active');
    $('.left-side').toggleClass('medium-6 medium-12');
    $('.left-side .tabs a').get(0).click();
    return App.isMergeView = !App.isMergeView;
  });
};

App.insertQueryPicker = function() {
  var lang, results;
  results = [];
  for (lang in {
    'sparql': 'sparql',
    'rdf': 'rdf',
    'rif': 'rif'
  }) {
    results.push($("#query-select-" + lang).html(JST['query_picker']({
      options: App.config['defaultData'][lang]
    })));
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
        }
      }
    }
  }
  if ($.isXMLDoc(xml)) {
    sparql = App.x2js.xml2json(xml);
    resultSets.push(App.processSparql(sparql, namespaces, colors));
  }
  return resultSets;
};

App.processResults = function(data, lang) {
  var colors, key, namespaces, resultSets, resultTab, value;
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
    resultTab = JST['results']({
      resultSets: resultSets,
      prefixes: namespaces,
      colors: colors
    });
    $('#results-tab').html(resultTab);
    if ($('#checkbox_downloadResult').is(':checked')) {
      $('#results-tab').append('<div class="buttonarea"><a id="downloadHTMLResult" download="Result.html" class="button evaluate">Download Result HTML</a></div>');
      return $("#downloadHTMLResult").attr('href', makeTextFile(JST['results/standalone']({
        content: resultTab
      })));
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
  var base, key, prefix;
  str = _.escape(str);
  for (key in namespaces) {
    prefix = namespaces[key];
    if (str.indexOf(prefix) !== -1) {
      return str.replace(prefix, JST['results/prefix']({
        prefix: key,
        color: colors[key]
      }));
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
  return $('#results-tab').html(JST['results/error']({
    msg: msg
  }));
};

App.baseName = function(str) {
  var base;
  base = new String(str).substring(str.lastIndexOf('/') + 1);
  if (base.lastIndexOf('.') !== -1) {
    base = base.substring(0, base.lastIndexOf('.'));
  }
  return base;
};

App.configComponents = {
  Radio: function(watchedElementsSelector, defaultVal, callback) {
    $(watchedElementsSelector).change(function() {
      callback($(watchedElementsSelector).filter(':checked').val());
      return true;
    });
    if (defaultVal) {
      return $(watchedElementsSelector).filter("[value=" + defaultVal + "]").click();
    }
  },
  Check: function(watchedElementSelector, defaultVal, callback) {
    $(watchedElementSelector).click(function() {
      callback($(watchedElementSelector).is(':checked'));
      return true;
    });
    if (defaultVal) {
      return $(watchedElementSelector).click();
    }
  }
};

App.initConfigComponents = function() {
  var actual, endpoint, evaluator, j, l, len, len1, len2, len3, len4, len5, n, o, p, q, radio, ref, ref1, ref2, ref3, ref4, ref5, s, tab, tones;
  ref = App.config.endpoints;
  for (j = 0, len = ref.length; j < len; j++) {
    endpoint = ref[j];
    $("#endpoint_selector").append('<option value="' + endpoint.name + '">' + endpoint.name + '</option>');
  }
  $('#endpoint_selector').trigger('change');
  ref1 = App.config.evaluators;
  for (l = 0, len1 = ref1.length; l < len1; l++) {
    evaluator = ref1[l];
    $("#evaluator_selector").append('<option value="' + evaluator + '">' + evaluator + '</option>');
  }
  ref2 = App.operators.tones;
  for (n = 0, len2 = ref2.length; n < len2; n++) {
    tones = ref2[n];
    $("#tone_select").append('<option value)"' + tones + '">' + tones + '</option>');
  }
  ref3 = App.config.hide.tabs;
  for (o = 0, len3 = ref3.length; o < len3; o++) {
    tab = ref3[o];
    $("#" + tab + "-tab").hide().removeClass('active');
    $("a[href=#" + tab + "-tab]").parent("dd").hide().removeClass('active');
  }
  if (indexOf.call(App.config.hide.tabs, 'graph') >= 0) {
    $('#getGraph').hide();
  }
  if (indexOf.call(App.config.hide.tabs, 'rif') >= 0) {
    $('#rule_rif').hide();
    $('#rule_rif_label').hide();
  }
  delay(1000, function() {
    return $('.tabs').each(function() {
      return $(this).find('dd:visible a').first().click();
    });
  });
  if (App.config.hide.sendRDF) {
    $('#send_rdf').hide();
  }
  if (App.config.hide.inference) {
    ref4 = ["rdfs", "owl", "rif", "without"];
    for (p = 0, len4 = ref4.length; p < len4; p++) {
      radio = ref4[p];
      actual = App.config['queryParameters']['inference'];
      if (actual.toLowerCase() !== radio && !(actual === "OWL2RL" && radio === "owl")) {
        s = '#rule_' + radio;
        $(s).hide();
        $(s + '_label').hide();
      }
    }
  }
  App.showWithGraph();
  ref5 = App.config.readOnlyTabs;
  for (q = 0, len5 = ref5.length; q < len5; q++) {
    tab = ref5[q];
    App.cm[tab].setOption("readOnly", true);
  }
  App.configComponents.Radio('#rule_radios input', App.config['queryParameters']['inference'], function(val) {
    return App.config['queryParameters']['inference'] = val;
  });
  return App.configComponents.Check('#send_rdf', App.config.sendRDF, function(send) {
    return App.config.sendRDF = send;
  });
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
