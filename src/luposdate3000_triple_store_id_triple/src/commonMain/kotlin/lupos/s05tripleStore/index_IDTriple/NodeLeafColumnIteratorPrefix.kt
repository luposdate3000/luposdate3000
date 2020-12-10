package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.MyReadWriteLock
import kotlin.jvm.JvmField
internal abstract class NodeLeafColumnIteratorPrefix(node: ByteArray, nodeid: Int, @JvmField val prefix: IntArray, lock: MyReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock)
