var durationOperator = [];
var durationOperatorDepth = [];
var durationOperatorType = [];
var durationOperatorVariable = [];
var durationDataIndex;
var durationDataVariable = [];

export function durationSetup() {
    App.mappingFunctions.Duration = function(string) {
        audioDimensionSetup(string, "Duration")
    }
}
