const scale = (num, in_min, in_max, out_min, out_max) => {
    return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min;
}

var unusableOperators = [];
var differentPositions = [];
var differentTypes = [];
var differentOperatorVariables = [];
var differentDataVariables = [];
var idMappings = {}


function calcDifferentPositions() { //called by formatResultData
    if (typeof networkSon !== "undefined" && networkSon != null) {
        var positions = Object.values(networkSon.getPositions());
        var positionsIds = Object.keys(networkSon.getPositions());
        differentPositions = [];
        var i, j;
        for (i = 0; i <= positions.length - 1; i++) {
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (dataNodes[j].id == parseInt(positionsIds[i], 10) && !dataNodes[j].label.includes('AOP') && !dataNodes[j].label.includes('OPBaseCompound')) {
                    if (!differentPositions.includes(positions[i].y)) {
                        differentPositions.push(positions[i].y);
                    }
                    idMappings["Operator-Depth"][dataNodes[i].id] = "A" + positions[i].y
                }
            }
        }
        differentPositions.sort((a, b) => a - b);
        for (i = 0; i < differentPositions.length; i++) {
            for (j in idMappings["Operator-Depth"]) {
                if (idMappings["Operator-Depth"][j] == "A" + differentPositions[i]) {
                    idMappings["Operator-Depth"][j] = i
                }
            }
        }
    }
}

function calcDifferentTypes() { //called by formatResultData
    var i;
    differentTypes = [];
    for (i = 0; i <= dataNodes.length - 1; i++) {
        var tmp = dataNodes[i].label.split(" ")[0]
        if (!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')) {
            if (!differentTypes.includes(tmp)) {
                idMappings["Operator-Type"][dataNodes[i].id] = differentTypes.length
                differentTypes.push(tmp);
            } else {
                idMappings["Operator-Type"][dataNodes[i].id] = differentTypes.indexOf(tmp)
            }
        }
    }
}

function calcDifferentOperatorVariables() { //called by formatResultData
    var i;
    differentOperatorVariables = [];
    for (i = 0; i <= dataNodes.length - 1; i++) {
        var tmp = dataNodes[i].label.split("\n")[1]
        if (!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')) {
            if (!differentOperatorVariables.includes(tmp)) {
                idMappings["Operator-Variable"][dataNodes[i].id] = differentOperatorVariables.length
                differentOperatorVariables.push(tmp);
            } else {
                idMappings["Operator-Variable"][dataNodes[i].id] = differentOperatorVariables.indexOf(tmp)
            }
        }
    }
}

function calcDifferentDataVariables() { //called by formatResultData
    var i;
    differentDataVariables = [];
    for (i = 0; i <= App.globalAnimationList.length - 1; i++) {
        var tmp = App.globalAnimationList[i][2].split(" ")[0]
        if (!differentDataVariables.includes(tmp)) {
            idMappings["Data-Variable"][App.globalAnimationList[i][3]] = differentDataVariables.length
            differentDataVariables.push(tmp);
        } else {
            idMappings["Data-Variable"][App.globalAnimationList[i][3]] = differentDataVariables.indexOf(tmp)
        }
    }
}



function formatResultData() { //called after query execution  - called by main.coffee
console.log("formatResultData")
    if (network != null) {
        network.destroy();
        network = null
        if (networkSon != null) {
            networkSon.destroy();
            networkSon = null
        }
    }
    var c = 0;
    for (var i in App.physGraph) {
        c++;
        $('#luposdate3000_graph-select').append($('<option>', {
            value: c,
            text: c
        }));
    }

console.log("formatResultData -> trigger change")
    //Change the step select box to the last (final optimized) step
    $("#luposdate3000_graph-select").val(App.physGraph.length);
    $("#luposdate3000_graph-select").trigger('change');



    //Load final optimized step of the physical operator graph by default
    replacePrefix();
console.log("formatResultData -> loadData A")
    loadData(App.physGraph[App.physGraph.length - 1], true);
console.log("formatResultData -> loadData B")
    loadData(App.physGraph[App.physGraph.length - 1], false);
    //draw the network

    idMappings = {
        "Operator-ID": {},
        "Operator-Depth": {},
        "Operator-Type": {},
        "Operator-Variable": {},
        "Data-Variable": {},
    }
    var counter = 0
    for (i = 0; i <= dataNodes.length - 1; i++) {
        if (!(dataNodes[i].label.includes('AOP') || dataNodes[i].label.includes('OPBaseCompound'))) {
            idMappings["Operator-ID"][dataNodes[i].id] = counter
            counter++
        }
    }
    calcDifferentPositions()
    calcDifferentTypes()
    calcDifferentOperatorVariables()
    calcDifferentDataVariables()
    resetAnimationQueue()
}
