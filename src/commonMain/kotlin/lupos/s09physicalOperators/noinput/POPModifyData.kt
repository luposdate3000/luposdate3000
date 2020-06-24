package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPModifyData(query: Query, projectedVariables: List<String>, @JvmField val type: EModifyType, @JvmField val data: List<LOPTriple>) : POPBase(query, projectedVariables, EOperatorID.POPModifyDataID, "POPModifyData", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean = other is POPModifyData && type == other.type && data == other.data
    override fun cloneOP() = POPModifyData(query, projectedVariables, type, data)
    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>("?success")
    override fun toSparql(): String {
        var res = ""
        when (type) {
            EModifyType.INSERT -> {
                res += "INSERT"
            }
            EModifyType.DELETE -> {
                res += "DELETE"
            }
        }
        res += " DATA {"
        for (c in data) {
            SanityCheck.checkFalse({ c.graphVar })
            if (c.graph == PersistentStoreLocal.defaultGraphName) {
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            }
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPModifyData")
        res.addAttribute("uuid", "" + uuid)
        for (t in data) {
            res.addContent(t.toXMLElement())
        }
        return res
    }

    override suspend fun evaluate(): IteratorBundle {
        val iteratorDataMap = mutableMapOf<String, Array<MyListValue>>()
        for (t in data) {
            for (i in 0 until 3) {
                var tmp = iteratorDataMap[t.graph]
                if (tmp == null) {
                    tmp = Array(3) { MyListValue() }
                    iteratorDataMap[t.graph] = tmp
                }
                tmp[i].add((t.children[i] as AOPConstant).value)
            }
        }
        for ((graph, iteratorData) in iteratorDataMap) {
            val graphLocal = DistributedTripleStore.getNamedGraph(query, graph)
            graphLocal.modify(Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }, type)
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(true)))))
    }
}
