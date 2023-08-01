const jquery = require("jquery")

export function updateResultGraphTab(result) {
    jquery("#result-graph").empty();
    const target = document.getElementById('result-graph');
//    target.appendChild(XXX);
    document.querySelector("#result-graph-tab-nav-item").style.display = "list-item"
}
