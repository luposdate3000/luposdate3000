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
        "min": 0,
        "max": 1,
        "default": 0.5
    },
    "Spatialization": {
        "mode": "range",
        "min": -1,
        "max": 1,
        "default": 0
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
    res[1].id = id + "-header"
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
            switch (sonificationOptions[label].mode) {
                case "values": {
                    const v = nameMappings[value][k]
                    const addCl = []
                    if (k < nameMappings[value].length - 1) {
                        addCl.push("mb-3")
                    }

                    function nestedChangeFunc() {
                        const value2 = jquery("#sonification-" + label + "-" + k).val()
                        sonificationConf[label][value][v] = value2
                        console.log("setting -", label, "-", value, "-", v, "-", value2)
                        console.log(sonificationConf)
                    }
                    target.appendChild(createLabelWithSelector(v, "sonification-" + label + "-" + k, sonificationOptions[label].values, sonificationOptions[label]["default"], addCl, nestedChangeFunc))
                    nestedChangeFunc()
                    break;
                }
                case "range": {
                    break
                }
            }
        }
    }
    res.appendChild(createLabelWithSelector(label, "sonification-" + label + "-select", options, "Simple", ["mb-3"], onChangeFunc));
    const resDetails = document.createElement("div");
    resDetails.id = "sonification-" + label + "-details"
    res.appendChild(resDetails)
    targetParent.appendChild(res)
    onChangeFunc()
}
