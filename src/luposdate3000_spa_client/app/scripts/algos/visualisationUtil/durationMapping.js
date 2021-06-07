var durationOperator = [];
var durationOperatorDepth = [];
var durationOperatorType = [];
var durationOperatorVariable = [];
var durationDataIndex;
var durationDataVariable = [];

function durationSetup() {
    App.mappingFunctions.Duration = function(string) {
        App.config.sonification.Duration.mode = string
        switch (string) {
            case "None":
                $('#durationSettings').hide();
                break;
            case 'Simple':
                if (!App.config.sonification.Duration.hasOwnProperty("Simple")) {
                    App.config.sonification.Duration.Simple = {
                        value: '4n'
                    }
                }

                $('#durationSettings').show();
                $('#durationSettings').empty();

                var html = '<fieldset>';
                html += '<h7>Select a global setting.</h7><br><br>';
                html += '<div style=overflow:hidden;>'
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                html += '<div nexus-ui=select id=durationDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

                html += '</fieldset>';
                $('#durationSettings').html(html);

                durationDataIndex = new Nexus.Select('#durationDataIndex', {
                    'size': [100, 40],
                    'options': App.operators.tones
                });
                durationDataIndex.value = App.config.sonification.Duration.Simple.value
                durationDataIndex.on('change', function(v) {
                    App.config.sonification.Duration.Simple.value = v.value
                });
                break;
            case 'Operator-ID':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';

                var j;
                var configSettings = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                        html += '<div nexus-ui=select id=durationOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                        configSettings.push('4n');
                    }
                }
                html += '</fieldset>';

                durationOperator = [];
                $('#durationSettings').html(html);

                if (!App.config.sonification.Duration.hasOwnProperty("OperatorID")) {
                    App.config.sonification.Duration.OperatorID = {
                        value: configSettings
                    }
                }

                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#durationOperator-' + j;

                        durationOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.tones

                        }));
                        durationOperator[durationOperator.length - 1].value = App.config.sonification.Duration.OperatorID.value[durationOperator.length - 1];
                        durationOperator[durationOperator.length - 1].on('change', function(v) {
                            App.config.sonification.Duration.OperatorID.value[durationOperator.length - 1] = v.value
                        });
                    }
                }
                break;
            case 'Operator-Depth':
                $('#durationSettings').show();
                $('#durationSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>";

                var configSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer: ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('4n');
                }

                html += "</fieldset>";

                durationOperatorDepth = [];
                $('#durationSettings').html(html);

                if (!App.config.sonification.Duration.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Duration.OperatorDepth = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#durationOperatorDepth-' + i;
                    durationOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                    durationOperatorDepth[durationOperatorDepth.length - 1].value = App.config.sonification.Duration.OperatorDepth.value[durationOperatorDepth.length - 1];
                    durationOperatorDepth[durationOperatorDepth.length - 1].on('change', function(v) {
                        App.config.sonification.Duration.OperatorDepth.value[durationOperatorDepth.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Type':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();

                var configSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('4n');
                }

                html += '</fieldset>';

                durationOperatorType = [];
                $('#durationSettings').html(html);

                if (!App.config.sonification.Duration.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Duration.OperatorType = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#durationOperatorType-' + i;
                    durationOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                    durationOperatorType[durationOperatorType.length - 1].value = App.config.sonification.Duration.OperatorType.value[durationOperatorType.length - 1];
                    durationOperatorType[durationOperatorType.length - 1].on('change', function(v) {
                        App.config.sonification.Duration.OperatorType.value[durationOperatorType.length - 1] = v.value
                    });
                }
                break;
            case 'Operator-Variable':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;
                var configSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('4n');
                }

                html += '</fieldset>';

                durationOperatorType = [];
                $('#durationSettings').html(html);

                if (!App.config.sonification.Duration.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Duration.OperatorVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#durationOperatorVariable-' + i;
                    durationOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                    durationOperatorVariable[durationOperatorVariable.length - 1].value = App.config.sonification.Duration.OperatorVariable.value[durationOperatorVariable.length - 1];
                    durationOperatorVariable[durationOperatorVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Duration.OperatorVariable.value[durationOperatorVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Data-Index':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = "<fieldset>";
                html += '<h7>Query progress will be mapped to note duration.</h7>';
                html += '</fieldset>';
                $('#durationSettings').html(html);
                break;
            case 'Data-Variable':
                $('#durationSettings').show();
                $('#durationSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();

                var configSettings = [];
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push('4n');
                }

                html += '</fieldset>';

                durationDataIndex = [];
                $('#durationSettings').html(html);

                if (!App.config.sonification.Duration.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Duration.DataVariable = {
                        value: configSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#durationDataVariable-' + i;
                    durationDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                    durationDataVariable[durationDataVariable.length - 1].value = App.config.sonification.Duration.DataVariable.value[durationDataVariable.length - 1];
                    durationDataVariable[durationDataVariable.length - 1].on('change', function(v) {
                        App.config.sonification.Duration.DataVariable.value[durationDataVariable.length - 1] = v.value
                    });
                }
                break;
            case 'Query-Progress':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = "<fieldset>";
                html += '<h7>Query progress will be mapped to note duration.</h7>';
                html += '</fieldset>';
                $('#durationSettings').html(html);
                break;
        }
    }
}