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

interface ITripleStoreBulkImport {
    fun getData(idx: EIndexPattern): IntArray
    fun getIdx(): Int
    fun insert(si: Int, pi: Int, oi: Int)
    fun finishImport()
}
