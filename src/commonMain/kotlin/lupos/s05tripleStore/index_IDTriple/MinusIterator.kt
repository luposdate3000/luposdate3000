package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class MinusIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    var flag = 0
    var useMinus = true
    init {
Coverage.funStart(5503)
        if (b.hasNext()) {
Coverage.ifStart(5504)
            b.next()
Coverage.statementStart(5505)
        } else {
Coverage.ifStart(5506)
            useMinus = false
Coverage.statementStart(5507)
        }
Coverage.statementStart(5508)
        nextInternal()
Coverage.statementStart(5509)
    }
    fun nextInternal() {
Coverage.funStart(5510)
        flag = 0
Coverage.statementStart(5511)
        if (a.hasNext()) {
Coverage.ifStart(5512)
            a.next()
Coverage.statementStart(5513)
            flag = 1
Coverage.statementStart(5514)
            while (useMinus) {
Coverage.whileLoopStart(5515)
                if (b.value[0] > a.value[0]) {
Coverage.ifStart(5516)
                    break
                } else if (b.value[0] < a.value[0]) {
Coverage.statementStart(5517)
                } else if (b.value[1] > a.value[1]) {
Coverage.ifStart(5518)
                    break
                } else if (b.value[1] < a.value[1]) {
Coverage.statementStart(5519)
                } else if (b.value[2] > a.value[2]) {
Coverage.ifStart(5520)
                    break
                } else {
Coverage.statementStart(5521)
                    if (a.hasNext()) {
Coverage.ifStart(5522)
                        a.next()
Coverage.statementStart(5523)
                    } else {
Coverage.ifStart(5524)
                        flag = 0
Coverage.statementStart(5525)
                        break
                    }
Coverage.statementStart(5526)
                }
Coverage.statementStart(5527)
                if (b.hasNext()) {
Coverage.ifStart(5528)
                    b.next()
Coverage.statementStart(5529)
                } else {
Coverage.ifStart(5530)
                    useMinus = false
Coverage.statementStart(5531)
                    break
                }
Coverage.statementStart(5532)
            }
Coverage.statementStart(5533)
        }
Coverage.statementStart(5534)
    }
    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
Coverage.funStart(5535)
        value[0] = a.value[0]
Coverage.statementStart(5536)
        value[1] = a.value[1]
Coverage.statementStart(5537)
        value[2] = a.value[2]
Coverage.statementStart(5538)
        nextInternal()
Coverage.statementStart(5539)
        return value[component]
    }
}
