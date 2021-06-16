package lupos.simulator_db.dummyImpl

import lupos.shared_inline.File
import lupos.simulator_db.ChoosenOperatorPackage
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IDatabaseState
import lupos.simulator_db.PreprocessingPackage
import lupos.simulator_db.ResultPackage

public class DatabaseSystemDummy : IDatabase {

    public lateinit var state: DatabaseState

    public override fun start(initialState: IDatabaseState) {
        state = DatabaseState(initialState.ownAddress, initialState.allAddresses, initialState.sender, initialState.absolutePathToDataDirectory)
        state.dataFile = "${initialState.absolutePathToDataDirectory}\\file.txt"
        File(state.dataFile).withOutputStream { }
    }

    public override fun activate() {
    }

    public override fun deactivate() {
    }

    public override fun end() {
        File(state.dataFile).deleteRecursively()
    }

    public override fun receive(pck: IDatabasePackage) {
        when (pck) {
            is PreprocessingPackage -> receive(pck)
            is ResultPackage -> receive(pck)
            is ChoosenOperatorPackage -> receive(pck)
        }
    }

    public override fun receiveQuery(sourceAddress: Int, query: ByteArray) {
        state.addressForQueryEndResult = sourceAddress
        val queryString = query.decodeToString()
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
        // TODO use this in the related „ReceivedResultsDummyImpl"
        // wenn dies nicht lokal benutzt wird, einfach weiter im operatorgraphen hochsenden
    }

    private fun receive(pck: ChoosenOperatorPackage) {
        val query = state.queriesInProgress[pck.queryID]!!
        val choosenOperators = query.choosenOperators
        query.answeredByNextHop[query.nextHops.indexOf(pck.senderAddress)] = true
        for (operatorID in pck.operators)
            choosenOperators.add(ReceivedResults(pck.senderAddress, operatorID))

        if (allReplied(query.answeredByNextHop))
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

        // Bei zb. Join Von Tripplestorezugriffen auf den selben knoten
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

        // Es kann passieren, dass mehrere Teilbäume auf dem gleichen Knoten landen,
        // Aber in übergeordneten Operatoren von anderen Knoten berechnet werden,
        // sodass zwar mehrere Operatoren auf dem selben Blattknoten berechnet werden können,
        // jedoch diese Operatoren nichts miteinander zu tun haben.
        // Bei kleinen Bäumen kommt der Fall nicht vor.
        // z.B wenn das selbe trippelmuster mehrfach auftaucht, aber noch etwas dazwischen liegt,
        // kann der Optimizer nicht immer diese Operanden fusionieren.
        for (op in choosenOperators) {
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
        if (!map.containsKey(hop))
            map[hop] = mutableSetOf()
        map[hop]!!.add(dest)
    }

    private fun updateQueryInProgress(parts: List<OperatorGraphPart>, nextHops: IntArray, senderAddress: Int, queryID: Int) {
        val newQuery = Query(parts, nextHops, senderAddress)
        state.queriesInProgress[queryID] = newQuery
    }

    private fun calculate(queryID: Int, isLastHop: Boolean, src: Int) {
        val query = state.queriesInProgress[queryID]!!
        for (part in query.operatorGraphParts)
            if (part.canBeEvaluatedWithoutRemoteDependencies())
                query.choosenOperators.add(part)

        if (isLastHop)
            startEvaluation(src, queryID)
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


    private fun setupOperatorGraph(destinationAddresses: IntArray, parts: List<OperatorGraphPart>, senderAddress: Int,  queryID: Int) {
        val nextHopToDestsMap = getHopToDestinationsMap(destinationAddresses)
        updateQueryInProgress(parts, nextHopToDestsMap.keys.toIntArray(), senderAddress, queryID)

        for ((hop, dest) in nextHopToDestsMap)
            if (hop == state.ownAddress)
                calculate(queryID, nextHopToDestsMap.size == 1, senderAddress)
             else
                sendPreprocessingPackage(hop,dest.toIntArray(), OperatorGraphPart.encodeToByteArray(parts), queryID)
    }
}
