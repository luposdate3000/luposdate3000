package lupos.s12p2p
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultSet
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.noinput.*
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer


object P2PLocalDummy {
    val nodeData = mutableMapOf<String, DistributedTripleStore>()

    inline fun execInsertOnNamedNode(nodeName: String, data: XMLElement) {
        var pstore = nodeData[nodeName]
        if (pstore == null) {
            pstore = DistributedTripleStore()
            nodeData[nodeName] = pstore
        }
        val store = pstore.getDefaultGraph()
        val transactionID = pstore.nextTransactionID()
        val dictionary = ResultSetDictionary()
        store.addData(transactionID, POPImportFromXml(dictionary, data))
        pstore.commit(transactionID)
    }

    inline fun execOnNamedNode(dictionary: ResultSetDictionary, transactionID: Long, nodeName: String, pop: OPBase): OPBase {
/*execute "pop" on remote node - if it exist - otherwiese throw an exception*/
        val optimizer = KeyDistributionOptimizer(transactionID, ResultSetDictionary())
        optimizer.store = nodeData[nodeName]!!
        val res = optimizer.optimizeCall(pop)
        return res
    }

    inline fun execGraphClearAll() {
        nodeData.clear()
    }
fun execGraphOperation(name:String,type:GraphOperationType) {
for((k,v) in nodeData){
when (type){
                GraphOperationType.CLEAR->v.localStore.clearGraph(name)
                GraphOperationType.CREATE->v.localStore.createGraph(name)
               GraphOperationType.DROP->v.localStore.dropGraph(name)
        }
}
}

    inline fun process_peers_list(): String {
/*nice to have, but not required*/
        return ""
    }

    inline fun process_peers_join(hostname: String?): String {
/*just a dummy ... should be removed if there is a real p2p*/
        return ""
    }
fun process_peers_self_test(): String{
return ""
}
fun process_peers_join_internal(hostname: String?): String {
return ""
}
    suspend inline fun start(bootstrap: String?) {
    }
}
