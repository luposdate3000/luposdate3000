var instrumentOperator = [];
var instrumentOperatorDepth = [];
var instrumentOperatorType = [];
var instrumentOperatorVariable = [];
var instrumentDataIndex;
var instrumentDataVariable = [];

function getInstrument(string, id, label, index) {
    switch (string) {
        case 'Simple':
            return App.config.sonification.Instrument.Simple.value
            break;
        case 'Operator-ID':
            var i;
            var j = 0;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = instrumentOperator[j].value;
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
                            var t = instrumentOperatorDepth[j].value;
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
                            return instrumentOperatorType[j].value;
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
                            return instrumentOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            return instrumentDataIndex.value;
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            return instrumentDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            return instrumentDataIndex.value;
            break;
    }
    return 'piano'
}

function instrumentSetup() {
    App.mappingFunctions.Instrument = function(string) {
        App.config.sonification.Instrument.mode = string
        switch (string) {
            case 'Simple':
                if (!App.config.sonification.Instrument.hasOwnProperty("Simple")) {
                    App.config.sonification.Instrument.Simple = {
                        value: App.samples[0]
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
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                        html += '<div nexus-ui=select id=instrumentOperator-' + j + ' style=float:left;margin-right:10px;></div>';
                        html += '</div>';
                    }
                }
                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentOperator = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#instrumentOperator-' + j;
                        instrumentOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': App.operators.instruments
                        }));
                        loadInstrument(instrumentOperator, true);
                    }
                }
                break;
            case 'Operator-Depth':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();

                calcDifferentPositions();
                var html = "<fieldset>";
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Layer ' + i + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorDepth-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }
                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentOperatorDepth = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#instrumentOperatorDepth-' + i;
                    instrumentOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    loadInstrument(instrumentOperatorDepth, true);
                }
                break;
            case 'Operator-Type':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = '<fieldset>';
                calcDifferentTypes();
                instrumentOperatorType = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorType-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentOperatorType = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#instrumentOperatorType-' + i;
                    instrumentOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    loadInstrument(instrumentOperatorType, true);
                }
                break;
            case 'Operator-Variable':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();
                var html = '<fieldset>';

                calcDifferentOperatorVariables();

                var i;

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentOperatorVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';
                $('#instrumentSettings').html(html);

                instrumentOperatorVariable = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#instrumentOperatorVariable-' + i;
                    instrumentOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    loadInstrument(instrumentOperatorVariable, true);
                }
                break;
            case 'Data-Index':
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
                loadInstrument(instrumentDataIndex, false);
                break;
            case 'Data-Variable':
                $('#instrumentSettings').show();
                $('#instrumentSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Instrument: </p>';
                    html += '<div nexus-ui=select id=instrumentDataVariable-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }

                html += '</fieldset>';

                instrumentDataVariable = [];
                $('#instrumentSettings').html(html);
                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#instrumentDataVariable-' + i;
                    instrumentDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.instruments
                    }));
                    loadInstrument(instrumentDataVariable, true);
                }
                break;
            case 'Query-Progress':
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
                loadInstrument(instrumentDataIndex, false);
                break;
            default:
                $('#instrumentSettings').hide();
                break;
        }
    }
}

function loadInstrument(array, isArray){
    var object;
    if(isArray){
        object = array[array.length-1];
    }else{
        object = array;
    }
    object.on('change', function(v){
    if (!usedInstruments.includes(v.value)){
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
