const jquery = require("jquery")
import {
    init as initLuposdate3000Browser
} from "./endpointLuposdate3000Browser.js"
import {
    init as initLuposdate
} from "./endpointLuposdate.js"
import {
    getUseRDF
} from "./handleConfiguration.js"
import {
    getRDF,
    getSparql
} from "./handleCodeMirror.js"
const randomColor = require('randomcolor');

const availableEndpoints = [
    initLuposdate3000Browser({
        name: "Browser Luposdate3000"
    }),
    initLuposdate({
        name: "Luposdate",
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

function resultXMLToHTMLUri(uri, namespaces, colors, target) {
    for (var k in namespaces) {
        const v = namespaces[k];
        if (uri.startsWith(v)) {
            const span = document.createElement('span');
            span.appendChild(document.createTextNode(k + ":"));
            span.style.color = colors[k];
            target.appendChild(span);
            target.appendChild(document.createTextNode(uri.substring(v.length)));
            return
        }
    }
    target.appendChild(document.createTextNode("<" + uri + ">"));
}

function resultXMLToHTML(data, namespaces) {
    const div = document.createElement('div');
    const tbl = document.createElement('table');
    const tblh = document.createElement('thead');
    const tblb = document.createElement('tbody');
    const tblhr = tblh.insertRow();
    for (var xmlsparql of jquery.parseXML(data).getElementsByTagName("sparql")) {
        const boolResult = xmlsparql.getElementsByTagName("boolean")
        if (boolResult.length > 0) {
            const th = document.createElement('th');
            th.appendChild(document.createTextNode("boolean"));
            tblhr.appendChild(th);
            const tr = tblb.insertRow();
            const td = tr.insertCell();
            td.appendChild(document.createTextNode(boolResult[0].textContent))
        } else {
            const tbln = document.createElement('table');
            const colors = {};
            for (var k in namespaces) {
                const color = randomColor({
                    luminosity: 'dark'
                });
                colors[k] = color;
                const v = namespaces[k];
                const tr = tbln.insertRow();
                const td1 = tr.insertCell();
                td1.appendChild(document.createTextNode(k));
                td1.style.color = color;
                const td2 = tr.insertCell();
                td2.appendChild(document.createTextNode(v));
            }
            const h4 = document.createElement('h4');
            h4.appendChild(document.createTextNode("Prefixes"));
            div.appendChild(h4);
            div.appendChild(tbln);
            const columnNames = [];
            for (var xmlhead of xmlsparql.getElementsByTagName("head")) {
                for (var xmlvariable of xmlsparql.getElementsByTagName("variable")) {
                    const th = document.createElement('th');
                    const name = xmlvariable.getAttribute("name");
                    columnNames.push(name);
                    th.appendChild(document.createTextNode(name));
                    tblhr.appendChild(th);
                }
            }
            for (var xmlresults of xmlsparql.getElementsByTagName("results")) {
                for (var xmlresult of xmlresults.getElementsByTagName("result")) {
                    const tmpRow = {}
                    for (var xmlbinding of xmlresult.getElementsByTagName("binding")) {
                        for (var child of xmlbinding.children) {
                            const div2 = document.createElement('div');
                            if (child.nodeName === "uri") {
                                resultXMLToHTMLUri(child.textContent, namespaces, colors, div2)
                            } else if (child.nodeName === "bnode") {
                                div2.appendChild("_:" + document.createTextNode(child.textContent))
                            } else if (child.nodeName === "literal") {
                                if (child.hasAttribute("datatype")) {
                                    div2.appendChild(document.createTextNode("\"" + child.textContent + "\"^^"));
                                    resultXMLToHTMLUri(child.getAttribute("datatype"), namespaces, colors, div2)
                                } else if (child.hasAttribute("xml:lang")) {
                                    div2.appendChild(document.createTextNode("\"" + child.textContent + "\"@" + child.getAttribute("xml:lang")))
                                } else {
                                    div2.appendChild(document.createTextNode(child.textContent))
                                }
                            } else {
                                console.log("unknown nodeName:", child.nodeName, child)
                            }
                            tmpRow[xmlbinding.getAttribute("name")] = div2
                        }
                    }
                    const tr = tblb.insertRow();
                    for (name of columnNames) {
                        const td = tr.insertCell();
                        if (name in tmpRow) {
                            td.appendChild(tmpRow[name])
                        }
                    }
                }
            }
        }
    }
    tbl.appendChild(tblh);
    tbl.appendChild(tblb);
    const h4 = document.createElement('h4');
    h4.appendChild(document.createTextNode("Results(" + tblb.getElementsByTagName("tr").length + ")"));
    div.appendChild(h4);
    div.appendChild(tbl);
    return div
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
        endpoint.evaluate(endpoint, sparql, rdf, getUseRDF(), function(result) {
            result.html = resultXMLToHTML(result.xml, namespaces)
            jquery("#result").empty();
            const target = document.getElementById('result');
            target.appendChild(result.html);
        });
    }
);