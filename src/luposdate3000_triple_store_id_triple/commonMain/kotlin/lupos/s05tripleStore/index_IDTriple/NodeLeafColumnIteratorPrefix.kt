package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock

internal abstract class NodeLeafColumnIteratorPrefix(node: ByteArray, nodeid: Int, @JvmField val prefix: IntArray, lock: MyReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock)
