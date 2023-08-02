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
var nodes = null
var cacheGraph = []
var cacheAnimation = []
var animationStep = 0
var animationSpeed = 1
var animationState = 'stop';
var animationStepDelay = 500
var animationVisualDelay = 30
var animationRunning = false
export function updateResultSonificationTab(result) {
    if ("optimization_steps" in result && "animation" in result) {
        cacheAnimation = []
        animationSpeed = 0
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
    nodes = new DataSet(cacheGraph.nodes);
    nodes.add([{
        id: 999,
        label: ""
    }]);
    network = new Network(document.getElementById("result-sonification-view"), {
        nodes: nodes,
        edges: new DataSet(cacheGraph.edges)
    }, visNetworkOptions);
    setTimeout(function() {
        clearAnimationElement()
    }, 100)
}

function clearAnimationElement() {
    nodes.updateOnly({
        id: 999,
        label: ""
    });
    const p = network.getPosition(cacheGraph.nodes[0].id);
    network.moveNode(999, p.x, p.y);
    network.fit();
}

function animationLoop() {
    if (!animationRunning) {
        animationRunning = true
        if (animationSpeed !== 0) {
            animationStep += animationSpeed
            if (animationStep < 0) {
                animationStep = 0
            }
            if (animationStep > cacheAnimation.length - 1) {
                animationRunning = false
                return
            }
            const currentAnimation = cacheAnimation[animationStep]
            const pFrom = network.getPosition(currentAnimation.from)
            const pTo = network.getPosition(currentAnimation.to)
            nodes.updateOnly({
                id: 999,
                label: currentAnimation.label
            });

            const loopCtrLimit = animationStepDelay / animationVisualDelay;
            var loopCtr = loopCtrLimit - 1
            const x_delta = (pFrom.x - pTo.x) / loopCtrLimit
            const y_delta = (pFrom.y - pTo.y) / loopCtrLimit
            const looper = setInterval(function() {
                loopCtr--;
                if (loopCtr <= 0) {
                    clearInterval(looper);
                } else {
                    network.moveNode(999, pTo.x + x_delta * loopCtr, pTo.y + y_delta * loopCtr);
                }
            }, animationVisualDelay);

            setTimeout(() => {
                animationLoop();
            }, animationStepDelay);
        }
        animationRunning = false
    }
}

jquery("#result-sonification-tab-trigger").on("click", function() {
    network.fit()
});

jquery("#result-sonification-btn-fbw").on("click", function() {
    animationStep = 0
});

jquery("#result-sonification-btn-bw").on("click", function() {
    if (animationStep > 0) {
        animationStep -= 1
    }
});

jquery("#result-sonification-btn-fw").on("click", function() {
    if (animationStep < cacheAnimation.length - 1) {
        animationStep += 1
    }
});

jquery("#result-sonification-btn-ffw").on("click", function() {
    animationStep = cacheAnimation.length - 1
});

jquery("#result-sonification-btn-play").on("click", function() {
    jquery("#result-sonification-btn-play").empty()
    if (animationState == 'stop') {
        jquery("#result-sonification-btn-play").append('<i class="fa fa-pause">')
        animationState = 'play';
        animationSpeed = 1
        animationLoop()
    } else if (animationState == 'play' || animationState == 'resume') {
        jquery("#result-sonification-btn-play").append('<i class="fa fa-play">')
        animationState = 'pause';
        animationSpeed = 0
        clearAnimationElement()
    } else if (animationState == 'pause') {
        jquery("#result-sonification-btn-play").append('<i class="fa fa-pause">')
        animationState = 'resume';
        animationSpeed = 1
        animationLoop()
    }
});

jquery("#result-sonification-btn-stop").on("click", function() {
    jquery("#result-sonification-btn-play").empty()
    jquery("#result-sonification-btn-play").append('<i class="fa fa-play">')
    animationState = 'stop';
    animationSpeed = 0
    animationStep = 0
    clearAnimationElement()
});