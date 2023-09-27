import Split from 'split.js'
const jquery = require("jquery")

var defaultSelectedTabCount = 1
var navTabToView = [{
        "#config": 0,
        "#sparql": 0,
        "#rdf": 0,
        "#result": 0,
        "#result-graph": 0,
        "#result-sonification": 0,
    },
    {
        "#config": 0,
        "#sparql": 0,
        "#rdf": 0,
        "#result": 1,
        "#result-graph": 1,
        "#result-sonification": 1,
    },
    {
        "#config": 0,
        "#sparql": 1,
        "#rdf": 0,
        "#result": 2,
        "#result-graph": 2,
        "#result-sonification": 2,
    }
]
var defaultSelections = [
    ["#config"],
    ["#config", "#result"],
    ["#config", "#sparql", "#result"]
]




var currentlySelected = 0
Split(['#split-3-0', '#split-3-1', '#split-3-2'], {
    minSize: [10, 10, 10]
})
Split(['#split-2-0', '#split-2-1'], {
    minSize: [10, 10]
})

jquery("input[name = 'splitViewTabs']").change(function() {
    changeToNewSplitCount(jquery("input[name = 'splitViewTabs']:checked").val())
});

function changeToNewSplitCount(tabCount) {
    currentlySelected = tabCount - 1
    const localConf = navTabToView[currentlySelected]
    for (const name in localConf) {
        jquery('#split-' + tabCount + '-' + localConf[name] + '-nav').append(jquery(name + "-tab-nav-item").detach());
        jquery('#split-' + tabCount + '-' + localConf[name] + '-content').append(jquery(name).detach());
    }
    for (const name of defaultSelections[currentlySelected]) {
        jquery(name + "-tab-trigger").click()
    }
}

const allConf = navTabToView[currentlySelected]
for (const name in allConf) {
    jquery(name + "-tab-trigger").click(function() {
        const localConf = navTabToView[currentlySelected]
        const myGroup = localConf[name]
        for (const n in localConf) {
            if (n != name && localConf[n] == myGroup) {
                jquery(n + "-tab-nav-item").removeClass("active");
                jquery(n).removeClass("show");
                jquery(n).removeClass("active");
                jquery(n + "-tab-trigger").removeClass("active");
            }
        }
        jquery(name + "-tab-nav-item").addClass("active");
        jquery(name).addClass("show");
        jquery(name).addClass("active");
        jquery(name + "-tab-trigger").addClass("active");
    })
}
jquery("#splitViewTabs" + defaultSelectedTabCount).prop("checked", true);
changeToNewSplitCount(defaultSelectedTabCount)
jquery("#sparql-tab-trigger").click()