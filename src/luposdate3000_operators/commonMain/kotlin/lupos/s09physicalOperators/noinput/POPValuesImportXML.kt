package lupos.s09physicalOperators.noinput

import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement

import lupos.s04logicalOperators.Query

class POPValuesImportXML : POPValuesImportBase {
    constructor(query: Query, projectedVariables: List<String>, data: XMLElement) : super(query, projectedVariables, data["head"]!!.childs.map { it.attributes["name"]!! }) {
        val variables = data["head"]!!.childs.map { it.attributes["name"]!! }
        SanityCheck.check({ data.tag == "sparql" })
        for (node in data["results"]!!.childs) {
            val row = arrayOfNulls<String>(variables.size)
            for (v in node.childs) {
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val datatype = child.attributes["datatype"]
                val lang = child.attributes["xml:lang"]
                SanityCheck.check({ !((datatype != null) && (lang != null)) })
                when {
                    child.tag == "uri" -> {
                        row[variables.indexOf(name)] = "<" + content + ">"
                    }
                    child.tag == "literal" && datatype != null -> {
                        row[variables.indexOf(name)] = "\"" + content + "\"^^<" + datatype + ">"
                    }
                    child.tag == "literal" && lang != null -> {
                        row[variables.indexOf(name)] = "\"" + content + "\"@" + lang
                    }
                    child.tag == "bnode" -> {
                        row[variables.indexOf(name)] = "_:" + content
                    }
                    else -> {
                        row[variables.indexOf(name)] = "\"" + content + "\""
                    }
                }
            }
            addRow(row)
        }
    }
}
