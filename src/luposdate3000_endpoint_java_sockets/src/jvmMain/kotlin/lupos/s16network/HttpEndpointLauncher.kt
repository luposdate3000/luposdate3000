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
package lupos.s16network

import lupos.jena.JenaWrapper
import lupos.operator.factory.XMLElementToOPBase
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.EnpointRecievedInvalidPath
import lupos.s00misc.File
import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyInputStream
import lupos.s00misc.MyLock
import lupos.s00misc.MyOutputStream
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.MyStringStream
import lupos.s00misc.Parallel
import lupos.s00misc.XMLElement
import lupos.s00misc.communicationHandler
import lupos.s00misc.xmlParser.XMLParser
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.partition.POPDistributedSendMulti
import lupos.s09physicalOperators.partition.POPDistributedSendSingle
import lupos.s11outputResult.EQueryResultToStreamExt
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.URLDecoder

@OptIn(ExperimentalStdlibApi::class)
public actual object HttpEndpointLauncher {
    private fun printHeaderSuccess(stream: MyPrintWriter) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println()
    }

    internal fun extractParamsFromString(str: String, params: MutableMap<String, String>) {
        for (p in str.split('&')) {
            val t = p.split('=')
            if (t.size > 1) {
                params[t[0]] = URLDecoder.decode(t[1])
            }
        }
    }

    internal fun inputElement(name: String, value: String): String = "<input type=\"text\" name=\"$name\" value=\"$value\"/>"

    internal var dictionaryMapping = mutableMapOf<String, RemoteDictionaryServer>()

    internal class QueryMappingContainer(internal val xml: XMLElement, internal var inputStreams: Array<IMyInputStream?>, internal var outputStreams: Array<IMyOutputStream?>) {
        internal var instance: POPBase? = null
        internal val instanceLock = MyLock()
    }

    internal var queryMappings = mutableMapOf<String, QueryMappingContainer>()

    public actual /*suspend*/ fun start() {
        val hosturl = tripleStoreManager.getLocalhost().split(":")
        val hostname = hosturl[0]
        val port = if (hosturl.size > 1) {
            hosturl[1].toInt()
        } else {
            80
        }
        try {
            communicationHandler = CommunicationHandler()
            val server = ServerSocket()
            server.bind(InetSocketAddress("0.0.0.0", port)) // maybe use "::" for ipv6
            println("launched server socket on '0.0.0.0':'$port' - waiting for connections now")
            while (true) {
                val connection = server.accept()
                Thread {
                    var dontCloseSockets: Boolean = false
                    Parallel.runBlocking {
                        var connectionInMy = MyInputStream(connection.getInputStream())
                        var connectionOutBase = connection.getOutputStream()
                        var connectionOutPrinter: MyPrintWriter? = null
                        var connectionOutMy: IMyOutputStream? = null
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
                            paths["/sparql/jenaquery"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE {?s <p> ?o . ?s ?p <o>}") to ::inputElement)) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print(JenaWrapper.execQuery(params["query"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/jenaload"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                JenaWrapper.loadFromFile(params["file"]!!)
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print("success")
                            }
                            paths["/sparql/query"] = PathMappingHelper(
                                true,
                                mapOf(
                                    Pair("query", "SELECT * WHERE {?s ?p ?o . ?s ?p1 <http://localhost/vocabulary/bench/Article> . }") to ::inputElement,
                                    Pair("evaluator", "") to { it, value ->
                                        var res: String = "<select name=\"$it\">"
                                        for (evaluator in EQueryResultToStreamExt.names) {
                                            res += "<option>$evaluator</option>"
                                        }
                                        res + "</select>"
                                    }
                                )
                            ) {
                                val e = params["evaluator"]
                                val evaluator = if (e == null) {
                                    EQueryResultToStreamExt.DEFAULT_STREAM
                                } else {
                                    val e2 = EQueryResultToStreamExt.names.indexOf(e)
                                    if (e2 >= 0) {
                                        e2
                                    } else {
                                        EQueryResultToStreamExt.DEFAULT_STREAM
                                    }
                                }
                                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(params["query"]!!, false)
                                val query = node.getQuery()
                                val dict = RemoteDictionaryServer(query.getDictionary())
                                query.setDictionaryServer(dict)
                                val key = "${query.getTransactionID()}"
                                dictionaryMapping[key] = dict
                                query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutPrinter2, evaluator)
                                dictionaryMapping.remove(key)
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/operator"] = PathMappingHelper(true, mapOf(Pair("query", "") to ::inputElement)) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print(LuposdateEndpoint.evaluateOperatorgraphxmlToResultB(params["query"]!!, true))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/turtle"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                val dict = mutableMapOf<String, Int>()
                                val dictfile = params["bnodeList"]
                                if (dictfile != null) {
                                    File(dictfile).forEachLine {
                                        dict[it] = nodeGlobalDictionary.createNewBNode()
                                    }
                                }
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print(LuposdateEndpoint.importTurtleFiles(params["file"]!!, dict))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/estimatedPartitions"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3.partitions") to ::inputElement)) {
                                LuposdateEndpoint.setEstimatedPartitionsFromFile(params["file"]!!)
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/import/intermediate"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print(LuposdateEndpoint.importIntermediateFiles(params["file"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/xml"] = PathMappingHelper(true, mapOf(Pair("xml", "") to ::inputElement)) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                connectionOutPrinter2.print(LuposdateEndpoint.importXmlData(params["xml"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/distributed/query/register"] = PathMappingHelper(true, mapOf()) {
                                val xml = XMLParser(MyStringStream(params["query"]!!))
                                val keys = mutableListOf<String>()
                                for (c in xml.childs) {
                                    if (c.tag == "partitionDistributionProvideKey") {
                                        keys.add(c.attributes["key"]!!)
                                    }
                                }
                                println("register ... :: $hostname:$port -> $keys")
                                val container = QueryMappingContainer(xml, Array<IMyInputStream?>(keys.size) { null }, Array<IMyOutputStream?>(keys.size) { null })
                                for (key in keys) {
                                    queryMappings[key] = container
                                }
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                connectionOutPrinter2.print("HTTP/1.1 200 OK\n\n")
                            }
                            paths["/distributed/query/dictionary"] = PathMappingHelper(false, mapOf()) {
                                val dict = dictionaryMapping[params["key"]!!]!!
                                val connectionOutMy2 = MyOutputStream(connectionOutBase)
                                connectionOutMy = connectionOutMy2
                                dict.connect(connectionInMy, connectionOutMy2)
                            }
                            paths["/distributed/query/execute"] = PathMappingHelper(false, mapOf()) {
                                println("execute ... :: $hostname:$port -> ${params["key"]}")
                                val key = params["key"]!!
                                val queryContainer = queryMappings[key]!!
                                var queryXML = queryContainer.xml
                                var dictionaryURL = params["dictionaryURL"]!!
                                val connectionOutMy2 = MyOutputStream(connectionOutBase)
                                connectionOutMy = connectionOutMy2
                                if (queryXML == null) {
                                    throw Exception("this query was not registered before")
                                } else {
                                    val comm = communicationHandler
// calculate current partition
                                    var partitionNumber: Int = 0
                                    if (queryContainer.inputStreams.size > 1) {
                                        for (k in key.split(":")) {
                                            val s = queryXML.attributes["partitionVariable"] + "="
                                            if (k.startsWith(s)) {
                                                partitionNumber = k.substring(s.length + 1).toInt()
                                                break
                                            }
                                        }
                                    }
                                    queryContainer.instanceLock.withLock {
                                        queryContainer.outputStreams[partitionNumber] = connectionOutMy
                                        queryContainer.inputStreams[partitionNumber] = connectionInMy
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
                                            var idx = dictionaryURL.indexOf("/")
                                            println("opening dictionary :: '${dictionaryURL.substring(0, idx)}' '${dictionaryURL.substring(idx)}'")
                                            val conn = comm.openConnection(dictionaryURL.substring(0, idx), "POST " + dictionaryURL.substring(idx) + "\n\n")
                                            val remoteDictionary = RemoteDictionaryClient(conn.first, conn.second)
                                            val query = Query(remoteDictionary)
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
                                                is POPDistributedSendSingle -> node.evaluate(connectionOutMy2)
                                                is POPDistributedSendMulti -> node.evaluate(connectionOutMy2, partitionNumber, queryContainer.outputStreams)
                                                else -> throw Exception("unexpected node '${node.classname}'")
                                            }
// release
                                            remoteDictionary.close()
                                            conn.first.close()
                                            conn.second.close()
                                            for (c in queryContainer.outputStreams) {
                                                c!!.flush()
                                                c!!.close()
                                            }
                                            for (c in queryContainer.inputStreams) {
                                                c!!.close()
                                            }
                                        } else {
                                        }
                                    }
// done
                                }
                                dontCloseSockets = true
                                queryMappings.remove(key)
                            }
                            paths["/distributed/query/list"] = PathMappingHelper(true, mapOf()) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                                for ((k, v) in queryMappings) {
                                    connectionOutPrinter2.println("<p> $k :: $v </p>")
                                }
                            }
                            paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement,)) {
                                val name = params["name"]!!
                                val query = Query()
                                tripleStoreManager.remoteCreateGraph(query, name, (params["origin"] == null || params["origin"].toBoolean()), params["metadata"])
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/distributed/graph/commit"] = PathMappingHelper(true, mapOf()) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                println("origin $origin $params")
                                tripleStoreManager.remoteCommit(query, origin)
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/distributed/graph/drop"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                tripleStoreManager.remoteDropGraph(query, params["name"]!!, origin)
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/distributed/graph/clear"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                tripleStoreManager.remoteClearGraph(query, params["name"]!!, origin)
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/distributed/graph/modify"] = PathMappingHelper(false, mapOf()) {
                                val query = Query()
                                val key = params["key"]!!
                                val idx = EIndexPatternExt.names.indexOf(params["idx"]!!)
                                val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
                                tripleStoreManager.remoteModify(query, key, mode, idx, connectionInMy)
                            }
                            paths["/debugGlobalDictionary"] = PathMappingHelper(false, mapOf()) {
                                nodeGlobalDictionary.debugAllDictionaryContent()
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/debugLocalStore"] = PathMappingHelper(false, mapOf()) {
                                tripleStoreManager.debugAllLocalStoreContent()
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                printHeaderSuccess(connectionOutPrinter2)
                            }
                            paths["/index.html"] = PathMappingHelper(true, mapOf()) {
                                val connectionOutPrinter2 = MyPrintWriter(connectionOutBase)
                                connectionOutPrinter = connectionOutPrinter2
                                connectionOutPrinter2.println("HTTP/1.1 200 OK")
                                connectionOutPrinter2.println("Content-Type: text/html; charset=UTF-8")
                                connectionOutPrinter2.println()
                                connectionOutPrinter2.println("<!DOCTYPE html>")
                                connectionOutPrinter2.println("<html lang=\"en\">")
                                connectionOutPrinter2.println("   <head>")
                                connectionOutPrinter2.println("   <title>Luposdate3000</title>")
                                connectionOutPrinter2.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>")
                                connectionOutPrinter2.println("<script>")
                                connectionOutPrinter2.println("   $(document).ready(function() {")
                                for ((k, v) in paths) {
                                    if (k.length > 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOutPrinter2.println("       $('#$formId').on(\"submit\", function(event) {")
                                        connectionOutPrinter2.println("           var formData = {")
                                        for ((p, q) in v.params) {
                                            connectionOutPrinter2.println("               '${p.first}': $('#$formId [name=${p.first}]').val(),")
                                        }
                                        connectionOutPrinter2.println("           };")
                                        connectionOutPrinter2.println("           $.ajax({")
                                        connectionOutPrinter2.println("                   type: 'POST',")
                                        connectionOutPrinter2.println("                   url: '${k.substring(1)}',")
                                        connectionOutPrinter2.println("                   data: formData")
                                        connectionOutPrinter2.println("               })")
                                        connectionOutPrinter2.println("               .done(function(data) {")
                                        connectionOutPrinter2.println("                   $('#responseDiv').text(data);")
                                        connectionOutPrinter2.println("               });")
                                        connectionOutPrinter2.println("           event.preventDefault();")
                                        connectionOutPrinter2.println("       });")
                                    }
                                }
                                connectionOutPrinter2.println("   });")
                                connectionOutPrinter2.println("</script>")
                                connectionOutPrinter2.println("   </head>")
                                connectionOutPrinter2.println("   <body>")
                                for ((k, v) in paths) {
                                    if (k.length > 1) {
                                        val formId = k.replace("/", "_")
                                        connectionOutPrinter2.println("<form id=\"$formId\" >")
                                        for ((p, q) in v.params) {
                                            connectionOutPrinter2.println("${q(p.first, p.second)}")
                                        }
                                        connectionOutPrinter2.println("<input type=\"submit\" value=\"$k\" />")
                                        connectionOutPrinter2.println("</form>")
                                    }
                                }
                                connectionOutPrinter2.println("   <div id=\"responseDiv\"></div>")
                                connectionOutPrinter2.println("   </body>")
                                connectionOutPrinter2.println("</html>")
                            }
                            paths[""] = paths["/index.html"]!!
                            paths["/"] = paths["/index.html"]!!
                            val actionHelper = paths[path]
                            if (actionHelper == null) {
                                throw EnpointRecievedInvalidPath(path)
                            } else {
                                if (actionHelper.addPostParams && isPost) {
                                    val buf = ByteArray(contentLength!!)
                                    connectionInMy.read(buf, contentLength!!)
                                    val content = buf.decodeToString()
                                    extractParamsFromString(content.toString(), params)
                                }
                                actionHelper.action()
                            }
                        } catch (e: Throwable) {
                            System.err.println("start error ...")
                            e.printStackTrace()
                            System.err.println("finish error ...")
                            val connectionOutPrinter2 = connectionOutPrinter
                            if (connectionOutPrinter2 != null) {
                                connectionOutPrinter2.println("HTTP/1.1 500 Internal Server Error")
                                connectionOutPrinter2.println()
                            }
                        } finally {
                            if (!dontCloseSockets) {
                                connectionOutMy?.flush()
                                connectionOutMy?.close()
                                connectionOutPrinter?.flush()
                                connectionOutPrinter?.close()
                                connectionOutBase.close()
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
