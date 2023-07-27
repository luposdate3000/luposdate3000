const luposdate3000 = require("../../luposdate3000_endpoint/build/distributions/luposdate3000_endpoint.js")

function evaluate(env, sparql, rdf, useRDF, callback) {
    if (useRDF) {
        luposdate3000.lupos.endpoint.LuposdateEndpoint.close()
        env.instance = luposdate3000.lupos.endpoint.LuposdateEndpoint.initialize()
        luposdate3000.lupos.endpoint.LuposdateEndpoint.import_turtle_string(env.instance, rdf);
    }
    var response = luposdate3000.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(env.instance, sparql);
    console.log(response)
}
export function init(data) {
    data.evaluate = evaluate;
    data.instance = luposdate3000.lupos.endpoint.LuposdateEndpoint.initialize()
    return data
}