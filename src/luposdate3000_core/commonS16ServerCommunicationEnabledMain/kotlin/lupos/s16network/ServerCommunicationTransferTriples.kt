package lupos.s16network

import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.TripleStoreBulkImport

object ServerCommunicationTransferTriples {
    fun receiveTriples(packet: ByteArrayRead, dict: ResultSetDictionary, remoteName: String, bulk: TripleStoreBulkImport) {
/*always assume SPO*/
        val columns = packet.readInt()
        SanityCheck.check { columns == 3 }
        while (packet.remaining() > 0) {
            var si = packet.readInt()
            var pi = packet.readInt()
            var oi = packet.readInt()
            bulk.insert(si, pi, oi)
        }
    }

    fun receiveTriples(packet: ByteArrayRead, dict: ResultSetDictionary, expectedColumns: Int, outputAsSingle: Boolean, remoteName: String): Array<MutableList<Int>> {
/*always assume SPO even _if some of the components are allowed to be missing*/
        val columns = packet.readInt()
        SanityCheck.check { columns == expectedColumns }
        SanityCheck.check { columns > 0 }
        var res: Array<MutableList<Int>>
        if (outputAsSingle) {
            res = Array(1) { mutableListOf<Int>() }
        } else {
            res = Array(columns) { mutableListOf<Int>() }
        }
        while (packet.remaining() > 0) {
            for (i in 0 until columns) {
                val v = packet.readInt()
                if (outputAsSingle) {
                    res[0].add(v)
                } else {
                    res[i].add(v)
                }
            }
        }
        return res
    }

    fun sendTriples(bundle: IteratorBundle, dict: ResultSetDictionary, params: Array<AOPBase>, onFullPacket: (ByteArrayBuilder) -> Unit) {
/*always assume SPO*/
        var iterators = mutableListOf<ColumnIterator>()
        for (p in params) {
            if (p is AOPVariable && p.name != "_") {
                iterators.add(bundle.columns[p.name]!!)
            }
        }
        sendTriples(iterators.toTypedArray(), dict, onFullPacket)
    }

    fun sendTriples(iterators: Array<ColumnIterator>, dict: ResultSetDictionary, onFullPacket: (ByteArrayBuilder) -> Unit) {
        /*always assume SPO - works with less than 3 columns too*/
        try {
            var builder = ByteArrayBuilder()
            loop@ while (true) {
                builder.writeInt(ServerCommunicationHeader.RESPONSE_TRIPLES.ordinal)
                builder.writeInt(iterators.size)
                var counter = 0
                while (builder.size < NETWORK_PACKET_SIZE || counter < NETWORK_PACKET_MIN_TRIPLES) {
                    counter++
                    for (i in 0 until iterators.size) {
                        val it = iterators[i]
                        val v = it.next()
                        if (v == null) {
                            SanityCheck.check { i == 0 }
                            for (closeIndex in 0 until iterators.size) {
                                iterators[closeIndex].close()
                            }
                            break@loop
                        } else {
                            builder.writeInt(v)
                        }
                    }
                }
                onFullPacket(builder)
                builder.reset()
            }
            onFullPacket(builder)
        } finally {
            for (i in 0 until iterators.size) {
                iterators[i].close()
            }
        }
    }

    fun sendTriples(si: Int, pi: Int, oi: Int, dict: ResultSetDictionary, builder: ByteArrayBuilder, onFullPacket: (ByteArrayBuilder) -> Unit) {
        /*always assume SPO requires exactly 3 columns*/
        if (builder.size >= NETWORK_PACKET_SIZE) {
            onFullPacket(builder)
            builder.reset()
        }
        if (builder.size == 0) {
            builder.writeInt(ServerCommunicationHeader.RESPONSE_TRIPLES.ordinal)
            builder.writeInt(3)
        }
        builder.writeInt(si)
        builder.writeInt(pi)
        builder.writeInt(oi)
    }
}
