package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
abstract class NodeLeafIteratorPrefix(var node: NodeLeaf, val prefix: IntArray) : TripleIterator() {
    var remaining = node.getTripleCount()
    var offset = 8
    var counter = IntArray(3)
    var valueNext = IntArray(3)
    var flag = true
    abstract fun checkTooSmall(): Boolean
    abstract fun checkNotTooLarge(): Boolean
    init {
Coverage.funStart(6031)
        nextInternal()
Coverage.statementStart(6032)
        while (flag && checkTooSmall()) {
Coverage.whileLoopStart(6033)
            nextInternal()
Coverage.statementStart(6034)
        }
Coverage.statementStart(6035)
        flag = flag && checkNotTooLarge()
Coverage.statementStart(6036)
    }
    override fun hasNext() = flag
    override fun next(component: Int): Int {
Coverage.funStart(6037)
        value[0] = valueNext[0]
Coverage.statementStart(6038)
        value[1] = valueNext[1]
Coverage.statementStart(6039)
        value[2] = valueNext[2]
Coverage.statementStart(6040)
        nextInternal()
Coverage.statementStart(6041)
        flag = flag && checkNotTooLarge()
Coverage.statementStart(6042)
        return value[component]
    }
    inline fun nextInternal() {
Coverage.funStart(6043)
        while (remaining == 0) {
Coverage.whileLoopStart(6044)
            var nextNodeIdx = node.getNextNode()
Coverage.statementStart(6045)
            if (nextNodeIdx != NodeManager.nodeNullPointer) {
Coverage.ifStart(6046)
                NodeManager.getNode(nextNodeIdx, {
Coverage.statementStart(6047)
                    SanityCheck.check { node != it }
Coverage.statementStart(6048)
                    node = it
Coverage.statementStart(6049)
                    remaining = node.getTripleCount()
Coverage.statementStart(6050)
                    valueNext[0] = 0
Coverage.statementStart(6051)
                    valueNext[1] = 0
Coverage.statementStart(6052)
                    valueNext[2] = 0
Coverage.statementStart(6053)
                    offset = 8
Coverage.statementStart(6054)
                }, {
Coverage.statementStart(6055)
                    throw Exception("unreachable")
                })
Coverage.statementStart(6056)
            } else {
Coverage.ifStart(6057)
                flag = false
Coverage.statementStart(6058)
                return
            }
Coverage.statementStart(6059)
        }
Coverage.statementStart(6060)
        var header = node.data.readInt1(offset)
Coverage.statementStart(6061)
        var headerA = header and 0b11000000
Coverage.statementStart(6062)
        if (headerA == 0b0000000) {
Coverage.ifStart(6063)
            counter[0] = ((header and 0b00110000) shr 4) + 1
Coverage.statementStart(6064)
            counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(6065)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(6066)
        } else if (headerA == 0b01000000) {
Coverage.ifStart(6067)
            counter[0] = 0
Coverage.statementStart(6068)
            counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(6069)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(6070)
        } else {
Coverage.ifStart(6071)
            SanityCheck.check { headerA == 0b10000000 }
Coverage.statementStart(6072)
            counter[0] = 0
Coverage.statementStart(6073)
            counter[1] = 0
Coverage.statementStart(6074)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(6075)
        }
Coverage.statementStart(6076)
        offset += 1
Coverage.statementStart(6077)
        for (i in 0 until 3) {
Coverage.forLoopStart(6078)
            when (counter[i]) {
                1 -> {
Coverage.whenCaseStart(6080)
                    valueNext[i] = valueNext[i] xor node.data.readInt1(offset)
Coverage.statementStart(6081)
                }
                2 -> {
Coverage.whenCaseStart(6082)
                    valueNext[i] = valueNext[i] xor node.data.readInt2(offset)
Coverage.statementStart(6083)
                }
                3 -> {
Coverage.whenCaseStart(6084)
                    valueNext[i] = valueNext[i] xor node.data.readInt3(offset)
Coverage.statementStart(6085)
                }
                4 -> {
Coverage.whenCaseStart(6086)
                    valueNext[i] = valueNext[i] xor node.data.readInt4(offset)
Coverage.statementStart(6087)
                }
            }
Coverage.statementStart(6088)
            offset += counter[i]
Coverage.statementStart(6089)
        }
Coverage.statementStart(6090)
        remaining--
Coverage.statementStart(6091)
    }
}
