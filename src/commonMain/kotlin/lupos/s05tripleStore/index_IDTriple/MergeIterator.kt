package lupos.s05tripleStore.index_IDTriple
class MergeIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    //assuming no duplicates in each input
    var flag = 0

    init {
        if (a.hasNext() && b.hasNext()) {
            a.next()
            b.next()
            flag = 3
        } else if (a.hasNext()) {
            value = a.value
            flag = 4
        } else if (b.hasNext()) {
            value = b.value
            flag = 5
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        when (flag) {
            3 -> {
                if (a.value[0] < b.value[0] || (a.value[0] == b.value[0] && a.value[1] < b.value[1]) || (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] <= b.value[2])) {
                    if (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] == b.value[2]) {
                        b.next()
                        if (!b.hasNext()) {
                            flag = 1
                        }
                    }
                    value[0] = a.value[0]
                    value[1] = a.value[1]
                    value[2] = a.value[2]
                    a.next()
                    if (!a.hasNext()) {
                        flag -= 1
                    }
                } else {
                    value[0] = b.value[0]
                    value[1] = b.value[1]
                    value[2] = b.value[2]
                    b.next()
                    if (!b.hasNext()) {
                        flag = 1
                    }
                }
            }
            1 -> {
                value = a.value
                flag = 4
            }
            2 -> {
                value = b.value
                flag = 5
            }
            4 -> {
                a.next()
                if (!a.hasNext()) {
                    flag = 0
                }
            }
            5 -> {
                b.next()
                if (!b.hasNext()) {
                    flag = 0
                }
            }
        }
        return value[component]
}
}

