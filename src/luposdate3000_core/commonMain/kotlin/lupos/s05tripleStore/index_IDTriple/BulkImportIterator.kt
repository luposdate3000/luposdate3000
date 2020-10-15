package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField

class BulkImportIterator(@JvmField val data: IntArray, @JvmField val count: Int, @JvmField val order: IntArray) : TripleIterator() {
    @JvmField
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
