package lupos.s05tripleStore.index_IDTriple
class EmptyIterator() : TripleIterator() {
    override fun hasNext() = false
    override fun next(component: Int) = throw Exception("unreachable")
}
