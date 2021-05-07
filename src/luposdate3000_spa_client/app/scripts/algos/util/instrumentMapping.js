var instrumentOperator = [];
var instrumentOperatorDepth = [];
var instrumentOperatorType = [];
var instrumentOperatorVariable = [];
var instrumentDataIndex;
var instrumentDataVariable = [];

function getInstrument(string, id, label, index){
    switch(string){
        case 'Operator-ID':
            var i;
            var j = 0;
            for(i=0;i<=dataNodes.length-1;i++){
                if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var t = instrumentOperator[j].value;
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
                            var t = instrumentOperatorDepth[j].value;
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
                            return instrumentOperatorType[j].value;
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
                            return instrumentOperatorVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            return instrumentDataIndex.value;
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            return instrumentDataVariable[j].value;
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            return instrumentDataIndex.value;
            break;
    }
}

function mappingInstrument(string){
    switch (string){
        case 'None':
            $('#instrumentSettings').hide();
            break;
        case 'Operator-ID':
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();
            var html = '<fieldset>';
            html += 'Instrument Settings<br>';
            var j;
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=select id=instrumentOperator-' + j + '></div><br>';
                }
            }
            html += '</fieldset>';
            $('#instrumentSettings').html(html);
            for (j=0;j<=dataNodes.length-1;j++) {
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#instrumentOperator-' + j;
                    console.log(string);
                    instrumentOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.samples)

                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();

            calcDifferentPositions();
            html+="Instrument Settings<br>"
            for(i=0;i<=differentPositions.length-1;i++){
                html+='Layer '+i;
                html += '<div nexus-ui=select id=instrumentOperatorDepth-' + i + '></div><br>';
            }

            $('#instrumentSettings').html(html);
            for (i=0;i<=differentPositions.length-1;i++) {
                var string = '#instrumentOperatorDepth-' + i;
                instrumentOperatorDepth.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.samples)
                }));
            }
            break;
        case 'Operator-Type':
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();
            var html = 'Instrument Seetings';
            calcDifferentTypes();

            for(i=0;i<=differentTypes.length-1;i++){
                html+='Type: '+differentTypes[i];
                html += '<div nexus-ui=select id=instrumentOperatorType-' + i + '></div><br>';
            }

            $('#instrumentSettings').html(html);
            for (i=0;i<=differentTypes.length-1;i++) {
                var string = '#instrumentOperatorType-' + i;
                instrumentOperatorType.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.samples)
                }));
            }
            break;
        case 'Operator-Variable':
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();
            var html = 'Instrument Settings';

            calcDifferentOperatorVariables();

            var i;

            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html+='Variable: '+differentOperatorVariables[i];
                html += '<div nexus-ui=select id=instrumentOperatorVariable-' + i + '></div><br>';
            }

            $('#instrumentSettings').html(html);
            for (i=0;i<=differentOperatorVariables.length-1;i++) {
                var string = '#instrumentOperatorVariable-' + i;
                instrumentOperatorVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.samples)
                }));
            }
            break;
        case 'Data-Index':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();
            var html="Instrument Settings<br>";
            html+='Instrument'
            html += '<div nexus-ui=select id=instrumentDataIndex></div><br>';
            $('#instrumentSettings').html(html);

            instrumentDataIndex = new Nexus.Select('#instrumentDataIndex', {
                'size': [100, 40],
                'options': Object.keys(App.samples)
            });
            break;
        case 'Data-Variable':
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();

            var html = 'Instrument Settings<br>';

            var i;
            calcDifferentDataVariables();

            for(i=0;i<=differentDataVariables.length-1;i++){
                html+='Variable: '+differentDataVariables[i];
                html += '<div nexus-ui=select id=instrumentDataVariable-' + i + '></div><br>';
            }

            $('#instrumentSettings').html(html);
            for (i=0;i<=differentDataVariables.length-1;i++) {
                var string = '#instrumentDataVariable-' + i;
                instrumentDataVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.samples)
                }));
            }
            break;
        case 'Query-Progress':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#instrumentSettings').show();
            $('#instrumentSettings').empty();
            var html="Instrument Settings<br>";
            html+='Instrument'
            html += '<div nexus-ui=select id=instrumentDataIndex></div><br>';
            $('#instrumentSettings').html(html);

            instrumentDataIndex = new Nexus.Select('#instrumentDataIndex', {
                'size': [100, 40],
                'options': Object.keys(App.samples)
            });
            break;
    }
}
