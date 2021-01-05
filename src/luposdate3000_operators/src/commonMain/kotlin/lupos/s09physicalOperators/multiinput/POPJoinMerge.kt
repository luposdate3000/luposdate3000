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
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
public class POPJoinMerge(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField public val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
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
    override fun toSparql(): String = children[0].toSparql() + children[1].toSparql()
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
    override fun equals(other: Any?): Boolean = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    internal class IteratorBundleImpl(@JvmField val columnsINJ0: List<ColumnIterator>, @JvmField val columnsINJ1: List<ColumnIterator>, @JvmField val columnsOUTJ: ColumnIteratorChildIterator) : IteratorBundle(0) {
        override /*suspend*/ fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != ResultSetDictionaryExt.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }
        @JvmField
        var localHasnext2closeI = 0
        /*suspend*/ private inline fun _hasNext2Close() {
            localHasnext2closeI = 0
            while (localHasnext2closeI < columnsINJ0.size) {
                columnsINJ0[localHasnext2closeI].close()
                localHasnext2closeI++
            }
            localHasnext2closeI = 0
            while (localHasnext2closeI < columnsINJ1.size) {
                columnsINJ1[localHasnext2closeI].close()
                localHasnext2closeI++
            }
        }
        override /*suspend*/ fun hasNext2Close() {
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
        var localNextI = 0
        @JvmField
        var localNextJ = 0
        @JvmField
        var localNextCounta = 0
        @JvmField
        var localNextCountb = 0
        @JvmField
        val localNextKeycopy = IntArray(columnsINJ0.size)
        @JvmField
        var localCloseI = 0
        @JvmField
        var skipO0 = 0
        @JvmField
        var skipO1 = 0
        @JvmField
        var sipbuf = IntArray(2)
        /*suspend*/ private inline fun __close() {
            if (label != 0) {
                localCloseI = 0
                while (localCloseI < columnsOUT0.size) {
                    columnsOUT0[localCloseI].closeOnNoMoreElements()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsOUT1.size) {
                    columnsOUT1[localCloseI].closeOnNoMoreElements()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsOUTJ.size) {
                    columnsOUTJ[localCloseI].closeOnNoMoreElements()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsINJ0.size) {
                    columnsINJ0[localCloseI].close()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsINJ1.size) {
                    columnsINJ1[localCloseI].close()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsINO0.size) {
                    columnsINO0[localCloseI].close()
                    localCloseI++
                }
                localCloseI = 0
                while (localCloseI < columnsINO1.size) {
                    columnsINO1[localCloseI].close()
                    localCloseI++
                }
                _close()
            }
        }
        override /*suspend*/ fun close() {
            __close()
        }
        override /*suspend*/ fun next(): Int {
            return nextHelper(
                {
                    if (key0[0] != ResultSetDictionaryExt.nullValue && key1[0] != ResultSetDictionaryExt.nullValue) {
                        loop@ while (true) {
                            SanityCheck.check { columnsINJ0.isNotEmpty() }
// first join column
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
                                    localNextJ = 1
                                    while (localNextJ < columnsINJ0.size) {
                                        key0[localNextJ] = columnsINJ0[localNextJ].skipSIP(skip0)
                                        SanityCheck.check { key0[localNextJ] != ResultSetDictionaryExt.undefValue }
                                        SanityCheck.check { key0[localNextJ] != ResultSetDictionaryExt.nullValue }
                                        localNextJ++
                                    }
                                }
                                if (skip1 > 0) {
                                    localNextJ = 1
                                    while (localNextJ < columnsINJ1.size) {
                                        key1[localNextJ] = columnsINJ1[localNextJ].skipSIP(skip1)
                                        SanityCheck.check { key1[localNextJ] != ResultSetDictionaryExt.undefValue }
                                        SanityCheck.check { key1[localNextJ] != ResultSetDictionaryExt.nullValue }
                                        localNextJ++
                                    }
                                }
                            }
// other join columns
                            localNextI = 1
                            while (localNextI < columnsINJ0.size) {
                                if (key0[localNextI] < key1[localNextI]) {
                                    skipO0++
                                    localNextJ = 0
                                    while (localNextJ < columnsINJ0.size) {
                                        key0[localNextJ] = columnsINJ0[localNextJ].next()
                                        SanityCheck.check { key0[localNextJ] != ResultSetDictionaryExt.undefValue }
                                        if (key0[localNextJ] == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.check { localNextJ == 0 }
                                            __close()
                                            break@loop
                                        }
                                        localNextJ++
                                    }
                                    continue@loop
                                } else if (key0[localNextI] > key1[localNextI]) {
                                    skipO1++
                                    localNextJ = 0
                                    while (localNextJ < columnsINJ1.size) {
                                        key1[localNextJ] = columnsINJ1[localNextJ].next()
                                        SanityCheck.check { key1[localNextJ] != ResultSetDictionaryExt.undefValue }
                                        if (key1[localNextJ] == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.check { localNextJ == 0 }
                                            __close()
                                            break@loop
                                        }
                                        localNextJ++
                                    }
                                    continue@loop
                                }
                                localNextI++
                            }
// safe the join columns
                            localNextI = 0
                            while (localNextI < columnsINJ0.size) {
                                localNextKeycopy[localNextI] = key0[localNextI]
                                localNextI++
                            }
// the only-A columns
                            localNextCounta = 0
                            loop2@ while (true) {
                                if (columnsINO0.isNotEmpty()) {
                                    if (localNextCounta >= data0[0].size) {
                                        localNextI = 0
                                        while (localNextI < data0.size) {
                                            val x = data0[localNextI]
                                            val d = IntArray(localNextCounta * 2)
                                            localNextJ = 0
                                            while (localNextJ < localNextCounta) {
                                                d[localNextJ] = x[localNextJ]
                                                localNextJ++
                                            }
                                            data0[localNextI] = d
                                            localNextI++
                                        }
                                    }
                                    localNextI = 0
                                    while (localNextI < columnsINO0.size) {
                                        data0[localNextI][localNextCounta] = columnsINO0[localNextI].skipSIP(skipO0)
                                        localNextI++
                                    }
                                    skipO0 = 0
                                }
                                localNextCounta++
                                localNextI = 0
                                while (localNextI < columnsINJ0.size) {
                                    key0[localNextI] = columnsINJ0[localNextI].next()
                                    SanityCheck.check { key0[localNextI] != ResultSetDictionaryExt.undefValue }
                                    localNextI++
                                }
                                localNextI = 0
                                while (localNextI < columnsINJ0.size) {
                                    if (key0[localNextI] != localNextKeycopy[localNextI]) {
                                        break@loop2
                                    }
                                    localNextI++
                                }
                            }
// the only-B columns
                            localNextCountb = 0
                            loop2@ while (true) {
                                if (columnsINO1.isNotEmpty()) {
                                    if (localNextCountb >= data1[0].size) {
                                        localNextI = 0
                                        while (localNextI < data1.size) {
                                            val x = data1[localNextI]
                                            val d = IntArray(localNextCountb * 2)
                                            localNextJ = 0
                                            while (localNextJ < localNextCountb) {
                                                d[localNextJ] = x[localNextJ]
                                                localNextJ++
                                            }
                                            data1[localNextI] = d
                                            localNextI++
                                        }
                                    }
                                    localNextI = 0
                                    while (localNextI < columnsINO1.size) {
                                        data1[localNextI][localNextCountb] = columnsINO1[localNextI].skipSIP(skipO1)
                                        localNextI++
                                    }
                                    skipO1 = 0
                                }
                                localNextCountb++
                                localNextI = 0
                                while (localNextI < columnsINJ1.size) {
                                    key1[localNextI] = columnsINJ1[localNextI].next()
                                    SanityCheck.check { key1[localNextI] != ResultSetDictionaryExt.undefValue }
                                    localNextI++
                                }
                                localNextI = 0
                                while (localNextI < columnsINJ1.size) {
                                    if (key1[localNextI] != localNextKeycopy[localNextI]) {
                                        break@loop2
                                    }
                                    localNextI++
                                }
                            }
                            POPJoin.crossProduct(data0, data1, localNextKeycopy, columnsOUT0, columnsOUT1, columnsOUTJ, localNextCounta, localNextCountb)
                            break@loop
                        }
                    } else {
                        __close()
                    }
                },
                { __close() }
            )
        }
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        // setup columns
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.println { "$uuid open $classname" }
        val child0 = children[0].evaluate(parent)
        val child1 = children[1].evaluate(parent)
        val columnsINO0 = mutableListOf<ColumnIterator>()
        val columnsINO1 = mutableListOf<ColumnIterator>()
        val columnsINJ0 = mutableListOf<ColumnIterator>()
        val columnsINJ1 = mutableListOf<ColumnIterator>()
        val columnsOUT0 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUT1 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // Key_in_outMap, which_outIteratorsCounter (J,O0,O1,none)
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
        for ((first, second) in outIterators) {
            val iterator = ColumnIteratorChildIteratorImpl(columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1)
            when (second) {
                0 -> {
                    outMap[first] = iterator
                    columnsOUTJ.add(iterator)
                }
                1 -> {
                    outMap[first] = iterator
                    columnsOUT0.add(iterator)
                }
                2 -> {
                    outMap[first] = iterator
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
