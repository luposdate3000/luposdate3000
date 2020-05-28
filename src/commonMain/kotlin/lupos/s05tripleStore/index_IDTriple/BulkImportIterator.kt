package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
class BulkImportIterator(val data: IntArray, val count: Int, val order: IntArray) : TripleIterator() {
    var offset = 0
    override fun hasNext(): Boolean {
Coverage.funStart(5395)
        return offset < count
    }
    override fun next(component: Int): Int {
Coverage.funStart(5396)
        value[0] = data[offset + order[0]]
Coverage.statementStart(5397)
        value[1] = data[offset + order[1]]
Coverage.statementStart(5398)
        value[2] = data[offset + order[2]]
Coverage.statementStart(5399)
        offset += 3
Coverage.statementStart(5400)
        return value[component]
    }
}
