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
package lupos.simulator_db.luposdate3000

import lupos.endpoint.LuposdateEndpoint
import lupos.operator.base.Query
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.result_format.QueryResultToXMLStream
import lupos.shared.EPartitionModeExt
import lupos.shared.ICommunicationHandler
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Luposdate3000Instance
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.operator.IOPBase
import lupos.shared.optimizer.IDistributedOptimizer
import lupos.shared_inline.MyPrintWriter
import lupos.simulator_db.ChoosenOperatorPackage
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IDatabaseState
import lupos.simulator_db.IRouter
import lupos.simulator_db.PreprocessingPackage
import lupos.simulator_db.ResultPackage

internal class MyAbstractPackage(val path: String, val params: Map<String, String>) : IDatabasePackage

internal class CommunicationHandler(val instance: Luposdate3000Instance, val router: IRouter) : ICommunicationHandler {
    /*
    public interface IRouter {
        public fun send(destinationAddress: Int, pck: IDatabasePackage)
        public fun sendQueryResult(destinationAddress: Int, result: ByteArray)
        public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
    }
    public class PreprocessingPackage(
        public val destinationAddresses: IntArray, // Richtung triple store
        public val operatorGraphParts: ByteArray,
        public val senderAddress: Int, // dies MUSS ein DB-node sein ... von wo kommt das paket
        public val queryID: Int, // die ist immer gleich für alles was zu einem "QueryPackage" gehört
    ) : IDatabasePackage

    public class ChoosenOperatorPackage(
        public val destinationAddress: Int, // Richtung root-node
        public val senderAddress: Int,
        public val operators: IntArray, // zeigt an welche "operatorGraphParts" teile berechnet werden - dadurch ist schnell klar, welcher node was berechnet
        public val queryID: Int,
    ) : IDatabasePackage

    public class ResultPackage(
        public val result: ByteArray, // die Nutzdaten ... zurzeit alles als ein Block, später besser bidirektionales streaming, wobei primär Richtung root-node gesendet wird.
        public val destinationAddress: Int, // Richtung root-node
        public val senderAddress: Int,
        public val queryID: Int,
        public val operatorID: Int, // damit der empfänger weiß, was für ein ergebnis dies ist ... kann ggf in "result" integriert werden
    ) : IDatabasePackage
    */
    override fun sendData(targetHost: String, path: String, params: Map<String, String>) {
        router.send(targetHost.toInt(), MyAbstractPackage(path, params))
    }

    override fun openConnection(targetHost: String, path: String, params: Map<String, String>): Pair<IMyInputStream, IMyOutputStream> {
        TODO()
    }

    override fun openConnection(targetHost: String, header: String): Pair<IMyInputStream, IMyOutputStream> {
        TODO()
    }
}

internal class DistributedOptimizer(val router: IRouter, val dest: () -> Int) : IDistributedOptimizer {
    private var originalOptimizer = DistributedOptimizerQuery()
    override fun optimize(query: IQuery): IOPBase {
        originalOptimizer.splitQuery(query)
        return query.getRoot()
    }
}

public class DatabaseHandle : IDatabase {

    private var instance = Luposdate3000Instance()

    private var targetForQueryResponse: Int = -1
    private var router: IRouter? = null

    override fun start(initialState: IDatabaseState) {
        if (initialState.allAddresses.size == 0) {
            throw Exception("invalid input")
        }
        println("${initialState.allAddresses.map { it }} ... ${initialState.ownAddress}")
        router = initialState.sender
        instance.LUPOS_PROCESS_URLS = initialState.allAddresses.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_ID = initialState.allAddresses.indexOf(initialState.ownAddress)
        instance.LUPOS_HOME = initialState.absolutePathToDataDirectory
        instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Process
        instance.LUPOS_DICTIONARY_MODE = EDictionaryTypeExt.KV
        instance.LUPOS_BUFFER_SIZE = 8192
        instance.communicationHandler = CommunicationHandler(instance, initialState.sender)
        instance = LuposdateEndpoint.initializeB(instance)
        instance.distributedOptimizerQueryFactory = {
            DistributedOptimizer(initialState.sender, { targetForQueryResponse })
        }
    }

    override fun activate() {
/*
        if (!instance.initialized) {
            instance = LuposdateEndpoint.initializeB(instance)
        }
*/
    }

    override fun deactivate() {
/*
        if ((!BufferManagerExt.isInMemoryOnly) && (instance.LUPOS_DICTIONARY_MODE != EDictionaryTypeExt.InMemory)) {
            // do not disable inmemory databases, because they would loose all data
            LuposdateEndpoint.close(instance)
        }
*/
    }

    override fun end() {
        LuposdateEndpoint.close(instance)
    }

    override fun receiveQuery(sourceAddress: Int, query: ByteArray) {
        val queryString = query.decodeToString()
        println(queryString)
        targetForQueryResponse = sourceAddress
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, queryString)
        op.getQuery().initialize(op)

        val parts = op.getQuery().getOperatorgraphParts()
        if (parts.size == 1) {

            val out = MyPrintWriter(true)
            QueryResultToXMLStream(op.getQuery().getRoot(), out)
            val res = out.toString().encodeToByteArray()
            router!!.sendQueryResult(targetForQueryResponse, res)
        } else {
            TODO()
        }
    }

    private fun receive(pck: PreprocessingPackage) {
        TODO()
    }

    private fun receive(pck: ResultPackage) {
        TODO()
    }

    private fun receive(pck: ChoosenOperatorPackage) {
        TODO()
    }

    private fun receive(pck: MyAbstractPackage) {
        when (pck.path) {
            "/distributed/query/dictionary/register" -> {
// dont use dictionaries right now -> register dictionary must proceed
            }
            "/distributed/graph/create" -> {
                val name = pck.params["name"]!!
                val query = Query(instance)
                instance.tripleStoreManager!!.remoteCreateGraph(query, name, (pck.params["origin"] == null || pck.params["origin"].toBoolean()), pck.params["metadata"])
            }
            else -> {
                TODO("${pck.path} ${pck.params}")
            }
        }
    }

    override fun receive(pck: IDatabasePackage) {
        when (pck) {
            is MyAbstractPackage -> receive(pck)
            is PreprocessingPackage -> receive(pck)
            is ResultPackage -> receive(pck)
            is ChoosenOperatorPackage -> receive(pck)
        }
    }
}
