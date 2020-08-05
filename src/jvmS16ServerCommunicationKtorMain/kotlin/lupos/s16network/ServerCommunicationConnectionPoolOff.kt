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

object ServerCommunicationConnectionPoolOff {
    val keepAliveServerConnection = false
    fun openServerSocket(hostname: String, port: Int): ServerSocket {
        return aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().bind(InetSocketAddress(hostname, port))
    }

    fun accept(server: ServerSocket, action: (ServerCommunicationConnectionPoolHelper) -> Unit) {
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

    fun openSocket(host: ServerCommunicationKnownHost): ServerCommunicationConnectionPoolHelper {
        val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host.hostname, host.port))
        return ServerCommunicationConnectionPoolHelper(socket, socket.openReadChannel(), socket.openWriteChannel())
    }

    fun closeSocketClean(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        if (!conn.hadException) {
            conn.socket.close()
        }
    }

    fun closeSocketException(host: ServerCommunicationKnownHost, conn: ServerCommunicationConnectionPoolHelper) {
        conn.hadException = true
        conn.socket.close()
    }
}
