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

import lupos.dictionary.DictionaryFactory
import lupos.endpoint.LuposdateEndpoint
import lupos.jena.JenaWrapper
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPDistributedSendMulti
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.operator.physical.partition.POPDistributedSendSingleCount
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EnpointRecievedInvalidPath
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.MyLock
import lupos.shared.Parallel
import lupos.shared.XMLElement
import lupos.shared.XMLElementFromXML
import lupos.shared.communicationHandler
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dictionary.nodeGlobalDictionary
import lupos.shared.tripleStoreManager
import lupos.shared.xmlParser.XMLParser
import lupos.shared_inline.File
import lupos.shared_inline.MyInputStream
import lupos.shared_inline.MyOutputStream
import lupos.shared_inline.MyStringStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import kotlin.jvm.JvmField

@OptIn(ExperimentalStdlibApi::class)
public actual object HttpEndpointLauncher {
    @JvmField
    internal var dictionaryMapping = mutableMapOf<String, RemoteDictionaryServer>()

    @JvmField
    internal var queryMappings = mutableMapOf<String, QueryMappingContainer>()

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
    internal fun selectElementEQueryResultToStreamExt(name: String, value: String): String {
        var res = "<select name=\"$name\">"
        for (evaluator in EQueryResultToStreamExt.names) {
            if (value == evaluator) {
                res += "<option selected=\"selected\">$evaluator</option>"
            } else {
                res += "<option>$evaluator</option>"
            }
        }
        res + "</select>"
        return res
    }

    internal class QueryMappingContainer(@JvmField internal val xml: XMLElement, @JvmField internal var inputStreams: Array<IMyInputStream?>, @JvmField internal var outputStreams: Array<IMyOutputStream?>, @JvmField internal var connections: Array<Socket?>) {
        @JvmField
        internal var instance: POPBase? = null

        @JvmField
        internal val instanceLock = MyLock()
    }

    private inline fun registerDictionary(key: String): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true))
        dictionaryMapping[key] = dict
        return dict
    }

    private inline fun registerDictionary(key: String, dict: IDictionary): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(dict)
        dictionaryMapping[key] = dict
        return dict
    }

    private inline fun removeDictionary(key: String) {
        dictionaryMapping.remove(key)
    }

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
                println("received connection from ${connection.getRemoteSocketAddress()}")
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
                            paths["/sparql/jenaquery"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement)) {
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
                                    Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement,
                                    Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt,
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
                                println("choosen ${EQueryResultToStreamExt.names[evaluator]} ${EQueryResultToStreamExt.names.map { it }}")
                                val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(params["query"]!!, false)
                                println(node.toXMLElementRoot(false).toPrettyString())
                                val query = node.getQuery()
                                val key = "${query.getTransactionID()}"
                                val dict = registerDictionary(key, query.getDictionary())
                                query.setDictionaryServer(dict)
                                query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
                                printHeaderSuccess(connectionOutMy)
                                LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
                                removeDictionary(key)
                                /*Coverage Unreachable*/
                            }
                            paths["/sparql/operator"] =
                                PathMappingHelper(
                                    true,
                                    mapOf(
                                        Pair("query", "") to ::inputElement,
                                        Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt,
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
                                    val query = Query()
                                    val node = XMLElementToOPBase(query, XMLElementFromXML()(params["query"]!!)!!)
                                    val key = "${query.getTransactionID()}"
                                    val dict = registerDictionary(key, query.getDictionary())
                                    query.setDictionaryServer(dict)
                                    query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
                                    printHeaderSuccess(connectionOutMy)
                                    LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
                                    removeDictionary(key)
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
                            paths["/import/turtledata"] = PathMappingHelper(true, mapOf(Pair("data", "<s> <p> <o> .") to ::inputElement)) {
                                val dict = mutableMapOf<String, Int>()
                                val dictfile = params["bnodeList"]
                                if (dictfile != null) {
                                    File(dictfile).forEachLine {
                                        dict[it] = nodeGlobalDictionary.createNewBNode()
                                    }
                                }
                                printHeaderSuccess(connectionOutMy)
                                connectionOutMy.print(LuposdateEndpoint.importTurtleString(params["data"]!!, dict))
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
                            paths["/shutdown"] = PathMappingHelper(false, mapOf()) {
                                LuposdateEndpoint.close()
                                System.exit(0)
                            }
                            paths["/distributed/query/dictionary"] = PathMappingHelper(false, mapOf()) {
                                val dict = dictionaryMapping[params["key"]!!]!!
                                dict.connect(connectionInMy, connectionOutMy)
                            }
                            paths["/distributed/query/dictionary/register"] = PathMappingHelper(true, mapOf()) {
                                registerDictionary(params["key"]!!)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/query/dictionary/remove"] = PathMappingHelper(true, mapOf()) {
                                removeDictionary(params["key"]!!)
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/query/execute"] = PathMappingHelper(false, mapOf()) {
                                println("execute ... :: $hostname:$port -> ${params["key"]}")
                                val key = params["key"]!!
                                val queryContainer = queryMappings[key]!!
                                var queryXML = queryContainer.xml
                                var dictionaryURL = params["dictionaryURL"]!!
                                val comm = communicationHandler
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
                                        var idx2 = dictionaryURL.indexOf("/")
                                        val conn = comm.openConnection(dictionaryURL.substring(0, idx2), "POST " + dictionaryURL.substring(idx2) + "\n\n")
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
                            paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement, )) {
                                val name = params["name"]!!
                                val query = Query()
                                tripleStoreManager.remoteCreateGraph(query, name, (params["origin"] == null || params["origin"].toBoolean()), params["metadata"])
                                printHeaderSuccess(connectionOutMy)
                            }
                            paths["/distributed/graph/commit"] = PathMappingHelper(true, mapOf()) {
                                val query = Query()
                                val origin = params["origin"] == null || params["origin"]!!.toBoolean()
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
                                val idx2 = EIndexPatternExt.names.indexOf(params["idx"]!!)
                                val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
                                tripleStoreManager.remoteModify(query, key, mode, idx2, connectionInMy)
                            }
                            paths["/distributed/graph/modifysorted"] = PathMappingHelper(false, mapOf()) {
                                val query = Query()
                                val key = params["key"]!!
                                val idx2 = EIndexPatternExt.names.indexOf(params["idx"]!!)
                                val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
                                tripleStoreManager.remoteModifySorted(query, key, mode, idx2, connectionInMy)
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
                                    connectionInMy.read(buf, contentLength)
                                    val content = buf.decodeToString()
                                    extractParamsFromString(content.toString(), params)
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
