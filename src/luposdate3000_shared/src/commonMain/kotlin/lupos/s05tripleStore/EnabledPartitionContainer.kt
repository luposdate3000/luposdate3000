package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern

class EnabledPartitionContainer(@JvmField val index: MutableSet<EIndexPattern>, @JvmField val column: Int, @JvmField val partitionCount: Int)
