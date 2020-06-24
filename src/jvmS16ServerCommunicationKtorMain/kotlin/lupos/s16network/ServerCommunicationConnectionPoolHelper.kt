package lupos.s16network

import io.ktor.network.sockets.Socket
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlinx.coroutines.Job
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s04logicalOperators.iterator.ColumnIterator

class ServerCommunicationConnectionPoolHelper(val socket: Socket, val input: ByteReadChannel, val output: ByteWriteChannel) {
    val localAddress = socket.localAddress.toString()
    var hadException = false
}

class ServerCommunicationModifyHelper(val conn: ServerCommunicationConnectionPoolHelper, val input: ByteReadChannel, val output: ByteWriteChannel, val iterators: Array<ColumnIterator>) {
    var job: Job? = null
}

class ServerCommunicationImportHelper(val conn: ServerCommunicationConnectionPoolHelper, val input: ByteReadChannel, val output: ByteWriteChannel, val builder: ByteArrayBuilder = ByteArrayBuilder()) {
}

suspend fun ByteWriteChannel.writeByteArray(builder: ByteArrayBuilder) {
    val packet = builder.build()
    writeInt(packet.size)
    writeFully(packet.data, 0, packet.size)
}

suspend fun ByteReadChannel.readByteArray(): ByteArrayRead {
    var size = readInt()
    var res = ByteArray(size)
    readFully(res, 0, size)
    return ByteArrayRead(res, size)
}
