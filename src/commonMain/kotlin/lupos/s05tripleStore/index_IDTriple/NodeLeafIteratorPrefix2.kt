package lupos.s05tripleStore.index_IDTriple

import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.ReadWriteLock
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafIteratorPrefix2(node: ByteArray, prefix: IntArray) : NodeLeafIteratorPrefix(node, prefix) {
    override fun checkTooSmall(): Boolean {
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] < prefix[1])
    }

    override fun checkNotTooLarge(): Boolean {
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] <= prefix[1])
    }
}
