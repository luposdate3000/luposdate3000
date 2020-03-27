package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.EOperatorID

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*



import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

class POPValuesImportXML : POPValuesImportBase {
    constructor(query: Query, data: XMLElement) : super(query, data["head"]!!.childs.map { it.attributes["name"]!! }) {
        val variables = data["head"]!!.childs.map { it.attributes["name"]!! }
        SanityCheck.checkEQ({ data.tag }, { "sparql" })
        for (node in data["results"]!!.childs) {
            val row = arrayOfNulls<String>(variables.size)
            for (v in node.childs) {
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val datatype = child.attributes["datatype"]
                val lang = child.attributes["xml:lang"]
                SanityCheck.checkFalse({ (datatype != null) && (lang != null) })
                when {
                    child.tag == "uri" -> row[variables.indexOf(name)] = "<" + content + ">"
                    child.tag == "literal" && datatype != null -> row[variables.indexOf(name)] = "\"" + content + "\"^^<" + datatype + ">"
                    child.tag == "literal" && lang != null -> row[variables.indexOf(name)] = "\"" + content + "\"@" + lang
                    child.tag == "bnode" -> row[variables.indexOf(name)] = "_:" + content
                    else -> row[variables.indexOf(name)] = "\"" + content + "\""
                }
            }
            addRow(row)
        }
    }
}
