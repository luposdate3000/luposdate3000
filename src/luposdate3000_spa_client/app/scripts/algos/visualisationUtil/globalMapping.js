var minId, maxId, minDepth, maxDepth, minIndex, maxIndex;

function getAudioData(string, id, label, index, audioDimension) {
    var mode = App.config.sonification[audioDimension].mode;
    var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(), 10);
    switch (mode) {
        case 'Simple':
            if (audioDimension === "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
        case "Operator-ID":
            if (audioDimension === "Pitch" && pitchMode === 0) {
                return scale(parseInt(label.split(" ")[1].split("\n")[0], 10), minId, maxId, 30, 800);
            } else {
                let j = 0;
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                        if (dataNodes[i].id === id) {
                            if (audioDimension === "Chord") {
                                return getChordTones(mode, j, true);
                            } else {
                                return App.config.sonification[audioDimension][mode].value[j];
                            }
                        }
                        j++;
                    }
                }
            }
            break;
        case 'Operator-Depth':
            if (audioDimension === "Pitch" && pitchMode === 0) {
                return scale(networkSon.getPosition(id).y, minDepth, maxDepth, 30, 800);
            } else {
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    if (dataNodes[i].id === id) {
                        for (j = 0; j <= differentPositions.length - 1; j++) {
                            if (Object.values(networkSon.getPositions(id))[0].y === parseInt(differentPositions[j], 10)) {
                                if (audioDimension === "Chord") {
                                    return getChordTones(mode, j, true);
                                } else {
                                    return App.config.sonification[audioDimension][mode].value[j];
                                }
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Type':
            var i, j;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id === id) {
                    for (j = 0; j <= differentTypes.length; j++) {
                        if (dataNodes[i].label.split(" ")[0] === differentTypes[j]) {
                            if (audioDimension === "Chord") {
                                return getChordTones(mode, j, true);
                            } else {
                                return App.config.sonification[audioDimension][mode].value[j];
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Variable':
            var i, j;
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (dataNodes[i].id === id) {
                    for (j = 0; j <= differentOperatorVariables.length; j++) {
                        if (dataNodes[i].label.split("\n")[1] === differentOperatorVariables[j]) {
                            if (audioDimension === "Chord") {
                                return getChordTones(mode, j, true);
                            } else {
                                return App.config.sonification[audioDimension][mode].value[j];
                            }
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            if (audioDimension === "Pitch") {
                return scale(index, minIndex, maxIndex, 30, 800);
            } else if (audioDimension === "Duration") {
                var durations = [1, 2, 4, 8, 16];
                var tmp2 = scale(index, minIndex, maxIndex, 1, 16);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return output + 'n';
            } else if (audioDimension === "Loudness") {
                var tmp = App.config.sonification[audioDimension][mode].value;
                return scale(index, minIndex, maxIndex, 0, tmp);
            } else if (audioDimension === "Octave") {
                var durations = [1, 2, 3, 4, 5, 6, 7];
                var tmp2 = scale(index, minIndex, maxIndex, 1, 7);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return '' + output;
            } else if (audioDimension === "Spatialization") {
                return scale(index, minIndex, maxIndex, -1, 1);
            } else if (audioDimension === "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
        case 'Data-Variable':
            var i, j;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                if (globalAnimationList[i][3] === index) {
                    for (j = 0; j <= differentDataVariables.length; j++) {
                        if (globalAnimationList[i][2].split(" ")[0] === differentDataVariables[j]) {
                            if (audioDimension === "Chord") {
                                return getChordTones(mode, j, true);
                            } else {
                                return App.config.sonification[audioDimension][mode].value[j];
                            }
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            if (audioDimension === "Pitch") {
                var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                return scale(tmp, 0, 100, 30, 800);
            } else if (audioDimension === "Duration") {
                var durations = [1, 2, 4, 8, 16];
                var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                var tmp2 = scale(tmp, 0, 100, 1, 16);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return output + 'n';
            } else if (audioDimension === "Loudness") {
                var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                var tmp2 = App.config.sonification[audioDimension][mode].value;
                return scale(tmp, 0, 100, 0, tmp2);
            } else if (audioDimension === "Octave") {
                var durations = [1, 2, 3, 4, 5, 6, 7];
                var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                var tmp2 = scale(tmp, 0, 100, 1, 7);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return '' + output;
            } else if (audioDimension === "Spatialization") {
                var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                return scale(tmp, 0, 100, -1, 1);
            } else if (audioDimension === "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
    }
}

function createMyMenu(audioDimension, selector) {
    if (audioDimension === "Loudness") {
        return new Nexus.Slider(selector, {
            'size': [120, 40],
            'mode': 'relative',
            'min': 0,
            'max': 1,
            'step': 0.05,
            'value': 0.5
        });
    } else if (audioDimension === "Spatialization") {
        return new Nexus.Slider(selector, {
            'size': [120, 40],
            'mode': 'relative',
            'min': -1,
            'max': 1,
            'step': 0.05,
            'value': 0
        });
    } else {
        return new Nexus.Select(selector, {
            'size': [100, 40],
            'options': App.operators[audioDimension]
        });
    }
}

function createDefaultConfig(audioDimension, mode, value, arpeggio) {
    if (App.config.sonification[audioDimension].mode != mode) {
        switch (mode) {
            case "None":
            case 'Data-Index':
            case 'Query-Progress':
                App.config.sonification[audioDimension] = {
                    "mode": mode
                }
                break
            default:
                if (audioDimension == "Chord") {
                    App.config.sonification[audioDimension] = {
                        "mode": mode,
                        mode: {
                            "value": App.operators[audioDimension][0],
                            "arpeggio": App.operators.defaultArpeggio
                        }
                    };
                } else {
                    App.config.sonification[audioDimension] = {
                        "mode": mode,
                        mode: {
                            "value": App.operators[audioDimension][0]
                        }
                    };
                }
        }
    }
}

function createArraySelector(audioDimension, labels) {
    var html;
    $("#" + audioDimension.toLowerCase() + "Settings").show();
    if (audioDimension === "Pitch") {
        $('#radioPitch').hide();
        $('#pitchSettingsExplicit').show();
        $('#pitchSettingsExplicit').empty();
        html = '';
    } else {
        $("#" + audioDimension.toLowerCase() + "Settings").empty();
        html = '<fieldset>';
    }
    var i;
    var configSettings = [];
    var arpeggioSettings = [];
    for (i = 0; i <= labels.length - 1; i++) {
        var selector = audioDimension.toLowerCase() + i
        html += '<h7>' + labels[i] + '</h7><br>';
        html += '<div style=overflow:hidden;>';
        html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
        html += '<div nexus-ui=select id=' + selector + ' style=float:left;margin-right:10px;></div>';
        if (audioDimension === "Chord") {
            html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
            html += '<input type=checkbox id=arpeggio' + selector + ' style=overflow:hidden;margin-top:12px;><br>';
            arpeggioSettings.push(App.operators.defaultArpeggio);
        }
        html += '</div>';
        configSettings.push(App.operators[audioDimension][0]);
    }
    if (audioDimension === "Pitch") {
        $('#pitchSettingsExplicit').html(html);
    } else {
        html += '</fieldset>';
        $("#" + audioDimension.toLowerCase() + "Settings").html(html);
    }
    createDefaultConfig(audioDimension, mode, configSettings, arpeggioSettings)
    for (i = 0; i <= labels.length - 1; i++) {
        var selector = audioDimension.toLowerCase() + i
        var myMenu = createMyMenu(audioDimension, "#" + selector)
        myMenu.value = App.config.sonification[audioDimension][mode].value[i];
        myMenu.myAudioDimension = audioDimension
        myMenu.myMode = mode
        myMenu.myI = i
        myMenu.on('change', function(v) {
            App.config.sonification[this.myAudioDimension][this.myMode].value[this.myI] = v.value
        });
        if (audioDimension === "Chord") {
            var arpeggio = $("#arpeggio" + selector)
            arpeggio.prop('checked', App.config.sonification[audioDimension][mode].arpeggio[i]);
            arpeggio.on('change', function(v) {
                App.config.sonification[audioDimension][mode].arpeggio[i] = arpeggio.is(':checked');
            });
        } else if (audioDimension === "Instrument") {
            loadInstrument(myMenu.value, true);
        }
    }
}

function createSimpleSelector(audioDimension, label) {
    var html;
    $("#" + audioDimension.toLowerCase() + "Settings").show();
    if (audioDimension === "Pitch") {
        $('#radioPitch').hide();
        $('#pitchSettingsExplicit').show();
        $('#pitchSettingsExplicit').empty();
        html = '';
    } else {
        $("#" + audioDimension.toLowerCase() + "Settings").empty();
        html = '<fieldset>';
    }
    var selector = audioDimension.toLowerCase() + 'Simple'
    html += '<h7>' + label + '</h7><br>';
    html += '<div style=overflow:hidden;>';
    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
    html += '<div nexus-ui=select id=' + selector + ' style=float:left;margin-right:10px;></div>';
    if (audioDimension === "Chord") {
        html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
        html += '<input type=checkbox id=arpeggio' + selector + ' style=overflow:hidden;margin-top:12px;><br>';
    }
    html += '</div>';
    if (audioDimension === "Pitch") {
        $('#pitchSettingsExplicit').html(html);
    } else {
        html += '</fieldset>';
        $("#" + audioDimension.toLowerCase() + "Settings").html(html);
    }
    createDefaultConfig(audioDimension, mode, App.operators[audioDimension][0], App.operators.defaultArpeggio)
    var myMenu = createMyMenu(audioDimension, "#" + selector)
    myMenu.value = App.config.sonification[audioDimension][mode].value;
    myMenu.myAudioDimension = audioDimension
    myMenu.myMode = mode
    myMenu.on('change', function(v) {
        App.config.sonification[this.myAudioDimension][this.myMode].value = v.value
    });
    if (audioDimension === "Chord") {
        var arpeggio = $("#arpeggio" + selector)
        arpeggio.prop('checked', App.config.sonification[audioDimension][mode].arpeggio);
        arpeggio.on('change', function(v) {
            App.config.sonification[audioDimension][mode].arpeggio = arpeggio.is(':checked');
        });
    } else if (audioDimension === "Instrument") {
        loadInstrument(myMenu.value, false);
    }
}

function audioDimensionSetup(mode, audioDimension) {
    switch (mode) {
        case 'None':
            createDefaultConfig(audioDimension, mode)
            $("#" + audioDimension.toLowerCase() + "Settings").hide();
            if (audioDimension === "Pitch") {
                $('#pitchSettingsExplicit').hide();
                $('#radioPitch').hide();
            }
            break;
        case 'Data-Index':
            switch (audioDimension) {
                case "Loudness":
                    createSimpleSelector(audioDimension, "Please choose maximum loudness.")
                    break
                case "Duration":
                case "Octave":
                case "Spatialization":
                    createDefaultConfig(audioDimension, mode)
                    $("#" + audioDimension.toLowerCase() + "Settings").show();
                    $("#" + audioDimension.toLowerCase() + "Settings").empty();
                    var html = "<fieldset>";
                    switch (audioDimension) {
                        case "Duration":
                            html += '<h7>Data-Index will be mapped to note duration.</h7>';
                            break
                        case "Octave":
                            html += '<h7>Data index will be mapped to the octave.</h7>';
                            break
                        case "Spatialization":
                            html += '<h7>Data index will be mapped to left and right speaker.</h7>';
                            break
                    }
                    html += '</fieldset>';
                    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
                    break
                case "Pitch":
                    createDefaultConfig(audioDimension, mode)
                    $('#radioPitch').hide();
                    $('#pitchSettings').show();
                    $('#pitchExplicit').prop('checked', true);
                    $('#pitchDynamic').prop('checked', false);
                    $('#pitchSettingsExplicit').show();
                    $('#pitchSettingsExplicit').empty();
                    var html = '<h7>Data index will be mapped to the note.</h7>';
                    $('#pitchSettingsExplicit').html(html);
                    break
            }
            break;
        case 'Query-Progress':
            switch (audioDimension) {
                case "Loudness":
                    createSimpleSelector(audioDimension, "Please choose maximum loudness.")
                    break
                case "Duration":
                case "Octave":
                case "Spatialization":
                    createDefaultConfig(audioDimension, mode)
                    $("#" + audioDimension.toLowerCase() + "Settings").show();
                    $("#" + audioDimension.toLowerCase() + "Settings").empty();
                    var html = "<fieldset>";
                    switch (audioDimension) {
                        case "Duration":
                            html += '<h7>Query progress will be mapped to note duration.</h7>';
                            break
                        case "Octave":
                            html += '<h7>Query progress will be mapped to the octave.</h7>';
                            break
                        case "Spatialization":
                            html += '<h7>Query progress will be mapped to left and right speaker.</h7>';
                            break
                    }
                    html += '</fieldset>';
                    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
                    break
                case "Pitch":
                    createDefaultConfig(audioDimension, mode)
                    $('#radioPitch').hide();
                    $('#pitchSettings').show();
                    $('#pitchExplicit').prop('checked', true);
                    $('#pitchDynamic').prop('checked', false);
                    $('#pitchSettingsExplicit').show();
                    $('#pitchSettingsExplicit').empty();
                    var html = '<h7>Query progress will be mapped to the note.</h7>';
                    $('#pitchSettingsExplicit').html(html);
                    break
            }
            break;
        case 'Simple':
            createSimpleSelector(audioDimension, "Select a global setting")
            break;
        case 'Operator-ID':
            var labels = []
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    labels.push(dataNodes[i].label.split("\n")[0])
                }
            }
            createArraySelector(audioDimension, labels)
            break;
        case 'Operator-Depth':
            calcDifferentPositions();
            var labels = []
            for (i = 0; i <= differentPositions.length - 1; i++) {
                labels.push('Layer: ' + i)
            }
            createArraySelector(audioDimension, labels)
            break;
        case 'Operator-Type':
            calcDifferentTypes();
            var labels = []
            for (i = 0; i <= differentTypes.length - 1; i++) {
                labels.push('Type: ' + differentTypes[i] + )
            }
            createArraySelector(audioDimension, labels)
            break;
        case 'Operator-Variable':
            calcDifferentOperatorVariables();
            var labels = []
            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                labels.push('Variable: ' + differentOperatorVariables[i] + )
            }
            createArraySelector(audioDimension, labels)
            break;
        case 'Data-Variable':
            calcDifferentDataVariables();
            var labels = []
            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                labels.push('Variable: ' + differentDataVariables[i] + )
            }
            createArraySelector(audioDimension, labels)
            break;
    }
}

function calculateMinMaxID() {
    maxId = 0;
    minId = 9999999999;
    var i;
    for (i = 0; i <= dataNodes.length - 1; i++) {
        var k = parseInt(dataNodes[i].label.split(" ")[1].split("\n")[0], 10);
        if (k > maxId) {
            maxId = k;
        }
        if (k < minId) {
            minId = k;
        }
    }
}

function calculateMinMaxDepth() {
    maxDepth = -999999999;
    minDepth = 999999999;
    var i;
    var positions = Object.values(networkSon.getPositions());
    for (i = 0; i <= positions.length - 1; i++) {
        if (positions[i].y > maxDepth) {
            maxDepth = positions[i].y;
        }
        if (positions[i].y < minDepth) {
            minDepth = positions[i].y;
        }
    }
}

function calculateMinMaxIndex() {
    maxIndex = 0;
    minIndex = 999999999999999;
    var i;
    for (i = 0; i <= globalAnimationList.length - 1; i++) {
        var k = parseInt(globalAnimationList[i][3], 10);
        if (k > maxIndex) {
            maxIndex = k;
        }
        if (k < minIndex) {
            minIndex = k;
        }
    }
}

function getChordTones(mode, j, isArray) {
    if (isArray) {
        var basicTone = App.operators.chords[App.config.sonification.Chord[mode].value[j]].basic;
        var firstTone = App.operators.chords[App.config.sonification.Chord[mode].value[j]].first;
        var secondTone = App.operators.chords[App.config.sonification.Chord[mode].value[j]].second;
        return [App.config.sonification.Chord[mode].arpeggio[j], basicTone, firstTone, secondTone];
    } else {
        var basicTone = App.operators.chords[App.config.sonification.Chord[mode].value].basic;
        var firstTone = App.operators.chords[App.config.sonification.Chord[mode].value].first;
        var secondTone = App.operators.chords[App.config.sonification.Chord[mode].value].second;
        return [App.config.sonification.Chord[mode].arpeggio, basicTone, firstTone, secondTone];
    }
}