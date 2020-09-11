package lupos.s05tripleStore.index_IDTriple


import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix1_2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {

    @JvmField
    var value0 = 0

    @JvmField
    var value2 = 0

    suspend override fun next(): Int {
        if (label != 0) {
            var done = false
            while (!done) {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple101(node, offset, value0, value2) { v0, v2 ->
                    value0 = v0
                    value2 = v2
                }
                if (value0 > prefix[0]) {
                    _close()
                    value2 = ResultSetDictionary.nullValue
                    done = true
                } else {
                    done = value0 == prefix[0]
                    updateRemaining() {
                        if (!done) {
                            value2 = ResultSetDictionary.nullValue
                        }
                        done = true
                    }
                }
            }
            return value2
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
