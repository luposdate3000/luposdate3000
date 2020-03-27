package lupos.s00misc

import kotlin.jvm.JvmField

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

fun XMLElement.Companion.parseFromTsv(tsv: String): List<XMLElement>? {
    val res = mutableListOf<XMLElement>()
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
    res.add(nodeSparql)
    val nodeHead = XMLElement("head")
    val nodeResults = XMLElement("results")
    nodeSparql.addContent(nodeHead)
    nodeSparql.addContent(nodeResults)
    val lines = tsv.lines()
    val variables = mutableListOf<Pair<String, Int>>()
    var i = 0
    for (variableName in lines.first().split("\t")) {
        nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
        variables.add(Pair(variableName.substring(1, variableName.length), i))
        i++
    }
    var firstLine = true
    for (line in lines) {
        if (firstLine) {
            firstLine = false
            continue
        }
        if (line.isEmpty())
            continue
        val nodeResult = XMLElement("result")
        nodeResults.addContent(nodeResult)
        val values = line.split("\t")
        for (variable in variables) {
            if (values.size > variable.second) {
                parseBindingFromString(nodeResult, values[variable.second], variable.first)
            }
        }
    }
    return res
}
