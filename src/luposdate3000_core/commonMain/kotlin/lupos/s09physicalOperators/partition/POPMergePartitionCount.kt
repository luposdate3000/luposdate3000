package lupos.s09physicalOperators.partition

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

internal class POPMergePartitionCount(query: Query, projectedVariables: List<String>, val partitionVariable: String, val partitionCount: Int, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMergePartitionCountID, "POPMergePartitionCount", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        if (variable == partitionVariable) {
            return 1
        } else {
            return children[0].getPartitionCount(variable)
        }
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount)
        return res
    }

    override fun getRequiredVariableNames(): List<String> = listOf<String>()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        if (tmp is POPBase) {
            return tmp.getProvidedVariableNamesInternal()
        } else {
            return tmp.getProvidedVariableNames()
        }
    }

    override fun cloneOP() = POPMergePartitionCount(query, projectedVariables, partitionVariable, partitionCount, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartitionCount && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (partitionCount == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            //partitionVariable as any other variable is not included in the result of the child operator
            val ringbufferReadHead = IntArray(partitionCount) { 0 } //owned by read-thread - no locking required - available count is the difference between "ringbufferReadHead" and "ringbufferWriteHead"
            val ringbufferWriteHead = IntArray(partitionCount) { 0 } //owned by write thread - no locking required
            val writerFinished = IntArray(partitionCount) { 0 } //writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until partitionCount) {
                Parallel.launch {
                    val child = children[0].evaluate(Partition(parent, partitionVariable, p, partitionCount))
                    loop@ while (readerFinished == 0) {
                        SanityCheck.println({ "merge $uuid $p writer loop start" })
                        var tmp = child.hasNext2()
                        if (tmp) {
                            SanityCheck.println({ "merge $uuid $p writer append data" })
                            ringbufferWriteHead[p]++
                        } else {
                            SanityCheck.println({ "merge $uuid $p writer closed B" })
                            writerFinished[p] = 1
                            break@loop
                        }
                    }
                    SanityCheck.println({ "merge $uuid $p writer exited loop" })
                }
            }
            var iterator = object : IteratorBundle(0) {
                override suspend fun hasNext2(): Boolean {
                    var res = false
                    loop@ while (true) {
                        SanityCheck.println({ "merge $uuid reader loop start" })
                        var finishedWriters = 0
                        for (p in 0 until partitionCount) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                //non empty queue -> read one row
                                SanityCheck.println({ "merge $uuid $p reader consumed data" })
                                res = true
                                ringbufferReadHead[p]++
                                break@loop
                            } else if (writerFinished[p] == 1) {
                                finishedWriters++
                            }
                        }
                        if (finishedWriters == partitionCount) {
                            //done
                            break@loop
                        }
                        SanityCheck.println({ "merge $uuid reader wait for writer" })
                        Parallel.delay(1)
                    }
                    return res
                }

                suspend override fun hasNext2Close() {
                    SanityCheck.println({ "merge $uuid reader closed" })
                    readerFinished = 1
                }
            }
            return iterator
        }
    }
}
