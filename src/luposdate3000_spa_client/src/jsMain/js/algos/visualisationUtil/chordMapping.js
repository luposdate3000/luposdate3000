var chordOperator = [];
var chordOperatorDepth = [];
var chordOperatorType = [];
var chordOperatorVariable = [];
var chordDataIndex;
var chordDataVariable = [];
var arpeggio = [];

function chordSetup() {
    App.mappingFunctions.Chord = function(string) {
        audioDimensionSetup(string, "Chord")
    }
}