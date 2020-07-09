package lupos.s09physicalOperators.parallel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlinx.coroutines.delay
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPMergeParallel(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMergeParallelID, "POPMergeParallel", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        return res
    }

    override fun cloneOP() = POPMergeParallel(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergeParallel && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (ParallelBase.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
//the variable may be eliminated directly after using it in the join            SanityCheck.check { variables.contains(partitionVariable) }
            val elementsPerRing = ParallelBase.queue_size * variables.size
            val ringbuffer = IntArray(elementsPerRing * ParallelBase.k) //only modified by writer, reader just modifies its pointer
            val ringbufferStart = IntArray(ParallelBase.k) { it * elementsPerRing } //constant
            val ringbufferReadHead = IntArray(ParallelBase.k) { 0 } //owned by read-thread - no locking required
            val ringbufferWriteHead = IntArray(ParallelBase.k) { 0 } //owned by write thread - no locking required
            val writerFinished = IntArray(ParallelBase.k) { 0 } //writer changes to 1 if finished
            var readerFinished = 0
            val jobs = mutableListOf<Job>()
            for (p in 0 until ParallelBase.k) {
                val job = GlobalScope.launch(Dispatchers.Default) {
                    val child = children[0].evaluate(Partition(parent, partitionVariable, p)).rows
                    val variableMapping = IntArray(variables.size)
                    for (variable in 0 until variables.size) {
                        for (variable2 in 0 until variables.size) {
                            if (variables[variable2] == child.columns[variable]) {
                                variableMapping[variable] = variable2
                                break
                            }
                        }
                    }
                    loop@ while (isActive) {
                        println("merge $uuid $p writer loop start")
                        var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                        while (ringbufferReadHead[p] == t) {
                            println("merge $uuid $p writer wait for reader to remove data")
                            delay(1)
                            if (!isActive) {
println("merge $uuid $p writer closed A")
                                child.close()
                                writerFinished[p] = 1
                                break@loop
                            }
                        }
                        var tmp = child.next()
                        if (tmp == -1) {
println("merge $uuid $p writer closed B")
                            writerFinished[p] = 1
                            break@loop
                        } else {
                            println("merge $uuid $p writer append data")
                            for (variable in 0 until variables.size) {
                                ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                            }
                            ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                        }
                    }
println("merge $uuid $p writer exited loop")
                }
                jobs.add(job)
            }
            var iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
iterator.buf=IntArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    println("merge $uuid reader loop start")
                    var finishedWriters = 0
                    for (p in 0 until ParallelBase.k) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            //non empty queue -> read one row
                            println("merge $uuid $p reader consumed data")
                            for (variable in 0 until variables.size) {
                                iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                            }
                            res = 0
                            ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                            break@loop
                        }else if (writerFinished[p] == 1) {
                            finishedWriters++
                        }
                    }
                    if (finishedWriters == ParallelBase.k) {
                        //done
                        break@loop
                    }
                    println("merge $uuid reader wait for writer")
                    delay(1)
                }
                /*return*/res
            }
            iterator.close = {
                println("merge $uuid reader closed")
                readerFinished = 1
                runBlocking {
                    for (job in jobs) {
                        job.cancelAndJoin()
                    }
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
