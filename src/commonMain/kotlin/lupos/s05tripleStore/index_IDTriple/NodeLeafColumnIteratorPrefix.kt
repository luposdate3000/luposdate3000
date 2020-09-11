package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

abstract class NodeLeafColumnIteratorPrefix(node: ByteArray, nodeid: Int, @JvmField val prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
}
