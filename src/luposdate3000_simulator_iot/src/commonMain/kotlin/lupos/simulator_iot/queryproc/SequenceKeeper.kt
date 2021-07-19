package lupos.simulator_iot.queryproc

import lupos.simulator_core.PriorityQueue
import lupos.simulator_iot.queryproc.pck.DBSequenceEndPackage
import lupos.simulator_iot.queryproc.pck.SequencedPackage

internal class SequenceKeeper(private val sender: ISequencePackageSender) {

    private val packageCounter: MutableMap<Int, Int> = mutableMapOf()

    private val firstSequenceNumber = 0

    private val firstPackageNumber = 0

    private var currentSequenceID = firstSequenceNumber

    private val receiveCounters: MutableMap<Int, SequenceCounter> = mutableMapOf()

    private val receiveBuffer: MutableMap<Int, PriorityQueue<SequencedPackage>> = mutableMapOf()

    internal fun receive(pck: SequencedPackage) {
        addCounter(pck.sourceAddress)
        buffSequencedPackage(pck)
        flushIfPossible()
    }

    private fun addCounter(address: Int) {
        if (!receiveCounters.containsKey(address)) {
            receiveCounters[address] = SequenceCounter(firstPackageNumber, firstSequenceNumber)
        }
    }

    private fun buffSequencedPackage(pck: SequencedPackage) {
        if (!receiveBuffer.containsKey(pck.sourceAddress)) {
            receiveBuffer[pck.sourceAddress] = PriorityQueue(compareBy<SequencedPackage> { it.sequenceNumber }.thenBy { it.packageNumber })
        }
        receiveBuffer[pck.sourceAddress]!!.enqueue(pck)
    }

    private fun flushIfPossible() {
        for ((address, buffer) in receiveBuffer) {
            flushIfPossible(address, buffer)
        }
    }

    private fun flushIfPossible(src: Int, queue: PriorityQueue<SequencedPackage>) {
        val counters = receiveCounters[src]!!
        while (queue.hasNext()) {
            val first = queue.peek()
            if (isFlushAble(first, counters)) {
                flushFirst(queue, counters)
            } else {
                break
            }
        }
    }

    private fun flushFirst(queue: PriorityQueue<SequencedPackage>, counters: SequenceCounter) {
        val first = queue.dequeue()
        if (first is DBSequenceEndPackage) {
            counters.expectedPackageNumber = 0
            counters.expectedSequenceNumber++
        } else {
            sender.receive(first)
            counters.expectedPackageNumber++
        }
    }

    private fun isFlushAble(pck: SequencedPackage, c: SequenceCounter): Boolean {
        return pck.sequenceNumber == c.expectedSequenceNumber && pck.packageNumber == c.expectedPackageNumber
    }

    private fun getPackageNumber(destinationAddress: Int): Int {
        if (!packageCounter.containsKey(destinationAddress)) {
            packageCounter[destinationAddress] = firstPackageNumber
        } else {
            packageCounter[destinationAddress] = packageCounter[destinationAddress]!! + 1
        }
        return packageCounter[destinationAddress]!!
    }

    internal fun markSequenceEnd() {
        for (dest in packageCounter.keys) {
            val endPck = DBSequenceEndPackage(sender.getSenderAddress(), dest)
            sendSequencedPackage(endPck)
        }
        if (packageCounter.isNotEmpty()) {
            packageCounter.clear()
            currentSequenceID++
        }
    }

    internal fun sendSequencedPackage(pck: SequencedPackage) {
        pck.packageNumber = getPackageNumber(pck.destinationAddress)
        pck.sequenceNumber = currentSequenceID
        sender.send(pck)
    }
}
