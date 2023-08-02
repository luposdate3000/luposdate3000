//const luposdate3000 = require("../../luposdate3000_endpoint/build/distributions/luposdate3000_endpoint.js")
const luposdate3000 = require("../../luposdate3000_endpoint/build/developmentExecutable/luposdate3000_endpoint.js")

function evaluate(env, sparql, rdf, useRDF, withGraph, callback) {
    if (useRDF) {
        luposdate3000.lupos.endpoint.LuposdateEndpoint.close()
        env.instance = luposdate3000.lupos.endpoint.LuposdateEndpoint.initialize()
        luposdate3000.lupos.endpoint.LuposdateEndpoint.import_turtle_string(env.instance, rdf);
    }
    if (withGraph) {
        const res = {
            optimization_steps: [],
            animation: []
        }
        const eev = new luposdate3000.lupos.endpoint.EndpointExtendedVisualize(sparql, env.instance);
        for (const e of eev.getOptimizedStepsLogical()) {
            res.optimization_steps.push(JSON.parse(e.toJson()))
        }
        for (const e of eev.getOptimizedStepsPhysical()) {
            res.optimization_steps.push(JSON.parse(e.toJson()))
        }
        res.xml = eev.getResult();
        for (const e of eev.getDataSteps()) {
            res.animation.push(JSON.parse(e));
        }
        callback(res);
    } else {
        callback({
            xml: luposdate3000.lupos.endpoint.LuposdateEndpoint.evaluate_sparql_to_result_b(env.instance, sparql)
        });
    }
}
export function init(env) {
    env.evaluate = evaluate;
    env.instance = luposdate3000.lupos.endpoint.LuposdateEndpoint.initialize()
    return env
}