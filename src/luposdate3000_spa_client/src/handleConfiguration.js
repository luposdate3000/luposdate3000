const jquery = require("jquery")
import {
    setSparql,
    setRDF
} from "./handleCodeMirror.js"
import {
    enableEndpoints
} from "./handleEndpoint.js"

export function setUseRDF(data) {
    if (data) {
        jquery("#useRDF").prop("checked", true);
    } else {
        jquery("#useRDF").prop("checked", false);
    }
}
export function getUseRDF() {
    return jquery("#useRDF").prop("checked");
}

function loadSparql(url) {
    if (url !== null) {
        jquery.get(url, function(data) {
            setSparql(data);
        });
    }
}

function loadRDF(url) {
    if (url !== null) {
        jquery.get(url, function(data) {
            setRDF(data);
        });
    }
}

function loadData(url) {
    if (url === null) {
        url = "resources/cloud-and-web-technologies/data1.json";
    }
    jquery.getJSON(url, function(data) {
        loadSparql(data.sparql);
        loadRDF(data.rdf);
    });
}

function loadConfig(url) {
    if (url === null) {
        url = "resources/config.json";
    }
    jquery.getJSON(url, function(data) {
        setUseRDF(data.useRDF)
        enableEndpoints(data.endpoints)
    });
}

const urlParams = new URLSearchParams(window.location.search);
loadData(urlParams.get('data'));
loadConfig(urlParams.get('config'));