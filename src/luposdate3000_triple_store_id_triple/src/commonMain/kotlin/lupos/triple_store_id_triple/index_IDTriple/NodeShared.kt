/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.Compressor
internal object NodeShared {
    const val MAX_TRIPLE_SIZE = 13

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setNodeType(node: ByteArray, type: Int) {
        BufferManagerPage.writeInt4(node, 0, type)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getNodeType(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 0)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setNextNode(node: ByteArray, nextNode: Int) {
        BufferManagerPage.writeInt4(node, 8, nextNode)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getNextNode(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 8)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun setTripleCount(node: ByteArray, count: Int) {
        BufferManagerPage.writeInt4(node, 4, count)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getTripleCount(node: ByteArray): Int {
        return BufferManagerPage.readInt4(node, 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readTriple000(node: ByteArray, offset: Int): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1 + counter2
        }
        return localOff - offset
    }

    internal inline fun readTriple111(node: ByteArray, offset: Int, d0: DictionaryValueType, d1: DictionaryValueType, d2: DictionaryValueType, crossinline action: (d0: DictionaryValueType, d1: DictionaryValueType, d2: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter0)
            localOff += counter0
            val v1 = d1 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter1)
            localOff += counter1
            val v2 = d2 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter2)
            localOff += counter2
            action(v0, v1, v2)
        }
        return localOff - offset
    }

    internal inline fun readTriple010(node: ByteArray, offset: Int, d1: DictionaryValueType, crossinline action: (d1: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0
            val v1 = d1 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter1)
            localOff += counter1 + counter2
            action(v1)
        }
        return localOff - offset
    }

    internal inline fun readTriple001(node: ByteArray, offset: Int, d2: DictionaryValueType, crossinline action: (d2: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            localOff += counter0 + counter1
            val v2 = d2 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter2)
            localOff += counter2
            action(v2)
        }
        return localOff - offset
    }

    internal inline fun readTriple100(node: ByteArray, offset: Int, d0: DictionaryValueType, crossinline action: (d0: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter0)
            localOff += counter0 + counter1 + counter2
            action(v0)
        }
        return localOff - offset
    }

    internal inline fun readTriple110(node: ByteArray, offset: Int, d0: DictionaryValueType, d1: DictionaryValueType, crossinline action: (d0: DictionaryValueType, d1: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter0)
            localOff += counter0
            val v1 = d1 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter1)
            localOff += counter1 + counter2
            action(v0, v1)
        }
        return localOff - offset
    }

    internal inline fun readTriple101(node: ByteArray, offset: Int, d0: DictionaryValueType, d2: DictionaryValueType, crossinline action: (d0: DictionaryValueType, d2: DictionaryValueType) -> Unit): Int {
        val header = BufferManagerPage.readInt1(node, offset)
        var localOff = offset + 1
        Compressor.decodeTripleHeader(header) { counter0, counter1, counter2 ->
            val v0 = d0 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter0)
            localOff += counter0 + counter1
            val v2 = d2 xor DictionaryValueHelper.fromByteArrayX(node, localOff, counter2)
            localOff += counter2
            action(v0, v2)
        }
        return localOff - offset
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun writeTriple(node: ByteArray, offset: Int, l: DictionaryValueTypeArray, d: DictionaryValueTypeArray): Int {
        val b0 = l[0] xor d[0]
        val b1 = l[1] xor d[1]
        val b2 = l[2] xor d[2]
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:148"/*SOURCE_FILE_END*/ }, { d[0] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:149"/*SOURCE_FILE_END*/ }, { d[1] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:150"/*SOURCE_FILE_END*/ }, { d[2] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:151"/*SOURCE_FILE_END*/ }, { l[0] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:152"/*SOURCE_FILE_END*/ }, { l[1] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:153"/*SOURCE_FILE_END*/ }, { l[2] >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:154"/*SOURCE_FILE_END*/ }, { b0 >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:155"/*SOURCE_FILE_END*/ }, { b1 >= 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:156"/*SOURCE_FILE_END*/ }, { b2 >= 0 })
        var counter0 = DictionaryValueHelper.numberOfBytesUsed(b0)
        var counter1 = DictionaryValueHelper.numberOfBytesUsed(b1)
        var counter2 = DictionaryValueHelper.numberOfBytesUsed(b2)
        Compressor.encodeTripleHeader(counter0, counter1, counter2) { header, corrected0, corrected1, corrected2 ->
            BufferManagerPage.writeInt1(node, offset, header)
            counter0 = corrected0
            counter1 = corrected1
            counter2 = corrected2
        }
        var localOff = offset + 1
        DictionaryValueHelper.toByteArrayX(node, localOff, b0, counter0)
        localOff += counter0
        DictionaryValueHelper.toByteArrayX(node, localOff, b1, counter1)
        localOff += counter1
        DictionaryValueHelper.toByteArrayX(node, localOff, b2, counter2)
        localOff += counter2
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:174"/*SOURCE_FILE_END*/ },
            {
                var size = readTriple000(node, offset)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:177"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple100(node, offset, l[0]) { n0 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:179"/*SOURCE_FILE_END*/ }, { n0 == d[0] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:181"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple010(node, offset, l[1]) { n1 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:183"/*SOURCE_FILE_END*/ }, { n1 == d[1] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:185"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple001(node, offset, l[2]) { n2 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:187"/*SOURCE_FILE_END*/ }, { n2 == d[2] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:189"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple110(node, offset, l[0], l[1]) { n0, n1 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:191"/*SOURCE_FILE_END*/ }, { n0 == d[0] })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:192"/*SOURCE_FILE_END*/ }, { n1 == d[1] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:194"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple101(node, offset, l[0], l[2]) { n0, n2 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:196"/*SOURCE_FILE_END*/ }, { n0 == d[0] })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:197"/*SOURCE_FILE_END*/ }, { n2 == d[2] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:199"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
                size = readTriple111(node, offset, l[0], l[1], l[2]) { n0, n1, n2 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:201"/*SOURCE_FILE_END*/ }, { n0 == d[0] })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:202"/*SOURCE_FILE_END*/ }, { n1 == d[1] })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:203"/*SOURCE_FILE_END*/ }, { n2 == d[2] })
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:205"/*SOURCE_FILE_END*/ }, { size == localOff - offset })
            }
        )
        l[0] = d[0]
        l[1] = d[1]
        l[2] = d[2]
        return localOff - offset
    }
}
