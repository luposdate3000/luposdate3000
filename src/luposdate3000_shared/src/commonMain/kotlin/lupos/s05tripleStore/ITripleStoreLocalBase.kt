package lupos.s05tripleStore
import lupos.s00misc.EModifyType
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
public interface ITripleStoreLocalBase {
    public fun dropStore()
    public fun getEnabledPartitions(): Array<EnabledPartitionContainer>
    /*suspend*/ public fun flush()
    /*suspend*/ public fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int>
    /*suspend*/ public fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    /*suspend*/ public fun import(dataImport: ITripleStoreBulkImport)
    /*suspend*/ public fun commit(query: IQuery)
    /*suspend*/ public fun clear()
    /*suspend*/ public fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType)
    public fun modify(query: IQuery, dataModify: MutableList<Int>, type: EModifyType)
}
