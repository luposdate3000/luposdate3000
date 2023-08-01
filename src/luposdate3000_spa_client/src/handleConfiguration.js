const jquery = require("jquery")
import {
    setSparql,
    setRDF
} from "./handleCodeMirror.js"
import {
    enableEndpoints
} from "./handleEndpoint.js"
var knownLectures = {}
var knownTasks = {}

export function setUseRDF(data) {
    if (data) {
        jquery("#useRDF").prop("checked", true);
    } else {
        jquery("#useRDF").prop("checked", false);
    }
}
export function getUseRDF() {
    return jquery("#useRDF").prop("checked");
}

export function setWithGraph(data) {
    if (data) {
        jquery("#withGraph").prop("checked", true);
    } else {
        jquery("#withGraph").prop("checked", false);
    }
}
export function getWithGraph() {
    return jquery("#WithGraph").prop("checked");
}

function loadSparql(url) {
    if ((url !== undefined) && (url !== null)) {
        jquery.get(url, function(data) {
            setSparql(data);
        });
    }
}

function loadRDF(url) {
    if ((url !== undefined) && (url !== null)) {
        jquery.get(url, function(data) {
            setRDF(data);
        });
    }
}

function loadData(url) {
    if ((url === undefined) || (url === null)) {
        url = "resources/cloud-and-web-technologies/data1.json";
    }
    jquery.getJSON(url, function(data) {
        loadSparql(data.sparql);
        loadRDF(data.rdf);
    });
}

function loadConfig(url) {
    if ((url === undefined) || (url === null)) {
        url = "resources/config.json";
    }
    jquery.getJSON(url, function(data) {
        setUseRDF(data.useRDF)
        setWithGraph(data.withGraph)
        enableEndpoints(data.endpoints)
        enableExampleLectures(data.lectures)
    });
}

function enableExampleLectures(data) {
    const lectures = jquery("#exampleLecture")
    lectures.empty();
    if ((data !== undefined) && (data !== null)) {
        for (const lecture of data) {
            knownLectures[lecture.label] = lecture
            lectures.append(jquery("<option></option>").attr("value", lecture.label).text(lecture.label));
        }
    }
}

function enableExampleLecture(data) {
    const tasks = jquery("#exampleTask")
    tasks.empty();
    if ((data !== undefined) && (data !== null)) {
        if ((data.tasks !== undefined) && (data.tasks !== null)) {
            knownTasks = {}
            for (const task of data.tasks) {
                knownTasks[task.label + " - Empty"] = {
                    sparql: task.empty,
                    rdf: task.rdf
                }
                knownTasks[task.label + " - Solution"] = {
                    sparql: task.target,
                    rdf: task.rdf
                }
                tasks.append(jquery("<option></option>").attr("value", task.label + " - Empty").text(task.label + " - Empty"));
                tasks.append(jquery("<option></option>").attr("value", task.label + " - Solution").text(task.label + " - Solution"));
            }
        }
    }
}
jquery("#exampleLecture").on("change", function() {
    const lecture = knownLectures[jquery("#exampleLecture").val()]
    if ((lecture.data === undefined) || (lecture.data === null)) {
        jquery.getJSON(lecture.url, function(data) {
            lecture.data = data
            enableExampleLecture(lecture.data)
        });
    } else {
        enableExampleLecture(lecture.data)
    }
});
jquery("#exampleLoad").on("click", function() {
    const data = knownTasks[jquery("#exampleTask").val()]
    loadSparql(data.sparql);
    loadRDF(data.rdf);
});

const urlParams = new URLSearchParams(window.location.search);
loadData(urlParams.get('data'));
loadConfig(urlParams.get('config'));

