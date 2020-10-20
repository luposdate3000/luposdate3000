package lupos.s15tripleStoreDistributed
import lupos.s00misc.Partition
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s00misc.EModifyType
import lupos.s00misc.EIndexPattern
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s09physicalOperators.IPOPBase
import lupos.s16network.ITripleStoreBulkImportDistributed

interface IDistributedTripleStore{
fun getGraphNames(includeDefault: Boolean = false): List<String> 
 suspend fun createGraph(query: IQuery, name: String): IDistributedGraph 
suspend fun dropGraph(query: IQuery, name: String)
 suspend fun clearGraph(query: IQuery, name: String)
 suspend fun getNamedGraph(query: IQuery, name: String): IDistributedGraph
 fun getDefaultGraph(query: IQuery): IDistributedGraph 
suspend fun commit(query: IQuery)
}

interface IDistributedGraph{
suspend fun bulkImport(action: suspend (ITripleStoreBulkImportDistributed) -> Unit) 
 suspend fun modify(data: Array<ColumnIterator>, type: EModifyType)
 fun getIterator(idx: EIndexPattern, partition: Partition): IPOPBase
  fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): IPOPBase
 suspend fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> 
}

var distributedTripleStore:IDistributedTripleStore=object:IDistributedTripleStore{
override fun getGraphNames(includeDefault: Boolean ): List<String> {throw Exception("unreachable")}
 suspend override fun createGraph(query: IQuery, name: String): IDistributedGraph {throw Exception("unreachable")}
suspend override fun dropGraph(query: IQuery, name: String){throw Exception("unreachable")}
 suspend override fun clearGraph(query: IQuery, name: String){throw Exception("unreachable")}
 suspend override fun getNamedGraph(query: IQuery, name: String): IDistributedGraph{throw Exception("unreachable")}
 override fun getDefaultGraph(query: IQuery): IDistributedGraph {throw Exception("unreachable")}
suspend override fun commit(query: IQuery){throw Exception("unreachable")}

}
