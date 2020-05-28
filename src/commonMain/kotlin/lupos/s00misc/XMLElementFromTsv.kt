package lupos.s00misc
import lupos.s00misc.Coverage
fun XMLElement.Companion.parseFromTsv(tsv: String): XMLElement? {
Coverage.funStart(1407)
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(1408)
    val nodeHead = XMLElement("head")
Coverage.statementStart(1409)
    val nodeResults = XMLElement("results")
Coverage.statementStart(1410)
    nodeSparql.addContent(nodeHead)
Coverage.statementStart(1411)
    nodeSparql.addContent(nodeResults)
Coverage.statementStart(1412)
    val lines = tsv.lines()
Coverage.statementStart(1413)
    val variables = mutableListOf<Pair<String, Int>>()
Coverage.statementStart(1414)
    var i = 0
Coverage.statementStart(1415)
    for (variableName in lines.first().split("\t")) {
Coverage.forLoopStart(1416)
        nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
Coverage.statementStart(1417)
        variables.add(Pair(variableName.substring(1, variableName.length), i))
Coverage.statementStart(1418)
        i++
Coverage.statementStart(1419)
    }
Coverage.statementStart(1420)
    var firstLine = true
Coverage.statementStart(1421)
    for (line in lines) {
Coverage.forLoopStart(1422)
        if (firstLine) {
Coverage.ifStart(1423)
            firstLine = false
Coverage.statementStart(1424)
            continue
        }
Coverage.statementStart(1425)
        if (line.isEmpty()) {
Coverage.ifStart(1426)
            continue
        }
Coverage.statementStart(1427)
        val nodeResult = XMLElement("result")
Coverage.statementStart(1428)
        nodeResults.addContent(nodeResult)
Coverage.statementStart(1429)
        val values = line.split("\t")
Coverage.statementStart(1430)
        for (variable in variables) {
Coverage.forLoopStart(1431)
            if (values.size > variable.second) {
Coverage.ifStart(1432)
                parseBindingFromString(nodeResult, values[variable.second], variable.first)
Coverage.statementStart(1433)
            }
Coverage.statementStart(1434)
        }
Coverage.statementStart(1435)
    }
Coverage.statementStart(1436)
    return nodeSparql
}
