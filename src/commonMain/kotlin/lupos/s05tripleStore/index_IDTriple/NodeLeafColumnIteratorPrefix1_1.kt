package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.readIntX
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix1_1(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {

    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    suspend override fun next(): Int {
        if (label != 0) {
            var done = false
            while (!done) {
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                }
                var header = node.readInt1(offset)
                offset += 1
                NodeShared.decodeTripleHeader(header) { counter0, counter1, counter2 ->
                    value0 = value0 xor node.readIntX(offset, counter0)
                    offset += counter0
                    value1 = value1 xor node.readIntX(offset, counter1)
                    offset += counter1 + counter2
                    if (value0 > prefix[0]) {
                        _close()
                        value1 = ResultSetDictionary.nullValue
                        done = true
                    } else {
                        done = value0 == prefix[0]
                        updateRemaining() {
if(!done){
value1=ResultSetDictionary.nullValue
}
                            done = true
                        }
                    }
                }
            }
            return value1
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
