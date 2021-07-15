var minId, maxId, minDepth, maxDepth, minIndex, maxIndex;

function getAudioData(idMappings, id, label, index, audioDimension) {
    //string is unused
    var mode = App.config.sonification[audioDimension].mode;
    switch (mode) {
        case 'None':
            return App.operators[audioDimension].standard
            break
        case 'Simple':
            if (audioDimension == "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
        case "Dynamic Operator-ID":
            if (audioDimension == "Pitch") {
                return scale(label.split(" ")[1].split("\n")[0] | 0, minId, maxId, 30, 800);
            }
            break
        case "Dynamic Operator-Depth":
            if (audioDimension == "Pitch") {
                return scale(networkSon.nodesHandler.getPosition(id) | 0, minDepth, maxDepth, 30, 800);
            }
            break
        case "Operator-ID":
        case 'Operator-Depth':
        case 'Operator-Type':
            if (audioDimension == "Chord") {
                return getChordTones(mode, idMappings[mode][id], true)
            } else {
                return App.config.sonification[audioDimension][mode].value[idMappings[mode][id]]
            }
            break;
        case 'Operator-Variable':
            if (audioDimension == "Chord") {
                return getChordTones(mode, idMappings[mode][id], true)
            } else {
                return App.config.sonification[audioDimension][mode].value[idMappings[mode][id]]
            }
            break;
        case 'Data-Variable':
            if (audioDimension == "Chord") {
                return getChordTones(mode, idMappings[mode][index], true)
            } else {
                return App.config.sonification[audioDimension][mode].value[idMappings[mode][index]]
            }
            break;
        case 'Data-Index':
            //using index
            if (audioDimension == "Pitch") {
                return scale(index, minIndex, maxIndex, 30, 800);
            } else if (audioDimension == "Duration") {
                var durations = [1, 2, 4, 8, 16];
                var tmp2 = scale(index, minIndex, maxIndex, 1, 16);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return output + 'n';
            } else if (audioDimension == "Loudness") {
                var tmp = App.config.sonification[audioDimension][mode].value;
                return scale(index, minIndex, maxIndex, 0, tmp);
            } else if (audioDimension == "Octave") {
                var durations = [1, 2, 3, 4, 5, 6, 7];
                var tmp2 = scale(index, minIndex, maxIndex, 1, 7);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return '' + output;
            } else if (audioDimension == "Spatialization") {
                return scale(index, minIndex, maxIndex, -1, 1);
            } else if (audioDimension == "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
        case 'Query-Progress':
            var tmp = ((App.globalAnimationList.length - currentIndex) / App.globalAnimationList.length) * 100;
            if (audioDimension == "Pitch") {
                return scale(tmp, 0, 100, 30, 800);
            } else if (audioDimension == "Duration") {
                var durations = [1, 2, 4, 8, 16];
                var tmp2 = scale(tmp, 0, 100, 1, 16);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return output + 'n';
            } else if (audioDimension == "Loudness") {
                var tmp2 = App.config.sonification[audioDimension][mode].value;
                return scale(tmp, 0, 100, 0, tmp2);
            } else if (audioDimension == "Octave") {
                var durations = [1, 2, 3, 4, 5, 6, 7];
                var tmp2 = scale(tmp, 0, 100, 1, 7);
                var output = durations.reduce((prev, curr) => Math.abs(curr - tmp2) < Math.abs(prev - tmp2) ? curr : prev);
                return '' + output;
            } else if (audioDimension == "Spatialization") {
                return scale(tmp, 0, 100, -1, 1);
            } else if (audioDimension == "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension][mode].value;
            }
            break;
    }
}

function createMyMenu(audioDimension, selector) {
    if (audioDimension == "Loudness") {
        return new Nexus.Slider(selector, {
            'size': [180, 40],
            'mode': 'relative',
            'min': 0,
            'max': 1,
            'step': 0.05,
            'value': 0.5
        });
    } else if (audioDimension == "Spatialization") {
        return new Nexus.Slider(selector, {
            'size': [180, 40],
            'mode': 'relative',
            'min': -1,
            'max': 1,
            'step': 0.05,
            'value': 0
        });
    } else if (audioDimension == "Chord") {
        values = []
        for (v in App.operators[audioDimension].values) {
            values.push(v)
        }
        return new Nexus.Select(selector, {
            'size': [180, 40],
            'options': values
        });
    } else {
        return new Nexus.Select(selector, {
            'size': [180, 40],
            'options': App.operators[audioDimension].values
        });
    }
}

function createDefaultConfig(audioDimension, mode, value, arpeggio) {
    if (App.config.sonification[audioDimension].mode != mode) {
        App.config.sonification[audioDimension] = {
            "mode": mode
        }

        switch (mode) {
            case "None":
                break
            case 'Dynamic Operator-ID':
            case 'Dynamic Operator-Depth':
            case 'Data-Index':
            case 'Query-Progress':
                if (audioDimension == "Loudness") {
                    App.config.sonification[audioDimension][mode] = {
                        "value": value
                    }
                }
                break
            default:
                App.config.sonification[audioDimension][mode] = {
                    "value": value
                }
                if (audioDimension == "Chord") {
                    App.config.sonification[audioDimension][mode].arpeggio = arpeggio
                }
        }
    }
}

function createArraySelector(audioDimension, labels, mode) {
    var html;
    $("#" + audioDimension.toLowerCase() + "Settings").show();
    $("#" + audioDimension.toLowerCase() + "Settings").empty();
    html = '<fieldset>';
    var i;
    var configSettings = [];
    var arpeggioSettings = [];
    for (i = 0; i <= labels.length - 1; i++) {
        var selector = audioDimension.toLowerCase() + i
        html += '<h7>' + labels[i] + '</h7><br>';
        html += '<div style=overflow:hidden;>';
        html += '<div style=overflow:hidden;>';
        html += '<p style=float:left;margin-right:10px;margin-top:9px;>'+App.operators[audioDimension].hinttext+'</p>';
        html += '<div nexus-ui=select id=' + selector + ' style=float:left;margin-right:10px;></div>';
html += '</div>';
        if (audioDimension == "Chord") {
html += '<div style=overflow:hidden;>';
            html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
            html += '<input type=checkbox myindex='+i+' id=arpeggio' + selector + ' style=overflow:hidden;margin-top:12px;><br>';
html += '</div>';
            arpeggioSettings.push(App.operators.defaultArpeggio);
        }
        html += '</div>';
        configSettings.push(App.operators[audioDimension].standard);
    }
    html += '</fieldset>';
    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
    createDefaultConfig(audioDimension, mode, configSettings, arpeggioSettings)
    for (i = 0; i <= labels.length - 1; i++) {
        var selector = audioDimension.toLowerCase() + i
        var myMenu = createMyMenu(audioDimension, "#" + selector)
        myMenu.value = App.config.sonification[audioDimension][mode].value[i];
        myMenu.myAudioDimension = audioDimension
        myMenu.myMode = mode
        myMenu.myI = i
        myMenu.on('change', function(v) {
            if (this.myAudioDimension == "Loudness" || this.myAudioDimension == "Spatialization") {
                App.config.sonification[this.myAudioDimension][this.myMode].value[this.myI] = v
            } else {
                App.config.sonification[this.myAudioDimension][this.myMode].value[this.myI] = v.value
            }
        });
        if (audioDimension == "Chord") {
            var arpeggio = $("#arpeggio" + selector)
            arpeggio.prop('checked', App.config.sonification[audioDimension][mode].arpeggio[i]);
            arpeggio.on('change', function(v) {
                App.config.sonification[audioDimension][mode].arpeggio[this.attributes.myindex.nodeValue] = this.checked;
            });
        }
    }
}

function createSimpleSelector(audioDimension, label, mode) {
    var html;
    $("#" + audioDimension.toLowerCase() + "Settings").show();
    $("#" + audioDimension.toLowerCase() + "Settings").empty();
    html = '<fieldset>';
    var selector = audioDimension.toLowerCase() + 'Simple'
    html += '<h7>' + label + '</h7><br>';
    html += '<div style=overflow:hidden;>';
    html += '<div style=overflow:hidden;>';
    html += '<p style=float:left;margin-right:10px;margin-top:9px;>'+App.operators[audioDimension].hinttext+'</p>';
    html += '<div nexus-ui=select id=' + selector + ' style=float:left;margin-right:10px;></div>';
html += '</div>';
    if (audioDimension == "Chord") {
    html += '<div style=overflow:hidden;>';
        html += '<p style=float:left;margin-top:9px;margin-right:8px;>Arpeggio: </p>';
        html += '<input type=checkbox id=arpeggio' + selector + ' style=overflow:hidden;margin-top:12px;><br>';
html += '</div>';
    }
    html += '</div>';
    html += '</fieldset>';
    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
    createDefaultConfig(audioDimension, mode, App.operators[audioDimension].standard, App.operators.defaultArpeggio)
    var myMenu = createMyMenu(audioDimension, "#" + selector)
    myMenu.value = App.config.sonification[audioDimension][mode].value;
    myMenu.myAudioDimension = audioDimension
    myMenu.myMode = mode
    myMenu.on('change', function(v) {
        if (this.myAudioDimension == "Loudness" || this.myAudioDimension == "Spatialization") {
            App.config.sonification[this.myAudioDimension][this.myMode].value = v
        } else {
            App.config.sonification[this.myAudioDimension][this.myMode].value = v.value
        }
    });
    if (audioDimension == "Chord") {
        var arpeggio = $("#arpeggio" + selector)
        arpeggio.prop('checked', App.config.sonification[audioDimension][mode].arpeggio);
        arpeggio.on('change', function(v) {
            App.config.sonification[audioDimension][mode].arpeggio = arpeggio.is(':checked');
        });
    }
}

function audioDimensionSetup(mode, audioDimension) {
    switch (mode) {
        case 'Dynamic Operator-ID':
        case 'Dynamic OperatorDepth':
        case 'None':
            createDefaultConfig(audioDimension, mode)
            $("#" + audioDimension.toLowerCase() + "Settings").hide();
            break;
        case 'Data-Index':
            switch (audioDimension) {
                case "Loudness":
                    createSimpleSelector(audioDimension, "Please choose maximum loudness.", mode)
                    break;
                case "Pitch":
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
                        case "Pitch":
                            html += '<h7>Data index will be mapped to the note.</h7>';
                            break
                    }
                    html += '</fieldset>';
                    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
                    break
            }
            break;
        case 'Query-Progress':
            switch (audioDimension) {
                case "Loudness":
                    createSimpleSelector(audioDimension, "Please choose maximum loudness.", mode)
                    break;
                case "Pitch":
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
                        case "Pitch":
                        case "Octave":
                            html += '<h7>Query progress will be mapped to the note.</h7>';
                            break
                    }
                    html += '</fieldset>';
                    $("#" + audioDimension.toLowerCase() + "Settings").html(html);
                    break
            }
            break;
        case 'Simple':
            createSimpleSelector(audioDimension, "Select a global setting", mode)
            break;
        case 'Operator-ID':
            var labels = []
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    labels.push(dataNodes[i].label.split("\n")[0])
                }
            }
            createArraySelector(audioDimension, labels, mode)
            break;
        case 'Operator-Depth':
            var labels = []
            for (i = 0; i <= differentPositions.length - 1; i++) {
                labels.push('Layer: ' + i)
            }
            createArraySelector(audioDimension, labels, mode)
            break;
        case 'Operator-Type':
            var labels = []
            for (i = 0; i <= differentTypes.length - 1; i++) {
                labels.push('Type: ' + differentTypes[i])
            }
            createArraySelector(audioDimension, labels, mode)
            break;
        case 'Operator-Variable':
            var labels = []
            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                labels.push('Variable: ' + differentOperatorVariables[i])
            }
            createArraySelector(audioDimension, labels, mode)
            break;
        case 'Data-Variable':
            var labels = []
            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                labels.push('Variable: ' + differentDataVariables[i])
            }
            createArraySelector(audioDimension, labels, mode)
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
    for (i = 0; i <= App.globalAnimationList.length - 1; i++) {
        var k = parseInt(App.globalAnimationList[i][3], 10);
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

        var basicTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value[j]].basic;
        var firstTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value[j]].first;
        var secondTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value[j]].second;
        return [App.config.sonification.Chord[mode].arpeggio[j], basicTone, firstTone, secondTone];
    } else {
        var basicTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value].basic;
        var firstTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value].first;
        var secondTone = App.operators.Chord.values[App.config.sonification.Chord[mode].value].second;
        return [App.config.sonification.Chord[mode].arpeggio, basicTone, firstTone, secondTone];
    }
}
