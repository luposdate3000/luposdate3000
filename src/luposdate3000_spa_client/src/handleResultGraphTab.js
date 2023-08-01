import {
    DataSet
} from "vis-data/peer";
import {
    Network
} from "vis-network/peer";
import "vis-network/styles/vis-network.css";
const jquery = require("jquery")

var resultCache = []
export function updateResultGraphTab(result) {
    document.querySelector("#result-graph-tab-nav-item").style.display = "list-item"
    resultCache = result.optimization_steps
    showGraph(resultCache[0])
}

function showGraph(g) {
    console.log(g)
    const nodes = new DataSet(g.nodes)
    const edges = new DataSet(g.edges)
    const container = document.getElementById("result-graph-view");
    const data = {
        nodes: nodes,
        edges: edges
    };
    const options = {
        physics: {
            enabled: false,
            hierarchicalRepulsion: {
                centralGravity: 0.0,
                springLength: 100,
                springConstant: 0.01,
                nodeDistance: 200,
                damping: 0.09
            }
        },
        nodes: {
            borderWidth: 1
        },
        layout: {
            hierarchical: {
                levelSeparation: 100,
                nodeSpacing: 400,
                treeSpacing: 500,
                direction: "UD",
                blockShifting: true,
                edgeMinimization: true,
                sortMethod: "directed"
            }
        }

    };
    const network = new Network(container, data, options);
}

jquery("#result-graph-view-input").on("change", function() {
    const v = parseInt(jquery("#result-graph-view-input").val());
    console.log("onchange", v);
    if (v) {
        if (v >= 0 && v < resultCache.size) {
            showGraph(resultCache[v])
        }
    }
});
