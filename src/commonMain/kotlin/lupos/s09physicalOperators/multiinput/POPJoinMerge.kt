package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
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
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql() = children[0].toSparql() + children[1].toSparql()
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
    override fun equals(other: Any?) = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    class IteratorBundleImpl(@JvmField val columnsINJ0: List<ColumnIterator>, @JvmField val columnsINJ1: List<ColumnIterator>, @JvmField val columnsOUTJ: ColumnIteratorChildIterator) : IteratorBundle(0) {
        override suspend fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != ResultSetDictionary.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }

        suspend inline fun _hasNext2Close() {
            for (it in columnsINJ0) {
                it.close()
            }
            for (it in columnsINJ1) {
                it.close()
            }
        }

        suspend override fun hasNext2Close() {
            _hasNext2Close()
        }
    }

    class ColumnIteratorChildIteratorImpl(
            @JvmField val columnsINJ0: List<ColumnIterator>,
            @JvmField val columnsINJ1: List<ColumnIterator>,
            @JvmField val columnsINO0: List<ColumnIterator>,
            @JvmField val columnsINO1: List<ColumnIterator>,
            @JvmField val columnsOUT0: List<ColumnIteratorChildIterator>,
            @JvmField val columnsOUT1: List<ColumnIteratorChildIterator>,
            @JvmField val columnsOUTJ: List<ColumnIteratorChildIterator>,
            @JvmField val key0: IntArray,
            @JvmField val key1: IntArray
    ) : ColumnIteratorChildIterator() {
        @JvmField
        val data0 = Array(columnsINO0.size) { IntArray(100) }

        @JvmField
        val data1 = Array(columnsINO1.size) { IntArray(100) }

        @JvmField
        var local_next_i = 0

        @JvmField
        var local_next_j = 0

        @JvmField
        var local_next_countA = 0

        @JvmField
        var local_next_countB = 0

        @JvmField
        val local_next_keyCopy = IntArray(columnsINJ0.size)

        suspend inline fun __close() {
            if (label != 0) {
                for (it in columnsOUT0) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsOUT1) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsOUTJ) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsINJ0) {
                    it.close()
                }
                for (it in columnsINJ1) {
                    it.close()
                }
                for (it in columnsINO0) {
                    it.close()
                }
                for (it in columnsINO1) {
                    it.close()
                }
                _close()
            }
        }

        override suspend fun close() {
            __close()
        }

        override suspend fun next(): Value {
            return next_helper {
                if (key0[0] != ResultSetDictionary.nullValue && key1[0] != ResultSetDictionary.nullValue) {
                    loop@ while (true) {
                        local_next_i = 0
                        while (local_next_i < columnsINJ0.size) {
                            if (key0[local_next_i] < key1[local_next_i]) {
                                local_next_j = 0
                                while (local_next_j < columnsINO0.size) {
                                    columnsINO0[local_next_j].next()
                                    local_next_j++
                                }
                                local_next_j = 0
                                while (local_next_j < columnsINJ0.size) {
                                    key0[local_next_j] = columnsINJ0[local_next_j].next()
                                    SanityCheck.check { key0[local_next_j] != ResultSetDictionary.undefValue }
                                    if (key0[local_next_j] == ResultSetDictionary.nullValue) {
                                        SanityCheck.check { local_next_j == 0 }
                                        __close()
                                        break@loop
                                    }
                                    local_next_j++
                                }
                                continue@loop
                            } else if (key0[local_next_i] > key1[local_next_i]) {
                                local_next_j = 0
                                while (local_next_j < columnsINO1.size) {
                                    columnsINO1[local_next_j].next()
                                    local_next_j++
                                }
                                local_next_j = 0
                                while (local_next_j < columnsINJ1.size) {
                                    key1[local_next_j] = columnsINJ1[local_next_j].next()
                                    SanityCheck.check { key1[local_next_j] != ResultSetDictionary.undefValue }
                                    if (key1[local_next_j] == ResultSetDictionary.nullValue) {
                                        SanityCheck.check { local_next_j == 0 }
                                        __close()
                                        break@loop
                                    }
                                    local_next_j++
                                }
                                continue@loop
                            }
                            local_next_i++
                        }
                        local_next_i = 0
                        while (local_next_i < columnsINJ0.size) {
                            local_next_keyCopy[local_next_i] = key0[local_next_i]
                            local_next_i++
                        }
                        loop2@ while (true) {
                            if (columnsINO0.size > 0) {
                                if (local_next_countA >= data0[0].size) {
                                    local_next_i = 0
                                    while (local_next_i < data0.size) {
                                        val x = data0[local_next_i]
                                        val d = IntArray(local_next_countA * 2)
                                        for (i in 0 until local_next_countA) {
                                            d[local_next_i] = x[local_next_i]
                                        }
                                        data0[local_next_i] = d
                                        local_next_i++
                                    }
                                }
                                local_next_i = 0
                                while (local_next_i < columnsINO0.size) {
                                    data0[local_next_i][local_next_countA] = columnsINO0[local_next_i].next()
                                    local_next_i++
                                }
                            }
                            local_next_countA++
                            local_next_i = 0
                            while (local_next_i < columnsINJ0.size) {
                                key0[local_next_i] = columnsINJ0[local_next_i].next()
                                SanityCheck.check { key0[local_next_i] != ResultSetDictionary.undefValue }
                                local_next_i++
                            }
                            local_next_i = 0
                            while (local_next_i < columnsINJ0.size) {
                                if (key0[local_next_i] != local_next_keyCopy[local_next_i]) {
                                    break@loop2
                                }
                                local_next_i++
                            }
                        }
                        loop2@ while (true) {
                            if (columnsINO1.size > 0) {
                                if (local_next_countB >= data1[0].size) {
                                    local_next_i = 0
                                    while (local_next_i < data1.size) {
                                        val x = data1[local_next_i]
                                        val d = IntArray(local_next_countB * 2)
                                        for (i in 0 until local_next_countB) {
                                            d[local_next_i] = x[local_next_i]
                                        }
                                        data1[local_next_i] = d
                                        local_next_i++
                                    }
                                }
                                local_next_i = 0
                                while (local_next_i < columnsINO1.size) {
                                    data1[local_next_i][local_next_countB] = columnsINO1[local_next_i].next()
                                    local_next_i++
                                }
                            }
                            local_next_countB++
                            local_next_i = 0
                            while (local_next_i < columnsINJ1.size) {
                                key1[local_next_i] = columnsINJ1[local_next_i].next()
                                SanityCheck.check { key1[local_next_i] != ResultSetDictionary.undefValue }
                                local_next_i++
                            }
                            local_next_i = 0
                            while (local_next_i < columnsINJ1.size) {
                                if (key1[local_next_i] != local_next_keyCopy[local_next_i]) {
                                    break@loop2
                                }
                                local_next_i++
                            }
                        }
                        POPJoin.crossProduct(data0, data1, local_next_keyCopy, columnsOUT0, columnsOUT1, columnsOUTJ, local_next_countA, local_next_countB)
                        break@loop
                    }
                } else {
                    __close()
                }
            }
        }
    }

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        //setup columns
        SanityCheck.println({ "$uuid open $classname" })
        val child0 = children[0].evaluate(parent)
        val child1 = children[1].evaluate(parent)
        val columnsINO0 = mutableListOf<ColumnIterator>()
        val columnsINO1 = mutableListOf<ColumnIterator>()
        val columnsINJ0 = mutableListOf<ColumnIterator>()
        val columnsINJ1 = mutableListOf<ColumnIterator>()
        val columnsOUT0 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUT1 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() //Key_in_outMap, which_outIteratorsCounter (J,O0,O1,none)
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    columnsINJ0.add(0, child0.columns[name]!!)
                    columnsINJ1.add(0, child1.columns[name]!!)
                } else {
                    columnsINJ0.add(child0.columns[name]!!)
                    columnsINJ1.add(child1.columns[name]!!)
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINO0.add(child0.columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO1.add(child1.columns[name]!!)
        }
        SanityCheck.check { columnsINJ0.size > 0 }
        SanityCheck.check { columnsINJ0.size == columnsINJ1.size }
        val emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key0 = IntArray(columnsINJ0.size)
        val key1 = IntArray(columnsINJ1.size)
        for (iteratorConfig in outIterators) {
            val iterator = ColumnIteratorChildIteratorImpl(columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1)
            when (iteratorConfig.second) {
                0 -> {
                    outMap[iteratorConfig.first] = iterator
                    columnsOUTJ.add(iterator)
                }
                1 -> {
                    outMap[iteratorConfig.first] = iterator
                    columnsOUT0.add(iterator)
                }
                2 -> {
                    outMap[iteratorConfig.first] = iterator
                    columnsOUT1.add(iterator)
                }
                3 -> {
                    columnsOUTJ.add(iterator)
                }
            }
        }
        val res: IteratorBundle
        if (emptyColumnsWithJoin) {
            res = IteratorBundleImpl(columnsINJ0, columnsINJ1, columnsOUTJ[0])
            for (it in columnsINO0) {
                it.close()
            }
            for (it in columnsINO1) {
                it.close()
            }
        } else {
            res = IteratorBundle(outMap)
        }

for(i in 0 until columnsINJ0.size){
key0[i]=columnsINJ0[i].next()
}
for(i in 0 until columnsINJ1.size){
key1[i]=columnsINJ1[i].next()
}
        return res
    }

}
