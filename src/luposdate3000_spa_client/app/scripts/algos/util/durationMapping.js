var durationOperator = [];
var durationOperatorDepth = [];
var durationOperatorType = [];
var durationOperatorVariable = [];
var durationDataIndex;
var durationDataVariable = [];

function getDuration(string, id, label, index){
    switch(string){
        case 'Operator-ID':
            var i;
            var j = 0;
            for(i=0;i<=dataNodes.length-1;i++){
                if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = durationOperator[j].value;
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
                            return durationOperatorDepth[j].value;
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
                            return durationOperatorType[j].value;
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
                            return durationOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            return durationDataIndex.value;
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            return durationDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            return durationDataIndex.value;
            break;
    }
}

function mappingDuration(string) {
    switch (string) {
        case 'Operator-ID':
            $('#durationSettings').show();
            $('#durationSettings').empty();
            var html = '<fieldset>';
            html += 'Duration Settings<br>';
            var j;
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=select id=durationOperator-' + j + '></div><br>';
                }
            }
            html+='</fieldset>';
            $('#durationSettings').html(html);
            for (j = 0; j <= dataNodes.length - 1; j++) {
                if (!(dataNodes[j].label.includes('AOP') || dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#durationOperator-' + j;
                    durationOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': App.operators.tones

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#durationSettings').show();
            $('#durationSettings').empty();

            calcDifferentPositions();
            html += "Duration Settings<br>";
            for (i = 0; i <= differentPositions.length - 1; i++) {
                html += 'Layer ' + i;
                html += '<div nexus-ui=select id=durationOperatorDepth-' + i + '></div><br>';
            }

            $('#durationSettings').html(html);
            for (i = 0; i <= differentPositions.length - 1; i++) {
                var string = '#durationOperatorDepth-' + i;
                durationOperatorDepth.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.tones
                }));
            }
            break;
        case 'Operator-Type':
            $('#durationSettings').show();
            $('#durationSettings').empty();
            var html = 'Duration Seetings';
            calcDifferentTypes();

            for (i = 0; i <= differentTypes.length - 1; i++) {
                html += 'Type: ' + differentTypes[i];
                html += '<div nexus-ui=select id=durationOperatorType-' + i + '></div><br>';
            }

            $('#durationSettings').html(html);
            for (i = 0; i <= differentTypes.length - 1; i++) {
                var string = '#durationOperatorType-' + i;
                durationOperatorType.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.tones
                }));
            }
            break;
        case 'Operator-Variable':
            $('#durationSettings').show();
            $('#durationSettings').empty();
            var html = 'Duration Settings';

            calcDifferentOperatorVariables();

            var i;

            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                html += 'Variable: ' + differentOperatorVariables[i];
                html += '<div nexus-ui=select id=durationOperatorVariable-' + i + '></div><br>';
            }

            $('#durationSettings').html(html);
            for (i = 0; i <= differentOperatorVariables.length - 1; i++) {
                var string = '#durationOperatorVariable-' + i;
                durationOperatorVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.tones
                }));
            }
            break;
        case 'Data-Index':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#durationSettings').show();
            $('#durationSettings').empty();
            var html = "Duration Settings<br>";
            html += 'Duration'
            html += '<div nexus-ui=select id=durationDataIndex></div><br>';
            $('#durationSettings').html(html);

            durationDataIndex = new Nexus.Select('#durationDataIndex', {
                'size': [100, 40],
                'options': App.operators.tones
            });
            break;
        case 'Data-Variable':
            $('#durationSettings').show();
            $('#durationSettings').empty();

            var html = 'Duration Settings<br>';

            var i;
            calcDifferentDataVariables();

            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                html += 'Variable: ' + differentDataVariables[i];
                html += '<div nexus-ui=select id=durationDataVariable-' + i + '></div><br>';
            }

            $('#durationSettings').html(html);
            for (i = 0; i <= differentDataVariables.length - 1; i++) {
                var string = '#durationDataVariable-' + i;
                durationDataVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': App.operators.tones
                }));
            }
            break;
        case 'Query-Progress':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#durationSettings').show();
            $('#durationSettings').empty();
            var html = "Duration Settings<br>";
            html += 'Duration'
            html += '<div nexus-ui=select id=durationDataIndex></div><br>';
            $('#durationSettings').html(html);

            durationDataIndex = new Nexus.Select('#durationDataIndex', {
                'size': [100, 40],
                'options': App.operators.tones
            });
            break;
    }
}
