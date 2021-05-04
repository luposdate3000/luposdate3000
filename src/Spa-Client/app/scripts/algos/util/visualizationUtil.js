const scale = (num, in_min, in_max, out_min, out_max) => {
    return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min;
}

var unusableOperators = [];
var differentPositions = [];
var differentTypes = [];
var differentOperatorVariables = [];
var differentDataVariables = [];

function calcDifferentPositions(){
    var positions = Object.values(networkSon.getPositions());
    var positionsIds = Object.keys(networkSon.getPositions());
    differentPositions = [];
    var i,j;
    for (i=0;i<=positions.length-1;i++){
        if(!differentPositions.includes(positions[i].y)){
            for (j=0;j<=dataNodes.length-1;j++) {
                if(dataNodes[j].id == parseInt(positionsIds[i],10) && !dataNodes[j].label.includes('AOP') && !dataNodes[j].label.includes('OPBaseCompound')){
                    differentPositions.push(positions[i].y);
                }
            }
        }
    }

    differentPositions.sort((a, b) => a - b);
}

function calcDifferentTypes(){
    var i;
    differentTypes = [];
    for (i=0;i<=dataNodes.length-1;i++){
        if(!differentTypes.includes(dataNodes[i].label.split(" ")[0])){
            if(!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')){
                differentTypes.push(dataNodes[i].label.split(" ")[0]);
            }
        }
    }
}

function calcDifferentOperatorVariables(){
    var i;
    differentOperatorVariables = [];
    for (i=0;i<=dataNodes.length-1;i++){
        if(!differentOperatorVariables.includes(dataNodes[i].label.split("\n")[1])){
            if(!dataNodes[i].label.includes('AOP') && !dataNodes[i].label.includes('OPBaseCompound')){
                differentOperatorVariables.push(dataNodes[i].label.split("\n")[1]);
            }
        }
    }
}

function calcDifferentDataVariables(){
    var i;
    differentDataVariables = [];
    for (i=0;i<=globalAnimationList.length-1;i++){
        if(!differentDataVariables.includes(globalAnimationList[i][2].split(" ")[0])){
            differentDataVariables.push(globalAnimationList[i][2].split(" ")[0]);
        }
    }
}

function connectToEndpoint(query, url){
    //Import RDF Triple
    if ($('#send_rdf').is(':checked')) {
        var formData = {
            'data': App.cm['rdf'].getValue(),
        };
        $.ajax({
            type: 'POST',
            url: url+'import/turtledata',
            data: formData
        })
            .done(function(data) {
                console.log(data);
                openEndpointSession(query, url)
            });
    }
}

function openEndpointSession(query, url){
    var formData = {
        'query': query,
        'evaluator': "XML_STREAM",
    };

    $.ajax({
        type: 'POST',
        url: url+'sparql/startSession',
        data: formData
    })
        .done(function(data) {
            getLogicalStepsFromEndpoint(data, url);
        });
}

function getLogicalStepsFromEndpoint(sessionID, url){
    var formData = {
        'sessionID': sessionID,
    };

    $.ajax({
        type: 'POST',
        url: url+'sparql/getLogicalVisual',
        data: formData
    })
        .done(function(data) {
            var tmp = data;
            var i;
            console.log(tmp);
            for (i=0;i<=tmp.split("NEWTREE").length-2;i++){
                logGraph.push(tmp.split("NEWTREE")[i]);
            }
            getPhysicalStepsFromEndpoint(sessionID, url);
        });
}

function getPhysicalStepsFromEndpoint(sessionID, url){
    var formData = {
        'sessionID': sessionID,
    };

    $.ajax({
        type: 'POST',
        url: url+'sparql/getPhysicalVisual',
        data: formData
    })
        .done(function(data) {
            var tmp = data;
            var i;
            console.log(tmp);
            for (i=0;i<=tmp.split("NEWTREE").length-2;i++){
                physGraph.push(tmp.split("NEWTREE")[i]);
            }
            getResultFromEndpoint(sessionID, url);
        });
}

function getResultFromEndpoint(sessionID, url){
    var formData = {
        'sessionID': sessionID,
    };

    $.ajax({
        type: 'POST',
        url: url+'sparql/getResult',
        data: formData
    })
        .done(function(data) {
            result = data;
            getAnimationDataFromEndpoint(sessionID, url);
        });
}

function getAnimationDataFromEndpoint(sessionID, url){
    var formData = {
        'sessionID': sessionID,
    };
    var tmpResult;
    $.ajax({
        type: 'POST',
        url: url+'sparql/getVisualisationData',
        data: formData
    })
        .done(function(data) {
            globalAnimationList = data;
            closeSessionWithEndpoint(sessionID, url);
        });
}

function closeSessionWithEndpoint(sessionID, url){
    var formData = {
        'sessionID': sessionID,
    };

    $.ajax({
        type: 'POST',
        url: url+'sparql/closeSession',
        data: formData
    })
        .done(function(data) {
            console.log(data);
            formatResultData();
        });
}

function formatResultData(){
    var c = 0;
    for (var i in physGraph) {
        c++;
        $('#luposdate3000_graph-select').append($('<option>', {
            value: c,
            text: c
        }));
    }
    //Change the step select box to the last (final optimized) step
    $("#luposdate3000_graph-select").val(physGraph.length);
    $("#luposdate3000_graph-select").trigger('change');

    //Load final optimized step of the physical operator graph by default
    loadData(physGraph[physGraph.length-1].split("SPLITHERE"), false);
    loadData(physGraph[physGraph.length-1].split("SPLITHERE"), true);
    replacePrefix();


}
