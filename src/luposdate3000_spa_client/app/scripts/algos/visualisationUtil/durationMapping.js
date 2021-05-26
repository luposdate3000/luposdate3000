var durationOperator = [];
var durationOperatorDepth = [];
var durationOperatorType = [];
var durationOperatorVariable = [];
var durationDataIndex;
var durationDataVariable = [];

function getDuration(string, id, label, index) {
    switch (string) {
        case 'Simple':
            return durationDataIndex.value;
            break;
        case 'Operator-ID':
            var i;
            var j = 0;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = durationOperator[j].value;
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
                            return durationOperatorDepth[j].value;
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
                            return durationOperatorType[j].value;
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
                            return durationOperatorVariable[j].value;
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
            var durations = [1, 2, 4, 8, 16];
            var tmp2 = scale(index, min, max, 1, 16);
            var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
            return output + 'n';
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            return durationDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var durations = [1, 2, 4, 8, 16];
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            var tmp2 = scale(tmp, 0, 100, 1, 16);
            var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
            return output + 'n';
            break;
    }
}

function durationSetup() {
    App.mappingFunctions.Duration = function(string) {
        App.config.sonification.Duration.mode = string
        switch (string) {
            case "None":
                $('#durationSettings').hide();
                break;
            case 'Simple':
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
                break;
            case 'Operator-ID':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';

                var j;
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                        html += '<div nexus-ui=select id=durationOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                    }
                }
                html += '</fieldset>';

                durationOperator = [];
                $('#durationSettings').html(html);
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#durationOperator-' + j;

                        durationOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.tones

                        }));
                    }
                }
                break;
            case 'Operator-Depth':
                $('#durationSettings').show();
                $('#durationSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>";

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer: ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += "</fieldset>";

                durationOperatorDepth = [];
                $('#durationSettings').html(html);
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#durationOperatorDepth-' + i;
                    durationOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                }
                break;
            case 'Operator-Type':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';

                durationOperatorType = [];
                $('#durationSettings').html(html);
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#durationOperatorType-' + i;
                    durationOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
                }
                break;
            case 'Operator-Variable':
                $('#durationSettings').show();
                $('#durationSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';

                durationOperatorType = [];
                $('#durationSettings').html(html);
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#durationOperatorVariable-' + i;
                    durationOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
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

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Duration: </p>';
                    html += '<div nexus-ui=select id=durationDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';

                durationDataIndex = [];
                $('#durationSettings').html(html);
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#durationDataVariable-' + i;
                    durationDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones
                    }));
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