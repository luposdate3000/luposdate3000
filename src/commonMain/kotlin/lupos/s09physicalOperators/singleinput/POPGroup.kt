package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GroupByColumnMissing
import lupos.s00misc.GroupByDuplicateColumnException
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s00misc.VariableNotDefinedSyntaxException
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
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind

class POPGroup : POPBase {
override fun getPartitionCount(variable:String):Int{
SanityCheck.check{children[0].getPartitionCount(variable)==1}
return 1
}
    @JvmField
    var by: List<AOPVariable>

    @JvmField
    var bindings = mutableListOf<Pair<String, AOPBase>>()
    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        val res = mutableListOf<List<SortHelper>>()
        val provided = Array(by.size) { by[it].name }
        for (x in children[0].getPossibleSortPriorities()) {
            val tmp = mutableListOf<SortHelper>()
            for (v in x) {
                if (provided.contains(v.variableName)) {
                    tmp.add(v)
                } else {
                    break
                }
            }
            addToPrefixFreeList(tmp, res)
        }
        return res
    }

    override fun toSparql(): String {
        var res = children[0].toSparql()
        res += " GROUP BY "
        for (b in by) {
            res += b.toSparql() + " "
        }
        for ((k, v) in bindings) {
            res += "(" + v.toSparql() + " AS " + AOPVariable(query, k).toSparql() + ")"
        }
        return res
    }

    override fun cloneOP(): POPGroup {
        if (bindings.size > 0) {
            var tmpBindings = POPBind(query, listOf<String>(), AOPVariable(query, bindings[0].first), bindings[0].second, OPEmptyRow(query))
            for (bp in 1 until bindings.size) {
                tmpBindings = POPBind(query, listOf<String>(), AOPVariable(query, bindings[bp].first), bindings[bp].second, tmpBindings)
            }
            return POPGroup(query, projectedVariables, by, tmpBindings, children[0].cloneOP())
        } else {
            return POPGroup(query, projectedVariables, by, null, children[0].cloneOP())
        }
/*Coverage Unreachable*/
    }

    constructor(query: Query, projectedVariables: List<String>, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super(query, projectedVariables, EOperatorID.POPGroupID, "POPGroup", arrayOf(child), ESortPriority.PREVENT_ANY) {
        this.by = by
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(tmpBind.name.name, tmpBind.children[1] as AOPBase))
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
    }

    constructor(query: Query, projectedVariables: List<String>, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: OPBase) : super(query, projectedVariables, EOperatorID.POPGroupID, "POPGroup", arrayOf(child), ESortPriority.PREVENT_ANY) {
        this.by = by
        this.bindings = bindings.toMutableList()
    }

    override fun equals(other: Any?) = other is POPGroup && by == other.by && children[0] == other.children[0] && bindings == other.bindings
    override fun getProvidedVariableNamesInternal() = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { bindings[it].first }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        var res = MutableList(by.size) { by[it].name }
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        SanityCheck.check({ additionalProvided.isEmpty() })
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by) {
            localRequire.add(v.name)
        }
        for (b in bindings) {
            localRequire += b.second.getRequiredVariableNamesRecoursive()
        }
        if (!localProvide.containsAll(localRequire)) {
            if (autocorrect) {
                for (name in localRequire) {
                    var found = false
                    for (prov in localProvide) {
                        if (prov == name) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        for (b in by) {
                            if (b.name == name) {
                                throw GroupByColumnMissing(name)
                            }
                        }
                        for (b in bindings.indices) {
                            bindings[b] = Pair(bindings[b].first, replaceVariableWithUndef(bindings[b].second, name, true) as AOPBase)
                        }
                    }
                }
            } else {
                var tmp = localRequire.toMutableSet()
                tmp.removeAll(localProvide)
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    fun getAggregations(node: OPBase): MutableList<AOPAggregationBase> {
        var res = mutableListOf<AOPAggregationBase>()
        for (n in node.children) {
            res.addAll(getAggregations(n))
        }
        if (node is AOPAggregationBase) {
            res.add(node)
        }
        return res
    }

    class MapKey(@JvmField val data: IntArray) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size) {
                res += data[i].hashCode()
            }
            return res
        }

        override fun equals(other: Any?) = other is MapKey && data.contentEquals(other.data)
    }

    class MapRow(val iterators: IteratorBundle, val aggregates: Array<ColumnIteratorAggregate>, val columns: Array<ColumnIteratorQueue>)

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val localVariables = children[0].getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumnNames = Array(by.size) { by[it].name }
        if (keyColumnNames.size != keyColumnNames.distinct().size) {
            throw GroupByDuplicateColumnException()
        }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        if (keyColumnNames.size == 0) {
            SanityCheck.println({ "group mode a" })
            val localMap = mutableMapOf<String, ColumnIterator>()
            val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
            for (columnIndex in 0 until valueColumnNames.size) {
                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
            }
            val row = IteratorBundle(localMap)
            val localAggregations = Array(aggregations.size) {
                val tmp = aggregations[it].createIterator(row)
                localMap["#" + aggregations[it].uuid] = tmp
                /*return*/tmp
            }
            val localRow = MapRow(row, localAggregations, localColumns)
            if (valueColumnNames.size == 0) {
                for (i in 0 until child.count()) {
                    for (aggregate in localRow.aggregates) {
                        aggregate.evaluate()
                    }
                }
            } else {
                loop2@ while (true) {
                    for (columnIndex in 0 until valueColumnNames.size) {
                        val value = valueColumns[columnIndex].next()
                        if (value == ResultSetDictionary.nullValue) {
                            SanityCheck.check { columnIndex == 0 }
                            for (closeIndex in 0 until valueColumnNames.size) {
                                valueColumns[closeIndex].close()
                            }
                            break@loop2
                        }
                        localRow.columns[columnIndex].tmp = value
                    }
                    for (aggregate in localRow.aggregates) {
                        aggregate.evaluate()
                    }
                }
            }
            for (columnIndex in 0 until bindings.size) {
                val value = query.dictionary.createValue(bindings[columnIndex].second.evaluate(localRow.iterators)())
                if (projectedVariables.contains(bindings[columnIndex].first)) {
                    outMap[bindings[columnIndex].first] = ColumnIteratorRepeatValue(1, value)
                }
            }
        } else {
            var tmpSortPriority = children[0].mySortPriority.map { it.variableName }
            var canUseSortedInput = true
            if ((!localVariables.containsAll(keyColumnNames.toMutableList())) || (children[0].mySortPriority.size < keyColumnNames.size)) {
                canUseSortedInput = false
            } else {
                for (i in 0 until keyColumnNames.size) {
                    if (!tmpSortPriority.contains(keyColumnNames[i])) {
                        canUseSortedInput = false
                        break
                    }
                }
            }
            if (canUseSortedInput) {
                SanityCheck.println({ "group mode b" })
                var currentKey = IntArray(keyColumnNames.size) { ResultSetDictionary.undefValue }
                var nextKey: IntArray? = null
                //first row ->
                var emptyResult = false
                for (columnIndex in 0 until keyColumnNames.size) {
                    val value = keyColumns[columnIndex].next()
                    if (value == ResultSetDictionary.nullValue) {
                        for (closeIndex in 0 until keyColumns.size) {
                            keyColumns[closeIndex].close()
                        }
                        for (closeIndex in 0 until valueColumns.size) {
                            valueColumns[closeIndex].close()
                        }
                        SanityCheck.check { columnIndex == 0 }
                        emptyResult = true
                        break
                    }
                    currentKey[columnIndex] = value
                }
                if (emptyResult) {
                    //there is no first row
                    for (v in keyColumnNames) {
                        if (projectedVariables.contains(v)) {
                            outMap[v] = ColumnIteratorRepeatValue(1, ResultSetDictionary.undefValue)
                        }
                    }
                    for (b in bindings) {
                        if (projectedVariables.contains(b.first)) {
                            outMap[b.first] = ColumnIteratorRepeatValue(1, ResultSetDictionary.undefValue)
                        }
                    }
                } else {
                    val localMap = mutableMapOf<String, ColumnIterator>()
                    var localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                    for (columnIndex in 0 until keyColumnNames.size) {
                        val tmp = ColumnIteratorQueueEmpty()
                        tmp.tmp = currentKey[columnIndex]
                        localMap[keyColumnNames[columnIndex]] = tmp
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localMap[valueColumnNames[columnIndex]] = localRowColumns[columnIndex]
                    }
                    var localRowIterators = IteratorBundle(localMap)
                    var localRowAggregates = Array(aggregations.size) {
                        val tmp = aggregations[it].createIterator(localRowIterators)
                        localMap["#" + aggregations[it].uuid] = tmp
                        /*return*/tmp
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localRowColumns[columnIndex].tmp = valueColumns[columnIndex].next()
                    }
                    for (aggregate in localRowAggregates) {
                        aggregate.evaluate()
                    }
                    //first row <-
                    val output = mutableListOf<ColumnIteratorQueue>()
                    for (outIndex in 0 until keyColumnNames.size + bindings.size) {
                        val iterator = object : ColumnIteratorQueue() {
                            override suspend fun close() {
                                __close()
                            }

                            inline suspend fun __close() {
                                if (label != 0) {
                                    _close()
                                    for (closeIndex in 0 until keyColumns.size) {
                                        keyColumns[closeIndex].close()
                                    }
                                    for (closeIndex in 0 until valueColumns.size) {
                                        valueColumns[closeIndex].close()
                                    }
                                }
                            }

                            override suspend fun next(): Value {
                                return next_helper({
                                    loop@ while (true) {
                                        var changedKey = false
                                        if (nextKey != null) {
                                            currentKey = nextKey!!
                                            nextKey = null
                                        }
                                        for (columnIndex in 0 until keyColumnNames.size) {
                                            val value = keyColumns[columnIndex].next()
                                            if (value == ResultSetDictionary.nullValue) {
                                                for (closeIndex in 0 until keyColumns.size) {
                                                    keyColumns[closeIndex].close()
                                                }
                                                for (closeIndex in 0 until valueColumns.size) {
                                                    valueColumns[closeIndex].close()
                                                }
                                                SanityCheck.check { columnIndex == 0 }
                                                for (columnIndex2 in 0 until keyColumnNames.size) {
                                                    if (projectedVariables.contains(keyColumnNames[columnIndex2])) {
                                                        output[columnIndex2].queue.add(currentKey[columnIndex2])
                                                    }
                                                }
                                                for (columnIndex2 in 0 until bindings.size) {
                                                    if (projectedVariables.contains(bindings[columnIndex2].first)) {
                                                        output[columnIndex2 + keyColumnNames.size].queue.add(query.dictionary.createValue(bindings[columnIndex2].second.evaluate(localRowIterators)()))
                                                    }
                                                }
                                                for (outIndex2 in 0 until output.size) {
                                                    output[outIndex2].closeOnEmptyQueue()
                                                }
                                                break@loop
                                            }
                                            if (nextKey != null) {
                                                nextKey!![columnIndex] = value
                                            } else if (currentKey[columnIndex] != value) {
                                                nextKey = IntArray(keyColumnNames.size) { currentKey[it] }
                                                nextKey!![columnIndex] = value
                                                changedKey = true
                                            }
                                        }
                                        if (changedKey) {
                                            for (columnIndex in 0 until keyColumnNames.size) {
                                                if (projectedVariables.contains(keyColumnNames[columnIndex])) {
                                                    output[columnIndex].queue.add(currentKey[columnIndex])
                                                }
                                            }
                                            for (columnIndex in 0 until bindings.size) {
                                                if (projectedVariables.contains(bindings[columnIndex].first)) {
                                                    output[columnIndex + keyColumnNames.size].queue.add(query.dictionary.createValue(bindings[columnIndex].second.evaluate(localRowIterators)()))
                                                }
                                            }
                                            localMap.clear()
                                            localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                                            for (columnIndex in 0 until keyColumnNames.size) {
                                                val tmp = ColumnIteratorQueueEmpty()
                                                tmp.tmp = currentKey[columnIndex]
                                                localMap[keyColumnNames[columnIndex]] = tmp
                                            }
                                            for (columnIndex in 0 until valueColumnNames.size) {
                                                localMap[valueColumnNames[columnIndex]] = localRowColumns[columnIndex]
                                            }
                                            localRowIterators = IteratorBundle(localMap)
                                            localRowAggregates = Array(aggregations.size) {
                                                val tmp = aggregations[it].createIterator(localRowIterators)
                                                localMap["#" + aggregations[it].uuid] = tmp
                                                /*return*/tmp
                                            }
                                        }
                                        for (columnIndex in 0 until valueColumnNames.size) {
                                            localRowColumns[columnIndex].tmp = valueColumns[columnIndex].next()
                                        }
                                        for (aggregate in localRowAggregates) {
                                            aggregate.evaluate()
                                        }
                                        if (changedKey) {
                                            break@loop
                                        }
                                    }
                                }, { __close() })
                            }
                        }
                        output.add(iterator)
                    }
                    for (columnIndex in 0 until keyColumnNames.size) {
                        if (projectedVariables.contains(keyColumnNames[columnIndex])) {
                            outMap[keyColumnNames[columnIndex]] = output[columnIndex]
                        }
                    }
                    for (columnIndex in 0 until bindings.size) {
                        if (projectedVariables.contains(bindings[columnIndex].first)) {
                            outMap[bindings[columnIndex].first] = output[columnIndex + keyColumnNames.size]
                        }
                    }
                }
            } else {
                SanityCheck.println({ "group mode c" })
                val map = mutableMapOf<MapKey, MapRow>()
                loop@ while (true) {
                    val currentKey = IntArray(keyColumnNames.size) { ResultSetDictionary.undefValue }
                    for (columnIndex in 0 until keyColumnNames.size) {
                        val value = keyColumns[columnIndex].next()
                        if (value == ResultSetDictionary.nullValue) {
                            for (closeIndex in 0 until keyColumns.size) {
                                keyColumns[closeIndex].close()
                            }
                            for (closeIndex in 0 until valueColumns.size) {
                                valueColumns[closeIndex].close()
                            }
                            SanityCheck.check { columnIndex == 0 }
                            break@loop
                        }
                        currentKey[columnIndex] = value
                    }
                    val key = MapKey(currentKey)
                    var localRow = map[key]
                    if (localRow == null) {
                        val localMap = mutableMapOf<String, ColumnIterator>()
                        val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                        for (columnIndex in 0 until keyColumnNames.size) {
                            val tmp = ColumnIteratorQueueEmpty()
                            tmp.tmp = currentKey[columnIndex]
                            localMap[keyColumnNames[columnIndex]] = tmp
                        }
                        for (columnIndex in 0 until valueColumnNames.size) {
                            localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
                        }
                        val row = IteratorBundle(localMap)
                        val localAggregations = Array(aggregations.size) {
                            val tmp = aggregations[it].createIterator(row)
                            localMap["#" + aggregations[it].uuid] = tmp
                            /*return*/tmp
                        }
                        localRow = MapRow(row, localAggregations, localColumns)
                        map[key] = localRow
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
                    }
                    for (aggregate in localRow.aggregates) {
                        aggregate.evaluate()
                    }
                }
                if (map.size == 0) {
                    for (v in keyColumnNames) {
                        outMap[v] = ColumnIteratorRepeatValue(1, ResultSetDictionary.undefValue)
                    }
                    for (b in bindings) {
                        outMap[b.first] = ColumnIteratorRepeatValue(1, ResultSetDictionary.undefValue)
                    }
                } else {
                    val outKeys = Array(keyColumnNames.size) { MyListValue() }
                    val outValues = Array(bindings.size) { MyListValue() }
                    for ((k, v) in map) {
                        for (columnIndex in 0 until keyColumnNames.size) {
                            outKeys[columnIndex].add(k.data[columnIndex])
                        }
                        for (columnIndex in 0 until bindings.size) {
                            outValues[columnIndex].add(query.dictionary.createValue(bindings[columnIndex].second.evaluate(v.iterators)()))
                        }
                    }
                    for (columnIndex in 0 until keyColumnNames.size) {
                        outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
                    }
                    for (columnIndex in 0 until bindings.size) {
                        outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
                    }
                }
            }
        }
        return IteratorBundle(outMap)
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        }
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for (b in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", b.first).addContent(b.second.toXMLElement()))
        }
        return res
    }
}
