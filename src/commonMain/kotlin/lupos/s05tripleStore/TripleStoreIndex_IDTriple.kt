package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query

class TripleStoreIndex_IDTriple : TripleStoreIndex {
    companion object {
        val NodeNullPointer = 0x8FFFFFFF.toInt()
        val allNodes = MyListGeneric<Node?>()

        fun getNode(idx: Int): Node {
            return allNodes[idx]!!
        }

        fun allocateNodeLeaf(action: (NodeLeaf, Int) -> Unit) {
            val it = allNodes.iterator()
            var i = 0
            while (it.hasNext()) {
                var current = it.next()
                if (current == null) {
                    break
                }
                i++
            }
            var tmp = NodeLeaf(ByteArray(8196))
            allNodes[i] = tmp
            action(tmp, i)
        }

        fun freeNode(node: Int) {
            allNodes[node] = null
        }

        fun freeNodeAndAllRelated(nodeIdx: Int) {
            var idx = nodeIdx
            while (idx != NodeNullPointer) {
                var node = allNodes[idx]!!
                when (node) {
                    is NodeLeaf -> {
                        allNodes[idx] = null
                        idx = node.getNextNode()
                    }
                    else -> {
                        require(false)
                    }
                }
            }
        }
    }

    var firstLeaf = NodeNullPointer

    override fun safeToFolder(filename: String) {
        throw Exception("not implemented")
    }

    override fun loadFromFolder(filename: String) {
        throw Exception("not implemented")
    }

class IteratorS(it:TripleIterator):ColumnIterator(){
init{
next={
var tmp:Value?=null
if(it.hasNext()){
tmp=it.nextS()
}
/*return*/tmp
}
}
}
class IteratorP(it:TripleIterator):ColumnIterator(){
init{
next={
var tmp:Value?=null
if(it.hasNext()){
tmp=it.nextP()
}
/*return*/tmp
}
}
}
class IteratorO(it:TripleIterator):ColumnIterator(){
init{
next={
var tmp:Value?=null
if(it.hasNext()){
tmp=it.nextO()
}
/*return*/tmp
}
}
}

    override fun getIterator(query: Query, filter: List<Value>, projection: List<String>): ColumnIteratorRow {
        val columns = mutableMapOf<String, ColumnIterator>()
        for (s in projection) {
            if (s != "_") {
                columns[s] = ColumnIterator()
            }
        }
        var res = ColumnIteratorRow(columns)
        if (filter.size == 0) {
            if (firstLeaf == NodeNullPointer) {
                return res
            } else {
val node=(getNode(firstLeaf) as NodeLeaf)!!
SanityCheck.check { filter.size == 0 }
                if (projection[0] != "_") {
                    columns[projection[0]] = ColumnIteratorDebug(0, projection[0], IteratorS(node.iterator()))
                    if (projection[1] != "_") {
                        columns[projection[1]] = ColumnIteratorDebug(0, projection[1], IteratorP(node.iterator()))
                        if (projection[2] != "_") {
                            columns[projection[2]] = ColumnIteratorDebug(0, projection[2], IteratorO(node.iterator()))
                        }
                    } else {
                        SanityCheck.check { projection[2] == "_" }
                    }
                } else {
                    SanityCheck.check { projection[1] == "_" }
                    SanityCheck.check { projection[2] == "_" }
                }
            }
        }
        throw Exception("not implemented")
    }

    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        val iteratorImport = BulkImportIterator(dataImport, count, order)
        var iteratorStore: TripleIterator
        if (firstLeaf == NodeNullPointer) {
            iteratorStore = EmptyIterator()
        } else {
            iteratorStore = (getNode(firstLeaf) as NodeLeaf)!!.iterator()
        }
        val iterator = MergeIterator(iteratorImport, iteratorStore)
        if (iterator.hasNext()) {
            var newFirstLeaf = NodeNullPointer
            var node2: NodeLeaf? = null
            allocateNodeLeaf { n, i ->
                newFirstLeaf = i
                node2 = n
            }
            var node = node2!!
            node.initializeWith(iterator)
            while (iterator.hasNext()) {
                allocateNodeLeaf { n, i ->
                    node.setNextNode(i)
                    node = n
                }
                node.initializeWith(iterator)
            }
            freeNodeAndAllRelated(firstLeaf)
            firstLeaf = newFirstLeaf
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
        throw Exception("not implemented")
    }

    override fun remove(a: Value, b: Value, c: Value) {
        throw Exception("not implemented")
    }

    override fun clear() {
        freeNodeAndAllRelated(firstLeaf)
        firstLeaf = NodeNullPointer
    }

    override fun printContents() {
        throw Exception("not implemented")
    }
}

interface Node {
}

inline class NodeLeaf(val data: ByteArray) : Node {
    fun setNextNode(node: Int) {
        write4(4, node)
    }

    fun getNextNode(): Int {
        return read4(4)
    }

    fun setTripleCount(count: Int) {
        write4(0, count)
    }

    fun getTripleCount(): Int {
        return read4(0)
    }
    /*
     * Bytes 0..3 : Number of stored Triples
     * Bytes 4..7 : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of jave/kotlin
     * afterwards :
     *
     * header (Bitlayout 7..0)
     * bits 0..1: # Bytes for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     */

    fun iterator(): TripleIterator {
        return NodeLeafIterator(this)
    }

    inline fun read4(offset: Int): Int {
        return ((data[offset].toInt() shl 24) or (data[offset + 1].toInt() shl 16) or (data[offset + 2].toInt() shl 8) or (data[offset + 3].toInt())).toInt()
    }

    inline fun read3(offset: Int): Int {
        return ((data[offset].toInt() shl 16) or (data[offset + 1].toInt() shl 8) or (data[offset + 2].toInt())).toInt()
    }

    inline fun read2(offset: Int): Int {
        return ((data[offset].toInt() shl 8) or (data[offset + 1].toInt())).toInt()
    }

    inline fun read1(offset: Int): Int {
        return data[offset].toInt()
    }

    inline fun write4(offset: Int, d: Int) {
        data[offset] = (d shr 24).toByte()
        data[offset + 1] = ((d shr 16) and 0xFF).toByte()
        data[offset + 2] = ((d shr 8) and 0xFF).toByte()
        data[offset + 3] = (d and 0xFF).toByte()
    }

    inline fun write3(offset: Int, d: Int) {
        data[offset] = ((d shr 16) and 0xFF).toByte()
        data[offset + 1] = ((d shr 8) and 0xFF).toByte()
        data[offset + 2] = (d and 0xFF).toByte()
    }

    inline fun write2(offset: Int, d: Int) {
        data[offset] = ((d shr 8) and 0xFF).toByte()
        data[offset + 1] = (d and 0xFF).toByte()
    }

    inline fun write1(offset: Int, d: Int) {
        data[offset] = (d and 0xFF).toByte()
    }

    inline fun writeFullTriple(offset: Int, d: IntArray): Int {
        /*
         * assuming enough space
         * return bytes written
         */
        write1(offset, 0b00111111)
        write4(offset + 1, d[0])
        write4(offset + 5, d[1])
        write4(offset + 9, d[2])
        return 13
    }

    inline fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
        /*
         * assuming enough space
         * returns bytes written
         */
        b[0] = l[0] xor d[0]
        b[1] = l[1] xor d[1]
        b[2] = l[2] xor d[2]
        l[0] = d[0]
        l[1] = d[1]
        l[2] = d[2]
        SanityCheck {
            require(d[0] >= 0)
            require(d[1] >= 0)
            require(d[2] >= 0)
            require(l[0] >= 0)
            require(l[1] >= 0)
            require(l[2] >= 0)
            require(b[0] >= 0)
            require(b[1] >= 0)
            require(b[2] >= 0)
        }
        var header = 0
        var localOff = offset + 1
        var flag = false
        if (b[0] >= (1 shl 24)) {
            header = 0b00110000
            write4(localOff, b[0])
            localOff += 4
            flag = true
        } else if (b[0] >= (1 shl 16)) {
            header = 0b00100000
            write3(localOff, b[0])
            localOff += 3
            flag = true
        } else if (b[0] >= (1 shl 8)) {
            header = 0b00010000
            write2(localOff, b[0])
            localOff += 2
            flag = true
        } else if (b[0] >= 0) {
            header = 0b00000000
            write1(localOff, b[0])
            localOff += 1
            flag = true
        }
        if (b[1] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00001100
            } else {
                header = 0b01001100
            }
            write4(localOff, b[1])
            localOff += 4
            flag = true
        } else if (b[1] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00001000
            } else {
                header = 0b01001000
            }
            write3(localOff, b[1])
            localOff += 3
            flag = true
        } else if (b[1] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000100
            } else {
                header = 0b01000100
            }
            write2(localOff, b[1])
            localOff += 2
            flag = true
        } else if (b[1] >= 0 || flag) {
            if (flag) {
                header = header or 0b00000000
            } else {
                header = 0b01000000
            }
            write1(localOff, b[1])
            localOff += 1
            flag = true
        }
        if (b[2] >= (1 shl 24)) {
            if (flag) {
                header = header or 0b00001100
            } else {
                header = 0b10001100
            }
            write4(localOff, b[2])
            localOff += 4
            flag = true
        } else if (b[2] >= (1 shl 16)) {
            if (flag) {
                header = header or 0b00001000
            } else {
                header = 0b10001000
            }
            write3(localOff, b[2])
            localOff += 3
            flag = true
        } else if (b[2] >= (1 shl 8)) {
            if (flag) {
                header = header or 0b00000100
            } else {
                header = 0b10000100
            }
            write2(localOff, b[2])
            localOff += 2
            flag = true
        } else if (b[2] >= 0 || flag) {
            if (flag) {
                header = header or 0b00000000
            } else {
                header = 0b10000000
            }
            write1(localOff, b[2])
            localOff += 1
            flag = true
        }
        write1(offset, header)
        require(flag)//otherwise this triple would equal the last one
        require(localOff > offset + 1)//at least ony byte must have been written additionally to the header
        return localOff - offset
    }

    fun initializeWith(iterator: TripleIterator) {
        require(iterator.hasNext())
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = 8
        var bytesWritten = writeFullTriple(offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = writeDiffTriple(offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        setTripleCount(triples)
        setNextNode(TripleStoreIndex_IDTriple.NodeNullPointer)
    }
}

abstract class TripleIterator() {
    var value = IntArray(3)
    abstract fun hasNext(): Boolean
    abstract fun next(component: Int): Int//write the current triple-data into "value" and update offset
    fun nextS() = next(0)
    fun nextP() = next(1)
    fun nextO() = next(2)
    fun next(): IntArray {
        next(0)
        return value
    }
}

class EmptyIterator() : TripleIterator() {
    override fun hasNext() = false
    override fun next(component: Int) = throw Exception("unreachable")
}

class MergeIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    //assuming no duplicates in each input
    var flag = 0

    init {
        if (a.hasNext() && b.hasNext()) {
            a.next()
            b.next()
            flag = 3
        } else if (a.hasNext()) {
            value = a.value
            flag = 4
        } else if (b.hasNext()) {
            value = b.value
            flag = 5
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        when (flag) {
            3 -> {
                if (a.value[0] < b.value[0] || (a.value[0] == b.value[0] && a.value[1] < b.value[1]) || (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] <= b.value[2])) {
                    if (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] == b.value[2]) {
                        b.next()
                        if (!b.hasNext()) {
                            flag = 1
                        }
                    }
                    value[0] = a.value[0]
                    value[1] = a.value[1]
                    value[2] = a.value[2]
                    a.next()
                    if (!a.hasNext()) {
                        flag -= 1
                    }
                } else {
                    value[0] = b.value[0]
                    value[1] = b.value[1]
                    value[2] = b.value[2]
                    b.next()
                    if (!b.hasNext()) {
                        flag = 1
                    }
                }
            }
            1 -> {
                value = a.value
                flag = 4
            }
            2 -> {
                value = b.value
                flag = 5
            }
            4 -> {
                a.next()
            }
            5 -> {
                b.next()
            }
        }
        return value[component]
    }
}

class NodeLeafIterator(var node: NodeLeaf) : TripleIterator() {
    var remaining = node.getTripleCount()
    var offset = 8
    var counter = IntArray(3)

    override fun hasNext() = remaining > 0

    override fun next(component: Int): Int {
        var header = node.read1(offset)
        var headerA = header and 0b11000000
        if (headerA == 0b0000000) {
            counter[0] = ((header and 0b00110000) shr 16) + 1
            counter[1] = ((header and 0b00001100) shr 8) + 1
            counter[2] = ((header and 0b00000011)) + 1
        } else if (headerA == 0b01000000) {
            counter[0] = 0
            counter[1] = ((header and 0b00001100) shr 8) + 1
            counter[2] = ((header and 0b00000011)) + 1
        } else {
            require(headerA == 0b10000000)
            counter[0] = 0
            counter[1] = 0
            counter[2] = ((header and 0b00000011)) + 1
        }
        offset += 1
        for (i in 0 until 3) {
            when (counter[i]) {
                1 -> {
                    value[i] = value[i] xor node.read1(offset)
                }
                2 -> {
                    value[i] = value[i] xor node.read2(offset)
                }
                3 -> {
                    value[i] = value[i] xor node.read3(offset)
                }
                4 -> {
                    value[i] = value[i] xor node.read4(offset)
                }
            }
            offset += counter[i]
        }
        remaining--
        loop@ while (remaining == 0) {
            var nextNodeIdx = node.getNextNode()
            if (nextNodeIdx != TripleStoreIndex_IDTriple.NodeNullPointer) {
                node = TripleStoreIndex_IDTriple.getNode(nextNodeIdx) as NodeLeaf
                remaining = node.getTripleCount()
                offset = 8
            }
        }
        return value[component]
    }
}

class BulkImportIterator(val data: IntArray, val count: Int, val order: IntArray) : TripleIterator() {
    var offset = 0
    override fun hasNext() = offset < count
    override fun next(component: Int): Int {
        value[0] = data[offset + order[0]]
        value[1] = data[offset + order[1]]
        value[2] = data[offset + order[2]]
        offset += 3
        return value[component]
    }
}
