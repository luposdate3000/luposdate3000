import {
    DataSet
} from "vis-data/peer";
import {
    Network
} from "vis-network/peer";
import "vis-network/styles/vis-network.css";
const jquery = require("jquery")
import {
    getColorByType,
    getAnimationSpeed
} from "./handleConfiguration.js"
import {
    visNetworkOptions
} from "./util.js"
import {
    extractRanges
} from "./sonificationExtractRanges.js";
import {
    createConfigHtml
} from "./handleResultSonificationConfig.js";
var network = null;
var nodes = null
var cacheGraph = []
var cacheAnimation = []
var animationStep = 0
var animationSpeed = 1
var animationState = 'stop';
var animationVisualDelay = 30
var animationRunning = false
var sonificationRanges = {}
var sonificationRangesReverse = {}

export function updateResultSonificationTab(result) {
    if ("optimization_steps" in result && "animation" in result) {
        cacheAnimation = []
        animationSpeed = 0
        document.querySelector("#result-sonification-tab-nav-item").style.display = "list-item"
        cacheGraph = result.optimization_steps[result.optimization_steps.length - 1]
        extractRanges(result, cacheGraph, sonificationRanges, sonificationRangesReverse)
        createConfigHtml(sonificationRanges)
        for (const nn in result.animation) {
            const n = result.animation[nn]
            cacheAnimation.push({
                "from": n[0],
                "to": n[1],
                "label": n[2],
                "id": n[3]
            })
        }

        for (const n of cacheGraph.nodes) {
            const l = n.label.split(' ')
            const l2 = l[1].split("\n")
            n.color = getColorByType(l[0])
        }
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
        jquery("#result-sonification-progress").attr('aria-valuemax', cacheAnimation.length - 1);
        setStep(0)
        console.log(sonificationRanges, sonificationRangesReverse)
    } else {
        document.querySelector("#result-sonification-tab-nav-item").style.display = "none"
    }
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

function setStep(x) {
    animationStep = x
    if (animationStep < 0) {
        animationStep = 0
    }
    const newprogress = animationStep / (cacheAnimation.length - 1)
    jquery("#result-sonification-progress").attr('aria-valuenow', animationStep).css('width', newprogress * 100 + '%').text("" + animationStep + "/" + (cacheAnimation.length - 1));
}

function animationLoop() {
    if (!animationRunning) {
        animationRunning = true
        const speed = getAnimationSpeed()
        if (animationSpeed !== 0) {
            setStep(animationStep + animationSpeed)
            if (animationStep > cacheAnimation.length - 1) {
                animationRunning = false
                jquery("#result-sonification-btn-play").append('<i class="fa fa-play">')
                animationState = 'pause';
                animationSpeed = 0
                return
            }
            const currentAnimation = cacheAnimation[animationStep]
            const pFrom = network.getPosition(currentAnimation.from)
            const pTo = network.getPosition(currentAnimation.to)
            nodes.updateOnly({
                id: 999,
                label: currentAnimation.label
            });

            const loopCtrLimit = speed / animationVisualDelay;
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
            }, speed);
        }
        animationRunning = false
    }
}

jquery("#result-sonification-tab-trigger").on("click", function() {
    network.fit()
});

jquery("#result-sonification-btn-fbw").on("click", function() {
    setStep(0)
});

jquery("#result-sonification-btn-bw").on("click", function() {
    setStep(animationStep - 1)
});

jquery("#result-sonification-btn-fw").on("click", function() {
    setStep(animationStep + 1)
});

jquery("#result-sonification-btn-ffw").on("click", function() {
    setStep(cacheAnimation.length - 1)
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
    setStep(0)
    clearAnimationElement()
});