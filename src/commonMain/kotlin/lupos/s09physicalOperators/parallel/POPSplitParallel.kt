package lupos.s09physicalOperators.parallel

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
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSplitParallel(query: Query, projectedVariables: List<String>, val partitionVariable: String, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPSplitParallelID, "POPSplitParallel", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        return res
    }

    override fun cloneOP() = POPSplitParallel(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitParallel && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (ParallelBase.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            var job: Job? = null
            val childPartition = Partition(parent, partitionVariable)
            query.partitionsLock.withWriteLockSuspend {
                val tmpIterators = query.partitionsIterators[uuid]
                if (tmpIterators != null) {
                    iterators = tmpIterators[childPartition]
                }
                val tmpJob = query.partitionsJobs[uuid]
                if (tmpJob != null) {
                    job = tmpJob[childPartition]
                }
                if (iterators == null) {
                    iterators = Array(ParallelBase.k) { IteratorBundle(0) }
                    val variables = getProvidedVariableNames()
                    val variables0 = children[0].getProvidedVariableNames()
                    SanityCheck.check { variables0.containsAll(variables) }
                    SanityCheck.check { variables.containsAll(variables0) }
                    SanityCheck.check { variables.contains(partitionVariable) }
                    val elementsPerRing = ParallelBase.queue_size * variables.size
                    val ringbuffer = IntArray(elementsPerRing * ParallelBase.k) //only modified by writer, reader just modifies its pointer
                    val ringbufferStart = IntArray(ParallelBase.k) { it * elementsPerRing } //constant
                    val ringbufferReadHead = IntArray(ParallelBase.k) { 0 } //owned by read-thread - no locking required
                    val ringbufferWriteHead = IntArray(ParallelBase.k) { 0 } //owned by write thread - no locking required
                    val readerFinished = IntArray(ParallelBase.k) { 0 } //writer changes to 1 if finished
                    var writerFinished = 0
                    println("ringbuffersize = ${ringbuffer.size} ${elementsPerRing} ${ParallelBase.k} ${ringbufferStart.map { it }} ${ringbufferReadHead.map { it }} ${ringbufferWriteHead.map { it }}")
                    job = GlobalScope.launch(Dispatchers.Default) {
                        val child = children[0].evaluate(childPartition).rows
                        var hashVariableIndex = -1
                        val variableMapping = IntArray(variables.size)
                        for (variable in 0 until variables.size) {
                            for (variable2 in 0 until variables.size) {
                                if (child.columns[variable] == partitionVariable) {
                                    hashVariableIndex = variable
                                }
                                if (variables[variable2] == child.columns[variable]) {
                                    variableMapping[variable] = variable2
                                    break
                                }
                            }
                        }
                        SanityCheck.check { hashVariableIndex != -1 }
                        loop@ while (isActive) {
                            println("split $uuid writer loop start")
                            var tmp = child.next()
                            if (tmp == -1) {
                                println("split $uuid writer closed A")
                                writerFinished = 1
                                break@loop
                            } else {
                                var p = child.buf[tmp + hashVariableIndex]
                                if (p < 0) {
                                    p = -p
                                }
                                p = p % ParallelBase.k
                                println("selected $p for $partitionVariable = $hashVariableIndex value ${child.buf[tmp + hashVariableIndex]}")
                                var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[p] == t) {
                                    println("split $uuid $p writer wait for reader")
                                    delay(1)
                                    if (!isActive) {
                                        println("split $uuid $p writer closed B")
                                        child.close()
                                        writerFinished = 1
                                        break@loop
                                    }
                                }
                                println("split $uuid $p writer append data ${variables.size} ${variableMapping.toMutableList()} ${ringbufferStart[p]}")
                                for (variable in 0 until variables.size) {
                                    println("split $uuid $p writer append data ... ${variable} ${ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]} ${tmp + variable}")
                                    ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    println("split $uuid $p writer append data --- $variables ${child.columns.map { it }} ${ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]}<-${tmp + variable} = ${child.buf[tmp + variable]}")
                                }
                                println("split $uuid $p writer append data - written data")
                                ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                println("split $uuid $p writer append data - increased pointer")
                            }
                            println("split $uuid writer loop end of iteration")
                        }
                        println("split $uuid writer exited loop")
                    }
                    for (p in 0 until ParallelBase.k) {
                        var iterator = RowIterator()
                        iterator.columns = variables.toTypedArray()
                        iterator.buf = IntArray(variables.size)
                        iterator.next = {
                            var res = -1
                            loop@ while (true) {
                                println("split $uuid $p reader loop start")
                                if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                    //non empty queue -> read one row
                                    println("split $uuid $p reader consumed data")
                                    for (variable in 0 until variables.size) {
                                        iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                                    }
                                    res = 0
                                    ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                                    break@loop
                                } else if (writerFinished == 1) {
                                    println("split $uuid $p reader closed")
                                    break@loop
                                }
                                println("split $uuid $p reader wait for writer")
                                delay(1)
                            }
                            /*return*/res
                        }
                        iterator.close = {
                            println("split $uuid $p reader close")
                            readerFinished[p] = 1
                            var tmp = 0
                            for (p in 0 until ParallelBase.k) {
                                if (readerFinished[p] == 1) {
                                    tmp++
                                }
                            }
                            if (tmp == ParallelBase.k) {
                                runBlocking {
                                    job!!.cancelAndJoin()
                                }
                            }
                            println("split $uuid $p reader closed")
                        }
                        iterators!![p] = IteratorBundle(iterator)
                    }
                    if (tmpIterators == null || tmpJob == null) {
                        query.partitionsIterators[uuid] = mutableMapOf(childPartition to iterators!!)
                        query.partitionsJobs[uuid] = mutableMapOf(childPartition to job!!)
                    } else {
                        tmpIterators[childPartition] = iterators!!
                        tmpJob[childPartition] = job!!
                    }
                }
            }
            return iterators!![parent.data[partitionVariable]!!]
        }
    }
}
