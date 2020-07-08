package lupos.s09physicalOperators.parallel

import lupos.s00misc.Partition

import lupos.s00misc.Coverage
import lupos.s00misc.XMLElement
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
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
res.addAttribute("partitionVariable",partitionVariable)
return res
}
    override fun cloneOP() = POPMergeParallel(query, projectedVariables, partitionVariable, children[0].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} MergeParallel {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPMergeParallel && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (ParallelBase.k == 1) {
            //single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            SanityCheck.check { variables.contains(partitionVariable) }
            val elementsPerRing = ParallelBase.queue_size * variables.size
            val ringbuffer = IntArray(elementsPerRing * ParallelBase.k) //only modified by writer, reader just modifies its pointer
            val ringbufferStart = IntArray(ParallelBase.k) { it * elementsPerRing } //constant
            val ringbufferReadHead = IntArray(ParallelBase.k) { it * elementsPerRing } //owned by read-thread - no locking required
            val ringbufferWriteHead = IntArray(ParallelBase.k) { it * elementsPerRing } //owned by write thread - no locking required
            val writerFinished = IntArray(ParallelBase.k) { 0 } //writer changes to 1 if finished
            var readerFinished = 0

            for (p in 0 until ParallelBase.k) {
//TODOlaunch ... {
                val child = children[0].evaluate(Partition(parent, partitionVariable, p)).rows
                loop@ while (true) {
                    var t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                    while (ringbufferReadHead[p] == t) {
//TODOwait for reader
                        if (readerFinished==1) {
                            child.close()
                            writerFinished[p] = 1
                            break@loop
                        }
                    }
                    var tmp = child.next()
                    if (tmp == -1) {
                        writerFinished[p] = 1
                        break@loop
                    } else {
                        for (variable in 0 until variables.size) {
                            ringbuffer[ringbufferWriteHead[p] + variable + ringbufferStart[p]] = child.buf[tmp + variable]
                        }
                    }
                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                }
//TODOlaunch ... }
            }
            var iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.next = {
var                res = -1
                loop@ while (true) {
                    var finishedWriters = 0
                    for (p in 0 until ParallelBase.k) {
                        if (writerFinished[p] == 1) {
                            finishedWriters++
                        } else if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
//non empty queue -> read one row
                            for (variable in 0 until variables.size) {
                                iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                            }
                            res = 0
                            ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
break@loop
                        }
                    }
                    if (finishedWriters == ParallelBase.k) {
//done
                        break@loop
                    }
//TODO wait for writer - or join one
                }
/*return*/res
            }
            iterator.close = {
                readerFinished = 1
                for (p in 0 until ParallelBase.k) {
                    ringbufferReadHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
