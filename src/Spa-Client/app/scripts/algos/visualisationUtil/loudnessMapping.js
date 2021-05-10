var loudnessOperator = [];
var loudnessOperatorDepth = [];
var loudnessOperatorType = [];
var loudnessOperatorVariable = [];
var loudnessDataVariable = [];
var loudnessDataIndex;

function getLoudness(string, id, label, index){
    switch(string){
        case 'Simple':
            return loudnessDataIndex.value;
            break;
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
            return scale(index, min, max, 0, loudnessDataIndex.value);
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
            return scale(tmp,0,100,0, loudnessDataIndex.value);
            break;
    }
}

function mappingLoudness(string){
    //Show Slider with min&max Loudness
    switch(string){
        case 'Simple':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();

            var html = '<fieldset>';
            html += '<h7>Select a global setting.</h7><br><br>';
            html += '<div style=overflow:hidden;>';
            html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
            html += '<div nexus-ui=select id=loudnessDataIndex style=float:left;margin-right:10px;></div>';
            html += '</div>';

            html += '</fieldset>';
            $('#loudnessSettings').html(html);
            loudnessDataIndex = new Nexus.Slider('#loudnessDataIndex', {
                'size': [120, 40],
                'mode': 'relative',
                'min': 0,
                'max': 1,
                'step':0.05,
                'value':0.5
            });
            break;
        case 'Operator-ID':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();
            var html = '<fieldset>';
            var j;
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '<h7>'+ dataNodes[j].label.split("\n")[0] + '</h7><br>';
                    html += '<div style=overflow:hidden;>';
                    html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                    html += '<div nexus-ui=select id=loudnessOperator-'+j+' style=float:left;margin-right:10px;></div>';
                    html += '</div>';
                }
            }
            html += '</fieldset>';

            loudnessOperator = [];
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
            var html="<fieldset>"
            for(i=0;i<=differentPositions.length-1;i++){
                html += '<h7>Layer: '+i+ '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessOperatorDepth-'+i+' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';

            loudnessOperatorDepth = [];
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
            var html = '<fieldset>';
            calcDifferentTypes();

            for(i=0;i<=differentTypes.length-1;i++){
                html += '<h7>Type: '+differentTypes[i]+ '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessOperatorType-'+i+' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html+='</fieldset>';

            loudnessOperatorType = [];
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
            var html = '<fieldset>';

            calcDifferentOperatorVariables();

            var i;

            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html += '<h7>Variable: '+differentOperatorVariables[i]+ '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessOperatorVariable-'+i+' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';

            loudnessOperatorVariable = [];
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

            var html = '<fieldset>';
                html += '<h7>Please choose maximum loudness.</h7><br><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessDataIndex style=float:left;margin-right:10px;></div>';
                html += '</div>';

            html += '</fieldset>';
            $('#loudnessSettings').html(html);
            loudnessDataIndex = new Nexus.Slider('#loudnessDataIndex', {
                'size': [120, 40],
                'mode': 'relative',
                'min': 0,
                'max': 1,
                'step':0.05,
                'value':0.5
            });

            break;
        case 'Data-Variable':
            $('#loudnessSettings').show();
            $('#loudnessSettings').empty();

            var html = '<fieldset>';

            var i;
            calcDifferentDataVariables();

            for(i=0;i<=differentDataVariables.length-1;i++){
                html += '<h7>Variable: '+differentDataVariables[i]+ '</h7><br>';
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
                html += '<div nexus-ui=select id=loudnessDataVariable-'+i+' style=float:left;margin-right:10px;></div>';
                html += '</div>';
            }

            html += '</fieldset>';

            loudnessDataVariable = [];
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

            var html = '<fieldset>';
            html += '<h7>Please choose maximum loudness.</h7><br><br>';
            html += '<div style=overflow:hidden;>';
            html += '<p style=float:left;margin-right:10px;margin-top:9px;>Loudness: </p>';
            html += '<div nexus-ui=select id=loudnessDataIndex style=float:left;margin-right:10px;></div>';
            html += '</div>';

            html += '</fieldset>';
            $('#loudnessSettings').html(html);
            loudnessDataIndex = new Nexus.Slider('#loudnessDataIndex', {
                'size': [120, 40],
                'mode': 'relative',
                'min': 0,
                'max': 1,
                'step':0.05,
                'value':0.5
            });
            break;
    }
}
