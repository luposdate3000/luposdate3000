package lupos.s15tripleStoreDistributed
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s05tripleStore.ITripleStoreBulkImport
import lupos.s09physicalOperators.IPOPBase
public interface IDistributedGraph {
  public   /*suspend*/ fun bulkImport(action: /*suspend*/ (ITripleStoreBulkImport) -> Unit)
  public   /*suspend*/ fun modify(data: Array<ColumnIterator>, type: EModifyType)
  public   fun getIterator(idx: EIndexPattern, partition: Partition): IPOPBase
  public   fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): IPOPBase
  public   /*suspend*/ fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
}
