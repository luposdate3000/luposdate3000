package lupos.s09physicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.Query
class POPValuesImportXML : POPValuesImportBase {
    constructor(query: Query, projectedVariables: List<String>, data: XMLElement) : super(query, projectedVariables, data["head"]!!.childs.map { it.attributes["name"]!! }) {
        val variables = data["head"]!!.childs.map { it.attributes["name"]!! }
        SanityCheck.checkEQ({ data.tag }, { "sparql" })
        for (node in data["results"]!!.childs) {
Coverage.forLoopStart(11287)
            val row = arrayOfNulls<String>(variables.size)
            for (v in node.childs) {
Coverage.forLoopStart(11288)
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val datatype = child.attributes["datatype"]
                val lang = child.attributes["xml:lang"]
                SanityCheck.checkFalse({ (datatype != null) && (lang != null) })
                when {
                    child.tag == "uri" -> {
Coverage.whenCaseStart(11290)
                        row[variables.indexOf(name)] = "<" + content + ">"
                    }
                    child.tag == "literal" && datatype != null -> {
Coverage.whenCaseStart(11291)
                        row[variables.indexOf(name)] = "\"" + content + "\"^^<" + datatype + ">"
                    }
                    child.tag == "literal" && lang != null -> {
Coverage.whenCaseStart(11292)
                        row[variables.indexOf(name)] = "\"" + content + "\"@" + lang
                    }
                    child.tag == "bnode" -> {
Coverage.whenCaseStart(11293)
                        row[variables.indexOf(name)] = "_:" + content
                    }
                    else -> {
Coverage.whenCaseStart(11294)
                        row[variables.indexOf(name)] = "\"" + content + "\""
                    }
                }
            }
            addRow(row)
        }
    }
}
