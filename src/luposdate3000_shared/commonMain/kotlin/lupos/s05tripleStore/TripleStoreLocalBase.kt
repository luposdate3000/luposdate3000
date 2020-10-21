package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle

class TripleStoreDistinctContainer(@JvmField val first: String, @JvmField val second: TripleStoreIndex, @JvmField val importField: (ITripleStoreBulkImport) -> IntArray, @JvmField val idx: EIndexPattern)
class EnabledPartitionContainer(@JvmField val index: MutableSet<EIndexPattern>, @JvmField val column: Int, @JvmField val partitionCount: Int)
interface ITripleStoreLocalBase {
    fun getEnabledPartitions(): Array<EnabledPartitionContainer>
    suspend fun flush()
    suspend fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int>
    suspend fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    suspend fun import(dataImport: ITripleStoreBulkImport)
    suspend fun commit(query: IQuery)
    suspend fun clear()
    suspend fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType)
    fun modify(query: IQuery, dataModify: MutableList<Int>, type: EModifyType)
}

interface ITripleStoreBulkImport {
fun getData(idx:EIndexPattern):IntArray
}
