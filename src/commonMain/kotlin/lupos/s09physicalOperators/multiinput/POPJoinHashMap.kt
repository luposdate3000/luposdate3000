package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.BugException
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinHashMap(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinHashMap && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size) {
                res += data[i].hashCode()
            }
            return res
        }

        override fun equals(other: Any?) = other is MapKey && data.contentEquals(other.data)
        fun equalsFuzzy(other: Any?): Boolean {
            SanityCheck.check { other is MapKey }
            for (i in 0 until data.size) {
                if (data[i] != ResultSetDictionary.undefValue && (other as MapKey).data[i] != ResultSetDictionary.undefValue && data[i] != other.data[i]) {
                    return false
                }
            }
            return true
        }
    }

    class MapRow(columns: Int) {
        val columns = Array(columns) { MyListValue() }
        var count = 0
    }

    override fun evaluate(parent: Partition): IteratorBundle {
//--- obtain child columns
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        require(columns[0].size != 0)
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
        val columnsINAJ = mutableListOf<ColumnIterator>()//join columnA
        val columnsINBJ = mutableListOf<ColumnIterator>()//join columnB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }//only in one of the childs
        val outJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>()//J,O0,O1,J-but-not-outmap
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: IteratorBundle?
        val tmp = mutableListOf<String>()
        var t: ColumnIteratorChildIterator
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
        var emptyColumnsWithJoin = outIterators.size == 0 && columnsINAJ.size != 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 4))
        }
        val mapWithoutUndef = mutableMapOf<MapKey, MapRow>()
        val mapWithUndef = mutableMapOf<MapKey, MapRow>()
        var currentKey: Array<Value>? = null
        var nextKey: Array<Value>?
        var map: MutableMap<MapKey, MapRow> = mapWithUndef
        var nextMap: MutableMap<MapKey, MapRow>
        var key: MapKey
        var oldArr: MapRow?
        var count: Int
        var countA: Int
        var countB: Int
        require(columnsINAJ.size > 0)
//--- insert second child into hash table
        while (true) {
            if (currentKey != null) {
                count = 1
            } else {
                count = 0
            }
            loopB@ while (true) {
                nextKey = Array(columnsINBJ.size) { ResultSetDictionary.undefValue }
                nextMap = mapWithoutUndef
                for (columnIndex in 0 until columnsINBJ.size) {
                    val value = columnsINBJ[columnIndex].next()
                    if (value == null) {
                        nextKey = null
                        break@loopB
                    }
                    nextKey!![columnIndex] = value
                    if (value == ResultSetDictionary.undefValue) {
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
//TODO dont use kotlin lists here, use pages instead
                for (j in 0 until count) {
                    oldArr.columns[columnIndex].add(columnsINBO[columnIndex].next()!!)
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
//--- iterate first child
//--- calculate next "cartesian product"
        for (iteratorConfig in outIterators) {
            val iterator = object : ColumnIteratorChildIterator() {
@JvmField val outIteratorsAllocated0=outIteratorsAllocated
@JvmField val columnsINAJ0=columnsINAJ
@JvmField val columnsINAO0=columnsINAO
override fun close(){
__close()
}
                inline fun __close() {
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

                override fun next():Value? {
return next_helper{
                    var done = false
                    while (!done) {
                        if (currentKey == null) {
                            countA = 0
                        } else {
                            countA = 1
                        }
                        loopA@ while (true) {
                            nextKey = Array(columnsINAJ.size) { ResultSetDictionary.undefValue }
                            nextMap = mapWithoutUndef
                            for (columnIndex in 0 until columnsINAJ.size) {
                                val value = columnsINAJ[columnIndex].next()
                                if (value == null) {
                                    SanityCheck.check { columnIndex == 0 }
                                    nextKey = null
                                    break@loopA
                                }
                                nextKey!![columnIndex] = value
                                if (value == ResultSetDictionary.undefValue) {
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
                            var others = mutableListOf<Pair<MapKey, MapRow>>()
//search for_join-partners
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
                            val dataOA = Array(columnsINAO.size) { MyListValue() }
                            for (columnIndex in 0 until columnsINAO.size) {
                                for (i in 0 until countA) {
                                    var tmp = columnsINAO[columnIndex].next()
                                    SanityCheck.check { tmp != null }
                                    dataOA[columnIndex].add(tmp!!)
                                }
                            }
                            if (others.size == 0) {
                                if (optional) {
//optional clause without match
                                    done = true
                                    countB = 1
                                    val dataJ: Array<Value?> = Array(outJ.size) { currentKey!![it] }
                                    val dataO: Array<Array<MyListValue>> = arrayOf(dataOA, Array(outO[1].size) { MyListValue(ResultSetDictionary.undefValue) })
                                    POPJoin.crossProduct(dataO, dataJ, outO, outJ, countA, countB)
                                }
                            } else {
                                done = true
                                for (otherIndex in 0 until others.size) {
                                    countB = others[otherIndex].second.count
                                    val dataJ: Array<Value?> = Array(outJ.size) {
                                        var res2: Value?
                                        if (currentKey!![it] != ResultSetDictionary.undefValue) {
                                            res2 = currentKey!![it]
                                        } else {
                                            res2 = others[otherIndex].first.data[it]
                                        }
                                        /*return*/res2
                                    }
                                    val dataO: Array<Array<MyListValue>> = arrayOf(dataOA, others[otherIndex].second.columns)
                                    POPJoin.crossProduct(dataO, dataJ, outO, outJ, countA, countB)
                                }
                            }
                            map = nextMap
                            currentKey = nextKey
                        }
                    }
                }
}
            }
            outIteratorsAllocated.add(iterator)
            when (iteratorConfig.second) {
                0 -> {
                    outMap[iteratorConfig.first] = iterator
                    outJ.add(iterator)
                }
                1 -> {
                    outMap[iteratorConfig.first] = iterator
                    outO[0].add(iterator)
                }
                2 -> {
                    outMap[iteratorConfig.first] = iterator
                    outO[1].add(iterator)
                }
                3 -> {
                    outJ.add(iterator)
                }
            }
        }
        if (outMap.size > 0) {
            res = IteratorBundle(outMap)
        } else {
            res = IteratorBundle(0)
        }
        if (emptyColumnsWithJoin) {
            res.hasNext2 = {
                /*return*/outJ[0].next() != null
            }
            res.hasNext2Close = {
                outJ[0].close()
                for (closeIndex in 0 until columnsINAJ.size) {
                    columnsINAJ[closeIndex].close()
                }
                for (closeIndex in 0 until columnsINAO.size) {
                    columnsINAO[closeIndex].close()
                }
            }
        }
        return res
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinHashMap(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
