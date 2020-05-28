package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class ThreadSafeMutableStack<T> {
    @JvmField
    val elements = ThreadSafeMutableList<T>()
    fun isEmpty() = elements.isEmpty()
    fun push(item: T) = elements.add(item)
    fun pop(): T? {
Coverage.funStart(1242)
        val item = elements.lastOrNull()
Coverage.statementStart(1243)
        if (!isEmpty()) {
Coverage.ifStart(1244)
            val idx = elements.size() - 1
Coverage.statementStart(1245)
            elements.removeAt(idx)
Coverage.statementStart(1246)
        }
Coverage.statementStart(1247)
        return item
    }
    override fun toString(): String = elements.toString()
}
