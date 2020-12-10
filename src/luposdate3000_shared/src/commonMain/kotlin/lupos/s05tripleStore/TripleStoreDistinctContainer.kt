package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import kotlin.jvm.JvmField

class TripleStoreDistinctContainer(@JvmField val first: String, @JvmField val second: TripleStoreIndex, @JvmField val importField: (ITripleStoreBulkImport) -> IntArray, @JvmField val idx: EIndexPattern)
