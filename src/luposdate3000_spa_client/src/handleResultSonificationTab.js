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
export function updateResultSonificationTab(result) {
    if ("optimization_steps" in result) {
        document.querySelector("#result-sonification-tab-nav-item").style.display = "list-item"
        resultCache = result.optimization_steps[result.optimization_steps.length - 1]
        for (const n of resultCache.nodes) {
            n.color = getColorByType(n.label.split(' ')[0])
        }
        showSonification()
    } else {
        document.querySelector("#result-sonification-tab-nav-item").style.display = "none"
    }
}
var network = null;

function showSonification() {
    if (network != null) {
        network.destroy();
    }
    network = new Network(document.getElementById("result-sonification-view"), {
        nodes: new DataSet(resultCache.nodes),
        edges: new DataSet(resultCache.edges)
    }, visNetworkOptions);
    setTimeout(function() {
        network.fit();
    }, 100)
}

jquery("#result-sonification-tab-trigger").on("click", function() {
    network.fit()
});