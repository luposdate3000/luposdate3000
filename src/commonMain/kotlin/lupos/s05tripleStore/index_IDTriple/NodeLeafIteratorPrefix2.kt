package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class NodeLeafIteratorPrefix2(node: NodeLeaf, prefix: IntArray) : NodeLeafIteratorPrefix(node, prefix) {
    override fun checkTooSmall(): Boolean {
Coverage.funStart(6027)
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] < prefix[1])
    }
    override fun checkNotTooLarge(): Boolean {
Coverage.funStart(6028)
        return (valueNext[0] < prefix[0]) || (valueNext[0] == prefix[0] && valueNext[1] <= prefix[1])
    }
}
