package lupos.s09physicalOperators.partition

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPMergePartitionCount(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMergePartitionCountID, "POPMergePartitionCount", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
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

    override fun cloneOP() = POPMergePartitionCount(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartitionCount && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (Partition.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            //partitionVariable as any other variable is not included in the result of the child operator
            val elementsPerRing = Partition.queue_size * variables.size
            val ringbufferReadHead = IntArray(Partition.k) { 0 } //owned by read-thread - no locking required - available count is the difference between "ringbufferReadHead" and "ringbufferWriteHead"
            val ringbufferWriteHead = IntArray(Partition.k) { 0 } //owned by write thread - no locking required
            val writerFinished = IntArray(Partition.k) { 0 } //writer changes to 1 if finished
            var readerFinished = 0
            val jobs = mutableListOf<Job>()
            for (p in 0 until Partition.k) {
                val job = GlobalScope.launch(Dispatchers.Default) {
                    val child = children[0].evaluate(Partition(parent, partitionVariable, p))
                    loop@ while (isActive && readerFinished == 0) {
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
                jobs.add(job)
            }
            var iterator = IteratorBundle(0)
            iterator.hasNext2 = {
                var res = false
                loop@ while (true) {
                    SanityCheck.println({ "merge $uuid reader loop start" })
                    var finishedWriters = 0
                    for (p in 0 until Partition.k) {
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
                    if (finishedWriters == Partition.k) {
                        //done
                        break@loop
                    }
                    SanityCheck.println({ "merge $uuid reader wait for writer" })
                    delay(1)
                }
                /*return*/res
            }
            iterator.hasNext2Close = {
                SanityCheck.println({ "merge $uuid reader closed" })
                readerFinished = 1
                runBlocking {
                    for (job in jobs) {
                        job.cancelAndJoin()
                    }
                }
            }
            return iterator
        }
    }
}
