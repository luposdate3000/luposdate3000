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


var resultCache = []
export function updateResultGraphTab(result) {
    document.querySelector("#result-graph-tab-nav-item").style.display = "list-item"
    resultCache = result.optimization_steps
console.log("a")
    for (const r of resultCache) {
        for (const n of r.nodes) {
            n.color = getColorByType(n.label.split(' ')[0])
        }
    }
console.log("b")
    jquery("#result-graph-view-input").val("0")
console.log("c")
    setTimeout(function() {
console.log("d")
        showGraph(resultCache[0])
    }, 100);
console.log("e")
}
var network = null;

function showGraph(g) {
    console.log(g)
    if (network != null) {
        network.destroy();
    }
    network = new Network(document.getElementById("result-graph-view"), {
        nodes: new DataSet(g.nodes),
        edges: new DataSet(g.edges)
    }, {
        autoResize: false,
        nodes: {
            physics: false,
            shape: "box",
            font: {
                size: 14,
                color: "#000000",
                face: "Ubuntu",
            },
            borderWidth: 1,
            color: {
                background: "#E1E1E1",
                border: "#000000",
                hover: {
                    background: "#C5C5C5",
                    border: "#000000",
                },
                highlight: {
                    background: "#C5C5C5",
                    border: "#000000",
                },

            },

        },
        edges: {
            smooth: false
        },
        physics: {
            enabled: true,
            hierarchicalRepulsion: {
                nodeDistance: 200,
                avoidOverlap: 1
            },
        },
        layout: {
            improvedLayout: true,
            hierarchical: {
                levelSeparation: 100,
                nodeSpacing: 300,
                treeSpacing: 200,
            }
        },
        interaction: {
            hover: true
        }
    });
    setTimeout(function() {
        network.fit();
    }, 100)
}

jquery("#result-graph-view-input").on("change", function() {
    const v = parseInt(jquery("#result-graph-view-input").val());
    if (v >= 0 && v < resultCache.length - 1) {
        showGraph(resultCache[v])
    }
});
