package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.POPBase

class POPJoinHashMap(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", arrayOf(childA, childB)) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinHashMap) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size) {
                res += data[i].hashCode()
            }
            return res
        }

        override fun equals(other: Any?): Boolean {
            require(other is MapKey)
            for (i in 0 until data.size) {
                if (data[i] != other.data[i]) {
                    return false
                }
            }
            return true
        }

        fun equalsFuzzy(other: Any?): Boolean {
            require(other is MapKey)
            for (i in 0 until data.size) {
                if (data[i] != ResultSetDictionary.undefValue && other.data[i] != ResultSetDictionary.undefValue && data[i] != other.data[i]) {
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
        var tmpCounterINAO = 0
        var tmpCounterINAJ = 0
        var tmpCounterINBO = 0
        var tmpCounterINBJ = 0
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
        val columnsINAJ = mutableListOf<ColumnIterator>()//join columnA
        val columnsINBJ = mutableListOf<ColumnIterator>()//join columnB
        val columnsOUTA = mutableListOf<ColumnIteratorChildIterator>()//only in childA
        val columnsOUTB = mutableListOf<ColumnIteratorChildIterator>()//only in childB
        val columnsOUTJLocal = mutableListOf<ColumnIteratorChildIterator>()//join column
        val columnsOUTJ = mutableListOf<Int>()//join column-map
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: ColumnIteratorRow?
        val tmp = mutableListOf<String>()
        var t: ColumnIteratorChildIterator
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                columnsINAJ.add(childA.columns[name]!!)
                columnsINBJ.add(childB.columns[name]!!)
                t = ColumnIteratorChildIterator()
                if (projectedVariables.contains(name)) {
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
                    outIterators.add(t)
                    columnsOUTJ.add(columnsOUTJLocal.size)
                }
                columnsOUTJLocal.add(t)
                tmp.remove(name)
            } else {
                t = ColumnIteratorChildIterator()
                outIterators.add(t)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
                columnsOUTA.add(t)
                columnsINAO.add(childA.columns[name]!!)
            }
        }
        for (name in tmp) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
            columnsOUTB.add(t)
            columnsINBO.add(childB.columns[name]!!)
        }
        var emptyColumnsWithJoin = columnsOUTA.size == 0 && columnsOUTB.size == 0 && columnsOUTJ.size == 0 && columnsOUTJLocal.size != 0
        if (emptyColumnsWithJoin) {
            columnsOUTJ.add(0)
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
//---check for_ empty columns
        if (columnsOUTJLocal.size == 0) {
            if (columnsINAO.size == 0 && columnsINBO.size == 0) {
                res = ColumnIteratorRow(outMap)
                res.count = childA.count * childB.count
            } else {
                if (columnsINAO.size == 0) {
//---no columns from a
                    if (childA.count > 0) {
                        for (columnIndex in 0 until columnsINBO.size) {
                            columnsOUTB[columnIndex].childs.add(ColumnIteratorRepeatIterator(childA.count, columnsINBO[columnIndex]))
                        }
                    }
                } else if (columnsINBO.size == 0) {
//---no columns from b
                    if (childB.count > 0) {
                        for (columnIndex in 0 until columnsINAO.size) {
                            columnsOUTA[columnIndex].childs.add(ColumnIteratorRepeatIterator(childB.count, columnsINAO[columnIndex]))
                        }
                    }
                } else {
//---cartesian product
//---insert second child into simple list
                    val data = Array(columnsINBO.size) { mutableListOf<Value>() }
                    loopC@ while (true) {
                        for (columnIndex in 0 until columnsINBO.size) {
                            tmpCounterINBO++
                            val value = columnsINBO[columnIndex].next()
                            if (value == null) {
                                break@loopC
                            }
                            data[columnIndex].add(value)
                        }
                    }
                    count = data[0].size
                    if (count > 0 || optional) {
                        for (iterator in outIterators) {
//this is just function pointer assignment. this loop does not calculate anything
                            iterator.close = {
                                iterator._close()
                                for (variable in children[0].getProvidedVariableNames()) {
                                    childA.columns[variable]!!.close()
                                }
                                for (variable in children[1].getProvidedVariableNames()) {
                                    childB.columns[variable]!!.close()
                                }
                            }
                            iterator.onNoMoreElements = {
                                var done = false
                                for (columnIndex in 0 until columnsINAO.size) {
                                    tmpCounterINAO++
                                    val value = columnsINAO[columnIndex].next()
                                    if (value == null) {
                                        require(columnIndex == 0)
                                        done = true
                                        break
                                    }
                                    columnsOUTA[columnIndex].childs.add(ColumnIteratorRepeatValue(count, value))
                                }
                                if (!done) {
                                    for (columnIndex in 0 until columnsINBO.size) {
                                        columnsOUTB[columnIndex].childs.add(ColumnIteratorMultiValue(data[columnIndex]))
                                    }
                                }
                            }
                        }
                    }
                }
                res = ColumnIteratorRow(outMap)
            }
        } else {
            //---join on at least one column
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
                        tmpCounterINBJ++
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
//xxx here nullpointer
                        tmpCounterINBO++
                        oldArr.columns[columnIndex].add(columnsINBO[columnIndex].next()!!)
                    }
                }
                currentKey = nextKey
                map = nextMap
            }
//--- iterate first child
//--- calculate next "cartesian product"
            for (iterator in outIterators) {
//this is just function pointer assignment. this loop does not calculate anything
                iterator.close = {
                    iterator._close()
                    for (variable in children[0].getProvidedVariableNames()) {
                        childA.columns[variable]!!.close()
                    }
                    for (variable in children[1].getProvidedVariableNames()) {
                        childB.columns[variable]!!.close()
                    }
                }
                iterator.onNoMoreElements = {
                    if (currentKey == null) {
                        countA = 0
                    } else {
                        countA = 1
                    }
                    loopA@ while (true) {
                        nextKey = Array(columnsINAJ.size) { ResultSetDictionary.undefValue }
                        nextMap = mapWithoutUndef
                        for (columnIndex in 0 until columnsINAJ.size) {
                            tmpCounterINAJ++
                            val value = columnsINAJ[columnIndex].next()
                            if (value == null) {
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
                        iterator.close()
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
                        if (others.size == 0) {
                            if (optional) {
//optional clause without match
                                for (columnIndex in 0 until columnsINAO.size) {
                                    val list = mutableListOf<Value>()
                                    for (i in 0 until countA) {
                                        tmpCounterINAO++
                                        list.add(columnsINAO[columnIndex].next()!!)
                                    }
                                    columnsOUTA[columnIndex].childs.add(ColumnIteratorMultiValue(list))
                                }
                                for (columnIndex in 0 until columnsINBO.size) {
                                    columnsOUTB[columnIndex].childs.add(ColumnIteratorRepeatValue(countA, ResultSetDictionary.undefValue))
                                }
                                for (columnIndex in columnsOUTJ) {
                                    columnsOUTJLocal[columnIndex].childs.add(ColumnIteratorRepeatValue(countA, currentKey!![columnIndex]))
                                }
                            }
                        } else {
                            val cacheIterators = Array(columnsINAO.size) { mutableListOf<Value>() }
                            for (columnIndex in 0 until columnsINAO.size) {
                                for (i in 0 until countA) {
//xxx here nullpointer
                                    tmpCounterINAO++
                                    cacheIterators[columnIndex].add(columnsINAO[columnIndex].next()!!)
                                }
                            }
                            for (otherIndex in 0 until others.size) {
//for_ each match - may contain undefined in the join-columns
                                countB = others[otherIndex].second.count
                                count = countA * countB
                                for (columnIndex in 0 until columnsINAO.size) {
                                    val iterators = mutableListOf<ColumnIterator>()
                                    for (i in 0 until countA) {
                                        iterators.add(ColumnIteratorRepeatValue(countB, cacheIterators[columnIndex][i]))
                                    }
                                    if (iterators.size == 1) {
                                        columnsOUTA[columnIndex].childs.add(iterators[0])
                                    } else {
                                        columnsOUTA[columnIndex].childs.add(ColumnIteratorMultiIterator(iterators))
                                    }
                                }
                                for (columnIndex in 0 until columnsINBO.size) {
                                    if (countA == 1) {
                                        columnsOUTB[columnIndex].childs.add(ColumnIteratorMultiValue(others[otherIndex].second.columns[columnIndex]))
                                    } else {
                                        columnsOUTB[columnIndex].childs.add(ColumnIteratorRepeatIterator(countA, ColumnIteratorMultiValue(others[otherIndex].second.columns[columnIndex])))
                                    }
                                }
//resolve undefined values in join columns
                                for (columnIndex in columnsOUTJ) {
                                    if (currentKey!![columnIndex] != ResultSetDictionary.undefValue) {
                                        columnsOUTJLocal[columnIndex].childs.add(ColumnIteratorRepeatValue(count, currentKey!![columnIndex]))
                                    } else {
                                        columnsOUTJLocal[columnIndex].childs.add(ColumnIteratorRepeatValue(count, others[otherIndex].first.data[columnIndex]))
                                    }
                                }
                            }
                        }
                        map = nextMap
                        currentKey = nextKey
                    }
                }
            }
            res = ColumnIteratorRow(outMap)
            if (emptyColumnsWithJoin) {
                res.hasNext = {
                    /*return*/columnsOUTJLocal[0].next() != null
                }
            }
        }
        return res
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinHashMap(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
