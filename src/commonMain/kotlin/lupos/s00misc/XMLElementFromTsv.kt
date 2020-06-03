package lupos.s00misc

import lupos.s00misc.Coverage

fun XMLElement.Companion.parseFromTsv(tsv: String): XMLElement? {
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
    val nodeHead = XMLElement("head")
    val nodeResults = XMLElement("results")
    nodeSparql.addContent(nodeHead)
    nodeSparql.addContent(nodeResults)
    val lines = tsv.lines()
    val variables = mutableListOf<String>()
    val columns = lines.first().split("\t")
    for (variableName in columns) {
        nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
        variables.add(variableName.substring(1, variableName.length))
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
        val values = line.split("\t")
        var i = 0
        while (i < variables.size && i < values.size) {
            parseBindingFromString(nodeResult, values[i], variables[i])
            i++
        }
    }
    return nodeSparql
}
