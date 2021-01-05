package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
import kotlin.jvm.JvmField
public class TripleStoreDistinctContainer(@JvmField public val first: String, @JvmField public val second: TripleStoreIndex, @JvmField public val importField: (ITripleStoreBulkImport) -> IntArray, @JvmField public val idx: EIndexPattern)
