package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck

internal class NodeLeafIterator(@JvmField var node: ByteArray, @JvmField var nodeid: Int) : TripleIterator() {
    @JvmField
    var remaining = NodeShared.getTripleCount(node)

    @JvmField
    var offset = NodeLeaf.START_OFFSET

    @JvmField
    var needsReset = true
    override fun hasNext() = remaining > 0
    override fun next(component: Int): Int {
        if (needsReset) {
            needsReset = false
            value[0] = 0
            value[1] = 0
            value[2] = 0
        }
        offset += NodeShared.readTriple111(node, offset, value[0], value[1], value[2]) { v0, v1, v2 ->
            value[0] = v0
            value[1] = v1
            value[2] = v2
        }
        updateRemaining()
        return value[component]
    }

    inline fun updateRemaining() {
        remaining--
        if (remaining == 0) {
            needsReset = true
            offset = NodeLeaf.START_OFFSET
            SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x194" })
            NodeManager.releaseNode(nodeid)
            nodeid = NodeShared.getNextNode(node)
            if (nodeid != NodeManager.nodeNullPointer) {
                SanityCheck.println({ "Outside.refcount($nodeid) ${NodeManager.bufferManager.allPagesRefcounters[nodeid]} x05" })
                NodeManager.getNodeLeaf(nodeid, {
                    SanityCheck.check { node != it }
                    node = it
                    remaining = NodeShared.getTripleCount(node)
                })
            }
        }
    }
}
