const jquery = require("jquery")

function evaluate(env, sparql, rdf, useRDF, callback) {
    var data = {}
    data.query = sparql
    if (useRDF) {
        data.rdf = rdf
    } else {
        data.rdf = ""
    }
    data.formats = ["xml", "plain"]
    data.inference = "NONE"
    data.inferenceGeneration = "GENERATEDOPT"
    data.evaluator = "MemoryIndex"
    data = JSON.stringify(data);
    jquery.ajax({
        type: 'POST',
        url: env.url,
        crossDomain: true,
        data: data,
        dataType: 'json',
        success: function(responseData, textStatus, jqXHR) {
            for (const response of responseData.XML) {
                callback({
                    xml: response
                });
            }
        },
        error: function(responseData, textStatus, errorThrown) {
            console.log("error", responseData, textStatus, errorThrown)
        }
    });
}
export function init(env) {
    env.evaluate = evaluate;
    return env
}