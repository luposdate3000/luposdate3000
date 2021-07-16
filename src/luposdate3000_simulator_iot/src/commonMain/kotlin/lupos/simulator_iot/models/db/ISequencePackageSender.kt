package lupos.simulator_iot.models.db

import lupos.simulator_iot.models.db.pck.SequencedPackage

internal interface ISequencePackageSender {
    fun send(pck: SequencedPackage)
    fun receive(pck: SequencedPackage)
    fun getSenderAddress(): Int
}
