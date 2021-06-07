var spatOperator = [];
var spatOperatorDepth = [];
var spatOperatorType = [];
var spatOperatorVariable = [];
var spatDataVariable = [];
var spatDataIndex;

function spatializationSetup() {
    App.mappingFunctions.Spatialization = function(string) {
        audioDimensionSetup(string, "Spatialization")
    }
}