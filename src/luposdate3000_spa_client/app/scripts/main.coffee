# Attach App as global variable for debugging
@App =
    isMergeView: false

App.init = ->

    App.mappingIdentifiers = {
        Pitch: '#pitchSettings'
        Instrument: '#instrumentSettings'
        Loudness: '#loudnessSettings'
        Spatialization: '#spatializationSettings'
        Duration: '#durationSettings'
        Melody: '#melodySettings'
        Chord: '#chordSettings'
        Octave: '#octaveSettings'
    }
    App.mappingFunctions = {}
    chordSetup()
    durationSetup()
    instrumentSetup()
    loudnessSetup()
    melodySetup()
    octaveSetup()
    pitchSetup()
    spatializationSetup()

    # Rico: Luposdate3000 Graph is disabled at the beginning
    $('#luposdate3000graph-tab').hide()

    # Load xml converter
    App.x2js = new X2JS
        attributeArray: '_attributes'

    # Load configuration
    App.URIQuery = URI(document.location.href).query(true)

    $.getJSON('config/config.json').done (data) ->
        App.config = data
        if App.URIQuery.config
            $.getJSON(App.URIQuery.config).done (addData) ->
                App.config = $.extend(data, addData, {})
                App.loadSonificationConfig()
        else
            App.loadSonificationConfig()

App.loadSonificationConfig = ->
    $.getJSON('config/operators.json').done (data) ->
        App.operators = data
        if App.URIQuery.config
            $.getJSON(App.URIQuery.config).done (addData) ->
                App.operators = $.extend(data, addData, {})
        App.play()

App.play = ->
    App.loadEditors()
    App.bindEvents()
    App.initConfigComponents()
    App.insertQueryPicker()
    App.samples = []
    #Rico: Load instruments
    #App.samples = SampleLibrary.load(
    #    instruments: ['piano', 'bass-electric', 'bassoon', 'cello', 'clarinet', 'contrabass', 'flute', 'french-horn',
    #        'guitar-acoustic', 'guitar-electric', 'guitar-nylon', 'harmonium', 'harp', 'organ', 'saxophone', 'trombone',
    #        'trumpet', 'tuba', 'violin', 'xylophone'],
    #    baseUrl: "./resources/samples/"
    #)

    if !App.config.hasOwnProperty("sonification")
        resetAllSonificationSettings()

    # CodeMirror is display:none during loading screen so we need
    # a delayed refresh to have it display properly
    pleaseWait.finish()
    delay 1500, ->
        App.cm['sparql'].refresh()
        App.cm['rif'].refresh()
        App.cm['rdf'].refresh()

App.loadEditors = ->
# Initialize editors
    App.cm = {}

    App.cm['sparql'] = CodeMirror.fromTextArea document.getElementById('codemirror'),
        mode: 'sparql'
        lineNumbers: true
        matchBrackets: true
        autoCloseBrackets: true

    App.loadQuery('sparql', 0)

    App.cm['rdf'] = CodeMirror.fromTextArea document.getElementById('codemirror_rdf'),
        lineNumbers: true
        mode: 'n3'
        matchBrackets: true
        autoCloseBrackets: true

    App.loadQuery('rdf', 0)

    App.cm['rif'] = CodeMirror.fromTextArea document.getElementById('codemirror_rif'),
        lineNumbers: true
        mode: 'rif'
        matchBrackets: true
        autoCloseBrackets: true

    App.loadQuery('rif', 0)

App.loadQuery = (lang, index) ->
    $statusElement = $(".load-query-status[data-lang=#{lang}]")
    $statusElement.show().html '<i class="fa fa-spinner"></i>'
    $.ajax(
        url: App.config.defaultData[lang][index]
        dataType: 'text'
    ).done (data) ->
        $statusElement.html('<i class="fa fa-check-circle"></i>').fadeOut(500)
        App.cm[lang].getDoc().setValue(data)

App.setSelectedEndpoint = ->
    endpointname = $("#endpoint_selector").val()
    i = 0
    for endpoint in App.config.endpoints
        if endpoint.name == endpointname
            App.config.selectedEndpoint = i
        i++

getGraphData = (data, urlPrefix, method, target)->

# AST Request
    request = {
        query: data.query
        evaluator: $('#evaluator_selector').val()
    }
    $.ajax
        url: urlPrefix + "/info"
        method: method
        data: JSON.stringify(request)
        success: (data) ->
            createGraph(data, target)
        error: (xhr, status, error) ->
            App.logError error

    # Operator-Graph Request
    $.ajax
        url: urlPrefix + "/graphs"
        method: method
        data: JSON.stringify(data)
        success: (data) ->
            createOPGraph(data, target)
        error: (xhr, status, error) ->
            App.logError error

App.showWithGraph = ->
    if App.config.hide.withGraph
        $('.label-with-graph').hide()
    else
        $('.label-with-graph').show()


App.loadluposdate3000 = (data, url, withGraph) ->
    queryData =
        query: data.query
        evaluator: "XML_STREAM"
    if withGraph
        $.ajax
            url: url + 'sparql/startSession'
            method: "POST"
            data: queryData
            success: (sessionID) ->
                sessionData =
                    sessionID: sessionID
                $.ajax
                    url: url + 'sparql/getLogicalVisual'
                    method: "POST"
                    data: sessionData
                    success: (logSteps) ->
                        addLocicalSteps(logSteps)
                        $.ajax
                            url: url + 'sparql/getPhysicalVisual'
                            method: "POST"
                            data: sessionData
                            success: (phySteps) ->
                                addPhysicalSteps(phySteps)
                                $.ajax
                                    url: url + 'sparql/getVisualisationData'
                                    method: "POST"
                                    data: sessionData
                                    success: (visData) ->
                                        addAnimationDataSplit(visData)
                                        $.ajax
                                            url: url + 'sparql/getResult'
                                            method: "POST"
                                            data: sessionData
                                            success: (resultData) ->
                                                App.result = resultData
                                                $.ajax
                                                    url: url + 'sparql/closeSession'
                                                    method: "POST"
                                                    data: sessionData
                                                    success: (closeResponse) ->
                                                        formatResultData();
                                                    error: (xhr, status, error) ->
                                                        App.logError error
                                            error: (xhr, status, error) ->
                                                App.logError error
                                    error: (xhr, status, error) ->
                                        App.logError error
                            error: (xhr, status, error) ->
                                App.logError error
                    error: (xhr, status, error) ->
                        App.logError error
            error: (xhr, status, error) ->
                App.logError error
    else
        $.ajax
            url: url + 'sparql/query'
            method: "POST"
            data: queryData
            success: (xml) ->
                App.processResults(xml, "sparql")
            error: (xhr, status, error) ->
                App.logError error

App.bindEvents = ->
    $('#getLuposdate3000Graphlink').click ->
        $('#luposdate3000-graph-tab').show()
        $('#luposdate3000-sonification-tab').hide()

    $('#getLuposdate3000GraphSonlink').click ->
        $('#luposdate3000-graph-tab').hide()
        $('#luposdate3000-sonification-tab').show()

    #Rico: Hide graph settings when sonification button is pressed and show sonification settings
    $('#sonificationsettings').click ->
        $('#graphsettings-ast').hide()
        $('#sonificationsettings-menu').show()
        $('#graphsettings-operator').hide()

    #Rico: Show again graph settings when the matching button is clicked
    $('#graphsettings').click ->
        $('#sonificationsettings-menu').hide()
        $('#graphsettings-ast').show()
        $('#graphsettings-operator').show()

    $('#getgraphdata').click ->
        $('#graphsettings').show()
        $('#graphsettings-ast').show()
        $('#graphsettings-operator').hide()

    # Rico: if Luposdate3000 is clicked, disable Endpoint and Evaluator selector
    $('#endpoint_selector').change ->
        version = $(this).val()
        if(version == 'Luposdate3000 - Browser' || version == 'Luposdate3000 - Endpoint')
            $('#evaluator_selector_label').hide()
            $('#checkbox_downloadResult_label').hide()
        else
            $('#evaluator_selector_label').show()
            $('#checkbox_downloadResult_label').show()

    $('#evaluator_selector').change ->
        evaluator = $(this).val()
        if(evaluator == "Jena" || evaluator == "Sesame")
            $('#eval-graph-sparql').prop('checked', false)
            $('#eval-graph-rif').prop('checked', false)
            $('.label-with-graph').hide()
            if($('a[href$="#rif-tab"]').attr("aria-selected") == "true")
                $('a[href$="#sparql-tab"]').click()

            $('a[href$="#rif-tab"]').hide()
        else
            App.showWithGraph();
            $('a[href$="#rif-tab"]').show()

    $('#getopgraphdata').click ->
        $('#graphsettings').show()
        $('#graphsettings-operator').show()
        $('#graphsettings-ast').hide()

    #Rico: disable operator graph settings when sonification graph tab is pressed
    $('#getLuposdate3000Graph').click ->
        $('#graphsettings').hide()
        $('#graphsettings-operator').show()
        $('#graphsettings-ast').hide()

    $('#result-tab').click ->
        $('#graphsettings').hide()

    $('#op-graph-down').click ->
        value = $('#graph-select').val()
        value = parseInt(value, 10)
        $('#op-graph-up').prop('disabled', false)
        $('#op-graph-up').removeClass('disabled')
        if (value != 0)
            value = value - 1
            graphSelect = $('#graph-select')

            graphValue = graphSelect.val(value)
            graphSelect.trigger('change')
            if (value == 0)
                $(this).addClass('disabled')
    #           $(this).prop('disabled', true);


    $('#op-graph-up').click ->
        value = $('#graph-select').val()
        value = parseInt(value, 10)
        $('#op-graph-down').prop('disabled', false)
        $('#op-graph-down').removeClass('disabled')
        graphSelect = $('#graph-select')
        max = graphSelect.children('option').length - 1

        if (value != max)
            value = value + 1

            graphValue = graphSelect.val(value)
            graphSelect.trigger('change')
            if (value == max)
                $(this).addClass('disabled')
    #           $(this).prop('disabled', true);


    # Send query to endpoint
    $('.query .evaluate').click ->

# Copy changes to textarea
        for key of App.cm
            App.cm[key].save()

        target = $(this).data 'target'
        App.setSelectedEndpoint()
        if target == 'sparql'
            withGraph = $('#eval-graph-sparql').prop('checked')
        else
            withGraph = $('#eval-graph-rif').prop('checked')
        endpoint = App.config.endpoints[App.config.selectedEndpoint]
        data =
            query: App.cm['sparql'].getValue();
        if App.config.sendRDF
            data['rdf'] = App.cm['rdf'].getValue()
        else
            data['rdf'] = ''
        if endpoint.nonstandard
            folder = endpoint[target]
            method = folder[1]
            locator = folder[0]
        else
            method = 'GET'
            locator = endpoint.without
        url = endpoint.url + locator
        if($('#endpoint_selector').val() == 'Luposdate3000 - Browser' || $('#endpoint_selector').val() == 'Luposdate3000 - Endpoint')
            $('#result-tab').show()
            $('#getgraphdata').hide()
            $('#getopgraphdata').hide()
            if withGraph
                $('#getLuposdate3000Graph').show()
                $('#getLuposdate3000GraphSon').show()
                visualisationSetup()
            version = $('#endpoint_selector').val();
            if (version == 'Luposdate3000 - Browser')
                if App.config.sendRDF
                    luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.import_turtle_string(data.rdf);
                #Receive optimized steps for logical and physical operator graph
                if withGraph
                    eev = new luposdate3000_endpoint.lupos.endpoint.EndpointExtendedVisualize(data.query)
                    App.logGraph = eev.getOptimizedStepsLogical();
                    App.physGraph = eev.getOptimizedStepsPhysical();
                    #Result from the query
                    App.result = eev.getResult();
                    eev.closeEEV();
                    eev.initEEV();
                    tmpResult = eev.getDataSteps();
                    addAnimationData(tmpResult)
                    formatResultData();
                else
                    res = luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(data.query)
                    App.processResults(res, "sparql")
            else if (version == 'Luposdate3000 - Endpoint')
                if App.config.sendRDF
                    rdfData =
                        data: data.rdf
                    $.ajax
                        url: url + 'import/turtledata'
                        method: "POST"
                        data: rdfData
                        success: (loadResponse) ->
                            App.loadluposdate3000 data, url, withGraph
                        error: (xhr, status, error) ->
                            App.logError error
                else
                    App.loadluposdate3000 data, url, withGraph
            visualisationStart()
        else
            $('#getgraphdata').show()
            $('#getopgraphdata').show()
            $('#getLuposdate3000Graph').hide()
            $('#getLuposdate3000GraphSon').hide()

            if endpoint.nonstandard
                data['formats'] = ['xml', 'plain']
                # Set query parameters from config
                for key of App.config['queryParameters']
                    data[key] = App.config['queryParameters'][key]
                inference = $('input[name="rule"]:checked').val()
                data['inference'] = inference
                if(inference == 'RIF')
                    data['rif'] = $('#codemirror_rif').val()
                data['evaluator'] = $('#evaluator_selector').val()
            # Nonstandard endpoints expect JSON-string as request body
            # data = JSON.stringify(data)


            if withGraph
#            $('.query .get-graph').trigger("click");
                getGraphData(data, url, method, target)
                $('#getgraphdata').parent("dd").show()
                $('#getopgraphdata').parent("dd").show()
            else
                $('.results-tab a').click()
                $('#getgraphdata').parent("dd").hide()
                $('#getopgraphdata').parent("dd").hide()

            # Nonstandard endpoints expect JSON-string as request body
            data = JSON.stringify(data)


            # Switch to results tab if needed
            if App.isMergeView
                $('.results-tab a').click()
            localhtml = ""
            localhtml += "<h4>Waiting for Server..</h4>"
            localhtml += "<div class='sk-spinner sk-spinner-cube-grid dark'>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "    <div class='sk-cube'></div>"
            localhtml += "</div>"
            $('#results-tab').html localhtml

            # /sparql/query
            # data query= select * ..
            # data evaluator -> EQueryResultToStreamExt.kt
            $.ajax
                url: url
                method: method
                data: data
                success: (data) ->
                    App.processResults data, target
                error: (xhr, status, error) ->
                    App.logError error

    # Reload editor when changing tabs
    $(document).foundation
        tab:
            callback: (tab) ->
                content = $(tab.children('a').attr('href'))
                if content.find('.CodeMirror').length
                    content.find('.CodeMirror')[0].CodeMirror.refresh()

    $('.query-select').change ->
        lang = $(this).data('lang')
        index = $(this).find('option:selected').index()
        App.loadQuery lang, index

    # Toggle fullscreen and other view options
    $('.fullscreen-toggle').click ->
        $('.main-section').toggleClass 'full'
        $(this).toggleClass 'active'

    # Merge/split tabs
    $('.right-side-toggle').click ->
        if $('.left-side .right-side-tab').length
            $('.right-side-tab-content').appendTo($('.right-side .tabs-content'))
            $('.right-side-tab').appendTo($('.right-side .tabs'))
            $('.right-side').show()
            # Auto-click first tab to refresh view
            $('.right-side .tabs a').get(0).click()
        else
            $('.right-side-tab-content').appendTo($('.left-side .tabs-content'))
            $('.right-side-tab').appendTo($('.left-side .tabs'))
            $('.right-side').hide()

        $(this).toggleClass 'active'
        $('.left-side').toggleClass 'medium-6 medium-12'
        $('.left-side .tabs a').get(0).click()
        App.isMergeView = not App.isMergeView


App.insertQueryPicker = ->
    for lang of {'sparql', 'rdf', 'rif'}
        localhtml = ""
        for option in App.config['defaultData'][lang]
            localhtml += "<option value="
            localhtml += _.escape(option)
            localhtml += ">"
            localhtml += App.baseName(option)
            localhtml += "</option>"
        $("#query-select-#{lang}").html localhtml

App.preprocessResults = (data, namespaces, colors) ->
    resultSets = []

    xml = data

    if App.config.endpoints[App.config.selectedEndpoint].nonstandard
# Process specific response types for nonstandard
        if 'triples' of data
            resultSets.push App.processTriples(data.triples, namespaces, colors)
        if 'predicates' of data
            resultSets.push App.processPredicates(data.predicates, namespaces, colors)
        if 'XML' of data
# Sometimes the server will return an empty string
            if data.XML[0] == ''
                resultSets.push App.emptyResultSet()
            else
                try
                    xml = $.parseXML(data.XML[0])
                catch error
                    console.log(error)
# In nonstandard we get XML as string
    else
        try
            xml = $.parseXML(data)
        catch error
            console.log(error)
    # Like we care


    if $.isXMLDoc(xml)
        sparql = App.x2js.xml2json(xml)
        resultSets.push App.processSparql(sparql, namespaces, colors)

    # # Save plain response just in case
    # if 'Plain' of data
    #     doc.plain = data.Plain

    return resultSets

App.processResults = (data, lang) ->

# Find and save defined prefixes
# Generate random colors while we're at it
    namespaces = {}
    colors = {}
    # define some standard prefixes:
    namespaces['rdf'] = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#'
    namespaces['rdfs'] = 'http://www.w3.org/2000/01/rdf-schema#'
    namespaces['owl'] = 'http://www.w3.org/2002/07/owl#'
    namespaces['xsd'] = 'http://www.w3.org/2001/XMLSchema#'
    # get prefixes from user's query, data and rules
    if App.config.sendRDF
        $.extend(namespaces, App.parseRDFPrefixes(App.cm['rdf'].getValue()))
    if lang == 'rif'
        $.extend(namespaces, App.parseRIFPrefixes(App.cm['rif'].getValue()))
    if lang == 'sparql'
        $.extend(namespaces, App.parseSPARQLPrefixes(App.cm['sparql'].getValue()))
    for key, value of namespaces
        colors[key] = randomColor({luminosity: 'dark'})
    # Actually use them in the result
    # Check validity, preprocess if necessary
    resultSets = App.preprocessResults(data, namespaces, colors)

    if resultSets.length
        resultTab = ""
        if Object.keys(prefixes).length > 0
            resultTab += "<h4>Prefixes</h4>"
            resultTab += "<table>"
            for value, prefix of namespaces
                resultTab += "    <tr>"
                resultTab += "        <td class='sparql-prefix' style='color: "
                resultTab += colors[value]
                resultTab += "'>"
                resultTab += prefix
                resultTab += "</td>"
                resultTab += "        <td>"
                resultTab += value
                resultTab += "</td>"
                resultTab += "    </tr>"
            resultTab += "</table>"
        resultTab += ""
        for resultSet, resultSetIndex in resultSets
            resultTab += "<h4>"
            resultTab += _.escape(resultSet.name)
            resultTab += " ("
            resultTab += _.escape(resultSet.results.length)
            resultTab += ")</h4>"
            if resultSet.results.length > 0
                resultTab += "<table class='results'>"
                resultTab += "    <thead>"
                resultTab += "        <tr>"
                for variable,variableIndex in resultSet.head
                    resultTab += "            <th>"
                    resultTab += "                "
                    resultTab += _.escape(variable)
                    resultTab += "            </th>"
                resultTab += "        </tr>"
                resultTab += "    </thead>"
                resultTab += "    <tbody>"
                for result, resultIndex in resultSet.results
                    resultTab += "        <tr>"
                    for bind,bindIndex in result
                        resultTab += "            <td>"
                        resultTab += bind
                        resultTab += "</td>"
                    resultTab += "        </tr>"
                resultTab += "    </tbody>"
                resultTab += "</table>"

        $('#results-tab').html(resultTab)
        if $('#checkbox_downloadResult').is(':checked')
            $('#results-tab').append('<div class="buttonarea"><a id="downloadHTMLResult" download="Result.html" class="button evaluate">Download Result HTML</a></div>')
            localhtml += "<html class=' js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths'>"
            localhtml += ""
            localhtml += "<head>"
            localhtml += "    <link rel='shortcut icon' href='images/favicon.png' type='image/png'>"
            localhtml += "    <meta charset='utf-8'>"
            localhtml += "    <title>Result of Query</title>"
            localhtml += "    <meta name='description' content=''>"
            localhtml += "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
            localhtml += ""
            localhtml += "    <link rel='stylesheet' href='https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/vendor.css'>"
            localhtml += ""
            localhtml += "    <!-- TODO: Keep fonts in this repo? -->"
            localhtml += "    <link href='//fonts.googleapis.com/css?family=Source+Code+Pro:400,700' rel='stylesheet' type='text/css'>"
            localhtml += "    <link href='//fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>"
            localhtml += "    <link href='//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' rel='stylesheet'>"
            localhtml += ""
            localhtml += "    <link rel='stylesheet' href='https://www.ifis.uni-luebeck.de/~groppe/luposdate-js-client/styles/main.css'>"
            localhtml += ""
            localhtml += "    <meta class='foundation-data-attribute-namespace'>"
            localhtml += "    <meta class='foundation-mq-xxlarge'>"
            localhtml += "    <meta class='foundation-mq-xlarge-only'>"
            localhtml += "    <meta class='foundation-mq-xlarge'>"
            localhtml += "    <meta class='foundation-mq-large-only'>"
            localhtml += "    <meta class='foundation-mq-large'>"
            localhtml += "    <meta class='foundation-mq-medium-only'>"
            localhtml += "    <meta class='foundation-mq-medium'>"
            localhtml += "    <meta class='foundation-mq-small-only'>"
            localhtml += "    <meta class='foundation-mq-small'>"
            localhtml += "    <style></style>"
            localhtml += "    <style></style>"
            localhtml += "    <meta class='foundation-mq-topbar'>"
            localhtml += "</head>"
            localhtml += ""
            localhtml += "<body style='margin: 20pt'>"
            localhtml += "    <!--[if lt IE 10]>"
            localhtml += "<p class='browsehappy'>You are using an <strong>outdated</strong> browser. Please <a href='https://browsehappy.com/'>upgrade"
            localhtml += "    your browser</a> to improve your experience.</p>"
            localhtml += "<![endif]-->"
            localhtml += "    <h1>Result of Query</h1>"
            localhtml += ""
            localhtml += "    "
            localhtml += resultTab
            localhtml += ""
            localhtml += "</body>"
            localhtml += ""
            localhtml += "</html>"
            $("#downloadHTMLResult").attr('href', makeTextFile(localhtml))
    else
        if 'queryError' of data
            App.logError 'Sparql: ' + data.queryError.errorMessage, lang, data.queryError.line
        else if 'rdfError' of data
            App.logError 'RDF: ' + data.rdfError.errorMessage, 'rdf', data.rdfError.line
        else if 'rifError' of data
            App.logError 'RIF: ' + data.rifError.errorMessage, 'rif', data.rifError.line
        else if 'error' of data
            App.logError data.error
        else
            if App.config.endpoints[App.config.selectedEndpoint].nonstandard
                App.logError 'Endpoint answer was not valid.'
            else
                App.logError data

App.emptyResultSet = ->
    name: 'Results'
    head: []
    results: []

App.processSparql = (doc, namespaces, colors) ->
    resultSet = App.emptyResultSet()

    if 'boolean' of doc.sparql
        resultSet.head.push 'Boolean'
        resultSet.results.push [doc.sparql.boolean]
    else if doc.sparql.head != ""
        unless $.isArray doc.sparql.head.variable
            doc.sparql.head.variable = [doc.sparql.head.variable]
        i = 0
        varorder = []
        for variable in doc.sparql.head.variable
            resultSet.head.push variable._attributes.name
            varorder[variable._attributes.name] = i
            i++

        unless $.isArray doc.sparql.results.result
            doc.sparql.results.result = [doc.sparql.results.result]
        for result in doc.sparql.results.result
            presult = []
            if $.isArray result.binding
                bindings = result.binding
            else bindings = [result.binding]
            varbinding = []
            for bind in bindings
                value = ''
                if(bind?)
                    if 'uri' of bind
                        value = App.replacePrefixes(bind.uri, namespaces, colors)
                    else if 'literal' of bind
                        value = "\"" + _.escape(bind.literal) + "\""
                        if bind.literal._attributes and 'datatype' of bind.literal._attributes
                            value += "^^" + App.replacePrefixes(bind.literal._attributes.datatype, namespaces, colors)
                        if bind.literal._attributes and 'xml:lang' of bind.literal._attributes
                            value += "@" + bind.literal._attributes['xml:lang']
                    else if 'bnode' of bind
                        value = "_:" + bind.bnode
                    varbinding[bind._attributes.name] = value
            for varname,index of varorder
                if varbinding[varname]
                    presult[index] = varbinding[varname]
                else presult[index] = ''
            resultSet.results.push presult

    return resultSet

App.processTriples = (data, namespaces, colors) ->
    varnames = ['subject', 'predicate', 'object']
    resultSet =
        name: 'Triples'
        head: varnames
        results: []

    for triple in data
        result = []
        for variable in varnames
            App.processLiteral(triple[variable], namespaces, colors, result)
        resultSet.results.push result

    return resultSet

App.processPredicates = (preds, namespaces, colors) ->
    resultSet =
        name: 'Predicates'
        head: []
        results: []

    resultSet.head.push 'Predicate Name'

    if preds[0].parameters
        for v,k in preds[0].parameters
            resultSet.head.push "Arg. #{k + 1}"

    for pred in preds
        result = []
        result.push App.replacePrefixes(pred.predicateName.value, namespaces, colors)
        if pred.parameters
            for para in pred.parameters
                App.processLiteral(para, namespaces, colors, result)
        resultSet.results.push result

    return resultSet

App.processLiteral = (para, namespaces, colors, result) ->
    para.value = _.escape(para.value)
    if para.datatype
        prefixeddatatype = App.replacePrefixes(para.datatype, namespaces, colors)
        result.push "\"#{para.value}\"^^#{prefixeddatatype}"
    else if para.type and para.type == 'uri'
        result.push App.replacePrefixes(para.value, namespaces, colors)
    else if para.type and para.type == 'bnode'
        result.push "_:#{para.value}"
    else
        r = "\"#{para.value}\""
        if para['xml:lang']
            r = r + '@' + para['xml:lang']
        result.push r

App.replacePrefixes = (str, namespaces, colors) ->
    str = _.escape(str)
    for key, prefix of namespaces
        if str.indexOf(prefix) isnt -1
            localhtml = "<span class='sparql-prefix' style='color: "
            localhtml += colors[key]
            localhtml += "'>"
            localhtml += _.escape(key)
            localhtml += ":</span>"
            return str.replace prefix, localhtml

    base = App.baseName(str)
    return "&lt;" + str.replace(base, "<em>#{base}</em>") + "&gt;"

App.parseRDFPrefixes = (data) ->
    prefixes = {}
    # first detect base
    reg = /@prefix\s+:\s*<([^>]+)>\s*\./g
    while(m = reg.exec(data))
        prefixes[''] = m[1]
    # now detect prefixes
    reg = /@prefix\s+([A-z0-9-]+):\s*<([^>]+)>\s*\./g
    while(m = reg.exec(data))
        prefixes[m[1]] = m[2]
    prefixes

App.parseSPARQLPrefixes = (data) ->
    prefixes = {}
    # first detect base
    reg = /base\s+<([^>]+)>/ig
    while(m = reg.exec(data))
        prefixes[''] = m[1]
    # now detect prefixes
    reg = /prefix\s+([A-z0-9-]+)\s*:\s*<([^>]+)>/ig
    while(m = reg.exec(data))
        prefixes[m[1]] = m[2]
    prefixes

App.parseRIFPrefixes = (data) ->
    prefixes = {}
    # first detect base
    reg = /base\(\s*<([^>]+)>\s*\)/ig
    while(m = reg.exec(data))
        prefixes[''] = m[1]
    # now detect prefixes
    reg = /prefix\(([^\s]+)\s+<([^>]+)>\s*\)/ig
    while(m = reg.exec(data))
        prefixes[m[1]] = m[2]
    prefixes

App.logError = (msg, editor, line) ->
    if editor and line
        line--
        App.cm[editor].setSelection {line: (line), ch: 0}, {line: (line), ch: 80}
        $(".#{editor}-tab a").click()
    localhtml = "<h4><i class='fa fa-exclamation-triangle'></i> Error</h4><p>The Server responded with:</p><pre>"
    localhtml += _.escape(msg)
    localhtml += "</pre>"
    $('#results-tab').html localhtml

App.baseName = (str) ->
    base = new String(str).substring(str.lastIndexOf('/') + 1)
    base = base.substring(0, base.lastIndexOf('.'))  unless base.lastIndexOf('.') is -1
    base

App.configComponents =
    Radio: (watchedElementsSelector, defaultVal, callback) ->
        $(watchedElementsSelector).change ->
            callback($(watchedElementsSelector).filter(':checked').val())
            return true
        if defaultVal
            $(watchedElementsSelector).filter("[value=#{defaultVal}]").click()
    Check: (watchedElementSelector, defaultVal, callback) ->
        $(watchedElementSelector).click ->
            callback($(watchedElementSelector).is(':checked'))
            return true
        if defaultVal
            $(watchedElementSelector).click()

App.initConfigComponents = ->
    for endpoint in App.config.endpoints
        $("#endpoint_selector").append('<option value="' + endpoint.name + '">' + endpoint.name + '</option>');
    $('#endpoint_selector').trigger('change')
    for evaluator in App.config.evaluators
        $("#evaluator_selector").append('<option value="' + evaluator + '">' + evaluator + '</option>');
    for tones in App.operators.tones
        $("#tone_select").append('<option value)"' + tones + '">' + tones + '</option>');
    for tab in App.config.hide.tabs
        $("##{tab}-tab").hide().removeClass 'active'
        $("a[href=##{tab}-tab]").parent("dd").hide().removeClass 'active'
    # do not show getGraph button if graph tab is disabled...
    if 'graph' in App.config.hide.tabs
        $('#getGraph').hide()
    # no RIF inference radio button if rif tab is disabled
    if 'rif' in App.config.hide.tabs
        $('#rule_rif').hide()
        $('#rule_rif_label').hide()
    # Find first visible tab and focus it
    # delay is due to foundation initialization
    # TODO: use promise instead
    delay 1000, ->
        $('.tabs').each ->
            $(this).find('dd:visible a').first().click()

    if App.config.hide.sendRDF
        $('#send_rdf').hide()

    if App.config.hide.inference
        for radio in ["rdfs", "owl", "rif", "without"]
            actual = App.config['queryParameters']['inference']
            if actual.toLowerCase() isnt radio and not (actual == "OWL2RL" and radio == "owl")
                s = '#rule_' + radio
                $(s).hide()
                $(s + '_label').hide()

    App.showWithGraph()

    for tab in App.config.readOnlyTabs
# $("##{tab}-tab, a[href=##{tab}-tab]").addClass 'read-only'
# $("##{tab}-tab").find('input, textarea, select').attr('readonly', true)
        App.cm[tab].setOption("readOnly", true)

    App.configComponents.Radio '#rule_radios input', App.config['queryParameters']['inference'], (val) ->
        App.config['queryParameters']['inference'] = val
    App.configComponents.Check '#send_rdf', App.config.sendRDF, (send) ->
        App.config.sendRDF = send

delay = (ms, func) -> setTimeout func, ms

$(document).ready ->
    App.init()

textFile = null
makeTextFile = (text) ->
    data = new Blob([text], {type: 'text/plain'})
    # If we are replacing a previously generated file we need to
    # manually revoke the object URL to avoid memory leaks.
    if textFile != null
        window.URL.revokeObjectURL(textFile)
    textFile = window.URL.createObjectURL(data)
    return textFile
