package lupos.s00misc

fun XMLElement.Companion.parseFromCsv(csv: String): XMLElement? {
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
    val nodeHead = XMLElement("head")
    val nodeResults = XMLElement("results")
    nodeSparql.addContent(nodeHead)
    nodeSparql.addContent(nodeResults)
    val lines = csv.lines()
    val variables = mutableListOf<String>()
    val columns = lines.first().split(",")
    for (variableName in columns) {
        nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName))
        variables.add(variableName)
    }
    var firstLine = true
    for (line in lines) {
        if (firstLine) {
            firstLine = false
            continue
        }
        if (line.isEmpty()) {
            continue
        }
        val nodeResult = XMLElement("result")
        nodeResults.addContent(nodeResult)
        val values = line.split(",")
        var i = 0
        while (i < variables.size && i < values.size) {
            parseBindingFromString(nodeResult, values[i], variables[i])
            i++
        }
    }
    return nodeSparql
}
