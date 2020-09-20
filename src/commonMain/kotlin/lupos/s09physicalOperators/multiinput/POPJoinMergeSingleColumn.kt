package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMergeSingleColumn(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeSingleColumnID, "POPJoinMergeSingleColumn", arrayOf(childA, childB), ESortPriority.JOIN) {
    //optimized using javap
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinMergeSingleColumn && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    class ColumnIteratorImpl(@JvmField val child0: ColumnIterator, @JvmField val child1: ColumnIterator, @JvmField var head0: Int, @JvmField var head1: Int) : ColumnIterator() {
        @JvmField
        var counter: Int = 0

        @JvmField
        var value: Int = head0

        @JvmField
        var label = 1

        @JvmField
        var sipbuf = IntArray(2)
        override suspend fun next(): Value {
            when (label) {
                1 -> {
                    if (counter == 0) {
                        var change = true
                        while (change) {
                            change = false
                            while (head0 < head1) {
                                child0.nextSIP(head1, sipbuf)
                                val c = sipbuf[1]
                                if (c == ResultSetDictionary.nullValue) {
                                    _close()
                                    return ResultSetDictionary.nullValue
                                } else {
                                    head0 = c
                                }
                            }
                            while (head1 < head0) {
                                change = true
                                child1.nextSIP(head0, sipbuf)
                                val c = sipbuf[1]
                                if (c == ResultSetDictionary.nullValue) {
                                    _close()
                                    return ResultSetDictionary.nullValue
                                } else {
                                    head1 = c
                                }
                            }
                        }
                        value = head0
                        var hadnull = false
                        var count0 = 0
                        while (head0 == value) {
                            count0++
                            val d = child0.next()
                            if (d == ResultSetDictionary.nullValue) {
                                hadnull = true
                                break
                            } else {
                                head0 = d
                            }
                        }
                        var count1 = 0
                        while (head1 == value) {
                            count1++
                            val d = child1.next()
                            if (d == ResultSetDictionary.nullValue) {
                                hadnull = true
                                break
                            } else {
                                head1 = d
                            }
                        }
                        counter = count0 * count1
                        if (hadnull) {
                            if (counter == 0) {
                                _close()
                            } else {
                                label = 2
                            }
                        }
                    }
                    counter--
                    return value
                }
                2 -> {
                    if (counter == 0) {
                        _close()
                        return ResultSetDictionary.nullValue
                    } else {
                        counter--
                    }
                    return value
                }
                else -> {
                    return ResultSetDictionary.nullValue
                }
            }
        }

        suspend inline fun _close() {
            if (label != 0) {
                label = 0
                SanityCheck.println({ "\$uuid close ColumnIteratorJoinMergeSingleColumn" })
                child0.close()
                child1.close()
            }
        }

        override suspend fun close() {
            _close()
        }
    }

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { projectedVariables.size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.println({ "$uuid open $classname" })
        val child0 = children[0].evaluate(parent).columns[projectedVariables[0]]!!
        val child1 = children[1].evaluate(parent).columns[projectedVariables[0]]!!
        val outMap = mutableMapOf<String, ColumnIterator>()
        val a = child0.next()
        val b = child1.next()
        if (a != ResultSetDictionary.nullValue && b != ResultSetDictionary.nullValue) {
            outMap[projectedVariables[0]] = ColumnIteratorImpl(child0, child1, a, b)
        } else {
            outMap[projectedVariables[0]] = ColumnIteratorEmpty()
            SanityCheck.println({ "$uuid close $classname" })
            child0.close()
            child1.close()
        }
        return IteratorBundle(outMap)
    }

    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
