var pitchOperator = [];
var pitchOperatorDepth = [];
var pitchOperatorType = [];
var pitchOperatorVariable = [];
var pitchDataVariable = [];
var pitchSimple;

export function pitchSetup() {
    App.mappingFunctions.Pitch = function(string) {
        audioDimensionSetup(string, "Pitch")
    }
}
