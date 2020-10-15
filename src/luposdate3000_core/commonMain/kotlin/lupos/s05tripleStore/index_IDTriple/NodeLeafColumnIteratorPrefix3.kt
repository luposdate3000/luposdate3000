package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.ReadWriteLock
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix3(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {
    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    @JvmField
    var value2 = 0
    suspend override fun next(): Int {
        if (label == 3) {
            label = 1
            __init()
        }
        if (label != 0) {
            var done = false
            while (!done) {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                    value2 = 0
                }
                offset += NodeShared.readTriple111(node, offset, value0, value1, value2) { v0, v1, v2 ->
                    value0 = v0
                    value1 = v1
                    value2 = v2
                }
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1]) || (value0 == prefix[0] && value1 == prefix[1] && value2 > prefix[2])) {
                    _close()
                    value2 = ResultSetDictionary.nullValue
                    done = true
                } else {
                    done = value0 == prefix[0] && value1 == prefix[1] && value2 == prefix[2]
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
