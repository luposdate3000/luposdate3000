package lupos.s15tripleStoreDistributed

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.Partition
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s05tripleStore.ITripleStoreBulkImport
import lupos.s09physicalOperators.IPOPBase

interface IDistributedGraph {
    /*suspend*/ fun bulkImport(action: /*suspend*/ (ITripleStoreBulkImport) -> Unit)
    /*suspend*/ fun modify(data: Array<ColumnIterator>, type: EModifyType)
    fun getIterator(idx: EIndexPattern, partition: Partition): IPOPBase
    fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): IPOPBase
    /*suspend*/ fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
}
