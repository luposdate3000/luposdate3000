package lupos.s03resultRepresentation


class ValueComparatorFast() : Comparator<Int> {
    override fun compare(a: Int, b: Int): Int {
        return a.compareTo(b)
    }
}
