package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.MyReadWriteLock
import lupos.s04logicalOperators.iterator.ColumnIterator

abstract class NodeLeafColumnIteratorPrefix(node: ByteArray, nodeid: Int, @JvmField val prefix: IntArray, lock: MyReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
}
