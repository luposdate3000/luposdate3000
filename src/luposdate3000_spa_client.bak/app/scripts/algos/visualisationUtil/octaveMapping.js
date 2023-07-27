var octaveOperator = [];
var octaveOperatorDepth = [];
var octaveOperatorType = [];
var octaveOperatorVariable = [];
var octaveDataIndex;
var octaveDataVariable = [];

export function octaveSetup() {
     App.mappingFunctions.Octave = function(string) {
        audioDimensionSetup(string, "Octave")
    }
}
