package lupos.simulator_iot.queryproc

import lupos.simulator_iot.queryproc.pck.DBSequenceEndPackage
import lupos.simulator_iot.queryproc.pck.SequencedPackage

internal class SequenceKeeper(private val sender: ISequencePackageSender) {

    private val sequenceCounters: MutableMap<Int, Int> = mutableMapOf()

    private val receivedNumbersOfPackages: MutableMap<Int, Int> = mutableMapOf()

    private val receiveBuffer: MutableMap<Int, MutableList<SequencedPackage>> = mutableMapOf()

    internal fun receive(pck: SequencedPackage) {
        if (pck is DBSequenceEndPackage) {
            processSequenceEnd(pck)
        } else {
            buff(pck)
        }

        process(pck)
    }

    private fun processSequenceEnd(pck: DBSequenceEndPackage) {
        receivedNumbersOfPackages[pck.sourceAddress] = pck.numberOfPackages
    }

    private fun buff(pck: SequencedPackage) {
        if (!receiveBuffer.containsKey(pck.sourceAddress)) {
            receiveBuffer[pck.sourceAddress] = mutableListOf()
        }
        receiveBuffer[pck.sourceAddress]!!.add(pck)
    }

    private fun process(pck: SequencedPackage) {
        if (!isEndPackageArrived(pck.sourceAddress)) {
            return
        }

        if (!isAtLeastOnePackageArrived(pck.sourceAddress)) {
            return
        }

        val numberOfPackagesInSequence = receivedNumbersOfPackages[pck.sourceAddress]!!
        val numberOfArrivedPackages = receiveBuffer[pck.sourceAddress]!!.size
        if (numberOfArrivedPackages == numberOfPackagesInSequence) {
            flushBuffer(pck.sourceAddress)
        }
    }

    private fun isEndPackageArrived(src: Int): Boolean {
        return receivedNumbersOfPackages.containsKey(src)
    }

    private fun isAtLeastOnePackageArrived(src: Int): Boolean {
        return receiveBuffer.containsKey(src)
    }

    private fun flushBuffer(src: Int) {
        val packages = receiveBuffer[src]!!
        receiveInOrder(packages)
        receiveBuffer.remove(src)
        receivedNumbersOfPackages.remove(src)
    }

    private fun receiveInOrder(packages: MutableList<SequencedPackage>) {
        packages.sortBy { it.sequenceNumber }
        for (pck in packages)
            sender.receive(pck)
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
            sender.send(endPck)
        }
        sequenceCounters.clear()
    }

    internal fun sendSequencedPackage(pck: SequencedPackage) {
        pck.sequenceNumber = getSequenceNumber(pck.destinationAddress)
        sender.send(pck)
    }
}
