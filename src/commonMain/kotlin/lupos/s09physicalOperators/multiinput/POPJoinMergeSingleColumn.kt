package lupos.s09physicalOperators.multiinput
import lupos.s04logicalOperators.iterator.ColumnIteratorNext
import lupos.s00misc.BenchmarkUtils
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPJoinMergeSingleColumn(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeSingleColumnID, "POPJoinMergeSingleColumn", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinMergeSingleColumn && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]


    class ColumnIteratorMergeJoin(@JvmField val child: Array<ColumnIterator>, @JvmField var head0: Int, @JvmField var head1: Int) : ColumnIterator() {
        @JvmField
        var totaltime = 0.0

        @JvmField
        var totalcounter = 0


        @JvmField
        var counter = 0

        @JvmField
        var value: Value? = head0

        init {
            next = ColumnIteratorNext("ColumnIteratorMergeJoin.next",::myNext)
            close = ::myClose
        }

        fun myClose() {
            BenchmarkUtils.setTimesHelper(11, totaltime, totalcounter)
            _close()
            SanityCheck.println({ "\$uuid close ColumnIteratorMergeJoin" })
            child[0].close()
            child[1].close()
        }

        fun myNext(): Value? {
            val timer = BenchmarkUtils.timesHelperMark()
            if (counter == 0) {
                var done = false
                var change = true
                loop@ while (change) {
                    change = false
                    while (head0 < head1) {
                        change = true
                        val c = child[0].next()
                        if (c == null) {
totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
                            close()
                            value = null
                            done = true
                            break@loop
                        } else {
                            head0 = c
                        }
                    }
                    while (head1 < head0) {
                        change = true
                        val c = child[1].next()
                        if (c == null) {
                            totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
close()
                            value = null
                            done = true
                            break@loop
                        } else {
                            head1 = c
                        }
                    }
                }
                if (!done) {
                    value = head0
                    val valuenonnull = head0
                    var hadnull = false
                    var count0 = 0
                    while (head0 == valuenonnull) {
                        count0++
                        val d = child[0].next()
                        if (d == null) {
                            hadnull = true
                            break
                        } else {
                            head0 = d
                        }
                    }
                    var count1 = 0
                    while (head1 == valuenonnull) {
                        count1++
                        val d = child[1].next()
                        if (d == null) {
                            hadnull = true
                            break
                        } else {
                            head1 = d
                        }
                    }
                    counter = count0 * count1
                    if (hadnull) {
                        if (counter == 0) {
                            totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
close()
                        } else {
                            next = ColumnIteratorNext("ColumnIteratorMergeJoin.next",::myNext2)
                        }
                    }
                }
            }
            counter--
            totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
            return value
        }

        fun myNext2(): Value? {
val timer = BenchmarkUtils.timesHelperMark()
            if (counter == 0) {
                totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
close()
                value = null
            } else {
                counter--
            }
totaltime += BenchmarkUtils.timesHelperDuration(timer)
            totalcounter++
            return value
        }
    }

    override fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { projectedVariables.size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.println({ "$uuid open $classname" })
        val child = Array(2) { children[it].evaluate(parent).columns[projectedVariables[0]]!! }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val a = child[0].next()
        val b = child[1].next()
        if (a != null && b != null) {
            outMap[projectedVariables[0]] = ColumnIteratorMergeJoin(child, a, b)
        } else {
            outMap[projectedVariables[0]] = ColumnIterator()
            SanityCheck.println({ "$uuid close $classname" })
            child[0].close()
            child[1].close()
        }
        return IteratorBundle(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
