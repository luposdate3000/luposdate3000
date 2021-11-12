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
package lupos.endpoint

import lupos.dictionary.DictionaryCache
import lupos.dictionary.DictionaryFactory
import lupos.dictionary.RemoteDictionaryClient
import lupos.dictionary.RemoteDictionaryServer
import lupos.jena_wrapper.JenaWrapper
import lupos.operator.base.Query
import lupos.operator.factory.BinaryToOPBase
import lupos.operator.factory.ConverterBinaryToIteratorBundle
import lupos.operator.factory.XMLElementToOPBase
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.XMLElementFromXML
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public object RestEndpoint {
    public const val key_global_dict: String = "global_dict"

    @JvmField
    internal var queryMappings = mutableMapOf<String, QueryMappingContainer>()

    @JvmField
    internal var dictionaryMapping = mutableMapOf<String, RemoteDictionaryServer>()
    private var sessionMap = mutableMapOf<Int, EndpointExtendedVisualize>()
    internal var localOperatorMap = ConverterBinaryToIteratorBundle.defaultOperatorMap
    public fun registerDictionary(key: String, instance: Luposdate3000Instance): IDictionary {
        return registerDictionary(key, DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true, instance), instance)
    }

    public fun registerDictionary(key: String, dict: IDictionary, instance: Luposdate3000Instance): IDictionary {
        val dict2 = RemoteDictionaryServer(dict, instance)
        dictionaryMapping[key] = dict2
        return dict2
    }

    public fun removeDictionary(key: String) {
        dictionaryMapping.remove(key)
    }

    public fun distributed_graph_create(params: Map<String, String>, instance: Luposdate3000Instance) {
        val name = params["name"]!!
        val query = Query(instance)
        instance.tripleStoreManager!!.remoteCreateGraph(query, name, (params["origin"] == null || params["origin"].toBoolean()), params["metadata"])
    }

    private fun printHeaderSuccess(stream: IMyOutputStream) {
        stream.println("HTTP/1.1 200 OK")
        stream.println("Content-Type: text/plain")
        stream.println()
    }

    private fun inputElement(name: String, value: String): String = "<input type=\"text\" name=\"$name\" value=\"$value\"/>"
    private fun selectElementEQueryResultToStreamExt(name: String, value: String): String {
        var res = "<select name=\"$name\">"
        for (evaluator in EQueryResultToStreamExt.names) {
            res += if (value == evaluator) {
                "<option selected=\"selected\">$evaluator</option>"
            } else {
                "<option>$evaluator</option>"
            }
        }
        res += "</select>"
        return res
    }

    public fun initialize(instance: Luposdate3000Instance, paths: MutableMap<String, PathMappingHelper>) {
        paths["/sparql/jenaquery"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement)) { params, _, connectionOutMy ->
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(JenaWrapper.execQuery(params["query"]!!))
            true
        }
        paths["/sparql/jenaload"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3") to ::inputElement)) { params, _, connectionOutMy ->
            JenaWrapper.loadFromFile(params["file"]!!)
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("success")
            true
        }
        paths["/sparql/startSession"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) { params, _, connectionOutMy ->
            val eev = EndpointExtendedVisualize(params["query"].toString(), instance)
            val key = sessionMap.size + 1
            sessionMap[key] = eev
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(key.toString())
            true
        }
        paths["/shacl/ontology/import"] = PathMappingHelper(true, mapOf(Pair("data", "") to ::inputElement)) { params, _, connectionOutMy ->
            if (instance.LUPOS_PROCESS_ID == 0) {
                LuposdateEndpoint.loadShaclOntology(instance, params["data"]!!)
            } else {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/shacl/ontology/import", params, -1)
            }
            true
        }
        paths["/shacl/ontology/load"] = PathMappingHelper(true, mapOf()) { params, connectionInMy, connectionOutMy ->
            val data = ByteArrayWrapper()
            val cache2 = instance.nodeGlobalOntologyCache
            val cache = if (cache2 == null) {
                val c = DictionaryCache(0)
                instance.nodeGlobalOntologyCache = c
                c
            } else {
                cache2
            }
            while (true) {
                var id = connectionInMy.readDictionaryValueType()
                if (id == DictionaryValueHelper.nullValue) {
                    break
                }
                val len = connectionInMy.readInt()
                ByteArrayWrapperExt.setSize(data, len, false)
                connectionInMy.read(ByteArrayWrapperExt.getBuf(data), len)
                cache.insertValuePairExtend(data, id)
            }
            true
        }
        paths["/sparql/getLogicalVisual"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) { params, _, connectionOutMy ->
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print("[")
                val steps = eev.getOptimizedStepsLogical()
                for (i in 0 until steps.size - 1) {
                    val step = steps[i]
                    connectionOutMy.print(step.toJson())
                    connectionOutMy.print(",")
                }
                if (steps.isNotEmpty()) {
                    connectionOutMy.print(steps[steps.size - 1].toJson())
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            true
        }
        paths["/sparql/getPhysicalVisual"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) { params, _, connectionOutMy ->
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print("[")
                val steps = eev.getOptimizedStepsPhysical()
                for (i in 0 until steps.size - 1) {
                    val step = steps[i]
                    connectionOutMy.print(step.toJson())
                    connectionOutMy.print(",")
                }
                if (steps.isNotEmpty()) {
                    connectionOutMy.print(steps[steps.size - 1].toJson())
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            true
        }
        paths["/sparql/getResult"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) { params, _, connectionOutMy ->
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print(eev.getResult())
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            true
        }
        paths["/sparql/getVisualisationData"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) { params, _, connectionOutMy ->
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                val tmp = eev.getDataSteps()
                connectionOutMy.print("[")
                for (i in 0 until tmp.size - 1) {
                    val j = tmp[i]
                    connectionOutMy.print(j)
                    connectionOutMy.print(",")
                }
                if (tmp.isNotEmpty()) {
                    connectionOutMy.print(tmp[tmp.size - 1])
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            true
        }
        paths["/sparql/closeSession"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) { params, _, connectionOutMy ->
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("SessionClosedACK")
            sessionMap.remove(params["sessionID"]!!.toInt())
            true
        }
        paths["/sparql/query"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) { params, _, connectionOutMy ->
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
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, params["query"]!!, false)
            val binaryAndMeta = BinaryToOPBase.convertToByteArrayAndMeta(node, true, true)
            val binary = binaryAndMeta.first
            val meta = binaryAndMeta.second
            val query = node.getQuery() as Query
            for ((k, v) in meta.idToOffset) {
                val v = meta.idToHost[k]
                meta.idToHost[k] = mutableSetOf(
                    if (v == null) {
                        query.getInstance().LUPOS_PROCESS_URLS_STORE[0]
                    } else {
                        v.first()
                    }
                )
            }
            val handler = instance.communicationHandler!!
            for ((id, hosts) in meta.idToHost) {
                val host = hosts.first()
                val keys = mutableSetOf<Int>()
                for (parentID in meta.getParentsForID(id)) {
                    keys.add(meta.dependenciesForID[parentID]!![id]!!)
                }
                val conn = handler.openConnection(
                    host,
                    "/distributed/query/register",
                    mapOf(
                        "dictionaryURL" to query.getDictionaryUrl()!!
                    ),
                    query.getTransactionID().toInt()
                )
                val bin = BinaryToOPBase.copyByteArray(query, binary, intArrayOf(id))
                val deps = mutableMapOf<Int, String>() // key->host
                for ((childID, key) in meta.dependenciesForID[id]!!) {
                    val h = meta.idToHost[childID]!!.first()
                    deps[key] = h
                }
                conn.second.writeInt(query.getTransactionID().toInt())
                conn.second.writeInt(keys.size)
                for (k in keys) {
                    conn.second.writeInt(k)
                }

                conn.second.writeInt(id)
                conn.second.writeInt(deps.size)
                for ((k, v) in deps) {
                    conn.second.writeInt(k)
                    val b = v.encodeToByteArray()
                    conn.second.writeInt(b.size)
                    conn.second.write(b)
                }
                conn.second.writeInt(ByteArrayWrapperExt.getSize(bin))
                conn.second.write(ByteArrayWrapperExt.getBuf(bin), ByteArrayWrapperExt.getSize(bin))
                conn.second.close()
                conn.first.close()
            }
            printHeaderSuccess(connectionOutMy)
            val iter = BinaryToOPBase.convertToIteratorBundle(query, binary, -1, localOperatorMap)
            LuposdateEndpoint.evaluateIteratorBundleToResultA(instance, iter, connectionOutMy, evaluator)
            true
        }
        paths["/sparql/benchmark"] = PathMappingHelper(true, mapOf(Pair("minimumTime", "10") to ::inputElement, Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) { params, _, connectionOutMy ->
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
            val e3 = params["minimumTime"]
            val minimumTime = try {
                e3!!.toDouble()
            } catch (e: Throwable) {
                10.0
            }
            val writer = MyPrintWriter(false)
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, params["query"]!!, false)
            printHeaderSuccess(connectionOutMy)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, writer, evaluator)
            val timer = DateHelperRelative.markNow()
            var time: Double
            var counter = 0
            while (true) {
                counter++
                LuposdateEndpoint.evaluateOperatorgraphToResultB(instance, node, writer)
                time = DateHelperRelative.elapsedSeconds(timer)
                if (time > minimumTime) {
                    break
                }
            }
            connectionOutMy.println("$counter,${time * 1000.0},${counter / time},${params["query"]!!}")
            true
        }
        paths["/sparql/operator"] = PathMappingHelper(true, mapOf(Pair("query", "") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) { params, _, connectionOutMy ->
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
            val query = Query(instance)
            val node = XMLElementToOPBase(query, XMLElementFromXML()(params["query"]!!)!!)
            printHeaderSuccess(connectionOutMy)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, connectionOutMy, evaluator)
            true
        }
        paths["/import/turtle"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3") to ::inputElement)) { params, _, connectionOutMy ->
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importTripleFile(instance, params["file"]!!))
            true
        }
        paths["/import/partition/scheme"] = PathMappingHelper(true, mapOf(Pair("file", "") to ::inputElement)) { params, _, connectionOutMy ->
            LuposdateEndpoint.setEstimatedPartitionsFromFile(instance, params["file"]!!)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/import/turtledata"] = PathMappingHelper(true, mapOf(Pair("data", "<s> <p> <o> .") to ::inputElement)) { params, _, connectionOutMy ->
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importTurtleString(instance, params["data"]!!))
            true
        }
        paths["/import/estimatedPartitions"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3.partitions") to ::inputElement)) { params, _, connectionOutMy ->
            LuposdateEndpoint.setEstimatedPartitionsFromFile(instance, params["file"]!!)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/query/dictionary"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            val dict = dictionaryMapping[params["key"]!!]!!
            dict.connect(connectionInMy, connectionOutMy)
            true
        }
        paths["/distributed/query/dictionary/register"] = PathMappingHelper(true, mapOf()) { params, _, connectionOutMy ->
            val key = params["key"]!!
            registerDictionary(key, instance)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/query/dictionary/remove"] = PathMappingHelper(true, mapOf()) { params, _, connectionOutMy ->
            val key = params["key"]!!
            removeDictionary(key)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) { params, _, connectionOutMy ->
            distributed_graph_create(params, instance)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/graph/commit"] = PathMappingHelper(true, mapOf()) { params, _, connectionOutMy ->
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteCommit(query, origin)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/graph/drop"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) { params, _, connectionOutMy ->
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteDropGraph(query, params["name"]!!, origin)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/graph/clear"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) { params, _, connectionOutMy ->
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteClearGraph(query, params["name"]!!, origin)
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/graph/modify"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            val query = Query(instance)
            val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
            val graph = params["graph"]!!
            val isSorted = params["isSorted"]!!.toBoolean()
            val sortedBy = params["sortedBy"]!!.toInt()
            instance.tripleStoreManager!!.remoteModify(query, mode, connectionInMy, isSorted, sortedBy, graph)
            true
        }
        paths["/debugLocalStore"] = PathMappingHelper(false, mapOf()) { params, _, connectionOutMy ->
            instance.tripleStoreManager!!.debugAllLocalStoreContent()
            printHeaderSuccess(connectionOutMy)
            true
        }
        paths["/distributed/query/histogram"] = PathMappingHelper(false, mapOf(Pair("tag", "") to ::inputElement)) { params, connectionInMy, connectionOutMy ->
            val cnt = connectionInMy.readInt()
            val filter = DictionaryValueTypeArray(cnt)
            for (i in 0 until cnt) {
                filter[i] = connectionInMy.readDictionaryValueType()
            }
            val tmp = instance.tripleStoreManager!!.remoteHistogram(params["tag"]!!, filter)
            connectionOutMy.writeInt(tmp.first)
            connectionOutMy.writeInt(tmp.second)
            connectionOutMy.flush()
            true
        }
        paths["/distributed/query/register"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            val transactionID = connectionInMy.readInt()
            val keysSize = connectionInMy.readInt()
            val keys = mutableSetOf<Int>()
            for (k in 0 until keysSize) {
                keys.add(connectionInMy.readInt())
            }
            val id = connectionInMy.readInt()
            val deps = mutableMapOf<Int, String>() // key->host
            val s = connectionInMy.readInt()
            for (i in 0 until s) {
                val k = connectionInMy.readInt()
                val l = connectionInMy.readInt()
                val buf = ByteArray(l)
                connectionInMy.read(buf, l)
                deps[k] = buf.decodeToString()
            }
            val l = connectionInMy.readInt()
            val d = ByteArray(l)
            connectionInMy.read(d, l)
            val bin = ByteArrayWrapper(d)
            val container = QueryMappingContainer(bin, id, keys)
            for (key in keys) {
                queryMappings["$transactionID-$key"] = container
            }
            connectionOutMy.print("HTTP/1.1 200 OK\n\n")
            true
        }
        paths["/distributed/query/execute"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            val transactionID = params["transactionID"]!!.toInt()
            val key = params["key"]!!.toInt()
            val queryContainer = queryMappings["$transactionID-$key"]!!
            val dictionaryURL = params["dictionaryURL"]!!
            val comm = instance.communicationHandler!!
// calculate current partition
            queryContainer.instanceLock.withLock {
                queryContainer.outputStreams[key] = connectionOutMy
                queryContainer.inputStreams[key] = connectionInMy
                var flag = true
                for (c in queryContainer.outputStreams) {
                    if (c == null) {
                        flag = false
                        break
                    }
                }
                if (flag) {
// only launch if all receivers are started
// init node
                    val query = Query(instance)
// init dictionary
                    val idx2 = dictionaryURL.indexOf("/")
                    val conn = comm.openConnection(dictionaryURL.substring(0, idx2), "POST " + dictionaryURL.substring(idx2) + "\n\n", query.getTransactionID().toInt())
                    val remoteDictionary = RemoteDictionaryClient(conn.first, conn.second, instance, true)
                    query.setDictionary(remoteDictionary)
                    query.setDictionaryUrl(dictionaryURL)
// evaluate
                    val node = BinaryToOPBase.convertToIteratorBundle(query, queryContainer.data, queryContainer.dataID, localOperatorMap)
                    for (n in node.nodes) {
                        n.second.rows.next()
                    }
// release
                    query.getDictionary().close()
                    for ((k, c) in queryContainer.outputStreams) {
                        c!!.close()
                    }
                    for ((k, c) in queryContainer.inputStreams) {
                        c!!.close()
                    }
                }
// done
            }
            queryMappings.remove("$transactionID-$key")
            false
        }
        paths["/distributed/query/list"] = PathMappingHelper(true, mapOf()) { params, _, connectionOutMy ->
            printHeaderSuccess(connectionOutMy)
            for ((k, v) in queryMappings) {
                connectionOutMy.println("<p> $k :: $v </p>")
            }
            true
        }
        paths["/debug.html"] = PathMappingHelper(true, mapOf()) { params, _, connectionOutMy ->
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
                    connectionOutMy.println(" var formData = {")
                    for ((first) in v.params.keys) {
                        connectionOutMy.println("     '$first': $('#$formId [name=$first]').val(),")
                    }
                    connectionOutMy.println(" };")
                    connectionOutMy.println(" $.ajax({")
                    connectionOutMy.println("         type: 'POST',")
                    connectionOutMy.println("         url: '${k.substring(1)}',")
                    connectionOutMy.println("         data: formData")
                    connectionOutMy.println("     })")
                    connectionOutMy.println("     .done(function(data) {")
                    connectionOutMy.println("         $('#responseDiv').text(data);")
                    connectionOutMy.println("     });")
                    connectionOutMy.println(" event.preventDefault();")
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
            true
        }
    }
}
