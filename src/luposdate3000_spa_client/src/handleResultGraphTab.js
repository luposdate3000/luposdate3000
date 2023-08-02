import {
    DataSet
} from "vis-data/peer";
import {
    Network
} from "vis-network/peer";
import "vis-network/styles/vis-network.css";
const jquery = require("jquery")
import {
    getColorByType
} from "./handleConfiguration.js"
import {
    visNetworkOptions
} from "./util.js"

var resultCache = []
export function updateResultGraphTab(result) {
    if ("optimization_steps" in result) {
        document.querySelector("#result-graph-tab-nav-item").style.display = "list-item"
        resultCache = []
        const resultCacheMinified = []
        for (const r of result.optimization_steps) {
            const m = {
                edges: r.edges,
                nodes: []
            }
            for (const n of r.nodes) {
                const a = n.label.split(" ")
                const b = a[1].substring(a[1].indexOf("\n") + 1);
                const l = a[0] + b
                m.nodes.push({
                    id: n.id,
                    label: l
                })
            }
            const mstr = JSON.stringify(m)
            if (resultCache.length == 0) {
                resultCache.push(r)
                resultCacheMinified.push(mstr)
            } else if (resultCacheMinified[resultCacheMinified.length - 1] != mstr) {
                resultCache.push(r)
                resultCacheMinified.push(mstr)
            }
        }
        for (const r of resultCache) {
            for (const n of r.nodes) {
                n.color = getColorByType(n.label.split(' ')[0])
            }
        }
        jquery("#result-graph-view-input").val("0")
        setTimeout(function() {
            showGraph(resultCache[0])
        }, 100);
    } else {
        document.querySelector("#result-graph-tab-nav-item").style.display = "none"
    }
}
var network = null;

function showGraph(g) {
    if (network != null) {
        network.destroy();
    }
    network = new Network(document.getElementById("result-graph-view"), {
        nodes: new DataSet(g.nodes),
        edges: new DataSet(g.edges)
    }, visNetworkOptions);
    setTimeout(function() {
        network.fit();
    }, 100)
}

jquery("#result-graph-view-input").on("change", function() {
    const v1 = jquery("#result-graph-view-input").val()
    var v = parseInt(v1);
    if (v != v1) {
        v = 0
        jquery("#result-graph-view-input").val(v)
    }
    if (v < 0) {
        v = 0
        jquery("#result-graph-view-input").val(v)
    } else if (v >= resultCache.length) {
        v = resultCache.length - 1
        jquery("#result-graph-view-input").val(v)
    }
    showGraph(resultCache[v])
});
jquery("#result-graph-tab-trigger").on("click", function() {
    network.fit()
});