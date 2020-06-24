package lupos.s05tripleStore.index_IDTriple


class BulkImportIterator(val data: IntArray, val count: Int, val order: IntArray) : TripleIterator() {
    var offset = 0
    override fun hasNext(): Boolean {
        return offset < count
    }

    override fun next(component: Int): Int {
        value[0] = data[offset + order[0]]
        value[1] = data[offset + order[1]]
        value[2] = data[offset + order[2]]
        offset += 3
        return value[component]
    }
}
