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
import lupos.endpoint_launcher.RestEndpoint
import lupos.operator.factory.XMLElementToOPBase
import lupos.result_format.QueryResultToXMLStream
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.shared.EPartitionModeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared_inline.MyPrintWriter
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IDatabaseState
import lupos.simulator_db.IRouter

public class DatabaseHandle : IDatabase {
    private var ownAdress: Int = 0
    private var instance = Luposdate3000Instance()
    private val myPendingWork = mutableListOf<MySimulatorPendingWork>()
    private val myPendingWorkData = mutableMapOf<String, ByteArrayWrapper>()
    private var router: IRouter? = null

    override fun start(initialState: IDatabaseState) {
        println("DatabaseHandle.start ${initialState.allAddresses.map{it}} .. ${initialState.ownAddress}")
        if (initialState.allAddresses.size == 0) {
            throw Exception("invalid input")
        }
        ownAdress = initialState.ownAddress
        router = initialState.sender
        instance.LUPOS_PROCESS_URLS = initialState.allAddresses.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_ID = initialState.allAddresses.indexOf(initialState.ownAddress)
        instance.LUPOS_HOME = initialState.absolutePathToDataDirectory
        instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Process
        instance.LUPOS_DICTIONARY_MODE = EDictionaryTypeExt.KV
        instance.LUPOS_BUFFER_SIZE = 8192
        instance.communicationHandler = MySimulatorCommunicationHandler(instance, initialState.sender)
        instance = LuposdateEndpoint.initializeB(instance)
        instance.distributedOptimizerQueryFactory = {
            MySimulatorDistributedOptimizer(initialState.sender)
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
        println("receive receiveQuery $queryString")
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, queryString)
        val q = op.getQuery()
        q.initialize(op)

        val parts = q.getOperatorgraphParts()
        if (parts.size == 1) {
            val out = MyPrintWriter(true)
            QueryResultToXMLStream(q.getRoot(), out)
            val res = out.toString().encodeToByteArray()
            router!!.sendQueryResult(sourceAddress, res)
        } else {
            val destinations = mutableMapOf("" to sourceAddress)
            receive(MySimulatorOperatorgraphPackage(parts, destinations, q.getOperatorgraphPartsToHostMap(), q.getDependenciesMapTopDown()))
        }
    }

    private fun receive(pck: MySimulatorAbstractPackage) {
        println("receive MySimulatorAbstractPackage ${pck.path}")
        when (pck.path) {
            "/distributed/query/dictionary/register",
            "/distributed/query/dictionary/remove" -> {
                // dont use dictionaries right now
            }
            "/distributed/graph/create" -> RestEndpoint.distributed_graph_create(pck.params, instance)
            "/distributed/graph/modify" -> RestEndpoint.distributed_graph_modify(pck.params, instance, MySimulatorInputStreamFromPackage(pck.data!!))
            else -> TODO("${pck.path} ${pck.params}")
        }
    }

    private fun receive(pck: MySimulatorOperatorgraphPackage) {
        println("receive MySimulatorOperatorgraphPackage")
        val allHosts = pck.operatorgraphPartsToHostMap.values.toSet().toTypedArray()
        val allHostAdresses = IntArray(allHosts.size) { allHosts[it].toInt() }
//        val nextHops = router!!.getNextDatabaseHops(allHostAdresses)  //TODO
        val nextHops = allHostAdresses
        val packages = mutableMapOf<Int, MySimulatorOperatorgraphPackage>()
        println("nextHops ${nextHops.map{it}} .. ${allHostAdresses.map{it}} .. ${allHosts.map{it}}")
        for (i in nextHops.toSet()) {
            packages[i] = MySimulatorOperatorgraphPackage(mutableMapOf(), mutableMapOf(), mutableMapOf(), mutableMapOf())
        }
        packages[ownAdress] = MySimulatorOperatorgraphPackage(mutableMapOf(), mutableMapOf(), mutableMapOf(), mutableMapOf())
        println("pp ${packages.keys}")
        val packageMap = mutableMapOf<String, Int>()
        for ((k, v) in pck.operatorgraphPartsToHostMap) {
            packageMap[k] = nextHops[allHostAdresses.indexOf(v.toInt())]
        }
        var changed = true
        while (changed) {
            changed = false
            loop@ for ((k, v) in pck.dependenciesMapTopDown) {
                if (!packageMap.contains(k)) {
                    SanityCheck.check { v.size >= 1 }
                    var dest = -1
                    for (key in v) {
                        val d = packageMap[key]
                        if (d != null) {
                            if (dest == -1) {
                                dest = d
                            } else {
                                if (dest != d) {
                                    packageMap[k] = ownAdress // alles mit unterschiedlichen next hops selber berechnen
for(i in pck.dependenciesMapTopDown[k]!!){ 
pck.destinations[i]=ownAdress
}
                                    changed = true
                                    continue@loop
                                }
                            }
                        } else {
                            continue@loop
                        }
                    }
for(i in pck.dependenciesMapTopDown[k]!!){
pck.destinations[i]=dest
}
                    packageMap[k] = dest // alles mit gemeinsamen next Hop zusammen weitersenden
                    changed = true
                }
            }
        }
        for ((k, v) in packageMap) {
            val targetInt = v
            val p = packages[targetInt]!!
            p.operatorGraph[k] = pck.operatorGraph[k]!!
            val h = pck.operatorgraphPartsToHostMap[k]
            if (h != null) {
                p.operatorgraphPartsToHostMap[k] = h
            }
            val deps = pck.dependenciesMapTopDown[k]
            if (deps != null) {
                p.dependenciesMapTopDown[k] = deps
            } else {
                p.dependenciesMapTopDown[k] = mutableSetOf()
            }
val d=pck.destinations[k]
if(d!=null){
p.destinations[k]=d
}else{
p.destinations[k]=ownAdress
}
        }
        SanityCheck.check { packageMap.size == pck.operatorGraph.size }
        for ((k, v) in packages) {
            if (k != ownAdress) {
                router!!.send(k, v)
            }
        }
        val p = packages[ownAdress!!]!!
        for ((k, v) in p.operatorGraph) {
            myPendingWork.add(
                MySimulatorPendingWork(
                    p.operatorGraph[k]!!,
                    p.destinations[k]!!,
                    p.dependenciesMapTopDown[k]!!,
k
                )
            )
        }
        doWork()
    }

    private fun doWork() {
var changed=true
while(changed){
changed=false
for(w in myPendingWork){
if(myPendingWorkData.keys.containsAll(w.dependencies)){
//w.operatorGraph
//w.destination
changed=true
val node = XMLElementToOPBase(query, queryXML) as POPBase
when (node) {
                                            is POPDistributedSendSingle -> {
                                                node.evaluate(MySimulatorOutputStreamToPackage(w.destination,"simulator-intermediate-result",mapOf("key" to w.key),router!!))
                                            }
                                            else -> throw Exception("unexpected node '${node.classname}'")
                                        }


myPendingWork.remove(w)
changed=true
break
}
}
}
    }

    override fun receive(pck: IDatabasePackage) {
        when (pck) {
            is MySimulatorAbstractPackage -> receive(pck)
            is MySimulatorOperatorgraphPackage -> receive(pck)
            else -> TODO("$pck")
        }
    }
}
