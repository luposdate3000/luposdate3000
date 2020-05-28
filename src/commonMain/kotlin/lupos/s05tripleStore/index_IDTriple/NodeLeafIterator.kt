package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
class NodeLeafIterator(var node: NodeLeaf) : TripleIterator() {
    var remaining = node.getTripleCount()
    var offset = 8
    var counter = IntArray(3)
    var needsReset = true
    override fun hasNext() = remaining > 0
    override fun next(component: Int): Int {
Coverage.funStart(5973)
        if (needsReset) {
Coverage.ifStart(5974)
            needsReset = false
Coverage.statementStart(5975)
            value[0] = 0
Coverage.statementStart(5976)
            value[1] = 0
Coverage.statementStart(5977)
            value[2] = 0
Coverage.statementStart(5978)
        }
Coverage.statementStart(5979)
        var header = node.data.readInt1(offset)
Coverage.statementStart(5980)
        var headerA = header and 0b11000000
Coverage.statementStart(5981)
        if (headerA == 0b0000000) {
Coverage.ifStart(5982)
            counter[0] = ((header and 0b00110000) shr 4) + 1
Coverage.statementStart(5983)
            counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(5984)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5985)
        } else if (headerA == 0b01000000) {
Coverage.ifStart(5986)
            counter[0] = 0
Coverage.statementStart(5987)
            counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(5988)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5989)
        } else {
Coverage.ifStart(5990)
            SanityCheck.check { headerA == 0b10000000 }
Coverage.statementStart(5991)
            counter[0] = 0
Coverage.statementStart(5992)
            counter[1] = 0
Coverage.statementStart(5993)
            counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5994)
        }
Coverage.statementStart(5995)
        offset += 1
Coverage.statementStart(5996)
        for (i in 0 until 3) {
Coverage.forLoopStart(5997)
            when (counter[i]) {
                1 -> {
Coverage.whenCaseStart(5999)
                    value[i] = value[i] xor node.data.readInt1(offset)
Coverage.statementStart(6000)
                }
                2 -> {
Coverage.whenCaseStart(6001)
                    value[i] = value[i] xor node.data.readInt2(offset)
Coverage.statementStart(6002)
                }
                3 -> {
Coverage.whenCaseStart(6003)
                    value[i] = value[i] xor node.data.readInt3(offset)
Coverage.statementStart(6004)
                }
                4 -> {
Coverage.whenCaseStart(6005)
                    value[i] = value[i] xor node.data.readInt4(offset)
Coverage.statementStart(6006)
                }
            }
Coverage.statementStart(6007)
            offset += counter[i]
Coverage.statementStart(6008)
        }
Coverage.statementStart(6009)
        remaining--
Coverage.statementStart(6010)
        loop@ while (remaining == 0) {
Coverage.whileLoopStart(6011)
            needsReset = true
Coverage.statementStart(6012)
            offset = 8
Coverage.statementStart(6013)
            var nextNodeIdx = node.getNextNode()
Coverage.statementStart(6014)
            if (nextNodeIdx != NodeManager.nodeNullPointer) {
Coverage.ifStart(6015)
                NodeManager.getNode(nextNodeIdx, {
Coverage.statementStart(6016)
                    SanityCheck.check { node != it }
Coverage.statementStart(6017)
                    node = it
Coverage.statementStart(6018)
                    remaining = node.getTripleCount()
Coverage.statementStart(6019)
                }, {
Coverage.statementStart(6020)
                    throw Exception("unreachable")
                })
Coverage.statementStart(6021)
            } else {
Coverage.ifStart(6022)
                break@loop
            }
Coverage.statementStart(6023)
        }
Coverage.statementStart(6024)
        return value[component]
    }
}
