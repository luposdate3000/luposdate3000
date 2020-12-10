package lupos.s09physicalOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
class POPJoinHashMap(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun getPartitionCount(variable: String): Int {
        return if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                children[0].getPartitionCount(variable)
            } else {
                children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable $variable")
            }
        }
    }
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean = other is POPJoinHashMap && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    internal class MapKey(@JvmField val data: IntArray) {
        override fun hashCode(): Int {
            var res = 0
            for (element in data) {
                res += element.hashCode()
            }
            return res
        }
        override fun equals(other: Any?) = other is MapKey && data.contentEquals(other.data)
        fun equalsFuzzy(other: Any?): Boolean {
            SanityCheck.check { other is MapKey }
            for (i in data.indices) {
                if (data[i] != ResultSetDictionaryExt.undefValue && (other as MapKey).data[i] != ResultSetDictionaryExt.undefValue && data[i] != other.data[i]) {
                    return false
                }
            }
            return true
        }
    }
    internal class MapRow(columns: Int) {
        val columns = Array(columns) { mutableListOf<Int>() }
        var count = 0
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
// --- obtain child columns
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check { columns[0].size != 0 }
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
        val columnsINAO = mutableListOf<ColumnIterator>() // only in childA
        val columnsINBO = mutableListOf<ColumnIterator>() // only in childB
        val columnsINAJ = mutableListOf<ColumnIterator>() // join columnA
        val columnsINBJ = mutableListOf<ColumnIterator>() // join columnB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() } // only in one of the childs
        val outJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // J,O0,O1,J-but-not-outmap
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: IteratorBundle?
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                columnsINAJ.add(0, childA.columns[name]!!)
                columnsINBJ.add(0, childB.columns[name]!!)
                if (projectedVariables.contains(name)) {
                    outIterators.add(0, Pair(name, 0))
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINAO.add(childA.columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINBO.add(childB.columns[name]!!)
        }
        val emptyColumnsWithJoin = outIterators.size == 0 && columnsINAJ.size != 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val mapWithoutUndef = mutableMapOf<MapKey, MapRow>()
        val mapWithUndef = mutableMapOf<MapKey, MapRow>()
        var currentKey: IntArray? = null
        var nextKey: IntArray?
        var map: MutableMap<MapKey, MapRow> = mapWithUndef
        var nextMap: MutableMap<MapKey, MapRow>
        var key: MapKey
        var oldArr: MapRow?
        var count: Int
        var countA: Int
        var countB: Int
        SanityCheck.check { columnsINAJ.size > 0 }
// --- insert second child into hash table
        while (true) {
            count = if (currentKey != null) {
                1
            } else {
                0
            }
            loopB@ while (true) {
                nextKey = IntArray(columnsINBJ.size) { ResultSetDictionaryExt.undefValue }
                nextMap = mapWithoutUndef
                for (columnIndex in 0 until columnsINBJ.size) {
                    val value = columnsINBJ[columnIndex].next()
                    if (value == ResultSetDictionaryExt.nullValue) {
                        nextKey = null
                        break@loopB
                    }
                    nextKey!![columnIndex] = value
                    if (value == ResultSetDictionaryExt.undefValue) {
                        nextMap = mapWithUndef
                    }
                }
                if (currentKey == null) {
                    currentKey = nextKey
                    map = nextMap
                } else if (nextKey == null || MapKey(nextKey) != MapKey(currentKey)) {
                    break
                }
                count++
            }
            if (currentKey == null) {
                break
            }
            key = MapKey(currentKey)
            oldArr = map[key]
            if (oldArr == null) {
                oldArr = MapRow(columnsINBO.size)
                map[key] = oldArr
            }
            oldArr.count += count
            for (columnIndex in 0 until columnsINBO.size) {
// TODO dont use kotlin lists here, use pages instead
                for (j in 0 until count) {
                    oldArr.columns[columnIndex].add(columnsINBO[columnIndex].next())
                }
            }
            currentKey = nextKey
            map = nextMap
        }
        for (closeIndex in 0 until columnsINBJ.size) {
            columnsINBJ[closeIndex].close()
        }
        for (closeIndex in 0 until columnsINBO.size) {
            columnsINBO[closeIndex].close()
        }
// --- iterate first child
// --- calculate next "cartesian product"
        for ((first, second) in outIterators) {
            val iterator = object : ColumnIteratorChildIterator() {
                @JvmField
                val outIteratorsAllocated0 = outIteratorsAllocated
                @JvmField
                val columnsINAJ0 = columnsINAJ
                @JvmField
                val columnsINAO0 = columnsINAO
                override /*suspend*/ fun close() {
                    __close()
                }
                /*suspend*/ inline fun __close() {
                    if (label != 0) {
                        _close()
                        for (iterator2 in outIteratorsAllocated0) {
                            iterator2.closeOnNoMoreElements()
                        }
                        for (closeIndex in 0 until columnsINAJ0.size) {
                            columnsINAJ0[closeIndex].close()
                        }
                        for (closeIndex in 0 until columnsINAO0.size) {
                            columnsINAO0[closeIndex].close()
                        }
                    }
                }
                override /*suspend*/ fun next(): Int {
                    return nextHelper(
                        {
                            var done = false
                            while (!done) {
                                countA = if (currentKey == null) {
                                    0
                                } else {
                                    1
                                }
                                loopA@ while (true) {
                                    nextKey = IntArray(columnsINAJ.size) { ResultSetDictionaryExt.undefValue }
                                    nextMap = mapWithoutUndef
                                    for (columnIndex in 0 until columnsINAJ.size) {
                                        val value = columnsINAJ[columnIndex].next()
                                        if (value == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.check { columnIndex == 0 }
                                            nextKey = null
                                            break@loopA
                                        }
                                        nextKey!![columnIndex] = value
                                        if (value == ResultSetDictionaryExt.undefValue) {
                                            nextMap = mapWithUndef
                                        }
                                    }
                                    if (currentKey == null) {
                                        map = nextMap
                                        currentKey = nextKey
                                    } else if (nextKey == null || MapKey(nextKey!!) != MapKey(currentKey!!)) {
                                        break
                                    }
                                    countA++
                                }
                                if (currentKey == null) {
                                    done = true
                                    __close()
                                } else {
                                    key = MapKey(currentKey!!)
                                    val others = mutableListOf<Pair<MapKey, MapRow>>()
// search for_join-partners
                                    if (map == mapWithoutUndef) {
                                        val tmp2 = mapWithoutUndef[key]
                                        if (tmp2 != null) {
                                            others.add(Pair(key, tmp2))
                                        }
                                        for ((k, v) in mapWithUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                    } else {
                                        for ((k, v) in mapWithoutUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                        for ((k, v) in mapWithUndef) {
                                            if (k.equalsFuzzy(key)) {
                                                others.add(Pair(k, v))
                                            }
                                        }
                                    }
                                    val dataOA = Array(columnsINAO.size) { mutableListOf<Int>() }
                                    for (columnIndex in 0 until columnsINAO.size) {
                                        for (i in 0 until countA) {
                                            val tmp2 = columnsINAO[columnIndex].next()
                                            SanityCheck.check { tmp2 != ResultSetDictionaryExt.nullValue }
                                            dataOA[columnIndex].add(tmp2)
                                        }
                                    }
                                    if (others.size == 0) {
                                        if (optional) {
// optional clause without match
                                            done = true
                                            countB = 1
                                            val dataJ = IntArray(outJ.size) { currentKey!![it] }
                                            POPJoin.crossProduct(dataOA, Array(outO[1].size) { mutableListOf(ResultSetDictionaryExt.undefValue) }, dataJ, outO[0], outO[1], outJ, countA, countB)
                                        }
                                    } else {
                                        done = true
                                        for (otherIndex in 0 until others.size) {
                                            countB = others[otherIndex].second.count
                                            val dataJ = IntArray(outJ.size) {
                                                val res2: Int = if (currentKey!![it] != ResultSetDictionaryExt.undefValue) {
                                                    currentKey!![it]
                                                } else {
                                                    others[otherIndex].first.data[it]
                                                }
                                                res2
                                            }
                                            POPJoin.crossProduct(dataOA, others[otherIndex].second.columns, dataJ, outO[0], outO[1], outJ, countA, countB)
                                        }
                                    }
                                    map = nextMap
                                    currentKey = nextKey
                                }
                            }
                        },
                        { __close() }
                    )
                }
            }
            outIteratorsAllocated.add(iterator)
            when (second) {
                0 -> {
                    outMap[first] = iterator
                    outJ.add(iterator)
                }
                1 -> {
                    outMap[first] = iterator
                    outO[0].add(iterator)
                }
                2 -> {
                    outMap[first] = iterator
                    outO[1].add(iterator)
                }
                3 -> {
                    outJ.add(iterator)
                }
            }
        }
        res = if (outMap.isNotEmpty()) {
            IteratorBundle(outMap)
        } else {
            IteratorBundle(0)
        }
        if (emptyColumnsWithJoin) {
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return outJ[0].next() != ResultSetDictionaryExt.nullValue
                }
                override /*suspend*/ fun hasNext2Close() {
                    outJ[0].close()
                    for (closeIndex in 0 until columnsINAJ.size) {
                        columnsINAJ[closeIndex].close()
                    }
                    for (closeIndex in 0 until columnsINAO.size) {
                        columnsINAO[closeIndex].close()
                    }
                }
            }
        }
        return res
    }
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinHashMap(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
