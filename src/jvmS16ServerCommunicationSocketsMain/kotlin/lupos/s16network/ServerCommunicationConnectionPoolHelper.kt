package lupos.s16network

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.Socket
import kotlinx.coroutines.Job
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.iterator.ColumnIterator

class ServerCommunicationConnectionPoolHelper(val socket: Socket, val input: BufferedInputStream, val output: BufferedOutputStream) {
    val localAddress = socket.localAddress.toString()
    var hadException = false
}

class ServerCommunicationModifyHelper(val conn: ServerCommunicationConnectionPoolHelper, val input: BufferedInputStream, val output: BufferedOutputStream, val iterators: Array<ColumnIterator>) {
    var job: Job? = null
}

class ServerCommunicationImportHelper(val conn: ServerCommunicationConnectionPoolHelper, val input: BufferedInputStream, val output: BufferedOutputStream, val builder: ByteArrayBuilder = ByteArrayBuilder()) {
}

suspend fun BufferedOutputStream.writeByteArray(builder: ByteArrayBuilder) {
    val packet = builder.build()
    var x = packet.size
    write(x and 255)
    x = x shr 8
    write(x and 255)
    x = x shr 8
    write(x and 255)
    x = x shr 8
    write(x and 255)
    write(packet.data, 0, packet.size)
}

suspend fun BufferedInputStream.readByteArray(): ByteArrayRead {
    var size = 0
    size = size + read()
    size = size shl 8
    size = size + read()
    size = size shl 8
    size = size + read()
    size = size shl 8
    size = size + read()
    var res = ByteArray(size)
    val flag = read(res, 0, size)
    if (flag < 0) {
        throw Exception("stream unexpectedly closed")
    }
    return ByteArrayRead(res, size)
}
