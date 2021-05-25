const scale = (num, in_min, in_max, out_min, out_max) => {
    return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min;
}

var unusableOperators = [];
var differentPositions = [];
var differentTypes = [];
var differentOperatorVariables = [];
var differentDataVariables = [];

function calcDifferentPositions() {
    var positions = Object.values(networkSon.getPositions());
    var positionsIds = Object.keys(networkSon.getPositions());
    differentPositions = [];
    var i, j;
    for (i = 0; i <= positions.length - 1; i++) {
        if (!differentPositions.includes(positions[i].y)) {
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (dataNodes[j].id == parseInt(positionsIds[i], 10) && !dataNodes[j].label.includes('AOP') && !dataNodes[j].label.includes('OPBaseCompound')) {
                    differentPositions.push(positions[i].y);
                }
            }
        }
    }

    differentPositions.sort((a, b) => a - b);
}

function calcDifferentTypes() {
    var i;
    differentTypes = [];
    for (i = 0; i <= dataNodes.length - 1; i++) {
        if (!differentTypes.includes(dataNodes[i].label.split(" ")[0])) {
            if (!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')) {
                differentTypes.push(dataNodes[i].label.split(" ")[0]);
            }
        }
    }
}

function calcDifferentOperatorVariables() {
    var i;
    differentOperatorVariables = [];
    for (i = 0; i <= dataNodes.length - 1; i++) {
        if (!differentOperatorVariables.includes(dataNodes[i].label.split("\n")[1])) {
            if (!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')) {
                differentOperatorVariables.push(dataNodes[i].label.split("\n")[1]);
            }
        }
    }
}

function calcDifferentDataVariables() {
    var i;
    differentDataVariables = [];
    for (i = 0; i <= globalAnimationList.length - 1; i++) {
        if (!differentDataVariables.includes(globalAnimationList[i][2].split(" ")[0])) {
            differentDataVariables.push(globalAnimationList[i][2].split(" ")[0]);
        }
    }
}

function addLocicalSteps(data){
var tmp=data.split("NEWTREE")
            var i;
            for (i = 0; i < tmp.length -1; i++) {
                App.logGraph.push(tmp[i]);
            }
}
function addPhysicalSteps(data){
var tmp=data.split("NEWTREE")
            var i;
            for (i = 0; i < tmp.length -1; i++) {
                App.physGraph.push(tmp[i]);
            }
}


function addAnimationDataSplit(visData){
 addAnimationData(visData.split("NEWDATA"));
}
function addAnimationData(tmpResult){
for (i = 0; i <= tmpResult.length - 2; i++) {
                var tmp;
                tmp = tmpResult[i].split("||");
                tmp[0] = parseInt(tmp[0], 10);
                tmp[1] = parseInt(tmp[1], 10);
                tmp[3] = parseInt(tmp[2], 10);
                globalAnimationList.push(tmp);
            }
}


function formatResultData() {
    var c = 0;
    for (var i in App.physGraph) {
        c++;
        $('#luposdate3000_graph-select').append($('<option>', {
            value: c,
            text: c
        }));
    }
    //Change the step select box to the last (final optimized) step
    $("#luposdate3000_graph-select").val(App.physGraph.length);
    $("#luposdate3000_graph-select").trigger('change');



    //Load final optimized step of the physical operator graph by default
    loadData(App.physGraph[App.physGraph.length - 1].split("SPLITHERE"), false);
    loadData(App.physGraph[App.physGraph.length - 1].split("SPLITHERE"), true);
    replacePrefix();
}
