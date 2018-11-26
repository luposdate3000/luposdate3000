package lupos.datastructures.b_plus_tree

import lupos.io.buffer.*

class B_Plus_Tree_Uncompressed_Int_to_Int(val filename:String, val k:Int = 1000, val k_star:Int = 500){
    val bplustree = B_Plus_Tree<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    inline fun binarySearch(key:Int) = bplustree.binarySearch(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = 4,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    inline fun range_binary_search(keyLeft:Int, keyRight:Int) = bplustree.range_binary_search(
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = 4,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
                                    { key1:Int, key2:Int -> (key2 - key1) },
                                    { key:Int-> key - keyLeft },
                                    { key:Int-> key - keyRight },
                                    notfoundtext = "left limit: $keyLeft right limit: $keyRight",
                                    innerNodeDeserializer = ::deserializeInt,
                                    serializedSizeOfKey = ::serializedSizeOfInt,
                                    leafNodeDeserializerKey = ::deserializeInt,
                                    leafNodeDeserializerValue = ::deserializeInt,
                                    serializedSizeOfValue = ::serializedSizeOfInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(size, iterator, k, k_star, PAGESIZE,
                serializeKey = ::serializeInt,
                serializedSizeOfKey = ::serializedSizeOfInt,
                serializeValue = ::serializeInt,
                serializedSizeOfValue = ::serializedSizeOfInt)
    }
}

class B_Plus_Tree_VariableSize_Int_to_Int(val filename:String, val k:Int = 1000, val k_star:Int = 500){
    val bplustree = B_Plus_Tree<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue= ::serializedSizeOfCompressedInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
            { key1:Int, key2:Int -> (key2 - key1) },
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(size, iterator, k, k_star, PAGESIZE,
                serializeKey = ::serializeCompressedInt,
                serializedSizeOfKey = ::serializedSizeOfCompressedInt,
                serializeValue = ::serializeCompressedInt,
                serializedSizeOfValue = ::serializedSizeOfCompressedInt)
    }
}

class B_Plus_Tree_VariableSizePointers_Int_to_Int(val filename:String, val k:Int = 1000, val k_star:Int = 500){// val k:Int = 1000, val k_star:Int = 500){
    val bplustree = B_Plus_Tree_VariableSizePointers<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue= ::serializedSizeOfCompressedInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: "+keyLeft+" right limit:"+keyRight,
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
            { key1:Int, key2:Int -> (key2 - key1) },
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(size, iterator, k, k_star, PAGESIZE,
                serializeKey = ::serializeCompressedInt,
                serializedSizeOfKey = ::serializedSizeOfCompressedInt,
                serializeValue = ::serializeCompressedInt,
                serializedSizeOfValue = ::serializedSizeOfCompressedInt)
    }
}

class B_Plus_Tree_Difference_Encoding_Int_to_Int(val filename:String, val k:Int = 1000, val k_star:Int = 500){
    val bplustree = B_Plus_Tree_Difference_Encoding<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            innerNodeDeserializerDiff = ::deserializeInt,
            serializedSizeOfKeyDiff = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerKeyDiff = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            innerNodeDeserializerDiff = ::deserializeInt,
            serializedSizeOfKeyDiff = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerKeyDiff = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
            { key1:Int, key2:Int -> (key2 - key1) },
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: $keyLeft right limit:$keyRight",
            keyDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            keyDiffDeserializer = ::deserializeInt,
            serializedSizeOfKeyDiff = ::serializedSizeOfInt,
            valueDeserializer = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt,
            deserializePointer = ::deserializeCompressedInt,
            serializedSizeOfPointer = ::serializedSizeOfCompressedInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(size, iterator, k, k_star, PAGESIZE,
                serializeKey = ::serializeCompressedInt,
                serializedSizeOfKey = ::serializedSizeOfCompressedInt,
                serializeKeyDiff = ::serializeInt,
                serializedSizeOfKeyDiff = ::serializedSizeOfInt,
                serializeValue = ::serializeCompressedInt,
                serializedSizeOfValue = ::serializedSizeOfCompressedInt)
    }
}

class B_Plus_Tree_Static_Int_to_Int(val filename:String){
    val bplustree = B_Plus_Tree_Static<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue= ::serializedSizeOfInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
            { key1:Int, key2:Int -> (key2 - key1) },
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: "+keyLeft+" right limit:"+keyRight,
            innerNodeDeserializer = ::deserializeInt,
            serializedSizeOfKey = ::serializedSizeOfInt,
            leafNodeDeserializerKey = ::deserializeInt,
            leafNodeDeserializerValue = ::deserializeInt,
            serializedSizeOfValue = ::serializedSizeOfInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(iterator, PAGESIZE,
                serializeKey = ::serializeInt,
                serializedSizeOfKey = ::serializedSizeOfInt,
                serializeValue = ::serializeInt,
                serializedSizeOfValue = ::serializedSizeOfInt)
    }
}

class B_Plus_Tree_StaticCompressed_Int_to_Int(val filename:String){
    val bplustree = B_Plus_Tree_Static_CompressedPointer<Int, Int>(filename)

    inline operator fun get(key:Int) = bplustree.search(key,
            compare = ::compareInt,
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue= ::serializedSizeOfCompressedInt)

    inline fun range_search(keyLeft:Int, keyRight:Int) = bplustree.range_search(
            {key:Int-> key - keyLeft},
            {key:Int-> key - keyRight},
            notfoundtext = "left limit: $keyLeft right limit: $keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun sip_search(keyLeft:Int, keyRight:Int) = bplustree.sip_search(
            { key1:Int, key2:Int -> (key2 - key1) },
            { key:Int-> key - keyLeft },
            { key:Int-> key - keyRight },
            notfoundtext = "left limit: $keyLeft right limit: &keyRight",
            innerNodeDeserializer = ::deserializeCompressedInt,
            serializedSizeOfKey = ::serializedSizeOfCompressedInt,
            leafNodeDeserializerKey = ::deserializeCompressedInt,
            leafNodeDeserializerValue = ::deserializeCompressedInt,
            serializedSizeOfValue = ::serializedSizeOfCompressedInt)

    fun generate(size:Int, iterator:Iterator<Pair<Int,Int>>){
        bplustree.generate(iterator, PAGESIZE,
                serializeKey = ::serializeCompressedInt,
                serializedSizeOfKey = ::serializedSizeOfCompressedInt,
                serializeValue = ::serializeCompressedInt,
                serializedSizeOfValue = ::serializedSizeOfCompressedInt)
    }
}