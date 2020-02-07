package lupos.s12p2p

import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStore
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.Endpoint


object P2PLocalDummy {
    val nodeData = mutableMapOf<String, PersistentStore>()

inline    fun execInsertOnNamedNode(nodeName: String, data: XMLElement) {
        var pstore = nodeData[nodeName]
        if (pstore == null) {
            pstore = PersistentStore()
            nodeData[nodeName] = pstore
        }
        val store = pstore.getDefaultGraph()
        val transactionID = pstore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        store.addData(transactionID, POPImportFromXml(dictionary, data))
        pstore.commit(transactionID)
    }

    inline fun execOnNamedNode(dictionary:ResultSetDictionary,transactionID: Long, nodeName: String, pop: OPBase): OPBase {
/*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        val optimizer = KeyDistributionOptimizer(transactionID, ResultSetDictionary())
        optimizer.store = nodeData[nodeName]!!
        val res = optimizer.optimizeCall(pop)
        return res
    }

    inline fun execTruncate() {
        nodeData.clear()
    }

    inline fun process_peers_list(): String {
/*nice to have, but not required*/
        return ""
    }

    inline fun process_peers_join(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        return ""
    }

    suspend inline fun start(bootstrap: String?) {
    }
}
