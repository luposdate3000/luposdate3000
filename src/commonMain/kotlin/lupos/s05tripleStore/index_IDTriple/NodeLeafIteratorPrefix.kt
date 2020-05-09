package lupos.s05tripleStore.index_IDTriple


abstract class NodeLeafIteratorPrefix(var node: NodeLeaf, val prefix: IntArray) : TripleIterator() {
    var remaining = node.getTripleCount()
    var offset = 8
    var counter = IntArray(3)
    var valueNext = IntArray(3)
    var flag = true
    abstract fun checkTooSmall(): Boolean
    abstract fun checkNotTooLarge(): Boolean

    init {
        nextInternal()
        while (flag && checkTooSmall()) {
            nextInternal()
        }
        flag = flag && checkNotTooLarge()
    }

    override fun hasNext() = flag
    override fun next(component: Int): Int {
        value[0] = valueNext[0]
        value[1] = valueNext[1]
        value[2] = valueNext[2]
        nextInternal()
        flag = flag && checkNotTooLarge()
        return value[component]
    }

    fun nextInternal() {
        while (remaining == 0) {
            var nextNodeIdx = node.getNextNode()
            if (nextNodeIdx != NodeManager.nodeNullPointer) {
                val tmp = NodeManager.getNode(nextNodeIdx) as NodeLeaf
                require(node != tmp)
                node = tmp
                remaining = node.getTripleCount()
                valueNext[0] = 0
                valueNext[1] = 0
                valueNext[2] = 0
                offset = 8
            } else {
                flag = false
                return
            }
        }
        var header = node.read1(offset)
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
            require(headerA == 0b10000000)
            counter[0] = 0
            counter[1] = 0
            counter[2] = ((header and 0b00000011)) + 1
        }
        offset += 1
        for (i in 0 until 3) {
            when (counter[i]) {
                1 -> {
                    valueNext[i] = valueNext[i] xor node.read1(offset)
                }
                2 -> {
                    valueNext[i] = valueNext[i] xor node.read2(offset)
                }
                3 -> {
                    valueNext[i] = valueNext[i] xor node.read3(offset)
                }
                4 -> {
                    valueNext[i] = valueNext[i] xor node.read4(offset)
                }
            }
            offset += counter[i]
        }
        remaining--
    }
}
