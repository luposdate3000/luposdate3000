package lupos.shared.inline

internal object Compressor{
 private val decodeTripleHeaderMapA = intArrayOf(0, 1, 2, 8)
    private val decodeTripleHeaderMapBC = intArrayOf(0, 1, 2, 3, 4, 5, 6, 8)
    internal inline fun decodeTripleHeader(header: Int, crossinline action: (counter0: Int, counter1: Int, counter2: Int) -> Unit) {
        val a = decodeTripleHeaderMapA[(header shr 6)and 0x3]
        val b = decodeTripleHeaderMapBC[(header shr 3)and 0x7]
        val c = decodeTripleHeaderMapBC[header and 0x7]
        action(a, b, c)
    }
    private val encodeTripleHeaderMapA = intArrayOf(0, 0x40, 0x80, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0)
    private val encodeTripleHeaderMapB = intArrayOf(0, 0x08, 0x10, 0x18, 0x20, 0x28, 0x30, 0x38, 0x38)
    private val encodeTripleHeaderMapC = intArrayOf(0, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x07)
    private val encodeTripleHeaderCorrectA = intArrayOf(0, 1, 2, 8, 8, 8, 8, 8, 8)
    private val encodeTripleHeaderCorrectBC = intArrayOf(0, 1, 2, 3, 4, 5, 6, 8, 8)
    internal inline fun encodeTripleHeader(counter0: Int, counter1: Int, counter2: Int, crossinline action: (header: Int, corrected0: Int, corrected1: Int, corrected2: Int) -> Unit) {
        var header = 0
        val corrected0: Int = encodeTripleHeaderCorrectA[counter0]
        header = encodeTripleHeaderMapA[counter0]
        val corrected1: Int = encodeTripleHeaderCorrectBC[counter1]
        header = header or encodeTripleHeaderMapB[counter1]
        val corrected2: Int = encodeTripleHeaderCorrectBC[counter2]
        header = header or encodeTripleHeaderMapC[counter2]
        action(header, corrected0, corrected1, corrected2)
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:78"/*SOURCE_FILE_END*/ },
            {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:80"/*SOURCE_FILE_END*/ }, { counter0 <= corrected0 })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:81"/*SOURCE_FILE_END*/ }, { counter1 <= corrected1 })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:82"/*SOURCE_FILE_END*/ }, { counter2 <= corrected2 })
                decodeTripleHeader(header) { c0, c1, c2 ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:84"/*SOURCE_FILE_END*/ }, { c0 == corrected0 }, { "$c0 == $corrected0" })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:85"/*SOURCE_FILE_END*/ }, { c1 == corrected1 }, { "$c1 == $corrected1" })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_id_triple/src/commonMain/kotlin/lupos/triple_store_id_triple/index_IDTriple/NodeShared.kt:86"/*SOURCE_FILE_END*/ }, { c2 == corrected2 }, { "$c2 == $corrected2" })
                }
            }
        )
    }
}
