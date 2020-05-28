package lupos.s09physicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
Coverage.funStart(11230)
        var res = ""
Coverage.statementStart(11231)
        when (type) {
            EModifyType.INSERT -> {
Coverage.whenCaseStart(11233)
                res += "INSERT"
Coverage.statementStart(11234)
            }
            EModifyType.DELETE -> {
Coverage.whenCaseStart(11235)
                res += "DELETE"
Coverage.statementStart(11236)
            }
        }
Coverage.statementStart(11237)
        res += " DATA {"
Coverage.statementStart(11238)
        for (c in data) {
Coverage.forLoopStart(11239)
            SanityCheck.checkFalse({ c.graphVar })
Coverage.statementStart(11240)
            if (c.graph == PersistentStoreLocal.defaultGraphName) {
Coverage.ifStart(11241)
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
Coverage.statementStart(11242)
            }
Coverage.statementStart(11243)
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
Coverage.statementStart(11244)
        }
Coverage.statementStart(11245)
        res += "}"
Coverage.statementStart(11246)
        return res
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(11247)
        val res = XMLElement("POPModifyData")
Coverage.statementStart(11248)
        res.addAttribute("uuid", "" + uuid)
Coverage.statementStart(11249)
        for (t in data) {
Coverage.forLoopStart(11250)
            res.addContent(t.toXMLElement())
Coverage.statementStart(11251)
        }
Coverage.statementStart(11252)
        return res
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11253)
        val iteratorDataMap = mutableMapOf<String, Array<MyListValue>>()
Coverage.statementStart(11254)
        for (t in data) {
Coverage.forLoopStart(11255)
            for (i in 0 until 3) {
Coverage.forLoopStart(11256)
                var tmp = iteratorDataMap[t.graph]
Coverage.statementStart(11257)
                if (tmp == null) {
Coverage.ifStart(11258)
                    tmp = Array(3) { MyListValue() }
Coverage.statementStart(11259)
                    iteratorDataMap[t.graph] = tmp
Coverage.statementStart(11260)
                }
Coverage.statementStart(11261)
                tmp[i].add((t.children[i] as AOPConstant).value)
Coverage.statementStart(11262)
            }
Coverage.statementStart(11263)
        }
Coverage.statementStart(11264)
        for ((graph, iteratorData) in iteratorDataMap) {
Coverage.forLoopStart(11265)
            val graphLocal = DistributedTripleStore.getNamedGraph(query, graph)
Coverage.statementStart(11266)
            graphLocal.modify(Array(3) { ColumnIteratorMultiValue(iteratorData[it]) }, type)
Coverage.statementStart(11267)
        }
Coverage.statementStart(11268)
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(true)))))
    }
}
