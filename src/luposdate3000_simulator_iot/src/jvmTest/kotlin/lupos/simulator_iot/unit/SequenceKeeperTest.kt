/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.unit
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
    ) : SequencedPackage(sourceAddress, destinationAddress)

    internal class SenderStub(private val senderAddress: Int = 0) : ISequencePackageSender {
        val sentList: MutableList<SequencedPackage> = mutableListOf()
        val receivedList: MutableList<SequencedPackage> = mutableListOf()

        override fun send(pck: SequencedPackage) {
            sentList.add(pck)
        }

        override fun receive(pck: SequencedPackage) {
            receivedList.add(pck)
        }

        override fun getSenderAddress(): Int {
            return senderAddress
        }
    }

    @Test
    fun receiveInOrder_SameSequenceAndSameSource() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)

        val pck1 = SequencedPackageStub(3, 4)
        pck1.sequenceNumber = 0
        pck1.packageNumber = 0
        keeper.receive(pck1)
        assertEquals(pck1, sender.receivedList[0])

        val pck2 = SequencedPackageStub(3, 4)
        pck2.sequenceNumber = 0
        pck2.packageNumber = 2
        keeper.receive(pck2)
        assertEquals(1, sender.receivedList.size)

        val pck3 = SequencedPackageStub(3, 4)
        pck3.sequenceNumber = 0
        pck3.packageNumber = 1
        keeper.receive(pck3)
        assertEquals(3, sender.receivedList.size)
        assertEquals(pck3, sender.receivedList[1])
        assertEquals(pck2, sender.receivedList[2])
    }

    @Test
    fun receiveInOrder_SameSequenceAndDifferentSource() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        val src1 = 3
        val src2 = 1

        val pck1 = SequencedPackageStub(src1, 4)
        pck1.sequenceNumber = 0
        pck1.packageNumber = 1
        keeper.receive(pck1)
        assertEquals(0, sender.receivedList.size)

        val pck2 = SequencedPackageStub(src2, 4)
        pck2.sequenceNumber = 0
        pck2.packageNumber = 0
        keeper.receive(pck2)
        assertEquals(1, sender.receivedList.size)
        assertEquals(pck2, sender.receivedList[0])

        val pck3 = SequencedPackageStub(src1, 4)
        pck3.sequenceNumber = 0
        pck3.packageNumber = 0
        keeper.receive(pck3)

        assertEquals(3, sender.receivedList.size)
        assertEquals(pck3, sender.receivedList[1])
        assertEquals(pck1, sender.receivedList[2])
    }

    @Test
    fun receiveInOrder_DifferentSequenceAndSameSource() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        val src1 = 3

        val pck1 = SequencedPackageStub(src1, 4)
        pck1.sequenceNumber = 1
        pck1.packageNumber = 0
        keeper.receive(pck1)
        assertEquals(0, sender.receivedList.size)

        val pck2 = SequencedPackageStub(src1, 4)
        pck2.sequenceNumber = 1
        pck2.packageNumber = 1
        keeper.receive(pck2)
        assertEquals(0, sender.receivedList.size)

        val pck3 = DBSequenceEndPackage(src1, 4)
        pck3.sequenceNumber = 0
        pck3.packageNumber = 1
        keeper.receive(pck3)
        assertEquals(0, sender.receivedList.size)

        val pck4 = SequencedPackageStub(src1, 4)
        pck4.sequenceNumber = 0
        pck4.packageNumber = 0
        keeper.receive(pck4)
        assertEquals(3, sender.receivedList.size)

        assertEquals(pck4, sender.receivedList[0])
        assertEquals(pck1, sender.receivedList[1])
        assertEquals(pck2, sender.receivedList[2])
    }

    @Test
    fun receiveInOrder_DifferentSequenceAndDifferentSource() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        val src1 = 3
        val src2 = 8

        val pck1 = SequencedPackageStub(src1, 4)
        pck1.sequenceNumber = 0
        pck1.packageNumber = 0
        keeper.receive(pck1)
        assertEquals(1, sender.receivedList.size)

        val pck2 = SequencedPackageStub(src2, 4)
        pck2.sequenceNumber = 1
        pck2.packageNumber = 1
        keeper.receive(pck2)
        assertEquals(1, sender.receivedList.size)

        val pck3 = DBSequenceEndPackage(src1, 4)
        pck3.sequenceNumber = 0
        pck3.packageNumber = 1
        keeper.receive(pck3)
        assertEquals(1, sender.receivedList.size)

        val pck4 = DBSequenceEndPackage(src2, 4)
        pck4.sequenceNumber = 0
        pck4.packageNumber = 0
        keeper.receive(pck4)
        assertEquals(1, sender.receivedList.size)

        val pck5 = SequencedPackageStub(src2, 4)
        pck5.sequenceNumber = 1
        pck5.packageNumber = 0
        keeper.receive(pck5)
        assertEquals(3, sender.receivedList.size)

        assertEquals(pck1, sender.receivedList[0])
        assertEquals(pck5, sender.receivedList[1])
        assertEquals(pck2, sender.receivedList[2])
    }

    // Sender
    // ----------------------

    @Test
    fun incrementPckNumberInOneSequence() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.markSequenceEnd()

        assertEquals(3, sender.sentList.size)
        assertEquals(0, sender.sentList[0].packageNumber)
        assertEquals(1, sender.sentList[1].packageNumber)
        assertEquals(2, sender.sentList[2].packageNumber)
    }

    @Test
    fun untilSequenceEndAllPcksHaveSameID() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.markSequenceEnd()
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))

        assertEquals(0, sender.sentList[0].sequenceNumber)
        assertEquals(0, sender.sentList[1].sequenceNumber)
        assertEquals(0, sender.sentList[2].sequenceNumber)
        assertEquals(1, sender.sentList[3].sequenceNumber)
    }

    @Test
    fun incrementSequenceNumberAfterEnd() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)

        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.markSequenceEnd()
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.markSequenceEnd()
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.markSequenceEnd()

        assertEquals(6, sender.sentList.size)
        assertEquals(2, sender.sentList[4].sequenceNumber)
        assertEquals(2, sender.sentList[5].sequenceNumber)
    }

    @Test
    fun everyDestinationStartHasOwnPackageNumbering() {
        val sender = SenderStub()
        val keeper = SequenceKeeper(sender)
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))
        keeper.sendSequencedPackage(SequencedPackageStub(3, 5))
        keeper.sendSequencedPackage(SequencedPackageStub(3, 6))
        keeper.sendSequencedPackage(SequencedPackageStub(3, 4))

        assertEquals(0, sender.sentList[0].packageNumber)
        assertEquals(0, sender.sentList[1].packageNumber)
        assertEquals(0, sender.sentList[2].packageNumber)
        assertEquals(1, sender.sentList[3].packageNumber)
    }
}
