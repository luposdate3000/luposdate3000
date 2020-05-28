package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.Coverage

class Count1PassThroughIterator(val a: TripleIterator) : TripleIterator() {
    var distinct = 0
    var count = 0
    var flag = 0

    init {
        if (a.hasNext()) {
            a.next()
            distinct++
            count++
            flag = 1
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        flag = 0
        if (a.hasNext()) {
            flag = 1
            a.next()
            count++
            if (value[0] != a.value[0]) {
                distinct++
            }
        }
        return value[component]
    }
}
