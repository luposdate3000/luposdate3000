package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage

class NodeLeafIteratorPrefix2(node: ByteArray, prefix: IntArray) : NodeLeafIteratorPrefix(node, prefix) {
    override fun checkTooSmall(): Boolean {
println("NodeLeafIteratorPrefix $uuid is checking for A ... ${valueNext[0]} ${valueNext[1]} ${prefix[0]} ${prefix[1]} ${(valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] < prefix[1])}")
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] < prefix[1])
    }

    override fun checkNotTooLarge(): Boolean {
println("NodeLeafIteratorPrefix $uuid is checking for B ... ${valueNext[0]} ${valueNext[1]} ${prefix[0]} ${prefix[1]} ${(valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] <= prefix[1])}")
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] <= prefix[1])
    }
}
