var octaveOperator = [];
var octaveOperatorDepth = [];
var octaveOperatorType = [];
var octaveOperatorVariable = [];
var octaveDataIndex;
var octaveDataVariable = [];

function getOctave(string, id, label, index) {
    switch (string) {
        case 'Simple':
            return octaveDataIndex.value;
            break;
        case 'Operator-ID':
            var i;
            var j = 0;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = octaveOperator[j].value;
                        return t;
                    }
                    j++;
                }
            }
            break;
        case 'Operator-Depth':
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id == id) {
                    for (j = 0; j <= differentPositions.length - 1; j++) {
                        if (Object.values(networkSon.getPositions(id))[0].y == parseInt(differentPositions[j], 10)) {
                            var t = octaveOperatorDepth[j].value;
                            return t;
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
                            return octaveOperatorType[j].value;
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
                            return octaveOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
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

            var durations = [1, 2, 3, 4, 5, 6, 7];
            var tmp2 = scale(index, min, max, 1, 7);
            var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
            return '' + output;
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            return octaveDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var durations = [1, 2, 3, 4, 5, 6, 7];
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            var tmp2 = scale(tmp, 0, 100, 1, 7);
            var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
            return '' + output;
            break;
    }
}

function octaveSetup(){
App.mappingFunctions.Octave=function(string){
App.config.sonification.Octave.mode=string
    switch (string) {
        case 'None':
            $('#octaveSettings').hide();
            break;
        case 'Simple':
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
            break;
        case 'Operator-ID':
            $('#octaveSettings').show();
            $('#octaveSettings').empty();
            var html = '<fieldset>';
            var j;
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                    html += '<div nexus-ui=select id=octaveOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }
            }
            html += '</fieldset>';

            octaveOperator = [];
            $('#octaveSettings').html(html);
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#octaveOperator-' + j;
                    octaveOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.octave

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#octaveSettings').show();
            $('#octaveSettings').empty();

            calcDifferentPositions();
            var html = "<fieldset>";
            for (i = 0; i <= differentPositions.length - 1; i++) {
                html += '<h7>Layer ' + i + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                html += '<div nexus-ui=select id=octaveOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }
            html += '</fieldset>';

            octaveOperatorDepth = [];
            $('#octaveSettings').html(html);
            for (i = 0; i <= differentPositions.length - 1; i++) {
                var string = '#octaveOperatorDepth-' + i;
                octaveOperatorDepth.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Operator-Type':
            $('#octaveSettings').show();
            $('#octavesSettings').empty();
            var html = '<fieldset>';
            calcDifferentTypes();

            for (i = 0; i <= differentTypes.length - 1; i++) {
                html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                html += '<div nexus-ui=select id=octaveOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';

            octaveOperatorType = [];
            $('#octaveSettings').html(html);
            for (i = 0; i <= differentTypes.length - 1; i++) {
                var string = '#octaveOperatorType-' + i;
                octaveOperatorType.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Operator-Variable':
            $('#octaveSettings').show();
            $('#octaveSettings').empty();
            var html = '<fieldset>';

            calcDifferentOperatorVariables();

            var i;

            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                html += '<div nexus-ui=select id=octaveOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';

            octaveOperatorVariable = [];
            $('#octaveSettings').html(html);
            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                var string = '#octaveOperatorVariable-' + i;
                octaveOperatorVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.octave
                }));
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

            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Octave: </p>';
                html += '<div nexus-ui=select id=octaveDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
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
