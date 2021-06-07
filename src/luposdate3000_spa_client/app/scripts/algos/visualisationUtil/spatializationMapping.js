var spatOperator = [];
var spatOperatorDepth = [];
var spatOperatorType = [];
var spatOperatorVariable = [];
var spatDataVariable = [];
var spatDataIndex;

function spatializationSetup() {
    App.mappingFunctions.Spatialization = function(string) {
        App.config.sonification.Spatialization.mode = string
        switch (string) {
            case 'None':
                audioDimensionSetup(string, "Spatialization")
                break;
            case 'Simple':
                audioDimensionSetup(string, "Spatialization")
                break;
            case 'Operator-ID':
                audioDimensionSetup(string, "Spatialization")
                break;
            case 'Operator-Depth':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>"

                var configSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer: ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                    html += '<div nexus-ui=select id=spatOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0);
                }

                html += '</fieldset>';
                $('#spatializationSettings').html(html);

                if (!App.config.sonification.Spatialization.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Spatialization.OperatorDepth = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#spatOperatorDepth-' + i;
                    spatOperatorDepth.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step': 0.05,
                        'value': 0

                    }));
                    spatOperatorDepth[spatOperatorDepth.length - 1].value = App.config.sonification.Spatialization.OperatorDepth.value[spatOperatorDepth.length - 1];
                    spatOperatorDepth[spatOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Spatialization.OperatorDepth.value[spatOperatorDepth.length - 1] = spatOperatorDepth[spatOperatorDepth.length - 1].value
                    });
                }
                break;
            case 'Operator-Type':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();

                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                    html += '<div nexus-ui=select id=spatOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0);
                }

                html += '</fieldset>';
                $('#spatializationSettings').html(html);

                if (!App.config.sonification.Spatialization.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Spatialization.OperatorType = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#spatOperatorType-' + i;
                    spatOperatorType.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step': 0.05,
                        'value': 0

                    }));
                    spatOperatorType[spatOperatorType.length - 1].value = App.config.sonification.Spatialization.OperatorType.value[spatOperatorType.length - 1];
                    spatOperatorType[spatOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Spatialization.OperatorType.value[spatOperatorType.length - 1] = spatOperatorType[spatOperatorType.length - 1].value
                    });
                }
                break;
            case 'Operator-Variable':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;

                var configSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                    html += '<div nexus-ui=select id=spatOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0);
                }

                html += '</fieldset>';
                $('#spatializationSettings').html(html);

                if (!App.config.sonification.Spatialization.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Spatialization.OperatorVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#spatOperatorVariable-' + i;
                    spatOperatorVariable.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step': 0.05,
                        'value': 0

                    }));
                    spatOperatorVariable[spatOperatorVariable.length - 1].value = App.config.sonification.Spatialization.OperatorVariable.value[spatOperatorVariable.length - 1];
                    spatOperatorVariable[spatOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Spatialization.OperatorVariable.value[spatOperatorVariable.length - 1] = spatOperatorVariable[spatOperatorVariable.length - 1].value
                    });
                }
                break;
            case 'Data-Index':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();
                //Mapping Settings are not necessary -> Simple map ID to left/right channel

                var html = '<fieldset>';
                html += '<h7>Data index will be mapped to left and right speaker.</h7><br><br>';
                html += '</fieldset>';
                $('#spatializationSettings').html(html);

                break;
            case 'Data-Variable':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();
                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();

                var configSettings = [];
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                    html += '<div nexus-ui=select id=spatDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(0);
                }

                html += '</fieldset>';
                $('#spatializationSettings').html(html);

                if (!App.config.sonification.Spatialization.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Spatialization.DataVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#spatDataVariable-' + i;
                    spatDataVariable.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step': 0.05,
                        'value': 0

                    }));
                    spatDataVariable[spatDataVariable.length - 1].value = App.config.sonification.Spatialization.DataVariable.value[spatDataVariable.length - 1];
                    spatDataVariable[spatDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Spatialization.DataVariable.value[spatDataVariable.length - 1] = spatDataVariable[spatDataVariable.length - 1].value
                    });
                }
                break;
            case 'Query-Progress':
                $('#spatializationSettings').show();
                $('#spatializationSettings').empty();
                //No settings necessary. Insteadt just map progress to left-right channel

                var html = '<fieldset>';
                html += '<h7>Query progress will be mapped to left and right speaker.</h7><br><br>';
                html += '</fieldset>';
                $('#spatializationSettings').html(html);
                break;
        }
    }
}