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

package lupos.simulator_db.dummyImpl

import lupos.shared.inline.File
import simora.ILogger
import simora.IPayload
import simora.applications.IApplicationStack_Actuator
import simora.applications.IApplicationStack_Middleware
import simora.applications.scenario.parking.Package_Query
import simora.parser.JsonParserObject

public class Application_DatabaseDummy public constructor(
    config: JsonParserObject,
    logger: ILogger,
    ownAddress: Int,
    absolutePathToDataDirectory: String,
    dbDeviceAddressesStoreList: MutableList<Int>,
    dbDeviceAddressesApplication_DatabaseDummy_QueryList: MutableList<Int>,
    private val databaseFeatureID: Int,
) : IApplicationStack_Actuator {
    override fun emptyEventQueue(): String? = null
    internal lateinit var state: Application_DatabaseDummy_State

    internal lateinit var sender: IApplicationStack_Middleware
    override fun setRouter(router: IApplicationStack_Middleware) {
        sender = router
    }

    override fun shutDown() {
    }

    init {
        state = Application_DatabaseDummy_State(logger, ownAddress, dbDeviceAddressesStoreList.toIntArray(), dbDeviceAddressesApplication_DatabaseDummy_QueryList.toIntArray(), absolutePathToDataDirectory)
        state.dataFile = "$absolutePathToDataDirectory/file.txt"
        File(absolutePathToDataDirectory).mkdirs()
    }

    override fun startUp() {
        File(state.dataFile).withOutputStream { }
    }

    override fun receive(pck: IPayload): IPayload? {
        when (pck) {
            is Package_DatabaseDummy_Preprocessing -> receive(pck)
            is Package_DatabaseDummy_Result -> receive(pck)
            is Package_DatabaseDummy_ChoosenOperator -> receive(pck)
            is Package_Query -> receive(pck)
            else -> return pck
        }
        return null
    }

    public fun receive(pck: Package_Query) {
        state.addressForQueryEndResult = pck.sourceAddress
        val queryString = pck.query.decodeToString()
        if (queryString.contains("INSERT DATA")) {
            saveData(queryString)
            return
        }

        val operatorGraph = Application_DatabaseDummy_Optimizer.optimize(queryString)
        val operatorGraphParts = Application_DatabaseDummy_Optimizer.split(operatorGraph)
        Application_DatabaseDummy_Optimizer.assignNodesToTripleStroceAccess(operatorGraphParts)
        val queryID = operatorGraph.getTransactionID()
        val destinationAddresses = Application_DatabaseDummy_Optimizer.extractTripleStoreAddresses(operatorGraphParts).distinct().toIntArray()
        setupApplication_DatabaseDummy_OperatorGraph(destinationAddresses, operatorGraphParts, state.ownAddress, queryID)
    }

    private fun saveData(data: String) {
        val stream = File(state.dataFile).openOutputStream(true)
        stream.println(data)
        stream.close()
    }

    private fun receive(pck: Package_DatabaseDummy_Preprocessing) {
        val operatorGraphParts = Application_DatabaseDummy_OperatorGraphPart.fromByteArray(pck.operatorGraphParts)
        setupApplication_DatabaseDummy_OperatorGraph(pck.destinationAddresses, operatorGraphParts, pck.senderAddress, pck.queryID)
    }

    private fun receive(pck: Package_DatabaseDummy_Result) {
        // if this is not used locally, then simply send it upwards in the operator graph
    }

    private fun receive(pck: Package_DatabaseDummy_ChoosenOperator) {
        val query = state.queriesInProgress[pck.queryID]!!

        query.answeredByNextHop[query.nextHops.indexOf(pck.senderAddress)] = true
        for (operatorID in pck.operators)
            query.choosenOperators.add(Application_DatabaseDummy_ReceivedResults(pck.senderAddress, operatorID))

        if (allReplied(query.answeredByNextHop)) {
            startEvaluation(query.parentAddress, pck.queryID)
        }
    }

    private fun allReplied(answeredByNextHop: BooleanArray): Boolean {
        for (hasReplied in answeredByNextHop)
            if (!hasReplied) {
                return false
            }
        return true
    }

    private fun mergeChoosenOperators(query: Application_DatabaseDummy_Query) {
        for (part in query.operatorGraphParts) {
            if (part.canBeEvaluatedWithTheseDependencies(query.choosenOperators)) {
                val dep = part.mergeAndGetDependencies(query.choosenOperators)
                query.choosenOperators.removeAll(dep)
                query.choosenOperators.add(part) // alle unnötigen netzwerk-möglichkeiten eliminieren, wenn mehrere query-parts auf der gleichen node berechnet werden
            }
        }
    }

    private fun startEvaluation(senderAddress: Int, queryID: Int) {
        val query = state.queriesInProgress[queryID]!!

        mergeChoosenOperators(query)

        sender.send(
            senderAddress,
            Package_DatabaseDummy_ChoosenOperator(
                destinationAddress = senderAddress,
                senderAddress = state.ownAddress,
                operators = query.choosenOperators.map { it.getUUID() }.toIntArray(),
                queryID = queryID,
            ),
        )

        // Es kann passieren, dass mehrere Teilbäume auf dem gleichen Knoten landen,
        // Aber in übergeordneten Operatoren von anderen Knoten berechnet werden,
        // sodass zwar mehrere Operatoren auf dem selben Blattknoten berechnet werden können,
        // jedoch diese Operatoren nichts miteinander zu tun haben.
        // Bei kleinen Bäumen kommt der Fall nicht vor.
        // z.B wenn das selbe trippelmuster mehrfach auftaucht, aber noch etwas dazwischen liegt,
        // kann der Application_DatabaseDummy_Optimizer nicht immer diese Operanden fusionieren.
        for (op in query.choosenOperators) {
// ergebnisse senden
            sender.send(
                senderAddress,
                Package_DatabaseDummy_Result(
                    destinationAddress = senderAddress,
                    senderAddress = state.ownAddress,
                    queryID = queryID,
                    operatorID = op.getUUID(),
                    result = op.evaluate(),
                ),
            )
        }
    }

    private fun getHopToDestinationsMap(destinationAddresses: IntArray): HashMap<Int, MutableSet<Int>> {
        val map = HashMap<Int, MutableSet<Int>>(destinationAddresses.size)
        val nextHops = sender.getNextFeatureHops(destinationAddresses, databaseFeatureID)
        for (i in nextHops.indices)
            addToHopMap(map, nextHops[i], destinationAddresses[i])
        return map
    }

    private fun addToHopMap(map: HashMap<Int, MutableSet<Int>>, hop: Int, dest: Int) {
        if (!map.containsKey(hop)) {
            map[hop] = mutableSetOf()
        }
        map[hop]!!.add(dest)
    }

    private fun updateApplication_DatabaseDummy_QueryInProgress(parts: List<Application_DatabaseDummy_OperatorGraphPart>, nextHops: IntArray, senderAddress: Int, queryID: Int) {
        val newApplication_DatabaseDummy_Query = Application_DatabaseDummy_Query(parts, nextHops, senderAddress)
        state.queriesInProgress[queryID] = newApplication_DatabaseDummy_Query
    }

    private fun chooseOperators(queryID: Int, isLastHop: Boolean, src: Int) {
        val query = state.queriesInProgress[queryID]!!
        for (part in query.operatorGraphParts)
            if (part.canBeEvaluatedWithoutRemoteDependencies()) {
                query.choosenOperators.add(part)
            }

        if (isLastHop) {
            startEvaluation(src, queryID)
        }
    }

    private fun sendPackage_DatabaseDummy_Preprocessing(to: Int, dests: IntArray, parts: ByteArray, queryID: Int) {
        sender.send(
            to,
            Package_DatabaseDummy_Preprocessing(
                destinationAddresses = dests,
                operatorGraphParts = parts,
                senderAddress = state.ownAddress,
                queryID = queryID,
            ),
        )
    }

    private fun setupApplication_DatabaseDummy_OperatorGraph(destinationAddresses: IntArray, parts: List<Application_DatabaseDummy_OperatorGraphPart>, senderAddress: Int, queryID: Int) {
        val nextHopToDestsMap = getHopToDestinationsMap(destinationAddresses)
        updateApplication_DatabaseDummy_QueryInProgress(parts, nextHopToDestsMap.keys.toIntArray(), senderAddress, queryID)

        for ((hop, dest) in nextHopToDestsMap)
            if (hop == state.ownAddress) {
                chooseOperators(queryID, nextHopToDestsMap.size == 1, senderAddress)
            }
            // TODO Bug: Sende immer weiter herunter wenn man nicht der letzte ist.
            else {
                sendPackage_DatabaseDummy_Preprocessing(hop, dest.toIntArray(), Application_DatabaseDummy_OperatorGraphPart.encodeToByteArray(parts), queryID)
            }
    }
}
