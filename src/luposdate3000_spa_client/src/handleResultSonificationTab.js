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
var cacheGraph = []
export function updateResultSonificationTab(result) {
console.log(result)
    if ("optimization_steps" in result) {
        document.querySelector("#result-sonification-tab-nav-item").style.display = "list-item"
        cacheGraph = result.optimization_steps[result.optimization_steps.length - 1]
        for (const n of cacheGraph.nodes) {
            n.color = getColorByType(n.label.split(' ')[0])
        }
        showSonification()
    } else {
        document.querySelector("#result-sonification-tab-nav-item").style.display = "none"
    }
}

function showSonification() {
    if (network != null) {
        network.destroy();
    }
    network = new Network(document.getElementById("result-sonification-view"), {
        nodes: new DataSet(cacheGraph.nodes),
        edges: new DataSet(cacheGraph.edges)
    }, visNetworkOptions);
    setTimeout(function() {
        network.fit();
    }, 100)
}

jquery("#result-sonification-tab-trigger").on("click", function() {
    network.fit()
});
