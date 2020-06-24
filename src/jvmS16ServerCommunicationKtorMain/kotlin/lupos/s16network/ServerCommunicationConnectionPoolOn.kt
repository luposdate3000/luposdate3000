package lupos.s16network

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.network.sockets.ServerSocket
import io.ktor.network.sockets.Socket
import java.net.InetSocketAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage

object ServerCommunicationConnectionPoolOn {
    val keepAliveServerConnection = true
    val cache = mutableMapOf<ServerCommunicationKnownHost, MutableList<ServerCommunicationConnectionPoolHelper>>()
    suspend fun openServerSocket(hostname: String, port: Int): ServerSocket {
        return aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().bind(InetSocketAddress(hostname, port))
    }

    suspend fun accept(server: ServerSocket, action: suspend (ServerCommunicationConnectionPoolHelper) -> Unit) {
        val socket = server.accept()
        GlobalScope.launch {
            try {
                runBlocking {
                    action(ServerCommunicationConnectionPoolHelper(socket, socket.openReadChannel(), socket.openWriteChannel()))
                }
            } finally {
                socket.close()
            }
        }
    }

    suspend fun openSocket(host: ServerCommunicationKnownHost): ServerCommunicationConnectionPoolHelper {
        val tmp = cache[host]
        if (tmp != null && !tmp.isEmpty()) {
            return tmp.removeAt(0)
        }
        val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
        return ServerCommunicationConnectionPoolHelper(socket, socket.openReadChannel(), socket.openWriteChannel())
    }

    fun closeSocketClean(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        if (!conn.hadException) {
            val tmp = cache[host]
            if (tmp == null) {
                cache[host] = mutableListOf(conn)
            } else {
                tmp.add(conn)
            }
        }
    }

    fun closeSocketException(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        conn.hadException = true
        conn.socket.close()
    }
}
