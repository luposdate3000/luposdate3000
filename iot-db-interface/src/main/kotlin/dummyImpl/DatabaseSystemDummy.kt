package dummyImpl

import ChoosenOperatorPackage
import IDatabasePackage
import IDatabase
import IDatabaseState
import PreprocessingPackage
import ResultPackage
import java.io.File

class DatabaseSystemDummy : IDatabase {

    lateinit var state: DatabaseState


    override fun start(initialState: IDatabaseState) {
        state = DatabaseState(initialState.ownAddress, initialState.allAddresses, initialState.sender, initialState.absolutePathToDataDirectory)
        state.dataFile = File("${initialState.absolutePathToDataDirectory}\\file.txt")
        state.dataFile.createNewFile()
    }

    override fun activate(state: IDatabaseState) {
        this.state = state as DatabaseState
    }

    override fun deactivate(): IDatabaseState {
        return state
    }

    override fun end() {
        state.dataFile.delete()
    }

    override fun receive(pck: IDatabasePackage) {
        when(pck) {
            is PreprocessingPackage -> receive(pck)
            is ResultPackage -> receive(pck)
            is ChoosenOperatorPackage -> receive(pck)
        }
    }

    override fun receiveQuery(from: Int, query: ByteArray) {
        state.addressForQueryEndResult = from
        val queryString = query.decodeToString()
        if(queryString.contains("INSERT DATA")) {
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
        state.dataFile.appendText(data)
    }

    private fun receive(pck: PreprocessingPackage) {
        val operatorGraphParts = OperatorGraphPart.fromByteArray(pck.operatorGraphParts)
        setupOperatorGraph(pck.destinationAddresses, operatorGraphParts, pck.senderAddress, pck.queryID)
    }

    private fun receive(pck: ResultPackage) {
        // TODO use this in the related „ReceivedResultsDummyImpl"
        //wenn dies nicht lokal benutzt wird, einfach weiter im operatorgraphen hochsenden
    }

    private fun receive(pck: ChoosenOperatorPackage) {
        val query = state.queriesInProgress[pck.queryID]!!
        val choosenOperators = query.choosenOperators
        query.answeredByNextHop[query.nextHops.indexOf(pck.senderAddress)] = true
        for (operatorID in pck.operators)
            choosenOperators.add(ReceivedResults(pck.senderAddress, operatorID))

        if(allReplied(query.answeredByNextHop))
            startEvaluation(query.parentAddress, pck.queryID)
    }

    private fun allReplied(answeredByNextHop: BooleanArray): Boolean {
        for (hasReplied in answeredByNextHop)
            if (!hasReplied)
                return false
        return true
    }


    private fun startEvaluation(senderAddress: Int, queryID: Int) {

        val query = state.queriesInProgress[queryID]!!
        val operatorGraphParts = query.operatorGraphParts
        val choosenOperators = query.choosenOperators

        //Bei zb. Join Von Tripplestorezugriffen auf den selben knoten
        for (part in operatorGraphParts) {
            if (part.canBeEvaluatedWithTheseDependencies(choosenOperators)) {
                val dep = part.mergeAndGetDependencies(choosenOperators)
                choosenOperators.removeAll(dep)
                choosenOperators.add(part) // alle unnötigen netzwerk-möglichkeiten eliminieren, wenn mehrere query-parts auf der gleichen node berechnet werden
            }

        }
        state.sender.send(
            senderAddress,
// anzeigen, was man berechnen wird, siehe #2
            ChoosenOperatorPackage(
                destinationAddress = senderAddress,
                senderAddress = state.ownAddress,
                operators = choosenOperators.map { it.getUUID() }.toIntArray(),
                queryID = queryID,
            )
        )

        //Es kann passieren, dass mehrere Teilbäume auf dem gleichen Knoten landen,
        //Aber in übergeordneten Operatoren von anderen Knoten berechnet werden,
        //sodass zwar mehrere Operatoren auf dem selben Blattknoten berechnet werden können,
        //jedoch diese Operatoren nichts miteinander zu tun haben.
        //Bei kleinen Bäumen kommt der Fall nicht vor.
        //z.B wenn das selbe trippelmuster mehrfach auftaucht, aber noch etwas dazwischen liegt,
        //kann der Optimizer nicht immer diese Operanden fusionieren.
        for (op in choosenOperators) {
// ergebnisse senden
            state.sender.send(senderAddress,
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

    private fun getNextHops(destinationAddresses: IntArray): HashMap<Int, MutableSet<Int>> {
        val map = HashMap<Int, MutableSet<Int>>(destinationAddresses.size)
        val nextHops = state.sender.getNextDBHopsFor(destinationAddresses)
        for (i in nextHops.indices) {
            if (!map.containsKey(nextHops[i]))
                map[nextHops[i]] = mutableSetOf()
            map[nextHops[i]]!!.add(destinationAddresses[i])
        }
        return map
    }


    private fun setupOperatorGraph(
        destinationAddresses: IntArray,
        parts: List<OperatorGraphPart>,
        senderAddress: Int,
        queryID: Int,
    ) {

        val nextHopMap = getNextHops(destinationAddresses)
        val newQuery = Query(parts, nextHopMap.keys.toIntArray(), senderAddress)
        state.queriesInProgress[queryID] = newQuery

        for ((hop, dest) in nextHopMap) {
            if (hop == state.ownAddress) {
// selber berechnen
                val q = state.queriesInProgress[queryID]!!
                val choosenOperators = q.choosenOperators
                for (part in parts) {
                    if (part.canBeEvaluatedWithoutRemoteDependencies()) {
                        choosenOperators.add(part)
                    }
                }
                if (nextHopMap.size == 1) {
// wenn ich ganz unten im operator Graph bin ... also der triple store
                    startEvaluation(senderAddress, queryID)
                }
            } else {
// weitersenden Richtung triple store
                state.sender.send(hop,
                    PreprocessingPackage(
                        destinationAddresses = dest.toIntArray(),
                        operatorGraphParts = OperatorGraphPart.encodeToByteArray(parts), // DB can filter here to reduce network-amount
                        senderAddress = state.ownAddress,
                        queryID = queryID,
                    )
                )
            }
        }
    }

}

