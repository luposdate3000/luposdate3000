/**
 * Created by Hannes on 02.02.2016.
 */

var options = {};
var lastOP = "";
var graphSteps = [];
var prefixNode;
var network;
var currentStep = 0;

var NODECOLOR = "#2FCBFF";
var PREFIXNODECOLOR = "#BEBEBE";

var graphOPSetting = {
    levelseparation: 100,
    nodespacing: 400,
    treespacing: 500,
    direction: "UD"
};

$('#getopgraphdata').click(function () {
    fitNetworkOP();
});

$("#graph-select").change(function () {
    var value = $(this).val();
    changeOptimizationStep(value);

    var max = graphSteps.length - 1;
    if (value == max) {
        $('#op-graph-down').removeClass('disabled').addClass('enabled');

        $('#op-graph-up').removeClass('enabled').addClass('disabled');
    } else if (value == 0) {
        $('#op-graph-up').removeClass('disabled').addClass('enabled');

        $('#op-graph-down').removeClass('enabled').addClass('disabled');
    } else {
        $('#op-graph-down').removeClass('disabled').addClass('enabled');

        $('#op-graph-up').removeClass('disabled').addClass('enabled');
    }
});


$('#lupo-op-setting-levelseparation, #lupo-op-setting-treespacing,#lupo-op-setting-nodespacing,#lupo-op-setting-direction').on("change input propertychange paste", function () {
    var $this = $(this);
    var id = $this.attr("id");
    switch (id) {
        case "lupo-op-setting-levelseparation":
            graphOPSetting.levelseparation = parseInt($this.val(), 10);
            break;
        case "lupo-op-setting-nodespacing":
            graphOPSetting.nodespacing = parseInt($this.val(), 10);
            break;
        case "lupo-op-setting-treespacing":
            graphOPSetting.treespacing = parseInt($this.val(), 10);
            break;
        case "lupo-op-setting-direction":
            graphOPSetting.direction = $this.val();
            break;
    }
    drawOP();
});


function createOPGraph(data, target) {
    graphSteps = [];
    $("#luposgraphOP").html("");
    $('#graph-select').html("");
    if (data.hasOwnProperty("info") && data.info == "No operator graphs available.") {
        $("#luposgraphOP").html(data.info)
        return;
    }

    $('#lupo-op-setting-levelseparation').val(graphOPSetting.levelseparation);
    $('#lupo-op-setting-treespacing').val(graphOPSetting.treespacing);
    $('#lupo-op-setting-nodespacing').val(graphOPSetting.nodespacing);
    $('#lupo-op-setting-direction').val(graphOPSetting.direction);

    if (data.hasOwnProperty("prefix")) {
        prefixNode = createPrefixNode(data.prefix);
    }
    if (data.hasOwnProperty("optimization")) {
        var OP = data.optimization;

        if (OP == lastOP) {
            return false;
        }
        lastOP = OP;
        $('#graph-select').html("");
        for (var i in OP.steps) {
            graphSteps[i] = generateGraphStep(OP.steps[i], i);
        }

        options = getOPOptions(target);
        createOPLegend();

        currentStep = graphSteps.length - 1;
        $('#graph-select').val(currentStep);
        $('#graph-select').trigger('change');
    }
}

function getOPOptions(target) {
    return {
        physics: {
            enabled: false,
            hierarchicalRepulsion: {
                centralGravity: 0.0,
                springLength: 100,
                springConstant: 0.01,
                nodeDistance: 200,
                damping: 0.09
            }
        },
        nodes: {
            font: {
                face: "'Source Sans Pro', sans-serif"
            },
            borderWidth: 10
        },
        layout: {
            hierarchical: {
                levelSeparation: graphOPSetting.levelseparation,
                nodeSpacing: graphOPSetting.nodespacing,
                treeSpacing: graphOPSetting.treespacing,
                blockShifting: true,
                edgeMinimization: true,
                direction: graphOPSetting.direction,
                sortMethod: "directed"
            }
        }

    };
}

function drawOP() {
    if (Array.isArray(graphSteps) && graphSteps.length > currentStep) {
        container = document.getElementById('luposgraphOP');
        network = new vis.Network(container, graphSteps[currentStep], getOPOptions());
        fitNetworkOP();
    }
}

function changeOptimizationStep(stepNo) {
    var stepNo = parseInt(stepNo, 10);

    var graphSelect = $('#graph-select');
    if (graphSelect.val() != stepNo) {
        graphSelect.val(stepNo);
        graphSelect.trigger('change');
    }

    currentStep = stepNo;
    if (Array.isArray(graphSteps) && graphSteps.length > stepNo) {
        var containerOP = document.getElementById('luposgraphOP');
        network = new vis.Network(containerOP, graphSteps[stepNo], getOPOptions());

        setTimeout(function () {
            network.fit();
        }, 500)
    }
}

function generateGraphStep(step, stepNr) {
    var stepNr = parseInt(stepNr, 10);
    var graph = step.operatorGraph;
    var nodes = graph.nodes;
    var edges = graph.edges;

    var newGraph = {
        nodes: new vis.DataSet(),
        edges: new vis.DataSet()
    };

    var node;
    for (var i in nodes) {
        node = createNode(nodes[i]);
        newGraph.nodes.add(node);
    }
    $('#graph-select').append($('<option>', {
        value: stepNr,
        text: (stepNr + 1) + ": " + step.description
    }));
    for (var key in edges) {
        var value = edges[key];

        var startId = parseInt(key, 10);

        for (var i in value) {
            var endId = value[i].nodeId;
            var operandPosition = value[i].operandPosition;
            endId = parseInt(endId, 10);
            newGraph.edges.add({from: startId, to: endId,arrows:'to',label:operandPosition , font: {align: 'horizontal'}});
        }
    }

    // Add the Prefix Node to the graph
    if (prefixNode) {
        newGraph.nodes.add(prefixNode);
        newGraph.edges.add({from: node.id, to: prefixNode.id, hidden: true});
    }
    return newGraph;
}

function createNode(data) {
    var currentNode = {};
    currentNode.description = data.description;
    currentNode.type = data.type;
    currentNode.label = data.description;
    currentNode.shape = 'box';
    currentNode.id = data.id;
    currentNode.color =
    {
        background: NODECOLOR,
        border: NODECOLOR
    };
    currentNode.shapeProperties = {
        borderRadius: 0
    };

    return currentNode;
}

function createPrefixNode(prefix) {
    var prefixNode = {};
    prefixNode.label = "TODO";
    prefixNode.shape = 'box';
    prefixNode.id = "prefix";
    prefixNode.color = {
        background: PREFIXNODECOLOR,
        border: PREFIXNODECOLOR
    };
    prefixNode.shapeProperties = {
        borderRadius: 0
    };

    var predefined = prefix["pre-defined"];
    var label = "";

    for (var key in predefined) {
        if (predefined.hasOwnProperty(key)) {
            var value = predefined[key];

            if (label != "") {
                label = label + "\n";
            }
            label = label + value + ": " + key;
        }
    }
    prefixNode.label = label;
    return prefixNode;
}


function fitNetworkOP() {
    if (typeof network != "undefined") {
        setTimeout(function () {
            network.fit();
        }, 100)
    }
}

function createOPLegend() {
    var luposlegend = document.getElementById('opluposlegend');
    if (luposlegend.innerHTML == "") {
        var legendDiv = document.createElement('div');
        legendDiv.innerHTML = "Node";
        legendDiv.className = "legendDiv";
        legendDiv.style.backgroundColor = NODECOLOR;
        luposlegend.appendChild(legendDiv);
        var legendDiv2 = document.createElement('div');
        legendDiv2.innerHTML = "Prefix";
        legendDiv2.className = "legendDiv";
        legendDiv2.style.backgroundColor = PREFIXNODECOLOR;
        luposlegend.appendChild(legendDiv2);
    }

}
