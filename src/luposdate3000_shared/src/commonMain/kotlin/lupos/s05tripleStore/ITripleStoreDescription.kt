package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator

public interface ITripleStoreDescription {
    public fun getIndices(idx: EIndexPattern): List<ITripleStoreIndexDescription>
    public fun modify(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType)
    public fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase
    public fun getHistogram(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
}
