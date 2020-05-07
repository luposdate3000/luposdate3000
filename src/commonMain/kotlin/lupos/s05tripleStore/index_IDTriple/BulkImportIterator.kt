package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage

class BulkImportIterator(val data: IntArray, val count: Int, val order: IntArray) : TripleIterator() {
    var offset = 0
    override fun hasNext() = offset < count
    override fun next(component: Int): Int {
        value[0] = data[offset + order[0]]
        value[1] = data[offset + order[1]]
        value[2] = data[offset + order[2]]
        offset += 3
        return value[component]
    }
}
