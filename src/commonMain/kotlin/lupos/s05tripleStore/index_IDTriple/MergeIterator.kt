package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class MergeIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    //assuming no duplicates in each input
    var flag = 0
    init {
Coverage.funStart(5437)
        if (a.hasNext() && b.hasNext()) {
Coverage.ifStart(5438)
            a.next()
Coverage.statementStart(5439)
            b.next()
Coverage.statementStart(5440)
            flag = 3
Coverage.statementStart(5441)
        } else if (a.hasNext()) {
Coverage.ifStart(5442)
            value = a.value
Coverage.statementStart(5443)
            flag = 4
Coverage.statementStart(5444)
        } else if (b.hasNext()) {
Coverage.ifStart(5445)
            value = b.value
Coverage.statementStart(5446)
            flag = 5
Coverage.statementStart(5447)
        }
Coverage.statementStart(5448)
    }
    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
Coverage.funStart(5449)
        when (flag) {
            3 -> {
Coverage.whenCaseStart(5451)
                if (a.value[0] < b.value[0] || (a.value[0] == b.value[0] && a.value[1] < b.value[1]) || (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] <= b.value[2])) {
Coverage.ifStart(5452)
                    if (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] == b.value[2]) {
Coverage.ifStart(5453)
                        if (!b.hasNext()) {
Coverage.ifStart(5454)
                            flag = 1
Coverage.statementStart(5455)
                        } else {
Coverage.ifStart(5456)
                            b.next()
Coverage.statementStart(5457)
                        }
Coverage.statementStart(5458)
                    }
Coverage.statementStart(5459)
                    value[0] = a.value[0]
Coverage.statementStart(5460)
                    value[1] = a.value[1]
Coverage.statementStart(5461)
                    value[2] = a.value[2]
Coverage.statementStart(5462)
                    if (!a.hasNext()) {
Coverage.ifStart(5463)
                        flag -= 1
Coverage.statementStart(5464)
                    } else {
Coverage.ifStart(5465)
                        a.next()
Coverage.statementStart(5466)
                    }
Coverage.statementStart(5467)
                } else {
Coverage.ifStart(5468)
                    value[0] = b.value[0]
Coverage.statementStart(5469)
                    value[1] = b.value[1]
Coverage.statementStart(5470)
                    value[2] = b.value[2]
Coverage.statementStart(5471)
                    if (!b.hasNext()) {
Coverage.ifStart(5472)
                        flag = 1
Coverage.statementStart(5473)
                    } else {
Coverage.ifStart(5474)
                        b.next()
Coverage.statementStart(5475)
                    }
Coverage.statementStart(5476)
                }
Coverage.statementStart(5477)
            }
            1 -> {
Coverage.whenCaseStart(5478)
                value = a.value
Coverage.statementStart(5479)
                if (a.hasNext()) {
Coverage.ifStart(5480)
                    flag = 4
Coverage.statementStart(5481)
                } else {
Coverage.ifStart(5482)
                    flag = 0
Coverage.statementStart(5483)
                }
Coverage.statementStart(5484)
            }
            2 -> {
Coverage.whenCaseStart(5485)
                value = b.value
Coverage.statementStart(5486)
                if (b.hasNext()) {
Coverage.ifStart(5487)
                    flag = 5
Coverage.statementStart(5488)
                } else {
Coverage.ifStart(5489)
                    flag = 0
Coverage.statementStart(5490)
                }
Coverage.statementStart(5491)
            }
            4 -> {
Coverage.whenCaseStart(5492)
                a.next()
Coverage.statementStart(5493)
                if (!a.hasNext()) {
Coverage.ifStart(5494)
                    flag = 0
Coverage.statementStart(5495)
                }
Coverage.statementStart(5496)
            }
            5 -> {
Coverage.whenCaseStart(5497)
                b.next()
Coverage.statementStart(5498)
                if (!b.hasNext()) {
Coverage.ifStart(5499)
                    flag = 0
Coverage.statementStart(5500)
                }
Coverage.statementStart(5501)
            }
        }
Coverage.statementStart(5502)
        return value[component]
    }
}
