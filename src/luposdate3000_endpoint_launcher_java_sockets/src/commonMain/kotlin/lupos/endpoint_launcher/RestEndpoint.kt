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
import lupos.jena_wrapper.JenaWrapper
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyOutputStream
import lupos.shared.LUPOS_REAL_WORLD_DATA_ROOT
import lupos.shared.XMLElementFromXML
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dictionary.nodeGlobalDictionary
import lupos.shared.tripleStoreManager
import lupos.shared_inline.File
import lupos.shared_inline.MyInputStream
import lupos.shared_inline.MyOutputStream
import kotlin.jvm.JvmField

internal object RestEndpoint {
    @JvmField
    internal var dictionaryMapping = mutableMapOf<String, RemoteDictionaryServer>()
    internal var sessionMap = mutableMapOf<Int, EndpointExtendedVisualize>()

    @Suppress("NOTHING_TO_INLNE")
    private inline fun registerDictionary(key: String): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true))
        dictionaryMapping[key] = dict
        return dict
    }

    @Suppress("NOTHING_TO_INLNE")
    private inline fun registerDictionary(key: String, dict: IDictionary): RemoteDictionaryServer {
        val dict = RemoteDictionaryServer(dict)
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

    internal fun initialize(paths: MutableMap<String, PathMappingHelper>, params: Map<String, String>, connectionInMy: MyInputStream, connectionOutMy: MyOutputStream, hostname: String, port: Int) {
        paths["/sparql/jenaquery"] = PathMappingHelper(true, mapOf(Pair("query", "SELECT * WHERE { ?s ?p ?o . }") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(JenaWrapper.execQuery(params["query"]!!))
            /*Coverage Unreachable*/
        }
        paths["/sparql/jenaload"] = PathMappingHelper(true, mapOf(Pair("file", "$LUPOS_REAL_WORLD_DATA_ROOT/sp2b/1024/complete.n3") to ::inputElement)) {
            JenaWrapper.loadFromFile(params["file"]!!)
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("success")
        }
        paths["/sparql/startSession"] = PathMappingHelper(
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
            var eev = EndpointExtendedVisualize(params["query"].toString())
            val key = sessionMap.size + 1
            sessionMap[key] = eev
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(key.toString())
            // LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
            /*Coverage Unreachable*/
        }

        paths["/sparql/getLogicalVisual"] = PathMappingHelper(
            true,
            mapOf(
                Pair("sessionID", "") to ::inputElement,
            )
        ) {
            val eev = params["sessionID"]?.let { sessionMap.get(it.toInt()) }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                for (step in eev.getOptimizedStepsLogical()) {
                    connectionOutMy.print(step)
                    connectionOutMy.print("NEWTREE")
                }
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
        }

        paths["/sparql/getPhysicalVisual"] = PathMappingHelper(
            true,
            mapOf(
                Pair("sessionID", "") to ::inputElement,
            )
        ) {
            val eev = params["sessionID"]?.let { sessionMap.get(it.toInt()) }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                for (step in eev.getOptimizedStepsPhysical()) {
                    connectionOutMy.print(step)
                    connectionOutMy.print("NEWTREE")
                }
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
        }
        paths["/sparql/getResult"] = PathMappingHelper(
            true,
            mapOf(
                Pair("sessionID", "") to ::inputElement,
            )
        ) {
            val eev = params["sessionID"]?.let { sessionMap.get(it.toInt()) }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                connectionOutMy.print(eev.getResult())
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            // LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
            /*Coverage Unreachable*/
        }

        paths["/sparql/getVisualisationData"] = PathMappingHelper(
            true,
            mapOf(
                Pair("sessionID", "") to ::inputElement,
            )
        ) {
            val eev = params["sessionID"]?.let { sessionMap.get(it.toInt()) }
            printHeaderSuccess(connectionOutMy)
            if (eev != null) {
                val tmp = eev.getDataSteps()
                for (i in tmp) {
                    connectionOutMy.print(i)
                }
            } else {
                connectionOutMy.print("SessionNotFoundException")
            }
            // LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
            /*Coverage Unreachable*/
        }

        paths["/sparql/closeSession"] = PathMappingHelper(
            true,
            mapOf(
                Pair("sessionID", "") to ::inputElement,
            )
        ) {

            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print("SessionClosedACK")
            sessionMap.remove(params["sessionID"])
            // LuposdateEndpoint.evaluateOperatorgraphToResultA(node, connectionOutMy, evaluator)
            /*Coverage Unreachable*/
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
        paths["/import/turtle"] = PathMappingHelper(true, mapOf(Pair("file", "$LUPOS_REAL_WORLD_DATA_ROOT/sp2b/1024/complete.n3") to ::inputElement)) {
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
        paths["/import/estimatedPartitions"] = PathMappingHelper(true, mapOf(Pair("file", "$LUPOS_REAL_WORLD_DATA_ROOT/sp2b/1024/complete.n3.partitions") to ::inputElement)) {
            LuposdateEndpoint.setEstimatedPartitionsFromFile(params["file"]!!)
            printHeaderSuccess(connectionOutMy)
        }
        paths["/import/intermediate"] = PathMappingHelper(true, mapOf(Pair("file", "$LUPOS_REAL_WORLD_DATA_ROOT/sp2b/1024/complete.n3") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importIntermediateFiles(params["file"]!!))
            /*Coverage Unreachable*/
        }
        paths["/import/xml"] = PathMappingHelper(true, mapOf(Pair("xml", "") to ::inputElement)) {
            printHeaderSuccess(connectionOutMy)
            connectionOutMy.print(LuposdateEndpoint.importXmlData(params["xml"]!!))
            /*Coverage Unreachable*/
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
        paths["/distributed/graph/create"] = PathMappingHelper(true, mapOf(Pair("name", "") to ::inputElement)) {
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
    }
}
