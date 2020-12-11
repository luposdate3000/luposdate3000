package lupos.s05tripleStore
import lupos.s00misc.EModifyType
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
interface ITripleStoreLocalBase {
    fun dropStore()
    fun getEnabledPartitions(): Array<EnabledPartitionContainer>
    /*suspend*/ fun flush()
    /*suspend*/ fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int>
    /*suspend*/ fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    /*suspend*/ fun import(dataImport: ITripleStoreBulkImport)
    /*suspend*/ fun commit(query: IQuery)
    /*suspend*/ fun clear()
    /*suspend*/ fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType)
    fun modify(query: IQuery, dataModify: MutableList<Int>, type: EModifyType)
}
