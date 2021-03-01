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
import lupos.s00misc.MyStringStream
import lupos.s00misc.Parallel
import lupos.s00misc.XMLElement
import lupos.s00misc.communicationHandler
import lupos.s00misc.xmlParser.XMLParser
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.partition.POPDistributedSendMulti
import lupos.s09physicalOperators.partition.POPDistributedSendSingle
import lupos.s11outputResult.EQueryResultToStreamExt
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder

@OptIn(ExperimentalStdlibApi::class)
public actual object HttpEndpointLauncher {
    private fun printHeaderSuccess(stream: IMyOutputStream) {
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

    internal class QueryMappingContainer(internal val xml: XMLElement, internal var inputStreams: Array<IMyInputStream?>, internal var outputStreams: Array<IMyOutputStream?>, internal var connections: Array<Socket?>) {
        internal var instance: POPBase? = null
        internal val instanceLock = MyLock()
    }

    internal var queryMappings = mutableMapOf<String, QueryMappingContainer>()

    public actual /*suspend*/ fun start() {
        fun registerDictionary(key: String): RemoteDictionaryServer {
            val dict = RemoteDictionaryServer(ResultSetDictionary())
            dictionaryMapping[key] = dict
            return dict
        }

        fun removeDictionary(key: String) {
            dictionaryMapping.remove(key)
        }

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
                        var connectionOutMy = MyOutputStream(connection.getOutputStream())
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
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(JenaWrapper.execQuery(params["query"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/jenaload"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                JenaWrapper.loadFromFile(params["file"]!!)
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print("success")
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
                                val key = "${query.getTransactionID()}"
                                val dict = registerDictionary(key)
                                query.setDictionaryServer(dict)
                                query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
                                LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
                                removeDictionary(key)
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/operator"] = PathMappingHelper(true, mapOf(Pair("query", "") to ::inputElement)) {
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(LuposdateEndpoint.evaluateOperatorgraphxmlToResultB(params["query"]!!, true))
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
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(LuposdateEndpoint.importTurtleFiles(params["file"]!!, dict))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/estimatedPartitions"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3.partitions") to ::inputElement)) {
                                LuposdateEndpoint.setEstimatedPartitionsFromFile(params["file"]!!)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/import/intermediate"] = PathMappingHelper(true, mapOf(Pair("file", "/mnt/luposdate-testdata/sp2b/1024/complete.n3") to ::inputElement)) {
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(LuposdateEndpoint.importIntermediateFiles(params["file"]!!))
                                /*Coverage Unreachable*/
                            }
                            paths["/import/xml"] = PathMappingHelper(true, mapOf(Pair("xml", "") to ::inputElement)) {
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(LuposdateEndpoint.importXmlData(params["xml"]!!))
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
                                val container = QueryMappingContainer(xml, Array<IMyInputStream?>(keys.size) { null }, Array<IMyOutputStream?>(keys.size) { null }, Array<Socket?>(keys.size) { null })
                                for (key in keys) {
                                    queryMappings[key] = container
                                }
                                connectionOutMy.print("HTTP/1.1 200 OK\n\n")
                            }
                            paths["/distributed/query/dictionary"] = PathMappingHelper(false, mapOf()) {
                                val dict = dictionaryMapping[params["key"]!!]!!
                                dict.connect(connectionInMy, connectionOutMy)
                            }
                            paths["/distributed/query/dictionary/register"] = PathMappingHelper(false, mapOf()) {
                                registerDictionary(params["key"]!!)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/query/dictionary/remove"] = PathMappingHelper(false, mapOf()) {
                                removeDictionary(params["key"]!!)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/query/execute"] = PathMappingHelper(false, mapOf()) {
                                println("execute ... :: $hostname:$port -> ${params["key"]}")
                                val key = params["key"]!!
                                val queryContainer = queryMappings[key]!!
                                var queryXML = queryContainer.xml
                                var dictionaryURL = params["dictionaryURL"]!!
                                if (queryXML == null) {
                                    throw Exception("this query was not registered before")
                                } else {
                                    println("execute a")
                                    val comm = communicationHandler
// calculate current partition
                                    var partitionNumber: Int = 0
                                    if (queryContainer.inputStreams.size > 1) {
                                        println("execute b")
                                        for (k in key.split(":")) {
                                            val s = queryXML.attributes["partitionVariable"] + "="
                                            if (k.startsWith(s)) {
                                                println("'$k' :: '$s' => '${k.substring(s.length)}'")
                                                partitionNumber = k.substring(s.length).toInt()
                                                break
                                            }
                                        }
                                    }
                                    println("execute c")
                                    queryContainer.instanceLock.withLock {
                                        println("execute d")
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
                                        println("${queryContainer.outputStreams.map { it != null }} $queryXML")
                                        if (flag) {
                                            println("execute e")
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
                                                println("execute f")
                                                node = XMLElementToOPBase(query, queryXML) as POPBase
                                                queryContainer.instance = node
                                            }
                                            query.root = node
// evaluate
                                            when (node) {
                                                is POPDistributedSendSingle -> {
                                                    println("execute g")
                                                    node.evaluate(connectionOutMy)
                                                }
                                                is POPDistributedSendMulti -> {
                                                    println("execute h")
                                                    node.evaluate(queryContainer.outputStreams)
                                                }
                                                else -> throw Exception("unexpected node '${node.classname}'")
                                            }
                                            println("execute i")
// release
                                            println("execute j")
                                            remoteDictionary.close()
                                            println("execute k")
                                            conn.first.close()
                                            println("execute l")
                                            conn.second.close()
                                            println("execute m")
                                            for (c in queryContainer.outputStreams) {
                                                c!!.close()
                                            }
                                            for (c in queryContainer.inputStreams) {
                                                c!!.close()
                                            }
                                            for (c in queryContainer.connections) {
                                                c!!.close()
                                            }
                                            println("execute n")
                                        }
                                        println("execute o")
                                    }
                                    println("execute p")
// done
                                }
                                println("execute q")
                                dontCloseSockets = true
                                queryMappings.remove(key)
                            }
                            paths["/distributed/query/list"] = PathMappingHelper(true, mapOf()) {
                                printHeaderSuccess(connectionOutMy)
                                for ((k, v) in queryMappings) {
                                    connectionOutMy.println("<p> $k :: $v </p>")
                                }
                            }
                            paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement,)) {
                                val name = params["name"]!!
                                val query = Query()
                                tripleStoreManager.remoteCreateGraph(query, name, (params["origin"] == null || params["origin"].toBoolean()), params["metadata"])
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/graph/commit"] = PathMappingHelper(true, mapOf()) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                println("origin $origin $params")
                                tripleStoreManager.remoteCommit(query, origin)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/graph/drop"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                tripleStoreManager.remoteDropGraph(query, params["name"]!!, origin)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/graph/clear"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
                                tripleStoreManager.remoteClearGraph(query, params["name"]!!, origin)
                                printHeaderSuccess(connectionOutMy)
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
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/debugLocalStore"] = PathMappingHelper(false, mapOf()) {
                                tripleStoreManager.debugAllLocalStoreContent()
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/index.html"] = PathMappingHelper(true, mapOf()) {
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
                                        for ((p, q) in v.params) {
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
                                            connectionOutMy.println("${q(p.first, p.second)}")
                                        }
                                        connectionOutMy.println("<input type=\"submit\" value=\"$k\" />")
                                        connectionOutMy.println("</form>")
                                    }
                                }
                                connectionOutMy.println("   <div id=\"responseDiv\"></div>")
                                connectionOutMy.println("   </body>")
                                connectionOutMy.println("</html>")
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
                            val connectionOutMy = connectionOutMy
                            if (connectionOutMy != null) {
                                connectionOutMy.println("HTTP/1.1 500 Internal Server Error")
                                connectionOutMy.println()
                            }
                        } finally {
                            if (!dontCloseSockets) {
                                connectionOutMy?.close()
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
