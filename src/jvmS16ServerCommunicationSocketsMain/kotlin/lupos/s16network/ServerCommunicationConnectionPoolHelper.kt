package lupos.s16network

import java.io.*
import java.net.*
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.CommuncationUnexpectedHeaderException
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChannel
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.iterator.RowIteratorBuf
import lupos.s04logicalOperators.iterator.RowIteratorChildIterator
import lupos.s04logicalOperators.iterator.RowIteratorDebug
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

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
