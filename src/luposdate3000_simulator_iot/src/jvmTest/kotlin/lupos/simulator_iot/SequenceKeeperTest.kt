package lupos.simulator_iot
import lupos.simulator_iot.queryproc.ISequencePackageSender
import lupos.simulator_iot.queryproc.SequenceKeeper
import lupos.simulator_iot.queryproc.pck.DBSequenceEndPackage
import lupos.simulator_iot.queryproc.pck.SequencedPackage
import kotlin.test.Test
import kotlin.test.assertEquals

class SequenceKeeperTest {

    internal class SequencedPackageStub(
        sourceAddress: Int,
        destinationAddress: Int
    ) : SequencedPackage(sourceAddress, destinationAddress) {
        var id = 0
    }

    @Test
    fun endPckArrivesAtFirst() {
        val receivedList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {}
            override fun receive(pck: SequencedPackage) {
                receivedList.add(pck)
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)
        val end = DBSequenceEndPackage(3, 4, 2)
        keeper.receive(end)
        assertEquals(0, receivedList.size)
        val pck1 = SequencedPackageStub(3, 4)
        keeper.receive(pck1)
        assertEquals(0, receivedList.size)
        val pck2 = SequencedPackageStub(3, 4)
        keeper.receive(pck2)
        assertEquals(2, receivedList.size)
    }

    @Test
    fun endPckArrivesAtLast() {
        val receivedList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {}
            override fun receive(pck: SequencedPackage) {
                receivedList.add(pck)
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)
        val pck1 = SequencedPackageStub(3, 4)
        keeper.receive(pck1)
        assertEquals(0, receivedList.size)
        val pck2 = SequencedPackageStub(3, 4)
        keeper.receive(pck2)
        assertEquals(0, receivedList.size)
        val end = DBSequenceEndPackage(3, 4, 2)
        keeper.receive(end)
        assertEquals(2, receivedList.size)
    }

    @Test
    fun otherPckArrivesInBetween() {
        val receivedList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {}
            override fun receive(pck: SequencedPackage) {
                receivedList.add(pck)
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)
        val pck1 = SequencedPackageStub(3, 4)
        pck1.id = 1
        keeper.receive(pck1)
        assertEquals(0, receivedList.size)
        val pck2 = SequencedPackageStub(8, 4)
        pck2.id = 2
        keeper.receive(pck2)
        assertEquals(0, receivedList.size)
        val pck3 = SequencedPackageStub(3, 4)
        pck3.id = 3
        keeper.receive(pck3)
        assertEquals(0, receivedList.size)
        val end = DBSequenceEndPackage(3, 4, 2)
        keeper.receive(end)
        assertEquals(2, receivedList.size)
        assertEquals(pck1.id, (receivedList[0] as SequencedPackageStub).id)
        assertEquals(pck3.id, (receivedList[1] as SequencedPackageStub).id)
    }

    @Test
    fun sendingIncreasesSequenceNumber() {
        val sentList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {
                sentList.add(pck)
            }
            override fun receive(pck: SequencedPackage) {
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)
        val pck1 = SequencedPackageStub(0, 4)
        keeper.sendSequencedPackage(pck1)
        assertEquals(1, sentList[0].packageNumberInSequence)

        val pck2 = SequencedPackageStub(0, 4)
        keeper.sendSequencedPackage(pck2)
        assertEquals(2, sentList[1].packageNumberInSequence)

        val pck3 = SequencedPackageStub(0, 4)
        keeper.sendSequencedPackage(pck3)
        assertEquals(3, sentList[2].packageNumberInSequence)
    }

    @Test
    fun sendingIncreasesSequenceNumberIndividually() {
        val sentList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {
                sentList.add(pck)
            }
            override fun receive(pck: SequencedPackage) {
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)
        val pck1 = SequencedPackageStub(0, 1)
        keeper.sendSequencedPackage(pck1)
        assertEquals(1, sentList[0].packageNumberInSequence)

        val pck2 = SequencedPackageStub(0, 4)
        keeper.sendSequencedPackage(pck2)
        assertEquals(1, sentList[1].packageNumberInSequence)

        val pck3 = SequencedPackageStub(0, 4)
        keeper.sendSequencedPackage(pck3)
        assertEquals(2, sentList[2].packageNumberInSequence)

        val pck4 = SequencedPackageStub(0, 1)
        keeper.sendSequencedPackage(pck4)
        assertEquals(2, sentList[3].packageNumberInSequence)

        val pck5 = SequencedPackageStub(0, 9)
        keeper.sendSequencedPackage(pck5)
        assertEquals(1, sentList[4].packageNumberInSequence)
    }

    @Test
    fun packagesAreSortedBySequenceNumber() {
        val receivedList: MutableList<SequencedPackage> = mutableListOf()
        val sender = object : ISequencePackageSender {
            override fun send(pck: SequencedPackage) {}
            override fun receive(pck: SequencedPackage) {
                receivedList.add(pck)
            }
            override fun getSenderAddress(): Int {
                return 0
            }
        }
        val keeper = SequenceKeeper(sender)

        val pck1 = SequencedPackageStub(3, 4)
        pck1.id = 1
        pck1.packageNumberInSequence = 1
        keeper.receive(pck1)

        val pck2 = SequencedPackageStub(3, 4)
        pck2.id = 2
        pck2.packageNumberInSequence = 2
        keeper.receive(pck2)

        val end = DBSequenceEndPackage(3, 4, 4)
        keeper.receive(end)

        val pck3 = SequencedPackageStub(3, 4)
        pck3.id = 3
        pck3.packageNumberInSequence = 0
        keeper.receive(pck3)

        val pck4 = SequencedPackageStub(3, 4)
        pck4.id = 4
        pck4.packageNumberInSequence = 3
        keeper.receive(pck4)

        assertEquals(4, receivedList.size)
        assertEquals(pck3.id, (receivedList[0] as SequencedPackageStub).id)
        assertEquals(pck1.id, (receivedList[1] as SequencedPackageStub).id)
        assertEquals(pck2.id, (receivedList[2] as SequencedPackageStub).id)
        assertEquals(pck4.id, (receivedList[3] as SequencedPackageStub).id)
    }
}
