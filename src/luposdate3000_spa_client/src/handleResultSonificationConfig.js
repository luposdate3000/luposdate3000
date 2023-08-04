const jquery = require("jquery")

const sonificationConf = {}

const sonificationOptions = {
    "Pitch": {
        "mode": "values",
        "defaultMode": "Simple",
        "defaultValue": "C",
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
        "defaultMode": "Simple",
        "mode": "values",
        "defaultValue": "piano",
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
        "defaultValue": "1n",
        "defaultMode": "Simple",
        "values": ["1n",
            "2n",
            "4n",
            "8n",
            "16n"
        ]
    },
    "Octave": {
        "mode": "values",
        "defaultMode": "Simple",
        "defaultValue": "3",
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
        "defaultMode": "Simple",
        "min": 0.0,
        "max": 1.0,
        "defaultValue": 0.5
    },
    "Spatialization": {
        "mode": "range",
        "defaultMode": "Simple",
        "min": -1.0,
        "max": 1.0,
        "defaultValue": 0.0
    }
}
const sonificationModeKeys = ["Simple", "Operator Types", "Operator IDs", "Operator Variables", "Operator Depths", "Data IDs", "Data Variables", "Query Progress"]
const sonificationTypeKeys = ["Pitch", "Instrument", "Loudness", "Duration", "Octave", "Spatialization"]
export function createConfigHtml(sonificationRanges) {
    jquery("#sonification-config-root").empty()
    const target = document.getElementById("sonification-config-root");
    for (const k of sonificationTypeKeys) {
        createConfigHtmlForLabel(target, sonificationRanges, k);
    }
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

function createConfigHtmlForLabel(targetParent, sonificationRanges, label) {
    const res = document.createElement("div");
    res.classList.add("mt-3")
    res.classList.add("rcorners2")
    const options = ["Simple"]
    for (const k of sonificationModeKeys) {
        if (sonificationRanges[k].length > 1) {
            options.push(k)
        }
    }

    function onChangeFunc() {
        const value = jquery("#sonification-" + label + "-select").val()
        sonificationConf[label] = {}
        sonificationConf[label].mode = value
        sonificationConf[label][value] = {}
        jquery("#sonification-" + label + "-details").empty();
        const target = document.getElementById("sonification-" + label + "-details");
        for (const k in sonificationRanges[value]) {
            const v = sonificationRanges[value][k]
            switch (sonificationOptions[label].mode) {
                case "values": {
                    function nestedChangeFunc() {
                        const value2 = jquery("#sonification-" + label + "-" + k).val()
                        sonificationConf[label][value][v] = value2
                    }
                    target.appendChild(createLabelWithSelector(v, "sonification-" + label + "-" + k, sonificationOptions[label].values, sonificationOptions[label].defaultValue, ["mt-3"], nestedChangeFunc))
                    nestedChangeFunc()
                    break;
                }
                case "range": {
                    if (value == "Simple") {
                        function nestedChangeFunc2() {
                            const value2 = jquery("#sonification-" + label + "-slider").val() * 0.01 * (sonificationOptions[label].max - sonificationOptions[label].min) + sonificationOptions[label].min
                            sonificationConf[label][value][v] = value2
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
                    }
                    break
                }
            }
        }
    }
    res.appendChild(createLabelWithSelector(label, "sonification-" + label + "-select", options, sonificationOptions[label].defaultMode, [], onChangeFunc));
    const resDetails = document.createElement("div");
    resDetails.id = "sonification-" + label + "-details"
    res.appendChild(resDetails)
    targetParent.appendChild(res)
    onChangeFunc()
}
export function applySonification(sonificationRangesReverse) {
    const res = []
    for (const tt of sonificationTypeKeys) {
        if (!(tt in sonificationConf)) {
            return
        }
    }
    for (const step in sonificationRangesReverse["Query Progress"]) {
        const resstep = {}
        for (const tt of sonificationTypeKeys) {
            const mm = sonificationConf[tt].mode
            switch (mm) {
                case "Simple": {
                    resstep[tt] = sonificationConf[tt][mm].Global
                    break
                }
                default: {
                    resstep[tt] = sonificationConf[tt][mm][sonificationRangesReverse[mm][step]]
                    break
                }
            }
        }
        res.push(resstep)
    }
    console.log(res)
return res
}
