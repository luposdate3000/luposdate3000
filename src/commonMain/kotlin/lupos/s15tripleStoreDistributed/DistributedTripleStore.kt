package lupos.s15tripleStoreDistributed
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s16network.*
class TripleStoreIteratorGlobal(query: Query, projectedVariables: List<String>, val graphName: String, params: Array<AOPBase>, val idx: EIndexPattern) : POPBase(query, projectedVariables, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", Array<OPBase>(3) { params[it] }, ESortPriority.ANY_PROVIDED_VARIABLE) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, projectedVariables, graphName, Array(3) { children[it] as AOPBase }, idx)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(12927)
        if (other !is TripleStoreIteratorGlobal) {
Coverage.ifStart(12928)
            return false
        }
Coverage.statementStart(12929)
        if (graphName != other.graphName) {
Coverage.ifStart(12930)
            return false
        }
Coverage.statementStart(12931)
        if (idx != other.idx) {
Coverage.ifStart(12932)
            return false
        }
Coverage.statementStart(12933)
        if (!projectedVariables.containsAll(other.projectedVariables)) {
Coverage.ifStart(12934)
            return false
        }
Coverage.statementStart(12935)
        if (!other.projectedVariables.containsAll(projectedVariables)) {
Coverage.ifStart(12936)
            return false
        }
Coverage.statementStart(12937)
        if (children.size != other.children.size) {
Coverage.ifStart(12938)
            return false
        }
Coverage.statementStart(12939)
        for (i in 0 until children.size) {
Coverage.forLoopStart(12940)
            if (children[i] != other.children[i]) {
Coverage.ifStart(12941)
                return false
            }
Coverage.statementStart(12942)
        }
Coverage.statementStart(12943)
        return true
    }
    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobal").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphName).//
            addAttribute("idx", "" + idx).//
            addAttribute("providedVariables", getProvidedVariableNames().toString()).//
            addAttribute("providedSort", getPossibleSortPriorities().toString()).//
            addAttribute("selectedSort", mySortPriority.toString()).//
            addContent(XMLElement("sparam").addContent(children[0].toXMLElement())).//
            addContent(XMLElement("pparam").addContent(children[1].toXMLElement())).//
            addContent(XMLElement("oparam").addContent(children[2].toXMLElement()))
    override fun toSparql(): String {
Coverage.funStart(12944)
        if (graphName == PersistentStoreLocal.defaultGraphName) {
Coverage.ifStart(12945)
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
Coverage.statementStart(12946)
        return "GRAPH <$graphName> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }
    override fun getProvidedVariableNamesInternal(): List<String> {
Coverage.funStart(12947)
        val tmp = mutableListOf<String>()
Coverage.statementStart(12948)
        for (p in children) {
Coverage.forLoopStart(12949)
            tmp.addAll(p.getRequiredVariableNames())
Coverage.statementStart(12950)
        }
Coverage.statementStart(12951)
        tmp.remove("_")
Coverage.statementStart(12952)
        tmp.remove("_")
Coverage.statementStart(12953)
        tmp.remove("_")
Coverage.statementStart(12954)
        return tmp.distinct()
    }
    init {
Coverage.funStart(12955)
        SanityCheck {
Coverage.statementStart(12956)
            if (idx.keyIndices.size == 3) {
Coverage.ifStart(12957)
                if (params[0] is AOPVariable) {
Coverage.ifStart(12958)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(12959)
                } else {
Coverage.ifStart(12960)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(12961)
                }
Coverage.statementStart(12962)
            } else {
Coverage.ifStart(12963)
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(12964)
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(12965)
            }
Coverage.statementStart(12966)
        }
Coverage.statementStart(12967)
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(12968)
        return ServerCommunicationSend.tripleGet(query, graphName, Array(3) { children[it] as AOPBase }, idx)
    }
}
class DistributedGraph(val query: Query, @JvmField val name: String) {
    suspend fun bulkImport(data: TripleStoreBulkImport, idx: EIndexPattern) {
Coverage.funStart(12969)
        DistributedTripleStore.localStore.getNamedGraph(query, name).import(data, idx)
Coverage.statementStart(12970)
    }
    suspend fun modify(data: Array<ColumnIterator>, type: EModifyType) {
Coverage.funStart(12971)
        SanityCheck.check { data.size == 3 }
Coverage.statementStart(12972)
        val map = Array(EIndexPattern.values().size) { Array(3) { MyListValue() } }
Coverage.statementStart(12973)
        loop@ while (true) {
Coverage.whileLoopStart(12974)
            val row = Array(3) { ResultSetDictionary.undefValue }
Coverage.statementStart(12975)
            for (columnIndex in 0 until 3) {
Coverage.forLoopStart(12976)
                val v = data[columnIndex].next()
Coverage.statementStart(12977)
                if (v == null) {
Coverage.ifStart(12978)
                    SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(12979)
                    break@loop
                }
Coverage.statementStart(12980)
                row[columnIndex] = v
Coverage.statementStart(12981)
            }
Coverage.statementStart(12982)
            for (idx in TripleStoreLocalBase.distinctIndices) {
Coverage.forLoopStart(12983)
                for (columnIndex in 0 until 3) {
Coverage.forLoopStart(12984)
                    map[idx.ordinal][columnIndex].add(row[columnIndex])
Coverage.statementStart(12985)
                }
Coverage.statementStart(12986)
            }
Coverage.statementStart(12987)
        }
Coverage.statementStart(12988)
        for (idx in TripleStoreLocalBase.distinctIndices) {
Coverage.forLoopStart(12989)
            if (map[idx.ordinal][0].size > 0) {
Coverage.ifStart(12990)
                DistributedTripleStore.localStore.getNamedGraph(query, name).modify(query, Array(3) { ColumnIteratorMultiValue(map[idx.ordinal][it]) }, idx, type)
Coverage.statementStart(12991)
            }
Coverage.statementStart(12992)
        }
Coverage.statementStart(12993)
    }
    fun getIterator(idx: EIndexPattern): POPBase {
Coverage.funStart(12994)
        val projectedVariables = listOf<String>("s", "p", "o")
Coverage.statementStart(12995)
        return TripleStoreIteratorGlobal(query, projectedVariables, name, arrayOf<AOPBase>(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), idx)
    }
    fun getIterator(params: Array<AOPBase>, idx: EIndexPattern): POPBase {
Coverage.funStart(12996)
        val projectedVariables = mutableListOf<String>()
Coverage.statementStart(12997)
        SanityCheck {
Coverage.statementStart(12998)
            if (idx.keyIndices.size == 3) {
Coverage.ifStart(12999)
                if (params[0] is AOPVariable) {
Coverage.ifStart(13000)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(13001)
                } else {
Coverage.ifStart(13002)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(13003)
                }
Coverage.statementStart(13004)
            } else {
Coverage.ifStart(13005)
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(13006)
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(13007)
            }
Coverage.statementStart(13008)
        }
Coverage.statementStart(13009)
        if (idx.keyIndices.size == 3) {
Coverage.ifStart(13010)
            if (params[0] is AOPVariable) {
Coverage.ifStart(13011)
                idx.keyIndices.forEach {
Coverage.forEachLoopStart(13012)
                    val tmp = (params[it] as AOPVariable).name
Coverage.statementStart(13013)
                    if (tmp != "_") {
Coverage.ifStart(13014)
                        projectedVariables.add(tmp)
Coverage.statementStart(13015)
                    }
Coverage.statementStart(13016)
                }
Coverage.statementStart(13017)
            }
Coverage.statementStart(13018)
        } else {
Coverage.ifStart(13019)
            idx.valueIndices.forEach {
Coverage.forEachLoopStart(13020)
                val tmp = (params[it] as AOPVariable).name
Coverage.statementStart(13021)
                if (tmp != "_") {
Coverage.ifStart(13022)
                    projectedVariables.add(tmp)
Coverage.statementStart(13023)
                }
Coverage.statementStart(13024)
            }
Coverage.statementStart(13025)
        }
Coverage.statementStart(13026)
        return TripleStoreIteratorGlobal(query, projectedVariables, name, params, idx)
    }
    fun getHistogram(params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
Coverage.funStart(13027)
        SanityCheck {
Coverage.statementStart(13028)
            if (idx.keyIndices.size == 3) {
Coverage.ifStart(13029)
                if (params[0] is AOPVariable) {
Coverage.ifStart(13030)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(13031)
                } else {
Coverage.ifStart(13032)
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(13033)
                }
Coverage.statementStart(13034)
            } else {
Coverage.ifStart(13035)
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
Coverage.statementStart(13036)
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
Coverage.statementStart(13037)
            }
Coverage.statementStart(13038)
            var variableNames = 0
Coverage.statementStart(13039)
            if (idx.keyIndices.size == 3) {
Coverage.ifStart(13040)
                if (params[0] is AOPVariable) {
Coverage.ifStart(13041)
                    idx.keyIndices.forEach {
Coverage.forEachLoopStart(13042)
                        val tmp = (params[it] as AOPVariable).name
Coverage.statementStart(13043)
                        if (tmp != "_") {
Coverage.ifStart(13044)
                            variableNames++
Coverage.statementStart(13045)
                        }
Coverage.statementStart(13046)
                    }
Coverage.statementStart(13047)
                }
Coverage.statementStart(13048)
            } else {
Coverage.ifStart(13049)
                idx.valueIndices.forEach {
Coverage.forEachLoopStart(13050)
                    val tmp = (params[it] as AOPVariable).name
Coverage.statementStart(13051)
                    if (tmp != "_") {
Coverage.ifStart(13052)
                        variableNames++
Coverage.statementStart(13053)
                    }
Coverage.statementStart(13054)
                }
Coverage.statementStart(13055)
            }
Coverage.statementStart(13056)
            SanityCheck { variableNames == 1 }
Coverage.statementStart(13057)
        }
Coverage.statementStart(13058)
        return ServerCommunicationSend.histogramGet(query, name, params, idx)
    }
}
object DistributedTripleStore {
    @JvmField
    val localStore = PersistentStoreLocal()
    fun getGraphNames(includeDefault: Boolean = false): List<String> {
Coverage.funStart(13059)
        return localStore.getGraphNames(includeDefault)
    }
    fun createGraph(query: Query, name: String): DistributedGraph {
Coverage.funStart(13060)
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.CREATE)
Coverage.statementStart(13061)
        return DistributedGraph(query, name)
    }
    fun dropGraph(query: Query, name: String) {
Coverage.funStart(13062)
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.DROP)
Coverage.statementStart(13063)
    }
    fun clearGraph(query: Query, name: String) {
Coverage.funStart(13064)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
Coverage.statementStart(13065)
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.CLEAR)
Coverage.statementStart(13066)
    }
    fun getNamedGraph(query: Query, name: String): DistributedGraph {
Coverage.funStart(13067)
        if (!(localStore.getGraphNames(true).contains(name))) {
Coverage.ifStart(13068)
            createGraph(query, name)
Coverage.statementStart(13069)
        }
Coverage.statementStart(13070)
        return DistributedGraph(query, name)
    }
    fun getDefaultGraph(query: Query): DistributedGraph {
Coverage.funStart(13071)
        return DistributedGraph(query, PersistentStoreLocal.defaultGraphName)
    }
    fun commit(query: Query) {
Coverage.funStart(13072)
        ServerCommunicationSend.commit(query)
Coverage.statementStart(13073)
    }
}
