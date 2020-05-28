package lupos.s00misc
import lupos.s00misc.Coverage
data class SortHelper(val variableName: String, val sortType: ESortType) {
    override fun toString() = "$variableName.$sortType"
}
