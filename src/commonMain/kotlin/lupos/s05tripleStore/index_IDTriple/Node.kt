package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage

interface Node {
    fun iterator(): TripleIterator
    fun iterator3(prefix: IntArray): TripleIterator
    fun iterator2(prefix: IntArray): TripleIterator
    fun iterator1(prefix: IntArray): TripleIterator
    fun getFirstTriple(b: IntArray)
    fun setNextNode(node: Int)
    fun getNextNode(): Int
    fun setTripleCount(count: Int)
    fun getTripleCount(): Int
}
