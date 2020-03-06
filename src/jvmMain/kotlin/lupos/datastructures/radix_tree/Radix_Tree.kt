package lupos.datastructures.radix_tree

import kotlin.jvm.JvmField
import kotlin.math.abs
import kotlin.math.min
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s01io.buffer.bufferManager
import lupos.s01io.buffer.Page
import lupos.s01io.buffer.toBytesUTF
import lupos.s01io.buffer.toStringUTF


class NotFoundException : Exception()

class Radix_Tree_MainMemory_Node<V>(@JvmField var label: ByteArray = ByteArray(0), @JvmField var children: Array<Radix_Tree_MainMemory_Node<V>> = arrayOf(), @JvmField var v: V? = null) {
    /**
     * @return a: If a<0: key k is smaller at position -a
     *            If a>0: key k is bigger at position a
     *            If a==0: label is a prefix of key k
     * throws NotFoundException if given key is smaller in length than label (and given key is a prefix of the label)
     */
    fun compareTo(k: ByteArray, offset: Int): Int {
        val remainingLengthKey = k.size - offset
        val maxLengthToCompare = min(remainingLengthKey, this.label.size)
        for (i in 0 until maxLengthToCompare) {
            val diff = (0xFF and k[i + offset].toInt()) - (0xFF and label[i].toInt())
            if (diff < 0) {
                return -(i + 1)
            } else if (diff > 0) {
                return (i + 1)
            }
        }
        if (remainingLengthKey < this.label.size) {
            throw NotFoundException()
        }
        return 0
    }

    /**
     * We assume that label is a prefix of key k...
     * This has to be checked before with e.g. compareTo(...)-method.
     */
    fun get(k: ByteArray, offset: Int): V? {
        val newOffset = offset + this.label.size
        if (newOffset == k.size) {
            // matching leaf node reached
            return this.v
        }
        // do binary search within the children...
        var left = 0
        var right = this.children.size - 1
        var middle = (left + right) / 2
        try {
            while (left <= right) {
                val cmp = this.children[middle].compareTo(k, newOffset)
                when {
                    cmp == 0 -> {
                        return this.children[middle].get(k, newOffset)
                    }
                    cmp == -1 -> {
                        // the first byte of the key is smaller than the label of the children
                        right = middle - 1
                        middle = (left + right) / 2
                    }
                    cmp == 1 -> {
                        // the first byte of the key is greater than the label of the children
                        left = middle + 1
                        middle = (left + right) / 2
                    }
                    else -> {
                        // There is a common prefix between label and key and the length of the key is bigger.
                        // Hence there is no other child matching the key...
                        return null
                    }
                }
            }
        } catch (e: NotFoundException) {
            // Key is smaller than the label of a child, and key is prefix of this label
        }
        return null
    }

    /**
     * We assume that label is a prefix of key k...
     * This has to be checked before with e.g. compareTo(...)-method.
     */
    fun put(k: ByteArray, offset: Int, v: V) {
        val newOffset = offset + this.label.size
        if (newOffset == k.size) {
            // matching leaf node reached
            this.v = v
            return
        }
        // do binary search within the children...
        var left = 0
        var right = this.children.size - 1
        var middle = (left + right) / 2
        while (left <= right) {
            try {
                val cmp = this.children[middle].compareTo(k, newOffset)
                when {
                    cmp == 0 -> {
                        // the value must be inserted into this child!
                        this.children[middle].put(k, newOffset, v)
                        return
                    }
                    cmp == -1 -> {
                        // the first byte of the key is smaller than the label of the children
                        right = middle - 1
                        middle = (left + right) / 2
                    }
                    cmp == 1 -> {
                        // the first byte of the key is greater than the label of the children
                        left = middle + 1
                        middle = (left + right) / 2
                    }
                    else -> {
                        // There is a common prefix between label and key and the length of the key is bigger.
                        // Hence there is no other child matching the key...
                        val absCMP = abs(cmp) - 1
                        this.children[middle].splitNode(absCMP, k, newOffset + absCMP, cmp < 0, v)
                        return
                    }
                }
            } catch (e: NotFoundException) {
                // Key is smaller than the label of a child, and key is prefix of this label
                this.children[middle].splitNode(k.size - newOffset, v)
                return
            }
        }
        // add new child for given key between right and left index positions...
        val newNode = Radix_Tree_MainMemory_Node<V>(k.copyOfRange(newOffset, k.size), arrayOf(), v)
        val newChildren = Array<Radix_Tree_MainMemory_Node<V>>(this.children.size + 1,
                { i ->
                    when {
                        i <= right -> {
                            this.children[i]
                        }
                        i >= left + 1 -> {
                            this.children[i - 1]
                        }
                        else -> {
                            newNode
                        }
                    }
                }
        )
        this.children = newChildren
    }

    fun splitNode(splitOffset: Int, v: V) {
        val newLabelTop = this.label.copyOfRange(0, splitOffset)
        val newLabelBottom = this.label.copyOfRange(splitOffset, this.label.size)
        val bottomNode = Radix_Tree_MainMemory_Node<V>(newLabelBottom, this.children, this.v)
        this.label = newLabelTop
        this.children = arrayOf(bottomNode)
        this.v = v
    }

    fun splitNode(splitOffset: Int, k: ByteArray, keyOffset: Int, keyIsSmaller: Boolean, v: V) {
        val newLabelTop = this.label.copyOfRange(0, splitOffset)
        val newLabelBottom = this.label.copyOfRange(splitOffset, this.label.size)
        val bottomNode = Radix_Tree_MainMemory_Node<V>(newLabelBottom, this.children, this.v)
        val newLabelKey = k.copyOfRange(keyOffset, k.size)
        val keyNode = Radix_Tree_MainMemory_Node<V>(newLabelKey, arrayOf(), v)
        this.label = newLabelTop
        this.children = if (keyIsSmaller) arrayOf(keyNode, bottomNode) else arrayOf(bottomNode, keyNode)
        this.v = null
    }

    fun toStringDataStructure(indent: String): String {
        var result = indent
        if (this.label.size > 0) {
            if (this.label.size % 2 == 0) {
                result += this.label.toStringUTF()
            } else {
                result += this.label.copyOfRange(0, this.label.size - 1).toStringUTF()
            }
        }
        result += (if (this.v != null) " : " + this.v else "") + "\r\n"
        val newIndent = indent + "  "
        if (this.label.size % 2 == 0) {
            for (c in this.children) {
                result += c.toStringDataStructure(newIndent)
            }
        } else {
            val b = this.label[this.label.size - 1]
            for (c in this.children) {
                result += c.toStringDataStructure(newIndent, b)
            }
        }
        return result
    }

    fun toStringDataStructure(indent: String, b: Byte): String {
        var result = indent
        if (this.label.size > 0) {
            if (this.label.size % 2 == 0) {
                result += (byteArrayOf(b) + this.label.copyOfRange(0, this.label.size - 1)).toStringUTF()
            } else {
                result += (byteArrayOf(b) + this.label).toStringUTF()
            }
        }
        result += (if (this.v != null) " : " + this.v else "") + "\r\n"
        val newIndent = indent + "  "
        if (this.label.size % 2 == 0) {
            val b = this.label[this.label.size - 1]
            for (c in this.children) {
                result += c.toStringDataStructure(newIndent, b)
            }
        } else {
            for (c in this.children) {
                result += c.toStringDataStructure(newIndent)
            }
        }
        return result
    }
}

class Radix_Tree_MainMemory<V> {
    val root: Radix_Tree_MainMemory_Node<V> = Radix_Tree_MainMemory_Node<V>()
    fun get(k: ByteArray): V? = this.root.get(k, 0)

    fun get(k: String): V? = this.get(k.toBytesUTF())

    fun put(k: ByteArray, v: V) = this.root.put(k, 0, v)

    fun put(k: String, v: V) = this.put(k.toBytesUTF(), v)

    fun toStringDataStructure(): String = this.root.toStringDataStructure("")
}

fun main() {
    val t = Radix_Tree_MainMemory<Int>()
    val toInsert = listOf("alf", "adalbert", "bert", "bernhard", "adam", "bern")
    var index = 0
    for (input in toInsert) {
        GlobalLogger.log(ELoggerType.DEBUG, { "---> Insert " + input })
        t.put(input, index)
        GlobalLogger.log(ELoggerType.DEBUG, { t.toStringDataStructure() })
        index++
    }
    for (input in toInsert) {
        GlobalLogger.log(ELoggerType.DEBUG, { input + " -> " + t.get(input) })
    }
}

class Static_Radix_Tree<V>(@JvmField val filename: String) {
    fun create(mainMemoryRadixTree: Radix_Tree_MainMemory<V>) {
        var p: Page = bufferManager.getPage(this.filename, 0)
        p.lock()
        val queueOfNodes = ArrayList<Radix_Tree_MainMemory_Node<V>>()
        queueOfNodes += mainMemoryRadixTree.root
        while (queueOfNodes.isNotEmpty()) {
            // !!!!!!!!!!!!!! TODO !!!!!!!!!!!!!!!!!!!!!!!!
        }
    }

    fun maxSizeOfOneNode(node: Radix_Tree_MainMemory_Node<V>): Int = sizeOfOneNode(node, 6) // max. size: assume none-local adddresses with offset => 6 bytes per address

    fun minSizeOfOneNode(node: Radix_Tree_MainMemory_Node<V>): Int = sizeOfOneNode(node, 1) // min. size: assume local adddresses with 1 byte offset

    private fun sizeOfOneNode(node: Radix_Tree_MainMemory_Node<V>, sizePerAddress: Int): Int {
        var size = 4 // size of label
        size += node.label.size * 2 // 1 char is stored in 2 bytes
        // TODO: calculate and consider size of v
        size += 1 // number of children (but 1 byte cannot store 256 (max. value))
        if (node.children.size >= 255) { // hence, in seldom cases, we have 1 byte more to differentiate between 255 and 256
            size += 1
        }
        size += node.children.size / 4 // calculate number of status bytes (2 bits in the status bytes are reserved per address => 1 status byte contains the stati of 4 addressses)
        if (node.children.size % 4 > 0) { // one status byte more in case of the number is not dividable by 4
            size += 1
        }
        // max. size: assume local adddresses with 1 byte offset
        size += node.children.size * sizePerAddress
        return size
    }
}
