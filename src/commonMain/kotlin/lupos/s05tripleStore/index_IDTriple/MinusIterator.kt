package lupos.s05tripleStore.index_IDTriple

class MinusIterator(val a: TripleIterator, val b: TripleIterator) : TripleIterator() {
    var flag = 0
    var useMinus = true

    init {
        if (b.hasNext()) {
            b.next()
            println("minus 1 B ${b.value.map { it }}")
        } else {
            useMinus = false
        }
        nextInternal()
    }

    fun nextInternal() {
        flag = 0
        if (a.hasNext()) {
            a.next()
            println("minus 2 A ${a.value.map { it }}")
            flag = 1
            while (useMinus) {
                if (b.value[0] > a.value[0]) {
                    println("minus found 1")
                    break
                } else if (b.value[0] < a.value[0]) {
                } else if (b.value[1] > a.value[1]) {
                    println("minus found 3")
                    break
                } else if (b.value[1] < a.value[1]) {
                } else if (b.value[2] > a.value[2]) {
                    println("minus found 5")
                    break
                } else {
                    if (a.hasNext()) {
                        a.next()
                        println("minus 5 A ${a.value.map { it }}")
                    } else {
                        flag = 0
                        println("minus found 7")
                        break
                    }
                }
                if (b.hasNext()) {
                    b.next()
                    println("minus 7 B ${b.value.map { it }}")
                } else {
                    useMinus = false
                    println("minus found 8")
                    break
                }
            }
        }
    }

    override fun hasNext() = flag != 0
    override fun next(component: Int): Int {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        println("minus use :: ${value.map { it }}")
        nextInternal()
        return value[component]
    }
}
