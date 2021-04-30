var pitchOperator = [];
var pitchOperatorDepth = [];
var pitchOperatorType = [];
var pitchOperatorVariable = [];
var pitchDataVariable = [];

function getPitch(string, id, label, index){
    switch(string){
        case "None":
            return 'C4';
            break;
        case "Operator-ID":
            var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(),10);

            if(pitchMode == 0) {
                //Find Max & Min ID
                var max = 0;
                var min = 9999999999;
                var i;
                for (i = 0; i <= dataNodes.length - 1; i++) {
                    var k = parseInt(dataNodes[i].label.split(" ")[1].split("\n")[0],10);
                    if (k > max) {
                        max = k;
                    }if (k < min) {
                        min = k;
                    }
                }
                return scale(parseInt(label.split(" ")[1].split("\n")[0],10), min, max, parseInt($('#sonHz-numberMin').val(),10), parseInt($('#sonHz-numberMax').val(),10));
            }else{
                var i;
                var j = 0;
                for(i=0;i<=dataNodes.length-1;i++){
                    if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                        if (dataNodes[i].id == id) {
                            var t = pitchOperator[j].value;
                            return t;
                        }
                        j++;
                    }
                }
            }
            break;
        case 'Operator-Depth':
            var pitchMode = parseInt($('input[type=radio][name=pitch]:checked').val(),10);

            if(pitchMode == 0) {
                //Find Max & Min Depth
                var max = -999999999;
                var min = 999999999;
                var i;
                var positions = Object.values(networkSon.getPositions());
                for (i = 0; i <= positions.length - 1; i++) {
                    if (positions[i].y > max) {
                        max = positions[i].y;
                    }if (positions[i].y < min) {
                        min = positions[i].y;
                    }
                }
                return scale(networkSon.getPosition(id).y, min, max, parseInt($('#sonHz-numberMin').val(),10), parseInt($('#sonHz-numberMax').val(),10));

            }else{
                for(i=0;i<=dataNodes.length-1;i++){
                    if(dataNodes[i].id == id){
                        for(j=0;j<=differentPositions.length-1;j++){
                            if(Object.values(networkSon.getPositions(id))[0].y == parseInt(differentPositions[j],10)){
                                var t = pitchOperatorDepth[j].value;
                                return t;
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Type':
            var i,j;
            for(i=0;i<=dataNodes.length-1;i++){
                if(dataNodes[i].id == id){
                    for(j=0;j<=differentTypes.length;j++){
                        if(dataNodes[i].label.split(" ")[0] == differentTypes[j]){
                            return pitchOperatorType[j].value;
                        }
                    }
                }
            }
            break;
        case 'Operator-Variable':
            var i,j;
            for(i=0;i<=dataNodes.length-1;i++){
                if(dataNodes[i].id == id){
                    for(j=0;j<=differentOperatorVariables.length;j++){
                        if(dataNodes[i].label.split("\n")[1] == differentOperatorVariables[j]){
                            return pitchOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            //Find Max & Min ID
            var max = 0;
            var min = 999999999999999;
            var i;
            for (i = 0; i <= globalAnimationList.length - 1; i++) {
                var k = parseInt(globalAnimationList[i][3],10);
                if (k > max) {
                    max = k;
                }if (k < min) {
                    min = k;
                }
            }
            return scale(index, min, max, parseInt($('#sonHz-numberMin').val(),10), parseInt($('#sonHz-numberMax').val(),10));
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            return pitchDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            return scale(tmp,0,100,parseInt($('#sonHz-numberMin').val(),10), parseInt($('#sonHz-numberMax').val(),10));
            break;
    }
}

function mappingPitch(string){

    //Double Handle Slider for Min & Max Frequencies
    //Pitch Slider
    sliderHz = document.getElementById("sonHz");
    $('#sonHz-numberMin').val(Nexus.scale(30, 0, 100, 30, 1500));
    $('#sonHz-numberMax').val(Nexus.scale(100, 0, 100, 30, 1500));

    var o;
    unusableOperators = [];
    for (o=0;o<=dataNodes.length-1;o++){
        if(dataNodes[o].label.includes('AOP')||dataNodes[o].label.includes('OPBaseCompound')){
            unusableOperators.push(dataNodes[o].id);
        }
    }

    switch (string){
        case 'Operator-ID':
            $('#radioPitch').show();
            $('#pitchSettings').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchSettingsExplicit').empty();
            var html = '<div id=pitchHeader>Pitch Settings</div>';
            //html += '<div id=pitchContent>';

            var j;
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=select id=pitchOperator-' + j + '></div><br>';
                }
            }
            //html+='</div>';
            $('#pitchSettingsExplicit').html(html);
            for (j=0;j<=dataNodes.length-1;j++) {
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#pitchOperator-' + j;
                    pitchOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.frequence

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#radioPitch').show();
            $('#pitchSettings').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchSettingsExplicit').empty();
            var html = '';

            calcDifferentPositions();

            for(i=0;i<=differentPositions.length-1;i++){
                html+='Layer '+i;
                html += '<div nexus-ui=select id=pitchOperatorDepth-' + i + '></div><br>';
            }

            $('#pitchSettingsExplicit').html(html);
            for (i=0;i<=differentPositions.length-1;i++) {
                var string = '#pitchOperatorDepth-' + i;
                pitchOperatorDepth.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.frequence
                }));
            }
            break;
        case 'Operator-Type':
            $('#pitchSettings').show();
            $('#pitchSettingsExplicit').empty();
            $('#pitchSettingsExplicit').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchDynamic').prop("checked",false);
            $('#pitchExplicit').prop("checked",true);
            $('#radioPitch').hide();
            var html = '';

            calcDifferentTypes();
            var i;

            for(i=0;i<=differentTypes.length-1;i++){
                html+='Type: '+differentTypes[i];
                html += '<div nexus-ui=select id=pitchOperatorType-' + i + '></div><br>';
            }

            $('#pitchSettingsExplicit').html(html);
            for (i=0;i<=differentTypes.length-1;i++) {
                var string = '#pitchOperatorType-' + i;
                pitchOperatorType.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.frequence
                }));
            }
            break;
        case 'Operator-Variable':
            $('#pitchSettings').show();
            $('#pitchSettingsExplicit').empty();
            $('#pitchSettingsExplicit').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchDynamic').prop("checked",false);
            $('#pitchExplicit').prop("checked",true);
            $('#radioPitch').hide();
            var html = '';
            var i;
            calcDifferentOperatorVariables();

            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html+='Variable: '+differentOperatorVariables[i];
                html += '<div nexus-ui=select id=pitchOperatorVariable-' + i + '></div><br>';
            }

            $('#pitchSettingsExplicit').html(html);
            for (i=0;i<=differentOperatorVariables.length-1;i++) {
                var string = '#pitchOperatorVariable-' + i;
                pitchOperatorVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.frequence
                }));
            }
            break;
        case 'Data-Index':
            $('#pitchSettings').show();
            $('#pitchSettingsExplicit').empty();
            $('#pitchSettingsExplicit').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchDynamic').prop("checked",false);
            $('#pitchExplicit').prop("checked",true);
            $('#radioPitch').hide();
            break;
        case 'Data-Variable':
            $('#pitchSettings').show();
            $('#pitchSettingsExplicit').empty();
            $('#pitchSettingsExplicit').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchDynamic').prop("checked",false);
            $('#pitchExplicit').prop("checked",true);
            $('#radioPitch').hide();

            var html = '';
            var i;
            differentDataVariables = [];
            for (i=0;i<=globalAnimationList.length-1;i++){
                if(!differentDataVariables.includes(globalAnimationList[i][2].split(" ")[0])){
                    differentDataVariables.push(globalAnimationList[i][2].split(" ")[0]);
                }
            }

            for(i=0;i<=differentDataVariables.length-1;i++){
                html+='Variable: '+differentDataVariables[i];
                html += '<div nexus-ui=select id=pitchDataVariable-' + i + '></div><br>';
            }

            $('#pitchSettingsExplicit').html(html);
            for (i=0;i<=differentDataVariables.length-1;i++) {
                var string = '#pitchDataVariable-' + i;
                pitchDataVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.frequence
                }));
            }
            break;
        case 'Query-Progress':
            $('#pitchSettings').show();
            $('#pitchSettingsExplicit').empty();
            $('#pitchSettingsExplicit').show();
            $('#sonHz').show();
            $('#sonHz-numberMin').show();
            $('#sonHz-numberMax').show();
            $('#pitchDynamic').prop("checked",false);
            $('#pitchExplicit').prop("checked",true);
            $('#radioPitch').hide();
            break;
    }
}
