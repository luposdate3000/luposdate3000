var loudnessOperator = [];
var loudnessOperatorDepth = [];
var loudnessOperatorType = [];
var loudnessOperatorVariable = [];
var loudnessDataVariable = [];
var loudnessDataIndex;

function loudnessSetup() {
    App.mappingFunctions.Loudness = function(string) {
        audioDimensionSetup(string, "Loudness")
    }
}