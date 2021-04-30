var chordOperator = [];
var octaveOperator = [];
var chordOperatorDepth = [];
var octaveOperatorDepth = [];
var chordOperatorType = [];
var octaveOperatorType = [];
var chordOperatorVariable = [];
var octaveOperatorVariable = [];
var chordDataIndex;
var octaveDataIndex;
var chordDataVariable = [];
var octaveDataVariable = [];
var arpeggio = [];

function getChord(string, id, label, index){
    switch(string){
        case 'Operator-ID':
            var i;
            var j = 0;
            for(i=0;i<=dataNodes.length-1;i++){
                if(!(dataNodes[i].label.includes('AOP')||dataNodes[i].label.includes('OPBaseCompound'))) {
                    if (dataNodes[i].id == id) {
                        var basicTone = App.operators.chords[chordOperator[j].value].basic+octaveOperator[j].value;
                        var firstTone = App.operators.chords[chordOperator[j].value].first+octaveOperator[j].value;
                        var secondTone = App.operators.chords[chordOperator[j].value].second+octaveOperator[j].value;
                        if ($(arpeggio[j].selector).is(':checked')) {
                            return [true, basicTone, firstTone, secondTone];
                        }else{
                            return [false, basicTone, firstTone, secondTone];
                        }
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
                            var basicTone = App.operators.chords[chordOperatorDepth[j].value].basic+octaveOperatorDepth[j].value;
                            var firstTone = App.operators.chords[chordOperatorDepth[j].value].first+octaveOperatorDepth[j].value;
                            var secondTone = App.operators.chords[chordOperatorDepth[j].value].second+octaveOperatorDepth[j].value;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            }else{
                                return [false, basicTone, firstTone, secondTone];
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
                            var basicTone = App.operators.chords[chordOperatorType[j].value].basic+octaveOperatorType[j].value;
                            var firstTone = App.operators.chords[chordOperatorType[j].value].first+octaveOperatorType[j].value;
                            var secondTone = App.operators.chords[chordOperatorType[j].value].second+octaveOperatorType[j].value;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            }else{
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Operator-Variable':
            console.log("switch-statement");
            var i,j;
            for(i=0;i<=dataNodes.length-1;i++){
                if(dataNodes[i].id == id){
                    for(j=0;j<=differentOperatorVariables.length;j++){
                        if(dataNodes[i].label.split("\n")[1] == differentOperatorVariables[j]){
                            var basicTone = App.operators.chords[chordOperatorVariable[j].value].basic+octaveOperatorVariable[j].value;
                            var firstTone = App.operators.chords[chordOperatorVariable[j].value].first+octaveOperatorVariable[j].value;
                            var secondTone = App.operators.chords[chordOperatorVariable[j].value].second+octaveOperatorVariable[j].value;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            }else{
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Data-Index':
            var basicTone = App.operators.chords[chordDataIndex.value].basic+octaveDataIndex.value;
            var firstTone = App.operators.chords[chordDataIndex.value].first+octaveDataIndex.value;
            var secondTone = App.operators.chords[chordDataIndex.value].second+octaveDataIndex.value;
            if ($('#arpeggioIndex').is(':checked')) {
                return [true, basicTone, firstTone, secondTone];
            }else{
                return [false, basicTone, firstTone, secondTone];
            }
            break;
        case 'Data-Variable':
            var i,j;
            for(i=0;i<=globalAnimationList.length-1;i++){
                if(globalAnimationList[i][3] == index){
                    for(j=0;j<=differentDataVariables.length;j++){
                        if(globalAnimationList[i][2].split(" ")[0] == differentDataVariables[j]){
                            var basicTone = App.operators.chords[chordDataVariable[j].value].basic+octaveDataVariable[j].value;
                            var firstTone = App.operators.chords[chordDataVariable[j].value].first+octaveDataVariable[j].value;
                            var secondTone = App.operators.chords[chordDataVariable[j].value].second+octaveDataVariable[j].value;
                            if ($(arpeggio[j].selector).is(':checked')) {
                                return [true, basicTone, firstTone, secondTone];
                            }else{
                                return [false, basicTone, firstTone, secondTone];
                            }
                        }
                    }
                }
            }
            break;
        case 'Query-Progress':
            var basicTone = App.operators.chords[chordDataIndex.value].basic+octaveDataIndex.value;
            var firstTone = App.operators.chords[chordDataIndex.value].first+octaveDataIndex.value;
            var secondTone = App.operators.chords[chordDataIndex.value].second+octaveDataIndex.value;
            if ($('#arpeggioQuery').is(':checked')) {
                return [true, basicTone, firstTone, secondTone];
            }else{
                return [false, basicTone, firstTone, secondTone];
            }
            break;
    }
}

function mappingChord(string){
    //If for one Operator is no chord set, use the default pitch settings
    switch(string){
        case 'Operator-ID':
            $('#chordSettings').show();
            $('#chordSettings').empty();
            var html = 'Chord Settings<br>';
            var j;
            arpeggio = [];
            for (j=0;j<=dataNodes.length-1;j++){
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    html += '' + dataNodes[j].label.split("\n")[0] + ' ';
                    html += '<div nexus-ui=select id=chordOperator-' + j + '></div>';
                    html += '<div nexus-ui=select id=octaveOperator-'+j+'></div>';
                    html += 'Arpeggio: <input type=checkbox id=arpeggio-'+j+'><br>';
                    var tmp = '#arpeggio-' + j;
                    arpeggio.push($(tmp));
                }
            }
            $('#chordSettings').html(html);
            for (j=0;j<=dataNodes.length-1;j++) {
                if(!(dataNodes[j].label.includes('AOP')||dataNodes[j].label.includes('OPBaseCompound'))) {
                    var string = '#chordOperator-' + j;
                    chordOperator.push(new Nexus.Select(string, {
                        'size': [100, 40],
                        'options': Object.keys(App.operators.chords)

                    }));
                    string = '#octaveOperator-'+j;
                    octaveOperator.push(new Nexus.Select(string, {
                        'size': [50,40],
                        'options': App.operators.octave
                    }));
                }
            }
            break;
        case 'Operator-Depth':
            $('#chordSettings').show();
            $('#chordSettings').empty();
            arpeggio = [];
            calcDifferentPositions();
            html+="Chord Settings<br>"
            for(i=0;i<=differentPositions.length-1;i++){
                html+='Layer '+i;
                html += '<div nexus-ui=select id=chordOperatorDepth-' + i + '></div>';
                html += '<div nexus-ui=select id=octaveOperatorDepth-'+i+'></div>';
                html += 'Arpeggio: <input type=checkbox id=arpeggio-'+i+'><br>';
                var tmp = '#arpeggio-' + i;
                arpeggio.push($(tmp));
            }

            $('#chordSettings').html(html);
            for (i=0;i<=differentPositions.length-1;i++) {
                var string = '#chordOperatorDepth-' + i;
                chordOperatorDepth.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                }));
                string = '#octaveOperatorDepth-'+i;
                octaveOperatorDepth.push(new Nexus.Select(string, {
                    'size': [50,40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Operator-Type':
            $('#chordSettings').show();
            $('#chordSettings').empty();
            var html = 'Chord Seetings';
            calcDifferentTypes();
            arpeggio = [];
            for(i=0;i<=differentTypes.length-1;i++){
                html+='Type: '+differentTypes[i];
                html += '<div nexus-ui=select id=chordOperatorType-' + i + '></div>';
                html += '<div nexus-ui=select id=octaveOperatorType-'+i+'></div>';
                html += 'Arpeggio: <input type=checkbox id=arpeggio-'+i+'><br>';
                var tmp = '#arpeggio-' + i;
                arpeggio.push($(tmp));
            }

            $('#chordSettings').html(html);
            for (i=0;i<=differentTypes.length-1;i++) {
                var string = '#chordOperatorType-' + i;
                chordOperatorType.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                }));
                string = '#octaveOperatorType-'+i;
                octaveOperatorType.push(new Nexus.Select(string, {
                    'size': [50,40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Operator-Variable':
            $('#chordSettings').show();
            $('#chordSettings').empty();

            var html = 'Chord Settings<br>';

            var i;
            calcDifferentOperatorVariables();
            arpeggio = [];
            for(i=0;i<=differentOperatorVariables.length-1;i++){
                html+='Variable: '+differentOperatorVariables[i];
                html += '<div nexus-ui=select id=chordOperatorVariable-' + i + '></div>';
                html += '<div nexus-ui=select id=octaveOperatorVariable-'+i+'></div>';
                html += 'Arpeggio: <input type=checkbox id=arpeggio-'+i+'><br>';
                var tmp = '#arpeggio-' + i;
                arpeggio.push($(tmp));
            }

            $('#chordSettings').html(html);
            for (i=0;i<=differentOperatorVariables.length-1;i++) {
                var string = '#chordOperatorVariable-' + i;
                chordOperatorVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                }));
                string = '#octaveOperatorVariable-'+i;
                octaveOperatorVariable.push(new Nexus.Select(string, {
                    'size': [50,40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Data-Index':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#chordSettings').show();
            $('#chordSettings').empty();
            var html='<fieldset>';
            html+="Chord Settings<br>";
            html+='Chord'
            html += '<div nexus-ui=select id=chordDataIndex></div>';
            html += '<div nexus-ui=select id=octaveDataIndex></div>';
            html += 'Arpeggio: <input type=checkbox id=arpeggioIndex><br>';
            html += '</fieldset>';
            $('#chordSettings').html(html);

            chordDataIndex = new Nexus.Select('#chordDataIndex', {
                'size': [100, 40],
                'options': Object.keys(App.operators.chords)
            });
            octaveDataIndex = new Nexus.Select('#octaveDataIndex', {
                'size': [50,40],
                'options': App.operators.octave
            });
            break;
        case 'Data-Variable':
            $('#chordSettings').show();
            $('#chordSettings').empty();
            arpeggio = [];
            var html = 'Chord Settings<br>';

            var i;
            calcDifferentDataVariables();

            for(i=0;i<=differentDataVariables.length-1;i++){
                html+='Variable: '+differentDataVariables[i];
                html += '<div nexus-ui=select id=chordDataVariable-' + i + '></div>';
                html += '<div nexus-ui=select id=octaveDataVariable-' + i + '></div>';
                html += 'Arpeggio: <input type=checkbox id=arpeggio-'+i+'><br>';
                var tmp = '#arpeggio-' + i;
                arpeggio.push($(tmp));
            }

            $('#chordSettings').html(html);
            for (i=0;i<=differentDataVariables.length-1;i++) {
                var string = '#chordDataVariable-' + i;
                chordDataVariable.push(new Nexus.Select(string, {
                    'size': [100, 40],
                    'options': Object.keys(App.operators.chords)
                }));
                string = '#octaveDataVariable-' + i;
                octaveDataVariable.push(new Nexus.Select(string, {
                    'size': [50,40],
                    'options': App.operators.octave
                }));
            }
            break;
        case 'Query-Progress':
            //This setting does not make much sense.
            //Instead, simple selection will be used.
            $('#chordSettings').show();
            $('#chordSettings').empty();
            var html="Chord Settings<br>";
            html+='Chord'
            html += '<div nexus-ui=select id=chordDataIndex></div>';
            html += '<div nexus-ui=select id=octaveDataIndex></div>';
            html += 'Arpeggio: <input type=checkbox id=arpeggioQuery><br>';
            $('#chordSettings').html(html);

            chordDataIndex = new Nexus.Select('#chordDataIndex', {
                'size': [100, 40],
                'options': Object.keys(App.operators.chords)
            });
            octaveDataIndex = new Nexus.Select('#octaveDataIndex', {
                'size': [50,40],
                'options': App.operators.octave
            });
            break;
    }
}
