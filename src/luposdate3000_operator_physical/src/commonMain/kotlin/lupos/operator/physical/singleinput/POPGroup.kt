/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.operator.physical.singleinput

import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.physical.MapKey
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.GroupByColumnMissing
import lupos.shared.GroupByDuplicateColumnException
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.VariableNotDefinedSyntaxException
import lupos.shared.XMLElement
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// TODO refactor such that the optimizer may choose which strategy to use
public class POPGroup : POPBase {
    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        /*possibilities for_ next operator*/
        val res = mutableListOf<List<SortHelper>>()
        val provided = by.map { it.name }
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

    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == 1 })
        return 1
    }

    @JvmField
    public var by: List<AOPVariable>

    @JvmField
    public var bindings: MutableList<Pair<String, AOPBase>> = mutableListOf()
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
        return if (bindings.size > 0) {
            var tmpBindings = POPBind(query, listOf(), AOPVariable(query, bindings[0].first), bindings[0].second, OPEmptyRow(query))
            for (bp in 1 until bindings.size) {
                tmpBindings = POPBind(query, listOf(), AOPVariable(query, bindings[bp].first), bindings[bp].second, tmpBindings)
            }
            POPGroup(query, projectedVariables, by, tmpBindings, children[0].cloneOP())
        } else {
            POPGroup(query, projectedVariables, by, null, children[0].cloneOP())
        }
    }

    public constructor(query: IQuery, projectedVariables: List<String>, by: List<AOPVariable>, bindings: POPBind?, child: IOPBase) : super(query, projectedVariables, EOperatorIDExt.POPGroupID, "POPGroup", arrayOf(child), ESortPriorityExt.GROUP) {
        this.by = by
        var tmpBind: IOPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(tmpBind.name.name, tmpBind.children[1] as AOPBase))
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
    }

    public constructor(query: IQuery, projectedVariables: List<String>, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: IOPBase) : super(query, projectedVariables, EOperatorIDExt.POPGroupID, "POPGroup", arrayOf(child), ESortPriorityExt.GROUP) {
        this.by = by
        this.bindings = bindings.toMutableList()
    }

    override fun equals(other: Any?): Boolean = other is POPGroup && by == other.by && children[0] == other.children[0] && bindings == other.bindings
    override fun getProvidedVariableNamesInternal(): List<String> = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { bindings[it].first }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        val res = MutableList(by.size) { by[it].name }
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { additionalProvided.isEmpty() })
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
                            bindings[b] = Pair(bindings[b].first, bindings[b].second.replaceVariableWithUndef(name, true) as AOPBase)
                        }
                    }
                }
            } else {
                val tmp = localRequire.toMutableSet()
                tmp.removeAll(localProvide)
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    private fun getAggregations(node: IOPBase): MutableList<AOPAggregationBase> {
        val res = mutableListOf<AOPAggregationBase>()
        for (n in node.getChildren()) {
            res.addAll(getAggregations(n))
        }
        if (node is AOPAggregationBase) {
            res.add(node)
        }
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val buffer = ByteArrayWrapper()
        val localVariables = children[0].getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumnNames = Array<String>(by.size) { by[it].name }
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
        if (keyColumnNames.isEmpty()) {
            val localMap = mutableMapOf<String, ColumnIterator>()
            val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
            for (columnIndex in 0 until valueColumnNames.size) {
                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
            }
            val row = IteratorBundle(localMap)
            val localAggregations = Array(aggregations.size) {
                val tmp = aggregations[it].createIterator(row)
                localMap["#" + aggregations[it].uuid] = tmp
                tmp
            }
            val localRow = POPGroup_Row(row, localAggregations, localColumns)
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
                        if (value == DictionaryValueHelper.nullValue) {
                            SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
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
                val columnName = bindings[columnIndex].first
                if (projectedVariables.contains(columnName)) {
                    val value = bindings[columnIndex].second.evaluateID(localRow.iterators)()
                    outMap[columnName] = ColumnIteratorRepeatValue(1, value)
                }
            }
        } else {
            val tmpSortPriority = children[0].getMySortPriority().map { it.variableName }
            var canUseSortedInput = true
            if ((!localVariables.containsAll(keyColumnNames.toMutableList())) || (tmpSortPriority.size < keyColumnNames.size)) {
                canUseSortedInput = false
            } else {
                for (element in keyColumnNames) {
                    if (!tmpSortPriority.contains(element)) {
                        canUseSortedInput = false
                        break
                    }
                }
            }
            if (canUseSortedInput) {
                var currentKey = DictionaryValueTypeArray(keyColumnNames.size) { DictionaryValueHelper.undefValue }
                var nextKey: DictionaryValueTypeArray? = null
                // first row ->
                var emptyResult = false
                for (columnIndex in keyColumnNames.indices) {
                    val value = keyColumns[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
                        for (element in keyColumns) {
                            element.close()
                        }
                        for (element in valueColumns) {
                            element.close()
                        }
                        SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                        emptyResult = true
                        break
                    }
                    currentKey[columnIndex] = value
                }
                if (emptyResult) {
                    // there is no first row
                    for (v in keyColumnNames) {
                        if (projectedVariables.contains(v)) {
                            outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                    }
                    for ((first) in bindings) {
                        if (projectedVariables.contains(first)) {
                            outMap[first] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                    }
                } else {
                    val localMap = mutableMapOf<String, ColumnIterator>()
                    var localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                    for (columnIndex in keyColumnNames.indices) {
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
                        tmp
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localRowColumns[columnIndex].tmp = valueColumns[columnIndex].next()
                    }
                    for (aggregate in localRowAggregates) {
                        aggregate.evaluate()
                    }
                    // first row <-
                    val output = mutableListOf<ColumnIteratorQueue>()
                    for (outIndex in 0 until keyColumnNames.size + bindings.size) {
                        val iterator = object : ColumnIteratorQueue() {
                            override /*suspend*/ fun close() {
                                __close()
                            }

                            @Suppress("NOTHING_TO_INLINE")
                            /*suspend*/ inline fun __close() {
                                if (label != 0) {
                                    ColumnIteratorQueueExt._close(this)
                                    for (element in keyColumns) {
                                        element.close()
                                    }
                                    for (element in valueColumns) {
                                        element.close()
                                    }
                                }
                            }

                            override /*suspend*/ fun next(): DictionaryValueType {
                                return ColumnIteratorQueueExt.nextHelper(
                                    this,
                                    {
                                        loop@ while (true) {
                                            var changedKey = false
                                            if (nextKey != null) {
                                                currentKey = nextKey!!
                                                nextKey = null
                                            }
                                            for (columnIndex in keyColumnNames.indices) {
                                                val value = keyColumns[columnIndex].next()
                                                if (value == DictionaryValueHelper.nullValue) {
                                                    for (element in keyColumns) {
                                                        element.close()
                                                    }
                                                    for (element in valueColumns) {
                                                        element.close()
                                                    }
                                                    SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                                                    for (columnIndex2 in keyColumnNames.indices) {
                                                        if (projectedVariables.contains(keyColumnNames[columnIndex2])) {
                                                            output[columnIndex2].queue.add(currentKey[columnIndex2])
                                                        }
                                                    }
                                                    for (columnIndex2 in 0 until bindings.size) {
                                                        if (projectedVariables.contains(bindings[columnIndex2].first)) {
                                                            output[columnIndex2 + keyColumnNames.size].queue.add(bindings[columnIndex2].second.evaluateID(localRowIterators)())
                                                        }
                                                    }
                                                    for (outIndex2 in 0 until output.size) {
                                                        ColumnIteratorQueueExt.closeOnEmptyQueue(output[outIndex2])
                                                    }
                                                    break@loop
                                                }
                                                if (nextKey != null) {
                                                    nextKey!![columnIndex] = value
                                                } else if (currentKey[columnIndex] != value) {
                                                    nextKey = DictionaryValueTypeArray(keyColumnNames.size) { currentKey[it] }
                                                    nextKey!![columnIndex] = value
                                                    changedKey = true
                                                }
                                            }
                                            if (changedKey) {
                                                for (columnIndex in keyColumnNames.indices) {
                                                    if (projectedVariables.contains(keyColumnNames[columnIndex])) {
                                                        output[columnIndex].queue.add(currentKey[columnIndex])
                                                    }
                                                }
                                                for (columnIndex in 0 until bindings.size) {
                                                    if (projectedVariables.contains(bindings[columnIndex].first)) {
                                                        output[columnIndex + keyColumnNames.size].queue.add(bindings[columnIndex].second.evaluateID(localRowIterators)())
                                                    }
                                                }
                                                localMap.clear()
                                                localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                                                for (columnIndex in keyColumnNames.indices) {
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
                                                    tmp
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
                                    },
                                    { __close() }
                                )
                            }
                        }
                        output.add(iterator)
                    }
                    for (columnIndex in keyColumnNames.indices) {
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
                if (bindings.size == 1 && bindings.toList().first().second is AOPAggregationCOUNT &&
// simplicity ->
                    keyColumnNames.size == 1 && valueColumnNames.size == 0
// <- simplicity
                ) {
                    val iterator = keyColumns[0]
                    val map = mutableMapOf<DictionaryValueType, Int>()
                    while (true) {
                        val value = iterator.next()
                        if (value == DictionaryValueHelper.nullValue) {
                            iterator.close()
                            break
                        }
                        val v = map[value]
                        if (v == null) {
                            map[value] = 1
                        } else {
                            map[value] = v + 1
                        }
                    }
                    val arrK = DictionaryValueTypeArray(map.size)
                    val arrV = DictionaryValueTypeArray(map.size)
                    var i = 0
                    val dict = query.getDictionary()
                    for ((k, v) in map) {
                        arrK[i] = k
                        DictionaryHelper.integerToByteArray(buffer, BigInteger(v))
                        arrV[i] = dict.createValue(buffer)
                        i++
                    }
                    outMap[keyColumnNames[0]] = ColumnIteratorMultiValue(arrK, arrK.size)
                    outMap[bindings.toList().first().first] = ColumnIteratorMultiValue(arrV, arrV.size)
                } else {
                    val map = mutableMapOf<MapKey, POPGroup_Row>()
                    loop@ while (true) {
                        val currentKey = DictionaryValueTypeArray(keyColumnNames.size) { DictionaryValueHelper.undefValue }
                        for (columnIndex in keyColumnNames.indices) {
                            val value = keyColumns[columnIndex].next()
                            if (value == DictionaryValueHelper.nullValue) {
                                for (element in keyColumns) {
                                    element.close()
                                }
                                for (element in valueColumns) {
                                    element.close()
                                }
                                SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                                break@loop
                            }
                            currentKey[columnIndex] = value
                        }
                        val key = MapKey(currentKey)
                        var localRow = map[key]
                        if (localRow == null) {
                            val localMap = mutableMapOf<String, ColumnIterator>()
                            val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                            for (columnIndex in keyColumnNames.indices) {
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
                                tmp
                            }
                            localRow = POPGroup_Row(row, localAggregations, localColumns)
                            map[key] = localRow
                        }
                        for (columnIndex in 0 until valueColumnNames.size) {
                            localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
                        }
                        for (aggregate in localRow.aggregates) {
                            aggregate.evaluate()
                        }
                    }
                    if (map.isEmpty()) {
                        for (v in keyColumnNames) {
                            outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                        for ((first) in bindings) {
                            outMap[first] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                    } else {
                        val outKeys = Array(keyColumnNames.size) { mutableListOf<DictionaryValueType>() }
                        val outValues = Array(bindings.size) { mutableListOf<DictionaryValueType>() }
                        for ((k, v) in map) {
                            for (columnIndex in keyColumnNames.indices) {
                                outKeys[columnIndex].add(k.data[columnIndex])
                            }
                            for (columnIndex in 0 until bindings.size) {
                                outValues[columnIndex].add(bindings[columnIndex].second.evaluateID(v.iterators)())
                            }
                        }
                        for (columnIndex in keyColumnNames.indices) {
                            outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
                        }
                        for (columnIndex in 0 until bindings.size) {
                            outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
                        }
                    }
                }
            }
        }
        return IteratorBundle(outMap)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        }
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for ((first, second) in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", first).addContent(second.toXMLElement(partial)))
        }
        return res
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
