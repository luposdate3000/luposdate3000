var octaveOperator = [];
var octaveOperatorDepth = [];
var octaveOperatorType = [];
var octaveOperatorVariable = [];
var octaveDataIndex;
var octaveDataVariable = [];

function octaveSetup() {
    App.mappingFunctions.Octave = function(string) {
        App.config.sonification.Octave.mode = string
        switch (string) {
            case 'None':
                $('#octaveSettings').hide();
                break;
            case 'Simple':
                if (!App.config.sonification.Octave.hasOwnProperty("Simple")) {
                    App.config.sonification.Octave.Simple = {
                        value: Object.keys(App.operators.octave)[0]
                    }
                }
                $('#octaveSettings').show();
                $('#octaveSettings').empty();

                var html = '<fieldset>';

                html += '<h7>Select a global setting.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                html += '<div nexus-ui=select id=octaveDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#octaveSettings').html(html);
                octaveDataIndex = new Nexus.Select('#octaveDataIndex', {
                    'size': [100, 40],
                    'options': App.operators.octave
                });
                octaveDataIndex.value = App.config.sonification.Octave.Simple.value
                octaveDataIndex.on('change', function(v) {
                    App.config.sonification.Octave.Simple.value = v.value
                });
                break;
            case 'Operator-ID':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();
                var html = '<fieldset>';
                var j;
                var configSettings = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                        html += '<div nexus-ui=select id=octaveOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                        configSettings.push(Object.keys(App.operators.octave)[0]);
                    }
                }
                html += '</fieldset>';

                octaveOperator = [];
                $('#octaveSettings').html(html);

                if (!App.config.sonification.Octave.hasOwnProperty("OperatorID")) {
                    App.config.sonification.Octave.OperatorID = {
                        value: configSettings
                    }
                }

                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#octaveOperator-' + j;
                        octaveOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.octave

                        }));
                        octaveOperator[octaveOperator.length - 1].value = App.config.sonification.Octave.OperatorID.value[octaveOperator.length - 1];
                        octaveOperator[octaveOperator.length - 1].on('change', function(v) {
                            App.config.sonification.Octave.OperatorID.value[octaveOperator.length - 1] = v.value
                        });
                    }
                }
                break;
            case 'Operator-Depth':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>";
                var configSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                    html += '<div nexus-ui=select id=octaveOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.operators.octave)[0]);
                }
                html += '</fieldset>';

                octaveOperatorDepth = [];
                $('#octaveSettings').html(html);

                if (!App.config.sonification.Octave.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Octave.OperatorDepth = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#octaveOperatorDepth-' + i;
                    octaveOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.octave
                    }));
                    octaveOperatorDepth[octaveOperatorDepth.length - 1].value = App.config.sonification.Octave.OperatorDepth.value[octaveOperatorDepth.length - 1];
                    octaveOperatorDepth[octaveOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Octave.OperatorDepth.value[octaveOperatorDepth.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Type':
                $('#octaveSettings').show();
                $('#octavesSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();

                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                    html += '<div nexus-ui=select id=octaveOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.operators.octave)[0]);
                }

                html += '</fieldset>';

                octaveOperatorType = [];
                $('#octaveSettings').html(html);

                if (!App.config.sonification.Octave.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Octave.OperatorType = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#octaveOperatorType-' + i;
                    octaveOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.octave
                    }));
                    octaveOperatorType[octaveOperatorType.length - 1].value = App.config.sonification.Octave.OperatorType.value[octaveOperatorType.length - 1];
                    octaveOperatorType[octaveOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Octave.OperatorType.value[octaveOperatorType.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Variable':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;

                var configSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                    html += '<div nexus-ui=select id=octaveOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.operators.octave)[0]);
                }

                html += '</fieldset>';

                octaveOperatorVariable = [];
                $('#octaveSettings').html(html);

                if (!App.config.sonification.Octave.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Octave.OperatorVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#octaveOperatorVariable-' + i;
                    octaveOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.octave
                    }));
                    octaveOperatorVariable[octaveOperatorVariable.length - 1].value = App.config.sonification.Octave.OperatorVariable.value[octaveOperatorVariable.length - 1];
                    octaveOperatorVariable[octaveOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Octave.OperatorVariable.value[octaveOperatorVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Data-Index':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();
                var html = "<fieldset>";
                html += '<h7>Data index will be mapped to the octave.</h7>';
                html += '</fieldset>';
                $('#octaveSettings').html(html);
                break;
            case 'Data-Variable':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();
                var configSettings = [];

                if (!App.config.sonification.Octave.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Octave.DataVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                    html += '<div nexus-ui=select id=octaveDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(Object.keys(App.operators.octave)[0]);
                }

                html += '</fieldset>';

                octaveDataVariable = [];
                $('#octaveSettings').html(html);
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#octaveDataVariable-' + i;
                    octaveDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.octave
                    }));
                    octaveDataVariable[octaveDataVariable.length - 1].value = App.config.sonification.Octave.DataVariable.value[octaveDataVariable.length - 1];
                    octaveDataVariable[octaveDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Octave.DataVariable.value[octaveDataVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Query-Progress':
                $('#octaveSettings').show();
                $('#octaveSettings').empty();
                var html = "<fieldset>";
                html += '<h7>Query progress will be mapped to the octave.</h7>';
                html += '</fieldset>';
                $('#octaveSettings').html(html);
                break;
        }
    }
}