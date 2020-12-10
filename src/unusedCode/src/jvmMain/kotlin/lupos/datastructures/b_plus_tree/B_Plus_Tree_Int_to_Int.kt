package lupos.datastructures.b_plus_tree

import lupos.s01io.buffer.PAGESIZE
import lupos.s01io.buffer.compareInt
import lupos.s01io.buffer.deserializeCompressedInt
import lupos.s01io.buffer.deserializeInt
import lupos.s01io.buffer.serializeCompressedInt
import lupos.s01io.buffer.serializeInt
import lupos.s01io.buffer.serializedSizeOfCompressedInt
import lupos.s01io.buffer.serializedSizeOfInt
import kotlin.jvm.JvmField

// this class avoids virtual method calls, which speeds up processing of Big Data
class B_Plus_Tree_Uncompressed_Int_to_Int(@JvmField val filename: String, @JvmField val k: Int = 1000, @JvmField val k_star: Int = 500) {
    val bplustree = B_Plus_Tree<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    inline fun binarySearch(key: Int) = bplustree.binarySearch(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = 4,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    inline fun range_binary_search(keyLeft: Int, keyRight: Int) = bplustree.range_binary_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = 4,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    inline fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            size, iterator, k, k_star, PAGESIZE,
            serializeKey = ::serializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            serializeValue = ::serializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_Uncompressed_Int_to_Int(@JvmField val tree: B_Plus_Tree_Uncompressed_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_Uncompressed_Int_to_Int(filename, k, k_star))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

// this class introduces virtual method calls, but implements a generic interface
// this class applies binary search
inline class Derived_B_Plus_Tree_Uncompressed_Int_to_Int_BinarySearch(@JvmField val tree: B_Plus_Tree_Uncompressed_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_Uncompressed_Int_to_Int(filename, k, k_star))

    override fun get(key: Int): Int = this.tree.binarySearch(key)
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_binary_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_VariableSize_Int_to_Int(@JvmField val filename: String, @JvmField val k: Int = 1000, @JvmField val k_star: Int = 500) {
    val bplustree = B_Plus_Tree<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            size, iterator, k, k_star, PAGESIZE,
            serializeKey = ::serializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            serializeValue = ::serializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_VariableSize_Int_to_Int(@JvmField val tree: B_Plus_Tree_VariableSize_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_VariableSize_Int_to_Int(filename, k, k_star))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_VariableSizePointers_Int_to_Int(@JvmField val filename: String, @JvmField val k: Int = 1000, @JvmField val k_star: Int = 500) { // val k:Int = 1000, @JvmField val k_star:Int = 500){
    val bplustree = B_Plus_Tree_VariableSizePointers<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            size, iterator, k, k_star, PAGESIZE,
            serializeKey = ::serializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            serializeValue = ::serializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_VariableSizePointers_Int_to_Int(@JvmField val tree: B_Plus_Tree_VariableSizePointers_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_VariableSizePointers_Int_to_Int(filename, k, k_star))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_DifferenceEncoding_Int_to_Int(@JvmField val filename: String, @JvmField val k: Int = 1000, @JvmField val k_star: Int = 500) {
    val bplustree = B_Plus_Tree_Difference_Encoding<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        innerNodeDeserializerDiff = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerKeyDiff = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        innerNodeDeserializerDiff = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerKeyDiff = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        keyDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        keyDiffDeserializer = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        valueDeserializer = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt,
        deserializePointer = ::deserializeCompressedInt,
        serializedSizeOfPointer = ::serializedSizeOfCompressedInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            size, iterator, k, k_star, PAGESIZE,
            serializeKey = ::serializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            serializeKeyDiff = ::serializeInt,
            serializedSizeOfKeyDiff = ::serializedSizeOfInt,
            serializeValue = ::serializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_DifferenceEncoding_Int_to_Int(@JvmField val tree: B_Plus_Tree_DifferenceEncoding_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_DifferenceEncoding_Int_to_Int(filename, k, k_star))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_DifferenceEncoding_Int(@JvmField val filename: String, @JvmField val k: Int = 1000, @JvmField val k_star: Int = 500) {
    val bplustree = B_Plus_Tree_Difference_Encoding_OnlyKeys<Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        innerNodeDeserializerDiff = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerKeyDiff = ::deserializeInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        innerNodeDeserializerDiff = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerKeyDiff = ::deserializeInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        keyDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        keyDiffDeserializer = ::deserializeInt,
        serializedSizeOfKeyDiff = ::serializedSizeOfInt,
        deserializePointer = ::deserializeCompressedInt,
        serializedSizeOfPointer = ::serializedSizeOfCompressedInt
    )

    fun generate(size: Int, iterator: Iterator<Int>) {
        bplustree.generate(
            size, iterator, k, k_star, PAGESIZE,
            serializeKey = ::serializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            serializeKeyDiff = ::serializeInt,
            serializedSizeOfKeyDiff = ::serializedSizeOfInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_DifferenceEncoding_Int_OnlyKeys(@JvmField val tree: B_Plus_Tree_DifferenceEncoding_Int) : I_B_Plus_Tree_KeyRangeSearch_OnlyKeys<Int> {
    constructor(filename: String, k: Int = 1000, k_star: Int = 500) : this(B_Plus_Tree_DifferenceEncoding_Int(filename, k, k_star))

    override fun get(key: Int): Boolean = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Int>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_Static_Int_to_Int(@JvmField val filename: String) {
    val bplustree = B_Plus_Tree_Static<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeInt,
        serializedSizeOfKey = ::serializedSizeOfInt,
        leafNodeDeserializerKey = ::deserializeInt,
        leafNodeDeserializerValue = ::deserializeInt,
        serializedSizeOfValue = ::serializedSizeOfInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            iterator, PAGESIZE,
            serializeKey = ::serializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            serializeValue = ::serializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_Static_Int_to_Int(@JvmField val tree: B_Plus_Tree_Static_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String) : this(B_Plus_Tree_Static_Int_to_Int(filename))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}

class B_Plus_Tree_StaticCompressed_Int_to_Int(@JvmField val filename: String) {
    val bplustree = B_Plus_Tree_Static_CompressedPointer<Int, Int>(filename)
    inline operator fun get(key: Int) = bplustree.search(
        key,
        compare = ::compareInt,
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    inline fun range_search(keyLeft: Int, keyRight: Int) = bplustree.range_search(
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun sip_search(keyLeft: Int, keyRight: Int) = bplustree.sip_search(
        { key1: Int, key2: Int -> (key2 - key1) },
        { key: Int -> key - keyLeft },
        { key: Int -> key - keyRight },
        innerNodeDeserializer = ::deserializeCompressedInt,
        serializedSizeOfKey = ::serializedSizeOfCompressedInt,
        leafNodeDeserializerKey = ::deserializeCompressedInt,
        leafNodeDeserializerValue = ::deserializeCompressedInt,
        serializedSizeOfValue = ::serializedSizeOfCompressedInt
    )

    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) {
        bplustree.generate(
            iterator, PAGESIZE,
            serializeKey = ::serializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            serializeValue = ::serializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt
        )
    }
}

// this class introduces virtual method calls, but implements a generic interface
inline class Derived_B_Plus_Tree_StaticCompressed_Int_to_Int(@JvmField val tree: B_Plus_Tree_StaticCompressed_Int_to_Int) : I_B_Plus_Tree_KeyRangeSearch<Int, Int> {
    constructor(filename: String) : this(B_Plus_Tree_StaticCompressed_Int_to_Int(filename))

    override fun get(key: Int): Int = this.tree[key]
    override fun range_search(keyLeft: Int, keyRight: Int): () -> Int? = this.tree.range_search(keyLeft, keyRight)
    override fun sip_search(keyLeft: Int, keyRight: Int): (Int) -> Int? = this.tree.sip_search(keyLeft, keyRight)
    override fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>) = this.tree.generate(size, iterator)
}
