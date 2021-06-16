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

import lupos.endpoint.LuposdateEndpoint
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPDistributedSendMulti
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.operator.physical.partition.POPDistributedSendSingleCount
import lupos.shared.EnpointRecievedInvalidPath
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.xmlParser.XMLParser
import lupos.shared_inline.MyInputStream
import lupos.shared_inline.MyOutputStream
import lupos.shared_inline.MyStringStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.URLDecoder
import kotlin.jvm.JvmField
import kotlin.system.exitProcess

@OptIn(ExperimentalStdlibApi::class)
public actual object HttpEndpointLauncher {

    @JvmField
    internal var queryMappings = mutableMapOf<String, QueryMappingContainer>()

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

    public actual /*suspend*/ fun start(instance: Luposdate3000Instance) {

        val hosturl = instance.tripleStoreManager!!.getLocalhost().split(":")
        val hostname = hosturl[0]
        val port = if (hosturl.size > 1) {
            hosturl[1].toInt()
        } else {
            80
        }
        try {
            instance.communicationHandler = CommunicationHandler()
            val server = ServerSocket()
            server.bind(InetSocketAddress("0.0.0.0", port)) // maybe use "::" for ipv6
            println("launched server socket on '0.0.0.0':'$port' - waiting for connections now")
            while (true) {
                val connection = server.accept()
                println("received connection from ${connection.remoteSocketAddress}")
                Thread {
                    var dontCloseSockets: Boolean = false
                    Parallel.runBlocking {
                        val connectionInMy = MyInputStream(connection.getInputStream())
                        val connectionOutMy = MyOutputStream(connection.getOutputStream())
                        try {
                            var line = connectionInMy.readLine()
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
                            println("$hostname:$port path : '$path'")
                            val paths = mutableMapOf<String, PathMappingHelper>()
                            RestEndpoint.initialize(instance, paths, params, connectionInMy, connectionOutMy, hostname, port)

                            paths["/shutdown"] = PathMappingHelper(false, mapOf()) {
                                LuposdateEndpoint.close()
                                exitProcess(0)
                            }
                            paths["/distributed/query/register"] = PathMappingHelper(true, mapOf()) {
                                val xml = XMLParser(MyStringStream(params["query"]!!))
                                val keys = mutableListOf<String>()
                                for (c in xml.childs) {
                                    if (c.tag == "partitionDistributionProvideKey") {
                                        keys.add(c.attributes["key"]!!)
                                    }
                                }
                                val container = QueryMappingContainer(xml, Array(keys.size) { null }, Array(keys.size) { null }, Array(keys.size) { null })
                                for (key in keys) {
                                    queryMappings[key] = container
                                }
                                connectionOutMy.print("HTTP/1.1 200 OK\n\n")
                            }
                            paths["/distributed/query/execute"] = PathMappingHelper(false, mapOf()) {
                                val key = params["key"]!!
                                val queryContainer = queryMappings[key]!!
                                val queryXML = queryContainer.xml
                                val dictionaryURL = params["dictionaryURL"]!!
                                val comm = instance.communicationHandler!!
// calculate current partition
                                var partitionNumber: Int = 0
                                if (queryContainer.inputStreams.size > 1) {
                                    for (k in key.split(":")) {
                                        val s = queryXML.attributes["partitionVariable"] + "="
                                        if (k.startsWith(s)) {
                                            partitionNumber = k.substring(s.length).toInt()
                                            break
                                        }
                                    }
                                }
                                queryContainer.instanceLock.withLock {
                                    queryContainer.outputStreams[partitionNumber] = connectionOutMy
                                    queryContainer.inputStreams[partitionNumber] = connectionInMy
                                    queryContainer.connections[partitionNumber] = connection
                                    var flag = true
                                    for (c in queryContainer.outputStreams) {
                                        if (c == null) {
                                            flag = false
                                            break
                                        }
                                    }
                                    if (flag) {
// only launch if all receivers are started
// init dictionary
                                        val idx2 = dictionaryURL.indexOf("/")
                                        val conn = comm.openConnection(dictionaryURL.substring(0, idx2), "POST " + dictionaryURL.substring(idx2) + "\n\n")
                                        val remoteDictionary = RemoteDictionaryClient(conn.first, conn.second, instance)
                                        val query = Query(remoteDictionary, instance)
                                        query.setDictionaryUrl(dictionaryURL)
// init node
                                        var node = queryContainer.instance
                                        if (node == null) {
                                            node = XMLElementToOPBase(query, queryXML) as POPBase
                                            queryContainer.instance = node
                                        }
                                        query.root = node
// evaluate
                                        when (node) {
                                            is POPDistributedSendSingle -> {
                                                node.evaluate(connectionOutMy)
                                            }
                                            is POPDistributedSendSingleCount -> {
                                                node.evaluate(connectionOutMy)
                                            }
                                            is POPDistributedSendMulti -> {
                                                node.evaluate(queryContainer.outputStreams)
                                            }
                                            else -> throw Exception("unexpected node '${node.classname}'")
                                        }
// release
                                        remoteDictionary.close()
                                        conn.first.close()
                                        conn.second.close()
                                        for (c in queryContainer.outputStreams) {
                                            c!!.close()
                                        }
                                        for (c in queryContainer.inputStreams) {
                                            c!!.close()
                                        }
                                        for (c in queryContainer.connections) {
                                            c!!.close()
                                        }
                                    }
// done
                                }
                                dontCloseSockets = true
                                queryMappings.remove(key)
                            }
                            paths["/distributed/query/list"] = PathMappingHelper(true, mapOf()) {
                                printHeaderSuccess(connectionOutMy)
                                for ((k, v) in queryMappings) {
                                    connectionOutMy.println("<p> $k :: $v </p>")
                                }
                            }

                            paths["/debug.html"] = PathMappingHelper(true, mapOf()) {
                                connectionOutMy.println("HTTP/1.1 200 OK")
                                connectionOutMy.println("Content-Type: text/html; charset=UTF-8")
                                connectionOutMy.println()
                                connectionOutMy.println("<!DOCTYPE html>")
                                connectionOutMy.println("<html lang=\"en\">")
                                connectionOutMy.println("   <head>")
                                connectionOutMy.println("   <title>Luposdate3000</title>")
                                connectionOutMy.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>")
                                connectionOutMy.println("<script>")
                                connectionOutMy.println("   $(document).ready(function() {")
                                for ((k, v) in paths) {
                                    if (k.length > 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOutMy.println("       $('#$formId').on(\"submit\", function(event) {")
                                        connectionOutMy.println("           var formData = {")
                                        for (p in v.params.keys) {
                                            connectionOutMy.println("               '${p.first}': $('#$formId [name=${p.first}]').val(),")
                                        }
                                        connectionOutMy.println("           };")
                                        connectionOutMy.println("           $.ajax({")
                                        connectionOutMy.println("                   type: 'POST',")
                                        connectionOutMy.println("                   url: '${k.substring(1)}',")
                                        connectionOutMy.println("                   data: formData")
                                        connectionOutMy.println("               })")
                                        connectionOutMy.println("               .done(function(data) {")
                                        connectionOutMy.println("                   $('#responseDiv').text(data);")
                                        connectionOutMy.println("               });")
                                        connectionOutMy.println("           event.preventDefault();")
                                        connectionOutMy.println("       });")
                                    }
                                }
                                connectionOutMy.println("   });")
                                connectionOutMy.println("</script>")
                                connectionOutMy.println("   </head>")
                                connectionOutMy.println("   <body>")
                                for ((k, v) in paths) {
                                    if (k.length > 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOutMy.println("<form id=\"$formId\" >")
                                        for ((p, q) in v.params) {
                                            connectionOutMy.println(q(p.first, p.second))
                                        }
                                        connectionOutMy.println("<input type=\"submit\" value=\"$k\" />")
                                        connectionOutMy.println("</form>")
                                    }
                                }
                                connectionOutMy.println("   <div id=\"responseDiv\"></div>")
                                connectionOutMy.println("   </body>")
                                connectionOutMy.println("</html>")
                            }
                            WebRootEndpoint.initialize(paths, connectionOutMy)
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
                                actionHelper.action()
                            }
                        } catch (e: Throwable) {
                            e.printStackTrace()
                            connectionOutMy.println("HTTP/1.1 500 Internal Server Error")
                            connectionOutMy.println()
                        } finally {
                            if (!dontCloseSockets) {
                                connectionOutMy.close()
                                connectionInMy.close()
                                connection?.close()
                            }
                        }
                    }
                }.start()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
