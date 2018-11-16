package lupos.datastructures.b_plus_tree

import lupos.io.buffer.*
import lupos.misc.bit0
import lupos.misc.bit1
import lupos.misc.bit2
import kotlin.math.ceil

class NotFoundException(val key:Any):NoSuchElementException(key.toString()+" not found!")

data class NodeParams(var nodeNumber:Int, val filename:String, var page:Page, var pageOffset:Long, var maxAdr:Long, var pageSizeMinusPointer:Long, var adrNode:Long, var numberOfEntriesPerNode:Double){
    companion object {
        var node = 1 // node number 0 is reserved for the root node!

        fun setStatusBytes(page:Page, type:Byte, numberOfStoredEntries:Int){
            page.putByte(0, type)
            page.putInt(1, numberOfStoredEntries)
        }

        fun constructNodeParams(root:Boolean, filename:String, PAGESIZE:Int, numberOfEntries:Int, numberOfNodes:Int):NodeParams {
            val startOffsetInPage = 5
            val POINTERSIZE = 4
            val nodeNumber:Int
            if(root) {
                nodeNumber = 0
            } else {
                nodeNumber = NodeParams.node
                NodeParams.node++
            }
            val page = bufferManager.getPage(filename, nodeNumber)
            val pageOffset = page.getPageIndex()
            val maxAdr = pageOffset + PAGESIZE - 1
            val pageSizeMinusPointer = maxAdr - POINTERSIZE
            val adrNode = pageOffset + startOffsetInPage
            val numberOfEntriesPerNode = numberOfEntries.toDouble() / numberOfNodes.toDouble()
            return NodeParams(nodeNumber, filename, page, pageOffset, maxAdr, pageSizeMinusPointer, adrNode, numberOfEntriesPerNode)
        }
    }
    var writtenEntryInPage = 0
    var writtenEntry = 0
    var nextNodeAtPos = numberOfEntriesPerNode
    var mustBeClosed = true
    var firstNodeNumber = nodeNumber
    fun overwrite(PAGESIZE:Int){
        node++
        nodeNumber = node
        firstNodeNumber = nodeNumber
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        page = bufferManager.getPage(filename, nodeNumber)
        pageOffset = page.getPageIndex()
        maxAdr = pageOffset + PAGESIZE - 1
        pageSizeMinusPointer = maxAdr - POINTERSIZE
        adrNode = pageOffset + startOffsetInPage
    }

    inline fun<K, V> write(key:K, value:V, sizeOfKey:Long, sizeOfValue:Long, lastEntryInLeaf:Boolean, PAGESIZE:Int, serializeKey: (Page, Long, K) -> Unit, serializeValue: (Page, Long, V) -> Unit) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + sizeOfKey
        val nextAdrValue = nextAdrKey + sizeOfValue
        if(nextAdrValue>=pageSizeMinusPointer-1){
            var createNewLeaf = true
            if(lastEntryInLeaf && writtenEntry<=nextNodeAtPos){ // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key/value still fits onto this page...
                if(nextAdrKey<=maxAdr){
                    // check if value still fits onto this page...
                    if(nextAdrKey+sizeOfValue<=maxAdr){
                        createNewLeaf = false
                    }
                }
            }
            // create succeeding page for this leaf node...
            if(createNewLeaf){
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage-1) // Is leaf node and does not fit onto this page!
                nodeNumber = node
                page = bufferManager.getPage(filename, node)
                pageOffset = page.getPageIndex()
                maxAdr = pageOffset + PAGESIZE - 1
                pageSizeMinusPointer = maxAdr - POINTERSIZE
                adrNode = pageOffset + startOffsetInPage
                writtenEntryInPage = 1
            }
        }
        serializeKey(page, adrNode, key)
        adrNode += sizeOfKey
        // write value
        serializeValue(page, adrNode, value)
        adrNode += sizeOfValue
    }

    inline fun<K, V> write(key:K, oldkey:K?, value:V, sizeOfKey:Long, sizeOfValue:Long, lastEntryInLeaf:Boolean, PAGESIZE:Int, serializeKey: (Page, Long, K) -> Unit, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializeValue: (Page, Long, V) -> Unit) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + sizeOfKey
        val nextAdrValue = nextAdrKey + sizeOfValue
        if(nextAdrValue>=pageSizeMinusPointer-1){
            var createNewLeaf = true
            if(lastEntryInLeaf && writtenEntry<=nextNodeAtPos){ // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key/value still fits onto this page...
                if(nextAdrKey<=maxAdr){
                    // check if value still fits onto this page...
                    if(nextAdrKey+sizeOfValue<=maxAdr){
                        createNewLeaf = false
                    }
                }
            }
            // create succeeding page for this leaf node...
            if(createNewLeaf){
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage-1) // Is leaf node and does not fit onto this page!
                nodeNumber = node
                page = bufferManager.getPage(filename, node)
                pageOffset = page.getPageIndex()
                maxAdr = pageOffset + PAGESIZE - 1
                pageSizeMinusPointer = maxAdr - POINTERSIZE
                adrNode = pageOffset + startOffsetInPage
                writtenEntryInPage = 1
            }
        }
        if(writtenEntryInPage == 1) {
            serializeKey(page, adrNode, key)
        } else {
            serializeKeyDiff(page, adrNode, key, oldkey!!)
        }
        adrNode += sizeOfKey
        // write value
        serializeValue(page, adrNode, value)
        adrNode += sizeOfValue
    }
}

inline fun<K:Any,V> search(filename:String, key:K, compare: (K,K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long, deserializePointer:(Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long):V {
    var node = 0
    while(true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var adr = 5L
        if (firstByte.bit0()) { // inner node
            for(i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if(i==numberOfElements){
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if(compare(nodekey, key) >= 0) {
                    node = pointer
                    break
                }
            }
            // continue with new inner or leaf node!
        } else { // leaf node
            for(i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compare(nodekey, key)
                if(cmp>0){
                    throw NotFoundException(key)
                }
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if(cmp==0){
                    return nodevalue
                }
            }
            if(firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                throw NotFoundException(key)
            }
        }
    }
}

inline fun<K,V> generate(filename:String, size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long, MAXPOINTERSIZE: Int, serializePointer:(Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5
    val POINTERSIZE = 4
    // How many leaf nodes?
    val numberOfLeafNodes = ceil(size.toDouble() / (2*k_star).toDouble()).toInt()
    val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
    var currentNumber = numberOfLeafNodes
    var numberOfInnerNodeLevels = 0
    val numberOfNodesList = mutableListOf<Int>()
    while(currentNumber > 1) {
        numberOfInnerNodeLevels++
        var currentSize = currentNumber
        currentNumber = ceil(currentSize.toDouble() / (2*k).toDouble()).toInt()
        numberOfNodesList += currentNumber
    }
    val numberOfNodes = numberOfNodesList.toTypedArray()
    val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if(it == 0) numberOfLeafNodes else numberOfNodes[it-1], numberOfNodes[it]) }
    for(entry in iterator) {
        currentLeafNode.writtenEntry++
        currentLeafNode.writtenEntryInPage++
        val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
        val key = entry.first
        val sizeOfKey = serializedSizeOfKey(key)
        val value = entry.second
        val sizeOfValue = serializedSizeOfValue(value)
        currentLeafNode.write(key, value, sizeOfKey, sizeOfValue, lastEntryInLeaf, PAGESIZE, serializeKey, serializeValue)
        if(currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
            var pointerToChild = currentLeafNode.firstNodeNumber
            // create new leaf node...
            currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node+1)
            NodeParams.Companion.setStatusBytes(currentLeafNode.page, 0, currentLeafNode.writtenEntryInPage) // Is leaf node and rest fits onto this page!
            currentLeafNode.overwrite(PAGESIZE)
            currentLeafNode.writtenEntryInPage = 0
            currentLeafNode.nextNodeAtPos += currentLeafNode.numberOfEntriesPerNode
            var i: Int = 0
            while (i < innerNodes.size) {
                val node = innerNodes[i]
                node.writtenEntry++
                // anyway write pointer to child node in this level
                if (node.adrNode + MAXPOINTERSIZE + (if (node.writtenEntry > node.nextNodeAtPos) serializedSizeOfKey(key) else 0) >= node.pageSizeMinusPointer - 1) {
                    NodeParams.node++
                    serializePointer(node.page, node.adrNode, NodeParams.node)
                    NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage) // Is inner node and does not fit onto this page!
                    node.page = bufferManager.getPage(filename, NodeParams.node)
                    node.pageOffset = node.page.getPageIndex()
                    node.maxAdr = node.pageOffset + PAGESIZE - 1
                    node.pageSizeMinusPointer = node.maxAdr - POINTERSIZE
                    node.adrNode = node.pageOffset + startOffsetInPage
                    node.writtenEntryInPage = 1
                } else {
                    node.writtenEntryInPage++
                }
                serializePointer(node.page, node.adrNode, pointerToChild)
                node.adrNode += serializedSizeOfPointer(pointerToChild)
                if (node.writtenEntry >= node.nextNodeAtPos) {
                    // last entry in the inner node -> remember the page number of the current inner node and proceed with the next level
                    pointerToChild = node.firstNodeNumber
                    NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                    if (node.writtenEntry < if(i==0) numberOfLeafNodes else numberOfNodes[i-1]) { // else-case: last entry written!
                        // create new inner node
                        node.overwrite(PAGESIZE)
                        node.writtenEntryInPage = 0
                    } else {
                        node.mustBeClosed = false
                    }
                    node.nextNodeAtPos += node.numberOfEntriesPerNode
                } else {
                    // the key is written into this level
                    serializeKey(node.page, node.adrNode, key)
                    node.adrNode += serializedSizeOfKey(key)
                    break
                }
                i++
            }
        }
    }
    // mark last node
    NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
    var pointer = currentLeafNode.firstNodeNumber
    // mark remaining inner nodes
    for (node in innerNodes) {
        // write pointer to inner node!
        if(node.mustBeClosed) {
            serializePointer(node.page, node.adrNode, pointer)
            node.adrNode += serializedSizeOfPointer(pointer)
            node.writtenEntryInPage++
            NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
        }
        pointer = node.firstNodeNumber
    }
}

data class PageAdr(var nodeNumber:Int, var page: Page, var adr:Long){
    var writtenEntryInPage = 0
    var newNodeOnThisLevel = false
}

inline fun<K,V> generateStaticTree(filename:String, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long, MAXPOINTERSIZE: Int, serializePointer:(Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5L
    val POINTERSIZE = 4
    val innerNodes = mutableListOf<PageAdr>()
    var nodeNumber = 0
    var maxPageNumber = 0
    var leafPage = bufferManager.getPage(filename, nodeNumber)
    var adr = startOffsetInPage
    var writtenEntryInPage = 0
    var writtenEntry = 0
    var oldKey:K? = null
    var sizeOfOldKey = 0L
    for(entry in iterator) {
        val key = entry.first
        val value = entry.second
        val sizeOfKey = serializedSizeOfKey(key)
        val sizeOfValue = serializedSizeOfValue(value)
        if(adr + sizeOfKey + sizeOfValue + MAXPOINTERSIZE >= PAGESIZE) {
            var pointer = nodeNumber
            // new leaf node!
            NodeParams.Companion.setStatusBytes(leafPage, 0, writtenEntryInPage) // Is leaf node and rest fits onto this page!
            maxPageNumber++
            nodeNumber = maxPageNumber
            serializePointer(leafPage, adr, nodeNumber)
            adr = startOffsetInPage
            leafPage = bufferManager.getPage(filename, nodeNumber)
            writtenEntryInPage = 0
            var newInnnerNodeLevel = true
            for(innerNode in innerNodes) {
                if(innerNode.newNodeOnThisLevel){
                    maxPageNumber++
                    innerNode.nodeNumber = maxPageNumber
                    innerNode.page = bufferManager.getPage(filename, innerNode.nodeNumber)
                    innerNode.adr = startOffsetInPage
                    innerNode.writtenEntryInPage = 0
                }
                innerNode.writtenEntryInPage++
                if(innerNode.adr + sizeOfOldKey + 2*MAXPOINTERSIZE >= PAGESIZE) { // 2*MAXPOINTERSIZE for having enough space...
                    pointer = innerNode.nodeNumber
                    serializePointer(innerNode.page, innerNode.adr, pointer)
                    NodeParams.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
                } else {
                    serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                    innerNode.adr += sizeOfOldKey
                    serializePointer(innerNode.page, innerNode.adr, pointer)
                    innerNode.adr += serializedSizeOfPointer(pointer)
                    break
                }
            }
            if(newInnnerNodeLevel){
                // one more level in the inner nodes...
                maxPageNumber++
                innerNodes += PageAdr(maxPageNumber, bufferManager.getPage(filename, maxPageNumber), startOffsetInPage)
                val innerNode = innerNodes[0]
                serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                innerNode.adr += sizeOfOldKey
                serializePointer(innerNode.page, innerNode.adr, pointer)
                innerNode.adr += serializedSizeOfPointer(pointer)
            }
        }
        serializeKey(leafPage, adr, key)
        adr += sizeOfKey
        serializeValue(leafPage, adr, value)
        adr += sizeOfValue
        writtenEntryInPage++
        writtenEntry++
        oldKey = key
        sizeOfOldKey = sizeOfKey
    }
    // mark last node
    NodeParams.Companion.setStatusBytes(leafPage, 4, writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
    var pointerToNode = nodeNumber
    // mark remaining inner nodes
    for(innerNode in innerNodes){
        // write pointer to inner node!
        serializePointer(innerNode.page, innerNode.adr, pointerToNode)
        innerNode.writtenEntryInPage++
        NodeParams.Companion.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
        pointerToNode = innerNode.nodeNumber
    }
}

inline fun<K, V> range_search(filename:String, compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, notfoundtext:String, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long, deserializePointer:(Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long):()-> V? {
    val startOffsetInPage = 5
    var node = 0
    while(true) {
        var page = bufferManager.getPage(filename, node)
        var adr = 0L
        val firstByte = page.getByte(adr) // load status byte
        adr++
        val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
        adr+=4
        if (firstByte.bit0()) { // inner node
            for(i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if(i==numberOfElements){
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if(compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                    break
                }
            }
        } else { // leaf node
            for(i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compareLeftLimit(nodekey)
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if(cmp>=0){
                    if(compareRightLimit(nodekey)<=0){
                        var first = true
                        var index = i
                        var numberOfElementsOfCurrentNode = numberOfElements
                        return {
                            if(first) {
                                first = false
                                nodevalue
                            } else {
                                var endReached = false
                                if(index>=numberOfElementsOfCurrentNode){
                                    if(page.getByte(0).bit2()){
                                        endReached = true
                                    } else {
                                        // load next leaf node
                                        val nextPage = page.getInt(adr)
                                        page = bufferManager.getPage(filename, nextPage)
                                        adr = page.getPageIndex() + startOffsetInPage
                                        numberOfElementsOfCurrentNode = page.getInt(1L)
                                        index = 0
                                    }
                                }
                                if(endReached){
                                    null
                                } else {
                                    val nodekey2 = leafNodeDeserializerKey(page, adr)
                                    adr += serializedSizeOfKey(nodekey)
                                    val nodevalue2 = leafNodeDeserializerValue(page, adr)
                                    adr += serializedSizeOfValue(nodevalue)
                                    index++
                                    if (compareRightLimit(nodekey2) <= 0) {
                                        nodevalue2
                                    } else {
                                        null
                                    }
                                }
                            }
                        }
                    } else {
                        throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
                    }
                }
            }
            if(firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
            }
        }
    }
}

/**
 * Layout of an inner node:
 * Byte 0: Status Byte (Bit 0: 0 => leaf node,
 *                             1 => internal node;
 *                      Bit 1: 0 => node fits onto this page (or is last page of this node),
 *                             1 => node does not fit onto this page)
 *                      Bit 2: 0 => default
 *                             1 => last leaf node
 * Bytes 1 to 4: number of key/value pairs stored in this page
 *
 * Internal Node:
 * for each entry:
 *     Pointer to child node
 *     Key
 * Pointer to child node | Pointer to next page of this node
 *
 * Leaf Node:
 * for each entry:
 *     Key
 *     Value
 * [Pointer to next page of this node]
 */
class B_Plus_Tree<K:Any, V:Any>(val filename:String){ // By K:Any and V:Any, neither K nor V can be nullable!

    // TODO insertion/deletion of key/value-pairs
    // TODO range search with sip
    // TODO generate considering only full pages (some form of static B_Plus_Tree e.g., for LSM trees etc.)... (However, this means no splitting/merging of nodes according to k/k_star parameters)

    inline fun search(key:K, compare: (K,K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long):V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }

    /**
     * binary search in inner nodes...
     * prerequirement: size of key is fixed...
     */
    inline fun binarySearch(key:K, compare: (K,K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long):V {
        var node = 0
        while(true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr+=4
            if (firstByte.bit0()) { // inner node
                var left = 0
                var right = numberOfElements-1
                while(left<right) {
                    val middle = (left+right) / 2
                    val adrElement = adr + middle * (4 + serializedSizeOfKey)
                    val nodekey = innerNodeDeserializer(page, adrElement + 4)
                    val cmp = compare(nodekey, key)
                    if (cmp < 0) {
                        left = middle + 1
                    } else {
                        right = middle
                    }
                }
                if(left==numberOfElements-1){
                    // check if the key already fits or if the right-most pointer must be followed...
                    val nodekey2 = innerNodeDeserializer(page, adr + (numberOfElements-2) * (4 + serializedSizeOfKey) + 4)
                    if(compare(nodekey2, key)>=0){
                        node = page.getInt(adr + (numberOfElements-2)*(4+serializedSizeOfKey))
                    } else {
                        // last pointer
                        // is pointing to next page of this inner node (in the case of that the node spans over several pages)
                        // or is pointing to the child node
                        node = page.getInt(adr + (numberOfElements-1)*(4+serializedSizeOfKey))
                    }
                } else {
                    node = page.getInt(adr + left*(4+serializedSizeOfKey))
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                for(i in 1..numberOfElements) {
                    val nodekey = leafNodeDeserializerKey(page, adr)
                    adr += serializedSizeOfKey
                    val cmp = compare(nodekey, key)
                    if(cmp>0){
                        throw NotFoundException(key)
                    }
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if(cmp==0){
                        return nodevalue
                    }
                }
                if(firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(key)
                }
            }
        }
    }

    inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, notfoundtext:String, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long):()-> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, notfoundtext, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }

    /**
     * binary search in inner nodes for range search...
     * prerequirement: size of key is fixed...
     */
    inline fun range_binary_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, notfoundtext:String, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long):()-> V? {
        val startOffsetInPage = 5
        var node = 0
        while(true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr+=4
            if (firstByte.bit0()) { // inner node
                var left = 0
                var right = numberOfElements-1
                while(left<right) {
                    val middle = (left+right) / 2
                    val adrElement = adr + middle * (4 + serializedSizeOfKey)
                    val nodekey = innerNodeDeserializer(page, adrElement + 4)
                    val cmp = compareLeftLimit(nodekey)
                    if (cmp < 0) {
                        left = middle + 1
                    } else {
                        right = middle
                    }
                }
                if(left==numberOfElements-1){
                    // check if the key already fits or if the right-most pointer must be followed...
                    val nodekey2 = innerNodeDeserializer(page, adr + (numberOfElements-2) * (4 + serializedSizeOfKey) + 4)
                    if(compareLeftLimit(nodekey2)>=0){
                        node = page.getInt(adr + (numberOfElements-2)*(4+serializedSizeOfKey))
                    } else {
                        // last pointer
                        // is pointing to next page of this inner node (in the case of that the node spans over several pages)
                        // or is pointing to the child node
                        node = page.getInt(adr + (numberOfElements-1)*(4+serializedSizeOfKey))
                    }
                } else {
                    node = page.getInt(adr + left*(4+serializedSizeOfKey))
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                for(i in 1..numberOfElements) {
                    val nodekey = leafNodeDeserializerKey(page, adr)
                    adr += serializedSizeOfKey
                    val cmp = compareLeftLimit(nodekey)
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if(cmp>=0){
                        if(compareRightLimit(nodekey)<=0){
                            var first = true
                            var index = i
                            var numberOfElementsOfCurrentNode = numberOfElements
                            return {
                                if(first) {
                                    first = false
                                    nodevalue
                                } else {
                                    var endReached = false
                                    if(index>=numberOfElementsOfCurrentNode){
                                        if(page.getByte(0).bit2()){
                                            endReached = true
                                        } else {
                                            // load next leaf node
                                            val nextPage = page.getInt(adr)
                                            page = bufferManager.getPage(filename, nextPage)
                                            adr = page.getPageIndex() + startOffsetInPage
                                            numberOfElementsOfCurrentNode = page.getInt(1L)
                                            index = 0
                                        }
                                    }
                                    if(endReached){
                                        null
                                    } else {
                                        val nodekey2 = leafNodeDeserializerKey(page, adr)
                                        adr += serializedSizeOfKey
                                        val nodevalue2 = leafNodeDeserializerValue(page, adr)
                                        adr += serializedSizeOfValue(nodevalue)
                                        index++
                                        if (compareRightLimit(nodekey2) <= 0) {
                                            nodevalue2
                                        } else {
                                            null
                                        }
                                    }
                                }
                            }
                        } else {
                            throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
                        }
                    }
                }
                if(firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
                }
            }
        }
    }

    /**
     * @param size: number of entries to be inserted into the tree
     * @param iterator: Iterator of the entries (as key/value pairs) to be inserted. The iterator should deliver the entries in the order of the keys
     * @param k: size of inner nodes
     * @param k_star: size of leaf nodes
     * @param pageSize: size of pages
     */
    inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        generate(filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeInt, ::serializedSizeOfInt)
    }
}

class B_Plus_Tree_VariableSizePointers<K:Any, V:Any>(val filename:String){ // By K:Any and V:Any, neither K nor V can be nullable!

    // TODO insertion/deletion of key/value-pairs
    // TODO range search with sip
    // TODO generate considering only full pages (some form of static B_Plus_Tree e.g., for LSM trees etc.)... (However, this means no splitting/merging of nodes according to k/k_star parameters)

    inline fun search(key:K, compare: (K,K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long):V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }

    inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, notfoundtext:String, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long):()-> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, notfoundtext, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }

    /**
     * @param size: number of entries to be inserted into the tree
     * @param iterator: Iterator of the entries (as key/value pairs) to be inserted. The iterator should deliver the entries in the order of the keys
     * @param k: size of inner nodes
     * @param k_star: size of leaf nodes
     * @param pageSize: size of pages
     */
    inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        generate(filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 5, ::serializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
}

class B_Plus_Tree_Difference_Encoding<K:Any, V:Any>(val filename:String){ // By K:Any and V:Any, neither K nor V can be nullable!
    // TODO still error for k = k_star = 8000, problem if (leaf?) node spans over two pages...

    // TODO insertion/deletion of key/value-pairs
    // TODO store numberOfElements and pointer with variable size

    inline fun search(key:K, compare: (K,K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, innerNodeDeserializerDiff: (Page, Long, K) -> K, serializedSizeOfKeyDiff: (K, K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerKeyDiff: (Page, Long, K) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long):V {
        var node = 0
        while(true) {
            var page = bufferManager.getPage(filename, node)
            val firstByte = page.getByte(0L) // load status byte
            val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
            var adr = 5L
            if (firstByte.bit0()) { // inner node
                // first key without difference encoding
                val pointer = deserializeCompressedInt(page, adr)
                adr += serializedSizeOfCompressedInt(pointer)
                var nodekeyOld = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekeyOld)
                if(compare(nodekeyOld, key) >= 0) {
                    node = pointer
                } else {
                    for (i in 2..numberOfElements) {
                        // other keys with difference encoding
                        val pointer = deserializeCompressedInt(page, adr)
                        adr += serializedSizeOfCompressedInt(pointer)
                        if (i == numberOfElements) {
                            node = pointer
                            break
                        }
                        val nodekey = innerNodeDeserializerDiff(page, adr, nodekeyOld)
                        adr += serializedSizeOfKeyDiff(nodekey, nodekeyOld)
                        if (compare(nodekey, key) >= 0) {
                            node = pointer
                            break
                        }
                        nodekeyOld = nodekey
                    }
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                // first key without difference encoding
                var nodekeyOld = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekeyOld)
                val cmp = compare(nodekeyOld, key)
                if(cmp>0){
                    throw NotFoundException(key)
                }
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if(cmp==0){
                    return nodevalue
                }
                for(i in 2..numberOfElements) {
                    // other keys with difference encoding
                    val nodekey = leafNodeDeserializerKeyDiff(page, adr, nodekeyOld)
                    adr += serializedSizeOfKeyDiff(nodekey, nodekeyOld)
                    val cmp = compare(nodekey, key)
                    if(cmp>0){
                        throw NotFoundException(key)
                    }
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if(cmp==0){
                        return nodevalue
                    }
                    nodekeyOld = nodekey
                }
                if(firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(key)
                }
            }
        }
    }

    inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, notfoundtext:String, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline innerNodeDeserializerDiff: (Page, Long, K) -> K, crossinline serializedSizeOfKeyDiff: (K, K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerKeyDiff: (Page, Long, K) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long):()-> V? {
        val startOffsetInPage = 5
        var node = 0
        while(true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr+=4
            if (firstByte.bit0()) { // inner node
                val pointer = deserializeCompressedInt(page, adr) // page.getInt(adr) // adr += 4
                adr += serializedSizeOfCompressedInt(pointer)
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if(compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                } else {
                    var oldkey = nodekey
                    for (i in 2..numberOfElements) {
                        // val pointer = page.getInt(adr) // adr += 4
                        val pointer = deserializeCompressedInt(page, adr)
                        adr += serializedSizeOfCompressedInt(pointer)
                        if (i == numberOfElements) {
                            node = pointer
                            break
                        }
                        val nodekey = innerNodeDeserializerDiff(page, adr, oldkey)
                        adr += serializedSizeOfKeyDiff(nodekey, oldkey)
                        oldkey = nodekey
                        if (compareLeftLimit(nodekey) >= 0) {
                            node = pointer
                            break
                        }
                    }
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                var oldkey:K? = null
                for(i in 1..numberOfElements) {
                    val nodekey = if(i==1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                    adr += if(i==1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, oldkey!!)
                    oldkey = nodekey
                    val cmp = compareLeftLimit(nodekey)
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if(cmp>=0){
                        if(compareRightLimit(nodekey)<=0){
                            var first = true
                            var index = i
                            var numberOfElementsOfCurrentNode = numberOfElements
                            return {
                                if(first) {
                                    first = false
                                    nodevalue
                                } else {
                                    var endReached = false
                                    if(index>=numberOfElementsOfCurrentNode){
                                        if(page.getByte(0).bit2()){
                                            endReached = true
                                        } else {
                                            // load next leaf node
                                            val nextPage = page.getInt(adr)
                                            page = bufferManager.getPage(filename, nextPage)
                                            adr = page.getPageIndex() + startOffsetInPage
                                            numberOfElementsOfCurrentNode = page.getInt(1L)
                                            oldkey = null
                                            index = 0
                                        }
                                    }
                                    if(endReached){
                                        null
                                    } else {
                                        index++
                                        val nodekey2 = if(index==1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                                        adr += if(index==1) serializedSizeOfKey(nodekey2) else serializedSizeOfKeyDiff(nodekey2, oldkey!!)
                                        oldkey = nodekey2
                                        val nodevalue2 = leafNodeDeserializerValue(page, adr)
                                        adr += serializedSizeOfValue(nodevalue2)
                                        if (compareRightLimit(nodekey2) <= 0) {
                                            nodevalue2
                                        } else {
                                            null
                                        }
                                    }
                                }
                            }
                        } else {
                            throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
                        }
                    }
                }
                if(firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(notfoundtext) // TODO: better object for not finding left/right limit
                }
            }
        }
    }

    /**
     * @param size: number of entries to be inserted into the tree
     * @param iterator: Iterator of the entries (as key/value pairs) to be inserted. The iterator should deliver the entries in the order of the keys
     * @param k: size of inner nodes
     * @param k_star: size of leaf nodes
     * @param pageSize: size of pages
     */
    inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        // How many leaf nodes?
        val numberOfLeafNodes = ceil(size.toDouble() / (2*k_star).toDouble()).toInt()
        val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
        var currentNumber = numberOfLeafNodes
        var numberOfInnerNodeLevels = 0
        val numberOfNodesList = mutableListOf<Int>()
        while(currentNumber > 1) {
            numberOfInnerNodeLevels++
            var currentSize = currentNumber
            currentNumber = ceil(currentSize.toDouble() / (2*k).toDouble()).toInt()
            numberOfNodesList += currentNumber
        }
        val numberOfNodes = numberOfNodesList.toTypedArray()
        val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if(it == 0) numberOfLeafNodes else numberOfNodes[it-1], numberOfNodes[it]) }
        val oldKeysInnerNodes:Array<Any?> = Array(numberOfNodes.size) { null }
        var key:K? = null
        var oldkey = key
        var index = 0
        for(entry in iterator) {
            index++
            currentLeafNode.writtenEntry++
            currentLeafNode.writtenEntryInPage++
            val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
            oldkey = key
            key = entry.first
            val sizeOfKey = if(currentLeafNode.writtenEntryInPage==1) serializedSizeOfKey(key) else serializedSizeOfKeyDiff(key, oldkey!!)
            val value = entry.second
            val sizeOfValue = serializedSizeOfValue(value)
            currentLeafNode.write(key, oldkey, value, sizeOfKey, sizeOfValue, lastEntryInLeaf, PAGESIZE, serializeKey, serializeKeyDiff, serializeValue)
            if(currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
                var pointerToChild = currentLeafNode.firstNodeNumber
                // create new leaf node...
                currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node+1)
                if(index < size) {
                    NodeParams.Companion.setStatusBytes(currentLeafNode.page, 0, currentLeafNode.writtenEntryInPage) // Is leaf node and rest fits onto this page!
                    currentLeafNode.overwrite(PAGESIZE)
                    currentLeafNode.writtenEntryInPage = 0
                    currentLeafNode.nextNodeAtPos += currentLeafNode.numberOfEntriesPerNode
                } else {
                    NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last(!) leaf node and rest fits onto this page!
                    currentLeafNode.mustBeClosed = false
                }
                var i: Int = 0
                while(i < innerNodes.size){
                    val node = innerNodes[i]
                    node.writtenEntry++
                    // anyway write pointer to child node in this level
                    if (node.adrNode + POINTERSIZE + (if (node.writtenEntry > node.nextNodeAtPos) serializedSizeOfKey(key) else 0) >= node.pageSizeMinusPointer - 1) {
                        NodeParams.node++
                        serializeCompressedInt(node.page, node.adrNode, NodeParams.node)
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage) // Is inner node and does not fit onto this page!
                        node.page = bufferManager.getPage(filename, NodeParams.node)
                        node.nodeNumber = NodeParams.node
                        node.pageOffset = node.page.getPageIndex()
                        node.maxAdr = node.pageOffset + PAGESIZE - 1
                        node.pageSizeMinusPointer = node.maxAdr - POINTERSIZE
                        node.adrNode = node.pageOffset + startOffsetInPage
                        node.writtenEntryInPage = 1
                    } else {
                        node.writtenEntryInPage++
                    }
                    serializeCompressedInt(node.page, node.adrNode, pointerToChild)
                    node.adrNode += serializedSizeOfCompressedInt(pointerToChild)
                    if (node.writtenEntry >= node.nextNodeAtPos) {
                        // last entry in the inner node -> remember the page number of the current inner node and proceed with the next level
                        pointerToChild = node.firstNodeNumber
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                        if (node.writtenEntry < if(i==0) numberOfLeafNodes else numberOfNodes[i-1]) { // else-case: last entry written!
                            // create new inner node
                            node.overwrite(PAGESIZE)
                            node.writtenEntryInPage = 0
                        } else {
                            node.mustBeClosed = false
                        }
                        node.nextNodeAtPos += node.numberOfEntriesPerNode
                    } else {
                        // the key is written into this level
                        if(node.writtenEntryInPage==1){
                            serializeKey(node.page, node.adrNode, key)
                            node.adrNode += serializedSizeOfKey(key)
                        } else {
                            val oldkey = oldKeysInnerNodes[i] as K?
                            serializeKeyDiff(node.page, node.adrNode, key, oldkey!!)
                            node.adrNode += serializedSizeOfKeyDiff(key, oldkey!!)
                        }
                        oldKeysInnerNodes[i] = key
                        break
                    }
                    i++
                }
            }
        }
        // mark last node
        if(currentLeafNode.mustBeClosed) {
            NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
        }
        var pointer = currentLeafNode.firstNodeNumber
        // mark remaining inner nodes
        for (node in innerNodes) {
            // write pointer to inner node!
            if(node.mustBeClosed) {
                serializeCompressedInt(node.page, node.adrNode, pointer)
                node.adrNode += serializedSizeOfCompressedInt(pointer)
                node.writtenEntryInPage++
                NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
            }
            pointer = node.firstNodeNumber
        }
    }
}

class B_Plus_Tree_Static<K:Any, V:Any>(val filename:String) { // By K:Any and V:Any, neither K nor V can be nullable!
    inline fun generate(iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        return generateStaticTree(filename, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeInt, ::serializedSizeOfInt)
    }
}

class B_Plus_Tree_Static_CompressedPointer<K:Any, V:Any>(val filename:String) { // By K:Any and V:Any, neither K nor V can be nullable!
    inline fun generate(iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        return generateStaticTree(filename, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
}