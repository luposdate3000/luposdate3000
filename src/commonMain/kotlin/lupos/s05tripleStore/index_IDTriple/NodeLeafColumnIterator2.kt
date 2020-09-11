package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIterator2(node: ByteArray, nodeid: Int, lock: ReadWriteLock) : NodeLeafColumnIterator(node, nodeid, lock) {
    suspend override fun next(): Int {
        if (label != 0) {
            if (needsReset) {
                needsReset = false
                value = 0
            }
            offset += NodeShared.readTriple001(node, offset, value) { v ->
                value = v
            }
            updateRemaining()
            return value
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
