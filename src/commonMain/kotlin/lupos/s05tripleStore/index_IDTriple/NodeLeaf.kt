package lupos.s05tripleStore.index_IDTriple
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.BenchmarkUtils
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s00misc.Coverage
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4

object NodeLeaf {
    /*
     * Bytes 0..3 : Number of stored Triples
     * Bytes 4..7 : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of java/kotlin
     * afterwards :
     *
     * header (Bitlayout 7..0)
     * bits 0..1: # Bytes _for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes _for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes _for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     *
     * absolute minimum is 21 used bytes for_ exactly 1 Triple/Node
     */
    fun getFirstTriple(data: ByteArray, b: IntArray) {
        b[0] = data.readInt4(9)
        b[1] = data.readInt4(13)
        b[2] = data.readInt4(17)
    }

    fun iterator(data: ByteArray): TripleIterator {
        return NodeLeafIterator(data)
    }

    fun iterator3(data: ByteArray, prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix3(data, prefix)
    }

    fun iterator2(data: ByteArray, prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix2(data, prefix)
    }

    fun iterator1(data: ByteArray, prefix: IntArray): TripleIterator {
        return NodeLeafIteratorPrefix1(data, prefix)
    }

    fun iterator(data: ByteArray,lock:ReadWriteLock,component:Int): ColumnIterator {
when(component){
0->{
        return NodeLeafColumnIterator0(data,lock)
}
1->{
        return NodeLeafColumnIterator1(data,lock)
}
2->{
        return NodeLeafColumnIterator2(data,lock)
}
else->{
throw Exception("unreachable")
}
}
    }

    fun iterator2(data: ByteArray, prefix: IntArray,lock:ReadWriteLock,component:Int): ColumnIterator {
when(component){
2->{
        return NodeLeafColumnIteratorPrefix2_2(data, prefix,lock)
}
else->{
throw Exception("unreachable")
}
}
    }

    fun iterator1(data: ByteArray, prefix: IntArray,lock:ReadWriteLock,component:Int): ColumnIterator {
when(component){
1->{
        return NodeLeafColumnIteratorPrefix1_1(data, prefix,lock)
}
2->{
        return NodeLeafColumnIteratorPrefix1_2(data, prefix,lock)
}
else->{
throw Exception("unreachable")
}
}
    }

    fun initializeWith(data: ByteArray, iterator: TripleIterator) {
        SanityCheck.check { iterator.hasNext() }
        var tripleCurrent = iterator.next()
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
        val tripleBuf = IntArray(3)
        var offset = 8
        var bytesWritten = NodeShared.writeFullTriple(data, offset, tripleLast)
        offset += bytesWritten
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
        var triples = 1
        while (iterator.hasNext() && offset <= offsetEnd) {
            bytesWritten = NodeShared.writeDiffTriple(data, offset, tripleLast, iterator.next(), tripleBuf)
            offset += bytesWritten
            triples++
        }
        NodeShared.setTripleCount(data, triples)
        NodeShared.setNextNode(data, NodeManager.nodeNullPointer)
    }
}
