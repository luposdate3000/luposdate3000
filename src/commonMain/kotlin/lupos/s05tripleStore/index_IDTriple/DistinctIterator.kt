package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage

class DistinctIterator(@JvmField val a: TripleIterator) : TripleIterator() {
    @JvmField
    var flag = 0

    init {
        if (a.hasNext()) {
            a.next()
            flag = 1
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        flag = 0
        while (a.hasNext()) {
            a.next()
            if (value[0] != a.value[0] || value[1] != a.value[1] || value[2] != a.value[2]) {
                flag = 1
                break
            }
        }
        return value[component]
    }
}
