const jquery = require("jquery")

const sonificationConf = {}

const sonificationOptions = {
    "Pitch": {
        "mode": "values",
        "default": "C",
        "values": ["C",
            "C#",
            "Db",
            "D",
            "D#",
            "Eb",
            "E",
            "F",
            "F#",
            "Gb",
            "G",
            "G#",
            "Ab",
            "A",
            "A#",
            "Bb",
            "B"
        ]
    },
    "Instrument": {
        "mode": "values",
        "default": "piano",
        "values": ["piano",
            "bass-electric",
            "bassoon",
            "cello",
            "clarinet",
            "contrabass",
            "flute",
            "french-horn",
            "guitar-acoustic",
            "guitar-electric",
            "guitar-nylon",
            "harmonium",
            "harp",
            "organ",
            "saxophone",
            "trombone",
            "trumpet",
            "tuba",
            "violin",
            "xylophone"
        ]
    },
    "Duration": {
        "mode": "values",
        "default": "1n",
        "values": ["1n",
            "2n",
            "4n",
            "8n",
            "16n"
        ]
    },
    "Octave": {
        "mode": "values",
        "default": "3",
        "values": [1,
            2,
            3,
            4,
            5,
            6,
            7
        ]
    },
    "Loudness": {
        "mode": "range",
        "min": 0.0,
        "max": 1.0,
        "default": 0.5
    },
    "Spatialization": {
        "mode": "range",
        "min": -1.0,
        "max": 1.0,
        "default": 0.0
    }
}

export function createConfigHtml(sonificationRanges) {
    jquery("#sonification-config-root").empty()
    const target = document.getElementById("sonification-config-root");
    createConfigHtmlForLabel(target, sonificationRanges, "Pitch", ["mb-3"]);
    createConfigHtmlForLabel(target, sonificationRanges, "Instrument", ["mb-3"]);
    createConfigHtmlForLabel(target, sonificationRanges, "Loudness", ["mb-3"]);
    createConfigHtmlForLabel(target, sonificationRanges, "Duration", ["mb-3"]);
    createConfigHtmlForLabel(target, sonificationRanges, "Octave", ["mb-3"]);
    createConfigHtmlForLabel(target, sonificationRanges, "Spatialization", []);
}


function createOptionWithValue(value, defaultOption) {
    const res = document.createElement("option");
    res.value = value
    res.textContent = value
    if (value == defaultOption) {
        res.selected = "selected"
    }
    return res
}

function createLabelWithSelector(label, id, options, defaultOption, additionalClasses, onChange) {
    const res = [document.createElement("div"), document.createElement("span"), document.createElement("select")]
    res[0].classList.add("input-group")
    for (const c of additionalClasses) {
        res[0].classList.add(c)
    }
    res[1].classList.add("input-group-text")
    res[1].textContent = label
    res[2].classList.add("form-control")
    res[2].classList.add("form-select")
    res[2].id = id
    res[2].setAttribute("aria-describedby", "label" + label + "Header")
    res[2].setAttribute("aria-label", label)
    for (const o of options) {
        res[2].appendChild(createOptionWithValue(o, defaultOption));
    }
    res[2].onchange = onChange
    res[0].appendChild(res[1])
    res[0].appendChild(res[2])
    return res[0]
}

function createConfigHtmlForLabel(targetParent, sonificationRanges, label, additionalClasses) {
    const res = document.createElement("div");
    for (const c of additionalClasses) {
        res.classList.add(c)
    }
    res.classList.add("rcorners2")
    const nameMappings = {
        "Simple": ["Global"],
        "Operator Types": sonificationRanges.operator.types,
        "Operator IDs": sonificationRanges.operator.ids,
        "Operator Variables": sonificationRanges.operator.variables,
        "Operator Depths": sonificationRanges.operator.depths,
        "Data IDs": sonificationRanges.data.ids,
        "Data Variables": sonificationRanges.data.variables,
        "Query Progress": sonificationRanges.query.progress
    }
    const options = ["Simple"]
    for (const k in nameMappings) {
        if (nameMappings[k].length > 1) {
            options.push(k)
        }
    }

    function onChangeFunc() {
        const value = jquery("#sonification-" + label + "-select").val()
        sonificationConf[label] = {}
        sonificationConf[label][value] = {}
        console.log(value)
        jquery("#sonification-" + label + "-details").empty();
        const target = document.getElementById("sonification-" + label + "-details");
        for (const k in nameMappings[value]) {
            const v = nameMappings[value][k]
            switch (sonificationOptions[label].mode) {
                case "values": {
                    function nestedChangeFunc() {
                        const value2 = jquery("#sonification-" + label + "-" + k).val()
                        sonificationConf[label][value][v] = value2
                        console.log(sonificationConf)
                    }
                    target.appendChild(createLabelWithSelector(v, "sonification-" + label + "-" + k, sonificationOptions[label].values, sonificationOptions[label]["default"], ["mt-3"], nestedChangeFunc))
                    nestedChangeFunc()
                    break;
                }
                case "range": {
                    if (value == "Simple") {
                        function nestedChangeFunc2() {
                            const value2 = jquery("#sonification-" + label + "-slider").val() * 0.01 * (sonificationOptions[label].max - sonificationOptions[label].min) + sonificationOptions[label].min
                            sonificationConf[label][value][v] = value2
                            console.log(sonificationConf)
                        }
                        const res = [document.createElement("div"), document.createElement("span"), document.createElement("input")]
                        res[0].classList.add("input-group")
                        res[0].classList.add("mt-3")
                        res[1].classList.add("input-group-text")
                        res[1].textContent = "Value"
                        res[2].classList.add("form-range")
                        res[2].classList.add("form-select")
                        res[2].id = "sonification-" + label + "-slider"
                        res[2].onchange = nestedChangeFunc2
                        res[2].min = 0
                        res[2].max = 100
                        res[2].type = "range"
                        res[2].style.height = "2.4rem"
                        res[0].appendChild(res[1])
                        res[0].appendChild(res[2])
                        target.appendChild(res[0])
                        nestedChangeFunc2()
                        /*
                        <div class="input-group mb-3">
                                    <span class="input-group-text" id="labelAnimationSpeed">Animation Speed</span> <input aria-describedby="labelAnimationSpeed" class="form-range form-control" id="sonification-animation-speed" max="5000" min="30" style="height: 2.4rem" type="range">
                                  </div>
                        */
                    }
                    break
                }
            }
        }
    }
    res.appendChild(createLabelWithSelector(label, "sonification-" + label + "-select", options, "Simple", [], onChangeFunc));
    const resDetails = document.createElement("div");
    resDetails.id = "sonification-" + label + "-details"
    res.appendChild(resDetails)
    targetParent.appendChild(res)
    onChangeFunc()
}