var loudnessOperator = [];
var loudnessOperatorDepth = [];
var loudnessOperatorType = [];
var loudnessOperatorVariable = [];
var loudnessDataVariable = [];

function getLoudness(string, id, label, index){
    switch(string){
        case 'Operator-ID':
            var i;
            var j = 0;
            for(i=0;i<=dataNodes.length-1;i++){
                if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = loudnessOperator[j].value;
                        return t;
                    }
                    j++;
                }
            }
            break;
        case 'Operator-Depth':
            for(i=0;i<=dataNodes.length-1;i++){
                if(dataNodes[i].id == id){
                    for(j=0;j<=differentPositions.length-1;j++){
                        if(Object.values(networkSon.getPositions(id))[0].y == parseInt(differentPositions[j],10)){
                            var t = loudnessOperatorDepth[j].value;
                            return t;
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
                            return loudnessOperatorType[j].value;
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
                            return loudnessOperatorVariable[j].value;
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
            return scale(index, min, max, 0, 1);
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            return loudnessDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            return scale(tmp,0,100,0, 1);
            break;
    }
}

function mappingLoudness(string){
    //Show Slider with min&max Loudness
    switch(string){
        case 'Operator-ID':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            var html = '</fieldset>';
            html += 'Loudness Settings<br>';
            var j;
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=select id=loudnessOperator-' + j + '></div><br>';
                }
            }
            html += '</fieldset>';
            $('#loudnessSettings').html(html);
            for (j=0;j<=dataNodes.length-1;j++) {
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#loudnessOperator-' + j;
                    loudnessOperator.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': 0,
                        'max': 1,
                        'step':0.05,
                        'value':0.5
                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();

            calcDifferentPositions();
            html+="Loudness Settings<br>"
            for(i=0;i<=differentPositions.length-1;i++){
                html+='Layer '+i;
                html += '<div nexus-ui=select id=loudnessOperatorDepth-' + i + '></div><br>';
            }

            $('#loudnessSettings').html(html);
            for (i=0;i<=differentPositions.length-1;i++) {
                var string = '#loudnessOperatorDepth-' + i;
                loudnessOperatorDepth.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step':0.05,
                    'value':0.5
                }));
            }
            break;
        case 'Operator-Type':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            var html = 'Loudness Seetings';
            calcDifferentTypes();

            for(i=0;i<=differentTypes.length-1;i++){
                html+='Type: '+differentTypes[i];
                html += '<div nexus-ui=select id=loudnessOperatorType-' + i + '></div><br>';
            }

            $('#loudnessSettings').html(html);
            for (i=0;i<=differentTypes.length-1;i++) {
                var string = '#loudnessOperatorType-' + i;
                loudnessOperatorType.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step':0.05,
                    'value':0.5
                }));
            }
            break;
        case 'Operator-Variable':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            var html = 'Loudness Settings';

            calcDifferentOperatorVariables();

            var i;

            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html+='Variable: '+differentOperatorVariables[i];
                html += '<div nexus-ui=select id=loudnessOperatorVariable-' + i + '></div><br>';
            }

            $('#loudnessSettings').html(html);
            for (i=0;i<=differentOperatorVariables.length-1;i++) {
                var string = '#loudnessOperatorVariable-' + i;
                loudnessOperatorVariable.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step':0.05,
                    'value':0.5
                }));
            }
            break;
        case 'Data-Index':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            break;
        case 'Data-Variable':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();

            var html = 'Loudness Settings<br>';

            var i;
            calcDifferentDataVariables();

            for(i=0;i<=differentDataVariables.length-1;i++){
                html+='Variable: '+differentDataVariables[i];
                html += '<div nexus-ui=select id=loudnessDataVariable-' + i + '></div><br>';
            }

            $('#loudnessSettings').html(html);
            for (i=0;i<=differentDataVariables.length-1;i++) {
                var string = '#loudnessDataVariable-' + i;
                loudnessDataVariable.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': 0,
                    'max': 1,
                    'step':0.05,
                    'value':0.5
                }));
            }
            break;
        case 'Query-Progress':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            break;
    }
}
