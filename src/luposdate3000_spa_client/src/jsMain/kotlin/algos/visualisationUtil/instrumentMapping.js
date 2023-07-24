var instrumentOperator = [];
var instrumentOperatorDepth = [];
var instrumentOperatorType = [];
var instrumentOperatorVariable = [];
var instrumentDataIndex;
var instrumentDataVariable = [];

function instrumentSetup() {
    App.mappingFunctions.Instrument = function(string) {
        audioDimensionSetup(string, "Instrument")
    }
}