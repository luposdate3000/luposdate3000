package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class NodeLeafIteratorPrefix1(node: NodeLeaf, prefix: IntArray) : NodeLeafIteratorPrefix(node, prefix) {
    override fun checkTooSmall(): Boolean {
Coverage.funStart(6025)
        return (valueNext[0] < prefix[0])
    }
    override fun checkNotTooLarge(): Boolean {
Coverage.funStart(6026)
        return (valueNext[0] <= prefix[0])
    }
}
