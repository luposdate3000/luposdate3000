var minId, maxId, minDepth, maxDepth, minIndex, maxIndex;

function getAudioData(string, id, label, index, audioDimension) {
    var mode = App.config.sonification[audioDimension].mode;
    console.log(mode);
    var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(), 10);
    switch (mode) {
        case 'Simple':
            if (audioDimension === "Chord") {
                return getChordTones(mode, false, false);
            } else {
                return App.config.sonification[audioDimension].Simple.value;
            }
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
                                    return App.config.sonification[audioDimension].OperatorID.value[j];
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
                                        return App.config.sonification[audioDimension].OperatorDepth.value[j];
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
                                    return App.config.sonification[audioDimension].OperatorType.value[j];
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
                                    return App.config.sonification[audioDimension].OperatorVariable.value[j];
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
                    var tmp = App.config.sonification[audioDimension].DataIndex.value;
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
                    return App.config.sonification[audioDimension].DataIndex.value;
                }
                case 'Data-Variable':
                    var i, j;
                    for (i = 0; i <= globalAnimationList.length - 1; i++) {
                        if (globalAnimationList[i][3] === index) {
                            for (j = 0; j <= differentDataVariables.length; j++) {
                                if (globalAnimationList[i][2].split(" ")[0] === differentDataVariables[j]) {
                                    if (audioDimension === "Chord") {
                                        return getChordTones(mode, j, true);
                                    } else {
                                        return App.config.sonification[audioDimension].DataVariable.value[j];
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
                        var tmp2 = App.config.sonification[audioDimension].QueryProgress.value;
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
                        return App.config.sonification[audioDimension].QueryProgress.value;
                    }
    }
}

function audioDimensionSetup(mode, audioDimension) {
    App.config.sonification[audioDimension].mode = mode;

    switch (mode) {
        case 'None':
            $("#" + audioDimension.toLowerCase() + "Settings").hide();
            if (audioDimension === "Pitch") {
                $('#pitchSettingsExplicit').hide();
                $('#radioPitch').hide();
            }
            break;
        case 'Simple':

            if (!App.config.sonification[audioDimension].hasOwnProperty('Simple')) {
                App.config.sonification[audioDimension].Simple = {
                    value: getDefaultValue(audioDimension)
                };
            }

            var html;
            $("#" + audioDimension.toLowerCase() + "Settings").hide();
            if (audioDimension === "Pitch") {
                hidePitchElements();
                html = '';
            } else {
                $("#" + audioDimension.toLowerCase() + "Settings").empty();
                html = '<fieldset>';
            }

            html += '<h7>Select a global setting.</h7><br><br>';
            html += '<div style=overflow:hidden;>'
            html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
            html += '<div nexus-ui=select id=' + audioDimension.toLowerCase() + 'Simple style=float:left;margin-right:10px;></div>';
            html += '</div>';

            if (audioDimension === "Pitch") {
                $('#pitchSettingsExplicit').html(html);
            } else {
                html += '</fieldset>';
                $("#" + audioDimension.toLowerCase() + "Settings").html(html);
            }


            var configParameter = getConfigParameter(audioDimension);


            mySimple = new Nexus.Select("#" + audioDimension.toLowerCase() + "Simple", {
                'size': [100, 40],
                'options': App.operators[configParameter]
            });
            mySimple.value = App.config.sonification[audioDimension].Simple.value;
            if (audioDimension === "Instrument") {
                loadInstrument(instrumentSimple, false);
            }
            mySimple.on('change', function(v) {
                App.config.sonification[audioDimension].Simple.value = v.value
            });
            break;
        case 'Operator-ID':
            var html;
            $("#" + audioDimension.toLowerCase() + "Settings").hide();
            if (audioDimension === "Pitch") {
                hidePitchElements();
                html = '<hr>';
            } else {
                $("#" + audioDimension.toLowerCase() + "Settings").empty();
                html = '<fieldset>';
            }

            var i;
            var configSettings = [];
            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    html += '<h7>' + dataNodes[i].label.split("\n")[0] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Note: </p>';
                    html += '<div nexus-ui=select id=' + audioDimension.toLowerCase() + 'Operator-' + i + ' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                    configSettings.push(getDefaultValue(audioDimension));
                }
            }

            if (audioDimension === "Pitch") {
                $('#pitchSettingsExplicit').html(html);
            } else {
                html += '</fieldset>';
                $("#" + audioDimension.toLowerCase() + "Settings").html(html);
            }

            if (!App.config.sonification[audioDimension].hasOwnProperty('OperatorID')) {
                App.config.sonification[audioDimension].OperatorID = {
                    value: configSettings
                };
            }

            myOperator = [];
            var configParameter = getConfigParameter(audioDimension);

            for (i = 0; i <= dataNodes.length - 1; i++) {
                if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
                    var string = '#' + audioDimension.toLowerCase() + 'Operator-' + i;
                    myOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators[configParameter]
                    }));
                }
                myOperator[myOperator.length - 1].value = App.config.sonification[audioDimension].OperatorID.value[myOperator.length - 1];
                myOperator[instrumentOperator.length - 1].on('change', function(v) {
                    App.config.sonification[audioDimension].OperatorID.value[myOperator.length - 1] = v.value
                });
                if (audioDimension === "Instrument") {
                    loadInstrument(instrumentOperator, true);
                }
            }
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

function hidePitchElements() {
    $('#radioPitch').hide();
    $('#pitchSettingsExplicit').show();
    $('#pitchSettingsExplicit').empty();
}

function getConfigParameter(audioDimension) {
    switch (audioDimension) {
        case 'Pitch':
            return "frequence";
        case 'Instrument':
            return "instruments";
    }
}

function getDefaultValue(audioDimension) {
    switch (audioDimension) {
        case 'Pitch':
            return "C";
        case 'Instrument':
            return Object.keys(App.samples)[0];
    }
}