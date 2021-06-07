var iterator = 0;

function getMelody(string, id, label, index) {
    var radios = document.getElementsByName('melody');
    var i;
    for (i = 0; i <= radios.length - 1; i++) {
        if (radios[i].checked) {
            switch (radios[i].value) {
                case 'durCad':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case '50s':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["Am"].basic;
                            var firstTone = App.operators.Chord.values["Am"].first;
                            var secondTone = App.operators.Chord.values["Am"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'hit':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["Am"].basic;
                            var firstTone = App.operators.Chord.values["Am"].first;
                            var secondTone = App.operators.Chord.values["Am"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'hitTurned':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["Am"].basic;
                            var firstTone = App.operators.Chord.values["Am"].first;
                            var secondTone = App.operators.Chord.values["Am"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'mill':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["Dm"].basic;
                            var firstTone = App.operators.Chord.values["Dm"].first;
                            var secondTone = App.operators.Chord.values["Dm"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case '251':
                    if (iterator >= 3) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["Dm"].basic;
                            var firstTone = App.operators.Chord.values["Dm"].first;
                            var secondTone = App.operators.Chord.values["Dm"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
                case 'pachebel':
                    if (iterator >= 7) {
                        iterator = 0;
                    }
                    switch (iterator) {
                        case 0:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 1:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                        case 2:
                            var basicTone = App.operators.Chord.values["Am"].basic;
                            var firstTone = App.operators.Chord.values["Am"].first;
                            var secondTone = App.operators.Chord.values["Am"].second;
                            break;
                        case 3:
                            var basicTone = App.operators.Chord.values["Em"].basic;
                            var firstTone = App.operators.Chord.values["Em"].first;
                            var secondTone = App.operators.Chord.values["Em"].second;
                            break;
                        case 4:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 5:
                            var basicTone = App.operators.Chord.values["C"].basic;
                            var firstTone = App.operators.Chord.values["C"].first;
                            var secondTone = App.operators.Chord.values["C"].second;
                            break;
                        case 6:
                            var basicTone = App.operators.Chord.values["F"].basic;
                            var firstTone = App.operators.Chord.values["F"].first;
                            var secondTone = App.operators.Chord.values["F"].second;
                            break;
                        case 7:
                            var basicTone = App.operators.Chord.values["G"].basic;
                            var firstTone = App.operators.Chord.values["G"].first;
                            var secondTone = App.operators.Chord.values["G"].second;
                            break;
                    }
                    iterator++;
                    if ($('#arpeggioMelody').is(':checked')) {
                        return [true, basicTone, firstTone, secondTone];
                    } else {
                        return [false, basicTone, firstTone, secondTone];
                    }
                    break;
            }
        }
    }

}

function melodySetup() {
    App.mappingFunctions.Melody = function(string) {
        App.config.sonification.Melody.mode = string
        switch (string) {
            case "No":
                $('#melodySettings').hide();
                break;
            default:
                //Mapping of melody is a bit different
                $('#melodySettings').show();
                $('#melodySettings').empty();
                var html = '<fieldset>';

                var i;
                html += '<div style=overflow:hidden;>';
                html += '<p style=float:left;margin-right:10px;>Arpeggio: </p>';
                html += '<input type=checkbox id=arpeggioMelody style=float:left;margin-right:10px;margin-top:3px;><br>';
                html += '</div>';
                html += '<p>Select a melody: </p>';
                html += '<input type=radio id=durCad name=melody value=durCad checked> Dur cadence<br>';
                html += '<input type=radio id=50s name=melody value=50s> 50s formula<br>';
                html += '<input type=radio id=hit name=melody value=hit> Hit formula<br>';
                html += '<input type=radio id=hitTurned name=melody value=hitTurned> Turned hit formula<br>';
                html += '<input type=radio id=mill name=melody value=mill> Millennium cadence<br>';
                html += '<input type=radio id=251 name=melody value=251> 2-5-1 connection<br>';
                html += '<input type=radio id=pachebel name=melody value=pachebel> Pachebel Canon<br>';

                $('#melodySettings').html(html);
                console.log("setup melody '" + string + "'")
                $('#durCad').prop('checked', true);
                break;
        }
    }
}