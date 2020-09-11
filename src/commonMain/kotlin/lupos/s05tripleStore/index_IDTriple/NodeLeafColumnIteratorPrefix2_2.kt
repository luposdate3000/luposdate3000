package lupos.s05tripleStore.index_IDTriple


import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator

class NodeLeafColumnIteratorPrefix2_2(node: ByteArray, nodeid: Int, prefix: IntArray, lock: ReadWriteLock) : NodeLeafColumnIteratorPrefix(node, nodeid, prefix, lock) {

    @JvmField
    var value0 = 0

    @JvmField
    var value1 = 0

    @JvmField
    var value2 = 0

    suspend override fun next(): Int {
println("NodeLeafColumnIteratorPrefix2_2 :: next")
        if (label != 0) {
            var done = false
            while (!done) {
println("NodeLeafColumnIteratorPrefix2_2 :: loop $remaining")
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
println("NodeLeafColumnIteratorPrefix2_2 :: $value0 $value1 $value2")
                if (value0 > prefix[0] || (value0 == prefix[0] && value1 > prefix[1])) {
println("NodeLeafColumnIteratorPrefix2_2 :: too large -> close")
                    _close()
                    value2 = ResultSetDictionary.nullValue
                    done = true
                } else {
                    done = value0 == prefix[0] && value1 == prefix[1]
if(!done){
println("NodeLeafColumnIteratorPrefix2_2 :: too small -> repeat")
}
                    updateRemaining() {
println("NodeLeafColumnIteratorPrefix2_2 :: update done")
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
