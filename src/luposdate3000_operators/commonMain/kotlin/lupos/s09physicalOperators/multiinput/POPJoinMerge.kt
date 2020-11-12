package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
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
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMerge(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun getPartitionCount(variable: String): Int {
        if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                return children[0].getPartitionCount(variable)
            } else {
                return children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                return children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable $variable")
            }
        }
    }

    override fun toSparql() = children[0].toSparql() + children[1].toSparql()
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
    override fun equals(other: Any?) = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    internal class IteratorBundleImpl(@JvmField val columnsINJ0: List<ColumnIterator>, @JvmField val columnsINJ1: List<ColumnIterator>, @JvmField val columnsOUTJ: ColumnIteratorChildIterator) : IteratorBundle(0) {
        override suspend fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != ResultSetDictionaryExt.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }

        @JvmField
        var local_hasNext2Close_i = 0
        suspend inline fun _hasNext2Close() {
            local_hasNext2Close_i = 0
            while (local_hasNext2Close_i < columnsINJ0.size) {
                columnsINJ0[local_hasNext2Close_i].close()
                local_hasNext2Close_i++
            }
            local_hasNext2Close_i = 0
            while (local_hasNext2Close_i < columnsINJ1.size) {
                columnsINJ1[local_hasNext2Close_i].close()
                local_hasNext2Close_i++
            }
        }

        override suspend fun hasNext2Close() {
            _hasNext2Close()
        }
    }

    internal class ColumnIteratorChildIteratorImpl(
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

        @JvmField
        var local___close_i = 0

        @JvmField
        var skipO0 = 0

        @JvmField
        var skipO1 = 0

        @JvmField
        var sipbuf = IntArray(2)
        internal suspend inline fun __close() {
            if (label != 0) {
                local___close_i = 0
                while (local___close_i < columnsOUT0.size) {
                    columnsOUT0[local___close_i].closeOnNoMoreElements()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsOUT1.size) {
                    columnsOUT1[local___close_i].closeOnNoMoreElements()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsOUTJ.size) {
                    columnsOUTJ[local___close_i].closeOnNoMoreElements()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsINJ0.size) {
                    columnsINJ0[local___close_i].close()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsINJ1.size) {
                    columnsINJ1[local___close_i].close()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsINO0.size) {
                    columnsINO0[local___close_i].close()
                    local___close_i++
                }
                local___close_i = 0
                while (local___close_i < columnsINO1.size) {
                    columnsINO1[local___close_i].close()
                    local___close_i++
                }
                _close()
            }
        }

        override suspend fun close() {
            __close()
        }

        override suspend fun next(): Int {
            return next_helper({
                if (key0[0] != ResultSetDictionaryExt.nullValue && key1[0] != ResultSetDictionaryExt.nullValue) {
                    loop@ while (true) {
                        SanityCheck.check { columnsINJ0.size > 0 }
//first join column
                        if (key0[0] != key1[0]) {
                            var skip0 = 0
                            var skip1 = 0
                            while (key0[0] != key1[0]) {
                                if (key0[0] < key1[0]) {
                                    columnsINJ0[0].nextSIP(key1[0], sipbuf)
                                    key0[0] = sipbuf[1]
                                    skip0 += sipbuf[0]
                                    skipO0 += sipbuf[0]
                                    skip0++
                                    skipO0++
                                    SanityCheck.check { key0[0] != ResultSetDictionaryExt.undefValue }
                                    if (key0[0] == ResultSetDictionaryExt.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                } else {
                                    columnsINJ1[0].nextSIP(key0[0], sipbuf)
                                    key1[0] = sipbuf[1]
                                    skip1 += sipbuf[0]
                                    skipO1 += sipbuf[0]
                                    skip1++
                                    skipO1++
                                    SanityCheck.check { key1[0] != ResultSetDictionaryExt.undefValue }
                                    if (key1[0] == ResultSetDictionaryExt.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                }
                            }
                            if (skip0 > 0) {
                                local_next_j = 1
                                while (local_next_j < columnsINJ0.size) {
                                    key0[local_next_j] = columnsINJ0[local_next_j].skipSIP(skip0)
                                    SanityCheck.check { key0[local_next_j] != ResultSetDictionaryExt.undefValue }
                                    SanityCheck.check { key0[local_next_j] != ResultSetDictionaryExt.nullValue }
                                    local_next_j++
                                }
                            }
                            if (skip1 > 0) {
                                local_next_j = 1
                                while (local_next_j < columnsINJ1.size) {
                                    key1[local_next_j] = columnsINJ1[local_next_j].skipSIP(skip1)
                                    SanityCheck.check { key1[local_next_j] != ResultSetDictionaryExt.undefValue }
                                    SanityCheck.check { key1[local_next_j] != ResultSetDictionaryExt.nullValue }
                                    local_next_j++
                                }
                            }
                        }
//other join columns
                        local_next_i = 1
                        while (local_next_i < columnsINJ0.size) {
                            if (key0[local_next_i] < key1[local_next_i]) {
                                skipO0++
                                local_next_j = 0
                                while (local_next_j < columnsINJ0.size) {
                                    key0[local_next_j] = columnsINJ0[local_next_j].next()
                                    SanityCheck.check { key0[local_next_j] != ResultSetDictionaryExt.undefValue }
                                    if (key0[local_next_j] == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.check { local_next_j == 0 }
                                        __close()
                                        break@loop
                                    }
                                    local_next_j++
                                }
                                continue@loop
                            } else if (key0[local_next_i] > key1[local_next_i]) {
                                skipO1++
                                local_next_j = 0
                                while (local_next_j < columnsINJ1.size) {
                                    key1[local_next_j] = columnsINJ1[local_next_j].next()
                                    SanityCheck.check { key1[local_next_j] != ResultSetDictionaryExt.undefValue }
                                    if (key1[local_next_j] == ResultSetDictionaryExt.nullValue) {
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
//safe the join columns
                        local_next_i = 0
                        while (local_next_i < columnsINJ0.size) {
                            local_next_keyCopy[local_next_i] = key0[local_next_i]
                            local_next_i++
                        }
//the only-A columns
                        local_next_countA = 0
                        loop2@ while (true) {
                            if (columnsINO0.size > 0) {
                                if (local_next_countA >= data0[0].size) {
                                    local_next_i = 0
                                    while (local_next_i < data0.size) {
                                        val x = data0[local_next_i]
                                        val d = IntArray(local_next_countA * 2)
                                        local_next_j = 0
                                        while (local_next_j < local_next_countA) {
                                            d[local_next_j] = x[local_next_j]
                                            local_next_j++
                                        }
                                        data0[local_next_i] = d
                                        local_next_i++
                                    }
                                }
                                local_next_i = 0
                                while (local_next_i < columnsINO0.size) {
                                    data0[local_next_i][local_next_countA] = columnsINO0[local_next_i].skipSIP(skipO0)
                                    local_next_i++
                                }
                                skipO0 = 0
                            }
                            local_next_countA++
                            local_next_i = 0
                            while (local_next_i < columnsINJ0.size) {
                                key0[local_next_i] = columnsINJ0[local_next_i].next()
                                SanityCheck.check { key0[local_next_i] != ResultSetDictionaryExt.undefValue }
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
//the only-B columns
                        local_next_countB = 0
                        loop2@ while (true) {
                            if (columnsINO1.size > 0) {
                                if (local_next_countB >= data1[0].size) {
                                    local_next_i = 0
                                    while (local_next_i < data1.size) {
                                        val x = data1[local_next_i]
                                        val d = IntArray(local_next_countB * 2)
                                        local_next_j = 0
                                        while (local_next_j < local_next_countB) {
                                            d[local_next_j] = x[local_next_j]
                                            local_next_j++
                                        }
                                        data1[local_next_i] = d
                                        local_next_i++
                                    }
                                }
                                local_next_i = 0
                                while (local_next_i < columnsINO1.size) {
                                    data1[local_next_i][local_next_countB] = columnsINO1[local_next_i].skipSIP(skipO1)
                                    local_next_i++
                                }
                                skipO1 = 0
                            }
                            local_next_countB++
                            local_next_i = 0
                            while (local_next_i < columnsINJ1.size) {
                                key1[local_next_i] = columnsINJ1[local_next_i].next()
                                SanityCheck.check { key1[local_next_i] != ResultSetDictionaryExt.undefValue }
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
            }, { __close() })
        }
    }

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        //setup columns
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
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
        for (i in 0 until columnsINJ0.size) {
            key0[i] = columnsINJ0[i].next()
        }
        for (i in 0 until columnsINJ1.size) {
            key1[i] = columnsINJ1[i].next()
        }
        return res
    }
}
