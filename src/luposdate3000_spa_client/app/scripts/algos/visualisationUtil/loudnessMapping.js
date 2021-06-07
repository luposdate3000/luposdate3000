var loudnessOperator = [];
var loudnessOperatorDepth = [];
var loudnessOperatorType = [];
var loudnessOperatorVariable = [];
var loudnessDataVariable = [];
var loudnessDataIndex;

function loudnessSetup() {
    App.mappingFunctions.Loudness = function(string) {
        App.config.sonification.Loudness.mode = string
        //Show Slider with min&max Loudness
        switch (string) {
            case "None":
                audioDimensionSetup(string, "Loudness")
                break;
            case 'Simple':
                audioDimensionSetup(string, "Loudness")
                break;
            case 'Operator-ID':
                audioDimensionSetup(string, "Loudness")
                break;
            case 'Operator-Depth':
                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>"
                var configSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer: ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                    html += '<div nexus-ui=select id=loudnessOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0.5);
                }

                html += '</fieldset>';

                loudnessOperatorDepth = [];
                $('#loudnessSettings').html(html);

                if (!App.config.sonification.Loudness.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Loudness.OperatorDepth = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#loudnessOperatorDepth-' + i;
                    loudnessOperatorDepth.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': 0,
                        'max': 1,
                        'step': 0.05,
                        'value': 0.5
                    }));
                    loudnessOperatorDepth[loudnessOperatorDepth.length - 1].value = App.config.sonification.Loudness.OperatorDepth.value[loudnessOperatorDepth.length - 1];
                    loudnessOperatorDepth[loudnessOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Loudness.OperatorDepth.value[loudnessOperatorDepth.length - 1] = loudnessOperatorDepth[loudnessOperatorDepth.length - 1].value
                    });
                }
                break;
            case 'Operator-Type':
                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();

                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                    html += '<div nexus-ui=select id=loudnessOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0.5);
                }

                html += '</fieldset>';

                loudnessOperatorType = [];
                $('#loudnessSettings').html(html);

                if (!App.config.sonification.Loudness.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Loudness.OperatorType = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#loudnessOperatorType-' + i;
                    loudnessOperatorType.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': 0,
                        'max': 1,
                        'step': 0.05,
                        'value': 0.5
                    }));
                    loudnessOperatorType[loudnessOperatorType.length - 1].value = App.config.sonification.Loudness.OperatorType.value[loudnessOperatorType.length - 1];
                    loudnessOperatorType[loudnessOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Loudness.OperatorType.value[loudnessOperatorType.length - 1] = loudnessOperatorType[loudnessOperatorType.length - 1].value
                    });
                }
                break;
            case 'Operator-Variable':
                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;

                var configSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                    html += '<div nexus-ui=select id=loudnessOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0.5);
                }

                html += '</fieldset>';

                loudnessOperatorVariable = [];
                $('#loudnessSettings').html(html);

                if (!App.config.sonification.Loudness.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Loudness.OperatorVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#loudnessOperatorVariable-' + i;
                    loudnessOperatorVariable.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': 0,
                        'max': 1,
                        'step': 0.05,
                        'value': 0.5
                    }));
                    loudnessOperatorVariable[loudnessOperatorVariable.length - 1].value = App.config.sonification.Loudness.OperatorVariable.value[loudnessOperatorVariable.length - 1];
                    loudnessOperatorVariable[loudnessOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Loudness.OperatorVariable.value[loudnessOperatorVariable.length - 1] = loudnessOperatorVariable[loudnessOperatorVariable.length - 1].value
                    });
                }
                break;
            case 'Data-Index':
                if (!App.config.sonification.Loudness.hasOwnProperty("DataIndex")) {
                    App.config.sonification.Loudness.DataIndex = {
                        value: 0.5
                    }
                }

                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();

                var html = '<fieldset>';
                html += '<h7>Please choose maximum loudness.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#loudnessSettings').html(html);
                loudnessDataIndex = new Nexus.Slider('#loudnessDataIndex', {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step': 0.05,
                    'value': 0.5
                });
                loudnessDataIndex.value = App.config.sonification.Loudness.DataIndex.value
                loudnessDataIndex.on('change', function(v) {
                    App.config.sonification.Loudness.DataIndex.value = loudnessDataIndex.value
                });

                break;
            case 'Data-Variable':
                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();

                var configSettings = [];
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                    html += '<div nexus-ui=select id=loudnessDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0.5);
                }

                html += '</fieldset>';

                loudnessDataVariable = [];
                $('#loudnessSettings').html(html);

                if (!App.config.sonification.Loudness.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Loudness.DataVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#loudnessDataVariable-' + i;
                    loudnessDataVariable.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': 0,
                        'max': 1,
                        'step': 0.05,
                        'value': 0.5
                    }));

                    loudnessDataVariable[loudnessDataVariable.length - 1].value = App.config.sonification.Loudness.DataVariable.value[loudnessDataVariable.length - 1];
                    loudnessDataVariable[loudnessDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Loudness.DataVariable.value[loudnessDataVariable.length - 1] = loudnessDataVariable[loudnessDataVariable.length - 1].value
                    });
                }
                break;
            case 'Query-Progress':
                if (!App.config.sonification.Loudness.hasOwnProperty("QueryProgress")) {
                    App.config.sonification.Loudness.QueryProgress = {
                        value: 0.5
                    }
                }

                $('#loudnessSettings').show();
                $('#loudnessSettings').empty();

                var html = '<fieldset>';
                html += '<h7>Please choose maximum loudness.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#loudnessSettings').html(html);
                loudnessDataIndex = new Nexus.Slider('#loudnessDataIndex', {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step': 0.05,
                    'value': 0.5
                });
                loudnessDataIndex.value = App.config.sonification.Loudness.QueryProgress.value
                loudnessDataIndex.on('change', function(v) {
                    App.config.sonification.Loudness.QueryProgress.value = loudnessDataIndex.value
                });
                break;
        }
    }
}