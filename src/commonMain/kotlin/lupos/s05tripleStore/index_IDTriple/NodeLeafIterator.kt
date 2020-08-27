package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck

class NodeLeafIterator(@JvmField var node: ByteArray,JvmField var nodeid:Int) : TripleIterator() {
    @JvmField
    var remaining = NodeShared.getTripleCount(node)

    @JvmField
    var offset = NodeLeaf.startOffset

    @JvmField
    var counter = IntArray(3)

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
        var header = node.readInt1(offset)
        var headerA = header and 0b11000000
        if (headerA == 0b0000000) {
            counter[0] = ((header and 0b00110000) shr 4) + 1
            counter[1] = ((header and 0b00001100) shr 2) + 1
            counter[2] = ((header and 0b00000011)) + 1
        } else if (headerA == 0b01000000) {
            counter[0] = 0
            counter[1] = ((header and 0b00001100) shr 2) + 1
            counter[2] = ((header and 0b00000011)) + 1
        } else {
            SanityCheck.check { headerA == 0b10000000 }
            counter[0] = 0
            counter[1] = 0
            counter[2] = ((header and 0b00000011)) + 1
        }
        offset += 1
        for (i in 0 until 3) {
            when (counter[i]) {
                1 -> {
                    value[i] = value[i] xor node.readInt1(offset)
                }
                2 -> {
                    value[i] = value[i] xor node.readInt2(offset)
                }
                3 -> {
                    value[i] = value[i] xor node.readInt3(offset)
                }
                4 -> {
                    value[i] = value[i] xor node.readInt4(offset)
                }
            }
            offset += counter[i]
        }
        remaining--
        if (remaining == 0) {
            loop@ while (remaining == 0) {
                needsReset = true
                offset = NodeLeaf.startOffset
                NodeManager.releaseNode(nodeid)
nodeid = NodeShared.getNextNode(node)
                if (nodeid != NodeManager.nodeNullPointer) {
                    NodeManager.getNodeLeaf(nodeid, {
                        SanityCheck.check { node != it }
                        node = it
                        remaining = NodeShared.getTripleCount(node)
                    })
                } else {
                    break@loop
                }
            }
        }
        return value[component]
    }
}
