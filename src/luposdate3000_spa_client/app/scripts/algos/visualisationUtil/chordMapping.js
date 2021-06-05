var chordOperator = [];
var chordOperatorDepth = [];
var chordOperatorType = [];
var chordOperatorVariable = [];
var chordDataIndex;
var chordDataVariable = [];
var arpeggio = [];

function getChord(string, id, label, index) {
    switch (string) {
        case 'Simple':
            var basicTone = App.operators.chords[chordDataIndex.value].basic;
            var firstTone = App.operators.chords[chordDataIndex.value].first;
            var secondTone = App.operators.chords[chordDataIndex.value].second;
            if ($('#arpeggioIndex').is(':checked')) {
                return [true, basicTone, firstTone, secondTone];
            } else {
                return [false, basicTone, firstTone, secondTone];
            }
            break;
        case 'Operator-ID':
            var i;
            var j = 0;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var basicTone = App.operators.chords[chordOperator[j].value].basic;
                        var firstTone = App.operators.chords[chordOperator[j].value].first;
                        var secondTone = App.operators.chords[chordOperator[j].value].second;
                        if ($(arpeggio[j].selector).is(':checked')) {
                            return [true, basicTone, firstTone, secondTone];
                        } else {
                            return [false, basicTone, firstTone, secondTone];
                        }
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
                            var basicTone = App.operators.chords[chordOperatorDepth[j].value].basic;
                            var firstTone = App.operators.chords[chordOperatorDepth[j].value].first;
                            var secondTone = App.operators.chords[chordOperatorDepth[j].value].second;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            } else {
                                return [false, basicTone, firstTone, secondTone];
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
                            var basicTone = App.operators.chords[chordOperatorType[j].value].basic;
                            var firstTone = App.operators.chords[chordOperatorType[j].value].first;
                            var secondTone = App.operators.chords[chordOperatorType[j].value].second;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            } else {
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Variable':
            console.log("switch-statement");
            var i, j;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id == id) {
                    for (j = 0; j <= differentOperatorVariables.length; j++) {
                        if (dataNodes[i].label.split("\n")[1] == differentOperatorVariables[j]) {
                            var basicTone = App.operators.chords[chordOperatorVariable[j].value].basic;
                            var firstTone = App.operators.chords[chordOperatorVariable[j].value].first;
                            var secondTone = App.operators.chords[chordOperatorVariable[j].value].second;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            } else {
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            var basicTone = App.operators.chords[chordDataIndex.value].basic;
            var firstTone = App.operators.chords[chordDataIndex.value].first;
            var secondTone = App.operators.chords[chordDataIndex.value].second;
            if ($('#arpeggioIndex').is(':checked')) {
                return [true, basicTone, firstTone, secondTone];
            } else {
                return [false, basicTone, firstTone, secondTone];
            }
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] == index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]) {
                            console.log(" counting");
                            var basicTone = App.operators.chords[chordDataVariable[j].value].basic;
                            var firstTone = App.operators.chords[chordDataVariable[j].value].first;
                            var secondTone = App.operators.chords[chordDataVariable[j].value].second;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            } else {
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var basicTone = App.operators.chords[chordDataIndex.value].basic;
            var firstTone = App.operators.chords[chordDataIndex.value].first;
            var secondTone = App.operators.chords[chordDataIndex.value].second;
            if ($('#arpeggioQuery').is(':checked')) {
                return [true, basicTone, firstTone, secondTone];
            } else {
                return [false, basicTone, firstTone, secondTone];
            }
            break;
    }
}

function chordSetup() {
    App.mappingFunctions.Chord = function(string) {
        App.config.sonification.Chord.mode = string
        //If for one Operator is no chord set, use the default pitch settings
        switch (string) {
            case 'None':
                $('#chordSettings').hide();
                break;
            case 'Simple':
                if (!App.config.sonification.Chord.hasOwnProperty("Simple")) {
                    App.config.sonification.Chord.Simple = {
                        value: Object.keys(App.operators.chords)[0],
                        arpeggio: false
                    }
                }

                $('#chordSettings').show();
                $('#chordSettings').empty();

                var html = "<fieldset>";
                html += '<h7>Select a global setting.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                html += '<div nexus-ui=select id=chordDataIndex style=float:left;margin-right:8px;></div>';
                html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                html += '<input type=checkbox id=arpeggioIndex style=overflow:hidden;margin-top:12px;><br>';
                html += '</div>';

                html += '</fieldset>'
                $('#chordSettings').html(html);

                chordDataIndex = new Nexus.Select('#chordDataIndex', {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                });
                chordDataIndex.value = App.config.sonification.Chord.Simple.value;
                $('#arpeggioIndex').prop('checked', App.config.sonification.Chord.Simple.arpeggio);
                $('#arpeggioIndex').on('change', function(){
                    App.config.sonification.Chord.Simple.arpeggio = $('#arpeggioIndex').is(':checked');
                });
                chordDataIndex.on('change', function(v) {
                    App.config.sonification.Chord.Simple.value = v.value
                });
                break;
            case 'Operator-ID':
                $('#chordSettings').show();
                $('#chordSettings').empty();
                var html = '<fieldset>';
                var j;
                arpeggio = [];
                var configSettings = [];
                var arpeggioSettings = [];
                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        html += '<h7>' + dataNodes[j].label.split("\n")[0] + '</h7><br>';
                        html += '<div style=overflow:hidden;>';
                        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                        html += '<div nexus-ui=select id=chordOperator-' + j + ' style=float:left;margin-right:8px;></div>';
                        html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                        html += '<input type=checkbox id=arpeggio-' + j + ' style=overflow:hidden;margin-top:12px;><br>';
                        html += '</div>';
                        var tmp = '#arpeggio-' + j;
                        arpeggio.push($(tmp));
                        configSettings.push(Object.keys(App.operators.chords)[0]);
                        arpeggioSettings.push(false);
                    }
                }

                html += '</fieldset>';

                chordOperator = [];
                $('#chordSettings').html(html);

                if (!App.config.sonification.Chord.hasOwnProperty("OperatorID")) {
                    App.config.sonification.Chord.OperatorID = {
                        value: configSettings,
                        arpeggio: arpeggioSettings
                    }
                }

                for (j = 0; j <= dataNodes.length - 1; j++) {
                    if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                        var string = '#chordOperator-' + j;
                        chordOperator.push(new Nexus.Select(string, {
                            'size': [100, 40],
                            'options': Object.keys(App.operators.chords)

                        }));
                        chordOperator[chordOperator.length-1].value = App.config.sonification.Chord.OperatorID.value[chordOperator.length-1];
                        arpeggio[chordOperator.length-1].prop('checked', App.config.sonification.Chord.OperatorID.arpeggio[chordOperator.length-1]);
                        chordOperator[chordOperator.length-1].on('change', function(v) {
                            App.config.sonification.Chord.OperatorID.value[chordOperator.length-1] = v.value
                        });
                        $(arpeggio[chordOperator.length-1].selector).on('change', function(v) {
                            App.config.sonification.Chord.OperatorID.arpeggio[chordOperator.length-1] = $(arpeggio[chordOperator.length-1].selector).is(':checked');
                        });
                    }
                }
                break;
            case 'Operator-Depth':
                $('#chordSettings').show();
                $('#chordSettings').empty();
                arpeggio = [];
                calcDifferentPositions();
                var html = "<fieldset>"

                var configSettings = [];
                var arpeggioSettings = [];
                for (i = 0; i <= differentPositions.length - 1; i++) {
                    html += '<h7>Depth: ' + differentPositions[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                    html += '<div nexus-ui=select id=chordOperatorDepth-' + i + ' style=float:left;margin-right:8px;></div>';
                    html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                    html += '<input type=checkbox id=arpeggio-' + i + ' style=overflow:hidden;margin-top:12px;><br>';
                    html += '</div>';
                    var tmp = '#arpeggio-' + i;
                    arpeggio.push($(tmp));
                    configSettings.push(Object.keys(App.operators.chords)[0]);
                    arpeggioSettings.push(false);
                }

                html += '</fieldset>';

                chordOperatorDepth = [];
                $('#chordSettings').html(html);

                if (!App.config.sonification.Chord.hasOwnProperty("OperatorDepth")) {
                    App.config.sonification.Chord.OperatorDepth = {
                        value: configSettings,
                        arpeggio: arpeggioSettings
                    }
                }

                for (i = 0; i <= differentPositions.length - 1; i++) {
                    var string = '#chordOperatorDepth-' + i;
                    chordOperatorDepth.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.operators.chords)
                    }));
                    chordOperatorDepth[chordOperatorDepth.length-1].value = App.config.sonification.Chord.OperatorDepth.value[chordOperatorDepth.length-1];
                    arpeggio[chordOperatorDepth.length-1].prop('checked', App.config.sonification.Chord.OperatorDepth.arpeggio[chordOperatorDepth.length-1]);
                    chordOperatorDepth[chordOperatorDepth.length-1].on('change', function(v) {
                        App.config.sonification.Chord.OperatorDepth.value[chordOperatorDepth.length-1] = v.value
                    });
                    $(arpeggio[chordOperatorDepth.length-1].selector).on('change', function(v) {
                        App.config.sonification.Chord.OperatorDepth.arpeggio[chordOperatorDepth.length-1] = $(arpeggio[chordOperatorDepth.length-1].selector).is(':checked');
                    });
                }
                break;
            case 'Operator-Type':
                $('#chordSettings').show();
                $('#chordSettings').empty();
                var html = '<fieldset>';

                calcDifferentTypes();
                arpeggio = [];
                var configSettings = [];
                var arpeggioSettings = [];
                for (i = 0; i <= differentTypes.length - 1; i++) {
                    html += '<h7>Type: ' + differentTypes[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                    html += '<div nexus-ui=select id=chordOperatorType-' + i + ' style=float:left;margin-right:8px;></div>';
                    html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                    html += '<input type=checkbox id=arpeggio-' + i + ' style=overflow:hidden;margin-top:12px;><br>';
                    html += '</div>';
                    var tmp = '#arpeggio-' + i;
                    arpeggio.push($(tmp));
                    configSettings.push(Object.keys(App.operators.chords)[0]);
                    arpeggioSettings.push(false);
                }


                chordOperatorType = [];
                $('#chordSettings').html(html);

                if (!App.config.sonification.Chord.hasOwnProperty("OperatorType")) {
                    App.config.sonification.Chord.OperatorType = {
                        value: configSettings,
                        arpeggio: arpeggioSettings
                    }
                }

                for (i = 0; i <= differentTypes.length - 1; i++) {
                    var string = '#chordOperatorType-' + i;
                    chordOperatorType.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.operators.chords)
                    }));
                    chordOperatorType[chordOperatorType.length-1].value = App.config.sonification.Chord.OperatorType.value[chordOperatorType.length-1];
                    arpeggio[chordOperatorType.length-1].prop('checked', App.config.sonification.Chord.OperatorType.arpeggio[chordOperatorType.length-1]);
                    chordOperatorType[chordOperatorType.length-1].on('change', function(v) {
                        App.config.sonification.Chord.OperatorType.value[chordOperatorType.length-1] = v.value
                    });
                    $(arpeggio[chordOperatorType.length-1].selector).on('change', function(v) {
                        App.config.sonification.Chord.OperatorType.arpeggio[chordOperatorType.length-1] = $(arpeggio[chordOperatorType.length-1].selector).is(':checked');
                    });
                }
                break;
            case 'Operator-Variable':
                $('#chordSettings').show();
                $('#chordSettings').empty();

                var html = '<fieldset>';

                var i;
                calcDifferentOperatorVariables();
                arpeggio = [];
                var configSettings = [];
                var arpeggioSettings = [];
                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentOperatorVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:8px;margin-top:9px;>Chord: </p>';
                    html += '<div nexus-ui=select id=chordOperatorVariable-' + i + ' style=float:left;margin-right:8px;></div>';
                    html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                    html += '<input type=checkbox id=arpeggio-' + i + ' style=overflow:hidden;margin-top:12px;><br>';
                    html += '</div>';
                    var tmp = '#arpeggio-' + i;
                    arpeggio.push($(tmp));
                    configSettings.push(Object.keys(App.operators.chords)[0]);
                    arpeggioSettings.push(false);
                }

                html += '</fieldset>';

                chordOperatorVariable = [];
                $('#chordSettings').html(html);

                if (!App.config.sonification.Chord.hasOwnProperty("OperatorVariable")) {
                    App.config.sonification.Chord.OperatorVariable = {
                        value: configSettings,
                        arpeggio: arpeggioSettings
                    }
                }

                for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                    var string = '#chordOperatorVariable-' + i;
                    chordOperatorVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.operators.chords)
                    }));
                    chordOperatorVariable[chordOperatorVariable.length-1].value = App.config.sonification.Chord.OperatorVariable.value[chordOperatorVariable.length-1];
                    arpeggio[chordOperatorVariable.length-1].prop('checked', App.config.sonification.Chord.OperatorVariable.arpeggio[chordOperatorVariable.length-1]);
                    chordOperatorVariable[chordOperatorVariable.length-1].on('change', function(v) {
                        App.config.sonification.Chord.OperatorVariable.value[chordOperatorVariable.length-1] = v.value
                    });
                    $(arpeggio[chordOperatorVariable.length-1].selector).on('change', function(v) {
                        App.config.sonification.Chord.OperatorVariable.arpeggio[chordOperatorVariable.length-1] = $(arpeggio[chordOperatorVariable.length-1].selector).is(':checked');
                    });
                }
                break;
            case 'Data-Index':
                if (!App.config.sonification.Chord.hasOwnProperty("DataIndex")) {
                    App.config.sonification.Chord.DataIndex = {
                        value: Object.keys(App.operators.chords)[0],
                        arpeggio: false
                    }
                }

                //This setting does not make much sense.
                //Instead, simple selection will be used.
                $('#chordSettings').show();
                $('#chordSettings').empty();
                var html = '<fieldset>';
                html += '<h7>Mapping the data index to a chord is not available. Instead choose a global chord.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                html += '<div nexus-ui=select id=chordDataIndex style=float:left;margin-right:8px;></div>';
                html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                html += '<input type=checkbox id=arpeggioIndex style=overflow:hidden;margin-top:12px;><br>';
                html += '</div>';

                html += '</fieldset>';
                $('#chordSettings').html(html);

                chordDataIndex = new Nexus.Select('#chordDataIndex', {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                });
                chordDataIndex.value = App.config.sonification.Chord.DataIndex.value;
                $('#arpeggioIndex').prop('checked', App.config.sonification.Chord.DataIndex.arpeggio);
                $('#arpeggioIndex').on('change', function(){
                    App.config.sonification.Chord.DataIndex.arpeggio = $('#arpeggioIndex').is(':checked');
                });
                chordDataIndex.on('change', function(v) {
                    App.config.sonification.Chord.DataIndex.value = v.value
                });
                break;
            case 'Data-Variable':
                $('#chordSettings').show();
                $('#chordSettings').empty();
                arpeggio = [];
                var html = '<fieldset>';

                var i;
                calcDifferentDataVariables();
                var configSettings = [];
                var arpeggioSettings = [];

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    html += '<h7>Variable: ' + differentDataVariables[i] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                    html += '<div nexus-ui=select id=chordDataVariable-' + i + ' style=float:left;margin-right:8px;></div>';
                    html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                    html += '<input type=checkbox id=arpeggio-' + i + ' style=overflow:hidden;margin-top:12px;><br>';
                    html += '</div>';
                    var tmp = '#arpeggio-' + i;
                    arpeggio.push($(tmp));
                    configSettings.push(Object.keys(App.operators.chords)[0]);
                    arpeggioSettings.push(false);
                }

                html += '</fieldset>';

                chordDataVariable = [];
                $('#chordSettings').html(html);

                if (!App.config.sonification.Chord.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Chord.DataVariable = {
                        value: configSettings,
                        arpeggio: arpeggioSettings
                    }
                }

                for (i = 0; i <= differentDataVariables.length - 1; i++) {
                    var string = '#chordDataVariable-' + i;
                    chordDataVariable.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.operators.chords)
                    }));
                    chordDataVariable[chordDataVariable.length-1].value = App.config.sonification.Chord.DataVariable.value[chordDataVariable.length-1];
                    arpeggio[chordDataVariable.length-1].prop('checked', App.config.sonification.Chord.DataVariable.arpeggio[chordDataVariable.length-1]);
                    chordDataVariable[chordDataVariable.length-1].on('change', function(v) {
                        App.config.sonification.Chord.DataVariable.value[chordDataVariable.length-1] = v.value
                    });
                    $(arpeggio[chordDataVariable.length-1].selector).on('change', function(v) {
                        App.config.sonification.Chord.DataVariable.arpeggio[chordDataVariable.length-1] = $(arpeggio[chordDataVariable.length-1].selector).is(':checked');
                    });
                }
                break;
            case 'Query-Progress':
                if (!App.config.sonification.Chord.hasOwnProperty("QueryProgress")) {
                    App.config.sonification.Chord.QueryProgress = {
                        value: Object.keys(App.operators.chords)[0],
                        arpeggio: false
                    }
                }

                //This setting does not make much sense.
                //Instead, simple selection will be used.
                $('#chordSettings').show();
                $('#chordSettings').empty();
                var html = "<fieldset>";
                html += '<h7>Mapping the query progress to a chord is not available. Instead choose a global chord.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Chord: </p>';
                html += '<div nexus-ui=select id=chordDataIndex style=float:left;margin-right:8px;></div>';
                html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
                html += '<input type=checkbox id=arpeggioIndex style=overflow:hidden;margin-top:12px;><br>';
                html += '</div>';

                html += '</fieldset>'
                $('#chordSettings').html(html);


                if (!App.config.sonification.Octave.hasOwnProperty("DataVariable")) {
                    App.config.sonification.Octave.DataVariable = {
                        value: configSettings
                    }
                }

                chordDataIndex = new Nexus.Select('#chordDataIndex', {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                });
                chordDataIndex.value = App.config.sonification.Chord.QueryProgress.value;
                $('#arpeggioIndex').prop('checked', App.config.sonification.Chord.QueryProgress.arpeggio);
                $('#arpeggioIndex').on('change', function(){
                    App.config.sonification.Chord.QueryProgress.arpeggio = $('#arpeggioIndex').is(':checked');
                });
                chordDataIndex.on('change', function(v) {
                    App.config.sonification.Chord.QueryProgress.value = v.value
                });
                break;
        }
    }
}
