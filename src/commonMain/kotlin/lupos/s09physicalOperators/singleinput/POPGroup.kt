package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind
class POPGroup : POPBase {
    @JvmField
    var by: List<AOPVariable>
    @JvmField
    var bindings = mutableListOf<Pair<String, AOPBase>>()
    override fun toSparql(): String {
Coverage.funStart(11679)
        var res = children[0].toSparql()
Coverage.statementStart(11680)
        res += " GROUP BY "
Coverage.statementStart(11681)
        for (b in by) {
Coverage.forLoopStart(11682)
            res += b.toSparql() + " "
Coverage.statementStart(11683)
        }
Coverage.statementStart(11684)
        for ((k, v) in bindings) {
Coverage.forLoopStart(11685)
            res += "(" + v.toSparql() + " AS " + AOPVariable(query, k).toSparql() + ")"
Coverage.statementStart(11686)
        }
Coverage.statementStart(11687)
        return res
    }
    override fun cloneOP(): POPGroup {
Coverage.funStart(11688)
        if (bindings.size > 0) {
Coverage.ifStart(11689)
            var tmpBindings = POPBind(query, listOf<String>(), AOPVariable(query, bindings[0].first), bindings[0].second, OPEmptyRow(query))
Coverage.statementStart(11690)
            for (bp in 1 until bindings.size) {
Coverage.forLoopStart(11691)
                tmpBindings = POPBind(query, listOf<String>(), AOPVariable(query, bindings[bp].first), bindings[bp].second, tmpBindings)
Coverage.statementStart(11692)
            }
Coverage.statementStart(11693)
            return POPGroup(query, projectedVariables, by, tmpBindings, children[0].cloneOP())
        } else {
Coverage.statementStart(11694)
            return POPGroup(query, projectedVariables, by, null, children[0].cloneOP())
        }
Coverage.statementStart(11695)
    }
    constructor(query: Query, projectedVariables: List<String>, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super(query, projectedVariables, EOperatorID.POPGroupID, "POPGroup", arrayOf(child), ESortPriority.PREVENT_ANY) {
        this.by = by
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
Coverage.whileLoopStart(11696)
            this.bindings.add(Pair(tmpBind.name.name, tmpBind.children[1] as AOPBase))
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
    }
    constructor(query: Query, projectedVariables: List<String>, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: OPBase) : super(query, projectedVariables, EOperatorID.POPGroupID, "POPGroup", arrayOf(child), ESortPriority.PREVENT_ANY) {
        this.by = by
        this.bindings = bindings.toMutableList()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11697)
        if (other !is POPGroup) {
Coverage.ifStart(11698)
            return false
        }
Coverage.statementStart(11699)
        if (by.size != other.by.size) {
Coverage.ifStart(11700)
            return false
        }
Coverage.statementStart(11701)
        for (i in 0 until by.size) {
Coverage.forLoopStart(11702)
            if (by[i] != other.by[i]) {
Coverage.ifStart(11703)
                return false
            }
Coverage.statementStart(11704)
        }
Coverage.statementStart(11705)
        if (bindings.size != other.bindings.size) {
Coverage.ifStart(11706)
            return false
        }
Coverage.statementStart(11707)
        for (i in 0 until bindings.size) {
Coverage.forLoopStart(11708)
            if (bindings[i].first != other.bindings[i].first) {
Coverage.ifStart(11709)
                return false
            }
Coverage.statementStart(11710)
            if (bindings[i].second != other.bindings[i].second) {
Coverage.ifStart(11711)
                return false
            }
Coverage.statementStart(11712)
        }
Coverage.statementStart(11713)
        if (children[0] != other.children[0]) {
Coverage.ifStart(11714)
            return false
        }
Coverage.statementStart(11715)
        return true
    }
    override fun getProvidedVariableNamesInternal() = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { bindings[it].first }).distinct()
    override fun getRequiredVariableNames(): List<String> {
Coverage.funStart(11716)
        var res = MutableList(by.size) { by[it].name }
Coverage.statementStart(11717)
        for (b in bindings) {
Coverage.forLoopStart(11718)
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
Coverage.statementStart(11719)
        }
Coverage.statementStart(11720)
        return res.distinct()
    }
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
Coverage.funStart(11721)
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
Coverage.statementStart(11722)
        SanityCheck.check({ additionalProvided.isEmpty() })
Coverage.statementStart(11723)
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
Coverage.statementStart(11724)
        val localRequire = mutableListOf<String>()
Coverage.statementStart(11725)
        for (v in by) {
Coverage.forLoopStart(11726)
            localRequire.add(v.name)
Coverage.statementStart(11727)
        }
Coverage.statementStart(11728)
        for (b in bindings) {
Coverage.forLoopStart(11729)
            localRequire += b.second.getRequiredVariableNamesRecoursive()
Coverage.statementStart(11730)
        }
Coverage.statementStart(11731)
        if (!localProvide.containsAll(localRequire)) {
Coverage.ifStart(11732)
            if (autocorrect) {
Coverage.ifStart(11733)
                for (name in localRequire) {
Coverage.forLoopStart(11734)
                    var found = false
Coverage.statementStart(11735)
                    for (prov in localProvide) {
Coverage.forLoopStart(11736)
                        if (prov == name) {
Coverage.ifStart(11737)
                            found = true
Coverage.statementStart(11738)
                            break
                        }
Coverage.statementStart(11739)
                    }
Coverage.statementStart(11740)
                    if (!found) {
Coverage.ifStart(11741)
                        for (b in by) {
Coverage.forLoopStart(11742)
                            if (b.name == name) {
Coverage.ifStart(11743)
                                throw Exception("undefined GROUP BY column >$name<")
                            }
Coverage.statementStart(11744)
                        }
Coverage.statementStart(11745)
                        for (b in bindings.indices) {
Coverage.forLoopStart(11746)
                            bindings[b] = Pair(bindings[b].first, replaceVariableWithUndef(bindings[b].second, name) as AOPBase)
Coverage.statementStart(11747)
                        }
Coverage.statementStart(11748)
                    }
Coverage.statementStart(11749)
                }
Coverage.statementStart(11750)
            } else {
Coverage.ifStart(11751)
                throw Exception("$classname undefined Variable")
            }
Coverage.statementStart(11752)
        }
Coverage.statementStart(11753)
    }
    fun getAggregations(node: OPBase): MutableList<AOPAggregationBase> {
Coverage.funStart(11754)
        var res = mutableListOf<AOPAggregationBase>()
Coverage.statementStart(11755)
        for (n in node.children) {
Coverage.forLoopStart(11756)
            res.addAll(getAggregations(n))
Coverage.statementStart(11757)
        }
Coverage.statementStart(11758)
        if (node is AOPAggregationBase) {
Coverage.ifStart(11759)
            res.add(node)
Coverage.statementStart(11760)
        }
Coverage.statementStart(11761)
        return res
    }
    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
Coverage.funStart(11762)
            var res = 0
Coverage.statementStart(11763)
            for (i in 0 until data.size) {
Coverage.forLoopStart(11764)
                res += data[i].hashCode()
Coverage.statementStart(11765)
            }
Coverage.statementStart(11766)
            return res
        }
        override fun equals(other: Any?): Boolean {
Coverage.funStart(11767)
            SanityCheck.check { other is MapKey }
Coverage.statementStart(11768)
            for (i in 0 until data.size) {
Coverage.forLoopStart(11769)
                if (data[i] != (other as MapKey).data[i]) {
Coverage.ifStart(11770)
                    return false
                }
Coverage.statementStart(11771)
            }
Coverage.statementStart(11772)
            return true
        }
        fun equalsFuzzy(other: Any?): Boolean {
Coverage.funStart(11773)
            SanityCheck.check { other is MapKey }
Coverage.statementStart(11774)
            for (i in 0 until data.size) {
Coverage.forLoopStart(11775)
                if (data[i] != ResultSetDictionary.undefValue && (other as MapKey).data[i] != ResultSetDictionary.undefValue && data[i] != other.data[i]) {
Coverage.ifStart(11776)
                    return false
                }
Coverage.statementStart(11777)
            }
Coverage.statementStart(11778)
            return true
        }
    }
    class MapRow(val iterators: IteratorBundle, val aggregates: Array<ColumnIteratorAggregate>, val columns: Array<ColumnIteratorQueue>)
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11779)
        val localVariables = children[0].getProvidedVariableNames()
Coverage.statementStart(11780)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11781)
        val child = children[0].evaluate()
Coverage.statementStart(11782)
        val aggregations = mutableListOf<AOPAggregationBase>()
Coverage.statementStart(11783)
        for (b in bindings) {
Coverage.forLoopStart(11784)
            aggregations.addAll(getAggregations(b.second))
Coverage.statementStart(11785)
        }
Coverage.statementStart(11786)
        val keyColumnNames = Array(by.size) { by[it].name }
Coverage.statementStart(11787)
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
Coverage.statementStart(11788)
        val valueColumnNames = mutableListOf<String>()
Coverage.statementStart(11789)
        for (name in localVariables) {
Coverage.forLoopStart(11790)
            if (!keyColumnNames.contains(name)) {
Coverage.ifStart(11791)
                valueColumnNames.add(name)
Coverage.statementStart(11792)
            }
Coverage.statementStart(11793)
        }
Coverage.statementStart(11794)
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
Coverage.statementStart(11795)
        if (keyColumnNames.size == 0) {
Coverage.ifStart(11796)
            val localMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11797)
            val localColumns = Array(valueColumnNames.size) { ColumnIteratorQueue() }
Coverage.statementStart(11798)
            for (columnIndex in 0 until valueColumnNames.size) {
Coverage.forLoopStart(11799)
                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
Coverage.statementStart(11800)
            }
Coverage.statementStart(11801)
            val row = IteratorBundle(localMap)
Coverage.statementStart(11802)
            val localAggregations = Array(aggregations.size) {
Coverage.statementStart(11803)
                val tmp = aggregations[it].createIterator(row)
Coverage.statementStart(11804)
                localMap["#" + aggregations[it].uuid] = tmp
Coverage.statementStart(11805)
                /*return*/tmp
            }
Coverage.statementStart(11806)
            val localRow = MapRow(row, localAggregations, localColumns)
Coverage.statementStart(11807)
            if (valueColumnNames.size == 0) {
Coverage.ifStart(11808)
                for (i in 0 until child.count) {
Coverage.forLoopStart(11809)
                    for (aggregate in localRow.aggregates) {
Coverage.forLoopStart(11810)
                        aggregate.evaluate()
Coverage.statementStart(11811)
                    }
Coverage.statementStart(11812)
                }
Coverage.statementStart(11813)
            } else {
Coverage.ifStart(11814)
                loop2@ while (true) {
Coverage.whileLoopStart(11815)
                    for (columnIndex in 0 until valueColumnNames.size) {
Coverage.forLoopStart(11816)
                        val value = valueColumns[columnIndex].next()
Coverage.statementStart(11817)
                        if (value == null) {
Coverage.ifStart(11818)
                            SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(11819)
                            break@loop2
                        }
Coverage.statementStart(11820)
                        localRow.columns[columnIndex].tmp = value
Coverage.statementStart(11821)
                    }
Coverage.statementStart(11822)
                    for (aggregate in localRow.aggregates) {
Coverage.forLoopStart(11823)
                        aggregate.evaluate()
Coverage.statementStart(11824)
                    }
Coverage.statementStart(11825)
                }
Coverage.statementStart(11826)
            }
Coverage.statementStart(11827)
            for (columnIndex in 0 until bindings.size) {
Coverage.forLoopStart(11828)
                val value = query.dictionary.createValue(bindings[columnIndex].second.evaluate(localRow.iterators)())
Coverage.statementStart(11829)
                outMap[bindings[columnIndex].first] = ColumnIteratorDebug(uuid, bindings[columnIndex].first, ColumnIteratorRepeatValue(1, value))
Coverage.statementStart(11830)
            }
Coverage.statementStart(11831)
        } else {
Coverage.ifStart(11832)
            val map = mutableMapOf<MapKey, MapRow>()
Coverage.statementStart(11833)
            loop@ while (true) {
Coverage.whileLoopStart(11834)
                val currentKey = Array(keyColumnNames.size) { ResultSetDictionary.undefValue }
Coverage.statementStart(11835)
                for (columnIndex in 0 until keyColumnNames.size) {
Coverage.forLoopStart(11836)
                    val value = keyColumns[columnIndex].next()
Coverage.statementStart(11837)
                    if (value == null) {
Coverage.ifStart(11838)
                        SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(11839)
                        break@loop
                    }
Coverage.statementStart(11840)
                    currentKey[columnIndex] = value
Coverage.statementStart(11841)
                }
Coverage.statementStart(11842)
                val key = MapKey(currentKey)
Coverage.statementStart(11843)
                var localRow = map[key]
Coverage.statementStart(11844)
                if (localRow == null) {
Coverage.ifStart(11845)
                    val localMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11846)
                    val localColumns = Array(valueColumnNames.size) { ColumnIteratorQueue() }
Coverage.statementStart(11847)
                    for (columnIndex in 0 until keyColumnNames.size) {
Coverage.forLoopStart(11848)
                        val tmp = ColumnIteratorQueue()
Coverage.statementStart(11849)
                        tmp.tmp = currentKey[columnIndex]
Coverage.statementStart(11850)
                        localMap[keyColumnNames[columnIndex]] = tmp
Coverage.statementStart(11851)
                    }
Coverage.statementStart(11852)
                    for (columnIndex in 0 until valueColumnNames.size) {
Coverage.forLoopStart(11853)
                        localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
Coverage.statementStart(11854)
                    }
Coverage.statementStart(11855)
                    val row = IteratorBundle(localMap)
Coverage.statementStart(11856)
                    val localAggregations = Array(aggregations.size) {
Coverage.statementStart(11857)
                        val tmp = aggregations[it].createIterator(row)
Coverage.statementStart(11858)
                        localMap["#" + aggregations[it].uuid] = tmp
Coverage.statementStart(11859)
                        /*return*/tmp
                    }
Coverage.statementStart(11860)
                    localRow = MapRow(row, localAggregations, localColumns)
Coverage.statementStart(11861)
                    map[key] = localRow
Coverage.statementStart(11862)
                }
Coverage.statementStart(11863)
                for (columnIndex in 0 until valueColumnNames.size) {
Coverage.forLoopStart(11864)
                    localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
Coverage.statementStart(11865)
                }
Coverage.statementStart(11866)
                for (aggregate in localRow.aggregates) {
Coverage.forLoopStart(11867)
                    aggregate.evaluate()
Coverage.statementStart(11868)
                }
Coverage.statementStart(11869)
            }
Coverage.statementStart(11870)
            val outKeys = Array(keyColumnNames.size) { MyListValue() }
Coverage.statementStart(11871)
            val outValues = Array(bindings.size) { MyListValue() }
Coverage.statementStart(11872)
            for ((k, v) in map) {
Coverage.forLoopStart(11873)
                for (columnIndex in 0 until keyColumnNames.size) {
Coverage.forLoopStart(11874)
                    outKeys[columnIndex].add(k.data[columnIndex])
Coverage.statementStart(11875)
                }
Coverage.statementStart(11876)
                for (columnIndex in 0 until bindings.size) {
Coverage.forLoopStart(11877)
                    outValues[columnIndex].add(query.dictionary.createValue(bindings[columnIndex].second.evaluate(v.iterators)()))
Coverage.statementStart(11878)
                }
Coverage.statementStart(11879)
            }
Coverage.statementStart(11880)
            for (columnIndex in 0 until keyColumnNames.size) {
Coverage.forLoopStart(11881)
                outMap[keyColumnNames[columnIndex]] = ColumnIteratorDebug(uuid, keyColumnNames[columnIndex], ColumnIteratorMultiValue(outKeys[columnIndex]))
Coverage.statementStart(11882)
            }
Coverage.statementStart(11883)
            for (columnIndex in 0 until bindings.size) {
Coverage.forLoopStart(11884)
                outMap[bindings[columnIndex].first] = ColumnIteratorDebug(uuid, bindings[columnIndex].first, ColumnIteratorMultiValue(outValues[columnIndex]))
Coverage.statementStart(11885)
            }
Coverage.statementStart(11886)
        }
Coverage.statementStart(11887)
        return IteratorBundle(outMap)
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(11888)
        val res = super.toXMLElement()
Coverage.statementStart(11889)
        val byxml = XMLElement("by")
Coverage.statementStart(11890)
        res.addContent(byxml)
Coverage.statementStart(11891)
        for (b in by) {
Coverage.forLoopStart(11892)
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
Coverage.statementStart(11893)
        }
Coverage.statementStart(11894)
        val xmlbindings = XMLElement("bindings")
Coverage.statementStart(11895)
        res.addContent(xmlbindings)
Coverage.statementStart(11896)
        for (b in bindings) {
Coverage.forLoopStart(11897)
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", b.first).addContent(b.second.toXMLElement()))
Coverage.statementStart(11898)
        }
Coverage.statementStart(11899)
        return res
    }
}
