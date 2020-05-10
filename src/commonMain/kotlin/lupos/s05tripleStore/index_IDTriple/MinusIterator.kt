package lupos.s05tripleStore.index_IDTriple

class MinusIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    var flag = 0

    init {
        if (a.hasNext()) {
            a.next()
            flag = 1
            if (b.hasNext()) {
                b.next()
                nextInternal()
            }
        }
    }

    fun nextInternal() {
        flag = 1
        while (true) {
            if (b.value[0] > a.value[0]) {
                break
            } else if (b.value[0] < a.value[0]) {
                if (a.hasNext()) {
                    a.next()
                } else {
                    flag = 0
                    break
                }
            } else if (b.value[1] > a.value[1]) {
                break
            } else if (b.value[1] < a.value[1]) {
                if (a.hasNext()) {
                    a.next()
                } else {
                    flag = 0
                    break
                }
            } else if (b.value[2] > a.value[2]) {
                break
            } else {
                if (a.hasNext()) {
                    a.next()
                    if (b.hasNext()) {
                        b.next()
                    } else {
                        break
                    }
                } else {
                    flag = 0
                    break
                }
            }
            if (b.hasNext()) {
                b.next()
            } else {
                break
            }
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        nextInternal()
        return value[component]
    }
}
