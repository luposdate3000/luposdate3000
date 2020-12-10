package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import kotlin.jvm.JvmField

class EnabledPartitionContainer(@JvmField val index: MutableSet<EIndexPattern>, @JvmField val column: Int, @JvmField val partitionCount: Int)
