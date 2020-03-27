package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID


import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*



import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

class POPJoinHashMap(query: Query, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", ResultSet(query.dictionary), arrayOf(childA, childB)) {
    override fun toSparql(): String {
        if (optional)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinHashMap)
            return false
        if (optional != other.optional)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size)
                res += data[i].hashCode()
            return res
        }

        override fun equals(other: Any?): Boolean {
            for (i in 0 until data.size) {
                if (data[i] != (other as MapKey).data[i]) {
                    return false
                }
            }
            return true
        }

        fun equalsFuzzy(other: Any?): Boolean {
            for (i in 0 until data.size) {
                if (data[i] != ResultSetDictionary.undefValue && (other as MapKey).data[i] != ResultSetDictionary.undefValue && data[i] != (other as MapKey).data[i]) {
                    return false
                }
            }
            return true
        }
    }

    class MapRow(columns: Int) {
        val columns = Array(columns) { mutableListOf<Value>() }
        var count = 0
    }

    override suspend fun evaluate(): ColumnIteratorRow {
//--- obtain child columns
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
        val columnsINAJ = mutableListOf<ColumnIterator>()//join columnA
        val columnsINBJ = mutableListOf<ColumnIterator>()//join columnB
        val columnsOUTA = mutableListOf<ColumnIteratorChildIterator>()//only in childA
        val columnsOUTB = mutableListOf<ColumnIteratorChildIterator>()//only in childB
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()//join column
        val outMap = mutableMapOf<String, ColumnIteratorChildIterator>()
        val tmp = mutableListOf<String>()
        var t: ColumnIterator
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                columnsINAJ.add(childA.columns[name]!!)
                columnsINBJ.add(childB.columns[name]!!)
                t = ColumnIteratorChildIterator(ColumnIterator())
                outMap[name] = t
                columnsOUTJ.add(t)
                tmp.remove(name)
            } else {
                t = ColumnIteratorChildIterator(ColumnIterator())
                outMap[name] = t
                columnsOUTA.add(t)
                columnsINAO.add(childA.columns[name]!!)
            }
        }
        for (name in tmp) {
            t = ColumnIteratorChildIterator(ColumnIterator())
            outMap[name] = t
            columnsOUTB.add(t)
            columnsINBO.add(childB.columns[name]!!)
        }
//--- insert second child into hash table
        val mapWithoutUndef = mutableMapOf<MapKey, MapRow>()
        val mapWithUndef = mutableMapOf<MapKey, MapRow>()
        var currentKey: Array<Value>? = null
        var nextKey: Array<Value>?
        var containsUndef: Boolean
        var map: MutableMap<MapKey, MapRow> = mapWithUndef
        var nextMap: MutableMap<MapKey, MapRow>
        var key: MapKey
        var oldArr: MapRow?
        var count: Int
        var countA: Int
        var countB: Int
        while (true) {
            count = 0
            loopB@ while (true) {
                nextKey = Array(columnsINBJ.size) { ResultSetDictionary.undefValue }
                nextMap = mapWithoutUndef
                for (columnIndex in 0 until columnsINBJ.size) {
                    val value = columnsINBJ[columnIndex].next()
                    if (value == null) {
                        nextKey = null
                        break@loopB
                    }
                    nextKey!![columnIndex] = value!!
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
                oldArr = MapRow(columnsOUTB.size)
                map[key] = oldArr
            }
            for (columnIndex in 0 until columnsOUTB.size) {
//TODO dont use kotlin lists here, use pages instead
                for (j in 0 until count)
                    oldArr!!.columns[columnIndex].add(columnsINBO[columnIndex].next()!!)
            }
            oldArr!!.count++
            currentKey = nextKey
            map = nextMap
        }
//--- iterate first child
//--- calculate next "cartesian product"
        for (iterator in outMap.values) {
//this is just function pointer assignment. this loop does not calculate anything
            iterator.close = {
                tmp._close()
                for (variable in childA.getProvidedVariableNames()) {
                    childA.columns[variable].close()
                    outMap[variable].close()
                }
                for (variable in childB.getProvidedVariableNames()) {
                    childB.columns[variable].close()
                    outMap[variable].close()
                }
            }
            iterator.next = {
                iterator.onNoMoreElements()
                iterator.next()
            }
            iterator.onNoMoreElements = {
                countA = 0
                loopA@ while (true) {
                    nextKey = Array(columnsINAJ.size) { ResultSetDictionary.undefValue }
                    nextMap = mapWithoutUndef
                    for (columnIndex in 0 until columnsINBJ.size) {
                        val value = columnsINBJ[columnIndex].next()
                        if (value == null) {
                            nextKey = null
                            break@loopA
                        }
                        nextKey!![columnIndex] = value!!
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
                    iterator.close()
                } else {
                    key = MapKey(currentKey!!)
                    oldArr = MapRow(columnsOUTA.size)
                    for (columnIndex in 0 until columnsOUTA.size) {
                        oldArr!!.columns[columnIndex].add(columnsINAO[columnIndex].next()!!)
                    }
                    var others = mutableListOf<Pair<MapKey, MapRow>>()
//search for join-partners
                    if (map == mapWithoutUndef) {
                        val tmp = mapWithoutUndef[key]
                        if (tmp != null) {
                            others.add(Pair(key, tmp))
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
                    if (others.size == 0) {
                        if (optional) {
//optional clause without match
                            for (columnIndex in 0 until columnsOUTA.size) {
                                val list = mutableListOf<Value>()
                                for (i in 0 until countA) {
                                    list.add(columnsINAO[columnIndex].next()!!)
                                }
                                columnsOUTA[columnIndex].child = ColumnIteratorMultiValue(list)
                            }
                            for (columnIndex in 0 until columnsOUTB.size) {
                                columnsOUTB[columnIndex].child = ColumnIteratorRepeatValue(countA, ResultSetDictionary.undefValue)
                            }
                            for (columnIndex in 0 until columnsOUTJ.size) {
                                columnsOUTJ[columnIndex].child = ColumnIteratorRepeatValue(countA, currentKey!![columnIndex])
                            }
                        }
                    } else {
                        for (otherIndex in 0 until others.size) {
//for each match - may contain undefined in the join-columns
                            countB = others[otherIndex].second.count
                            count = countA * countB
                            for (columnIndex in 0 until columnsOUTA.size) {
                                val iterators = mutableListOf<ColumnIterator>()
                                for (i in 0 until countA) {
                                    iterators.add(ColumnIteratorRepeatValue(countB, columnsINAO[columnIndex].next()!!))
                                }
                                if (iterators.size == 1) {
                                    columnsOUTA[columnIndex].child = iterators[0]
                                } else {
                                    columnsOUTA[columnIndex].child = ColumnIteratorMultiIterator(iterators)
                                }
                            }
                            for (columnIndex in 0 until columnsOUTB.size) {
                                if (countA == 1) {
                                    columnsOUTB[columnIndex].child = ColumnIteratorMultiValue(others[otherIndex].second.columns[columnIndex])
                                } else {
                                    columnsOUTB[columnIndex].child = ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(others[otherIndex].second.columns[columnIndex]))
                                }
                            }
//resolve undefined values in join columns
                            for (columnIndex in 0 until columnsOUTJ.size) {
                                if (currentKey!![columnIndex] == ResultSetDictionary.undefValue) {
                                    columnsOUTJ[columnIndex].child = ColumnIteratorRepeatValue(count, currentKey!![columnIndex])
                                } else {
                                    columnsOUTJ[columnIndex].child = ColumnIteratorRepeatValue(count, others[otherIndex].first.data[columnIndex])
                                }
                            }
                        }
                    }
                    map = nextMap
                    currentKey = nextKey
                }
            }
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinHashMap(query, children[0].cloneOP(), children[1].cloneOP(), optional)
}
