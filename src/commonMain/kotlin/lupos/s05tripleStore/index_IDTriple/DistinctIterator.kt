package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class DistinctIterator(val a: TripleIterator) : TripleIterator() {
    var flag = 0
    init {
Coverage.funStart(5421)
        if (a.hasNext()) {
Coverage.ifStart(5422)
            a.next()
Coverage.statementStart(5423)
            flag = 1
Coverage.statementStart(5424)
        }
Coverage.statementStart(5425)
    }
    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
Coverage.funStart(5426)
        value[0] = a.value[0]
Coverage.statementStart(5427)
        value[1] = a.value[1]
Coverage.statementStart(5428)
        value[2] = a.value[2]
Coverage.statementStart(5429)
        flag = 0
Coverage.statementStart(5430)
        while (a.hasNext()) {
Coverage.whileLoopStart(5431)
            a.next()
Coverage.statementStart(5432)
            if (value[0] != a.value[0] || value[1] != a.value[1] || value[2] != a.value[2]) {
Coverage.ifStart(5433)
                flag = 1
Coverage.statementStart(5434)
                break
            }
Coverage.statementStart(5435)
        }
Coverage.statementStart(5436)
        return value[component]
    }
}
