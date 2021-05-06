/**
 * Created by Hannes on 02.02.2016.
 */

var options = {};
var nodes = [];
var edges = [];
nodes[0] = new vis.DataSet();
edges[0] = new vis.DataSet();
nodes[1] = new vis.DataSet();
edges[1] = new vis.DataSet();
var tempNodesArray = [];
var tempEdgesArray = [];
var ASTs = [];
var lastAST = "";
var networ_ast;
var container;
var index = 0;
var astType = null;
var QUERY_NODE_ID = "queryNode";

var graphSettings = {
    levelseparation: 100,
    nodespacing: 400,
    treespacing: 500,
    direction: "UD"
};


$('#getgraphdata').click(function () {
    fitNetworkAST();
});

$('input[type=radio][name=ASTGraph]').change(function () {
    index = parseInt($(this).val(), 10);
    draw(index);
});


$('#lupo-setting-levelseparation, #lupo-setting-treespacing,#lupo-setting-nodespacing,#lupo-setting-direction').on("change input propertychange paste", function () {
    var $this = $(this);
    var id = $this.attr("id");
    switch (id) {
        case "lupo-setting-levelseparation":
            graphSettings.levelseparation = parseInt($this.val(), 10);
            break;
        case "lupo-setting-nodespacing":
            graphSettings.nodespacing = parseInt($this.val(), 10);
            break;
        case "lupo-setting-treespacing":
            graphSettings.treespacing = parseInt($this.val(), 10);
            break;
        case "lupo-setting-direction":
            graphSettings.direction = $this.val();
            break;
    }
    draw(index);
});


function createGraph(data,target) {
    astType = target;
    $('#lupo-setting-levelseparation').val(graphSettings.levelseparation);
    $('#lupo-setting-nodespacing').val(graphSettings.nodespacing);
    $('#lupo-setting-direction').val(graphSettings.direction);

    if (data.hasOwnProperty("AST")) {
        var AST = data.AST;
        if (AST == lastAST) {
            return false;
        }
        lastAST = AST;
        nodes[0].clear();
        edges[0].clear();

        tempNodesArray = [];
        tempEdgesArray = [];

        extractNodeData(AST, undefined, 0);
        nodes[0].add(tempNodesArray);
        edges[0].add(tempEdgesArray);

        ASTs[0] = {
            nodes: nodes[0],
            edges: edges[0]
        };
        if(astType == "sparql"){
            createLegend();
            $("label[for='coreASTradio']").text("CoreSPARQL AST")
        }else{
            $("label[for='coreASTradio']").text("Rule AST")
            $("#luposlegend").hide();
        }

    }

    if (data.hasOwnProperty("coreAST")) {
        var coreAST = data.coreAST;

        nodes[1].clear();
        edges[1].clear();

        tempNodesArray = [];
        tempEdgesArray = [];

        createQueryNode(data.coreSPARQL);

        extractNodeData(coreAST, QUERY_NODE_ID, 1);
        nodes[1].add(tempNodesArray);
        edges[1].add(tempEdgesArray);
        ASTs[1] = {
            nodes: nodes[1],
            edges: edges[1]
        };
    }
    else if (data.hasOwnProperty("rulesAST")) {
        var rulesAST = data.rulesAST;
        nodes[1].clear();
        edges[1].clear();

        tempNodesArray = [];
        tempEdgesArray = [];

        extractNodeData(rulesAST, undefined, 1);
        nodes[1].add(tempNodesArray);
        edges[1].add(tempEdgesArray);

        ASTs[1] = {
            nodes: nodes[1],
            edges: edges[1]
        };
    }
    draw(index);
}

function createQueryNode(coreSparql)
{
    //var coreSparql = coreSparql.replace(/</g, '&lt;').replace(/>/g, '&gt;');
    var currentNode = {};
    currentNode.id = QUERY_NODE_ID;
    //currentNode.type = data.type;
    //currentNode.classification = data.classification;
    currentNode.label = coreSparql;
    currentNode.shape = 'box';
    //currentNode.operandPosition = data.operandPosition;
    currentNode.color =
    {
        background: "#FFFFFF",
        border: "#FFFFFF"
    };
    currentNode.shapeProperties = {
        borderRadius: 0
    };

    tempNodesArray.push(currentNode);
}
function getOptions() {
    var options = null;
    switch (astType) {
        case "sparql" :
        case "rif" :
            options = {
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
                        levelSeparation: graphSettings.levelseparation,
                        nodeSpacing: graphSettings.nodespacing,
                        treeSpacing: 500,
                        blockShifting: true,
                        edgeMinimization: true,
                        direction: graphSettings.direction,
                        sortMethod: "directed"
                    }
                }
            };
            break;

        case "rifBACKUP" :
            options = {
                "edges": {
                    "smooth": false
                },
                "physics": {
                    "enabled": true,
                    "springLength": 185,
                    "minVelocity": 0.75
                }
            };
            break;
        default : console.error("falscher astType",astType)
    }
    return options;
}

function draw(index) {
    if(index === 1 && astType == "sparql"){
        $("#coreSparqlQuery").show();
    }else{
        $("#coreSparqlQuery").hide();
    }
    container = document.getElementById('luposgraph');
    var options = getOptions(astType);

    networ_ast = new vis.Network(container, ASTs[index], options);
    fitNetworkAST();
}


function fitNetworkAST() {
    if (typeof networ_ast != "undefined") {
        setTimeout(function () {
            networ_ast.fit();
        }, 100)
    }

}
function extractNodeData(data, parentId, index) {

    var currentNode = {};
    currentNode.id = data.id;
    currentNode.type = data.type;
    currentNode.classification = data.classification;
    currentNode.label = data.description;
    currentNode.shape = 'box';
    currentNode.operandPosition = data.operandPosition;
    currentNode.color =
    {
        background: generateColor(data.classification),
        border: generateColor(data.classification)
    };
    currentNode.shapeProperties = {
        borderRadius: 0
    };
    var isIn = isIdInArray(tempNodesArray, currentNode.id);
    if (!isIn) {
        tempNodesArray.push(currentNode);
    }else{

        currentNode.id = currentNode.id+"_"+Date.now();
        currentNode.color =  'lime';
        tempNodesArray[isIn].color = "red";
        tempNodesArray.push(currentNode);
    }

    if(parentId === QUERY_NODE_ID){
        var edge = {from: parentId, to: currentNode.id, hidden: true};
        tempEdgesArray.push(edge);
    }
    else if (typeof parentId != "undefined") {
        var edge = {from: parentId, to: currentNode.id,arrows:'to',label:currentNode.operandPosition , font: {align: 'horizontal'}};
        //edges[index].add(edge);
        tempEdgesArray.push(edge);
    }
    if (data.hasOwnProperty("children")) {
        extractChildren(data.children, data.id, index)
    }
}
function extractChildren(children, parentId, index) {
    for (var i in children) {
        var child = children[i];
        extractNodeData(child, parentId, index);
    }
}

function isIdInArray(array, id) {
    for (var i in array) {
        var element = array[i];
        if (element.id == id) {
            return i;
        }
    }
    return false;
}

function generateColor(type) {
    var color = "#BEBEBE";
    switch (type) {
        case  "TerminalNode" :
            color = "#FF4A4A"; //red
            break;
        case  "QueryHead":
            color = "#C67CE4"; //violette
            break;
        case  "NonTerminalNode":
            color = "#8DE668"; //green
            break;
        case  "UnknownNode":
            color = "#FF4A4A"; //red
            break;
        case  "HighLevelOperator":
            color = "#8DE668"; //green
            break;
    }
    return color;
}

function createLegend() {
    var luposlegend = document.getElementById('luposlegend');
    if (luposlegend.innerHTML == "") {
        createSingleLegendDiv(luposlegend, "QueryHead", "Query Head");
        createSingleLegendDiv(luposlegend, "HighLevelOperator", "High Level Operator");
        createSingleLegendDiv(luposlegend, "NonTerminalNode", "Non-Terminal Node");
        createSingleLegendDiv(luposlegend, "TerminalNode", "Terminal Node");
        createSingleLegendDiv(luposlegend, "UnknownNode", "Unknown Node");
    }
    $("#luposlegend").show()

}

function createSingleLegendDiv(parent, type, description) {
    if (!description) {
        description = type;
    }

    var legendDiv = document.createElement('div');
    legendDiv.innerHTML = description;
    legendDiv.className = "legendDiv";
    legendDiv.style.backgroundColor = generateColor(type);
    parent.appendChild(legendDiv);
}
