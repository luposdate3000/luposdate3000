package lupos.s09physicalOperators.noinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPValuesImportXML : POPValuesImportBase {
    constructor(query: Query, projectedVariables: List<String>, data: XMLElement) : super(query, projectedVariables, data["head"]!!.childs.map { it.attributes["name"]!! }) {
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
