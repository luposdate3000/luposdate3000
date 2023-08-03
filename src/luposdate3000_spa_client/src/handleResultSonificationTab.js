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

function createOrAppend(arr, key, element) {
    if (key in arr) {
        arr[key].push(element)
    } else {
        arr[key] = [element]
    }
}

function reverseArray(src, dest) {
    for (const k in src) {
        for (const v of src[k]) {
            createOrAppend(dest, v, k)
        }
    }
}

export function updateResultSonificationTab(result) {
    if ("optimization_steps" in result && "animation" in result) {
        cacheAnimation = []
        animationSpeed = 0
        sonificationRanges = {
            "operator": {
                "types": {},
                "ids": {},
                "variables": {},
                "depths": {}
            },
            "query": {
                "progress": {}
            },
            "data": {
                "ids": {},
                "variables": {}
            }
        }
        sonificationRangesReverse = {
            "operator": {
                "types": {},
                "ids": {},
                "variables": {},
                "depths": {}
            },
            "query": {
                "progress": {}
            },
            "data": {
                "ids": {},
                "variables": {}
            }
        }
        document.querySelector("#result-sonification-tab-nav-item").style.display = "list-item"
        cacheGraph = result.optimization_steps[result.optimization_steps.length - 1]
        sonificationRangesReverse.operator.depths[cacheGraph.nodes[0].id] = 0
        var changed = true
        while (changed) {
            changed = false
            for (const n of cacheGraph.edges) {
                if (n.from in sonificationRangesReverse.operator.depths && (!(n.to in sonificationRangesReverse.operator.depths))) {
                    changed = true
                    sonificationRangesReverse.operator.depths[n.to] = sonificationRangesReverse.operator.depths[n.from] + 1
                }
            }
        }
        const relevantNodes = {}
        for (const nn in result.animation) {
            const n = result.animation[nn]
            const l = n[2].split(" = ")
            relevantNodes[n[0]] = 1
            createOrAppend(sonificationRanges.query.progress, nn, nn)
            createOrAppend(sonificationRanges.data.ids, n[3], nn)
            createOrAppend(sonificationRanges.data.variables, l[0], nn)
            cacheAnimation.push({
                "from": n[0],
                "to": n[1],
                "label": n[2],
                "id": n[3]
            })
        }
        for (const n in sonificationRangesReverse.operator.depths) {
            const nn = sonificationRangesReverse.operator.depths[n]
            if (nn in relevantNodes) {
                createOrAppend(sonificationRanges.operator.depths, nn, n)
            } else {
                delete sonificationRangesReverse.operator.depths[n]
            }
        }

        for (const n of cacheGraph.nodes) {
            const l = n.label.split(' ')
            const l2 = l[1].split("\n")
            n.color = getColorByType(l[0])
            if (n.id in relevantNodes) {
                createOrAppend(sonificationRanges.operator.types, l[0], n.id)
                createOrAppend(sonificationRanges.operator.ids, l2[0], n.id)
                createOrAppend(sonificationRanges.operator.variables, l2[1], n.id)
            }
        }
        reverseArray(sonificationRanges.operator.types, sonificationRangesReverse.operator.types)
        reverseArray(sonificationRanges.operator.ids, sonificationRangesReverse.operator.ids)
        reverseArray(sonificationRanges.operator.variables, sonificationRangesReverse.operator.variables)
        reverseArray(sonificationRanges.data.variables, sonificationRangesReverse.data.variables)
        reverseArray(sonificationRanges.data.ids, sonificationRangesReverse.data.ids)
        reverseArray(sonificationRanges.query.progress, sonificationRangesReverse.query.progress)
        console.log(cacheGraph)
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