package commonTest

import kotlin.jvm.JvmField
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.fail
import kotlin.test.Test
import lupos.datastructures.b_plus_tree.B_Plus_Tree_StaticCompressed_Int_to_Int
import lupos.datastructures.b_plus_tree.B_Plus_Tree_Uncompressed_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_DifferenceEncoding_Int_OnlyKeys
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_DifferenceEncoding_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_StaticCompressed_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_Static_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_Uncompressed_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_Uncompressed_Int_to_Int_BinarySearch
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_VariableSize_Int_to_Int
import lupos.datastructures.b_plus_tree.Derived_B_Plus_Tree_VariableSizePointers_Int_to_Int
import lupos.datastructures.b_plus_tree.I_B_Plus_Tree
import lupos.datastructures.b_plus_tree.I_B_Plus_Tree_KeyRangeSearch
import lupos.datastructures.b_plus_tree.I_B_Plus_Tree_KeyRangeSearch_OnlyKeys
import lupos.datastructures.b_plus_tree.I_B_Plus_Tree_OnlyKeys
import lupos.datastructures.b_plus_tree.NotFoundException
import lupos.io.buffer.bufferManager

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class BPlusTreeTests {
    fun getFilename(testnumber: String = "") = "../tmp/test/test$testnumber"
    fun testBPlusTreeInsertAndExactSearch(tree: I_B_Plus_Tree<Int, Int>) {
        // initialize and create B_Plus_Tree
        val list = mutableListOf<Pair<Int, Int>>()
        val size = 500
        for (i in 1..size) {
            list += Pair(i, i)
        }
        tree.generate(size, list.asIterable().iterator())
        try {
            for (i in size downTo 1) {
                assertEquals(tree[i], i)
            }
            assertFailsWith<NotFoundException> { tree[2 * size] }
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DETAIL, { e })
            fail(e.key.toString() + " not found!")
        }
        // TODO much more rigorously
    }

    fun testBPlusTreeInsertAndExactSearch_OnlyKeys(tree: I_B_Plus_Tree_OnlyKeys<Int>) {
        // initialize and create B_Plus_Tree
        val list = mutableListOf<Int>()
        val size = 500000
        for (i in 1..size) {
            list += i
        }
        tree.generate(size, list.asIterable().iterator())
        try {
            for (i in size downTo 1) {
                assertEquals(tree[i], true)
            }
            assertEquals(tree[2 * size], false)
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DEBUG, { e })
            fail(e.key.toString() + " not found!")
        }
        // TODO much more rigorously
    }

    fun testBPlusTreeInsertAndRangeSearch(tree: I_B_Plus_Tree_KeyRangeSearch<Int, Int>) {
        val list = mutableListOf<Pair<Int, Int>>()
        val size = 500000
        for (i in 1..size) {
            list += Pair(i, i)
        }
        tree.generate(size, list.asIterable().iterator())
        try {
            val range = tree.range_search(100, 2000)
            var i = 100
            do {
                val result = range()
                assertEquals(result, if (i <= 2000) i else null)
                i++
            } while (result != null)
            val rangeExceedingLimits = tree.range_search(2 * size, 3 * size)
            val result = rangeExceedingLimits()
            assertEquals(result, null)
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DEBUG, { e })
            fail(e.key.toString() + " not found!")
        }
        // TODO much more rigorously
    }

    fun testBPlusTreeInsertAndRangeSearch_OnlyKeys(tree: I_B_Plus_Tree_KeyRangeSearch_OnlyKeys<Int>) {
        val list = mutableListOf<Int>()
        val size = 500000
        for (i in 1..size) {
            list += i
        }
        tree.generate(size, list.asIterable().iterator())
        try {
            val range = tree.range_search(100, 2000)
            var i = 100
            do {
                val result = range()
                assertEquals(result, if (i <= 2000) i else null)
                i++
            } while (result != null)
            assertFailsWith<NotFoundException> { tree.range_search(2 * size, 3 * size) }
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DEBUG, { e })
            fail(e.key.toString() + " not found!")
        }
        // TODO much more rigorously
    }

    @Test
    fun testBPlusTree_Uncompressed_Int_to_Int_ManualGeneration() {
        // build B+-tree
        val filename = getFilename("1")
        val page = bufferManager.getPage(filename, 0)
        var adr = 0L
        page.putByte(adr, 0.toByte()) // root node is leaf node
        adr++
        page.putInt(adr, 4)
        adr += 4
        page.putInt(adr, 0)
        adr += 4
        page.putInt(adr, 0)
        adr += 4
        page.putInt(adr, 1)
        adr += 4
        page.putInt(adr, 1)
        adr += 4
        page.putInt(adr, 2)
        adr += 4
        page.putInt(adr, 2)
        adr += 4
        page.putInt(adr, 3)
        adr += 4
        page.putInt(adr, 3)
        adr += 4
        // now test search
        val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename)
        try {
            // assertEquals(b[0],0);
            assertEquals(b[0], 0)
            assertEquals(b[1], 1)
            assertEquals(b[2], 2)
            assertEquals(b[3], 3)
            assertFailsWith<NotFoundException> { b[4] }
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DEBUG, { e })
            fail(e.key.toString() + " not found!")
        }
        GlobalLogger.log(ELoggerType.TEST_DEBUG, { "Test 1 passed..." })
        // TODO much more rigorously
    }

    @Test
    fun testBPlusTree_ExactSearch_Uncompressed_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_Uncompressed_Int_to_Int(getFilename("2"), 8000, 8000))

    @Test
    fun testBPlusTree_ExactSearch_Uncompressed_Int_to_Int_BinarySearch() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_Uncompressed_Int_to_Int_BinarySearch(getFilename("3"), 8000, 8000))

    @Test
    fun testBPlusTree_ExactSearch_VariableSize_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_VariableSize_Int_to_Int(getFilename("4"), 8000, 8000))

    @Test
    fun testBPlusTree_ExactSearch_VariableSizePointers_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_VariableSizePointers_Int_to_Int(getFilename("5"), 8000, 8000))

    @Test
    fun testBPlusTree_ExactSearch_DifferenceEncoding_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_DifferenceEncoding_Int_to_Int(getFilename("6"), 8000, 8000))

    @Test
    fun testBPlusTree_ExactSearch_Static_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_Static_Int_to_Int(getFilename("7")))

    @Test
    fun testBPlusTree_ExactSearch_StaticCompressed_Int_to_Int() = testBPlusTreeInsertAndExactSearch(Derived_B_Plus_Tree_StaticCompressed_Int_to_Int(getFilename("8")))

    @Test
    fun testBPlusTree_ExactSearch_DifferenceEncoding_Int() = testBPlusTreeInsertAndExactSearch_OnlyKeys(Derived_B_Plus_Tree_DifferenceEncoding_Int_OnlyKeys(getFilename("9"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_Uncompressed_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_Uncompressed_Int_to_Int(getFilename("Range2"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_Uncompressed_Int_to_Int_BinarySearch() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_Uncompressed_Int_to_Int_BinarySearch(getFilename("Range3"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_VariableSize_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_VariableSize_Int_to_Int(getFilename("Range4"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_VariableSizePointers_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_VariableSizePointers_Int_to_Int(getFilename("Range5"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_DifferenceEncoding_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_DifferenceEncoding_Int_to_Int(getFilename("Range6"), 8000, 8000))

    @Test
    fun testBPlusTree_RangeSearch_Static_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_Static_Int_to_Int(getFilename("Range7")))

    @Test
    fun testBPlusTree_RangeSearch_StaticCompressed_Int_to_Int() = testBPlusTreeInsertAndRangeSearch(Derived_B_Plus_Tree_StaticCompressed_Int_to_Int(getFilename("Range8")))

    @Test
    fun testBPlusTree_RangeSearch_DifferenceEncoding_Int() = testBPlusTreeInsertAndRangeSearch_OnlyKeys(Derived_B_Plus_Tree_DifferenceEncoding_Int_OnlyKeys(getFilename("Range9"), 8000, 8000))

    // TODO: test of sip_search! Similar to the following method...
    fun testBPlusTree4b() {
        val filename = getFilename("4b")
        // initialize and create B_Plus_Tree
        val b = B_Plus_Tree_StaticCompressed_Int_to_Int(filename) // B_Plus_Tree_Uncompressed_Int_to_Int(filename)
        val list = mutableListOf<Pair<Int, Int>>()
        val size = 500000
        for (i in 1..size) {
            list += Pair(i, i)
        }
        b.generate(size, list.asIterable().iterator())
        try {
            val range = b.sip_search(100, size) // error for 51712
            var i = 101
            do {
                val result = range(i)
                GlobalLogger.log(ELoggerType.TEST_DEBUG, { "$i: $result" })
                i = i * 2
            } while (result != null)
        } catch (e: NotFoundException) {
            GlobalLogger.log(ELoggerType.TEST_DEBUG, { e })
            fail(e.key.toString() + " not found!")
        }
        // TODO much more rigorously
    }
}
