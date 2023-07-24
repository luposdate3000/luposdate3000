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
package lupos.endpoint_launcher
import lupos.dictionary.RemoteDictionaryClient
import lupos.endpoint.PathMappingHelper
import lupos.endpoint.RestEndpoint
import lupos.endpoint.WebRootEndpoint
import lupos.network.wrapper.ServerSocket
import lupos.shared.EnpointRecievedInvalidPath
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.ParallelThread
import lupos.shared.myPrintStackTrace
import lupos.shared.network.ASocket
import lupos.shared.network.InetSocketAddress
import lupos.shared.network.URLDecoder

// import kotlin.system.exitProcess

@OptIn(ExperimentalStdlibApi::class)
public object HttpEndpointLauncher {

    private fun printHeaderSuccess(stream: IMyOutputStream) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println()
    }

    private fun extractParamsFromString(str: String, params: MutableMap<String, String>) {
        for (p in str.split('&')) {
            val t = p.split('=')
            if (t.size > 1) {
                params[t[0]] = URLDecoder.decode(t[1])
            }
        }
    }

    public /*suspend*/ fun start(instance: Luposdate3000Instance) {
        val localhost = instance.tripleStoreManager!!.getLocalhost()
        val hosturl = localhost.split(":")
        val port = if (hosturl.size > 1) {
            hosturl[1].toInt()
        } else {
            80
        }
        try {
            instance.communicationHandler = CommunicationHandler()
            val server = ServerSocket()
            server.bind(InetSocketAddress("0.0.0.0", port)) // maybe use "::" for ipv6
            if (localhost == instance.LUPOS_PROCESS_URLS_ALL[0]) {
                RestEndpoint.registerDictionary(RestEndpoint.key_global_dict, instance.nodeGlobalDictionary!!, instance)
            } else {
                var done = false
                while (!done) {
                    try {
                        val conn = instance.communicationHandler!!.openConnection(instance.LUPOS_PROCESS_URLS_ALL[0], "POST /distributed/query/dictionary?key=${RestEndpoint.key_global_dict}\n\n", -1)
                        instance.nodeGlobalDictionary = RemoteDictionaryClient(conn.first, conn.second, instance, false)
                        done = true
                    } catch (e: Throwable) {
                        ParallelThread.delay(1000)
                    }
                }
            }
            println("waiting for connections now")
            while (true) {
                var connection: ASocket? = null
                connection = server.accept()
                connection!!
                ParallelThread.launch {
                    try {
                        var closeSockets: Boolean = true
                        ParallelThread.runBlocking {
                            val connectionInMy = connection.getInputStream()
                            val connectionOutMy = connection.getOutputStream()
                            try {
                                var line: String? = null
                                line = connectionInMy.readLine()
                                var contentLength: Int? = null
                                var path = ""
                                var isPost = false
                                val params = mutableMapOf<String, String>()
                                while (line != null && line.isNotEmpty()) {
                                    if (line.startsWith("POST")) {
                                        isPost = true
                                        path = line.substring(5)
                                    } else if (line.startsWith("GET")) {
                                        path = line.substring(4)
                                    } else if (line.startsWith("Content-Length: ")) {
                                        contentLength = line.substring("Content-Length: ".length).toInt()
                                    }
                                    line = connectionInMy.readLine()
                                }
                                var idx = path.indexOf(' ')
                                if (idx > 0) {
                                    path = path.substring(0, idx)
                                }
                                idx = path.indexOf('?')
                                if (idx > 0) {
                                    extractParamsFromString(path.substring(idx + 1), params)
                                    path = path.substring(0, idx)
                                }
                                val paths = mutableMapOf<String, PathMappingHelper>()
                                /*paths["/shutdown"] = PathMappingHelper(false, mapOf()) { _, _, _ ->
                                    RestEndpoint.removeDictionary(RestEndpoint.key_global_dict)
                                    LuposdateEndpoint.close()
                                    exitProcess(0)
                                }*/
                                RestEndpoint.initialize(instance, paths)
                                WebRootEndpoint.initialize(paths)
                                val tmpRoot = paths["/index.html"]
                                if (tmpRoot != null) {
                                    paths[""] = tmpRoot
                                    paths["/"] = tmpRoot
                                } else {
                                    val tmpRoot2 = paths["/debug.html"]
                                    paths[""] = tmpRoot2!!
                                    paths["/"] = tmpRoot2
                                }
                                val actionHelper = paths[path]
                                if (actionHelper == null) {
                                    throw EnpointRecievedInvalidPath(path)
                                } else {
                                    if (actionHelper.addPostParams && isPost) {
                                        val buf = ByteArray(contentLength!!)
                                        connectionInMy.read(buf, contentLength)
                                        val content = buf.decodeToString()
                                        extractParamsFromString(content, params)
                                    }
                                    closeSockets = actionHelper.action(params, connectionInMy, connectionOutMy)
                                }
                            } catch (e: Throwable) {
                                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint_launcher/src/commonMain/kotlin/lupos/endpoint_launcher/HttpEndpointLauncher.kt:145"/*SOURCE_FILE_END*/)
                                connectionOutMy.println("HTTP/1.1 500 Internal Server Error")
                                connectionOutMy.println()
                                connectionOutMy.println(e.stackTraceToString())
                            } finally {
                                if (closeSockets) {
                                    connectionOutMy.close()
                                    connectionInMy.close()
                                    connection?.close()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint_launcher/src/commonMain/kotlin/lupos/endpoint_launcher/HttpEndpointLauncher.kt:158"/*SOURCE_FILE_END*/)
                    }
                }
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint_launcher/src/commonMain/kotlin/lupos/endpoint_launcher/HttpEndpointLauncher.kt:163"/*SOURCE_FILE_END*/)
        }
    }
}
