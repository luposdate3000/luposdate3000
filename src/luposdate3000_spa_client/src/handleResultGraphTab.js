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

var network = null;
var cacheGraphs = []
export function updateResultGraphTab(result) {
    if ("optimization_steps" in result) {
        jquery("#result-graph-tab-nav-item").removeClass("hidden")
        cacheGraphs = []
        const cacheGraphsMinified = []
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
            if (cacheGraphs.length == 0) {
                cacheGraphs.push(r)
                cacheGraphsMinified.push(mstr)
            } else if (cacheGraphsMinified[cacheGraphsMinified.length - 1] != mstr) {
                cacheGraphs.push(r)
                cacheGraphsMinified.push(mstr)
            }
        }
        for (const r of cacheGraphs) {
            for (const n of r.nodes) {
                n.color = getColorByType(n.label.split(' ')[0])
            }
        }
        jquery("#result-graph-view-input").val("0")
        setTimeout(function() {
            showGraph(cacheGraphs[0])
        }, 100);
    } else {
        jquery("#result-graph-tab-nav-item").addClass("hidden")
    }
}

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
    } else if (v >= cacheGraphs.length) {
        v = cacheGraphs.length - 1
        jquery("#result-graph-view-input").val(v)
    }
    showGraph(cacheGraphs[v])
});
jquery("#result-graph-tab-trigger").on("click", function() {
    network.fit()
});