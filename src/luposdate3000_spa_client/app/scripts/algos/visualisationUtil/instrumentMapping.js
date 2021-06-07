var instrumentOperator = [];
var instrumentOperatorDepth = [];
var instrumentOperatorType = [];
var instrumentOperatorVariable = [];
var instrumentDataIndex;
var instrumentDataVariable = [];

function instrumentSetup() {
    App.mappingFunctions.Instrument = function(string) {
        App.config.sonification.Instrument.mode = string
        switch (string) {
            case 'Simple':
                if (!App.config.sonification.Instrument.hasOwnProperty("Simple")) {
                    App.config.sonification.Instrument.Simple = {
                        value: Object.keys(App.samples)[0]
                    }
                }
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = "<fieldset>";

                html += '<h7>Select a global setting.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                html += '<div nexus-ui=select id=instrumentDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentDataIndex = new Nexus.Select('#instrumentDataIndex', {
                    'size': [100, 40],
                    'options': App.operators.instruments
                });
                instrumentDataIndex.value = App.config.sonification.Instrument.Simple.value
                loadInstrument(instrumentDataIndex, false);
                instrumentDataIndex.on('change', function(v) {
                    App.config.sonification.Instrument.Simple.value = v.value
                });
                break;
            case 'Operator-ID':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = '<fieldset>';

                var j;
                var configSettings = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                        html += '<div nexus-ui=select id=instrumentOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                        configSettings.push(Object.keys(App.samples)[0]);
                    }
                }
                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                if (!App.config.sonification.Instrument.hasOwnProperty("OperatorID")) {
                    App.config.sonification.Instrument.OperatorID = {
                        value: configSettings
                    }
                }

                instrumentOperator = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#instrumentOperator-' + j;
                        instrumentOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.instruments
                        }));
                        instrumentOperator[instrumentOperator.length - 1].value = App.config.sonification.Instrument.OperatorID.value[instrumentOperator.length - 1];
                        instrumentOperator[instrumentOperator.length - 1].on('change', function(v) {
                            App.config.sonification.Instrument.OperatorID.value[instrumentOperator.length - 1] = v.value
                        });
                        loadInstrument(instrumentOperator, true);
                    }
                }
                break;
            case 'Operator-Depth':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();

                calcDifferentPositions();
                var configSettings = [];
                var html = "<fieldset>";
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.samples)[0]);
                }
                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                if (!App.config.sonification.Instrument.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Instrument.OperatorDepth = {
                        value: configSettings
                    }
                }

                instrumentOperatorDepth = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#instrumentOperatorDepth-' + i;
                    instrumentOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    loadInstrument(instrumentOperatorDepth, true);
                    instrumentOperatorDepth[instrumentOperatorDepth.length - 1].value = App.config.sonification.Instrument.OperatorDepth.value[instrumentOperatorDepth.length - 1];
                    instrumentOperatorDepth[instrumentOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Instrument.OperatorDepth.value[instrumentOperatorDepth.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Type':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();
                instrumentOperatorType = [];
                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.samples)[0]);
                }

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                if (!App.config.sonification.Instrument.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Instrument.OperatorType = {
                        value: configSettings
                    }
                }

                instrumentOperatorType = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#instrumentOperatorType-' + i;
                    instrumentOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    instrumentOperatorType[instrumentOperatorType.length - 1].value = App.config.sonification.Instrument.OperatorType.value[instrumentOperatorType.length - 1];
                    instrumentOperatorType[instrumentOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Instrument.OperatorType.value[instrumentOperatorType.length - 1] = v.value
                    });
                    loadInstrument(instrumentOperatorType, true);
                }
                break;
            case 'Operator-Variable':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;
                var configSettings = [];

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.samples)[0]);
                }

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                if (!App.config.sonification.Instrument.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Instrument.OperatorVariable = {
                        value: configSettings
                    }
                }

                instrumentOperatorVariable = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#instrumentOperatorVariable-' + i;
                    instrumentOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    instrumentOperatorVariable[instrumentOperatorVariable.length - 1].value = App.config.sonification.Instrument.OperatorVariable.value[instrumentOperatorVariable.length - 1];
                    instrumentOperatorVariable[instrumentOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Instrument.OperatorVariable.value[instrumentOperatorVariable.length - 1] = v.value
                    });
                    loadInstrument(instrumentOperatorVariable, true);
                }
                break;
            case 'Data-Index':
                if (!App.config.sonification.Instrument.hasOwnProperty("DataIndex")) {
                    App.config.sonification.Instrument.DataIndex = {
                        value: Object.keys(App.samples)[0]
                    }
                }
                //This setting does not make much sense.
                //Instead, simple selection will be used.
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = "<fieldset>";

                html += '<h7>Mapping the data index to an instrument is not available. Instead choose an global instrument</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                html += '<div nexus-ui=select id=instrumentDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#instrumentSettings').html(html);
                instrumentDataIndex = new Nexus.Select('#instrumentDataIndex', {
                    'size': [100, 40],
                    'options': App.operators.instruments
                });
                instrumentDataIndex.value = App.config.sonification.Instrument.DataIndex.value
                loadInstrument(instrumentDataIndex, false);
                instrumentDataIndex.on('change', function(v) {
                    App.config.sonification.Instrument.DataIndex.value = v.value
                });
                break;
            case 'Data-Variable':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();
                var configSettings = [];
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.samples)[0]);
                }

                html += '</fieldset>';

                if (!App.config.sonification.Instrument.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Instrument.DataVariable = {
                        value: configSettings
                    }
                }

                instrumentDataVariable = [];
                $('#instrumentSettings').html(html);
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#instrumentDataVariable-' + i;
                    instrumentDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    instrumentDataVariable[instrumentDataVariable.length - 1].value = App.config.sonification.Instrument.DataVariable.value[instrumentDataVariable.length - 1];
                    instrumentDataVariable[instrumentDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Instrument.DataVariable.value[instrumentDataVariable.length - 1] = v.value
                    });
                    loadInstrument(instrumentDataVariable, true);
                }
                break;
            case 'Query-Progress':
                if (!App.config.sonification.Instrument.hasOwnProperty("QueryProgress")) {
                    App.config.sonification.Instrument.QueryProgress = {
                        value: Object.keys(App.samples)[0]
                    }
                }
                //This setting does not make much sense.
                //Instead, simple selection will be used.
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = "<fieldset>";

                html += '<h7>Mapping the query progress to an instrument is not available. Instead choose an global instrument</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                html += '<div nexus-ui=select id=instrumentDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentDataIndex = new Nexus.Select('#instrumentDataIndex', {
                    'size': [100, 40],
                    'options': App.operators.instruments
                });
                instrumentDataIndex.value = App.config.sonification.Instrument.QueryProgress.value
                loadInstrument(instrumentDataIndex, false);
                instrumentDataIndex.on('change', function(v) {
                    App.config.sonification.Instrument.QueryProgress.value = v.value
                });
                break;
            default:
                $('#instrumentSettings').hide();
                break;
        }
    }
}

function loadInstrument(array, isArray) {
    var object;
    if (isArray) {
        object = array[array.length - 1];
    } else {
        object = array;
    }
    object.on('change', function(v) {
        if (!usedInstruments.includes(v.value)) {
            usedInstruments.push(v.value);
            App.samples = SampleLibrary.load({
                instruments: usedInstruments,
                baseUrl: "./resources/samples/"
            });
            // loop through instruments and set release, connect to master output
            App.samples[v.value].release = .5;
            App.samples[v.value].toDestination();
        }
    });
}