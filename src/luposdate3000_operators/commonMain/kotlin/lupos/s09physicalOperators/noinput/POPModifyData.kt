package lupos.s09physicalOperators.noinput

import lupos.s00misc.*
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s05tripleStore.PersistentStoreLocalExt
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField

class POPModifyData(query: IQuery, projectedVariables: List<String>, @JvmField val type: EModifyType, @JvmField val data: List<LOPTriple>) : POPBase(query, projectedVariables, EOperatorID.POPModifyDataID, "POPModifyData", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun equals(other: Any?): Boolean = other is POPModifyData && type == other.type && data == other.data
    override fun cloneOP(): IOPBase = POPModifyData(query, projectedVariables, type, data)
    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf("?success")
    override fun toSparql(): String {
        var res = ""
        res += when (type) {
            EModifyType.INSERT -> {
                "INSERT"
            }
            EModifyType.DELETE -> {
                "DELETE"
            }
        }
        res += " DATA {"
        for (c in data) {
            if (c.graphVar) {
                throw GraphVariablesNotImplementedException(classname)
            }
            SanityCheck.check { !c.graphVar }
            if (c.graph == PersistentStoreLocalExt.defaultGraphName) {
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            }
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = XMLElement("POPModifyData")
        res.addAttribute("uuid", "" + uuid)
        for (t in data) {
            res.addContent(t.toXMLElement())
        }
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val iteratorDataMap = mutableMapOf<String, Array<MutableList<Int>>>()
        for (t in data) {
            for (i in 0 until 3) {
                var tmp = iteratorDataMap[t.graph]
                if (tmp == null) {
                    tmp = Array(3) { mutableListOf() }
                    iteratorDataMap[t.graph] = tmp
                }
                tmp[i].add((t.children[i] as AOPConstant).value)
            }
        }
        for ((graph, iteratorData) in iteratorDataMap) {
            val graphLocal = distributedTripleStore.getNamedGraph(query, graph)
            graphLocal.modify(Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }, type)
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.getDictionary().createValue(ValueBoolean(true)))))
    }
}
