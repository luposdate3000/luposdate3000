package lupos.s05tripleStore.index_IDTriple

import kotlin.jvm.JvmField

internal class Count1PassThroughIterator(@JvmField val a: TripleIterator) : TripleIterator() {
    @JvmField
    var distinct = 0

    @JvmField
    var count = 0

    @JvmField
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
