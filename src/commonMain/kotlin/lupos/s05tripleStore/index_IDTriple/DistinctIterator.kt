package lupos.s05tripleStore.index_IDTriple

class DistinctIterator(val a: TripleIterator) : TripleIterator() {
    var flag = 0

    init {
        if (a.hasNext()) {
            a.next()
            value[0] = a.value[0]
            value[1] = a.value[1]
            value[2] = a.value[2]
            flag = 1
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        flag = 0
        while (flag == 0 && a.hasNext()) {
            a.next()
            if (value[0] != a.value[0] || value[1] != a.value[1] || value[2] != a.value[2]) {
                value[0] = a.value[0]
                value[1] = a.value[1]
                value[2] = a.value[2]
                flag = 1
            }
        }
        return value[component]
    }
}
