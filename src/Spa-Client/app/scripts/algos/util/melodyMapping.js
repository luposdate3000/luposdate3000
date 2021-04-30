var iterator = 0;
var octaveMelody;

function getMelody(string, id, label, index){
    var radios = document.getElementsByName('melody');
    var i;
    for (i=0;i<=radios.length-1;i++){
        if(radios[i].checked){
            switch(radios[i].value){
                case 'durCad':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case '50s':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["Am"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Am"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Am"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'hit':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["Am"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Am"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Am"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'hitTurned':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["Am"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Am"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Am"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'mill':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["Dm"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Dm"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Dm"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case '251':
                    if(iterator >= 3){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["Dm"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Dm"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Dm"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'pachebel':
                    if(iterator >= 7){
                        iterator = 0;
                    }
                    switch(iterator){
                        case 0:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 1:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                        case 2:
                            var basicTone = App.operators.chords["Am"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Am"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Am"].second+octaveMelody.value;
                            break;
                        case 3:
                            var basicTone = App.operators.chords["Em"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["Em"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["Em"].second+octaveMelody.value;
                            break;
                        case 4:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 5:
                            var basicTone = App.operators.chords["C"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["C"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["C"].second+octaveMelody.value;
                            break;
                        case 6:
                            var basicTone = App.operators.chords["F"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["F"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["F"].second+octaveMelody.value;
                            break;
                        case 7:
                            var basicTone = App.operators.chords["G"].basic+octaveMelody.value;
                            var firstTone = App.operators.chords["G"].first+octaveMelody.value;
                            var secondTone = App.operators.chords["G"].second+octaveMelody.value;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    }else{
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
            }
        }
    }

}

function mappingMelody(string){
    //Mapping of melody is a bit different
    $('#melodySettings').show();
    $('#melodySettings').empty();
    var html = '<fieldset>';
    html += 'Melody Settings<br>';

    var i;
    html += 'Arpeggio: <input type=checkbox id=arpeggioMelody><br>';
    html += 'Octave <div nexus-ui=select id=octaveMelody></div>';
    html+= '<fieldset>';
    html+='<input type=radio id=durCad name=melody cadence value=durCad> Dur cadence<br>';
    html+='<input type=radio id=50s name=melody value=50s> 50s formula<br>';
    html+='<input type=radio id=hit name=melody value=hit> Hit formula<br>';
    html+='<input type=radio id=hitTurned name=melody value=hitTurned> Turned hit formula<br>';
    html+='<input type=radio id=mill name=melody value=mill> Millennium cadence<br>';
    html+='<input type=radio id=251 name=melody value=251> 2-5-1 connection<br>';
    html+='<input type=radio id=pachebel name=melody value=pachebel> Pachebel Canon<br>';
    html+='</fieldset>'

    $('#melodySettings').html(html);

    octaveMelody = new Nexus.Select('#octaveMelody', {
        'size': [50,40],
        'options': App.operators.octave
    });
}
