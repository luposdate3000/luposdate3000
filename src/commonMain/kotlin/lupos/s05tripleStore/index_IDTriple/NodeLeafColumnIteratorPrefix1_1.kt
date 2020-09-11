package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
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
println("NodeLeafColumnIteratorPrefix1_1 :: next")
        if (label != 0) {
            var done = false
            while (!done) {
println("NodeLeafColumnIteratorPrefix1_1 :: loop $remaining")
                if (needsReset) {
                    needsReset = false
                    value0 = 0
                    value1 = 0
                }
                offset += NodeShared.readTriple110(node, offset, value0, value1) { v0, v1 ->
                    value0 = v0
                    value1 = v1
                }
                if (value0 > prefix[0]) {
println("NodeLeafColumnIteratorPrefix1_1 :: too large -> close")
                    _close()
                    value1 = ResultSetDictionary.nullValue
                    done = true
                } else {
                    done = value0 == prefix[0]
if(!done){
println("NodeLeafColumnIteratorPrefix1_1 :: too small -> repeat")
}
                    updateRemaining() {
println("NodeLeafColumnIteratorPrefix1_1 :: update done")
                        if (!done) {
                            value1 = ResultSetDictionary.nullValue
                        }
                        done = true
                    }
                }
            }
            return value1
        } else {
println("NodeLeafColumnIteratorPrefix1_1 :: already closed")
            return ResultSetDictionary.nullValue
        }
    }
}
