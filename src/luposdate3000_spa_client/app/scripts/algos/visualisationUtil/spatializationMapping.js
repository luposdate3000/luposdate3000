var spatOperator = [];
var spatOperatorDepth = [];
var spatOperatorType = [];
var spatOperatorVariable = [];
var spatDataVariable = [];
var spatDataIndex;

function getSpatialization(string, id, label, index) {
    switch (string) {
        case 'Simple':
            return spatDataIndex.value;
            break;
        case 'Operator-ID':
            var i;
            var j = 0;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = spatOperator[j].value;
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
                            var t = spatOperatorDepth[j].value;
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
                            return spatOperatorType[j].value;
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
                            return spatOperatorVariable[j].value;
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
            return scale(index, min, max, -1, 1);
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            return spatDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            return scale(tmp, 0, 100, -1, 1);
            break;
    }
}

function spatializationSetup(){
App.mappingFunctions.Spatialization=function(string){
App.config.sonification.Spatialization.mode=string
    switch (string) {
        case 'None':
            $('#spatializationSettings').hide();
            break;
        case 'Simple':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = '<fieldset>';

            html += '<h7>Select a global setting.</h7><br><br>';
            html += '<div style=overflow:hidden;>';
            html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
            html += '<div nexus-ui=select id=spatDataIndex style=float:left;margin-right:10px;></div>';
            html += '</div>';

            html += '</fieldset>';
            $('#spatializationSettings').html(html);
            spatDataIndex = new Nexus.Slider('#spatDataIndex', {
                'size': [120, 40],
                'mode': 'relative',
                'min': -1,
                'max': 1,
                'step': 0.05,
                'value': 0
            });
            break;
        case 'Operator-ID':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = '<fieldset>';
            var j;
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                    html += '<div nexus-ui=select id=spatOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }
            }

            html += '</fieldset';
            $('#spatializationSettings').html(html);
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#spatOperator-' + j;
                    spatOperator.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step': 0.05,
                        'value': 0

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();

            calcDifferentPositions();
            var html = "<fieldset>"

            for (i = 0; i <= differentPositions.length - 1; i++) {
                html += '<h7>Layer: ' + i + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                html += '<div nexus-ui=select id=spatOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';
            $('#spatializationSettings').html(html);
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
            }
            break;
        case 'Operator-Type':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = '<fieldset>';
            calcDifferentTypes();

            for (i = 0; i <= differentTypes.length - 1; i++) {
                html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                html += '<div nexus-ui=select id=spatOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';
            $('#spatializationSettings').html(html);
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
            }
            break;
        case 'Operator-Variable':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = '<fieldset>';

            calcDifferentOperatorVariables();

            var i;

            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                html += '<div nexus-ui=select id=spatOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';
            $('#spatializationSettings').html(html);
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

            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Spatialization: </p>';
                html += '<div nexus-ui=select id=spatDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';
            $('#spatializationSettings').html(html);
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
