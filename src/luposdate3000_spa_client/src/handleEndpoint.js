const jquery = require("jquery")
import {
    init as initLuposdate3000Browser
} from "./endpointLuposdate3000Browser.js"
import {
    init as initLuposdate
} from "./endpointLuposdate.js"
import {
    getUseRDF,
    getWithGraph
} from "./handleConfiguration.js"
import {
    getRDF,
    getSparql
} from "./handleCodeMirror.js"
import {
    updateResultTab
} from "./handleResultTab.js"
import {
    updateResultGraphTab
} from "./handleResultGraphTab.js"
import {
    updateResultSonificationTab
} from "./handleResultSonificationTab.js"

const availableEndpoints = [
    initLuposdate3000Browser({
        name: "Luposdate3000 Browser"
    }),
    initLuposdate({
        name: "Luposdate IFIS",
        url: "https://www.ifis.uni-luebeck.de/sparql-endpoint/nonstandard/sparql"
    })
];
var enabledEndpoints = {};
export function enableEndpoints(data) {
    enabledEndpoints = {};
    jquery("#endpoint").empty();
    for (var endpoint of availableEndpoints) {
        if (jquery.inArray(endpoint.name, data) !== -1) {
            enabledEndpoints[endpoint.name] = endpoint;
            jquery("#endpoint").append(jquery("<option></option>").attr("value", endpoint.name).text(endpoint.name));
        }
    }
}

function getEndpoint() {
    return enabledEndpoints[jquery("#endpoint").val()]
}

function extractSPARQLPrefixes(data) {
    let m;
    const prefixes = {};
    let reg = /base\s+<([^>]+)>/ig;
    while (m = reg.exec(data)) {
        prefixes[''] = m[1];
    }
    reg = /prefix\s+([a-zA-Z0-9-]+)\s*:\s*<([^>]+)>/ig;
    while (m = reg.exec(data)) {
        prefixes[m[1]] = m[2];
    }
    return prefixes;
}

function extractRDFPrefixes(data) {
    let m;
    const prefixes = {};
    let reg = /@prefix\s+:\s*<([^>]+)>\s*\./g;
    while (m = reg.exec(data)) {
        prefixes[''] = m[1];
    }
    reg = /@prefix\s+([a-zA-Z0-9-]+):\s*<([^>]+)>\s*\./g;
    while (m = reg.exec(data)) {
        prefixes[m[1]] = m[2];
    }
    return prefixes;
}

jquery("#evaluate").click(
    function() {
        const endpoint = getEndpoint();
        const sparql = getSparql();
        const rdf = getRDF();
        const namespaces = {};
        namespaces.rdf = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
        namespaces.rdfs = 'http://www.w3.org/2000/01/rdf-schema#';
        namespaces.owl = 'http://www.w3.org/2002/07/owl#';
        namespaces.xsd = 'http://www.w3.org/2001/XMLSchema#';
        jquery.extend(namespaces, extractRDFPrefixes(rdf))
        jquery.extend(namespaces, extractSPARQLPrefixes(sparql))
        const useRDF = getUseRDF()
        const withGraph = getWithGraph()
        endpoint.evaluate(endpoint, sparql, rdf, useRDF, withGraph, function(result) {
            if ("error" in result) {
                updateResultTab(result)
            } else {
                result.namespaces = namespaces
                updateResultTab(result)
                if (withGraph) {
                    updateResultGraphTab(result)
                    updateResultSonificationTab(result)
                }
            }
        });
    }
);