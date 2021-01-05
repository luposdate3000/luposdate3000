package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
import kotlin.jvm.JvmField
public class EnabledPartitionContainer(@JvmField public val index: MutableSet<EIndexPattern>, @JvmField public val column: Int, @JvmField public val partitionCount: Int)
