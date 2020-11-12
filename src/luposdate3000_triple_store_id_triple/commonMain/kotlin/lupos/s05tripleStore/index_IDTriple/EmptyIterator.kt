package lupos.s05tripleStore.index_IDTriple

import lupos.s00misc.SanityCheck

internal class EmptyIterator : TripleIterator() {
    override fun hasNext() = false
    override fun next(component: Int) = SanityCheck.checkUnreachable()
}
