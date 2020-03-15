package lupos.s01io

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class SortedMap<K, V>(comparator: Comparator<K>, val arrayAllocator: (Int) -> Array<Pair<K, V>>, val undefValue: V) {
    class LocalComparator<K, V>(val cmp: Comparator<K>) : Comparator<Pair<K, V>> {
        override fun compare(a: Pair<K, V>, b: Pair<K, V>): Int = cmp.compare(a.first, b.first)
    }

    val cmp = LocalComparator<K, V>(comparator)
    val data = SortedSet<Pair<K, V>>(cmp, arrayAllocator)
    fun get(key: K, comparator: Comparator<Pair<K, V>> = cmp): V? = data.get(Pair(key, undefValue), comparator)?.second
    fun set(key: K, value: V, comparator: Comparator<Pair<K, V>> = cmp): V? = data.set(Pair(key, value), comparator)?.second
    fun delete(key: K, comparator: Comparator<Pair<K, V>> = cmp): V? = data.delete(Pair(key, undefValue), comparator)?.second
    fun update(key: K, comparator: Comparator<Pair<K, V>> = cmp, onCreate: () -> V, onUpdate: (V) -> V): V? = data.update(Pair(key, undefValue), comparator, { Pair(key, onCreate()) }, { it -> Pair(key, onUpdate(it.second)) })?.second
    fun forEach(action: (K, V) -> Unit) = data.forEach { action(it.first, it.second) }
}
