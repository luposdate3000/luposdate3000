package lupos.s05tripleStore.index_IDTriple
import kotlin.jvm.JvmField
internal class DebugPassThroughIterator(@JvmField public val a: TripleIterator) : TripleIterator() {
    val queueS = mutableListOf<Int>()
    val queueP = mutableListOf<Int>()
    val queueO = mutableListOf<Int>()
    override fun hasNext() = a.hasNext()
    override fun next(component: Int): Int {
        a.next()
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        queueS.add(value[0])
        queueP.add(value[1])
        queueO.add(value[2])
        return value[component]
    }
}
