package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class Count1PassThroughIterator(val a: TripleIterator) : TripleIterator() {
    var distinct = 0
    var count = 0
    var flag = 0
    init {
Coverage.funStart(5401)
        if (a.hasNext()) {
Coverage.ifStart(5402)
            a.next()
Coverage.statementStart(5403)
            distinct++
Coverage.statementStart(5404)
            count++
Coverage.statementStart(5405)
            flag = 1
Coverage.statementStart(5406)
        }
Coverage.statementStart(5407)
    }
    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
Coverage.funStart(5408)
        value[0] = a.value[0]
Coverage.statementStart(5409)
        value[1] = a.value[1]
Coverage.statementStart(5410)
        value[2] = a.value[2]
Coverage.statementStart(5411)
        flag = 0
Coverage.statementStart(5412)
        if (a.hasNext()) {
Coverage.ifStart(5413)
            flag = 1
Coverage.statementStart(5414)
            a.next()
Coverage.statementStart(5415)
            count++
Coverage.statementStart(5416)
            if (value[0] != a.value[0]) {
Coverage.ifStart(5417)
                distinct++
Coverage.statementStart(5418)
            }
Coverage.statementStart(5419)
        }
Coverage.statementStart(5420)
        return value[component]
    }
}
