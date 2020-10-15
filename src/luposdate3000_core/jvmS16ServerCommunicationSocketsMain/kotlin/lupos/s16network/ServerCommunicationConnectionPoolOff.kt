package lupos.s16network

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import lupos.s00misc.Parallel

object ServerCommunicationConnectionPoolOff {
    val keepAliveServerConnection = false
    fun openServerSocket(hostname: String, port: Int): ServerSocket {
        val server = ServerSocket()
        server.bind(InetSocketAddress(hostname, port))
        return server
    }

    fun accept(server: ServerSocket, action: (ServerCommunicationConnectionPoolHelper) -> Unit) {
        val socket = server.accept()
        Thread = Parallel.runBlocking {
            try {
                action(ServerCommunicationConnectionPoolHelper(socket, BufferedInputStream(socket.getInputStream()), BufferedOutputStream(socket.getOutputStream())))
            } finally {
                socket.close()
            }
        }.start()
    }

    fun openSocket(host: ServerCommunicationKnownHost): ServerCommunicationConnectionPoolHelper {
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
