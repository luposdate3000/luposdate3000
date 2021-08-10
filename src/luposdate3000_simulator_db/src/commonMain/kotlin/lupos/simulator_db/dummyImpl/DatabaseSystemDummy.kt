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
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.QueryPackage

public class DatabaseSystemDummy : IDatabase {

    private lateinit var state: DummyDatabaseState

    public override fun start(initialState: DatabaseState) {
        state = DummyDatabaseState(initialState.visualisationNetwork, initialState.ownAddress, initialState.allAddresses, initialState.sender, initialState.absolutePathToDataDirectory)
        state.dataFile = "${initialState.absolutePathToDataDirectory}/file.txt"
        File(state.dataFile).withOutputStream { }
    }

    public override fun activate() {
    }

    public override fun deactivate() {
    }

    public override fun end() {
    }

    public override fun receive(pck: IDatabasePackage) {
        when (pck) {
            is PreprocessingPackage -> receive(pck)
            is ResultPackage -> receive(pck)
            is ChoosenOperatorPackage -> receive(pck)
            is QueryPackage -> receive(pck)
            else -> TODO()
        }
    }

    public fun receive(pck: QueryPackage) {
        state.addressForQueryEndResult = pck.sourceAddress
        val queryString = pck.query.decodeToString()
        if (queryString.contains("INSERT DATA")) {
            saveData(queryString)
            return
        }

        val operatorGraph = Optimizer.optimize(queryString)
        val operatorGraphParts = Optimizer.split(operatorGraph)
        Optimizer.assignNodesToTripleStroceAccess(operatorGraphParts)
        val queryID = operatorGraph.getTransactionID()
        val destinationAddresses = Optimizer.extractTripleStoreAddresses(operatorGraphParts).distinct().toIntArray()
        setupOperatorGraph(destinationAddresses, operatorGraphParts, state.ownAddress, queryID)
    }

    private fun saveData(data: String) {
        val stream = File(state.dataFile).openOutputStream(true)
        stream.println(data)
        stream.close()
    }

    private fun receive(pck: PreprocessingPackage) {
        val operatorGraphParts = OperatorGraphPart.fromByteArray(pck.operatorGraphParts)
        setupOperatorGraph(pck.destinationAddresses, operatorGraphParts, pck.senderAddress, pck.queryID)
    }

    private fun receive(pck: ResultPackage) {
        // if this is not used locally, then simply send it upwards in the operator graph
    }

    private fun receive(pck: ChoosenOperatorPackage) {
        val query = state.queriesInProgress[pck.queryID]!!

        query.answeredByNextHop[query.nextHops.indexOf(pck.senderAddress)] = true
        for (operatorID in pck.operators)
            query.choosenOperators.add(ReceivedResults(pck.senderAddress, operatorID))

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

    private fun mergeChoosenOperators(query: Query) {
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

        state.sender.send(
            senderAddress,
            ChoosenOperatorPackage(
                destinationAddress = senderAddress,
                senderAddress = state.ownAddress,
                operators = query.choosenOperators.map { it.getUUID() }.toIntArray(),
                queryID = queryID,
            )
        )

        // Es kann passieren, dass mehrere Teilbäume auf dem gleichen Knoten landen,
        // Aber in übergeordneten Operatoren von anderen Knoten berechnet werden,
        // sodass zwar mehrere Operatoren auf dem selben Blattknoten berechnet werden können,
        // jedoch diese Operatoren nichts miteinander zu tun haben.
        // Bei kleinen Bäumen kommt der Fall nicht vor.
        // z.B wenn das selbe trippelmuster mehrfach auftaucht, aber noch etwas dazwischen liegt,
        // kann der Optimizer nicht immer diese Operanden fusionieren.
        for (op in query.choosenOperators) {
// ergebnisse senden
            state.sender.send(
                senderAddress,
                ResultPackage(
                    destinationAddress = senderAddress,
                    senderAddress = state.ownAddress,
                    queryID = queryID,
                    operatorID = op.getUUID(),
                    result = op.evaluate(),
                )
            )
        }
    }

    private fun getHopToDestinationsMap(destinationAddresses: IntArray): HashMap<Int, MutableSet<Int>> {
        val map = HashMap<Int, MutableSet<Int>>(destinationAddresses.size)
        val nextHops = state.sender.getNextDatabaseHops(destinationAddresses)
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

    private fun updateQueryInProgress(parts: List<OperatorGraphPart>, nextHops: IntArray, senderAddress: Int, queryID: Int) {
        val newQuery = Query(parts, nextHops, senderAddress)
        state.queriesInProgress[queryID] = newQuery
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

    private fun sendPreprocessingPackage(to: Int, dests: IntArray, parts: ByteArray, queryID: Int) {
        state.sender.send(
            to,
            PreprocessingPackage(
                destinationAddresses = dests,
                operatorGraphParts = parts,
                senderAddress = state.ownAddress,
                queryID = queryID,
            )
        )
    }

    private fun setupOperatorGraph(destinationAddresses: IntArray, parts: List<OperatorGraphPart>, senderAddress: Int, queryID: Int) {
        val nextHopToDestsMap = getHopToDestinationsMap(destinationAddresses)
        updateQueryInProgress(parts, nextHopToDestsMap.keys.toIntArray(), senderAddress, queryID)

        for ((hop, dest) in nextHopToDestsMap)
            if (hop == state.ownAddress) {
                chooseOperators(queryID, nextHopToDestsMap.size == 1, senderAddress)
            }
            // TODO Bug: Sende immer weiter herunter wenn man nicht der letzte ist.
            else {
                sendPreprocessingPackage(hop, dest.toIntArray(), OperatorGraphPart.encodeToByteArray(parts), queryID)
            }
    }
}
