package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern

class TripleStoreDistinctContainer(@JvmField val first: String, @JvmField val second: TripleStoreIndex, @JvmField val importField: (ITripleStoreBulkImport) -> IntArray, @JvmField val idx: EIndexPattern)
