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
var cacheAnimation = []
var animationStep = 0
export function updateResultSonificationTab(result) {
    if ("optimization_steps" in result && "animation" in result) {
        cacheAnimation = []
        animationStep = 0
        document.querySelector("#result-sonification-tab-nav-item").style.display = "list-item"
        cacheGraph = result.optimization_steps[result.optimization_steps.length - 1]
        for (const n of cacheGraph.nodes) {
            n.color = getColorByType(n.label.split(' ')[0])
        }
        for (const n of result.animation) {
            cacheAnimation.push({
                "from": n[0],
                "to": n[1],
                "label": n[2],
                "id": n[3]
            })
        }
        console.log(cacheAnimation)
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

var animationSpeed = 1
var animationState = 'stop';
jquery("#result-sonification-btn-fbw").on("click", function() {
    animationStep = 0
});

jquery("#result-sonification-btn-bw").on("click", function() {
    if (animationSpeed >= 0) {
        animationSpeed = -1
    }
});

jquery("#result-sonification-btn-fw").on("click", function() {
    if (animationSpeed >= 0) {
        animationSpeed = 1
    }
});

jquery("#result-sonification-btn-ffw").on("click", function() {
    animationStep = cacheAnimation.length - 1
});

jquery("#result-sonification-btn-play").on("click", function() {
    if (animationState == 'stop') {
        animationState = 'play';
        var button = d3.select("#button_play").classed('btn-success', true);
        button.select("i").attr('class', "fa fa-pause");
animationSpeed = 1
    } else if (animationState == 'play' || animationState == 'resume') {
        animationState = 'pause';
        d3.select("#button_play i").attr('class', "fa fa-play");
animationSpeed = 0
    } else if (animationState == 'pause') {
        animationState = 'resume';
        d3.select("#button_play i").attr('class', "fa fa-pause");
animationSpeed = 1
    }
    console.log("button play pressed, play was " + animationState);
});

jquery("#result-sonification-btn-stop").on("click", function() {
    animationState = 'stop';
    var button = d3.select("#button_play").classed('btn-success', false);
    button.select("i").attr('class', "fa fa-play");
    console.log("button stop invoked.");
});
