package lupos.s00misc
import lupos.s00misc.Coverage
fun XMLElement.Companion.parseFromCsv(csv: String): XMLElement? {
Coverage.funStart(1248)
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(1249)
    val nodeHead = XMLElement("head")
Coverage.statementStart(1250)
    val nodeResults = XMLElement("results")
Coverage.statementStart(1251)
    nodeSparql.addContent(nodeHead)
Coverage.statementStart(1252)
    nodeSparql.addContent(nodeResults)
Coverage.statementStart(1253)
    val lines = csv.lines()
Coverage.statementStart(1254)
    val variables = mutableListOf<Pair<String, Int>>()
Coverage.statementStart(1255)
    var i = 0
Coverage.statementStart(1256)
    for (variableName in lines.first().split(",")) {
Coverage.forLoopStart(1257)
        nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
Coverage.statementStart(1258)
        variables.add(Pair(variableName.substring(1, variableName.length), i))
Coverage.statementStart(1259)
        i++
Coverage.statementStart(1260)
    }
Coverage.statementStart(1261)
    var firstLine = true
Coverage.statementStart(1262)
    for (line in lines) {
Coverage.forLoopStart(1263)
        if (firstLine) {
Coverage.ifStart(1264)
            firstLine = false
Coverage.statementStart(1265)
            continue
        }
Coverage.statementStart(1266)
        if (line.isEmpty()) {
Coverage.ifStart(1267)
            continue
        }
Coverage.statementStart(1268)
        val nodeResult = XMLElement("result")
Coverage.statementStart(1269)
        nodeResults.addContent(nodeResult)
Coverage.statementStart(1270)
        val values = line.split(",")
Coverage.statementStart(1271)
        for (variable in variables) {
Coverage.forLoopStart(1272)
            if (values.size > variable.second) {
Coverage.ifStart(1273)
                parseBindingFromString(nodeResult, values[variable.second], variable.first)
Coverage.statementStart(1274)
            }
Coverage.statementStart(1275)
        }
Coverage.statementStart(1276)
    }
Coverage.statementStart(1277)
    return nodeSparql
}
