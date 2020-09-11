package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIterator0(node: ByteArray, nodeid: Int, lock: ReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
    @JvmField
    var value = 0

    suspend override fun next(): Int {
        value = next_helper(value, NodeShared::readTriple100)
        return value
    }

    suspend override fun nextSIP(minValue: Int, crossinline skippedElements: (counter: Int) -> Unit): Int {
        value = nextSIP_helper(value, minValue, skippedElements, NodeShared::readTriple100)
        println("usedSIPMinValue")
        return value
    }

    override suspend open fun nextSIP(skipCount: Int): Int {
        value = nextSIP_helper(value, skipCount, NodeShared::readTriple100)
        println("usedSIPSkip")
        return value
    }
}
