var pitchOperator = [];
var pitchOperatorDepth = [];
var pitchOperatorType = [];
var pitchOperatorVariable = [];
var pitchDataVariable = [];
var pitchSimple;

function getPitch(string, id, label, index) {
    switch (string) {
        case 'Simple':
            return pitchSimple.value;
            break;
        case "Operator-ID":
            var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(), 10);

            if (pitchMode == 0) {
                //Find Max & Min ID
                var max = 0;
                var min = 9999999999;
                var i;
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    var k = parseInt(dataNodes[i].label.split(" ")[1].split("\n")[0], 10);
                    if (k > max) {
                        max = k;
                    }
                    if (k < min) {
                        min = k;
                    }
                }
                return scale(parseInt(label.split(" ")[1].split("\n")[0], 10), min, max, 30, 800);
                break;
            } else {
                var i;
                var j = 0;
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                        if (dataNodes[i].id == id) {
                            return '' + pitchOperator[j].value;
                        }
                        j++;
                    }
                }
            }
            break;
        case 'Operator-Depth':
            var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(), 10);

            if (pitchMode == 0) {
                //Find Max & Min Depth
                var max = -999999999;
                var min = 999999999;
                var i;
                var positions = Object.values(networkSon.getPositions());
                for (i = 0; i <= positions.length - 1; i++) {
                    if (positions[i].y > max) {
                        max = positions[i].y;
                    }
                    if (positions[i].y < min) {
                        min = positions[i].y;
                    }
                }
                return scale(networkSon.getPosition(id).y, min, max, 30, 800);
                break;
            } else {
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (dataNodes[i].id == id) {
                        for (j = 0; j <= differentPositions.length - 1; j++) {
                            if (Object.values(networkSon.getPositions(id))[0].y == parseInt(differentPositions[j], 10)) {
                                return '' + pitchOperatorDepth[j].value;
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Type':
            var i, j;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id == id) {
                    for (j = 0; j <= differentTypes.length; j++) {
                        if (dataNodes[i].label.split(" ")[0] == differentTypes[j]) {
                            return '' + pitchOperatorType[j].value;
                        }
                    }
                }
            }
            break;
        case 'Operator-Variable':
            var i, j;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id == id) {
                    for (j = 0; j <= differentOperatorVariables.length; j++) {
                        if (dataNodes[i].label.split("\n")[1] == differentOperatorVariables[j]) {
                            return '' + pitchOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            //Find Max & Min ID
            var max = 0;
            var min = 999999999999999;
            var i;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                var k = parseInt(globalAnimationList[i][3], 10);
                if (k > max) {
                    max = k;
                }
                if (k < min) {
                    min = k;
                }
            }
            return scale(index, min, max, 30, 800);
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            return '' + pitchDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            return scale(tmp, 0, 100, 30, 800);
            break;
    }
}

function pitchSetup() {
    App.mappingFunctions.Pitch = function(string) {
        App.config.sonification.Pitch.mode = string
        switch (string) {
            case "None":
                $('#pitchSettings').hide();
                $('#pitchSettingsExplicit').hide();
                $('#radioPitch').hide();
                break;
            case 'Simple':
                $('#radioPitch').hide();
                $('#pitchSettings').show();
                $('#pitchSettingsExplicit').show();
                $('#pitchSettingsExplicit').empty();

                var html = '';
                html += '<h7>Select a global setting.</h7><br><br>';
                html += '<div style=overflow:hidden;>'
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                html += '<div nexus-ui=select id=pitchSimple style=float:left;margin-right:10px;></div>';
                html += '</div>';

                $('#pitchSettingsExplicit').html(html);

                pitchSimple = new Nexus.Select('#pitchSimple', {
                    'size': [100, 40],
                    'options': App.operators.frequence
                });
                break;
            case 'Operator-ID':
                $('#radioPitch').show();
                $('#pitchSettings').show();
                $('#pitchSettingsExplicit').empty();
                var html = '<hr>';
                var i;
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[i].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                        html += '<div nexus-ui=select id=pitchOperator-' + i + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                    }
                }
                pitchOperator = [];
                $('#pitchSettingsExplicit').html(html);
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                        var string = '#pitchOperator-' + i;
                        pitchOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.frequence

                        }));
                    }
                }
                break;
            case 'Operator-Depth':
                $('#radioPitch').show();
                $('#pitchSettings').show();
                $('#pitchSettingsExplicit').empty();
                var html = '<hr>';

                calcDifferentPositions();

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }
                pitchOperatorDepth = [];
                $('#pitchSettingsExplicit').html(html);
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#pitchOperatorDepth-' + i;
                    pitchOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
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

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                pitchOperatorType = [];
                $('#pitchSettingsExplicit').html(html);
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#pitchOperatorType-' + i;
                    pitchOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
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

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>'
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=pitchOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                pitchOperatorVariable = [];
                $('#pitchSettingsExplicit').html(html);
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#pitchOperatorVariable-' + i;
                    pitchOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
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
                for (i = 0; i <= globalAnimationList.length - 1; i++) {
                    if (!differentDataVariables.includes(globalAnimationList[i][2].split(" ")[0])) {
                        differentDataVariables.push(globalAnimationList[i][2].split(" ")[0]);
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
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#pitchDataVariable-' + i;
                    pitchDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence
                    }));
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