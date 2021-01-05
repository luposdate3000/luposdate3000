package lupos.datastructures.b_plus_tree
import lupos.s00misc.SanityCheck
import lupos.s00misc.bit0
import lupos.s00misc.bit1
import lupos.s00misc.bit2
import lupos.s01io.buffer.Page
import lupos.s01io.buffer.bufferManager
import lupos.s01io.buffer.deserializeCompressedInt
import lupos.s01io.buffer.deserializeInt
import lupos.s01io.buffer.serializeCompressedInt
import lupos.s01io.buffer.serializeInt
import lupos.s01io.buffer.serializedSizeOfCompressedInt
import lupos.s01io.buffer.serializedSizeOfInt
import kotlin.jvm.JvmField
import kotlin.math.ceil
interface I_B_Plus_Tree<K : Any, V> {
    operator fun get(key: K): V
    fun generate(size: Int, iterator: Iterator<Pair<Int, Int>>)
}
interface I_B_Plus_Tree_KeyRangeSearch<K : Any, V> : I_B_Plus_Tree<K, V> {
    fun range_search(keyLeft: K, keyRight: K): () -> V?
    fun sip_search(keyLeft: K, keyRight: K): (K) -> V?
}
interface I_B_Plus_Tree_ComparatorRangeSearch<K : Any, V> : I_B_Plus_Tree<K, V> {
    fun range_search(compareLeftLimit: (K) -> Int, compareRightLimit: (K) -> Int): () -> V?
    fun sip_search(compareLeftLimit: (K) -> Int, compareRightLimit: (K) -> Int): (K) -> V?
}
interface I_B_Plus_Tree_OnlyKeys<K : Any> {
    operator fun get(key: K): Boolean
    fun generate(size: Int, iterator: Iterator<Int>)
}
interface I_B_Plus_Tree_KeyRangeSearch_OnlyKeys<K : Any> : I_B_Plus_Tree_OnlyKeys<K> {
    fun range_search(keyLeft: K, keyRight: K): () -> K?
    fun sip_search(keyLeft: K, keyRight: K): (K) -> K?
}
interface I_B_Plus_Tree_ComparatorRangeSearch_OnlyKeys<K : Any> : I_B_Plus_Tree_OnlyKeys<K> {
    fun range_search(compareLeftLimit: (K) -> Int, compareRightLimit: (K) -> Int): () -> K?
    fun sip_search(compareLeftLimit: (K) -> Int, compareRightLimit: (K) -> Int): (K) -> K?
}
class NotFoundException(@JvmField val key: Any) : NoSuchElementException(key.toString() + " not found!")
data class NodeParams(@JvmField var nodeNumber: Int, @JvmField val filename: String, @JvmField var page: Page, @JvmField var pageOffset: Long, @JvmField var maxAdr: Long, @JvmField var pageSizeMinusPointer: Long, @JvmField var adrNode: Long, @JvmField var numberOfEntriesPerNode: Double) {
    companion object {
        var node = 1 // node number 0 is reserved for the root node!
        fun setStatusBytes(page: Page, type: Byte, numberOfStoredEntries: Int) {
            page.putByte(0, type)
            page.putInt(1, numberOfStoredEntries)
        }
        fun constructLockedNodeParams(root: Boolean, filename: String, PAGESIZE: Int, numberOfEntries: Int, numberOfNodes: Int): NodeParams {
            val result = constructNodeParams(root, filename, PAGESIZE, numberOfEntries, numberOfNodes)
            result.page.lock()
            return result
        }
        fun constructNodeParams(root: Boolean, filename: String, PAGESIZE: Int, numberOfEntries: Int, numberOfNodes: Int): NodeParams {
            val startOffsetInPage = 5
            val POINTERSIZE = 4
            val nodeNumber: Int
            if (root) {
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
    fun overwrite(PAGESIZE: Int) {
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
    internal inline fun <K, V> write(key: K, value: V, sizeOfKey: Long, sizeOfValue: Long, lastEntryInLeaf: Boolean, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializeValue: (Page, Long, V) -> Unit) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + sizeOfKey
        val nextAdrValue = nextAdrKey + sizeOfValue
        if (nextAdrValue >= pageSizeMinusPointer - 1) {
            var createNewLeaf = true
            if (lastEntryInLeaf && writtenEntry <= nextNodeAtPos) { // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key/value still fits onto this page...
                if (nextAdrKey <= maxAdr) {
                    // check if value still fits onto this page...
                    if (nextAdrKey + sizeOfValue <= maxAdr) {
                        createNewLeaf = false
                    }
                }
            }
            // create succeeding page for this leaf node...
            if (createNewLeaf) {
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage - 1) // Is leaf node and does not fit onto this page!
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
    // for B+-trees, which store only keys and no values
    internal inline fun <K> write(key: K, sizeOfKey: Long, lastEntryInLeaf: Boolean, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + sizeOfKey
        if (nextAdrKey >= pageSizeMinusPointer - 1) {
            var createNewLeaf = true
            if (lastEntryInLeaf && writtenEntry <= nextNodeAtPos) { // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key still fits onto this page...
                if (nextAdrKey <= maxAdr) {
                    createNewLeaf = false
                }
            }
            // create succeeding page for this leaf node...
            if (createNewLeaf) {
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage - 1) // Is leaf node and does not fit onto this page!
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
    }
    internal inline fun <K, V> write(key: K, oldkey: K?, value: V, sizeOfValue: Long, lastEntryInLeaf: Boolean, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long, serializeValue: (Page, Long, V) -> Unit) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + (if (oldkey == null) serializedSizeOfKey(key) else serializedSizeOfKeyDiff(key, oldkey))
        val nextAdrValue = nextAdrKey + sizeOfValue
        if (nextAdrValue >= pageSizeMinusPointer - 1) {
            var createNewLeaf = true
            if (lastEntryInLeaf && writtenEntry <= nextNodeAtPos) { // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key/value still fits onto this page...
                if (nextAdrKey <= maxAdr) {
                    // check if value still fits onto this page...
                    if (nextAdrKey + sizeOfValue <= maxAdr) {
                        createNewLeaf = false
                    }
                }
            }
            // create succeeding page for this leaf node...
            if (createNewLeaf) {
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage - 1) // Is leaf node and does not fit onto this page!
                nodeNumber = node
                page = bufferManager.getPage(filename, node)
                pageOffset = page.getPageIndex()
                maxAdr = pageOffset + PAGESIZE - 1
                pageSizeMinusPointer = maxAdr - POINTERSIZE
                adrNode = pageOffset + startOffsetInPage
                writtenEntryInPage = 1
            }
        }
        if (writtenEntryInPage == 1) {
            serializeKey(page, adrNode, key)
            adrNode += serializedSizeOfKey(key)
        } else {
            serializeKeyDiff(page, adrNode, key, oldkey!!)
            adrNode += serializedSizeOfKeyDiff(key, oldkey)
        }
        // write value
        serializeValue(page, adrNode, value)
        adrNode += sizeOfValue
    }
    // for B+-trees, which contain only keys (and no values)
    internal inline fun <K> write(key: K, oldkey: K?, lastEntryInLeaf: Boolean, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long) {
        val startOffsetInPage = 5
        val POINTERSIZE = 4
        val nextAdrKey = adrNode + (if (oldkey == null) serializedSizeOfKey(key) else serializedSizeOfKeyDiff(key, oldkey))
        if (nextAdrKey >= pageSizeMinusPointer - 1) {
            var createNewLeaf = true
            if (lastEntryInLeaf && writtenEntry <= nextNodeAtPos) { // if anyway a new leaf node follows, then there must be space for the address of the next leaf node
                // check if last key still fits onto this page...
                if (nextAdrKey <= maxAdr) {
                    createNewLeaf = false
                }
            }
            // create succeeding page for this leaf node...
            if (createNewLeaf) {
                node++
                page.putInt(adrNode, node)
                setStatusBytes(page, 2, writtenEntryInPage - 1) // Is leaf node and does not fit onto this page!
                nodeNumber = node
                page = bufferManager.getPage(filename, node)
                pageOffset = page.getPageIndex()
                maxAdr = pageOffset + PAGESIZE - 1
                pageSizeMinusPointer = maxAdr - POINTERSIZE
                adrNode = pageOffset + startOffsetInPage
                writtenEntryInPage = 1
            }
        }
        if (writtenEntryInPage == 1) {
            serializeKey(page, adrNode, key)
            adrNode += serializedSizeOfKey(key)
        } else {
            serializeKeyDiff(page, adrNode, key, oldkey!!)
            adrNode += serializedSizeOfKeyDiff(key, oldkey)
        }
    }
}
inline fun <K : Any, V> search(filename: String, key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long, deserializePointer: (Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long): V {
    var node = 0
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var adr = 5L
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compare(nodekey, key) >= 0) {
                    node = pointer
                    break
                }
            }
            // continue with new inner or leaf node!
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compare(nodekey, key)
                if (cmp > 0) {
                    throw NotFoundException(key)
                }
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if (cmp == 0) {
                    return nodevalue
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                throw NotFoundException(key)
            }
        }
    }
}
// search within B+-tree, which contains only keys (and does not contain values)
inline fun <K : Any> search(filename: String, key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, deserializePointer: (Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long): Boolean {
    var node = 0
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var adr = 5L
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal with this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compare(nodekey, key) >= 0) {
                    node = pointer
                    break
                }
            }
            // continue with new inner or leaf node!
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compare(nodekey, key)
                if (cmp > 0) {
                    return false
                }
                if (cmp == 0) {
                    return true
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                return false
            }
        }
    }
}
inline fun <K, V> generate(filename: String, size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long, MAXPOINTERSIZE: Int, serializePointer: (Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5
    val POINTERSIZE = 4
    // How many leaf nodes?
    val numberOfLeafNodes = ceil(size.toDouble() / (2 * k_star).toDouble()).toInt()
    val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
    var currentNumber = numberOfLeafNodes
    var numberOfInnerNodeLevels = 0
    val numberOfNodesList = mutableListOf<Int>()
    while (currentNumber > 1) {
        numberOfInnerNodeLevels++
        var currentSize = currentNumber
        currentNumber = ceil(currentSize.toDouble() / (2 * k).toDouble()).toInt()
        numberOfNodesList += currentNumber
    }
    val numberOfNodes = numberOfNodesList.toTypedArray()
    val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructLockedNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if (it == 0) numberOfLeafNodes else numberOfNodes[it - 1], numberOfNodes[it]) }
    for (entry in iterator) {
        currentLeafNode.writtenEntry++
        currentLeafNode.writtenEntryInPage++
        val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
        val key = entry.first
        val sizeOfKey = serializedSizeOfKey(key)
        val value = entry.second
        val sizeOfValue = serializedSizeOfValue(value)
        currentLeafNode.write(key, value, sizeOfKey, sizeOfValue, lastEntryInLeaf, PAGESIZE, serializeKey, serializeValue)
        if (currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
            var pointerToChild = currentLeafNode.firstNodeNumber
            // create new leaf node...
            currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node + 1)
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
                    node.page.unlock()
                    node.page = bufferManager.getPage(filename, NodeParams.node)
                    node.page.lock()
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
                    if (node.writtenEntry < if (i == 0) numberOfLeafNodes else numberOfNodes[i - 1]) { // else-case: last entry written!
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                        node.page.unlock()
                        // create new inner node
                        node.overwrite(PAGESIZE)
                        node.page.lock()
                        node.writtenEntryInPage = 0
                    } else {
                        NodeParams.setStatusBytes(node.page, 1, node.writtenEntryInPage)
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
        if (node.mustBeClosed) {
            serializePointer(node.page, node.adrNode, pointer)
            node.adrNode += serializedSizeOfPointer(pointer)
            node.writtenEntryInPage++
            NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
        }
        node.page.unlock()
        pointer = node.firstNodeNumber
    }
}
// generate B+-tree, which contains only keys (and does not contain values)
inline fun <K> generate(filename: String, size: Int, iterator: Iterator<K>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, MAXPOINTERSIZE: Int, serializePointer: (Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5
    val POINTERSIZE = 4
    // How many leaf nodes?
    val numberOfLeafNodes = ceil(size.toDouble() / (2 * k_star).toDouble()).toInt()
    val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
    var currentNumber = numberOfLeafNodes
    var numberOfInnerNodeLevels = 0
    val numberOfNodesList = mutableListOf<Int>()
    while (currentNumber > 1) {
        numberOfInnerNodeLevels++
        var currentSize = currentNumber
        currentNumber = ceil(currentSize.toDouble() / (2 * k).toDouble()).toInt()
        numberOfNodesList += currentNumber
    }
    val numberOfNodes = numberOfNodesList.toTypedArray()
    val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructLockedNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if (it == 0) numberOfLeafNodes else numberOfNodes[it - 1], numberOfNodes[it]) }
    for (entry in iterator) {
        currentLeafNode.writtenEntry++
        currentLeafNode.writtenEntryInPage++
        val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
        val key = entry
        val sizeOfKey = serializedSizeOfKey(key)
        currentLeafNode.write(key, sizeOfKey, lastEntryInLeaf, PAGESIZE, serializeKey)
        if (currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
            var pointerToChild = currentLeafNode.firstNodeNumber
            // create new leaf node...
            currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node + 1)
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
                    node.page.unlock()
                    node.page = bufferManager.getPage(filename, NodeParams.node)
                    node.page.lock()
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
                    if (node.writtenEntry < if (i == 0) numberOfLeafNodes else numberOfNodes[i - 1]) { // else-case: last entry written!
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                        node.page.unlock()
                        // create new inner node
                        node.overwrite(PAGESIZE)
                        node.page.lock()
                        node.writtenEntryInPage = 0
                    } else {
                        NodeParams.setStatusBytes(node.page, 1, node.writtenEntryInPage)
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
        if (node.mustBeClosed) {
            serializePointer(node.page, node.adrNode, pointer)
            node.adrNode += serializedSizeOfPointer(pointer)
            node.writtenEntryInPage++
            NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
        }
        node.page.unlock()
        pointer = node.firstNodeNumber
    }
}
data class PageAdr(@JvmField var nodeNumber: Int, @JvmField var page: Page, @JvmField var adr: Long) {
    var writtenEntryInPage = 0
    var newNodeOnThisLevel = false
}
inline fun <K, V> generateStaticTree(filename: String, iterator: Iterator<Pair<K, V>>, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long, MAXPOINTERSIZE: Int, serializePointer: (Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5L
    val POINTERSIZE = 4
    val innerNodes = mutableListOf<PageAdr>()
    var nodeNumber = 1
    var maxPageNumber = 1
    var leafPage = bufferManager.getPage(filename, nodeNumber)
    var adr = startOffsetInPage
    var writtenEntryInPage = 0
    var writtenEntry = 0
    var oldKey: K? = null
    var sizeOfOldKey = 0L
    for (entry in iterator) {
        val key = entry.first
        val value = entry.second
        val sizeOfKey = serializedSizeOfKey(key)
        val sizeOfValue = serializedSizeOfValue(value)
        if (adr + sizeOfKey + sizeOfValue + MAXPOINTERSIZE >= PAGESIZE) {
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
            for (innerNode in innerNodes) {
                if (innerNode.newNodeOnThisLevel) {
                    innerNode.newNodeOnThisLevel = false
                    maxPageNumber++
                    innerNode.nodeNumber = maxPageNumber
                    innerNode.page.unlock()
                    innerNode.page = bufferManager.getPage(filename, innerNode.nodeNumber)
                    innerNode.page.lock()
                    innerNode.adr = startOffsetInPage
                    innerNode.writtenEntryInPage = 0
                }
                innerNode.writtenEntryInPage++
                serializePointer(innerNode.page, innerNode.adr, pointer)
                innerNode.adr += serializedSizeOfPointer(pointer)
                if (innerNode.adr + sizeOfOldKey + MAXPOINTERSIZE >= PAGESIZE) {
                    NodeParams.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
                    innerNode.newNodeOnThisLevel = true
                } else {
                    serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                    innerNode.adr += sizeOfOldKey
                    newInnnerNodeLevel = false
                    break
                }
                pointer = innerNode.nodeNumber
            }
            if (newInnnerNodeLevel) {
                // one more level in the inner nodes...
                maxPageNumber++
                val innerNode = PageAdr(maxPageNumber, bufferManager.getPage(filename, maxPageNumber), startOffsetInPage)
                innerNode.page.lock()
                innerNodes += innerNode
                serializePointer(innerNode.page, innerNode.adr, pointer)
                innerNode.adr += serializedSizeOfPointer(pointer)
                serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                innerNode.adr += sizeOfOldKey
                innerNode.writtenEntryInPage++
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
    for (innerNode in innerNodes) {
        // write pointer to inner node!
        serializePointer(innerNode.page, innerNode.adr, pointerToNode)
        innerNode.writtenEntryInPage++
        NodeParams.Companion.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
        innerNode.page.unlock()
        pointerToNode = innerNode.nodeNumber
    }
    // copy root page to page 0:
    val rootpage = innerNodes.last().page
    val offset = rootpage.getPageIndex()
    val page0 = bufferManager.getPage(filename, 0)
    val offset0 = page0.getPageIndex()
    for (i in 0 until PAGESIZE) {
        page0.putByte(offset0 + i, rootpage.getByte(offset + i))
    }
}
// generate static B+-tree, which contains only keys (and does not contain values)
inline fun <K> generateStaticTree(filename: String, iterator: Iterator<K>, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, MAXPOINTERSIZE: Int, serializePointer: (Page, Long, Int) -> Unit, serializedSizeOfPointer: (Int) -> Long) {
    val startOffsetInPage = 5L
    val POINTERSIZE = 4
    val innerNodes = mutableListOf<PageAdr>()
    var nodeNumber = 1
    var maxPageNumber = 1
    var leafPage = bufferManager.getPage(filename, nodeNumber)
    var adr = startOffsetInPage
    var writtenEntryInPage = 0
    var writtenEntry = 0
    var oldKey: K? = null
    var sizeOfOldKey = 0L
    for (entry in iterator) {
        val key = entry
        val sizeOfKey = serializedSizeOfKey(key)
        if (adr + sizeOfKey + MAXPOINTERSIZE >= PAGESIZE) {
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
            for (innerNode in innerNodes) {
                if (innerNode.newNodeOnThisLevel) {
                    innerNode.newNodeOnThisLevel = false
                    maxPageNumber++
                    innerNode.nodeNumber = maxPageNumber
                    innerNode.page.unlock()
                    innerNode.page = bufferManager.getPage(filename, innerNode.nodeNumber)
                    innerNode.page.lock()
                    innerNode.adr = startOffsetInPage
                    innerNode.writtenEntryInPage = 0
                }
                innerNode.writtenEntryInPage++
                serializePointer(innerNode.page, innerNode.adr, pointer)
                innerNode.adr += serializedSizeOfPointer(pointer)
                if (innerNode.adr + sizeOfOldKey + MAXPOINTERSIZE >= PAGESIZE) {
                    NodeParams.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
                    innerNode.newNodeOnThisLevel = true
                } else {
                    serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                    innerNode.adr += sizeOfOldKey
                    newInnnerNodeLevel = false
                    break
                }
                pointer = innerNode.nodeNumber
            }
            if (newInnnerNodeLevel) {
                // one more level in the inner nodes...
                maxPageNumber++
                val innerNode = PageAdr(maxPageNumber, bufferManager.getPage(filename, maxPageNumber), startOffsetInPage)
                innerNode.page.lock()
                innerNodes += innerNode
                serializePointer(innerNode.page, innerNode.adr, pointer)
                innerNode.adr += serializedSizeOfPointer(pointer)
                serializeKey(innerNode.page, innerNode.adr, oldKey!!)
                innerNode.adr += sizeOfOldKey
                innerNode.writtenEntryInPage++
            }
        }
        serializeKey(leafPage, adr, key)
        adr += sizeOfKey
        writtenEntryInPage++
        writtenEntry++
        oldKey = key
        sizeOfOldKey = sizeOfKey
    }
    // mark last node
    NodeParams.Companion.setStatusBytes(leafPage, 4, writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
    var pointerToNode = nodeNumber
    // mark remaining inner nodes
    for (innerNode in innerNodes) {
        // write pointer to inner node!
        serializePointer(innerNode.page, innerNode.adr, pointerToNode)
        innerNode.writtenEntryInPage++
        NodeParams.Companion.setStatusBytes(innerNode.page, 1, innerNode.writtenEntryInPage)
        innerNode.page.unlock()
        pointerToNode = innerNode.nodeNumber
    }
    // copy root page to page 0:
    val rootpage = innerNodes.last().page
    val offset = rootpage.getPageIndex()
    val page0 = bufferManager.getPage(filename, 0)
    val offset0 = page0.getPageIndex()
    for (i in 0 until PAGESIZE) {
        page0.putByte(offset0 + i, rootpage.getByte(offset + i))
    }
}
/**
 * @param size: number of entries to be inserted into the tree
 * @param iterator: Iterator of the entries (as key/value pairs) to be inserted. The iterator should deliver the entries in the order of the keys
 * @param k: size of inner nodes
 * @param k_star: size of leaf nodes
 * @param pageSize: size of pages
 */
inline fun <K, V> generateDifferenceEncodedBPlusTree(filename: String, size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
    val startOffsetInPage = 5
    val POINTERSIZE = 4
    // How many leaf nodes?
    val numberOfLeafNodes = ceil(size.toDouble() / (2 * k_star).toDouble()).toInt()
    val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
    var currentNumber = numberOfLeafNodes
    var numberOfInnerNodeLevels = 0
    val numberOfNodesList = mutableListOf<Int>()
    while (currentNumber > 1) {
        numberOfInnerNodeLevels++
        var currentSize = currentNumber
        currentNumber = ceil(currentSize.toDouble() / (2 * k).toDouble()).toInt()
        numberOfNodesList += currentNumber
    }
    val numberOfNodes = numberOfNodesList.toTypedArray()
    val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructLockedNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if (it == 0) numberOfLeafNodes else numberOfNodes[it - 1], numberOfNodes[it]) }
    val oldKeysInnerNodes: Array<Any?> = Array(numberOfNodes.size) { null }
    var key: K? = null
    var oldkey = key
    var index = 0
    for (entry in iterator) {
        index++
        currentLeafNode.writtenEntry++
        currentLeafNode.writtenEntryInPage++
        val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
        oldkey = key
        key = entry.first
        val value = entry.second
        currentLeafNode.write(key, oldkey, value, serializedSizeOfValue(value), lastEntryInLeaf, PAGESIZE, serializeKey, serializedSizeOfKey, serializeKeyDiff, serializedSizeOfKeyDiff, serializeValue)
        if (currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
            var pointerToChild = currentLeafNode.firstNodeNumber
            // create new leaf node...
            currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node + 1)
            if (index < size) {
                NodeParams.Companion.setStatusBytes(currentLeafNode.page, 0, currentLeafNode.writtenEntryInPage) // Is leaf node and rest fits onto this page!
                currentLeafNode.overwrite(PAGESIZE)
                currentLeafNode.writtenEntryInPage = 0
                currentLeafNode.nextNodeAtPos += currentLeafNode.numberOfEntriesPerNode
            } else {
                NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last(!) leaf node and rest fits onto this page!
                currentLeafNode.mustBeClosed = false
            }
            var i: Int = 0
            while (i < innerNodes.size) {
                val node = innerNodes[i]
                node.writtenEntry++
                // anyway write pointer to child node in this level
                if (node.adrNode + POINTERSIZE + (if (node.writtenEntry > node.nextNodeAtPos) serializedSizeOfKey(key) else 0) >= node.pageSizeMinusPointer - 1) {
                    NodeParams.node++
                    serializeCompressedInt(node.page, node.adrNode, NodeParams.node)
                    NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage) // Is inner node and does not fit onto this page!
                    node.page.unlock()
                    node.page = bufferManager.getPage(filename, NodeParams.node)
                    node.page.lock()
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
                    if (node.writtenEntry < if (i == 0) numberOfLeafNodes else numberOfNodes[i - 1]) { // else-case: last entry written!
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                        node.page.unlock()
                        // create new inner node
                        node.overwrite(PAGESIZE)
                        node.page.lock()
                        node.writtenEntryInPage = 0
                    } else {
                        NodeParams.setStatusBytes(node.page, 1, node.writtenEntryInPage)
                        node.mustBeClosed = false
                    }
                    node.nextNodeAtPos += node.numberOfEntriesPerNode
                } else {
                    // the key is written into this level
                    if (node.writtenEntryInPage == 1) {
                        serializeKey(node.page, node.adrNode, key)
                        node.adrNode += serializedSizeOfKey(key)
                    } else {
                        val oldkey = oldKeysInnerNodes[i] as K?
                        serializeKeyDiff(node.page, node.adrNode, key, oldkey!!)
                        node.adrNode += serializedSizeOfKeyDiff(key, oldkey)
                    }
                    oldKeysInnerNodes[i] = key
                    break
                }
                i++
            }
        }
    }
    // mark last node
    if (currentLeafNode.mustBeClosed) {
        NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
    }
    var pointer = currentLeafNode.firstNodeNumber
    // mark remaining inner nodes
    for (node in innerNodes) {
        // write pointer to inner node!
        if (node.mustBeClosed) {
            serializeCompressedInt(node.page, node.adrNode, pointer)
            node.adrNode += serializedSizeOfCompressedInt(pointer)
            node.writtenEntryInPage++
            NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
        }
        node.page.unlock()
        pointer = node.firstNodeNumber
    }
}
// generate static B+-tree, which contains only keys (and does not contain values)
inline fun <K> generateDifferenceEncodedBPlusTree(filename: String, size: Int, iterator: Iterator<K>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long) {
    val startOffsetInPage = 5
    val POINTERSIZE = 4
    // How many leaf nodes?
    val numberOfLeafNodes = ceil(size.toDouble() / (2 * k_star).toDouble()).toInt()
    val currentLeafNode = NodeParams.constructNodeParams(numberOfLeafNodes <= 1, filename, PAGESIZE, size, numberOfLeafNodes)
    var currentNumber = numberOfLeafNodes
    var numberOfInnerNodeLevels = 0
    val numberOfNodesList = mutableListOf<Int>()
    while (currentNumber > 1) {
        numberOfInnerNodeLevels++
        var currentSize = currentNumber
        currentNumber = ceil(currentSize.toDouble() / (2 * k).toDouble()).toInt()
        numberOfNodesList += currentNumber
    }
    val numberOfNodes = numberOfNodesList.toTypedArray()
    val innerNodes = Array<NodeParams>(numberOfNodes.size) { NodeParams.constructLockedNodeParams(it == numberOfNodes.size - 1, filename, PAGESIZE, if (it == 0) numberOfLeafNodes else numberOfNodes[it - 1], numberOfNodes[it]) }
    val oldKeysInnerNodes: Array<Any?> = Array(numberOfNodes.size) { null }
    var key: K? = null
    var oldkey = key
    var index = 0
    for (entry in iterator) {
        index++
        currentLeafNode.writtenEntry++
        currentLeafNode.writtenEntryInPage++
        val lastEntryInLeaf = (currentLeafNode.writtenEntry + 1 > currentLeafNode.nextNodeAtPos)
        oldkey = key
        key = entry
        currentLeafNode.write(key, oldkey, lastEntryInLeaf, PAGESIZE, serializeKey, serializedSizeOfKey, serializeKeyDiff, serializedSizeOfKeyDiff)
        if (currentLeafNode.writtenEntry > currentLeafNode.nextNodeAtPos) {
            var pointerToChild = currentLeafNode.firstNodeNumber
            // create new leaf node...
            currentLeafNode.page.putInt(currentLeafNode.adrNode, NodeParams.Companion.node + 1)
            if (index < size) {
                NodeParams.Companion.setStatusBytes(currentLeafNode.page, 0, currentLeafNode.writtenEntryInPage) // Is leaf node and rest fits onto this page!
                currentLeafNode.overwrite(PAGESIZE)
                currentLeafNode.writtenEntryInPage = 0
                currentLeafNode.nextNodeAtPos += currentLeafNode.numberOfEntriesPerNode
            } else {
                NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last(!) leaf node and rest fits onto this page!
                currentLeafNode.mustBeClosed = false
            }
            var i: Int = 0
            while (i < innerNodes.size) {
                val node = innerNodes[i]
                node.writtenEntry++
                // anyway write pointer to child node in this level
                if (node.adrNode + POINTERSIZE + (if (node.writtenEntry > node.nextNodeAtPos) serializedSizeOfKey(key) else 0) >= node.pageSizeMinusPointer - 1) {
                    NodeParams.node++
                    serializeCompressedInt(node.page, node.adrNode, NodeParams.node)
                    NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage) // Is inner node and does not fit onto this page!
                    node.page.unlock()
                    node.page = bufferManager.getPage(filename, NodeParams.node)
                    node.page.lock()
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
                    if (node.writtenEntry < if (i == 0) numberOfLeafNodes else numberOfNodes[i - 1]) { // else-case: last entry written!
                        NodeParams.setStatusBytes(node.page, 3, node.writtenEntryInPage)
                        node.page.unlock()
                        // create new inner node
                        node.overwrite(PAGESIZE)
                        node.page.lock()
                        node.writtenEntryInPage = 0
                    } else {
                        NodeParams.setStatusBytes(node.page, 1, node.writtenEntryInPage)
                        node.mustBeClosed = false
                    }
                    node.nextNodeAtPos += node.numberOfEntriesPerNode
                } else {
                    // the key is written into this level
                    if (node.writtenEntryInPage == 1) {
                        serializeKey(node.page, node.adrNode, key)
                        node.adrNode += serializedSizeOfKey(key)
                    } else {
                        val oldkey = oldKeysInnerNodes[i] as K?
                        serializeKeyDiff(node.page, node.adrNode, key, oldkey!!)
                        node.adrNode += serializedSizeOfKeyDiff(key, oldkey)
                    }
                    oldKeysInnerNodes[i] = key
                    break
                }
                i++
            }
        }
    }
    // mark last node
    if (currentLeafNode.mustBeClosed) {
        NodeParams.Companion.setStatusBytes(currentLeafNode.page, 4, currentLeafNode.writtenEntryInPage) // Is last (!) leaf node and rest fits onto this page!
    }
    var pointer = currentLeafNode.firstNodeNumber
    // mark remaining inner nodes
    for (node in innerNodes) {
        // write pointer to inner node!
        if (node.mustBeClosed) {
            serializeCompressedInt(node.page, node.adrNode, pointer)
            node.adrNode += serializedSizeOfCompressedInt(pointer)
            node.writtenEntryInPage++
            NodeParams.Companion.setStatusBytes(node.page, 1, node.writtenEntryInPage)
        }
        node.page.unlock()
        pointer = node.firstNodeNumber
    }
}
inline fun <K, V> range_search(filename: String, compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long, deserializePointer: (Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long): () -> V? {
    val startOffsetInPage = 5
    var node = 0
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var adr = 5L
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                    break
                }
            }
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compareLeftLimit(nodekey)
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if (cmp >= 0) {
                    if (compareRightLimit(nodekey) <= 0) {
                        var first = true
                        var index = i
                        var numberOfElementsOfCurrentNode = numberOfElements
                        return {
                            if (first) {
                                first = false
                                nodevalue
                            } else {
                                var endReached = false
                                if (index >= numberOfElementsOfCurrentNode) {
                                    if (page.getByte(0).bit2()) {
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
                                if (endReached) {
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
                        return { null }
                    }
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                return { null }
            }
        }
    }
}
// for B+-trees with only keys (without values)
inline fun <K> range_search(filename: String, compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, deserializePointer: (Page, Long) -> Int, serializedSizeOfPointer: (Int) -> Long): () -> K? {
    val startOffsetInPage = 5
    var node = 0
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var adr = 5L
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, adr)
                adr += serializedSizeOfPointer(pointer)
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    // Anyway we must continue our search in this new node and deal this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                    break
                }
            }
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, adr)
                adr += serializedSizeOfKey(nodekey)
                val cmp = compareLeftLimit(nodekey)
                if (cmp >= 0) {
                    if (compareRightLimit(nodekey) <= 0) {
                        var first = true
                        var index = i
                        var numberOfElementsOfCurrentNode = numberOfElements
                        return {
                            if (first) {
                                first = false
                                nodekey
                            } else {
                                var endReached = false
                                if (index >= numberOfElementsOfCurrentNode) {
                                    if (page.getByte(0).bit2()) {
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
                                if (endReached) {
                                    null
                                } else {
                                    val nodekey2 = leafNodeDeserializerKey(page, adr)
                                    adr += serializedSizeOfKey(nodekey)
                                    index++
                                    if (compareRightLimit(nodekey2) <= 0) {
                                        nodekey2
                                    } else {
                                        null
                                    }
                                }
                            }
                        }
                    } else {
                        return { null }
                    }
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(adr) // continue search in next page of this node
            } else {
                // end of node reached without finding the key...
                return { null }
            }
        }
    }
}
class NodeInSIPPath<K>(@JvmField var nodeNumber: Int, @JvmField var page: Page, @JvmField var adr: Long, @JvmField var key: K?, @JvmField var pointer: Int?, @JvmField var index: Int, @JvmField var numberOfElements: Int)
inline fun <K, V> sip_range_search(filename: String, crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long, crossinline deserializePointer: (Page, Long) -> Int, crossinline serializedSizeOfPointer: (Int) -> Long): (K) -> V? {
    val startOffsetInPage = 5
    var node = 0
    val path = mutableListOf<NodeInSIPPath<K>>()
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
        path += currentNodeInPath
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                currentNodeInPath.index = i
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    if (firstByte.bit1()) {
                        // next page for this node
                        // => replace current page with the next page of this node (within the next iteration)
                        path.remove(currentNodeInPath)
                    } else {
                        currentNodeInPath.key = null // last pointer has no key!
                        currentNodeInPath.pointer = pointer
                    }
                    // Anyway we must continue our search in this new node and deal with this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, currentNodeInPath.adr)
                currentNodeInPath.key = nodekey
                currentNodeInPath.pointer = pointer
                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                    break
                }
            }
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                currentNodeInPath.key = nodekey
                val cmp = compareLeftLimit(nodekey)
                val nodevalue = leafNodeDeserializerValue(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfValue(nodevalue)
                if (cmp >= 0) {
                    if (compareRightLimit(nodekey) <= 0) {
                        var first = true
                        currentNodeInPath.index = i
                        var numberOfElementsOfCurrentNode = numberOfElements
                        result_fct@ return {
                            var result: V? = null
                            if (first) {
                                first = false
                                if (compare(it, nodekey) >= 0) {
                                    result = nodevalue
                                }
                            }
                            if (result == null) {
                                var runOverCompleteLeafNode = false
                                loop@ while (true) {
                                    var endReached = false
                                    if (currentNodeInPath.index >= numberOfElementsOfCurrentNode) {
                                        if (page.getByte(0).bit2()) {
                                            endReached = true
                                        } else {
                                            if (runOverCompleteLeafNode) {
                                                // now we have run over a complete leaf node without finding a key >= the key given as parameter
                                                // navigate through inner nodes...
                                                path.remove(currentNodeInPath) // remove leaf node from path
                                                var goDownward = false
                                                sip_loop@ for (i in path.size - 1 downTo 0) {
                                                    var current = path[i] // current inner node
                                                    while (current.index < current.numberOfElements) {
                                                        val pointer = deserializePointer(current.page, current.adr)
                                                        current.adr += serializedSizeOfPointer(pointer)
                                                        current.index++
                                                        if (current.index == current.numberOfElements) {
                                                            // go to next level of inner nodes
                                                            path.remove(current)
                                                            continue@sip_loop
                                                        }
                                                        val nodekey = innerNodeDeserializer(current.page, current.adr)
                                                        current.key = nodekey
                                                        current.pointer = pointer
                                                        current.adr += serializedSizeOfKey(nodekey)
                                                        if (compare(it, nodekey) >= 0) {
                                                            node = pointer
                                                            goDownward = true
                                                            break@sip_loop
                                                        }
                                                    }
                                                }
                                                if (path.size == 0) {
                                                    endReached = true // check if this is correct...
                                                } else if (goDownward) {
                                                    // go downward node with search for key >= it
                                                    searchInInnerNodes@ while (true) {
                                                        page = bufferManager.getPage(filename, node)
                                                        val firstByte = page.getByte(0L) // load status byte
                                                        numberOfElementsOfCurrentNode = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
                                                        currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
                                                        path += currentNodeInPath
                                                        if (firstByte.bit0()) { // inner node
                                                            for (i in 1..numberOfElementsOfCurrentNode) {
                                                                val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                currentNodeInPath.index = i
                                                                if (i == numberOfElementsOfCurrentNode) {
                                                                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                                                                    // otherwise this is a pointer to the next page for this node...
                                                                    if (firstByte.bit1()) {
                                                                        // next page for this node
                                                                        // => replace current page with the next page of this node (within the next iteration)
                                                                        path.remove(currentNodeInPath)
                                                                    } else {
                                                                        currentNodeInPath.key = null // last pointer has no key!
                                                                        currentNodeInPath.pointer = pointer
                                                                    }
                                                                    // Anyway we must continue our search in this new node and deal with this new node in the same way
                                                                    node = pointer
                                                                    break
                                                                }
                                                                val nodekey = innerNodeDeserializer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.key = nodekey
                                                                currentNodeInPath.pointer = pointer
                                                                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                                                                if (compareLeftLimit(nodekey) >= 0) {
                                                                    node = pointer
                                                                    break
                                                                }
                                                            }
                                                        } else {
                                                            // leaf node reached
                                                            break@searchInInnerNodes
                                                        }
                                                        if (firstByte.bit1()) { // node does not fit onto this page
                                                            node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                                                            path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                                                        } else {
                                                            // end of node reached without finding the key...
                                                            result = null
                                                            break@loop
                                                        }
                                                    }
                                                }
                                                runOverCompleteLeafNode = false
                                            } else {
                                                // load next leaf node
                                                val nextPage = page.getInt(currentNodeInPath.adr)
                                                page = bufferManager.getPage(filename, nextPage)
                                                currentNodeInPath.adr = page.getPageIndex() + startOffsetInPage
                                                numberOfElementsOfCurrentNode = page.getInt(1L)
                                                currentNodeInPath.index = 0
                                                runOverCompleteLeafNode = true
                                            }
                                        }
                                    }
                                    if (endReached) {
                                        result = null
                                        break@loop
                                    } else {
                                        val nodekey2 = leafNodeDeserializerKey(page, currentNodeInPath.adr)
                                        currentNodeInPath.key = nodekey2
                                        currentNodeInPath.adr += serializedSizeOfKey(nodekey2)
                                        val nodevalue2 = leafNodeDeserializerValue(page, currentNodeInPath.adr)
                                        currentNodeInPath.adr += serializedSizeOfValue(nodevalue2)
                                        currentNodeInPath.index++
                                        if (compareRightLimit(nodekey2) <= 0) {
                                            if (compare(it, nodekey2) >= 0) {
                                                runOverCompleteLeafNode = false
                                                result = nodevalue2
                                                break@loop
                                            }
                                        } else {
                                            result = null
                                            break@loop
                                        }
                                    }
                                }
                            }
                            result
                        }
                    } else {
                        return { null }
                    }
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
            } else {
                // end of node reached without finding the key...
                return { null }
            }
        }
    }
}
// for B+-tree with only keys (without values)
inline fun <K> sip_range_search(filename: String, crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline deserializePointer: (Page, Long) -> Int, crossinline serializedSizeOfPointer: (Int) -> Long): (K) -> K? {
    val startOffsetInPage = 5
    var node = 0
    val path = mutableListOf<NodeInSIPPath<K>>()
    while (true) {
        var page = bufferManager.getPage(filename, node)
        val firstByte = page.getByte(0L) // load status byte
        val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
        var currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
        path += currentNodeInPath
        if (firstByte.bit0()) { // inner node
            for (i in 1..numberOfElements) {
                val pointer = deserializePointer(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                currentNodeInPath.index = i
                if (i == numberOfElements) {
                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                    // otherwise this is a pointer to the next page for this node...
                    if (firstByte.bit1()) {
                        // next page for this node
                        // => replace current page with the next page of this node (within the next iteration)
                        path.remove(currentNodeInPath)
                    } else {
                        currentNodeInPath.key = null // last pointer has no key!
                        currentNodeInPath.pointer = pointer
                    }
                    // Anyway we must continue our search in this new node and deal with this new node in the same way
                    node = pointer
                    break
                }
                val nodekey = innerNodeDeserializer(page, currentNodeInPath.adr)
                currentNodeInPath.key = nodekey
                currentNodeInPath.pointer = pointer
                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
                    node = pointer
                    break
                }
            }
        } else { // leaf node
            for (i in 1..numberOfElements) {
                val nodekey = leafNodeDeserializerKey(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                currentNodeInPath.key = nodekey
                val cmp = compareLeftLimit(nodekey)
                if (cmp >= 0) {
                    if (compareRightLimit(nodekey) <= 0) {
                        var first = true
                        currentNodeInPath.index = i
                        var numberOfElementsOfCurrentNode = numberOfElements
                        result_fct@ return {
                            var result: K? = null
                            if (first) {
                                first = false
                                if (compare(it, nodekey) >= 0) {
                                    result = nodekey
                                }
                            }
                            if (result == null) {
                                var runOverCompleteLeafNode = false
                                loop@ while (true) {
                                    var endReached = false
                                    if (currentNodeInPath.index >= numberOfElementsOfCurrentNode) {
                                        if (page.getByte(0).bit2()) {
                                            endReached = true
                                        } else {
                                            if (runOverCompleteLeafNode) {
                                                // now we have run over a complete leaf node without finding a key >= the key given as parameter
                                                // navigate through inner nodes...
                                                path.remove(currentNodeInPath) // remove leaf node from path
                                                var goDownward = false
                                                sip_loop@ for (i in path.size - 1 downTo 0) {
                                                    var current = path[i] // current inner node
                                                    while (current.index < current.numberOfElements) {
                                                        val pointer = deserializePointer(current.page, current.adr)
                                                        current.adr += serializedSizeOfPointer(pointer)
                                                        current.index++
                                                        if (current.index == current.numberOfElements) {
                                                            // go to next level of inner nodes
                                                            path.remove(current)
                                                            continue@sip_loop
                                                        }
                                                        val nodekey = innerNodeDeserializer(current.page, current.adr)
                                                        current.key = nodekey
                                                        current.pointer = pointer
                                                        current.adr += serializedSizeOfKey(nodekey)
                                                        if (compare(it, nodekey) >= 0) {
                                                            node = pointer
                                                            goDownward = true
                                                            break@sip_loop
                                                        }
                                                    }
                                                }
                                                if (path.size == 0) {
                                                    endReached = true // check if this is correct...
                                                } else if (goDownward) {
                                                    // go downward node with search for key >= it
                                                    searchInInnerNodes@ while (true) {
                                                        page = bufferManager.getPage(filename, node)
                                                        val firstByte = page.getByte(0L) // load status byte
                                                        numberOfElementsOfCurrentNode = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
                                                        currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
                                                        path += currentNodeInPath
                                                        if (firstByte.bit0()) { // inner node
                                                            for (i in 1..numberOfElementsOfCurrentNode) {
                                                                val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                currentNodeInPath.index = i
                                                                if (i == numberOfElementsOfCurrentNode) {
                                                                    // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                                                                    // otherwise this is a pointer to the next page for this node...
                                                                    if (firstByte.bit1()) {
                                                                        // next page for this node
                                                                        // => replace current page with the next page of this node (within the next iteration)
                                                                        path.remove(currentNodeInPath)
                                                                    } else {
                                                                        currentNodeInPath.key = null // last pointer has no key!
                                                                        currentNodeInPath.pointer = pointer
                                                                    }
                                                                    // Anyway we must continue our search in this new node and deal with this new node in the same way
                                                                    node = pointer
                                                                    break
                                                                }
                                                                val nodekey = innerNodeDeserializer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.key = nodekey
                                                                currentNodeInPath.pointer = pointer
                                                                currentNodeInPath.adr += serializedSizeOfKey(nodekey)
                                                                if (compareLeftLimit(nodekey) >= 0) {
                                                                    node = pointer
                                                                    break
                                                                }
                                                            }
                                                        } else {
                                                            // leaf node reached
                                                            break@searchInInnerNodes
                                                        }
                                                        if (firstByte.bit1()) { // node does not fit onto this page
                                                            node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                                                            path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                                                        } else {
                                                            // end of node reached without finding the key...
                                                            result = null
                                                            break@loop
                                                        }
                                                    }
                                                }
                                                runOverCompleteLeafNode = false
                                            } else {
                                                // load next leaf node
                                                val nextPage = page.getInt(currentNodeInPath.adr)
                                                page = bufferManager.getPage(filename, nextPage)
                                                currentNodeInPath.adr = page.getPageIndex() + startOffsetInPage
                                                numberOfElementsOfCurrentNode = page.getInt(1L)
                                                currentNodeInPath.index = 0
                                                runOverCompleteLeafNode = true
                                            }
                                        }
                                    }
                                    if (endReached) {
                                        result = null
                                        break@loop
                                    } else {
                                        val nodekey2 = leafNodeDeserializerKey(page, currentNodeInPath.adr)
                                        currentNodeInPath.key = nodekey2
                                        currentNodeInPath.adr += serializedSizeOfKey(nodekey2)
                                        currentNodeInPath.index++
                                        if (compareRightLimit(nodekey2) <= 0) {
                                            if (compare(it, nodekey2) >= 0) {
                                                runOverCompleteLeafNode = false
                                                result = nodekey2
                                                break@loop
                                            }
                                        } else {
                                            result = null
                                            break@loop
                                        }
                                    }
                                }
                            }
                            result
                        }
                    } else {
                        return { null }
                    }
                }
            }
            if (firstByte.bit1()) { // node does not fit onto this page
                node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
            } else {
                // end of node reached without finding the key...
                return { null }
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
class B_Plus_Tree<K : Any, V : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    // TODO insertion/deletion of key/value-pairs
    // TODO range search with sip
    // TODO generate considering only full pages (some form of static B_Plus_Tree e.g., for LSM trees etc.)... (However, this means no splitting/merging of nodes according to k/k_star parameters)
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    internal inline fun <K, V> sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): (K) -> V? {
        return sip_range_search(filename, compare, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    /**
     * binary search in inner nodes...
     * prerequirement: size of key is fixed...
     */
    internal inline fun binarySearch(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        var node = 0
        while (true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr += 4
            if (firstByte.bit0()) { // inner node
                var left = 0
                var right = numberOfElements - 1
                while (left < right) {
                    val middle = (left + right) / 2
                    val adrElement = adr + middle * (4 + serializedSizeOfKey)
                    val nodekey = innerNodeDeserializer(page, adrElement + 4)
                    val cmp = compare(nodekey, key)
                    if (cmp < 0) {
                        left = middle + 1
                    } else {
                        right = middle
                    }
                }
                if (left == numberOfElements - 1) {
                    // check if the key already fits or if the right-most pointer must be followed...
                    val nodekey2 = innerNodeDeserializer(page, adr + (numberOfElements - 2) * (4 + serializedSizeOfKey) + 4)
                    if (compare(nodekey2, key) >= 0) {
                        node = page.getInt(adr + (numberOfElements - 2) * (4 + serializedSizeOfKey))
                    } else {
                        // last pointer
                        // is pointing to next page of this inner node (in the case of that the node spans over several pages)
                        // or is pointing to the child node
                        node = page.getInt(adr + (numberOfElements - 1) * (4 + serializedSizeOfKey))
                    }
                } else {
                    node = page.getInt(adr + left * (4 + serializedSizeOfKey))
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                for (i in 1..numberOfElements) {
                    val nodekey = leafNodeDeserializerKey(page, adr)
                    adr += serializedSizeOfKey
                    val cmp = compare(nodekey, key)
                    if (cmp > 0) {
                        throw NotFoundException(key)
                    }
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if (cmp == 0) {
                        return nodevalue
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(key)
                }
            }
        }
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    /**
     * binary search in inner nodes for range search...
     * prerequirement: size of key is fixed...
     */
    internal inline fun range_binary_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        val startOffsetInPage = 5
        var node = 0
        while (true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr += 4
            if (firstByte.bit0()) { // inner node
                var left = 0
                var right = numberOfElements - 1
                while (left < right) {
                    val middle = (left + right) / 2
                    val adrElement = adr + middle * (4 + serializedSizeOfKey)
                    val nodekey = innerNodeDeserializer(page, adrElement + 4)
                    val cmp = compareLeftLimit(nodekey)
                    if (cmp < 0) {
                        left = middle + 1
                    } else {
                        right = middle
                    }
                }
                if (left == numberOfElements - 1) {
                    // check if the key already fits or if the right-most pointer must be followed...
                    val nodekey2 = innerNodeDeserializer(page, adr + (numberOfElements - 2) * (4 + serializedSizeOfKey) + 4)
                    if (compareLeftLimit(nodekey2) >= 0) {
                        node = page.getInt(adr + (numberOfElements - 2) * (4 + serializedSizeOfKey))
                    } else {
                        // last pointer
                        // is pointing to next page of this inner node (in the case of that the node spans over several pages)
                        // or is pointing to the child node
                        node = page.getInt(adr + (numberOfElements - 1) * (4 + serializedSizeOfKey))
                    }
                } else {
                    node = page.getInt(adr + left * (4 + serializedSizeOfKey))
                }
                // continue with new inner or leaf node!
            } else { // leaf node
                for (i in 1..numberOfElements) {
                    val nodekey = leafNodeDeserializerKey(page, adr)
                    adr += serializedSizeOfKey
                    val cmp = compareLeftLimit(nodekey)
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if (cmp >= 0) {
                        if (compareRightLimit(nodekey) <= 0) {
                            var first = true
                            var index = i
                            var numberOfElementsOfCurrentNode = numberOfElements
                            return {
                                if (first) {
                                    first = false
                                    nodevalue
                                } else {
                                    var endReached = false
                                    if (index >= numberOfElementsOfCurrentNode) {
                                        if (page.getByte(0).bit2()) {
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
                                    if (endReached) {
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
                            return { null }
                        }
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    return { null }
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
    internal inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        generate(filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeInt, ::serializedSizeOfInt)
    }
}
class B_Plus_Tree_VariableSizePointers<K : Any, V : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    // TODO insertion/deletion of key/value-pairs
    // TODO range search with sip
    // TODO generate considering only full pages (some form of static B_Plus_Tree e.g., for LSM trees etc.)... (However, this means no splitting/merging of nodes according to k/k_star parameters)
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    internal inline fun <K, V> sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): (K) -> V? {
        return sip_range_search(filename, compare, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    /**
     * @param size: number of entries to be inserted into the tree
     * @param iterator: Iterator of the entries (as key/value pairs) to be inserted. The iterator should deliver the entries in the order of the keys
     * @param k: size of inner nodes
     * @param k_star: size of leaf nodes
     * @param pageSize: size of pages
     */
    internal inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        generate(filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 5, ::serializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
}
class B_Plus_Tree_Difference_Encoding<K : Any, V : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    // TODO still error for k = k_star = 8000, problem if (leaf?) node spans over two pages...
    // TODO insertion/deletion of key/value-pairs
    // TODO store numberOfElements and pointer with variable size
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, innerNodeDeserializerDiff: (Page, Long, K) -> K, serializedSizeOfKeyDiff: (K, K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerKeyDiff: (Page, Long, K) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        var node = 0
        while (true) {
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
                if (compare(nodekeyOld, key) >= 0) {
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
                if (cmp > 0) {
                    throw NotFoundException(key)
                }
                val nodevalue = leafNodeDeserializerValue(page, adr)
                adr += serializedSizeOfValue(nodevalue)
                if (cmp == 0) {
                    return nodevalue
                }
                for (i in 2..numberOfElements) {
                    // other keys with difference encoding
                    val nodekey = leafNodeDeserializerKeyDiff(page, adr, nodekeyOld)
                    adr += serializedSizeOfKeyDiff(nodekey, nodekeyOld)
                    val cmp = compare(nodekey, key)
                    if (cmp > 0) {
                        throw NotFoundException(key)
                    }
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if (cmp == 0) {
                        return nodevalue
                    }
                    nodekeyOld = nodekey
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    throw NotFoundException(key)
                }
            }
        }
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline innerNodeDeserializerDiff: (Page, Long, K) -> K, crossinline serializedSizeOfKeyDiff: (K, K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerKeyDiff: (Page, Long, K) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        val startOffsetInPage = 5
        var node = 0
        while (true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr += 4
            if (firstByte.bit0()) { // inner node
                val pointer = deserializeCompressedInt(page, adr) // page.getInt(adr) // adr += 4
                adr += serializedSizeOfCompressedInt(pointer)
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
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
                var oldkey: K? = null
                for (i in 1..numberOfElements) {
                    val nodekey = if (i == 1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                    adr += if (i == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, oldkey!!)
                    oldkey = nodekey
                    val cmp = compareLeftLimit(nodekey)
                    val nodevalue = leafNodeDeserializerValue(page, adr)
                    adr += serializedSizeOfValue(nodevalue)
                    if (cmp >= 0) {
                        if (compareRightLimit(nodekey) <= 0) {
                            var first = true
                            var index = i
                            var numberOfElementsOfCurrentNode = numberOfElements
                            return {
                                if (first) {
                                    first = false
                                    nodevalue
                                } else {
                                    var endReached = false
                                    if (index >= numberOfElementsOfCurrentNode) {
                                        if (page.getByte(0).bit2()) {
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
                                    if (endReached) {
                                        null
                                    } else {
                                        index++
                                        val nodekey2 = if (index == 1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                                        adr += if (index == 1) serializedSizeOfKey(nodekey2) else serializedSizeOfKeyDiff(nodekey2, oldkey!!)
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
                            return { null }
                        }
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    return { null }
                }
            }
        }
    }
    internal inline fun sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline keyDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline keyDiffDeserializer: (Page, Long, K) -> K, crossinline serializedSizeOfKeyDiff: (K, K) -> Long, crossinline valueDeserializer: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long, crossinline deserializePointer: (Page, Long) -> Int, crossinline serializedSizeOfPointer: (Int) -> Long): (K) -> V? {
        val startOffsetInPage = 5
        var node = 0
        val path = mutableListOf<NodeInSIPPath<K>>()
        while (true) {
            var page = bufferManager.getPage(filename, node)
            val firstByte = page.getByte(0L) // load status byte
            val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
            var currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
            path += currentNodeInPath
            if (firstByte.bit0()) { // inner node
                val pointer = deserializePointer(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                currentNodeInPath.index = 1
                var oldkey = keyDeserializer(page, currentNodeInPath.adr)
                currentNodeInPath.key = oldkey
                currentNodeInPath.pointer = pointer
                currentNodeInPath.adr += serializedSizeOfKey(oldkey)
                if (compareLeftLimit(oldkey) >= 0) {
                    node = pointer
                } else {
                    for (i in 2..numberOfElements) {
                        val pointer = deserializePointer(page, currentNodeInPath.adr)
                        currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                        currentNodeInPath.index = i
                        if (i == numberOfElements) {
                            // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                            // otherwise this is a pointer to the next page for this node...
                            if (firstByte.bit1()) {
                                // next page for this node
                                // => replace current page with the next page of this node (within the next iteration)
                                path.remove(currentNodeInPath)
                            } else {
                                currentNodeInPath.key = null // last pointer has no key!
                                currentNodeInPath.pointer = pointer
                            }
                            // Anyway we must continue our search in this new node and deal with this new node in the same way
                            node = pointer
                            break
                        }
                        val nodekey = keyDiffDeserializer(page, currentNodeInPath.adr, oldkey)
                        currentNodeInPath.key = nodekey
                        currentNodeInPath.pointer = pointer
                        currentNodeInPath.adr += serializedSizeOfKeyDiff(nodekey, oldkey)
                        if (compareLeftLimit(nodekey) >= 0) {
                            node = pointer
                            break
                        }
                        oldkey = nodekey
                    }
                }
            } else { // leaf node
                var oldkey: K? = null
                for (i in 1..numberOfElements) {
                    val nodekey = if (i == 1) keyDeserializer(page, currentNodeInPath.adr) else keyDiffDeserializer(page, currentNodeInPath.adr, oldkey!!)
                    currentNodeInPath.adr += if (i == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, oldkey!!)
                    currentNodeInPath.key = nodekey
                    currentNodeInPath.index = i
                    oldkey = nodekey
                    val cmp = compareLeftLimit(nodekey)
                    val nodevalue = valueDeserializer(page, currentNodeInPath.adr)
                    currentNodeInPath.adr += serializedSizeOfValue(nodevalue)
                    if (cmp >= 0) {
                        if (compareRightLimit(nodekey) <= 0) {
                            var first = true
                            var numberOfElementsOfCurrentNode = numberOfElements
                            result_fct@ return {
                                var result: V? = null
                                if (first) {
                                    first = false
                                    if (compare(it, nodekey) >= 0) {
                                        result = nodevalue
                                    }
                                }
                                if (result == null) {
                                    var runOverCompleteLeafNode = false
                                    loop@ while (true) {
                                        var endReached = false
                                        if (currentNodeInPath.index >= numberOfElementsOfCurrentNode) {
                                            if (page.getByte(0).bit2()) {
                                                endReached = true
                                            } else {
                                                if (runOverCompleteLeafNode) {
                                                    // now we have run over a complete leaf node without finding a key >= the key given as parameter
                                                    // navigate through inner nodes...
                                                    path.remove(currentNodeInPath) // remove leaf node from path
                                                    var goDownward = false
                                                    sip_loop@ for (i in path.size - 1 downTo 0) {
                                                        var current = path[i] // current inner node
                                                        SanityCheck.println { "#: ${current.numberOfElements}" }
                                                        while (current.index < current.numberOfElements) {
                                                            var pointer = deserializePointer(current.page, current.adr)
                                                            current.adr += serializedSizeOfPointer(pointer)
                                                            if (current.index == current.numberOfElements - 1) {
                                                                if (!current.page.getByte(0L).bit1()) {
                                                                    SanityCheck.println { "go upwards" }
                                                                    // go to next level of inner nodes
                                                                    path.remove(current)
                                                                    continue@sip_loop
                                                                }
                                                            }
                                                            current.index++
                                                            val nodekey = if (current.index == 1) keyDeserializer(current.page, current.adr) else keyDiffDeserializer(current.page, current.adr, current.key!!)
                                                            current.adr += if (current.index == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, current.key!!)
                                                            current.key = nodekey
                                                            current.pointer = pointer
                                                            SanityCheck.println { "---> index: ${current.index} nodekey: $nodekey pointer: $pointer" }
                                                            if (compare(it, nodekey) >= 0) {
                                                                node = pointer
                                                                goDownward = true
                                                                break@sip_loop
                                                            }
                                                            if (current.index == current.numberOfElements) {
                                                                if (current.page.getByte(0L).bit1()) {
                                                                    // node spans over two pages...
                                                                    node = page.getInt(current.adr)
                                                                    SanityCheck.println { "node spans several pages, next page: $node" }
                                                                    current.page = bufferManager.getPage(filename, node)
                                                                    current.nodeNumber = node
                                                                    current.numberOfElements = current.page.getInt(1L)
                                                                    SanityCheck.println { "# = ${current.numberOfElements}" }
                                                                    current.adr = 5L
                                                                    pointer = deserializePointer(current.page, current.adr)
                                                                    current.adr += serializedSizeOfPointer(pointer)
                                                                    current.index = 0
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (path.size == 0) {
                                                        endReached = true // check if this is correct...
                                                    } else if (goDownward) {
                                                        SanityCheck.println { "goDownward $node" }
                                                        // go downward node with search for key >= it
                                                        searchInInnerNodes@ while (true) {
                                                            page = bufferManager.getPage(filename, node)
                                                            val firstByte = page.getByte(0L) // load status byte
                                                            numberOfElementsOfCurrentNode = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
                                                            currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
                                                            path += currentNodeInPath
                                                            if (firstByte.bit0()) { // inner node
                                                                val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                currentNodeInPath.index = 1
                                                                var oldkey = keyDeserializer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.key = oldkey
                                                                currentNodeInPath.pointer = pointer
                                                                currentNodeInPath.adr += serializedSizeOfKey(oldkey)
                                                                SanityCheck.println { "first key: " + oldkey + " pointer: " + pointer }
                                                                if (compareLeftLimit(oldkey) >= 0) {
                                                                    node = pointer
                                                                } else {
                                                                    for (i in 2..numberOfElementsOfCurrentNode) {
                                                                        val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                        currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                        currentNodeInPath.index = i
                                                                        if (i == numberOfElementsOfCurrentNode) {
                                                                            // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                                                                            // otherwise this is a pointer to the next page for this node...
                                                                            if (firstByte.bit1()) {
                                                                                // next page for this node
                                                                                // => replace current page with the next page of this node (within the next iteration)
                                                                                path.remove(currentNodeInPath)
                                                                            } else {
                                                                                currentNodeInPath.key = null // last pointer has no key!
                                                                                currentNodeInPath.pointer = pointer
                                                                            }
                                                                            // Anyway we must continue our search in this new node and deal with this new node in the same way
                                                                            node = pointer
                                                                            break
                                                                        }
                                                                        val nodekey = keyDiffDeserializer(page, currentNodeInPath.adr, oldkey)
                                                                        SanityCheck.println { "key: " + nodekey }
                                                                        currentNodeInPath.adr += serializedSizeOfKeyDiff(nodekey, oldkey)
                                                                        currentNodeInPath.key = nodekey
                                                                        oldkey = nodekey
                                                                        currentNodeInPath.pointer = pointer
                                                                        if (compareLeftLimit(nodekey) >= 0) {
                                                                            node = pointer
                                                                            break
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                // leaf node reached
                                                                break@searchInInnerNodes
                                                            }
                                                            if (firstByte.bit1()) { // node does not fit onto this page
                                                                node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                                                                path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                                                            } else {
                                                                // end of node reached without finding the key...
                                                                result = null
                                                                break@loop
                                                            }
                                                        }
                                                    }
                                                    runOverCompleteLeafNode = false
                                                } else {
                                                    // load next leaf node
                                                    val nextPage = page.getInt(currentNodeInPath.adr)
                                                    page = bufferManager.getPage(filename, nextPage)
                                                    currentNodeInPath.adr = page.getPageIndex() + startOffsetInPage
                                                    numberOfElementsOfCurrentNode = page.getInt(1L)
                                                    currentNodeInPath.index = 0
                                                    currentNodeInPath.nodeNumber = nextPage
                                                    runOverCompleteLeafNode = true
                                                }
                                            }
                                        }
                                        if (endReached) {
                                            result = null
                                            break@loop
                                        } else {
                                            SanityCheck.println { "Leaf node: " + currentNodeInPath.nodeNumber }
                                            val nodekey2 = if (currentNodeInPath.index == 0) keyDeserializer(page, currentNodeInPath.adr) else keyDiffDeserializer(page, currentNodeInPath.adr, currentNodeInPath.key!!)
                                            SanityCheck.println { "key: " + nodekey2 }
                                            currentNodeInPath.adr += if (currentNodeInPath.index == 0) serializedSizeOfKey(nodekey2) else serializedSizeOfKeyDiff(nodekey2, currentNodeInPath.key!!)
                                            currentNodeInPath.key = nodekey2
                                            val nodevalue2 = valueDeserializer(page, currentNodeInPath.adr)
                                            currentNodeInPath.adr += serializedSizeOfValue(nodevalue2)
                                            currentNodeInPath.index++
                                            if (compareRightLimit(nodekey2) <= 0) {
                                                if (compare(it, nodekey2) >= 0) {
                                                    runOverCompleteLeafNode = false
                                                    result = nodevalue2
                                                    break@loop
                                                }
                                            } else {
                                                result = null
                                                break@loop
                                            }
                                        }
                                    }
                                }
                                result
                            }
                        } else {
                            return { null }
                        }
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                    path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                } else {
                    // end of node reached without finding the key...
                    return { null }
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
    internal inline fun generate(size: Int, iterator: Iterator<Pair<K, V>>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        return generateDifferenceEncodedBPlusTree(this.filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeKeyDiff, serializedSizeOfKeyDiff, serializeValue, serializedSizeOfValue)
    }
}
class B_Plus_Tree_Difference_Encoding_OnlyKeys<K : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    // TODO still error for k = k_star = 8000, problem if (leaf?) node spans over two pages...
    // TODO insertion/deletion of key/value-pairs
    // TODO store numberOfElements and pointer with variable size
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, innerNodeDeserializerDiff: (Page, Long, K) -> K, serializedSizeOfKeyDiff: (K, K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerKeyDiff: (Page, Long, K) -> K): Boolean {
        var node = 0
        while (true) {
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
                if (compare(nodekeyOld, key) >= 0) {
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
                if (cmp > 0) {
                    throw NotFoundException(key)
                }
                if (cmp == 0) {
                    return true
                }
                for (i in 2..numberOfElements) {
                    // other keys with difference encoding
                    val nodekey = leafNodeDeserializerKeyDiff(page, adr, nodekeyOld)
                    adr += serializedSizeOfKeyDiff(nodekey, nodekeyOld)
                    val cmp = compare(nodekey, key)
                    if (cmp > 0) {
                        throw NotFoundException(key)
                    }
                    if (cmp == 0) {
                        return true
                    }
                    nodekeyOld = nodekey
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    return false
                }
            }
        }
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline innerNodeDeserializerDiff: (Page, Long, K) -> K, crossinline serializedSizeOfKeyDiff: (K, K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerKeyDiff: (Page, Long, K) -> K): () -> K? {
        val startOffsetInPage = 5
        var node = 0
        while (true) {
            var page = bufferManager.getPage(filename, node)
            var adr = 0L
            val firstByte = page.getByte(adr) // load status byte
            adr++
            val numberOfElements = page.getInt(adr) // stores only the number of elements on this page. There could be more on the next page for this node!
            adr += 4
            if (firstByte.bit0()) { // inner node
                val pointer = deserializeCompressedInt(page, adr) // page.getInt(adr) // adr += 4
                adr += serializedSizeOfCompressedInt(pointer)
                val nodekey = innerNodeDeserializer(page, adr)
                adr += serializedSizeOfKey(nodekey)
                if (compareLeftLimit(nodekey) >= 0) {
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
                var oldkey: K? = null
                for (i in 1..numberOfElements) {
                    val nodekey = if (i == 1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                    adr += if (i == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, oldkey!!)
                    oldkey = nodekey
                    val cmp = compareLeftLimit(nodekey)
                    if (cmp >= 0) {
                        if (compareRightLimit(nodekey) <= 0) {
                            var first = true
                            var index = i
                            var numberOfElementsOfCurrentNode = numberOfElements
                            return {
                                if (first) {
                                    first = false
                                    nodekey
                                } else {
                                    var endReached = false
                                    if (index >= numberOfElementsOfCurrentNode) {
                                        if (page.getByte(0).bit2()) {
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
                                    if (endReached) {
                                        null
                                    } else {
                                        index++
                                        val nodekey2 = if (index == 1) leafNodeDeserializerKey(page, adr) else leafNodeDeserializerKeyDiff(page, adr, oldkey!!)
                                        adr += if (index == 1) serializedSizeOfKey(nodekey2) else serializedSizeOfKeyDiff(nodekey2, oldkey!!)
                                        oldkey = nodekey2
                                        if (compareRightLimit(nodekey2) <= 0) {
                                            nodekey2
                                        } else {
                                            null
                                        }
                                    }
                                }
                            }
                        } else {
                            return { null }
                        }
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(adr) // continue search in next page of this node
                } else {
                    // end of node reached without finding the key...
                    return { null }
                }
            }
        }
    }
    internal inline fun sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline keyDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline keyDiffDeserializer: (Page, Long, K) -> K, crossinline serializedSizeOfKeyDiff: (K, K) -> Long, crossinline deserializePointer: (Page, Long) -> Int, crossinline serializedSizeOfPointer: (Int) -> Long): (K) -> K? {
        val startOffsetInPage = 5
        var node = 0
        val path = mutableListOf<NodeInSIPPath<K>>()
        while (true) {
            var page = bufferManager.getPage(filename, node)
            val firstByte = page.getByte(0L) // load status byte
            val numberOfElements = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
            var currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
            path += currentNodeInPath
            if (firstByte.bit0()) { // inner node
                val pointer = deserializePointer(page, currentNodeInPath.adr)
                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                currentNodeInPath.index = 1
                var oldkey = keyDeserializer(page, currentNodeInPath.adr)
                currentNodeInPath.key = oldkey
                currentNodeInPath.pointer = pointer
                currentNodeInPath.adr += serializedSizeOfKey(oldkey)
                if (compareLeftLimit(oldkey) >= 0) {
                    node = pointer
                } else {
                    for (i in 2..numberOfElements) {
                        val pointer = deserializePointer(page, currentNodeInPath.adr)
                        currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                        currentNodeInPath.index = i
                        if (i == numberOfElements) {
                            // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                            // otherwise this is a pointer to the next page for this node...
                            if (firstByte.bit1()) {
                                // next page for this node
                                // => replace current page with the next page of this node (within the next iteration)
                                path.remove(currentNodeInPath)
                            } else {
                                currentNodeInPath.key = null // last pointer has no key!
                                currentNodeInPath.pointer = pointer
                            }
                            // Anyway we must continue our search in this new node and deal with this new node in the same way
                            node = pointer
                            break
                        }
                        val nodekey = keyDiffDeserializer(page, currentNodeInPath.adr, oldkey)
                        currentNodeInPath.key = nodekey
                        currentNodeInPath.pointer = pointer
                        currentNodeInPath.adr += serializedSizeOfKeyDiff(nodekey, oldkey)
                        if (compareLeftLimit(nodekey) >= 0) {
                            node = pointer
                            break
                        }
                        oldkey = nodekey
                    }
                }
            } else { // leaf node
                var oldkey: K? = null
                for (i in 1..numberOfElements) {
                    val nodekey = if (i == 1) keyDeserializer(page, currentNodeInPath.adr) else keyDiffDeserializer(page, currentNodeInPath.adr, oldkey!!)
                    currentNodeInPath.adr += if (i == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, oldkey!!)
                    currentNodeInPath.key = nodekey
                    currentNodeInPath.index = i
                    oldkey = nodekey
                    val cmp = compareLeftLimit(nodekey)
                    if (cmp >= 0) {
                        if (compareRightLimit(nodekey) <= 0) {
                            var first = true
                            var numberOfElementsOfCurrentNode = numberOfElements
                            result_fct@ return {
                                var result: K? = null
                                if (first) {
                                    first = false
                                    if (compare(it, nodekey) >= 0) {
                                        result = nodekey
                                    }
                                }
                                if (result == null) {
                                    var runOverCompleteLeafNode = false
                                    loop@ while (true) {
                                        var endReached = false
                                        if (currentNodeInPath.index >= numberOfElementsOfCurrentNode) {
                                            if (page.getByte(0).bit2()) {
                                                endReached = true
                                            } else {
                                                if (runOverCompleteLeafNode) {
                                                    // now we have run over a complete leaf node without finding a key >= the key given as parameter
                                                    // navigate through inner nodes...
                                                    path.remove(currentNodeInPath) // remove leaf node from path
                                                    var goDownward = false
                                                    sip_loop@ for (i in path.size - 1 downTo 0) {
                                                        var current = path[i] // current inner node
                                                        SanityCheck.println { "#: ${current.numberOfElements}" }
                                                        while (current.index < current.numberOfElements) {
                                                            if (current.index == current.numberOfElements - 1) {
                                                                if (current.page.getByte(0L).bit1()) {
                                                                    // node spans over two pages...
                                                                    node = current.page.getInt(current.adr) // deserializePointer(current.page, current.adr)
                                                                    SanityCheck.println { "node spans several pages, next page: $node" }
                                                                    current.page = bufferManager.getPage(filename, node)
                                                                    current.nodeNumber = node
                                                                    current.numberOfElements = current.page.getInt(1L)
                                                                    SanityCheck.println { "# = ${current.numberOfElements}" }
                                                                    current.adr = 5L
                                                                    current.index = 0
                                                                }
                                                            }
                                                            var pointer = deserializePointer(current.page, current.adr)
                                                            current.adr += serializedSizeOfPointer(pointer)
                                                            if (current.index == current.numberOfElements - 1) {
                                                                if (!current.page.getByte(0L).bit1()) {
                                                                    SanityCheck.println { "go upwards" }
                                                                    // go to next level of inner nodes
                                                                    // TODO: check current key of inner node, maybe it must be going down following the most right pointer
                                                                    path.remove(current)
                                                                    continue@sip_loop
                                                                }
                                                            }
                                                            current.index++
                                                            val nodekey = if (current.index == 1) keyDeserializer(current.page, current.adr) else keyDiffDeserializer(current.page, current.adr, current.key!!)
                                                            current.adr += if (current.index == 1) serializedSizeOfKey(nodekey) else serializedSizeOfKeyDiff(nodekey, current.key!!)
                                                            current.key = nodekey
                                                            current.pointer = pointer
                                                            SanityCheck.println { "---> index: ${current.index} nodekey: $nodekey pointer: $pointer" }
                                                            if (compare(it, nodekey) >= 0) {
                                                                node = pointer
                                                                goDownward = true
                                                                break@sip_loop
                                                            }
                                                        }
                                                    }
                                                    if (path.size == 0) {
                                                        endReached = true // check if this is correct...
                                                    } else if (goDownward) {
                                                        SanityCheck.println { "goDownward $node" }
                                                        // go downward node with search for key >= it
                                                        searchInInnerNodes@ while (true) {
                                                            page = bufferManager.getPage(filename, node)
                                                            val firstByte = page.getByte(0L) // load status byte
                                                            numberOfElementsOfCurrentNode = page.getInt(1L) // stores only the number of elements on this page. There could be more on the next page for this node!
                                                            currentNodeInPath = NodeInSIPPath<K>(node, page, 5L, null, null, 0, numberOfElements)
                                                            path += currentNodeInPath
                                                            if (firstByte.bit0()) { // inner node
                                                                val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                currentNodeInPath.index = 1
                                                                var oldkey = keyDeserializer(page, currentNodeInPath.adr)
                                                                currentNodeInPath.key = oldkey
                                                                currentNodeInPath.pointer = pointer
                                                                currentNodeInPath.adr += serializedSizeOfKey(oldkey)
                                                                SanityCheck.println { "first key: " + oldkey + " pointer: " + pointer }
                                                                if (compareLeftLimit(oldkey) >= 0) {
                                                                    node = pointer
                                                                } else {
                                                                    for (i in 2..numberOfElementsOfCurrentNode) {
                                                                        val pointer = deserializePointer(page, currentNodeInPath.adr)
                                                                        currentNodeInPath.adr += serializedSizeOfPointer(pointer)
                                                                        currentNodeInPath.index = i
                                                                        if (i == numberOfElementsOfCurrentNode) {
                                                                            // if the node fits onto this page (or is the last page for this node), then this is a pointer to rightest child node
                                                                            // otherwise this is a pointer to the next page for this node...
                                                                            if (firstByte.bit1()) {
                                                                                // next page for this node
                                                                                // => replace current page with the next page of this node (within the next iteration)
                                                                                path.remove(currentNodeInPath)
                                                                            } else {
                                                                                currentNodeInPath.key = null // last pointer has no key!
                                                                                currentNodeInPath.pointer = pointer
                                                                            }
                                                                            // Anyway we must continue our search in this new node and deal with this new node in the same way
                                                                            node = pointer
                                                                            break
                                                                        }
                                                                        val nodekey = keyDiffDeserializer(page, currentNodeInPath.adr, oldkey)
                                                                        SanityCheck.println { "key: " + nodekey }
                                                                        currentNodeInPath.adr += serializedSizeOfKeyDiff(nodekey, oldkey)
                                                                        currentNodeInPath.key = nodekey
                                                                        oldkey = nodekey
                                                                        currentNodeInPath.pointer = pointer
                                                                        if (compareLeftLimit(nodekey) >= 0) {
                                                                            node = pointer
                                                                            break
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                // leaf node reached
                                                                break@searchInInnerNodes
                                                            }
                                                            if (firstByte.bit1()) { // node does not fit onto this page
                                                                node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                                                                path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                                                            } else {
                                                                // end of node reached without finding the key...
                                                                result = null
                                                                break@loop
                                                            }
                                                        }
                                                    }
                                                    runOverCompleteLeafNode = false
                                                } else {
                                                    // load next leaf node
                                                    val nextPage = page.getInt(currentNodeInPath.adr)
                                                    page = bufferManager.getPage(filename, nextPage)
                                                    currentNodeInPath.adr = page.getPageIndex() + startOffsetInPage
                                                    numberOfElementsOfCurrentNode = page.getInt(1L)
                                                    currentNodeInPath.index = 0
                                                    currentNodeInPath.nodeNumber = nextPage
                                                    runOverCompleteLeafNode = true
                                                }
                                            }
                                        }
                                        if (endReached) {
                                            result = null
                                            break@loop
                                        } else {
                                            SanityCheck.println { "Leaf node: " + currentNodeInPath.nodeNumber }
                                            val nodekey2 = if (currentNodeInPath.index == 0) keyDeserializer(page, currentNodeInPath.adr) else keyDiffDeserializer(page, currentNodeInPath.adr, currentNodeInPath.key!!)
                                            SanityCheck.println { "key: " + nodekey2 }
                                            currentNodeInPath.adr += if (currentNodeInPath.index == 0) serializedSizeOfKey(nodekey2) else serializedSizeOfKeyDiff(nodekey2, currentNodeInPath.key!!)
                                            currentNodeInPath.key = nodekey2
                                            currentNodeInPath.index++
                                            if (compareRightLimit(nodekey2) <= 0) {
                                                if (compare(it, nodekey2) >= 0) {
                                                    runOverCompleteLeafNode = false
                                                    result = nodekey2
                                                    break@loop
                                                }
                                            } else {
                                                result = null
                                                break@loop
                                            }
                                        }
                                    }
                                }
                                result
                            }
                        } else {
                            return { null }
                        }
                    }
                }
                if (firstByte.bit1()) { // node does not fit onto this page
                    node = page.getInt(currentNodeInPath.adr) // continue search in next page of this node
                    path.remove(currentNodeInPath) // replace current page with the next page of this node (within the next iteration)
                } else {
                    // end of node reached without finding the key...
                    return { null }
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
    internal inline fun generate(size: Int, iterator: Iterator<K>, k: Int, k_star: Int, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeKeyDiff: (Page, Long, K, K) -> Unit, serializedSizeOfKeyDiff: (K, K) -> Long) {
        return generateDifferenceEncodedBPlusTree(this.filename, size, iterator, k, k_star, PAGESIZE, serializeKey, serializedSizeOfKey, serializeKeyDiff, serializedSizeOfKeyDiff)
    }
}
class B_Plus_Tree_Static<K : Any, V : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    internal inline fun <K, V> sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): (K) -> V? {
        return sip_range_search(filename, compare, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeInt, ::serializedSizeOfInt)
    }
    internal inline fun generate(iterator: Iterator<Pair<K, V>>, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        return generateStaticTree(filename, iterator, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeInt, ::serializedSizeOfInt)
    }
}
class B_Plus_Tree_Static_CompressedPointer<K : Any, V : Any>(@JvmField val filename: String) { // By K:Any and V:Any, neither K nor V can be nullable!
    internal inline fun search(key: K, compare: (K, K) -> Int, innerNodeDeserializer: (Page, Long) -> K, serializedSizeOfKey: (K) -> Long, leafNodeDeserializerKey: (Page, Long) -> K, leafNodeDeserializerValue: (Page, Long) -> V, serializedSizeOfValue: (V) -> Long): V {
        return search(filename, key, compare, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    internal inline fun range_search(compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): () -> V? {
        return range_search(filename, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    internal inline fun <K, V> sip_search(crossinline compare: (K, K) -> Int, crossinline compareLeftLimit: (K) -> Int, crossinline compareRightLimit: (K) -> Int, crossinline innerNodeDeserializer: (Page, Long) -> K, crossinline serializedSizeOfKey: (K) -> Long, crossinline leafNodeDeserializerKey: (Page, Long) -> K, crossinline leafNodeDeserializerValue: (Page, Long) -> V, crossinline serializedSizeOfValue: (V) -> Long): (K) -> V? {
        return sip_range_search(filename, compare, compareLeftLimit, compareRightLimit, innerNodeDeserializer, serializedSizeOfKey, leafNodeDeserializerKey, leafNodeDeserializerValue, serializedSizeOfValue, ::deserializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
    internal inline fun generate(iterator: Iterator<Pair<K, V>>, PAGESIZE: Int, serializeKey: (Page, Long, K) -> Unit, serializedSizeOfKey: (K) -> Long, serializeValue: (Page, Long, V) -> Unit, serializedSizeOfValue: (V) -> Long) {
        return generateStaticTree(filename, iterator, PAGESIZE, serializeKey, serializedSizeOfKey, serializeValue, serializedSizeOfValue, 4, ::serializeCompressedInt, ::serializedSizeOfCompressedInt)
    }
}
