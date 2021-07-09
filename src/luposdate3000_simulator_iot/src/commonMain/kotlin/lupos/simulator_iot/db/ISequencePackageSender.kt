package lupos.simulator_iot.db

internal interface ISequencePackageSender {
    fun send(pck: SequencedPackage)
    fun receive(pck: SequencedPackage)
    fun getSenderAddress(): Int
}
