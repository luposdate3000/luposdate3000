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
