package lupos.simulator_iot.queryproc

import lupos.simulator_iot.queryproc.pck.DBSequenceEndPackage
import lupos.simulator_iot.queryproc.pck.SequencedPackage

internal class SequenceKeeper(private val sender: ISequencePackageSender) {

    private val numOfPacksInitValue = -1

    private val sequenceCounters: MutableMap<Int, Int> = mutableMapOf()

    private val receiveBuffer: MutableMap<SequenceEndpoint, SequenceBuffer> = mutableMapOf()

    internal val firstSequenceID = 0

    private var currentSequenceID = firstSequenceID

    internal fun receive(pck: SequencedPackage) {
        val endpoint = SequenceEndpoint(pck.sourceAddress, pck.sequenceID)
        addNewEndpoint(endpoint)
        if (pck is DBSequenceEndPackage) {
            receiveBuffer[endpoint]!!.expectedNumberOfPackages = pck.numberOfPackages
        } else {
            receiveBuffer[endpoint]!!.packetsReceivedSoFar.add(pck)
        }
        process(endpoint)
    }

    private fun addNewEndpoint(endpoint: SequenceEndpoint) {
        if (!receiveBuffer.containsKey(endpoint)) {
            receiveBuffer[endpoint] = SequenceBuffer(mutableListOf(), numOfPacksInitValue)
        }
    }

    private fun process(endpoint: SequenceEndpoint) {
        if (!isEndPackageArrived(endpoint)) {
            return
        }
        if (!isAtLeastOnePackageArrived(endpoint)) {
            return
        }
        val buffer = receiveBuffer[endpoint]!!
        if (buffer.packetsReceivedSoFar.size == buffer.expectedNumberOfPackages) {
            flushBuffer(endpoint)
        }
    }

    private fun isEndPackageArrived(endpoint: SequenceEndpoint): Boolean {
        val buffer = receiveBuffer[endpoint]
        if(buffer != null) {
            if(buffer.expectedNumberOfPackages != numOfPacksInitValue)
                return true
        }
        return false
    }

    private fun isAtLeastOnePackageArrived(endpoint: SequenceEndpoint): Boolean {
        return receiveBuffer.containsKey(endpoint)
    }

    private fun flushBuffer(endpoint: SequenceEndpoint) {
        val packages = receiveBuffer[endpoint]!!
        receiveInOrder(packages.packetsReceivedSoFar)
        receiveBuffer.remove(endpoint)
    }

    private fun receiveInOrder(packages: MutableList<SequencedPackage>) {
        packages.sortBy { it.packageNumberInSequence }
        for (pck in packages) {
            sender.receive(pck)
        }
    }

    private fun getSequenceNumber(destinationAddress: Int): Int {
        if (!sequenceCounters.containsKey(destinationAddress)) {
            sequenceCounters[destinationAddress] = 1
        } else {
            sequenceCounters[destinationAddress] = sequenceCounters[destinationAddress]!! + 1
        }
        return sequenceCounters[destinationAddress]!!
    }

    internal fun markSequenceEnd() {
        for ((dest, number) in sequenceCounters) {
            val endPck = DBSequenceEndPackage(sender.getSenderAddress(), dest, number)
            endPck.sequenceID = currentSequenceID
            sender.send(endPck)
        }
        sequenceCounters.clear()
        currentSequenceID++
    }

    internal fun sendSequencedPackage(pck: SequencedPackage) {
        pck.packageNumberInSequence = getSequenceNumber(pck.destinationAddress)
        pck.sequenceID = currentSequenceID
        sender.send(pck)
    }
}
