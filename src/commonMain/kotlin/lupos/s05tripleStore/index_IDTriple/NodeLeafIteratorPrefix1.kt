package lupos.s05tripleStore.index_IDTriple

class NodeLeafIteratorPrefix1(node: ByteArray, prefix: IntArray) : NodeLeafIteratorPrefix(node, prefix) {
    override fun checkTooSmall(): Boolean {
        return (valueNext[0] < prefix[0])
    }

    override fun checkNotTooLarge(): Boolean {
        return (valueNext[0] <= prefix[0])
    }
}
