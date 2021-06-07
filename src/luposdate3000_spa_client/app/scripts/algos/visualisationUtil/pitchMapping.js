var pitchOperator = [];
var pitchOperatorDepth = [];
var pitchOperatorType = [];
var pitchOperatorVariable = [];
var pitchDataVariable = [];
var pitchSimple;

function pitchSetup() {
    App.mappingFunctions.Pitch = function(string) {
        App.config.sonification.Pitch.mode = string
        switch (string) {
            case "None":
                audioDimensionSetup(string, "Pitch")
                break;
            case 'Simple':
                audioDimensionSetup(string, "Pitch")
                break;
            case 'Operator-ID':
                audioDimensionSetup(string, "Pitch")
                break;
            case 'Operator-Depth':
                $('#radioPitch').show();
                $('#pitchSettings').show();
                $('#pitchSettingsExplicit').empty();
                var html = '<hr>';

                calcDifferentPositions();

                var configSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('C');
                }
                pitchOperatorDepth = [];
                $('#pitchSettingsExplicit').html(html);

                if (!App.config.sonification.Pitch.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Pitch.OperatorDepth = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#pitchOperatorDepth-' + i;
                    pitchOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
                    pitchOperatorDepth[pitchOperatorDepth.length - 1].value = App.config.sonification.Pitch.OperatorDepth.value[pitchOperatorDepth.length - 1];
                    pitchOperatorDepth[pitchOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Pitch.OperatorDepth.value[pitchOperatorDepth.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Type':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchExplicit').prop('checked', true);
                $('#pitchDynamic').prop('checked', false);
                $('#pitchSettingsExplicit').show();
                $('#pitchSettingsExplicit').empty();
                var html = '';

                calcDifferentTypes();
                var i;

                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('C');
                }

                pitchOperatorType = [];
                $('#pitchSettingsExplicit').html(html);

                if (!App.config.sonification.Pitch.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Pitch.OperatorType = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#pitchOperatorType-' + i;
                    pitchOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
                    pitchOperatorType[pitchOperatorType.length - 1].value = App.config.sonification.Pitch.OperatorType.value[pitchOperatorType.length - 1];
                    pitchOperatorType[pitchOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Pitch.OperatorType.value[pitchOperatorType.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Variable':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchExplicit').prop('checked', true);
                $('#pitchDynamic').prop('checked', false);
                $('#pitchSettingsExplicit').show();
                $('#pitchSettingsExplicit').empty();
                var html = '';
                var i;
                calcDifferentOperatorVariables();

                var configSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('C');
                }

                pitchOperatorVariable = [];
                $('#pitchSettingsExplicit').html(html);

                if (!App.config.sonification.Pitch.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Pitch.OperatorVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#pitchOperatorVariable-' + i;
                    pitchOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
                    pitchOperatorVariable[pitchOperatorVariable.length - 1].value = App.config.sonification.Pitch.OperatorVariable.value[pitchOperatorVariable.length - 1];
                    pitchOperatorVariable[pitchOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Pitch.OperatorVariable.value[pitchOperatorVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Data-Index':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchExplicit').prop('checked', true);
                $('#pitchDynamic').prop('checked', false);
                $('#pitchSettingsExplicit').show();
                $('#pitchSettingsExplicit').empty();
                var html = '<h7>Data index will be mapped to the note.</h7>';
                $('#pitchSettingsExplicit').html(html);
                break;
            case 'Data-Variable':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchSettingsExplicit').empty();
                $('#pitchSettingsExplicit').show();
                var html = '';
                var i;
                differentDataVariables = [];
                var configSettings = [];
                for (i = 0; i <= globalAnimationList.length - 1; i++) {
                    if (!differentDataVariables.includes(globalAnimationList[i][2].split(" ")[0])) {
                        differentDataVariables.push(globalAnimationList[i][2].split(" ")[0]);
                        configSettings.push('C');
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                pitchDataVariable = [];
                $('#pitchSettingsExplicit').html(html);

                if (!App.config.sonification.Pitch.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Pitch.DataVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#pitchDataVariable-' + i;
                    pitchDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
                    pitchDataVariable[pitchDataVariable.length - 1].value = App.config.sonification.Pitch.DataVariable.value[pitchDataVariable.length - 1];
                    pitchDataVariable[pitchDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Pitch.DataVariable.value[pitchDataVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Query-Progress':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchExplicit').prop('checked', true);
                $('#pitchDynamic').prop('checked', false);
                $('#pitchSettingsExplicit').show();
                $('#pitchSettingsExplicit').empty();
                var html = '<h7>Query progress will be mapped to the note.</h7>';
                $('#pitchSettingsExplicit').html(html);
                break;
        }
    }
}