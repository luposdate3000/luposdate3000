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
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
public class POPJoinMergeSingleColumn(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeSingleColumnID, "POPJoinMergeSingleColumn", arrayOf(childA, childB), ESortPriority.JOIN) {
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
    // optimized using javap
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean = other is POPJoinMergeSingleColumn && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    internal class ColumnIteratorImpl(@JvmField val child0: ColumnIterator, @JvmField val child1: ColumnIterator, @JvmField var head0: Int, @JvmField var head1: Int) : ColumnIterator() {
        @JvmField
        var counter: Int = 0
        @JvmField
        var value: Int = head0
        @JvmField
        var label = 1
        @JvmField
        var sipbuf = IntArray(2)
        override /*suspend*/ fun next(): Int {
            when (label) {
                1 -> {
                    if (counter == 0) {
                        var change = true
                        while (change) {
                            change = false
                            while (head0 < head1) {
                                child0.nextSIP(head1, sipbuf)
                                val c = sipbuf[1]
                                if (c == ResultSetDictionaryExt.nullValue) {
                                    _close()
                                    return ResultSetDictionaryExt.nullValue
                                } else {
                                    head0 = c
                                }
                            }
                            while (head1 < head0) {
                                change = true
                                child1.nextSIP(head0, sipbuf)
                                val c = sipbuf[1]
                                if (c == ResultSetDictionaryExt.nullValue) {
                                    _close()
                                    return ResultSetDictionaryExt.nullValue
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
                            if (d == ResultSetDictionaryExt.nullValue) {
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
                            if (d == ResultSetDictionaryExt.nullValue) {
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
                        return ResultSetDictionaryExt.nullValue
                    } else {
                        counter--
                    }
                    return value
                }
                else -> {
                    return ResultSetDictionaryExt.nullValue
                }
            }
        }
        /*suspend*/ internal inline fun _close() {
            if (label != 0) {
                label = 0
                SanityCheck.println { "\$uuid close ColumnIteratorJoinMergeSingleColumn" }
                child0.close()
                child1.close()
            }
        }
        override /*suspend*/ fun close() {
            _close()
        }
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check { !optional }
        SanityCheck.check { projectedVariables.size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.println { "$uuid open $classname" }
        val child0 = children[0].evaluate(parent).columns[projectedVariables[0]]!!
        val child1 = children[1].evaluate(parent).columns[projectedVariables[0]]!!
        val outMap = mutableMapOf<String, ColumnIterator>()
        val a = child0.next()
        val b = child1.next()
        if (a != ResultSetDictionaryExt.nullValue && b != ResultSetDictionaryExt.nullValue) {
            outMap[projectedVariables[0]] = ColumnIteratorImpl(child0, child1, a, b)
        } else {
            outMap[projectedVariables[0]] = ColumnIteratorEmpty()
            SanityCheck.println { "$uuid close $classname" }
            child0.close()
            child1.close()
        }
        return IteratorBundle(outMap)
    }
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
