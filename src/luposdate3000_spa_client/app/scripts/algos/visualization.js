/*
    Created by Rico Klinckenberg
 */

// Set of global variables needed for the visualization and sonification
var sessionId;
// Variables for the vis network
var network, networkSon, options, container, containerSon, data, dataSon, dataNodes, dataEdges, dataSetSon, dataSetEdges;
// Variables for the animation
var stopFlag, pauseFlag; // Flag top stop the current animation process
var contextMenuFlag = false;
var timer;
var current; //Stores the current instrument
// Variables for general data storage
var queue = [];
var prefixes = []; //Stores all prefixes from the SPARQL and RDF editor
var currentIndex;
//NexusUI Variables (graphical elements i.e.  etc)
var toggle, instrumentSimpleSelect, instrumentSimpleSelectTone;
var selectArray = [];
var selectToneArray = [];
var selectAudio = {}
var usedInstruments = [];
const panner = new Tone.Panner(0).toDestination();
var hoverNodeId;

function initLuposdate3000() { //first time initialization - called by main.coffee, before anything else is loaded
    if (typeof luposdate3000_endpoint === "undefined") {
        luposdate3000_endpoint = Luposdate3000_Endpoint
    }
}

function setAnimationFlags(stopF, pauseF) {
    stopFlag = stopF
    pauseFlag = pauseF
    if (stopFlag) {
        $('#luposdate3000_stop').hide();
        $('#luposdate3000_play').show();
        $('#luposdate3000_pause').hide();
        $('#luposdate3000_forward').hide();
        $('#luposdate3000_backward').hide();
        $('#luposdate3000_forward').prop('disabled', true);
        $('#luposdate3000_backward').prop('disabled', true);
        currentIndex = 0
    } else if (pauseFlag) {
        $('#luposdate3000_stop').show();
        $('#luposdate3000_play').show();
        $('#luposdate3000_pause').hide();
        $('#luposdate3000_forward').show();
        $('#luposdate3000_backward').show();
        $('#luposdate3000_forward').prop('disabled', currentIndex >= queue.length);
        $('#luposdate3000_backward').prop('disabled', currentIndex <= 0);
    } else {
        $('#luposdate3000_stop').show();
        $('#luposdate3000_play').hide();
        $('#luposdate3000_pause').show();
        $('#luposdate3000_forward').show();
        $('#luposdate3000_backward').show();
        $('#luposdate3000_forward').prop('disabled', true);
        $('#luposdate3000_backward').prop('disabled', true);
    }
}

function initVisualization() { //first time initialization - called by main.coffee, after configuration is loaded
    var slider = new Nexus.Slider('#sonification-animation-speed', {
        'size': [130, 30],
        'mode': 'absolute', // 'relative' or 'absolute'
        'min': 40,
        'max': 990,
        'step': 10,
        'value': App.config.animationSpeed
    });
    slider.on('change', function(v) {
        App.config.animationSpeed = v
    });
    toggle = new Nexus.Toggle('#sonification-follow', { //TODO config file
        'size': [130, 30],
        'state': false
    });
    var html = '';
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        html += '<h7>' + App.operators.audioDimension[j] + ' Settings</h7><br><br>';
        html += '<div nexus-ui=select id=selectAudio-' + j + '></div><br>';
    }
    $('#instrumentAdvanced-select').html(html);
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        var i = App.operators.audioDimension[j]
        var selectAudioIdentifier = '#selectAudio-' + j;
        selectAudio[i] = new Nexus.Select(selectAudioIdentifier, {
            'size': [290, 40],
            'options': App.operators.information[i].values
        });
        selectAudio[i].myIdentifier = App.mappingIdentifiers[i]
        selectAudio[i].myFunction = App.mappingFunctions[i]
        selectAudio[i].myNoValue = App.operators.information[i].standard
        selectAudio[i].on('change', function(v) {
            if (v.value == this.myNoValue) {
                $(this.myIdentifier).hide();
            } else {
                $(this.myIdentifier).show();
            }
            this.myFunction(v.value);
        });
        $(selectAudioIdentifier).after($(App.mappingIdentifiers[i]));
    }
    setAnimationFlags(true, false)
}


function selectMapping() { //applies the current settings to the settings-menu-items
    for (j = 0; j <= App.operators.audioDimension.length - 1; j++) {
        var i = App.operators.audioDimension[j]
        selectAudio[i].value = App.config.sonification[i].mode
    }
}
$("a[href=#luposdate3000-graph-tab]").click(function() {
    if (App.config.evalGraphSparql) {
        sleep(50).then(() => {
            network.redraw();
            network.fit();
        })
    }
});


//Event Listener
//Check when the Graph Step Selector is changed if its at the minimum or maximum
//and is disabling/enabling the up/down Buttons accordingly
$("#luposdate3000_graph-select").change(function() {
    var value = $(this).val();
    var index = parseInt($('input[type=radio][name=L3Graph]:checked').val(), 10);
    if (index == 0) {
        loadData(App.logGraph[value - 1], false);
        var max = App.logGraph.length;
    } else if (index == 1) {
        loadData(App.physGraph[value - 1], false);
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
        loadData(App.logGraph[1], false);
    } else {
        var graph = App.physGraph;
        loadData(App.physGraph[1], false);
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


$("a[href=#luposdate3000-sonification-tab]").click(function() {
    sleep(50).then(() => {
        loadData(App.physGraph[App.physGraph.length - 1], true);
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
            "Operator-Type": {
                value: ['guitar-acoustic', 'xylophone', 'piano']
            }
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
})

$('#reset').click(function() {
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
})

$('#dataVariableDepth').click(function() {
    App.config.sonification = {
        "Pitch": {
            "mode": "Data-Variable",
            "Data-Variable": {
                value: App.operators.Pitch.values
            }
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
            "Operator-Depth": {
                value: ["16n", "8n", "4n", "2n", "1n"]
            }
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

//Event Listener
// Trigger animation method when play button is pressed.
$('#luposdate3000_play').click(function() {
    setAnimationFlags(false, false)
    resetEdges();
    animation(true);
});

function resetEdges() {
    for (i = 0; i <= dataSetEdges.getIds().length - 1; i++) {
        edgeId = dataSetEdges.get(dataSetEdges.getIds()[i]).id;
        dataSetEdges.update([{
            id: edgeId,
            width: 1
        }]);
    }
}

function resetAnimationQueue() {
    $('.meter').css("width", 0 + "%");
    queue = App.globalAnimationList.map(function(arr) {
        return arr.slice();
    });

    currentIndex = 0;
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

//Event Listener
// Set stopFlag to stop Animation when stop button is pressed
$('#luposdate3000_stop').click(function() {
    resetAnimationQueue()
    setAnimationFlags(true, false)
});

$('#luposdate3000_pause').click(function() {
    setAnimationFlags(false, true)
});

$('#luposdate3000_forward').click(function() {
    currentIndex++
    animation(false)
});

$('#luposdate3000_backward').click(function() {
    currentIndex -= 1;
    animation(false)
    sleep(30).then(() => {
        setAnimationFlags(false, true)
    });
});

//Function to pause the thread.
//Needed as javascript is asynchronous
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

//Goes through all prefixes from the SPARQL and RDF editor and replaces the data within
//the current saved data.
function replacePrefix() { //
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
    for (j = 0; j <= App.globalAnimationList.length - 1; j++) {
        for (i = 0; i <= prefixes.length - 1; i++) {
            if (App.globalAnimationList[j][2].includes(prefixes[i][1])) {
                var string = App.globalAnimationList[j][2].replaceAll(prefixes[i][1], "" + prefixes[i][0] + ":");
                string = string.replaceAll("<", "");
                string = string.replaceAll(">", "");
                App.globalAnimationList[j][2] = string;
            }
        }
    }
for(s=0;s<App.logGraph.length;s++){
replacePrefixHelper(App.logGraph[s].nodes,prefixes)
}
for(s=0;s<App.physGraph.length;s++){
replacePrefixHelper(App.physGraph[s].nodes,prefixes)
}
    selectMapping()
    App.processResults(App.result, "sparql");
}
function replacePrefixHelper(nodes,prefixes) { //
    for (j = 0; j <= nodes.length - 1; j++) {
        for (i = 0; i <= prefixes.length - 1; i++) {
            if (nodes[j].label.includes(prefixes[i][1])) {
                var string = nodes[j].label.replaceAll(prefixes[i][1], "" + prefixes[i][0] + ":");
                string = string.replaceAll("<", "");
                string = string.replaceAll(">", "");
                nodes[j].label = string;
            }
        }
    }
}
//Avoid buffer problem when instrument files are not yet loaded
function triggerNote(chord, duration, delay, velocity, currentname) {
    if (typeof App.samples[currentname] === "undefined") {
        App.samples[currentname] = new Tone.Sampler(
            SampleLibrary[currentname], {
                baseUrl: "./resources/samples/" + currentname + "/",
                onload: null
            })
        App.samples[currentname].release = .5;
        App.samples[currentname].toDestination();
    }
    var current = App.samples[currentname];
    if (current._buffers.loaded)
        try {
            current.connect(panner).triggerAttackRelease(chord, duration, delay, velocity);
        } catch (error) {}
    else {
        sleep(100).then(() => {
            triggerNote(chord, duration, delay, velocity, currentname);
        });
    }
}

function addNewNode() {
    var queueHead = queue[currentIndex]
    var positions = networkSon.getPositions();
    var x_start = positions[queueHead[0]].x,
        y_start = positions[queueHead[0]].y - 32;
    try {
        dataSet.add([{
            id: 999,
            label: queueHead[2],
            group: 1
        }]);
    } catch (error) {
        //this may happen if you click "next" to fast
    }
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
    var id = dataNodes.find(element => element.id == queueHead[0]).id
    var label = dataNodes.find(element => element.id == queueHead[0]).label
    var index = queueHead[3]
    var pitchType = App.config.sonification.Pitch.mode;
    var melodyType = App.config.sonification.Melody.mode;
    var chordType = App.config.sonification.Chord.mode;

    var tone, velocity, duration, octave;

    var currentname = getAudioData(idMappings, id, label, index, "Instrument");

    velocity = getAudioData(idMappings, id, label, index, "Loudness")

    panner.pan.value = getAudioData(idMappings, id, label, index, "Spatialization");

    duration = getAudioData(idMappings, id, label, index, "Duration");

    //Octave
    var b1 = pitchType == 'Operator-ID' && pitchType == "Dynamic";
    var b2 = pitchType == 'Operator-Depth' && pitchType == "Dynamic";
    var b3 = pitchType == 'Data-Index';
    var b4 = pitchType == 'Query-Progress';
    var b5 = chordType == 'None' && melodyType == 'No';
    if ((b1 || b2 || b3 || b4) && b5) {
        octave = '';
    } else {
        octave = getAudioData(idMappings, id, label, index, "Octave");
    }

    //What Pitch, or maybe what Chord
    //If Chord is selected the pitch settings will be ignored/overwritten
    if (melodyType == 'No') {
        if (chordType != 'None') {
            tone = getAudioData(idMappings, id, label, index, "Chord");
        } else if (pitchType == 'Dynamic Operator-ID' || pitchType == 'Dynamic Operator-Depth') {
            tone = getAudioData(idMappings, id, label, index, "Pitch");
            triggerNote(tone, duration, "+0", velocity, currentname)
        } else if (pitchType != 'None') {
            tone = getAudioData(idMappings, id, label, index, "Pitch");
            triggerNote(tone + octave, duration, "+0", velocity, currentname)
        } else {
            triggerNote('C' + octave, duration, "+0", velocity, currentname)
        }
    } else {
        tone = getMelody(melodyType, id, label, index);
    }

    if (melodyType != 'No' || chordType != 'None') {
        if (tone[0]) {
            triggerNote(tone[1] + octave, duration, "+0", velocity, currentname);
            triggerNote(tone[2] + octave, duration, "+0.15", velocity, currentname);
            triggerNote(tone[3] + octave, duration, "+0.3", velocity, currentname);
        } else {
            var chord = [tone[1] + octave, tone[2] + octave, tone[3] + octave];
            triggerNote(chord, duration, "+0", velocity, currentname);
        }
    }
}

//Function that handles the animation for one data triple from one operator to the other
async function animation(loop) {
    if (queue.length > currentIndex && currentIndex >= 0) {
        if ((!pauseFlag && !stopFlag) || !loop) {
            if (!loop) {
                $('#luposdate3000_forward').prop('disabled', true);
                $('#luposdate3000_backward').prop('disabled', true);
            }
            var queueHead = queue[currentIndex]
            var positions = networkSon.getPositions();
            var k = 0;
            var lambda = 0;
            var tick = 10;
            var x_start = positions[queueHead[0]].x;
            var y_start = positions[queueHead[0]].y - 32;
            var inverseSpeed = 1000 - App.config.animationSpeed
            var nrOfSteps = Math.floor(inverseSpeed / tick);

            addNewNode();

            //Wait a few miliseconds and then move the node a bit
            sleep(tick).then(() => {
                timer = setInterval(function() {

                    this.pause

                    k++;
                    var l = k / nrOfSteps;

                    // get target positions
                    var x_target = positions[queueHead[1]].x;
                    var y_target = positions[queueHead[1]].y + 32;

                    // compute the convex combination of x_start and x_target to find intermediate x and move node to it, same for y
                    var xt = x_start * (1 - l) + x_target * l;
                    var yt = y_start * (1 - l) + y_target * l;

                    // move node
                    try {
                        networkSon.moveNode(999, xt, yt);
                    } catch (error) {
                        //if there is a new query executed, this may happen
                    }

                    if (k == nrOfSteps) {
                        clearInterval(timer)
                        sleep(tick).then(() => {
                            //Delete node, delete data triple in the queue and change the progress meter
                            networkSon.releaseNode();
                            try {
                                dataSet.remove(999);
                            } catch (error) {
                                //if there is a new query executed, this may happen
                            }
                            updateEdgeSize(queueHead[0], queueHead[1]);
                            if (loop) {
                                if (contextMenuFlag) {
                                    contextMenuFlag = false;
                                    animation(true);
                                } else {
                                    currentIndex++;
                                    animation(true);
                                }
                            } else {
                                setAnimationFlags(false, true)
                            }
                            var tmp = (currentIndex / App.globalAnimationList.length) * 100;
                            if (!stopFlag) {
                                $('.meter').css("width", tmp + "%");
                            } else {
                                $('.meter').css("width", 0 + "%");
                            }
                        })
                    }

                }, tick);
            })
        }
    } else {
        networkSon.fit();
        setAnimationFlags(true, false)
    }
}

function updateEdgeSize(startID, endID) { //only called by animation
    var i;
    var edgeId;
    for (i = 0; i <= dataSetEdges.getIds().length - 1; i++) {
        if (dataSetEdges.get(dataSetEdges.getIds()[i]).from == startID && dataSetEdges.get(dataSetEdges.getIds()[i]).to == endID) {
            edgeId = dataSetEdges.get(dataSetEdges.getIds()[i]).id;
            var updateStep = 15 / App.globalAnimationList.length;
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
function deleteDebugOperator() { //only called by loadData
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
        tmpFlag = false;
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
        }
    }

    tmpFlag = true;
    while (tmpFlag) {
        tmpFlag = false;
        for (i = 0; i <= dataNodes.length - 1; i++) {
            if (dataNodes[i].label.includes('POPVisualisation')) {
                dataNodes.splice(i, 1);
                tmpFlag = true;
                break;
            }
        }
    }

}

//Loads the Nodes and Edges from the data provided
function loadData(dataParameter, flag) {
    dataNodes = dataParameter.nodes;
    dataEdges = dataParameter.edges;
    //remove the POPRico operator
    deleteDebugOperator();
    data = {
        nodes: dataNodes,
        edges: dataEdges,
    };
    draw(flag);
}

function addCustomContextMenu(networkObject, contextFlag) {
    var string;
    if (contextFlag) {
        string = "luposdate3000OPSon";
    } else {
        string = "luposdate3000OP";
    }
    var i;

    networkObject.on("hoverNode", function(params) {
        if ($('#rkm').is(":hidden")) {
            hoverNodeId = params.node;
        }
    });

    //Apply Event Listener for the right click menus
    networkObject.on("oncontext", function(params) {
        var x, y;
        //If right click is made within the canvas, open custom context menu
        if (typeof hoverNodeId != 'undefined') {
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

            //            var elem = document.getElementById('luposdate3000_lastOp');
            //            elem.replaceWith(elem.cloneNode(true));

            //            elem = document.getElementById('luposdate3000_nextOp');
            //            elem.replaceWith(elem.cloneNode(true));

            document.getElementById("luposdate3000_nextOp").addEventListener('click', function(e) {
                var i;
                for (i = currentIndex + 1; i <= queue.length - 1; i++) {
                    if (queue[i][0] == hoverNodeId) {
                        currentIndex = i;
                        contextMenuFlag = true;
                        //animation(false)
                        break;
                    }
                }

            }, false);

            document.getElementById("luposdate3000_lastOp").addEventListener('click', function(e) {
                var i;
                for (i = currentIndex - 1; i >= 0; i--) {
                    if (queue[i][0] == hoverNodeId) {
                        currentIndex = i;
                        contextMenuFlag = true;
                        //animation(false)
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
                    var theChoosenColor = "rgb(" + color._r + "," + color._g + "," + color._b + ")"
                    dataSet.update({
                        id: hoverNodeId,
                        color: theChoosenColor
                    });
                    saveColorChoice(contextFlag, hoverNodeId, theChoosenColor)
                }
            });
        } else {
            document.getElementById(string).addEventListener('contextmenu', function(e) {
                // Alternative
                e.preventDefault();
            }, false);
        }
    });
}

function saveColorChoice(key, id, color) {
    if (typeof App.config.colorsByID === "undefined") {
        App.config.colorsByID = {}
    }
    if (typeof App.config.colorsByID[key] === "undefined") {
        App.config.colorsByID[key] = {}
    }
    App.config.colorsByID[key][id] = color
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
    var colorByType = App.config.colorsByType
    if (typeof colorByType !== "undefined") {
        for (nodeIdx in dataNodes) {
            var node = dataNodes[nodeIdx]
            var nodeType = node.label.split(' ')[0];
            if (typeof colorByType[nodeType] !== "undefined") {
                node.color = colorByType[nodeType]
            }
        }
    }
    var localcolors = App.config.colorsByID
    if (typeof localcolors !== "undefined") {
        var localcolors2 = localcolors[flag]
        if (typeof localcolors2 !== "undefined") {
            for (id in localcolors2) {
                for (nodeIdx in dataNodes) {
                    var node = dataNodes[nodeIdx]
                    if (node.id == id) {
                        node.color = localcolors2[id]
                    }
                }
            }
        }
    }

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
    var net = new vis.Network(container, data, options);
    if (flag) {
        networkSon = net
        calculateMinMaxID();
        calculateMinMaxDepth();
        calculateMinMaxIndex();
        selectMapping();
    } else {
        network = net
    }
    addCustomContextMenu(net, flag);
    setTimeout(() => {
        net.setOptions({
            layout: {
                hierarchical: false
            },
        });
        container.style.display = "inline-block";
        net.fit();
    }, 10);
}
