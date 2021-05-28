/*
    Created by Rico Klinckenberg
 */

// Set of global variables needed for the visualization and sonification
var sessionId;
// Variables for the vis network
var network, networkSon, options, container, containerSon, data, dataSon, dataNodes, dataEdges, dataSetSon,
    dataSetEdges;
// Variables for the animation
var stopFlag, pauseFlag; // Flag top stop the current animation process
var timer;
var current; //Stores the current instrument
// Variables for general data storage
var globalAnimationList = []; //Array that stores all data that is needed to be animated
var queue = [];
var prefixes = []; //Stores all prefixes from the SPARQL and RDF editor
var currentIndex;
//NexusUI Variables (graphical elements i.e. slider, etc)
var slider, toggle, instrumentSimpleSelect, instrumentSimpleSelectTone;
var selectArray = [];
var selectToneArray = [];
var selectAudio = {}
var usedInstruments = [];

const panner = new Tone.Panner(0).toDestination();
if (typeof luposdate3000_endpoint === "undefined") {
    luposdate3000_endpoint = Luposdate3000_Endpoint
}
//Initialize all interactive elements and Luposdate3000
// -> If evaluate Button is clicked
function visualisationSetup() {
    //Loading all instruments and the "none" Option
    var instrumentList = Object.keys(App.operators.instruments);
    instrumentList.push("None");
    //Load piano as default instrument
    App.samples = SampleLibrary.load({
        instruments: ['piano'],
        baseUrl: "./resources/samples/"
    });
    usedInstruments.push('piano');
    // loop through instruments and set release, connect to master output
    App.samples['piano'].release = .5;
    App.samples['piano'].toDestination();

    current = App.samples['piano'];
    current.connect(panner);

    //If network was already build up:
    //Delete current Animation List and Netowrk
    if (network != null) {
        network.destroy();
        if (networkSon != null) {
            networkSon.destroy();
        }
        globalAnimationList = [];
        $('.results-tab a').click()
        //First time initialization
    } else {
        luposdate3000_endpoint.lupos.endpoint.LuposdateEndpoint.initialize();

        //Slider for the Animation Speed
        slider = new Nexus.Slider('#sonification-animation-speed', {
            'size': [130, 30],
            'mode': 'relative', // 'relative' or 'absolute'
            'min': 40,
            'max': 1000,
            'step': 10,
            'value': 80
        });

        // Textfield for Animation Speed Slider
        // And Event Listener
        $('#sonification-animation-speed-number').val(80);
        slider.on('change', function(v) {
            $('#sonification-animation-speed-number').val(v);
        });

        //Toogle-Button for the Follow Mode
        toggle = new Nexus.Toggle('#sonification-follow', {
            'size': [40, 30],
            'state': false
        });

        createMapping();
    }

    //Show necessary elements, hide pause animation Button
    $('#luposdate3000-graph-tab').show();
    $('#luposdate3000_play').show();
    $('#luposdate3000_pause').hide();
    $('#luposdate3000_stop').hide();
    $('#luposdate3000_forward').hide();
    $('#luposdate3000_backward').hide();
}

function visualisationStart() {
    //Default: Simple Instrument Mode
    $('#instrumentAdvanced-select').show();
    //$('#instrumentSimple').prop("checked", true);
    $('#pitchDynamic').prop("checked", true);
    $('#dataSorting').prop('checked', false);

}

function createMapping() {
    var html = '';
    var i;
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        html += '<h7>' + App.operators.audioDimension[j] + ' Settings</h7><br><br>';
        html += '<div nexus-ui=select id=selectAudio-' + j + '></div><br>';
    }
    $('#instrumentAdvanced-select').html(html);
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        var i = App.operators.audioDimension[j]
        var selectAudioIdentifier = '#selectAudio-' + j;
        var options = App.operators.information
        var noValue = "None"
        if (i == "Melody") { //If Melody
            options = ["No", "Yes"]
            noValue = "No"
        }
        selectAudio[i] = new Nexus.Select(selectAudioIdentifier, {
            'size': [100, 40],
            'options': options
        });
        selectAudio[i].myIdentifier = App.mappingIdentifiers[i]
        selectAudio[i].myFunction = App.mappingFunctions[i]
        selectAudio[i].myNoValue = noValue
        selectAudio[i].on('change', function(v) { //initialise listener
            if (v.value == this.myNoValue) {
                $(this.myIdentifier).hide();
            } else {
                $(this.myIdentifier).show();
            }
            this.myFunction(v.value);
        });
        $(selectAudioIdentifier).after($(App.mappingIdentifiers[i])); //positioning of the html
    }
    selectMapping()
}

function selectMapping() {
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        var i = App.operators.audioDimension[j]
        selectAudio[i].value = App.config.sonification[i].mode
    }
}


// Event Listener
// Re-fit the canvas if the "Sonification" tab is clicked
$('#getLuposdate3000Graphlink').click(function() {
    if ($('#eval-graph-sparql').prop('checked')) {
        sleep(50).then(() => {
            network.redraw();
            network.fit();
        })
    }
});

//Event Listener
//Change the Slider value of the Animation Speed if the matching
//Textfield has changed
$('#sonification-animation-speed-number').change(function() {
    var tmp = $('#sonification-animation-speed-number');
    if (tmp.val() >= 40 && tmp.val() <= 1000) {
        slider.value = tmp.val();
    } else if (tmp.val < 40) {
        $('#sonification-animation-speed-number').mouseleave
        $('#sonification-animation-speed-number').val("40");
        slider.value = 40;
    } else if (tmp.val > 1000) {
        $('#sonification-animation-speed-number').blur();
        $('#sonification-animation-speed-number').val("1000");
        slider.value = 1000;
    }
});

$('#pitchDynamic').click(function() {
    $('#pitchDynamic').prop('checked', true);
    $('#pitchExplicit').prop('checked', false);
    $('#pitchSettingsExplicit').hide();
});

$('#pitchExplicit').click(function() {
    $('#pitchDynamic').prop('checked', false);
    $('#pitchExplicit').prop('checked', true);
    $('#pitchSettingsExplicit').show();
});

//Event Listener
//Check when the Graph Step Selector is changed if its at the minimum or maximum
//and is disabling/enabling the up/down Buttons accordingly
$("#luposdate3000_graph-select").change(function() {
    var value = $(this).val();
    var index = parseInt($('input[type=radio][name=L3Graph]:checked').val(), 10);
    if (index == 0) {
        loadData(App.logGraph[value - 1].split("SPLITHERE"), false);
    } else if (index == 1) {
        loadData(App.physGraph[value - 1].split("SPLITHERE"), false);
    }

    if (index == 0) {
        var max = App.logGraph.length;
    } else if (index == 1) {
        var max = App.physGraph.length;
    }

    if (value == max) {
        $('#luposdate3000-op-graph-down').removeClass('disabled').addClass('enabled');
        $('#luposdate3000-op-graph-up').removeClass('enabled').addClass('disabled');
    } else if (value == 1) {
        $('#luposdate3000-op-graph-up').removeClass('disabled').addClass('enabled');
        $('#luposdate3000-op-graph-down').removeClass('enabled').addClass('disabled');
    } else {
        $('#luposdate3000-op-graph-down').removeClass('disabled').addClass('enabled');
        $('#luposdate3000-op-graph-up').removeClass('disabled').addClass('enabled');
    }
});

// Event Listener
// Radio Buttons for Logical/Physical Operator Graph
// Load matching graph and change the select box
$('input[type=radio][name=L3Graph]').change(function() {
    var index = parseInt($(this).val(), 10);
    $("#luposdate3000_graph-select").val(1);
    if (index == 0) {
        var graph = App.logGraph;
        loadData(App.logGraph[1].split("SPLITHERE"), false);
    } else {
        var graph = App.physGraph;
        loadData(App.physGraph[1].split("SPLITHERE"), false);
    }
    $('#luposdate3000_graph-select').empty();
    var c = 0;
    for (var i in graph) {
        c++;
        $('#luposdate3000_graph-select').append($('<option>', {
            value: c,
            text: c
        }));
    }

});

$('#getLuposdate3000Graphlink').click(function() {
    sleep(50).then(() => {
        network.redraw();
        network.fit();
    })
});


$('#getLuposdate3000GraphSonlink').click(function() {
    sleep(50).then(() => {
        loadData(App.physGraph[App.physGraph.length - 1].split("SPLITHERE"), true);
    })
});

//Event Listener
//Change select menu when down button is clicked
$('#luposdate3000-op-graph-down').click(function() {
    var value = parseInt($('#luposdate3000_graph-select').val(), 10);
    if (value != 1)
        value--;
    $('#luposdate3000_graph-select').val(value);
    $('#luposdate3000_graph-select').trigger('change');
});

//Event Listener
//Change select menu when up button is clicked
$('#luposdate3000-op-graph-up').click(function() {
    var value = parseInt($('#luposdate3000_graph-select').val(), 10);
    var index = parseInt($('input[type=radio][name=L3Graph]:checked').val(), 10);
    if (index == 0) {
        var max = App.logGraph.length;
    } else if (index == 1) {
        var max = App.physGraph.length;
    }
    if (value != max)
        value++;
    $('#luposdate3000_graph-select').val(value)
    $('#luposdate3000_graph-select').trigger('change')
});

//Event Listener
// Trigger animation method when play button is pressed.
$('#luposdate3000_play').click(function() {
    if (!pauseFlag) {
        queue = globalAnimationList.map(function(arr) {
            return arr.slice();
        });
        currentIndex = 0;
    }
    if (stopFlag) {
        var i;
        for (i = 0; i <= dataSetEdges.getIds().length - 1; i++) {
            var edgeId = dataSetEdges.getIds()[i];
            var newWidth = 1;
            dataSetEdges.update([{
                id: edgeId,
                width: newWidth
            }]);
        }
    }
    $('#getLuposdate3000Graphlink').css("pointer-events", "none");
    $('#luposdate3000_stop').show();
    $('#luposdate3000_play').hide();
    $('#luposdate3000_pause').show();
    $('#luposdate3000_forward').show();
    $('#luposdate3000_backward').show();
    animation(queue);
    $('#luposdate3000_forward').prop('disabled', true);
    $('#luposdate3000_backward').prop('disabled', true);
    stopFlag = false;
    pauseFlag = false;
});

$('#dataSorting').click(function() {
    App.config.sonification = {
        "Pitch": {
            "mode": "Data-Index",
        },
        "Instrument": {
            "mode": "None",
        },
        "Loudness": {
            "mode": "None",
        },
        "Spatialization": {
            "mode": "Query-Progress",
        },
        "Duration": {
            "mode": "None",
        },
        "Melody": {
            "mode": "No",
        },
        "Chord": {
            "mode": "None",
        },
        "Octave": {
            "mode": "None",
        },
    }
    selectMapping()
});

$('#joinHighlight').click(function() {
    App.config.sonification = {
        "Pitch": {
            "mode": "None",
        },
        "Instrument": {
            "mode": "Operator-Type",
        },
        "Loudness": {
            "mode": "None",
        },
        "Spatialization": {
            "mode": "None",
        },
        "Duration": {
            "mode": "None",
        },
        "Melody": {
            "mode": "No",
        },
        "Chord": {
            "mode": "None",
        },
        "Octave": {
            "mode": "None",
        },
    }
    selectMapping()
    var i;
    for (i = 0; i <= instrumentOperatorType.length - 1; i++) {
        if (differentTypes[i].includes("TripleStore")) {
            instrumentOperatorType[i].value = 'guitar-acoustic';
        } else if (differentTypes[i].includes("Join")) {
            instrumentOperatorType[i].value = 'xylophone';
        } else {
            instrumentOperatorType[i].value = 'piano';
        }
    }
})

$('#reset').click(function() {
    resetAllSonificationSettings()
})

function resetAllSonificationSettings() {
    App.config.sonification = {
        "Pitch": {
            "mode": "None",
        },
        "Instrument": {
            "mode": "None",
        },
        "Loudness": {
            "mode": "None",
        },
        "Spatialization": {
            "mode": "None",
        },
        "Duration": {
            "mode": "None",
        },
        "Melody": {
            "mode": "No",
        },
        "Chord": {
            "mode": "None",
        },
        "Octave": {
            "mode": "None",
        },
    }
    selectMapping()
    $('#dataSorting').prop('checked', false);
    $('#joinHighlight').prop('checked', false);
    $('#dataVariableDepth').prop('checked', false);
}

$('#dataVariableDepth').click(function() {
    App.config.sonification = {
        "Pitch": {
            "mode": "Data-Variable",
        },
        "Instrument": {
            "mode": "None",
        },
        "Loudness": {
            "mode": "None",
        },
        "Spatialization": {
            "mode": "None",
        },
        "Duration": {
            "mode": "Operator-Depth",
        },
        "Melody": {
            "mode": "No",
        },
        "Chord": {
            "mode": "None",
        },
        "Octave": {
            "mode": "None",
        },
    }
    selectMapping()
    var i;
    for (i = 0; i <= differentDataVariables.length - 1; i++) {
        pitchDataVariable[i].value = App.operators.frequence[i * 2];
    }
    $('#pitchExplicit').prop('checked', true);
    for (i = 0; i <= differentPositions.length - 1; i++) {
        switch (i) {
            case 0:
                durationOperatorDepth[i].value = '16n';
                break;
            case 1:
                durationOperatorDepth[i].value = '8n';
                break;
            case 2:
                durationOperatorDepth[i].value = '4n';
                break;
            case 3:
                durationOperatorDepth[i].value = '2n';
                break;
            default:
                durationOperatorDepth[i].value = '1n';
                break;
        }
    }
});

//Event Listener
// Set stopFlag to stop Animation when stop button is pressed
$('#luposdate3000_stop').click(function() {
    $('#getLuposdate3000Graphlink').css("pointer-events", "");
    $('#luposdate3000_stop').hide();
    $('#luposdate3000_play').show();
    $('#luposdate3000_pause').hide();
    $('#luposdate3000_forward').hide();
    $('#luposdate3000_backward').hide();
    $('.meter').css("width", 0 + "%");
    pauseFlag = false;
    stopFlag = true;
    var i;
    for (i = 0; i <= dataSetEdges.getIds().length - 1; i++) {
        var edgeId = dataSetEdges.getIds()[i];
        var newWidth = 1;
        dataSetEdges.update([{
            id: edgeId,
            width: newWidth
        }]);
    }
    if (dataSet.getIds().includes(999)) {
        dataSet.remove(999);
    }
    $('#luposdate3000_forward').prop('disabled', true);
    $('#luposdate3000_backward').prop('disabled', true);
});

$('#luposdate3000_pause').click(function() {
    $('#luposdate3000_stop').show();
    $('#luposdate3000_play').show();
    $('#luposdate3000_pause').hide();
    $('#luposdate3000_forward').show();
    $('#luposdate3000_backward').show();
    pauseFlag = true;
    $('#luposdate3000_forward').prop('disabled', false);
    $('#luposdate3000_backward').prop('disabled', false);

});

$('#luposdate3000_forward').click(function() {
    dataSet.remove(999);
    queue.shift();
    currentIndex++;
    var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
    $('.meter').css("width", tmp + "%");
    addNewNode();
});

$('#luposdate3000_backward').click(function() {
    dataSet.remove(999);
    queue = globalAnimationList.map(function(arr) {
        return arr.slice();
    });
    currentIndex--;
    queue = queue.slice(currentIndex);
    var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
    $('.meter').css("width", tmp + "%");
    addNewNode();
});

//Creates a table on the result Tab and show the results from the query
function calculateResult() {
    App.processResults(App.result, "sparql")
}

//Function to pause the thread.
//Needed as javascript is asynchronous
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

//Goes through all prefixes from the SPARQL and RDF editor and replaces the data within
//the current saved data.
function replacePrefix() {
    prefixes = [];
    var i;
    var rdfPrefix = App.parseRDFPrefixes(App.cm['rdf'].getValue());
    var SPARQLPrefix = App.parseSPARQLPrefixes(App.cm['sparql'].getValue());
    for (i = 0; i <= Object.keys(rdfPrefix).length - 1; i++) {
        var tmp = [Object.keys(rdfPrefix)[i], Object.values(rdfPrefix)[i]];
        prefixes.push(tmp);
    }
    for (i = 0; i <= Object.keys(SPARQLPrefix).length - 1; i++) {
        var tmp = [Object.keys(SPARQLPrefix)[i], Object.values(SPARQLPrefix)[i]];
        prefixes.push(tmp);
    }

    var j;
    for (j = 0; j <= globalAnimationList.length - 1; j++) {
        for (i = 0; i <= prefixes.length - 1; i++) {
            if (globalAnimationList[j][2].includes(prefixes[i][1])) {
                var string = globalAnimationList[j][2].replaceAll(prefixes[i][1], "" + prefixes[i][0] + ":");
                string = string.replaceAll("<", "");
                string = string.replaceAll(">", "");
                globalAnimationList[j][2] = string;
            }
        }
    }
    for (j = 0; j <= dataNodes.length - 1; j++) {
        for (i = 0; i <= prefixes.length - 1; i++) {
            if (dataNodes[j].label.includes(prefixes[i][1])) {
                var string = dataNodes[j].label.replaceAll(prefixes[i][1], "" + prefixes[i][0] + ":");
                string = string.replaceAll("<", "");
                string = string.replaceAll(">", "");
                dataNodes[j].label = string;
            }
        }
    }
    calculateResult();
}

//Function that is getting called by the Luposdate3000 Library and pushing the data
//to animate
function receiveAnimation(childUUID, parentUUID, string, index) {
    //globalAnimationList.push([childUUID, parentUUID, string, index]);
}

function playNoteMapping(id, label, index) {
    var pitchType = App.config.sonification.Pitch.mode;
    var instrumentType = App.config.sonification.Instrument.mode;
    var loudnessType = App.config.sonification.Loudness.mode;
    var spatializationType = App.config.sonification.Spatialization.mode;
    var durationType = App.config.sonification.Duration.mode;
    var melodyType = App.config.sonification.Melody.mode;
    var chordType = App.config.sonification.Chord.mode;
    var octaveType = App.config.sonification.Octave.mode;

    var tone, velocity, duration, octave;

    current = App.samples[getInstrument(instrumentType, id, label, index)];

    //Loudness of the Tone
    if (loudnessType != 'None') {
        velocity = getLoudness(loudnessType, id, label, index)
    } else {
        velocity = 1;
    }

    //Spatialization
    if (spatializationType != 'None') {
        panner.pan.value = getSpatialization(spatializationType, id, label, index);
    } else {
        panner.pan.value = 0;
    }

    //Duration
    if (durationType != 'None') {
        duration = getDuration(durationType, id, label, index);
    } else {
        duration = '4n';
    }

    //Octave
    var b1 = pitchType == 'Operator-ID' && $('#pitchDynamic').is(':checked');
    var b2 = pitchType == 'Operator-Depth' && $('#pitchDynamic').is(':checked');
    var b3 = pitchType == 'Data-Index';
    var b4 = pitchType == 'Query-Progress';
    var b5 = chordType == 'None' && melodyType == 'No';
    if ((b1 || b2 || b3 || b4) && b5) {
        octave = '';
    } else {
        if (octaveType != 'None') {
            octave = getOctave(octaveType, id, label, index);
        } else {
            octave = '3';
        }
    }

    //What Pitch, or maybe what Chord
    //If Chord is selected the pitch settings will be ignored/overwritten
    if (melodyType == 'No') {
        if (chordType != 'None') {
            tone = getChord(chordType, id, label, index);
        } else if (pitchType != 'None') {
            tone = getPitch(pitchType, id, label, index);
            current.connect(panner).triggerAttackRelease(tone + octave, duration, "+0", velocity);
        } else {
            current.connect(panner).triggerAttackRelease('C' + octave, duration, "+0", velocity);
        }
    } else {
        tone = getMelody(melodyType, id, label, index);
    }

    if (melodyType != 'No' || chordType != 'None') {
        if (tone[0]) {
            current.connect(panner).triggerAttackRelease(tone[1] + octave, duration, "+0", velocity);
            current.connect(panner).triggerAttackRelease(tone[2] + octave, duration, "+0.15", velocity);
            current.connect(panner).triggerAttackRelease(tone[3] + octave, duration, "+0.3", velocity);
        } else {
            var chord = [tone[1] + octave, tone[2] + octave, tone[3] + octave];
            current.connect(panner).triggerAttackRelease(chord, duration, "+0", velocity);
        }
    }
}

function addNewNode() {
    var positions = networkSon.getPositions();
    var x_start = positions[queue[0][0]].x,
        y_start = positions[queue[0][0]].y - 32;
    dataSet.add([{
        id: 999,
        label: queue[0][2],
        group: 1
    }]);
    networkSon.moveNode(999, x_start, y_start);
    if (toggle.state) {
        networkSon.focus("999", {
            scale: 1,
            offset: {
                x: 0,
                y: 0
            },
            locked: true,
        });
    } else {
        networkSon.fit();
    }
    playNoteMapping(dataNodes.find(element => element.id == queue[0][0]).id, dataNodes.find(element => element.id == queue[0][0]).label, queue[0][3]);
}

//Function that handles the animation for one data triple from one operator to the other
async function animation(queue) {

    //Set focus to new node if Follow-Mode is active


    // Parameters for the animation
    var positions = networkSon.getPositions();
    var val = slider.max + slider.min - slider.value;
    var k = 0,
        lambda = 0,
        tick = 10,
        totalTime = val;
    var x_start = positions[queue[0][0]].x,
        y_start = positions[queue[0][0]].y - 32;
    var nrOfSteps = Math.floor(totalTime / tick);

    if (!pauseFlag) {
        addNewNode();
    }

    //Wait a few miliseconds and then move the node a bit
    sleep(val / 10).then(() => {
        timer = setInterval(function() {

            this.pause

            k++;
            var l = k / nrOfSteps;

            // get target positions
            var x_target = positions[queue[0][1]].x;
            var y_target = positions[queue[0][1]].y + 32;

            // compute the convex combination of x_start and x_target to find intermediate x and move node to it, same for y
            var xt = x_start * (1 - l) + x_target * l;
            var yt = y_start * (1 - l) + y_target * l;

            // move node
            networkSon.moveNode(999, xt, yt);

            // stop if we have reached nr of steps
            if (k == nrOfSteps) {
                clearInterval(timer)
            }

            //If node reached the end point
            if (k == totalTime / tick && !pauseFlag) {
                sleep(val / 2).then(() => {
                    //Delete node, delete data triple in the queue and change the progress meter
                    networkSon.releaseNode();
                    dataSet.remove(999);
                    updateEdgeSize(queue[0][0], queue[0][1]);
                    queue.shift();
                    currentIndex++;
                    var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                    if (!stopFlag) {
                        $('.meter').css("width", tmp + "%");
                    } else {
                        $('.meter').css("width", 0 + "%");
                        queue = [];
                    }

                    //call animation method again if queue is not empty
                    //otherwise set stopFlag to stop animation
                    if (queue.length != 0) {
                        animation(queue);
                    } else {
                        networkSon.fit();
                        $('#luposdate3000_play').show();
                        $('#luposdate3000_stop').hide();
                        $('#luposdate3000_pause').hide();
                        stopFlag = true;
                    }
                })
            }

        }, tick);
    })
}

function updateEdgeSize(startID, endID) {
    var i;
    var edgeId;
    for (i = 0; i <= dataSetEdges.getIds().length - 1; i++) {
        if (dataSetEdges.get(dataSetEdges.getIds()[i]).from == startID && dataSetEdges.get(dataSetEdges.getIds()[i]).to == endID) {
            edgeId = dataSetEdges.get(dataSetEdges.getIds()[i]).id;
            var updateStep = 15 / globalAnimationList.length;
            var newWidth = dataSetEdges.get(dataSetEdges.getIds()[i]).width + updateStep;
            if (newWidth > 15) {
                newWidth = 15;
            }
            dataSetEdges.update([{
                id: edgeId,
                width: newWidth
            }]);
        }
    }
}

//This function removes the POPRico Operator and is recalculating the edges that are going out
//and in to this operator.
function deleteDebugOperator() {
    var i;
    var tmp = [];

    for (i = 0; i <= dataNodes.length - 1; i++) {
        if (dataNodes[i].label.includes('POPVisualisation')) {
            tmp.push(dataNodes[i].id);
            var fromV;
            var toV;
            var j;
            for (j = 0; j <= dataEdges.length - 1; j++) {
                if (dataEdges[j].from == dataNodes[i].id) {
                    toV = dataEdges[j].to;
                } else if (dataEdges[j].to == dataNodes[i].id) {
                    fromV = dataEdges[j].from;
                }
            }
            dataEdges.push({
                from: fromV,
                to: toV,
                width: 2
            });
        }
    }

    var tmpFlag = true;
    while (tmpFlag) {
        for (i = 0; i <= dataEdges.length - 1; i++) {
            if (tmp.includes(dataEdges[i].from)) {
                dataEdges.splice(i, 1);
                tmpFlag = true;
                break;
            } else if (tmp.includes(dataEdges[i].to)) {
                dataEdges.splice(i, 1);
                tmpFlag = true;
                break;
            }
            tmpFlag = false;
        }
    }

    tmpFlag = true;
    while (tmpFlag) {
        for (i = 0; i <= dataNodes.length - 1; i++) {
            if (dataNodes[i].label.includes('POPVisualisation')) {
                dataNodes.splice(i, 1);
                tmpFlag = true;
                break;
            }
            tmpFlag = false;
        }
    }

}

//Loads the Nodes and Edges from the data provided
function loadData(dataParameter, flag) {
    dataNodes = JSON.parse(dataParameter[0]);
    dataEdges = JSON.parse(dataParameter[1]);
    //remove the POPRico operator
    deleteDebugOperator();
    data = {
        nodes: dataNodes,
        edges: dataEdges,
    };
    replacePrefix();
    //draw the network
    draw(flag);
}

function testAjax() {
    var inputValue = App.cm['sparql'].getValue();

    var formData = {
        'query': inputValue,
        'evaluator': "XML_STREAM",
    };
    $.ajax({
            type: 'POST',
            url: '/sparql/query',
            data: formData
        })
        .done(function(data) {
            App.resultValue = data;
        });
    return App.resultValue;
}


function addCustomContextMenu(networkObject, contextFlag) {
    var string;
    if (contextFlag) {
        string = "luposdate3000OPSon";
    } else {
        string = "luposdate3000OP";
    }
    //Apply Event Listener for the right click menus
    networkObject.on("oncontext", function(params) {
        var x, y;
        //If right click is made within the canvas, open custom context menu
        if (typeof params.nodes[0] != 'undefined') {
            document.getElementById(string).addEventListener('contextmenu', function(e) {
                // Alternative
                e.preventDefault();
                x = e.clientX;
                y = e.clientY;
                $("#rkm").css("left", x + "px");
                $("#rkm").css("top", y + "px");
                $("#rkm").show();
            }, false);

            //Event Listener, if mouse is pressed outside the custom context menu
            document.getElementById(string).addEventListener('mousedown', function(e) {
                if (!(e.clientX >= x && e.clientX <= (x + $("#rkm").width()) && e.clientY >= y && e.clientY <= (y + $("#rkm").height()))) {
                    $('#rkm').hide();
                }
            }, false);

            //Event Listener that closes the custom context menu if page is scrolled
            $(window).scroll(function() {
                $('#rkm').hide();
            });

            var elem = document.getElementById('luposdate3000_lastOp');
            elem.replaceWith(elem.cloneNode(true));

            elem = document.getElementById('luposdate3000_nextOp');
            elem.replaceWith(elem.cloneNode(true));

            document.getElementById("luposdate3000_nextOp").addEventListener('click', function(e) {
                var i;
                for (i = 1; i <= queue.length - 1; i++) {
                    if (queue[i][0] == params.nodes[0]) {
                        dataSet.remove(999);
                        queue = queue.slice(i);
                        currentIndex += i;
                        var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                        $('.meter').css("width", tmp + "%");
                        addNewNode();
                        break;
                    }
                }

            }, false);

            document.getElementById("luposdate3000_lastOp").addEventListener('click', function(e) {
                var i;
                queue = globalAnimationList.map(function(arr) {
                    return arr.slice();
                });
                for (i = currentIndex - 1; i >= 0; i--) {
                    if (queue[i][0] == params.nodes[0]) {
                        dataSet.remove(999);
                        queue = queue.slice(i);
                        currentIndex = i;
                        var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
                        $('.meter').css("width", tmp + "%");
                        addNewNode();
                        break;
                    }
                }

            }, false);

            //Creates a new color picker that shows a palette of pre-defined colors and
            //changes the node that is contained in the param-value of the event listener
            //accordingly
            $("#colorpicker").spectrum({
                showPaletteOnly: true,
                togglePaletteOnly: false,
                color: 'blanchedalmond',
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ],
                change: function(color) {
                    if (dataSet.getIds().includes(params.nodes[0])) {
                        dataSet.update({
                            id: params.nodes[0],
                            color: "rgb(" + color._r + "," + color._g + "," + color._b + ")"
                        });
                    }
                }
            });
        }
    });
}

//Draws the graph using the vis.js framework
function draw(flag) {
    if (flag) {
        container = document.getElementById("luposdate3000OPSon");
    } else {
        container = document.getElementById("luposdate3000OP");
    }

    //DataSet containing Nodes and Edges
    dataSet = new vis.DataSet(dataNodes);
    dataSetEdges = new vis.DataSet(dataEdges);
    data = {
        nodes: dataSet,
        edges: dataSetEdges,
    };

    //Options that apply for the network
    options = {
        autoResize: false,
        nodes: {
            physics: false,
            shape: "box",
            font: {
                size: 14,
                color: "#000000",
                face: "Ubuntu",
            },
            borderWidth: 1,
            color: {
                background: "#E1E1E1",
                border: "#000000",
                hover: {
                    background: "#C5C5C5",
                    border: "#000000",
                },
                highlight: {
                    background: "#C5C5C5",
                    border: "#000000",
                },

            },

        },
        edges: {
            smooth: false
        },
        physics: {
            enabled: true,
            hierarchicalRepulsion: {
                nodeDistance: 200,
                avoidOverlap: 1
            },
        },
        layout: {
            improvedLayout: true,
            hierarchical: {
                levelSeparation: 100,
                nodeSpacing: 300,
                treeSpacing: 200,
            }
        },
        interaction: {
            hover: true
        }
    };

    //Draw network
    if (flag) {
        networkSon = new vis.Network(container, data, options);
        addCustomContextMenu(networkSon, flag);

        //By default, the network is dynamic and is changing via a constant animation
        //For the hierarchical layout it is essential that is is turned off after the build-up
        //process.
        setTimeout(() => {
            networkSon.setOptions({
                layout: {
                    hierarchical: false
                },
            });

            container.style.display = "inline-block";
            networkSon.fit();
        }, 10);
    } else {
        network = new vis.Network(container, data, options);
        //Apply Event Listener for the right click menus
        addCustomContextMenu(network, flag);

        //By default, the network is dynamic and is changing via a constant animation
        //For the hierarchical layout it is essential that is is turned off after the build-up
        //process.
        setTimeout(() => {
            network.setOptions({
                layout: {
                    hierarchical: false
                },
            });

            container.style.display = "inline-block";
            network.fit();
        }, 10);
    }
}