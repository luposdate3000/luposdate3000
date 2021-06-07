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

function loadInstrument(array, isArray) {
    var object;
    if (isArray) {
        object = array[array.length - 1];
    } else {
        object = array;
    }
    object.on('change', function(v) {
        if (!usedInstruments.includes(v.value)) {
            usedInstruments.push(v.value);
            App.samples = SampleLibrary.load({
                instruments: usedInstruments,
                baseUrl: "./resources/samples/"
            });
            // loop through instruments and set release, connect to master output
            App.samples[v.value].release = .5;
            App.samples[v.value].toDestination();
        }
    });
}