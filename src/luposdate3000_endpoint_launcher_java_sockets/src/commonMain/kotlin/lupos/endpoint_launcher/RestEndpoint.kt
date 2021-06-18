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
import lupos.endpoint.EndpointExtendedVisualize
import lupos.endpoint.LuposdateEndpoint
import lupos.jena_wrapper.JenaWrapper
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.DateHelperRelative
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.XMLElementFromXML
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.inline.MyInputStream
import lupos.shared.inline.MyOutputStream
import lupos.shared.inline.MyPrintWriter
import kotlin.jvm.JvmField

public object RestEndpoint {
    @JvmField
    internal var dictionaryMapping = mutableMapOf<String, RemoteDictionaryServer>()
    private var sessionMap = mutableMapOf<Int, EndpointExtendedVisualize>()

    public fun distributed_graph_create(params: Map<String, String>, instance: Luposdate3000Instance) {
        val name = params["name"]!!
        val query = Query(instance)
        instance.tripleStoreManager!!.remoteCreateGraph(query, name, (params["origin"] == null || params["origin"].toBoolean()), params["metadata"])
    }

    public fun distributed_graph_modify(params: Map<String, String>, instance: Luposdate3000Instance, connectionInMy: IMyInputStream) {
        val query = Query(instance)
        val key = params["key"]!!
        val idx2 = EIndexPatternExt.names.indexOf(params["idx"]!!)
        val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
        instance.tripleStoreManager!!.remoteModify(query, key, mode, idx2, connectionInMy)
    }

    @Suppress("NOTHING_TO_INLNE")
    private inline fun registerDictionary(instance: Luposdate3000Instance, key: String): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true, instance), instance)
        dictionaryMapping[key] = dict
        return dict
    }

    @Suppress("NOTHING_TO_INLNE")
    private inline fun registerDictionary(instance: Luposdate3000Instance, key: String, dict: IDictionary): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(dict, instance)
        dictionaryMapping[key] = dict
        return dict
    }

    @Suppress("NOTHING_TO_INLNE")
    private inline fun removeDictionary(key: String) {
        dictionaryMapping.remove(key)
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

    internal fun initialize(instance: Luposdate3000Instance, paths: MutableMap<String, PathMappingHelper>, params: Map<String, String>, connectionInMy: MyInputStream, connectionOutMy: MyOutputStream, hostname: String, port: Int) {
        paths["/sparql/jenaquery"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(JenaWrapper.execQuery(params["query"]!!))
            /*Coverage Unreachable*/
        }
        paths["/sparql/jenaload"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3") to ::inputElement)) {
            JenaWrapper.loadFromFile(params["file"]!!)
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("success")
        }
        paths["/sparql/startSession"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) {
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
            val eev = EndpointExtendedVisualize(params["query"].toString(), instance)
            val key = sessionMap.size + 1
            sessionMap[key] = eev
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(key.toString())
            /*Coverage Unreachable*/
        }
        paths["/sparql/getLogicalVisual"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) {
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print("[")
                val steps = eev.getOptimizedStepsLogical()
                for (i in 0 until steps.size - 1) {
                    val step = steps[i]
                    connectionOutMy.print(step)
                    connectionOutMy.print(",")
                }
                if (steps.size > 0) {
                    connectionOutMy.print(steps[steps.size - 1])
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
        }
        paths["/sparql/getPhysicalVisual"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) {
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print("[")
                val steps = eev.getOptimizedStepsPhysical()
                for (i in 0 until steps.size - 1) {
                    val step = steps[i]
                    connectionOutMy.print(step)
                    connectionOutMy.print(",")
                }
                if (steps.size > 0) {
                    connectionOutMy.print(steps[steps.size - 1])
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
        }
        paths["/sparql/getResult"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) {
            val eev = params["sessionID"]?.let { sessionMap[it.toInt()] }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print(eev.getResult())
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            /*Coverage Unreachable*/
        }
        paths["/sparql/getVisualisationData"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) {
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
                if (tmp.size > 0) {
                    connectionOutMy.print(tmp[tmp.size - 1])
                }
                connectionOutMy.print("]")
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            /*Coverage Unreachable*/
        }
        paths["/sparql/closeSession"] = PathMappingHelper(true, mapOf(Pair("sessionID", "") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("SessionClosedACK")
            sessionMap.remove(params["sessionID"]!!.toInt())
            /*Coverage Unreachable*/
        }
        paths["/sparql/query"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) {
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
            val query = node.getQuery()
            val key = "${query.getTransactionID()}"
            val dict = registerDictionary(instance, key, query.getDictionary())
            query.setDictionaryServer(dict)
            query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
            printHeaderSuccess(connectionOutMy)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, connectionOutMy, evaluator)
            removeDictionary(key)
            /*Coverage Unreachable*/
        }
        paths["/sparql/benchmark"] = PathMappingHelper(true, mapOf(Pair("minimumTime", "10") to ::inputElement, Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) {
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
            var minimumTime = try {
                e3!!.toDouble()
            } catch (e: Throwable) {
                10.0
            }
            val writer = MyPrintWriter(false)
            val node = LuposdateEndpoint.evaluateSparqlToOperatorgraphB(instance, params["query"]!!, false)
            val query = node.getQuery()
            val key = "${query.getTransactionID()}"
            val dict = registerDictionary(instance, key, query.getDictionary())
            query.setDictionaryServer(dict)
            query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
            printHeaderSuccess(connectionOutMy)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, writer, evaluator)
            val timer = DateHelperRelative.markNow()
            var time: Double
            var counter = 0
            while (true) {
                counter++
                LuposdateEndpoint.evaluateOperatorgraphToResult(instance, node, writer)
                time = DateHelperRelative.elapsedSeconds(timer)
                if (time > minimumTime) {
                    break
                }
            }
            connectionOutMy.println("$counter,${time * 1000.0},${counter / time},${params["query"]!!}")
            removeDictionary(key)
            /*Coverage Unreachable*/
        }
        paths["/sparql/operator"] = PathMappingHelper(true, mapOf(Pair("query", "") to ::inputElement, Pair("evaluator", "") to ::selectElementEQueryResultToStreamExt)) {
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
            val key = "${query.getTransactionID()}"
            val dict = registerDictionary(instance, key, query.getDictionary())
            query.setDictionaryServer(dict)
            query.setDictionaryUrl("$hostname:$port/distributed/query/dictionary?key=$key")
            printHeaderSuccess(connectionOutMy)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, node, connectionOutMy, evaluator)
            removeDictionary(key)
            /*Coverage Unreachable*/
        }
        paths["/import/turtle"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importTurtleFile(instance, params["file"]!!))
            /*Coverage Unreachable*/
        }
        paths["/import/turtledata"] = PathMappingHelper(true, mapOf(Pair("data", "<s> <p> <o> .") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importTurtleString(instance, params["data"]!!))
            /*Coverage Unreachable*/
        }
        paths["/import/estimatedPartitions"] = PathMappingHelper(true, mapOf(Pair("file", "${instance.LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/1024/complete.n3.partitions") to ::inputElement)) {
            LuposdateEndpoint.setEstimatedPartitionsFromFile(instance, params["file"]!!)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/import/xml"] = PathMappingHelper(true, mapOf(Pair("xml", "") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importXmlData(instance, params["xml"]!!))
            /*Coverage Unreachable*/
        }
        paths["/distributed/query/dictionary"] = PathMappingHelper(false, mapOf()) {
            val dict = dictionaryMapping[params["key"]!!]!!
            dict.connect(connectionInMy, connectionOutMy)
        }
        paths["/distributed/query/dictionary/register"] = PathMappingHelper(true, mapOf()) {
            registerDictionary(instance, params["key"]!!)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/query/dictionary/remove"] = PathMappingHelper(true, mapOf()) {
            removeDictionary(params["key"]!!)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
            distributed_graph_create(params, instance)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/graph/commit"] = PathMappingHelper(true, mapOf()) {
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteCommit(query, origin)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/graph/drop"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteDropGraph(query, params["name"]!!, origin)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/graph/clear"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
            val query = Query(instance)
            val origin = params["origin"] == null || params["origin"]!!.toBoolean()
            instance.tripleStoreManager!!.remoteClearGraph(query, params["name"]!!, origin)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/distributed/graph/modify"] = PathMappingHelper(false, mapOf()) {
            distributed_graph_modify(params, instance, connectionInMy)
        }
        paths["/distributed/graph/modifysorted"] = PathMappingHelper(false, mapOf()) {
            val query = Query(instance)
            val key = params["key"]!!
            val idx2 = EIndexPatternExt.names.indexOf(params["idx"]!!)
            val mode = EModifyTypeExt.names.indexOf(params["mode"]!!)
            instance.tripleStoreManager!!.remoteModifySorted(query, key, mode, idx2, connectionInMy)
        }
        paths["/debugLocalStore"] = PathMappingHelper(false, mapOf()) {
            instance.tripleStoreManager!!.debugAllLocalStoreContent()
            printHeaderSuccess(connectionOutMy)
        }
    }
}
