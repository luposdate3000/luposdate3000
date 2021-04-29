var spatOperator = [];
var spatOperatorDepth = [];
var spatOperatorType = [];
var spatOperatorVariable = [];
var spatDataVariable = [];

function getSpatialization(string, id, label, index){
    switch(string){
        case 'Operator-ID':
            var i;
            var j = 0;
            for(i=0;i<=dataNodes.length-1;i++){
                if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = spatOperator[j].value;
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
                            var t = spatOperatorDepth[j].value;
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
                            return spatOperatorType[j].value;
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
                            return spatOperatorVariable[j].value;
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
            return scale(index, min, max, -1, 1);
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            return spatDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var tmp = ((globalAnimationList.length - queue.length) / globalAnimationList.length) * 100;
            return scale(tmp,0,100,-1, 1);
            break;
    }
}

function mappingSpatialization(string){
    switch(string){
        case 'None':
            $('#spatializationSettings').hide();
            break;
        case 'Operator-ID':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html='Spatialization Settings <br>';
            var j;
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=slider id=spatOperator-' + j + '></div><br>';
                }
            }
            $('#spatializationSettings').html(html);
            for (j=0;j<=dataNodes.length-1;j++) {
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#spatOperator-' + j;
                    spatOperator.push(new Nexus.Slider(string, {
                        'size': [120, 40],
                        'mode': 'relative',
                        'min': -1,
                        'max': 1,
                        'step':0.05,
                        'value':0

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();

            calcDifferentPositions();
            html+="Spatialization Settings<br>"
            for(i=0;i<=differentPositions.length-1;i++){
                html+='Layer '+i;
                html += '<div nexus-ui=select id=spatOperatorDepth-' + i + '></div><br>';
            }

            $('#spatializationSettings').html(html);
            for (i=0;i<=differentPositions.length-1;i++) {
                var string = '#spatOperatorDepth-' + i;
                spatOperatorDepth.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': -1,
                    'max': 1,
                    'step':0.05,
                    'value':0

                }));
            }
            break;
        case 'Operator-Type':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = 'Spatialization Seetings';
            calcDifferentTypes();

            for(i=0;i<=differentTypes.length-1;i++){
                html+='Type: '+differentTypes[i];
                html += '<div nexus-ui=select id=spatOperatorType-' + i + '></div><br>';
            }

            $('#spatializationSettings').html(html);
            for (i=0;i<=differentTypes.length-1;i++) {
                var string = '#spatOperatorType-' + i;
                spatOperatorType.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': -1,
                    'max': 1,
                    'step':0.05,
                    'value':0

                }));
            }
            break;
        case 'Operator-Variable':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = 'Spatilization Settings';

            calcDifferentOperatorVariables();

            var i;

            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html+='Variable: '+differentOperatorVariables[i];
                html += '<div nexus-ui=select id=spatOperatorVariable-' + i + '></div><br>';
            }

            $('#spatializationSettings').html(html);
            for (i=0;i<=differentOperatorVariables.length-1;i++) {
                var string = '#spatOperatorVariable-' + i;
                spatOperatorVariable.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': -1,
                    'max': 1,
                    'step':0.05,
                    'value':0

                }));
            }
            break;
        case 'Data-Index':
            //Mapping Settings are not necessary -> Simple map ID to left/right channel
            break;
        case 'Data-Variable':
            $('#spatializationSettings').show();
            $('#spatializationSettings').empty();
            var html = '<fieldset>';
            html += 'Spatialization Settings<br>';

            var i;
            calcDifferentDataVariables();

            for(i=0;i<=differentDataVariables.length-1;i++){
                html+='Variable: '+differentDataVariables[i];
                html += '<div nexus-ui=select id=spatDataVariable-' + i + '></div><br>';
            }
            html += '</fieldset>';
            $('#spatializationSettings').html(html);
            for (i=0;i<=differentDataVariables.length-1;i++) {
                var string = '#spatDataVariable-' + i;
                spatDataVariable.push(new Nexus.Slider(string, {
                    'size': [120, 40],
                    'mode': 'relative',
                    'min': -1,
                    'max': 1,
                    'step':0.05,
                    'value':0

                }));
            }
            break;
        case 'Query-Progress':
            //No settings necessary. Insteadt just map progress to left-right channel
            break;
    }
}
