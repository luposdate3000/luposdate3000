package lupos.simulator_iot.models.queryproc

import lupos.simulator_iot.models.queryproc.pck.SequencedPackage

internal interface ISequencePackageSender {
    fun send(pck: SequencedPackage)
    fun receive(pck: SequencedPackage)
    fun getSenderAddress(): Int
}
