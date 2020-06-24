package lupos.s16network

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage

object ServerCommunicationConnectionPoolOff {
    val keepAliveServerConnection = false
    suspend fun openServerSocket(hostname: String, port: Int): ServerSocket {
        val server = ServerSocket()
        server.bind(InetSocketAddress(hostname, port))
        return server
    }

    suspend fun accept(server: ServerSocket, action: suspend (ServerCommunicationConnectionPoolHelper) -> Unit) {
        val socket = server.accept()
        Thread {
            try {
                runBlocking {
                    action(ServerCommunicationConnectionPoolHelper(socket, BufferedInputStream(socket.getInputStream()), BufferedOutputStream(socket.getOutputStream())))
                }
            } finally {
                socket.close()
            }
        }.start()
    }

    suspend fun openSocket(host: ServerCommunicationKnownHost): ServerCommunicationConnectionPoolHelper {
        val socket = Socket()
        socket.connect(InetSocketAddress(host.hostname, host.port))
        return ServerCommunicationConnectionPoolHelper(socket, BufferedInputStream(socket.getInputStream()), BufferedOutputStream(socket.getOutputStream()))
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
