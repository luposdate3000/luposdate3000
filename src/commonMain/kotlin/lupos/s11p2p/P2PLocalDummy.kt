package lupos.s11p2p

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
import lupos.s03buildOperatorGraph.*
import lupos.s05logicalOptimisation.LogicalOptimizer
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.*
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s08tripleStore.TripleStore
import lupos.s09physicalOptimisation.PhysicalOptimizer
import lupos.s10outputResult.QueryResultToXML
import lupos.s11p2p.*
import lupos.s13endpoint.*
import lupos.s13endpoint.Endpoint


object P2PLocalDummy {
    val nodeData = mutableMapOf<String, TripleStore>()

    fun insertOnNamedNode(nodeName: String, data: XMLElement) {
        val store = TripleStore()
        store.addData(POPImportFromXml(data))
        nodeData[nodeName] = store
println("ZZZ"+nodeName+data.toPrettyString())
    }

    fun execOnNamedNode(nodeName: String, pop: OPBase): OPBase {
/*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        val optimizer = PhysicalOptimizer()
        optimizer.store = nodeData[nodeName]!!
        val res= optimizer.optimize(pop)
println("YYY"+nodeName)
println(pop)
println(res)
return res
    }

    fun execTruncate() {
println("AAA")
        nodeData.clear()
    }

    fun process_peers_list(): String {
/*nice to have, but not required*/
        return ""
    }

    fun process_peers_join(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        return ""
    }

    suspend fun start(bootstrap: String?) {
    }
}
